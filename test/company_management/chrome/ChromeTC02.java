package company_management.chrome;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.MethodSorters;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import company_management.CMFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // the test methods in this test file can run in any order but I prefer a fixed order
public class ChromeTC02 extends CMFunctions {
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
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();	  
	    prop.load(new FileInputStream("./Configuration/CM_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
  }


  @Test
  @FileParameters("test/TC02_test_cases.csv")
  public void TC02a(int testCaseNumber, String companyID, String companyName, String companyPhone, String companyEmail, String errorMessage, String companyIDErrorMessage, 
		  						String companyNameErrorMessage, String companyPhoneErrorMessage, String companyEmailErrorMessage) throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
    driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.insertCompany); //select Insert Company from homepage
    insertCompany_function(driver, companyID, companyName, companyPhone, companyEmail,methodName+" insertCompanyFunction test case "+testCaseNumber);
    // verify error messages
    verifyInsertCompanyErrorMessages(driver, errorMessage,companyIDErrorMessage,companyNameErrorMessage,companyPhoneErrorMessage,companyEmailErrorMessage,
    		methodName+" verifyInsertCompanyErrorMessages test case "+testCaseNumber);
  }

  @Test
//  @FileParameters("test/TC02_test_cases.csv")
  public void TC02b() throws Exception {
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
    MainApp_function(driver,FunctionEnum.insertCompany); //select Insert Company from homepage
	// Generate random companyName, CompanyID, Company Email, Employee ID, and Badge numbers to avoid violating PKs
    // input valid information in Company form
  	String companyNameRand = "Selenium"+generateSixdigRandString(),idcompanyRand=generateSixdigRandString(),phoneRand=generateTendigRandString();
    String emailRand=generateSixdigRandString()+"@email.com",idemployeeRand=generateSixdigRandString(),name="Jack",surname="BeNimble";
    String badgeRand=generateFivedigRandString();
    insertCompany_function(driver,idcompanyRand,companyNameRand,phoneRand,emailRand,methodName+" insertCompany test case 1");
    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_errMsgError"))).getAttribute("value").equals(""));
    // insert valid data in employee form
	insertEmployee_function(driver,idemployeeRand,name,surname,badgeRand,"done",methodName+" insertEmployeeFunction test case 1");
    MainApp_function(driver,FunctionEnum.searchCompanies); // select SearchCompany from homepage
    searchCompany_function(driver,"",idcompanyRand,methodName+" searchCompany_function test case 1"); //search on Company ID previously entered
    // Verify header for Company Search table results
    verifyHeaders(driver,"searchCompanyResults_companyIDTitle","Company ID","searchCompanyResults_phoneTitle","Phone","searchCompanyResults_companyNameTitle","Company Name","searchCompanyResults_emailTitle","Email","","",methodName+" verifyHeaders test case 1");
    // Verify contents for Company Search table results
    verifyContents(driver,"searchCompanyResults_companyIDValue",idcompanyRand,"searchCompanyResults_phoneValue",phoneRand,"searchCompanyResults_companyNameValue",companyNameRand,"searchCompanyResults_emailValue",emailRand,methodName+" verifyContents test case 1");
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
