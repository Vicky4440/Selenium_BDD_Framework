package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;

import org.testng.Assert;

import PageOperations.LoginOperations;

public class LoginStepDef {
	
	LoginOperations loginOperations;
	
	public LoginStepDef() {
	
	loginOperations = new LoginOperations();
	}
	
	@Given("Launch the application on browser")
	public void launch_the_application_on_browser() {
		loginOperations.launchTheApplication();		
	}
	
	@Given("login with {string} username and password")
	public void enter_username_and_password(String credsValidity) {
		loginOperations.EnteringCreds(credsValidity);
	}

	@Then("verify the title of the webpage")
	public void verify_the_title_of_the_webpage() {
	    Assert.assertTrue(loginOperations.VerifyingTheTitle(), "Assert Failure: Expected Title is not displayed");
	}
	
	@Then("verify the home page")
	public void verify_the_home_page() {
	    Assert.assertTrue(loginOperations.VerifyingTheHomePage(), "Assert Failure: Home page  verification failed");
	}
	
	
	
}