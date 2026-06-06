package PageOperations;

import java.io.IOException;

import org.testng.Assert;

import Data.Data_Store;
import Logics.CommonOperations;
import Logics.ComponentOperations;
import Logics.Drivers;
import Logics.ExcelLogics;
import Logics.Operations;
import PageObjects.ItemPurchaseObjects;
//import junit.framework.Assert;

public class ItemPurchaseOperations {
	
	ItemPurchaseObjects itemPurchaseObjects;
	
	public ItemPurchaseOperations(){
		itemPurchaseObjects = new ItemPurchaseObjects();
	}
	
	String ProductMasterFilePath = System.getProperty("user.dir")+ "\\src\\test\\java\\Data\\ProductMaster.xlsx";
	
	public void clickOnTheIcon(String iconName) {
		CommonOperations.clickXpathElement(itemPurchaseObjects.getIconXpath(iconName));
		
		
	}

	public void ClickOnTheButtonForSelectedItems(String buttonName) {
		for(int i=0;i<Data_Store.Items.size();i++) {
		String itemName = Data_Store.Items.get(i);
		System.out.println("Itemname = "+itemName);
		CommonOperations.clickXpathElement(itemPurchaseObjects.getButtonXpathForSelectedItems(itemName,buttonName));
		}
		
	}

	
	

	public boolean verifyTheNavigationToThePage(String pageName) {
		return verifyurl() && verifyheader();
	}

	private boolean verifyheader() {
		return CommonOperations.isXpathElementAvailable(itemPurchaseObjects.getHeaderXpath());
	}

	private boolean verifyurl() {
		String urlForCurrentPage = Drivers.driver.getCurrentUrl();
		System.out.println("url for current page= "+ urlForCurrentPage);
	    Assert.assertEquals(Data_Store.UrlForCartPage, urlForCurrentPage);
	    return true; 
	}

	public boolean verifyingItemsInTheCart() {
	boolean result = false ;
		for(int i=0;i<Data_Store.Items.size();i++) {
			String itemName = Data_Store.Items.get(i);
			System.out.println("Itemname = "+itemName);
		result= CommonOperations.isXpathElementAvailable(itemPurchaseObjects.getItemNameXpathInCart(itemName));
		}
		return result;
		
	}

	public void clickOnTheButton(String buttonName) {
		CommonOperations.clickXpathElement(itemPurchaseObjects.getButtonXpath(buttonName));
		
	}

	public void enterValueIntoTheField(String fieldName) {
		CommonOperations.insertXpathElement(itemPurchaseObjects.getInputFieldXpath(fieldName), Data_Store.addressDetails(fieldName));
		
	}

	public boolean verifyingProductDetails(String detail) throws IOException {
		
		boolean result=false;
		for(int i=0;i<Data_Store.Items.size();i++) {
			String itemName = Data_Store.Items.get(i);
			System.out.println("Itemname = "+itemName);
			for(int j=1;j<ExcelLogics.rowCount(ProductMasterFilePath);j++) {
				String productNameInMaster = ExcelLogics.ReadDataRowWiseStringData(ProductMasterFilePath, j).get(0);
				System.out.println("productname in excel ="+productNameInMaster);
				if(itemName.equalsIgnoreCase(productNameInMaster)) {

					String productDescriptionInMaster = ExcelLogics.ReadDataRowWiseStringData(ProductMasterFilePath, j).get(1);
					String productCostInMaster = ExcelLogics.ReadDataRowWiseStringData(ProductMasterFilePath, j).get(2);
					System.out.println("Description in xcel ="+ productDescriptionInMaster);
					System.out.println("cost in xcel ="+ productCostInMaster);
					String actualDescription = CommonOperations.getElementText(itemPurchaseObjects.getDescriptionXpath(itemName));
					String currency = CommonOperations.getElementText(itemPurchaseObjects.getCurrencyXpath(itemName));
					//CommonOperations.isXpathElementAvailable(itemPurchaseObjects.getValueXpath(productCostInMaster));
					String actualCost = currency;
					System.out.println("description in site= "+ actualDescription);
					System.out.println("cost in site= "+ actualCost);
					if(detail.equalsIgnoreCase("description")) {
					    result =Operations.isValueSame(actualDescription, productDescriptionInMaster);
					}
					else if(detail.equalsIgnoreCase("cost")) {
						result =Operations.isValueSame(actualCost, productCostInMaster);
					}
					}
			}
		}
		
		return result;
	}

	

}
