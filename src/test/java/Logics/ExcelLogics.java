package Logics;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class ExcelLogics {
	
	public void data() throws IOException {
		String filepath = "C:\\Users\\lenovo\\OneDrive\\Documents\\Book1.xlsx";
		for(int rowNum=0;rowNum<=rowCount(filepath);rowNum++) {
		ReadDataRowWise(filepath, rowNum);
		}
	}
	
	public static int rowCount(String filepath) throws IOException {
		FileInputStream inputStream = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount=sheet.getLastRowNum();
		return rowCount;
	}
	
	
	public static List<Object> ReadDataRowWise(String filepath, int rowNum) throws IOException{
		FileInputStream inputStream = new FileInputStream(filepath);
		List<Object> data = new ArrayList<Object>();
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		//XSSFSheet sheet = workbook.getSheet(Sheet1); --fetching sheet with sheet name
		int cols=sheet.getRow(1).getLastCellNum();
		XSSFRow row = sheet.getRow(rowNum);
		for(int c=0;c<cols;c++) {
			XSSFCell cell =row.getCell(c);
			if(row.getCell(c)==null) {
				data.add("");
				continue;
			}else {
				switch(cell.getCellType()) {
				case STRING:
					data.add(cell.getStringCellValue()+"");
					break;
				case NUMERIC:
					data.add(cell.getNumericCellValue()+"");
					break;
				case BOOLEAN:
					data.add(cell.getBooleanCellValue()+"");
					break;
				default:
					break;
				}
			}
		}
		System.out.println("Data: "+data);
		return data;
		
	}
	
	public static List<String> ReadDataRowWiseStringData(String filepath, int rowNum) throws IOException{
		FileInputStream inputStream = new FileInputStream(filepath);
		List<String> data = new ArrayList<String>();
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		//XSSFSheet sheet = workbook.getSheet(Sheet1); --fetching sheet with sheet name
		int cols=sheet.getRow(1).getLastCellNum();
		XSSFRow row = sheet.getRow(rowNum);
		for(int c=0;c<cols;c++) {
			XSSFCell cell =row.getCell(c);
			if(row.getCell(c)==null) {
				data.add("");
				continue;
			}else {
				switch(cell.getCellType()) {
				case STRING:
					data.add(cell.getStringCellValue()+"");
					break;
				default:
					break;
				}
			}
		}
		//System.out.println("Data: "+data);
		return data;
		
	}

}
