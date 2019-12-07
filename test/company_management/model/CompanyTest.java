package company_management.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CompanyTest {
	
	Company cmp;
	CompanyErrorMsgs CerrMsgs;
	
	@Before
	public void setUp() throws Exception {
		cmp = new Company();
		CerrMsgs = new CompanyErrorMsgs();
	}	

	@Test
	@FileParameters("test/Company_test_cases.csv")
	public void test(	int testcaseNo, String action, String idcompany, String company_name,String phone, String email, String errorMsg,    
						String companyIDerror,    String companyNameError,    String phoneError,    String emailError) {
		
		cmp.setCompany(idcompany, company_name, phone, email);
		cmp.validateCompany(action, cmp, CerrMsgs);
		assertTrue(errorMsg.equals(CerrMsgs.getErrorMsg()));
		assertTrue(companyIDerror.equals(CerrMsgs.getCompanyIDerror()));
		assertTrue(companyNameError.equals(CerrMsgs.getCompanyNameError()));
		assertTrue(phoneError.equals(CerrMsgs.getPhoneError()));
		assertTrue(emailError.equals(CerrMsgs.getEmailError()));
	}
}