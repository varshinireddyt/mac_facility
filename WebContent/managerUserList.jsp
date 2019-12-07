<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
  <h2>User List:</h2>
     <div class="mainbar"><div class="submb"></div></div>      
<form action="/company_management/UserController?action=managerUserList" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Last Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">First Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">User name</th> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left">UserID</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Email</th> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Role</th> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left"></th> 				
     		</tr>

      		<c:forEach items="${USERS}" var="item">
			<tr class="myTableRow">
            <td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.lastName}" /></td>
            <td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.firstName}" /></td>
    		<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.userName}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.userId}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.email}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.role}" /></td>
			<td> <a href="/company_management/UserController?action=listSpecificUser&id=${item.userId}">View</a></td>
            </tr>
		</c:forEach>

 </table>
<div style="width:650px; ">
<br></br>
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log out</a>
</div>

 </form>
</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>