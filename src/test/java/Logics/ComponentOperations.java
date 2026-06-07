package Logics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComponentOperations {
	static WebDriver driver;
	private static final int DEFAULT_WAIT_SECONDS = 15;

	public ComponentOperations() {
		driver = Drivers.driver;
	}

	// Helper to get driver (keeps usage consistent)
	private static WebDriver d() {
		return Drivers.driver;
	}

	// ---------- Clicks ----------
	public static void clickXpathElement(String xpath) {
		click(By.xpath(xpath));
	}

	public static void click(By by) {
		waitForClickable(by, DEFAULT_WAIT_SECONDS).click();
	}

	public static void jsClick(By by) {
		WebElement el = waitForPresence(by, DEFAULT_WAIT_SECONDS);
		((JavascriptExecutor) d()).executeScript("arguments[0].click();", el);
	}

	public static boolean safeClick(By by, int attempts) {
		int tries = 0;
		while (tries < attempts) {
			try {
				click(by);
				return true;
			} catch (Exception e) {
				tries++;
				sleep(500);
			}
		}
		return false;
	}

	// ---------- Input / text ----------
	public static void sendKeys(By by, String text) {
		WebElement el = waitForVisible(by, DEFAULT_WAIT_SECONDS);
		el.clear();
		el.sendKeys(text);
	}

	public static void sendKeysXpath(String xpath, String text) {
		sendKeys(By.xpath(xpath), text);
	}

	public static void clear(By by) {
		WebElement el = waitForVisible(by, DEFAULT_WAIT_SECONDS);
		el.clear();
	}

	public static String getText(By by) {
		return waitForVisible(by, DEFAULT_WAIT_SECONDS).getText();
	}

	public static String getTextXpath(String xpath) {
		return getText(By.xpath(xpath));
	}

	public static String getAttribute(By by, String attribute) {
		return waitForPresence(by, DEFAULT_WAIT_SECONDS).getAttribute(attribute);
	}

	// ---------- Checks ----------
	public static boolean isDisplayed(By by) {
		try {
			return waitForVisible(by, DEFAULT_WAIT_SECONDS).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEnabled(By by) {
		try {
			return waitForPresence(by, DEFAULT_WAIT_SECONDS).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isSelected(By by) {
		try {
			return waitForPresence(by, DEFAULT_WAIT_SECONDS).isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	// ---------- Waits ----------
	public static WebElement waitForPresence(By by, int seconds) {
		WebDriverWait wait = new WebDriverWait(d(), Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static WebElement waitForVisible(By by, int seconds) {
		WebDriverWait wait = new WebDriverWait(d(), Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static WebElement waitForClickable(By by, int seconds) {
		WebDriverWait wait = new WebDriverWait(d(), Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// ---------- Actions ----------
	public static void hover(By by) {
		Actions actions = new Actions(d());
		actions.moveToElement(waitForVisible(by, DEFAULT_WAIT_SECONDS)).perform();
	}

	public static void doubleClick(By by) {
		Actions actions = new Actions(d());
		actions.doubleClick(waitForVisible(by, DEFAULT_WAIT_SECONDS)).perform();
	}

	public static void rightClick(By by) {
		Actions actions = new Actions(d());
		actions.contextClick(waitForVisible(by, DEFAULT_WAIT_SECONDS)).perform();
	}

	public static void dragAndDrop(By source, By target) {
		Actions actions = new Actions(d());
		actions.dragAndDrop(waitForVisible(source, DEFAULT_WAIT_SECONDS), waitForVisible(target, DEFAULT_WAIT_SECONDS)).perform();
	}

	public static void scrollIntoView(By by) {
		WebElement el = waitForPresence(by, DEFAULT_WAIT_SECONDS);
		((JavascriptExecutor) d()).executeScript("arguments[0].scrollIntoView(true);", el);
	}

	// ---------- Select / Dropdown ----------
	public static void selectByVisibleText(By by, String text) {
		Select s = new Select(waitForVisible(by, DEFAULT_WAIT_SECONDS));
		s.selectByVisibleText(text);
	}

	public static void selectByValue(By by, String value) {
		Select s = new Select(waitForVisible(by, DEFAULT_WAIT_SECONDS));
		s.selectByValue(value);
	}

	public static void selectByIndex(By by, int index) {
		Select s = new Select(waitForVisible(by, DEFAULT_WAIT_SECONDS));
		s.selectByIndex(index);
	}

	// ---------- Alerts & Frames & Windows ----------
	public static void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(d(), Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public static void dismissAlert() {
		WebDriverWait wait = new WebDriverWait(d(), Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
	}

	public static String getAlertText() {
		WebDriverWait wait = new WebDriverWait(d(), Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert.getText();
	}

	public static void switchToFrameByIndex(int index) {
		d().switchTo().frame(index);
	}

	public static void switchToFrameByNameOrId(String nameOrId) {
		d().switchTo().frame(nameOrId);
	}

	public static void switchToFrameByElement(By by) {
		d().switchTo().frame(waitForPresence(by, DEFAULT_WAIT_SECONDS));
	}

	public static void switchToDefaultContent() {
		d().switchTo().defaultContent();
	}

	// ---------- Collections & utilities ----------
	public static List<WebElement> getElements(By by) {
		return d().findElements(by);
	}

	public static int getElementsCount(By by) {
		return getElements(by).size();
	}

	public static void uploadFile(By by, String filePath) {
		waitForPresence(by, DEFAULT_WAIT_SECONDS).sendKeys(filePath);
	}

	public static boolean takeScreenshot(String targetPath) {
		try {
			if (!(d() instanceof TakesScreenshot)) return false;
			File src = ((TakesScreenshot) d()).getScreenshotAs(OutputType.FILE);
			Path dest = Path.of(targetPath);
			Files.createDirectories(dest.getParent());
			Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
