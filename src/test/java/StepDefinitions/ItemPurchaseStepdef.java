package StepDefinitions;

import PageOperations.ItemPurchaseOperations;
import PageOperations.LoginOperations;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import org.testng.Assert;

import Data.Data_Store;

public class ItemPurchaseStepdef {
	
	ItemPurchaseOperations itemPurchaseOperations;
	LoginOperations loginOperations;
	
	public ItemPurchaseStepdef() {
		itemPurchaseOperations = new ItemPurchaseOperations();
		loginOperations =new LoginOperations();
		
	}
	
	
	
	@When("navigate to the home page")
	public void navigate_to_the_home_page() {
		Assert.assertTrue(loginOperations.VerifyingTheTitle(), "Assert Failure: Expected Title is not displayed");
	
	}

	@Then("click on {string} button for the selected items")
	public void click_on_button_for_the_items(String buttonName) {
		itemPurchaseOperations.ClickOnTheButtonForSelectedItems(buttonName);
	    
	}

	@Then("click on {string} icon")
	public void click_on_icon(String iconName) {
	    itemPurchaseOperations.clickOnTheIcon(iconName);
	}

	@Then("verify the navigation to the {string} page")
	public void verify_the_navigation_to_the_page(String pageName) {
	    Assert.assertTrue(itemPurchaseOperations.verifyTheNavigationToThePage(pageName),"Assert Failure: Cart page is not verified");
	}

	@Then("verify the selected items in the cart")
	public void verify_the_selected_items_in_the_cart() {
	    Assert.assertTrue(itemPurchaseOperations.verifyingItemsInTheCart(), "Assert Failure: Selected Item(s) are not availavble in the cart");
	}

	@Then("click on {string} button")
	public void click_on_button(String buttonName) {
	    itemPurchaseOperations.clickOnTheButton(buttonName);
	}

	@Then("enter {string} into the field")
	public void enter_into_the_field(String fieldName) {
	   itemPurchaseOperations.enterValueIntoTheField(fieldName);
	}

	

	
	@Then("verify the product name with {string}")
	public void verify_the_product_name_with(String detail) throws IOException {
		Assert.assertTrue(itemPurchaseOperations.verifyingProductDetails(detail),"Assert Failure: Product Description mismatch");
	}

	@Then("verify the {string} for the product")
	public void verify_the_for_the_product(String detail) throws IOException {
		Assert.assertTrue(itemPurchaseOperations.verifyingProductDetails(detail),"Assert Failure: Product Description mismatch");
	}

	@Then("verify the'payment' details")
	public void verify_the_payment_details() {
	    
	}

	@Then("verify the {string} details")
	public void verify_the_details(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("verify the {string} based on products")
	public void verify_the_based_on_products(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("verify the display of {string} button")
	public void verify_the_display_of_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("verify the checkout-complete page")
	public void verify_the_checkout_complete_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("verify the display of success message")
	public void verify_the_display_of_success_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("verify the navigation into the home page")
	public void verify_the_navigation_into_the_home_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
