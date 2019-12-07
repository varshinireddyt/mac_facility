package company_management.model;

import java.io.Serializable;

public class User implements  Serializable {
	private static final long serialVersionUID = 3L;
	private String userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String revoke_status;
	
	public void setUser (String userId, String userName, String password, String firstName, String lastName, String email, String role, String revoke_status) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setRole(role);
		this.setRevoke_status(revoke_status);
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId=userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public String getRevoke_status() {
		return revoke_status;
	}
	public void setRevoke_status(String revoke_status) {
		this.revoke_status=revoke_status;
	}
}
