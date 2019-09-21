package company_management.selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
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
public class SeleniumTC03 extends CMFunctions {
  private StringBuffer verificationErrors = new StringBuffer();
  public String sAppURL, sSharedUIMapPath, testDelay;

private String generateSixdigRandString() {
    Random rand = new Random();
    return Integer.toString(rand.nextInt(900)+100)+Integer.toString(rand.nextInt(900)+100);
}
  
private String generateFivedigRandString() {
    Random rand = new Random();
    return Integer.toString(rand.nextInt(900)+100)+Integer.toString(rand.nextInt(90)+10);
}

private String generateTendigRandString() {
    Random rand = new Random();
    return Integer.toString(rand.nextInt(900)+100)+Integer.toString(rand.nextInt(900)+100)+Integer.toString(rand.nextInt(900)+100)+Integer.toString(rand.nextInt(9)+1);
}

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
@FileParameters("test/TC03_test_cases.csv")
public void TC03a(int testCaseNumber, String employeeID, String firstName, String lastName, String badgeNumber, String errorMessage, String employeeIDErrorMessage, 
		  						String employeeFirstNameErrorMessage, String employeeLastNameErrorMessage, String badgeNumberErrorMessage) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.insertCompany); //select Insert Company from homepage
  	String companyNameRand = "Selenium"+generateSixdigRandString(),idcompanyRand=generateSixdigRandString(),phoneRand=generateTendigRandString(),
  			emailRand=generateSixdigRandString()+"@email.com";
	insertCompany_function(driver,idcompanyRand,companyNameRand,phoneRand,emailRand,methodName+" insertCompanyFunction test case "+testCaseNumber);
    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_errMsgError"))).getAttribute("value").equals(""));
	insertEmployee_function(driver,employeeID,firstName,lastName,badgeNumber,"",methodName+" insertEmployeeFunction test case "+testCaseNumber);
	verifyInsertEmployeeErrorMessages(driver,errorMessage, employeeIDErrorMessage,employeeFirstNameErrorMessage,employeeLastNameErrorMessage,badgeNumberErrorMessage
			,methodName+" insertEmployeeErrorMessages test case "+testCaseNumber);
 }

@Test
//@FileParameters("test/TC02_test_cases.csv")
public void TC03b() throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	MainApp_function(driver,FunctionEnum.insertCompany); //select Insert Company from homepage
	// Generate random companyName, CompanyID, Company Email, Employee ID, and Badge numbers to avoid violating PKs
	// input valid information in Company form
	String companyNameRand = "Selenium"+generateSixdigRandString(),idcompanyRand=generateSixdigRandString(),phoneRand=generateTendigRandString();
	String emailRand=generateSixdigRandString()+"@email.com",idemployeeRand=generateSixdigRandString(),name="Jack",surname="BeNimble";
	String badgeRand=generateFivedigRandString();
	insertCompany_function(driver,idcompanyRand,companyNameRand,phoneRand,emailRand,methodName+" insertCompanyFunction test case 1");
	assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_errMsgError"))).getAttribute("value").equals(""));
	// insert valid data in employee form
	insertEmployee_function(driver,idemployeeRand,name,surname,badgeRand,"done",methodName+" insertEmployeeFunction test case 1");
	MainApp_function(driver,FunctionEnum.searchEmployee);  //select Search Employee
	searchEmployee_function(driver,idcompanyRand,methodName+" searchEmployeeFunction test case 1"); //search Employee by Company ID previously entered
	// Verify header for Employee Search table results
	verifyHeaders(driver,"searchEmployeeResults_lastNameTitle","Last Name","searchEmployeeResults_FirstNameTitle","First Name",
			"searchEmployeeResults_EmployeeIDTitle","Employee ID","searchEmployeeResults_BadgeTitle","Badge","","",methodName+" verifyHeaders test case 1");
	// Verify contents for Employee Search table results
	verifyContents(driver,"searchEmployeeResults_lastNameValue",surname,"searchEmployeeResults_FirstNameValue",name,"searchEmployeeResults_EmployeeIDValue",
			idemployeeRand,"searchEmployeeResults_BadgeValue",badgeRand,methodName+" verifyContents test case 1");
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
