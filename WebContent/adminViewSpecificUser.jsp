<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>User Information</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<h2>User Information: </h2>
<table>
  <tr>
   <td>
         <table border="1" class="myTable">
    <tr>
    <td> User ID: </td>
    <td> <c:out value="${USERS.userId}" /> </td>
    </tr>
     
    <tr>
    <td> User Name: </td>
    <td>  <c:out value="${USERS.userName}" /> </td>
    </tr>

    <tr>
    <td> First Name: </td>
    <td> <c:out value="${USERS.firstName}" /></td>
    </tr>

    <tr>
    <td> Last Name: </td>
    <td> <c:out value="${USERS.lastName}" /></td>
    </tr>
    
    <tr>
    <td> Email: </td>
    <td> <c:out value="${USERS.email}" /></td>
    </tr>
    
    <tr>
    <td> Role: </td>
    <td> <c:out value="${USERS.role}" /></td>
    </tr>
    
    <tr>
    <td> Revoke Status </td>
    <td>  <c:out value="${USERS.revoke_status}" /> </td>
    </tr>


    </table>
<tr>
<td><span style="color:red"><%=(request.getAttribute("erMessage") == null) ? "" : request.getAttribute("erMessage")%></span></td>
<td><span style="color:green"><%=(request.getAttribute("suhMessage") == null) ? "" : request.getAttribute("suhMessage")%></span></td>
</tr>
    </td>
    </tr>
</table>
<div style="text-align:center; width:400px">
	<a href="adminModifyUser.jsp">Modify</a>
</div>
<div style="text-align:center; width:800px">
	<a href="adminRevokeUser.jsp">Change Revoke Status</a>
</div>
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log out</a>
</div>
</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>