<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Facilites list List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
     <div class="mainbar"><div class="submb"></div></div>
              
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility ID</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Facility type</th> 
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Deposit</th> 
			</tr>

 		<c:forEach items="${FACILITIES}" var="item">
			<tr class="myTableRow">	
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.idfacility}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.facility_name}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.facility_type}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.deposit}" /></td>
            <td> <a href="/company_management/FacilityController?action=listSpecificFacility&id=${item.idfacility}">View</a></td>
			</tr>
		</c:forEach>
 </table>
<div style="width:650px; ">
<br></br>
<a href="<%=request.getContextPath()%>/manager.jsp" style="float:left">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log Out</a>
</div>
</body>
</html>