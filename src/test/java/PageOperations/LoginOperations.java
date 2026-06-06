package PageOperations;



import org.testng.Assert;
import org.testng.annotations.DataProvider;

import Data.Configreader;
import Data.Data_Store;
import Logics.CommonOperations;
import Logics.Drivers;
import PageObjects.LoginObjects;


public class LoginOperations {
	
	Configreader configreader;
	LoginObjects loginObjects;
	
	public LoginOperations() {
		configreader = new Configreader();
		loginObjects = new LoginObjects();
	}

	public void launchTheApplication() {
		Drivers.driver.navigate().to(configreader.getUrl());
		
	}

	public void EnteringCreds(String credsValidity) {
		if(credsValidity.equalsIgnoreCase("valid")) {
			ValidUserLogin();
			
		}else if(credsValidity.equalsIgnoreCase("invalid")) {
			InvalidUserLogin();			
		}
		
	}

	private void InvalidUserLogin() {
		CommonOperations.clickXpathElement(loginObjects.getUsernameFieldXpath());
		CommonOperations.insertXpathElement(loginObjects.getUsernameFieldXpath(),configreader.getInvalidUserName());
		CommonOperations.clickXpathElement(loginObjects.getPasswordFieldXpath());
		CommonOperations.insertXpathElement(loginObjects.getPasswordFieldXpath(),configreader.getPassword());
		CommonOperations.clickXpathElement(loginObjects.getLoginButtonXpath());
		Assert.assertTrue(VerifyingLoginError(),"Invalid Creds Login verification failed");
		
	}

	private void ValidUserLogin() {
		CommonOperations.clickXpathElement(loginObjects.getUsernameFieldXpath());
		CommonOperations.insertXpathElement(loginObjects.getUsernameFieldXpath(),configreader.getUserName());
		CommonOperations.clickXpathElement(loginObjects.getPasswordFieldXpath());
		CommonOperations.insertXpathElement(loginObjects.getPasswordFieldXpath(),configreader.getPassword());
		CommonOperations.clickXpathElement(loginObjects.getLoginButtonXpath());
		
	}

	private boolean VerifyingLoginError() {
		String msg = CommonOperations.getElementText(loginObjects.getinvalidCredsErrorMsgXpath());
		System.out.println("Text= "+msg);
		return CommonOperations.getElementText(loginObjects.getinvalidCredsErrorMsgXpath()).equalsIgnoreCase(Data_Store.InvalidCredsErrorMsg);
	}

	public boolean VerifyingTheTitle() {
		String title = Drivers.driver.getTitle();	
		System.out.println("Title = "+title);
		return title.equalsIgnoreCase(Data_Store.TitleAfterLogin);
	}

	public boolean VerifyingTheHomePage() {
		
		return (VerifyHeader()&& VerifyItemsListTitle() && VerifyCartIcon() && VerifyItemsList());
	}

	private boolean VerifyItemsList() {
		return CommonOperations.isElementDisplayed(loginObjects.getItemsListXpath());
	}

	private boolean VerifyCartIcon() {
		return CommonOperations.isElementDisplayed(loginObjects.getCartIconXpath());
	}

	private boolean VerifyItemsListTitle() {
		return CommonOperations.isElementDisplayed(loginObjects.getItemsListTitleXpath());
	}

	private boolean VerifyHeader() {
		return CommonOperations.isElementDisplayed(loginObjects.getHeaderXpath());
	}
	

	
}
