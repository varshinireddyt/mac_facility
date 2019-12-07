<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 5px;
  text-align: left;    
}
</style>

<title>Search Facility</title>
</head>

<body>
<center><h2>Search Facility</h2></center><br><br>
<div style="text-align:center">

<table align="center">
<tr>
	<td>
	<form action="/company_management/UserFacilityController?action=searchFacility" method="post">
	<input name="iduser" value="<c:out value='${USER.userId}'/>" type="hidden"> 
	<table style="width:75%">
	<tr>
	<tr>
  	<td> Facility Type: </td>
 		<td> <input name="facility_type" value="<c:out value='${facility.facility_type}'/>" type="text" maxlength="45">  </td>
  	</tr>
    <tr>
    <td> Start Date: </td>
    <td> <input type="text" name="start_date" required> </td>
  	</tr>
    <tr>
    <td> Start Time: </td>
    <td> <input type="text" name="start_time" required> </td>
  	</tr>
	</table>
  		<input type="submit" value="Search">
	</form>      
	</td>
</tr>
</table>

</div><br>

<br><br>

<div style="padding-right:10px; padding-left:10px">
<!-- <button style="float:right" type="button">Back</button> -->
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float: right">Log out</a>
</div>
<br>

</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>