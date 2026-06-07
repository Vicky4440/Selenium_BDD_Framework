package Runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentTest;

import Logics.Drivers;

public class Listeners implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		ReportManager.initReports();
	}

	@Override
	public void onFinish(ITestContext context) {
		// flush and write the HTML report to disk
		ReportManager.flushReports();

		// Optionally send the report by email if configured
		try {
			Data.Configreader cfg = new Data.Configreader();
			if (cfg.isSendReportByEmail()) {
				String host = cfg.getSmtpHost();
				String port = cfg.getSmtpPort();
				String user = cfg.getSmtpUsername();
				String pass = cfg.getSmtpPassword();
				String from = cfg.getSmtpFrom();
				String to = cfg.getSmtpTo();
				boolean tls = cfg.isSmtpTls();
				try {
					ReportManager.sendReportByEmail(host, port, user, pass, from, to, tls);
				} catch (Exception e) {
					// log but don't fail the test run
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// ignore config/email errors
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		// If using Cucumber with TestNG the actual scenario name is passed as a parameter
		// The TestNG method name will often be the generic runner name (e.g. "runScenario").
		// Try to extract a more meaningful scenario name from the test parameters.
		Object[] params = result.getParameters();
		if (params != null && params.length > 0) {
			for (Object p : params) {
				if (p == null) continue;
				try {
					Class<?> cls = p.getClass();

					// Cucumber's PickleWrapper (io.cucumber.testng.PickleWrapper) -> has getPickle().getName()
					if ("io.cucumber.testng.PickleWrapper".equals(cls.getName())) {
						java.lang.reflect.Method getPickle = cls.getMethod("getPickle");
						Object pickle = getPickle.invoke(p);
						if (pickle != null) {
							java.lang.reflect.Method getName = pickle.getClass().getMethod("getName");
							Object name = getName.invoke(pickle);
							if (name != null) {
								testName = name.toString();
								break;
							}
						}
					}

					// Direct Pickle (io.cucumber.plugin.event.Pickle) -> has getName()
					else if ("io.cucumber.plugin.event.Pickle".equals(cls.getName())) {
						java.lang.reflect.Method getName = cls.getMethod("getName");
						Object name = getName.invoke(p);
						if (name != null) { testName = name.toString(); break; }
					}

					// Fallback: use parameter's toString() if it looks reasonable
					else {
						String s = p.toString();
						if (s != null && !s.isEmpty() && s.length() < 300) {
							// avoid generic array/identity strings
							if (!s.matches(".*@[0-9a-fA-F]+$") ) {
								testName = s;
								break;
							}
						}
					}
				} catch (Exception e) {
					// ignore reflection errors and continue to next param
				}
			}
		}

		ReportManager.createTest(testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.fail(result.getThrowable());

		// take screenshot
		try {
			TakesScreenshot ts = (TakesScreenshot) Drivers.driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			Path reportsDir = Path.of(System.getProperty("user.dir"), "Reports", "screenshots");
			Files.createDirectories(reportsDir);
			Path dest = reportsDir.resolve(result.getMethod().getMethodName() + "_" + timestamp + ".png");
			Files.copy(src.toPath(), dest);
			if (test != null) test.addScreenCaptureFromPath(dest.toString());
		} catch (IOException | ClassCastException e) {
			// ignore
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.skip("Test skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not used
	}

}
