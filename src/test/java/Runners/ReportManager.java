package Runners;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.text.StringEscapeUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.text.StringEscapeUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
public class ReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static Path reportHtmlPath;

    public static void initReports() {
        try {
            Path reportsDir = Path.of(System.getProperty("user.dir"), "Reports");
            Files.createDirectories(reportsDir);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            reportHtmlPath = reportsDir.resolve("AutomationReport_" + timestamp + ".html");

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportHtmlPath.toString());
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            htmlReporter.config().setDocumentTitle("Automation Test Report");
            htmlReporter.config().setReportName("Automation Test Results");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExtentTest createTest(String name) {
        if (extent == null) initReports();
        test = extent.createTest(name);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }
    public static void flushReports() {
        if (extent != null) {
            try {
                extent.flush();
            } catch (Exception e) {
                // ensure flush doesn't throw and prevent test shutdown
                e.printStackTrace();
            }

            // Optional: leave PDF conversion disabled by default. If you want PDF output,
            // uncomment the conversion block below.
            // try {
            //     Path pdf = reportHtmlPath.resolveSibling(reportHtmlPath.getFileName().toString().replaceFirst("\\.html$", ".pdf"));
            //     convertHtmlToPdf(reportHtmlPath, pdf);
            // } catch (Exception e) {
            //     // ignore conversion errors
            //     e.printStackTrace();
            // }
        }
    }

    /**
     * Send the latest/generated HTML report as an email attachment using JavaMail (javax.mail).
     */
    public static void sendReportByEmail(String host, String port, final String username, final String password,
                                         String from, String toCsv, boolean useTls) throws MessagingException {
        // find report file if not set
        if (reportHtmlPath == null) {
            try {
                Path reportsDir = Path.of(System.getProperty("user.dir"), "Reports");
                if (Files.exists(reportsDir) && Files.isDirectory(reportsDir)) {
                    reportHtmlPath = Files.list(reportsDir)
                            .filter(p -> p.getFileName().toString().endsWith(".html"))
                            .sorted((a, b) -> Long.compare(b.toFile().lastModified(), a.toFile().lastModified()))
                            .findFirst().orElse(null);
                }
            } catch (IOException e) {
                // ignore
            }
        }

        if (reportHtmlPath == null) {
            throw new MessagingException("No HTML report file found to attach");
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(username != null && !username.isEmpty()));
        props.put("mail.smtp.starttls.enable", String.valueOf(useTls));
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session;
        if (username != null && !username.isEmpty()) {
            session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } else {
            session = Session.getInstance(props);
        }

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            String[] recipients = toCsv.split(",");
            for (String r : recipients) {
                if (r != null && !r.trim().isEmpty()) message.addRecipient(Message.RecipientType.TO, new InternetAddress(r.trim()));
            }

            message.setSubject("Automation Test Report - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find attached the automation HTML report.");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(reportHtmlPath.toFile());
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(reportHtmlPath.getFileName().toString());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            throw e;
        }
    }

//    private static void convertHtmlToPdf(Path htmlPath, Path pdfPath) throws IOException {
//
//        String html = Files.readString(htmlPath, StandardCharsets.UTF_8);
//
//        // Escape invalid ampersands
//        html = html.replaceAll("&(?!amp;|lt;|gt;|quot;|apos;|#\\d+;)", "&amp;");
//
//        try (OutputStream os = Files.newOutputStream(
//                pdfPath,
//                StandardOpenOption.CREATE,
//                StandardOpenOption.TRUNCATE_EXISTING)) {
//
//            PdfRendererBuilder builder = new PdfRendererBuilder();
//            String baseUri = htmlPath.getParent().toUri().toString();
//
//            builder.withHtmlContent(html, baseUri);
//            builder.toStream(os);
//            builder.run();
//        } catch (Exception e) {
//            throw new IOException("Failed to convert HTML to PDF", e);
//        }
//    }
}
