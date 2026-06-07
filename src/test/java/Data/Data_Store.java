package Data;


import java.util.ArrayList;
import java.util.Collections;

public class Data_Store {
	
	public static final String InvalidCredsErrorMsg = "Epic sadface: Sorry, this user has been locked out.";
	public static final String TitleAfterLogin = "Swag Labs";
	public static final String UrlForCartPage ="https://www.saucedemo.com/cart.html";
	//public static final String detalils = {"description","cost"};
	public static String addressDetails(String fieldName) {

		String result = null;
		if(fieldName.equals("First Name")) {
			result= "Vikram";
		}else if(fieldName.equals("Last Name")) {
			result= "Nalawade";
		}else if(fieldName.equals("Zip/Postal Code")){
			result= "522509";
		}
		return result;
	}

	
	public static ArrayList<String> Items = new ArrayList<String>();
	static {
		Collections.addAll(Items,"Sauce Labs Bike Light","Sauce Labs Bolt T-Shirt","Sauce Labs Fleece Jacket");
		
	}
		
	}
	

