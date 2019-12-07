 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Page</title>
</head>
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Admin")== null) )
{
%>
<jsp:forward page="/login.jsp"></jsp:forward>
<%} %>
<body>
<center><h2>Admin's Home</h2></center>
 
Welcome <%=request.getAttribute("userName") %>
<ul>
<li><a href="/company_management/UserController?action=viewMyProfile&id=<%=request.getAttribute("userId") %>"  target="_top"><span>View My Profile</span></a></li>
<li><a href="/company_management/UserAdminController?action=adminUserList" target="_top"><span>User List</span></a></li>
<li><a href="adminSearchUser.jsp"  target="_top"><span>Search User</span></a></li>
</ul>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutController">Logout</a></div>
</body>
</html>