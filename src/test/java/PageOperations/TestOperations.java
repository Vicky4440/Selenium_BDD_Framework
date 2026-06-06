package PageOperations;

import Logics.Drivers;
import Logics.ExcelLogics;

import java.io.IOException;
import java.util.ArrayList;

import Logics.CommonOperations;
//import PageObjects.TestObject;
import PageObjects.TestObject;

public class TestOperations {
	
	TestObject testObject;
	
	public TestOperations() {
		testObject = new TestObject();
	}

	public void launchTheUrl() {
		
		Drivers.driver.navigate().to("https:facebook.com");
		
		
	}

	public void NaviateToTheTab(String tabName) {
		CommonOperations.clickXpathElement(testObject.getTabXpath(tabName));
		CommonOperations.clickXpathElement("//a/div[text()='"+tabName+"']");
		
		
	}
	

	public void data() throws IOException {
		String path = "C:\\Users\\lenovo\\OneDrive\\Documents\\Book1.xlsx";
		for(int rowNum=0;rowNum<=ExcelLogics.rowCount(path);rowNum++) {
		ArrayList<Object> rowData=(ArrayList<Object>) ExcelLogics.ReadDataRowWise(path, rowNum);
		String Name = (String) rowData.get(1);
		System.out.println("Name: "+Name);
		String AssetName = (String) rowData.get(2);
		System.out.println("Laptop: "+AssetName);
		}
	}

	public void clickinOnLink() {
		// TODO Auto-generated method stub
		//Drivers.driver.findElement(By.xpath("//a[contains(text(),'Forgotten password')]")).click();
		CommonOperations.clickXpathElement("//a[contains(text(),'Forgotten password')]");
		//commonOperations.Operation(("//a[contains(text(),'Forgotten password')]"));
		//ComponentOperations.clickXpathElement("//a[contains(text(),'Forgotten password')]");
		
	}
	

}



