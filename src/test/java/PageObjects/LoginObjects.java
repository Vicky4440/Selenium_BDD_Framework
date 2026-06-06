package PageObjects;

public class LoginObjects {

	public String getUsernameFieldXpath() {
		// TODO Auto-generated method stub
		return "//input[@id='user-name']";
	}

	public String getPasswordFieldXpath() {
		// TODO Auto-generated method stub
		return "//input[@id='password']";
	}

	public String getLoginButtonXpath() {
		// TODO Auto-generated method stub
		return "//input[@id='login-button']";
	}

	public String getinvalidCredsErrorMsgXpath() {
		// TODO Auto-generated method stub
		//return "//h3[contains(text(),'Sorry, this user has been locked out.')]";
		return "//h3";
	}

	public String getItemsListXpath() {
		return "//div[@class='inventory_list']";
	}

	public String getCartIconXpath() {
		return "//a[@class='shopping_cart_link']";
	}

	public String getItemsListTitleXpath() {
		return "//span[@class='title' and text()='Products']";
	}

	public String getHeaderXpath() {
		return "//div[@class='header_label']/div[text()='Swag Labs']";
	}

}
