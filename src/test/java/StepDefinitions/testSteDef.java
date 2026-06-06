package StepDefinitions;

import PageOperations.TestOperations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class testSteDef {
	TestOperations testOperations;
	
	
	public testSteDef() {
		testOperations = new TestOperations();
	}
	
//	
//	@Given("launch the url on browser")
//	public void launch_the_url_on_browser() {
//	    testOperations.launchTheUrl();
//	}

	@Then("navigate to the {string}")
	public void navigate_to_the(String TabName) {
		
		testOperations.NaviateToTheTab(TabName);
	    
	}

}
