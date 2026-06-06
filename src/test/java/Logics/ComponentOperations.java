package Logics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComponentOperations {
	static WebDriver driver;
	
	public ComponentOperations() {
		driver = Drivers.driver;
	}
	
	public static void clickXpathElement(String xpath) {
		//WaitLogic.waitForSeconds(6);
		Drivers.driver.findElement(By.xpath(xpath)).click();
		//driver.findElement(By.xpath("//a/div[text()='Revenue Calculator']")).click(); 
	}

}
