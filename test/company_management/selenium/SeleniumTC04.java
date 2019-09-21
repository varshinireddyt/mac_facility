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
public class SeleniumTC04 extends CMFunctions {
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
@FileParameters("test/TC04a_test_cases.csv")
public void TC04a(int testCaseNumber, String companyName, String companyID, String errorMessage, String companyNameErrorMessage, 
		  						String companyIDErrorMessage) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.searchCompanies); //select Search Companies from homepage
    searchCompany_function(driver,companyName,companyID,methodName+" searchCompanyFunction test case "+testCaseNumber);
    verifySearchCompanyErrorMessages(driver,errorMessage, companyNameErrorMessage,companyIDErrorMessage,methodName+" verifySearchCompanyErrorMessages test case "+testCaseNumber);
 }

@Test
@FileParameters("test/TC04b_test_cases.csv")
public void TC04b(int testCaseNumber, String companyName, String companyID, String resultsCompanyName, String resultsCompanyID, 
								String resultsPhone, String resultsEmail) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.searchCompanies); //select Search Companies from homepage
    searchCompany_function(driver,companyName,companyID,methodName+" searchCompanyFunction test case "+testCaseNumber);
	verifyHeaders(driver,"searchCompanyResults_companyNameTitle","Company Name","searchCompanyResults_companyIDTitle","Company ID",
			"searchCompanyResults_phoneTitle","Phone","searchCompanyResults_emailTitle","Email","","",methodName+" verifyHeaders test case "+testCaseNumber);
		// Verify contents for Employee Search table results
	verifyContents(driver,"searchCompanyResults_companyNameValue",resultsCompanyName,"searchCompanyResults_companyIDValue",resultsCompanyID,
			"searchCompanyResults_phoneValue", resultsPhone,"searchCompanyResults_emailValue",resultsEmail,methodName+" verifyContents test case "+testCaseNumber);
	driver.findElement(By.xpath(prop.getProperty("searchCompanyResults_companyMgtAppLink"))).click(); //return to homepage	    
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
