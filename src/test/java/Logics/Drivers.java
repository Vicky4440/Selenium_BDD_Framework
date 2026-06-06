package Logics;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drivers {
	
	 public static  WebDriver driver;
	 
	 WebDriverWait wait;
	 
	// WebDriverWait wait;
	//driver = new WebDriver();

	public void instantiateDriver(String browserName) {
		// TODO Auto-generated method stub
		if(browserName.equalsIgnoreCase("chrome")) {
			chrome();
			//driver.manage().timeouts().pageLoadTimeout(300,TimeUnit.SECONDS);
			//driver.manage().window().maximize();
	
		}
	}

	private void chrome() {
		//
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		//ChromeOptions options = new ChromeOptions();
		//driver=new ChromeDriver();
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.getBrowserName();
//		capabilities.getBrowserVersion();
//		options.addArguments("--start-maximized","--incognito");
//		options.addArguments("--remote-allow-origins=*");
//		options.merge(capabilities);
//		WebDriver driver = new ChromeDriver(options);
//		wait = new WebDriverWait(driver,Duration.ofSeconds(100));
		
		 //System.setProperty("webdriver.chrome.driver","C:\\Users\\lenovo\\eclipse-workspace1\\automation\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		//	driver.get("https://www.fitpeo.com");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			wait = new WebDriverWait(driver,Duration.ofSeconds(100));
			
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--remote-allow-origins=*"); 
//        
//        
//        WebDriver driver = new ChromeDriver(options);
//        
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		
		
	}

}
