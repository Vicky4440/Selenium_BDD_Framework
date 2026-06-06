package Logics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

public class CommonOperations {
	
	static WebDriver driver;
	
	public CommonOperations() {
		driver = Drivers.driver;
	}
	
	
	public static void clickXpathElement(String xpath) {
		//WaitLogic.waitForSeconds(6);
		Drivers.driver.findElement(By.xpath(xpath)).click();
		//driver.findElement(By.xpath("//a/div[text()='Revenue Calculator']")).click(); 
	}
	
	public static String insertXpathElement(String xpath,String inputText) {
		Drivers.driver.findElement(By.xpath(xpath)).sendKeys(inputText);
		return inputText;
	}
	
	public static String getElementText(String xpath) {
		return Drivers.driver.findElement(By.xpath(xpath)).getText();
		
	}
	
	
	
	
	
	
	public static boolean isElementDisplayed(String xpath) {
		return Drivers.driver.findElement(By.xpath(xpath)).isDisplayed();
	}
	
	public static boolean isXpathElementAvailable(String xpath) {
		return Drivers.driver.findElement(By.xpath(xpath)).isDisplayed();
	}


	
	
	

}
