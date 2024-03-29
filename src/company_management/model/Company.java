package company_management.model;

import java.io.Serializable;
import company_management.data.CompanyDAO;

public class Company implements Serializable{

	private static final long serialVersionUID = 3L;
	private String idcompany;
	private String company_name;
	private String phone;
	private String email;
	
	public void setCompany (String idcompany, String company_name,String phone, String email) {
		setIdcompany(idcompany);
		setCompany_name(company_name);
		setPhone(phone);
		setEmail(email);
	}
	
	public String getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(String idcompany) {
		this.idcompany = idcompany;
	}
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
        this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void validateCompany (String action, Company company, CompanyErrorMsgs errorMsgs) {
		if (action.equals("saveCompany")) {
			errorMsgs.setCompanyIDerror(validateIdcompany(action,company.getIdcompany()));
			errorMsgs.setCompanyNameError(validateCompany_name(company.getCompany_name()));
			errorMsgs.setPhoneError(validatePhone(company.getPhone()));
			errorMsgs.setEmailError(validateEmail(company.getEmail()));
			errorMsgs.setErrorMsg();
		}
		else
			if (action.equals("searchCompany")) {
				if (company_name.equals("") && idcompany.equals("")) 
					errorMsgs.setCompanyNameError("Both Company Name and Company ID cannot be blank");
				else
					if (!idcompany.equals(""))
						errorMsgs.setCompanyIDerror(validateIdcompany(action, idcompany));
				errorMsgs.setErrorMsg();				
			}
			else { //action=searchEmployee
				if (idcompany.equals("")) 
					errorMsgs.setCompanyIDerror("Company ID cannot be blank");
				else
					errorMsgs.setCompanyIDerror(validateIdcompany(action,idcompany));
				errorMsgs.setErrorMsg();
			}
	}

	private String validateIdcompany(String action, String idcompany) {
		String result="";
		if (!isTextAnInteger(idcompany))
			result="Your company ID must be a number";
		else
			if (action.equals("saveCompany")) {
				if (!stringSize(idcompany,3,16))
					result= "Your Company Id must between 3 and 16 digits";
				else
					if (!CompanyDAO.CompanyIDunique(idcompany))
						result="Company ID already in database";
			}
		return result;
	}
	
	private String validateCompany_name(String company_name) {
		String result="";
		if (!stringSize(company_name,3,45))
			result= "Your Company Name must between 3 and 45 digits";
		else
			if (Character.isLowerCase(company_name.charAt(0)))
				result="Your company name must start with a capital letter";
		return result;		
	}
	
	private String validatePhone(String phone) {
		String result="";
		if (phone.length()!=10)
			result="Phone number must be 10 digits in length";
		else
			if (!isTextAnInteger(phone))
				result="Phone number must be a number";
		return result;		
	}
	
	private String validateEmail(String email) {
		String result="",extension="";
		if (!email.contains("@"))
			result = "Email address needs to contain @";
		else
			if (!stringSize(email,7,45))
				result="Email address must be between 7 and 45 characters long";
			else {
				extension = email.substring(email.length()-4, email.length());
				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
					result = "Invalid domain name";				
			}
		return result;		
	}

//	This section is for general purpose methods used internally in this class
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
}