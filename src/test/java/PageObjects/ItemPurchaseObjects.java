package PageObjects;

public class ItemPurchaseObjects {

	public String getIconXpath(String iconName) {
		
		return "//a[@class='shopping_cart_link']";
	}

	public String getButtonXpathForSelectedItems(String itemName, String buttonName) {
		return "//div[text()='"+itemName+"']/../.././following-sibling::div/button[text()='"+buttonName+"']";
	}

	public String getHeaderXpath() {
		return "//span[@class='title' and text()='Your Cart']";
	}

	public String getItemNameXpathInCart(String itemName) {
		return "//div[@class='cart_item']/div/a/div[text()='"+itemName+"']";
	}

	public String getButtonXpath(String buttonName) {
		if(buttonName.equalsIgnoreCase("continue")) {
			return "//input[@name='continue']";
		}
		return "//button[text()='"+buttonName+"']";
	}

	public String getInputFieldXpath(String fieldName) {
		return "//input[@placeholder='"+fieldName+"']";
	}

	public String getDescriptionXpath(String productName) {
		
		return "(//div[text()='"+productName+"']/../following-sibling::div)[1]";
	}

	public String getValueXpath(String itemName) {
		
		return "//div[text()='"+itemName+"']/../following-sibling::div/div/text()[2]";
	}

	public String getCurrencyXpath(String itemName) {
		return "//div[text()='"+itemName+"']/../following-sibling::div/div";
	}

}
