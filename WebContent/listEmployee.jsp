<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Companies List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Company Management Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>

     <div class="mainbar"><div class="submb"></div></div>
     
      
      <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 130px; ">Last Name</th>
				<th class="myTableHead" style="width: 130px; ">First Name</th>
				<th class="myTableHead" style="width: 105px; ">Employee ID</th>
				<th class="myTableHead" style="width: 74px;  ">Badge</th> 
			</tr>
			<c:forEach items="${employees}" var="item">
			<tr class="myTableRow"> 
				<td class="myTableCell" style="width: 130px; "><c:out value="${item.surname}" /></td> 
				<td class="myTableCell" style="width: 130px; "><c:out value="${item.name}" /></td>
				<td class="myTableCell" style="width: 105px; "><c:out value="${item.idemployee}" /></td>
				<td class="myTableCell" style="width: 74px;  "><c:out value="${item.badge}" /></td>
		   </tr>
		   </c:forEach>
</table>
</body>
</html>