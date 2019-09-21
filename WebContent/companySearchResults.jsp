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
      
  </div>


     
      
 <form action="/company_management/CompanyController?action=listSpecificCompany" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Company ID</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Phone</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Company Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Email</th> 
			</tr>

 		<c:forEach items="${COMPANIES}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.idcompany}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.phone}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.company_name}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.email}" /></td>
			</tr>
		</c:forEach>
 </table>
 </form>
</body>
</html>