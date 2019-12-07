<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update My Information</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<h2>Update My Information: </h2>
 <form name="updateMyProfile" action="/company_management/UserController?updateMyProfile" method="post">
 <input name="userId" value="<c:out value='${USERS.userId}'/>" type="hidden"> 
 <input name="username" value="<c:out value='${USERS.userName}'/>" type="hidden"> 
 <input name="role" value="<c:out value='${USERS.role}'/>" type="hidden"> 
<table>
  <tr>
   <td>
   
    <table border="1" class="myTable">
    <tr>
    <td> User ID: </td>
    <td> <input value="<c:out value='${USERS.userId}'/>" maxlength="16" disabled="disabled"> </td>
    </tr>
     
    <tr>
    <td> User Name: </td>
    <td> <input value="<c:out value='${USERS.userName}'/>" maxlength="16" disabled="disabled"> </td>
    </tr>
    
    <tr>
    <td> Password: </td>
    <td> <input name="password" value="<c:out value='${USERS.password}'/>" type="password" maxlength="16"> </td>
    </tr>

    <tr>
    <td> First Name: </td>
    <td> <input name="firstname" value="<c:out value='${USERS.firstName}'/>" type="text" maxlength="16"> </td>
    </tr>

    <tr>
    <td> Last Name: </td>
    <td> <input name="lastname" value="<c:out value='${USERS.lastName}'/>" type="text" maxlength="16"> </td>
    </tr>
    
    <tr>
    <td> Email: </td>
    <td> <input name="email" value="<c:out value='${USERS.email}'/>" type="text" maxlength="16"> </td>
    </tr>
    
    <tr>
    <td> Role: </td>
    <td> <input value="<c:out value='${USERS.role}'/>" maxlength="16" disabled="disabled"> </td>
    </tr>

    </table>
    </td>
    </tr>
    </table>
    <input name="action" value="updateMyProfile" type="hidden">
    <input name="updateProfileButton" type="submit" value="Update Profile">
    </form>
<div style="width:400px">
<form method="GET" action="<%=request.getContextPath()%>/updateMyProfile.jsp">
</form>
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