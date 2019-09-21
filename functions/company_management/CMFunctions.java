package company_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import company_management.SnapshotFunction;
import static org.junit.Assert.assertTrue;
import java.util.Properties;
public class CMFunctions {

		  private SnapshotFunction snap = new SnapshotFunction();
		  public WebDriver driver;
		  public Properties prop;
		  public enum FunctionEnum {listCompanies, insertCompany, searchCompanies, searchEmployee}

		  public void MainApp_function (WebDriver driver, FunctionEnum function) {
			  switch (function) {
			  	case listCompanies:
				    driver.findElement(By.xpath(prop.getProperty("MainApp_ListCompanyLink"))).click(); //select List Company from homepage
				    break;
			  	case insertCompany:
			  	    driver.findElement(By.xpath(prop.getProperty("MainApp_InsertCompanyLink"))).click(); //select Insert Company from homepage
			  	    break;
			  	case searchCompanies:
			  	    driver.findElement(By.xpath(prop.getProperty("MainApp_SearchCompaniesLink"))).click(); //select Insert Company from homepage
			  	    break;
			  	case searchEmployee:
			  	    driver.findElement(By.xpath(prop.getProperty("MainApp_SearchEmployeeLink"))).click(); //select Insert Company from homepage
			  }
		  }
		  
		  public void insertCompany_function (WebDriver driver, String companyID, String companyName, String companyPhone, String companyEmail, String snapShotName) {
			    driver.findElement(By.xpath(prop.getProperty("insertCompany_companyIDValue"))).sendKeys(companyID);
			    driver.findElement(By.xpath(prop.getProperty("insertCompany_companyNameValue"))).sendKeys(companyName);
			    driver.findElement(By.xpath(prop.getProperty("insertCompany_phoneValue"))).sendKeys(companyPhone);
			    driver.findElement(By.xpath(prop.getProperty("insertCompany_emailValue"))).sendKeys(companyEmail);
			    snap.takeScreenshot(driver, snapShotName);
			    driver.findElement(By.xpath(prop.getProperty("insertCompany_insertCompanyButton"))).click(); //click on insertCompany button
		  }
		  
		  public void verifyInsertCompanyErrorMessages (WebDriver driver, String errorMessage, String companyIDErrorMessage, 
				  										String companyNameErrorMessage, String companyPhoneErrorMessage, String companyEmailErrorMessage, String snapShotName) {
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_errMsgError"))).getAttribute("value").equals(errorMessage));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_companyIDError"))).getAttribute("value").equals(companyIDErrorMessage));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_companyNameError"))).getAttribute("value").equals(companyNameErrorMessage));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_phoneError"))).getAttribute("value").equals(companyPhoneErrorMessage));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("insertCompany_emailError"))).getAttribute("value").equals(companyEmailErrorMessage));
			    snap.takeScreenshot(driver,snapShotName);
		  }
		  
		  public void verifyHeaders(WebDriver driver, String header1OnPage, String expectedHeader1Name,String header2OnPage, String expectedHeader2Name,
				  									String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
				  									String header5OnPage, String expectedHeader5Name, String snapShotName) {
			    assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
			    if (!header5OnPage.equals(""))
			    	assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
			    snap.takeScreenshot(driver,snapShotName);
		  }
		  
		  public void verifyContents(WebDriver driver, String col1ValueOnPage, String expectedcol1Value,String col2ValueOnPage, String expectedcol2Value,
												String col3ValueOnPage, String expectedcol3Value,String col4ValueOnPage, String expectedcol4Value, String snapShotName) {
			  	assertTrue(driver.findElement(By.xpath(prop.getProperty(col1ValueOnPage))).getText().equals(expectedcol1Value));
				assertTrue(driver.findElement(By.xpath(prop.getProperty(col2ValueOnPage))).getText().equals(expectedcol2Value));
				assertTrue(driver.findElement(By.xpath(prop.getProperty(col3ValueOnPage))).getText().equals(expectedcol3Value));
				assertTrue(driver.findElement(By.xpath(prop.getProperty(col4ValueOnPage))).getText().equals(expectedcol4Value));
			    snap.takeScreenshot(driver,snapShotName);
		  }
		  
		  public void insertEmployee_function (WebDriver driver, String employeeID, String firstName, String lastName, String badgeNo, String doneButton, String snapShotName) {
			    driver.findElement(By.xpath(prop.getProperty("insertEmployee_employeeIDValue"))).sendKeys(employeeID);
			    driver.findElement(By.xpath(prop.getProperty("insertEmployee_firstNameValue"))).sendKeys(firstName);
			    driver.findElement(By.xpath(prop.getProperty("insertEmployee_lastNameValue"))).sendKeys(lastName);
			    driver.findElement(By.xpath(prop.getProperty("insertEmployee_companyBadgeValue"))).sendKeys(badgeNo);
			    snap.takeScreenshot(driver,snapShotName);
			    driver.findElement(By.xpath(prop.getProperty("insertEmployee_insertEmployeeButton"))).click(); //click insertEmployee button
			    if (!doneButton.equals(""))
			    	driver.findElement(By.xpath(prop.getProperty("insertEmployee_doneButton"))).click(); // click Done button
		  }

		  public void verifyInsertEmployeeErrorMessages (WebDriver driver, String errorMessage, String employeeIDErrorMessage, 
					String employeeFirstNameErrorMessage, String employeeLastNameErrorMessage, String employeeBadgeNumberErrorMessage, String snapShotName) {
				assertTrue(driver.findElement(By.xpath(prop.getProperty("insertEmployee_errMsgError"))).getAttribute("value").equals(errorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("insertEmployee_employeeIDError"))).getAttribute("value").equals(employeeIDErrorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("insertEmployee_firstNameError"))).getAttribute("value").equals(employeeFirstNameErrorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("insertEmployee_lastNameError"))).getAttribute("value").equals(employeeLastNameErrorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("insertEmployee_companyBadgeError"))).getAttribute("value").equals(employeeBadgeNumberErrorMessage));
			    snap.takeScreenshot(driver,snapShotName);
		  }

		  public void searchCompany_function (WebDriver driver, String companyName, String companyID, String snapShotName) {
			    driver.findElement(By.xpath(prop.getProperty("searchCompany_companyNameValue"))).sendKeys(companyName);
			    driver.findElement(By.xpath(prop.getProperty("searchCompany_companyIDValue"))).sendKeys(companyID);
			    snap.takeScreenshot(driver,snapShotName);
			    driver.findElement(By.xpath(prop.getProperty("searchCompany_submitButton"))).click();			    
		  }
		  
		  public void verifySearchCompanyErrorMessages (WebDriver driver, String errorMessage, String companyNameErrorMessage, String CompanyIDErrorMessage, String snapShotName) {
			  	assertTrue(driver.findElement(By.xpath(prop.getProperty("searchCompany_errMsgError"))).getAttribute("value").equals(errorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("searchCompany_companyNameError"))).getAttribute("value").equals(companyNameErrorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("searchCompany_companyIDError"))).getAttribute("value").equals(CompanyIDErrorMessage));
			    snap.takeScreenshot(driver,snapShotName);
		  }

		  public void searchEmployee_function (WebDriver driver, String companyID, String snapShotName) {
			    driver.findElement(By.xpath(prop.getProperty("searchEmployee_companyIDValue"))).sendKeys(companyID);
			    snap.takeScreenshot(driver,snapShotName);
			    driver.findElement(By.xpath(prop.getProperty("searchEmployee_submitButton"))).click();			    
		  }
		  
		  public void verifySearchEmployeeErrorMessages (WebDriver driver, String errorMessage, String CompanyIDErrorMessage, String snapShotName) {
			  	assertTrue(driver.findElement(By.xpath(prop.getProperty("searchEmployee_errMsgError"))).getAttribute("value").equals(errorMessage));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("searchEmployee_companyIDError"))).getAttribute("value").equals(CompanyIDErrorMessage));
			    snap.takeScreenshot(driver,snapShotName);
		  }

}
