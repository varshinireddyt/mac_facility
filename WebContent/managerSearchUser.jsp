<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search User</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
  </div>
<table>
<tr>
	<td>
	<form action="/company_management/UserController?action=managerSearchUser" method="post">
	<table style="width: 1200px; ">
	<tr>
	<tr>
  	<td> First Name: </td>
 	<td> <input name="firstName" value="<c:out value='${users.firstName}'/>" type="text" maxlength="45">  </td>
  	</tr>
    <tr>
    <td> Last Name: </td>
    <td> <input name="lastName" value="<c:out value='${users.lastName}'/>" type="text" maxlength="45"> </td>
  	</tr>
</table>

<input type="submit" value="Search">
</form>  
<div style="width:600px; ">
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log out</a>
</div>
    
</td>
</tr>
</table>

</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>