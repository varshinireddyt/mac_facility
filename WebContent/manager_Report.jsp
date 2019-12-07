<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<p><b>Note:</b> Report user for:</p>
<form action="">
  <input type="radio" name="gender" value="male"> No-show<br>
  <input type="radio" name="gender" value="female"> Violation<br>
  <input type="submit" value="Submit">
</form>
<div style="width:700px; ">
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