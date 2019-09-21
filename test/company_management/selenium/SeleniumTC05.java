package company_management.selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import company_management.CMFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // the test methods in this test file can run in any order but I prefer a fixed order
public class SeleniumTC05 extends CMFunctions {
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

    
@Test
@FileParameters("test/TC05a_test_cases.csv")
public void TC05a(int testCaseNumber, String companyID, String errorMessage, String companyIDErrorMessage) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.searchEmployee); //select Search Employee from homepage
    searchEmployee_function(driver,companyID,methodName+" searchEmployeeFunction test case "+testCaseNumber);
    verifySearchEmployeeErrorMessages(driver,errorMessage, companyIDErrorMessage,methodName+" verifySearchEmployeeErrorMessages test case "+testCaseNumber);
 }

@Test
@FileParameters("test/TC05b_test_cases.csv")
public void TC05b(int testCaseNumber, String companyID, String resultsLastName, String resultsFirstName, 
								String resultsEmployeeID, String resultsBadge) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.searchEmployee); //select Search Employee from homepage
    searchEmployee_function(driver,companyID,methodName+" searchEmployeeFunction test case "+testCaseNumber);
	verifyHeaders(driver,"searchEmployeeResults_lastNameTitle","Last Name","searchEmployeeResults_FirstNameTitle","First Name",
			"searchEmployeeResults_EmployeeIDTitle","Employee ID","searchEmployeeResults_BadgeTitle","Badge","","",methodName+" verifyHeaders test case "+testCaseNumber);
		// Verify contents for Employee Search table results
	verifyContents(driver,"searchEmployeeResults_lastNameValue",resultsLastName,"searchEmployeeResults_FirstNameValue",resultsFirstName,
			"searchEmployeeResults_EmployeeIDValue", resultsEmployeeID,"searchEmployeeResults_BadgeValue",resultsBadge,methodName+" verifyContents test case "+testCaseNumber);
	driver.findElement(By.xpath(prop.getProperty("searchEmployeeResults_companyMgtAppLink"))).click(); //return to homepage	    
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
