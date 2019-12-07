<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration: </title>
</head>
<body>
<h1><a href="http://localhost:8080/company_management/">Facility Reservation System</a></h1>
<h2>Registration: </h2>
<table>
  <tr>
   <td>
    <form name="companyForm" action="/company_management/UserController?registration" method="post">
    <table style="width: 500px; ">
    <tr>
    <td> User name: </td>
    <td> <input name="username" value="<c:out value='${user.userName}'/>" type="text" maxlength="16"> </td>
    </tr>
    
    <tr>
    <td> Password: </td>
    <td> <input name="password" value="<c:out value='${user.password}'/>" type="password" maxlength="45">  </td>
    </tr>  

    <tr>
    <td> First Name: </td>
    <td> <input name="firstname" value="<c:out value='${user.firstName}'/>" type="text" maxlength="45">  </td>
    </tr>
    
   
    <tr>
    <td> Last Name: </td>
    <td> <input name="lastname" value="<c:out value='${user.lastName}'/>" type="text" maxlength="45">  </td>
    </tr>

    <tr>
    <td> Email: </td>
    <td> <input name="email" value="<c:out value='${user.email}'/>" type="email" maxlength="45">  </td>
    </tr>
    
    <tr><td><p>Please select your role:</p></td></tr>
    <tr style="width: 500px;">
    <td><input type="radio" name="role" value="Admin"> Admin<br></td>
	<td><input type="radio" name="role" value="Manager"> Facility Manager<br></td>
	<td><input type="radio" name="role" value="User"> User<br></td>
	</tr>

    </table>
    <br>
    <input name="action" value="registration" type="hidden">
    <input name="registrationButton" type="submit" value="Register new user">
    </form>
</td>
</tr>
</table>
<div style="width: 500px">
<a href="<%=request.getContextPath()%>" style="float:left">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log Out</a>
</div>
</body>
</html>