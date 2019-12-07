<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Companies List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
  </div>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
<tr>
	<td>
	<form action="/company_management/FacilityController?action=managerSearchFacility" method="post">
	<table border="1" class="myTable"> 
	<tr>
	<tr>
  	<td> Facility Type: </td>
 	<td> <input name="facilityType" value="<c:out value='${facility.facility_type}'/>" type="text" maxlength="45">  </td>
  	</tr>
    <tr>
    <td> Facility Name: </td>
    <td> <input name="facilityName" value="<c:out value='${facility.facility_name}'/>" type="text" maxlength="45"> </td>
    </tr>
</table>
  <input type="submit" value="Search">
<div style="width:700px; ">
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log out</a>
</div>
	</form>      
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