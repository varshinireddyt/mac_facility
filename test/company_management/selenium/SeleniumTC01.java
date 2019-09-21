package company_management.selenium;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.runners.MethodSorters;
import company_management.data.CompanyDAO;
import company_management.model.Company;
import company_management.CMFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // the test methods in this test file can run in any order but I prefer a fixed order
public class SeleniumTC01 extends CMFunctions {
  private StringBuffer verificationErrors = new StringBuffer();
  public String sAppURL, sSharedUIMapPath, testDelay;
  
  @Before
  public void setUp() throws Exception {
//	MAGIC CODE GOES HERE 
	System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    prop = new Properties();	  
    prop.load(new FileInputStream("./Configuration/CM_Configuration.properties"));
	sAppURL = prop.getProperty("sAppURL");
	sSharedUIMapPath = prop.getProperty("SharedUIMap");
	testDelay=prop.getProperty("testDelay");
	prop.load(new FileInputStream(sSharedUIMapPath));
  }

  private String [][] getTableContentsFromPage(int listSize) { // this method gets the list company table contents from the web page
	  String [][] companyArray = new String[listSize-1][4];
	  for (int i=0; i<listSize-1; i++) {
		companyArray[i][0]=  driver.findElement(By.xpath(prop.getProperty("listCompany_prefixAddressCompanyTable")+(i+2)+
								prop.getProperty("listCompany_CompanyTableCompanyIDCol"))).getText();
		companyArray[i][1] = driver.findElement(By.xpath(prop.getProperty("listCompany_prefixAddressCompanyTable")+(i+2)+
								prop.getProperty("listCompany_CompanyTableCompanyNameCol"))).getText();
		companyArray[i][2] = driver.findElement(By.xpath(prop.getProperty("listCompany_prefixAddressCompanyTable")+(i+2)+
								prop.getProperty("listCompany_CompanyTableCompanyPhoneCol"))).getText();
		companyArray[i][3] = driver.findElement(By.xpath(prop.getProperty("listCompany_prefixAddressCompanyTable")+(i+2)+
								prop.getProperty("listCompany_CompanyTableCompanyEmailCol"))).getText();
	  }
	  return companyArray;
  }
  
  private String [][] getCompanyListFromDB(int listSize) throws SQLException { // this method gets the list company table contents from the DB
	    ArrayList<Company> fromDB= CompanyDAO.listCompanies();
	    String [][] arrayDB = new String [listSize-1][4];
	    int i=0;
	    for (Company p:fromDB) {
	    	arrayDB[i][0]=p.getIdcompany();
	    	arrayDB[i][1]=p.getCompany_name();
	    	arrayDB[i][2]=p.getPhone();
	    	arrayDB[i][3]=p.getEmail();
	 		i++;
	    }
	    return arrayDB;
  }
  
  private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
	  Boolean diff=false || (array1.length!=array2.length);
	  for (int i=0;i<array1.length && !diff;i++) {
		 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
				 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]);
	  }
	  return diff;
  }
    
  @Test
  @FileParameters("test/TC01a_test_cases.csv")
  public void TC01a(int testCaseNumber, String col1, String col2, String col3, String col4, String col5) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
    driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.listCompanies); //select list Companies from homepage
    // check company listing headers
    verifyHeaders(driver,"listCompany_col1InListCompanyTableHeader",col1,"listCompany_col2InListCompanyTableHeader",col2,
    							  "listCompany_col3InListCompanyTableHeader",col3,"listCompany_col4InListCompanyTableHeader",col4,
    							  "listCompany_col5InListCompanyTableHeader",col5,methodName+" verifyHeaders test case "+testCaseNumber);
    //check company listing table contents by comparing results directly with the same database query
    //Notice - I chose to do this INSTEAD of using a spreadsheet because this is more resistant to changes in the database
	WebElement companyTable = driver.findElement(By.xpath(prop.getProperty("listCompany_companyTable")));
	int rows = companyTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
	assertFalse(arraysDiff(getCompanyListFromDB(rows),getTableContentsFromPage(rows)));
	driver.findElement(By.xpath(prop.getProperty("listCompany_companyMgtAppLink"))).click(); //go back to homepage	
  }

  @Test
  @FileParameters("test/TC01b_test_cases.csv")
  public void TC01b(String errMsg) throws Exception {
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.listCompanies); //select list Companies from homepage
	driver.findElement(By.xpath(prop.getProperty("listCompany_ListSelectedCompanyButton"))).click();
	assertTrue(driver.findElement(By.xpath(prop.getProperty("listCompany_ErrMsg"))).getAttribute("value").equals(errMsg));
	if (testDelay.equals("true"))
		Thread.sleep(3_000);
	driver.findElement(By.xpath(prop.getProperty("listCompany_companyMgtAppLink"))).click(); //go back to homepage	
  }

  @Test
  @FileParameters("test/TC01c_test_cases.csv")
  public void TC01c(int testCaseNumber, String path1, String path2, String col1, String col2, String col3, String col4, String data1, String data2, String data3, String data4) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.listCompanies); //select list Companies from homepage
	// now we select a company
	driver.findElement(By.xpath(path1)).click();
	if (!path2.equals(""))
		driver.findElement(By.name(path2)).click();
	verifyHeaders(driver,"listSpecificCompany_Company_ID_title",col1,"listSpecificCompany_Company_Name_title",col2,
								         "listSpecificCompany_Phone_title",col3,"listSpecificCompany_Email_title",col4,"","",methodName+" verifyHeaders test case "+testCaseNumber);
	verifyContents(driver,"listSpecificCompany_Company_ID_value",data1,"listSpecificCompany_Company_Name_value",data2,
									 "listSpecificCompany_Phone_value",data3,"listSpecificCompany_Email_value",data4,methodName+" verifyContents test case "+testCaseNumber);
	driver.findElement(By.xpath(prop.getProperty("listCompany_companyMgtAppLink"))).click(); //go back to homepage	
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
