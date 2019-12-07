package company_management.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import company_management.bean.LoginBean;
import company_management.util.SQLConnection;
 
public class LoginDao {
	static SQLConnection DBMgr = SQLConnection.getInstance();
 
public void authenticateUser(LoginBean loginBean)
{
	 String userName = loginBean.getUserName();
	 String password = loginBean.getPassword();
	 
	 Statement statement = null;
	 ResultSet resultSet = null;
	 
	 String userNameDB = "";
	 String passwordDB = "";
	 
	 try
	 {
		 Connection conn = SQLConnection.getDBConnection();  
		 statement = conn.createStatement();
		 resultSet = statement.executeQuery("select userId, username,password,role from users");
	 
		 while(resultSet.next())
		 {
			 userNameDB = resultSet.getString("username");
			 passwordDB = resultSet.getString("password");
			 
			 if(userName.equals(userNameDB) && password.equals(passwordDB)){
				 loginBean.setRole(resultSet.getString("role"));
				 loginBean.setUserId(resultSet.getString("userId"));
			 }
		 }
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	}
}
