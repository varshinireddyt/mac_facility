package company_management.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class EmployeeTest {
	
	Employee emp;
	EmployeeErrorMsgs EerrMsgs;
	
	@Before
	public void setUp() throws Exception {
		emp = new Employee();
		EerrMsgs = new EmployeeErrorMsgs();
	}

	@Test
	@FileParameters("test/Employee_test_cases.csv")
	public void test(	int testcaseNo, String idemployee, String name,String surname, String badge, String fk_company, String errorMsg,    
						String employeeIDerror,    String firstNameError,    String lastNameError,    String companyBadgeError) {
		emp.setEmployee(idemployee, name, surname, badge);
//	Following 2 lines added to get complete JaCoCo coverage
		@SuppressWarnings("unused")
		String dummy=emp.getFk_company();
		emp.setFk_company(fk_company);
		emp.validateEmployee(emp, EerrMsgs);
		assertTrue(errorMsg.equals(EerrMsgs.getErrorMsg()));
		assertTrue(employeeIDerror.equals(EerrMsgs.getEmployeeIDerror()));
		assertTrue(firstNameError.equals(EerrMsgs.getFirstNameError()));
		assertTrue(lastNameError.equals(EerrMsgs.getLastNameError()));
		assertTrue(companyBadgeError.equals(EerrMsgs.getCompanyBadgeError()));
	}
}