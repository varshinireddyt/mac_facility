package company_management.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import company_management.model.User;
import company_management.util.SQLConnection;

public class UserDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	//list All User by role by manager
	public static ArrayList<User> ReturnMatchingUserList(String queryString){
		ArrayList<User> userListInDB= new ArrayList<User>();
		Statement stmt = null;   
		Connection conn = SQLConnection.getDBConnection(); 
		try {
			stmt = conn.createStatement();
	//		String searchUser = "SELECT * from USERS WHERE ROLE = 'User' ORDER BY LastName";
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				User user=new User();
/*				String UserId= userList.getString("UserId");
				String UserName= userList.getString("UserName");
				String Password= userList.getString("Password");
				String FirstName= userList.getString("FirstName");
				String LastName= userList.getString("LastName");
				String Email= userList.getString("Email");
				String Role= userList.getString("Role");*/
				user.setUserId(userList.getString("userId"));
				user.setUserName(userList.getString("userName"));
				user.setPassword(userList.getString("password"));
				user.setFirstName(userList.getString("firstName"));
				user.setLastName(userList.getString("lastName"));
				user.setEmail(userList.getString("email"));
				user.setRole(userList.getString("role"));
				user.setRevoke_status(userList.getString("revoke_status"));
				userListInDB.add(user);
			}
		}catch (SQLException e) {}
		return userListInDB;		
	}
	private static void StoreListinDB (User user,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertUser = queryString + " VALUES ('"  
					+ user.getUserName()  + "','"
					+ user.getPassword() + "','"		
					+ user.getFirstName() + "','"
					+ user.getLastName() + "','"
					+ user.getEmail() + "','"
					+ user.getRole() + "')";
			stmt.executeUpdate(insertUser);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	public static void updateMyProfile(User user) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String updateUser = "UPDATE USERS SET "
							+ "UserName='" + user.getUserName() + "', "
							+ "Password='" + user.getPassword() + "', "
							+ "FirstName='" + user.getFirstName() + "', "
							+ "LastName='" + user.getLastName() + "', "
							+ "Email='" + user.getEmail() + "' "
							+ "WHERE UserId='" + user.getUserId() + "'";
			stmt.executeUpdate(updateUser);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	public static void insertUser(User user) {  
		StoreListinDB(user,"INSERT INTO USERS (UserName,Password, FirstName, LastName, Email, Role) ");
	} 
	//Search specific User by User id
		public static ArrayList<User> searchUser(String userId)  {  
				return ReturnMatchingUserList(" SELECT * from USERS WHERE userId = '"+userId+"' ORDER BY lastName");
		}
	//User list
	public static ArrayList<User> managerUserList(){
		return ReturnMatchingUserList("SELECT * from USERS WHERE ROLE = 'user' ORDER BY lastName, firstName");
	}
	public static ArrayList<User> adminUserList(){
		return ReturnMatchingUserList("SELECT * from USERS ORDER BY lastName, firstName");
	}
	//Search User First Name
	public static ArrayList<User> managerSearchUserfirst(String firstName){
		return ReturnMatchingUserList("SELECT * from USERS where ROLE = 'user' and firstName LIKE '%"+firstName+"' ORDER BY lastName, firstName");
	}
	public static ArrayList<User> adminSearchUserfirst(String firstName){
		return ReturnMatchingUserList("SELECT * from USERS where firstName LIKE '%"+firstName+"' ORDER BY lastName, firstName");
	}
	//Search User Last Name
	public static ArrayList<User> managerSearchUserlast(String lastName){
		return ReturnMatchingUserList("SELECT * from USERS where ROLE = 'user' and lastName LIKE '%"+lastName+"' ORDER BY lastName, firstName");
	}
	public static ArrayList<User> adminSearchUserlast(String lastName){
		return ReturnMatchingUserList("SELECT * from USERS where lastName LIKE '%"+lastName+"' ORDER BY lastName, firstName");
	}
	//Search User User id
	public static ArrayList<User> managerSearchUserId(String userId){
		return ReturnMatchingUserList("SELECT * from USERS where ROLE = 'user' and userId='"+userId+"' ORDER BY lastName"); 
	}
	public static ArrayList<User> adminSearchUserId(String userId){
		return ReturnMatchingUserList("SELECT * from USERS where userId='"+userId+"' ORDER BY lastName"); 
	}
	public static ArrayList<User> modifyUser(String Id,String role){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String queryString="UPDATE USERS SET role='"+role+"'WHERE userId='"+Id+"'";		
		conn = SQLConnection.getDBConnection();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();					 
		} catch (SQLException e) {}
		return ReturnMatchingUserList("SELECT * from USERS where userId='"+Id+"' ORDER BY lastName");
		
	}
	//RevokeFacility
	public static ArrayList<User> revokeUser(String Id,String Revoke){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String queryString="UPDATE USERS SET revoke_status='"+Revoke+"'WHERE userId='"+Id+"'";		
		conn = SQLConnection.getDBConnection();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();					 
		} catch (SQLException e) {}
		return ReturnMatchingUserList("SELECT * from USERS where userId='"+Id+"' ORDER BY lastName");
		
	}

}
