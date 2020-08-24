package smarte.webautomation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import smarte.pagefactory.web.SalesforceCustomFields;
import smarte.utility.ExcelUtility;

public class CreateCustomFields extends BaseTest {
	
	Properties prop = new Properties();
	@BeforeTest
	public void loadDriver() {
		WebDriver driver;
		String propFileName = "/properties/login.properties";

		InputStream inputStream = CreateCustomFields.class.getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {

			}
		}
	}

	@AfterTest
	public void close() {
		driver.quit();
	}	

	@Test
	public void createCustomFieldsForLead() {	
		String dataType, label, name, description;

		XSSFSheet sheet = null;
		List<String> lstStatus = new ArrayList<String>();

		String file = CreateCustomFields.class.getResource("/testdata/Lead.xlsx").getPath();
		sheet = ExcelUtility.openSpreadSheet(file, "Lead");
		int lastRow = sheet.getLastRowNum();
		new SalesforceCustomFields(driver).login(prop.getProperty("username"), prop.getProperty("password"));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int row = 1; row <= lastRow; row++) {			
			try
			{
			new SalesforceCustomFields(driver).clickOnSetupLink();
			new SalesforceCustomFields(driver).clickOnCustomize();
			Thread.sleep(1000);	
			new SalesforceCustomFields(driver).clickOnLead();
			new SalesforceCustomFields(driver).clickOnAddCustomFieldstoLeads();
			Thread.sleep(1000);
			new SalesforceCustomFields(driver).clickOnNew();
		//	Thread.sleep(1000);			
			dataType = 	ExcelUtility.getCellData(sheet, row, "DataType");
			new SalesforceCustomFields(driver).clickOnDataType(dataType);			
			new SalesforceCustomFields(driver).clickOnNext();			
			
			label = ExcelUtility.getCellData(sheet, row, "Label");
			name = ExcelUtility.getCellData(sheet, row, "Name");
			description = ExcelUtility.getCellData(sheet, row, "Description");
			new SalesforceCustomFields(driver).createCustomFieldForLead(label, name, description);
			}
			catch(Exception ex) {
				continue;
			}
		}
	}

	@Test
	public void createCustomFieldsForAccount() throws InterruptedException {
		String dataType,label, name, description;

		XSSFSheet sheet = null;
		List<String> lstStatus = new ArrayList<String>();

		String file = CreateCustomFields.class.getResource("/TestData/Account.xlsx").getPath();

		sheet = ExcelUtility.openSpreadSheet(file, "Sheet1");
		int lastRow = sheet.getLastRowNum();	
		new SalesforceCustomFields(driver).login(prop.getProperty("username"), prop.getProperty("password"));
		
		for (int row = 1; row <= lastRow; row++) {
			
		//	new SalesforceCustomFields(driver).clickOnSetupLink();
			new SalesforceCustomFields(driver).clickOnCustomize();

			new SalesforceCustomFields(driver).clickOnAccount();
			new SalesforceCustomFields(driver).clickOnAddCustomFieldstoAccounts();
			new SalesforceCustomFields(driver).clickOnNewAccount();

			dataType = 	ExcelUtility.getCellData(sheet, row, "DataType");
			new SalesforceCustomFields(driver).clickOnDataType(dataType);			
			new SalesforceCustomFields(driver).clickOnNext();				
			
			label = ExcelUtility.getCellData(sheet, row, "Label");
			name = ExcelUtility.getCellData(sheet, row, "Name");
			description = ExcelUtility.getCellData(sheet, row, "Description");
			new SalesforceCustomFields(driver).createCustomFieldForLead(label, name, description);
		}
	}

	@Test
	public void createCustomFieldsForContact() throws InterruptedException {
		String dataType, label, name, description;

		XSSFSheet sheet = null;
		List<String> lstStatus = new ArrayList<String>();

		String file = CreateCustomFields.class.getResource("/TestData/Contact.xlsx").getPath();

		sheet = ExcelUtility.openSpreadSheet(file, "Sheet1");		

		new SalesforceCustomFields(driver).login(prop.getProperty("username"), prop.getProperty("password"));
		int lastRow = sheet.getLastRowNum();

		for (int row = 1; row <= lastRow; row++) {

			//new SalesforceCustomFields(driver).clickOnSetupLink();
			new SalesforceCustomFields(driver).clickOnCustomize();

			new SalesforceCustomFields(driver).clickOnContact();
			new SalesforceCustomFields(driver).clickOnAddCustomFieldstoContact();
			new SalesforceCustomFields(driver).clickOnNewAccount();

			dataType = 	ExcelUtility.getCellData(sheet, row, "DataType");
			new SalesforceCustomFields(driver).clickOnDataType(dataType);
			
			new SalesforceCustomFields(driver).clickOnNext();
			
			label = ExcelUtility.getCellData(sheet, row, "Label");
			name = ExcelUtility.getCellData(sheet, row, "Name");
			description = ExcelUtility.getCellData(sheet, row, "Description");
			new SalesforceCustomFields(driver).createCustomFieldForLead(label, name, description);					
		}

	}

}
