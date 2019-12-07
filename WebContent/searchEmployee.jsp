<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search Employee</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
      <div class="logo"><h1><a href="/company_management">Company Management Application</a></h1></div>
      <div class="menu_nav">
  </div>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
<tr>
<td>
<form action="/company_management/EmployeeController?action=searchEmployee" method="post">
	<table style="width: 1200px; ">
<tr>
<tr> 
<td> Company ID: </td>
    <td> <input name="idcompany" value="<c:out value='${company.idcompany}'/>" type="text" maxlength="16"> </td>
   	<td> <input name="companyIDerror"  value="<c:out value='${errorMsgs.companyIDerror}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
</tr>
</table>
  <input type="submit" value="Submit">
	</form>
	</td>
	</tr>      
</table>
</body>
</html>