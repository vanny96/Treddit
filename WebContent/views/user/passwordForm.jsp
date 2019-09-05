<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="${requestScope.requestURI}" method="post">

<label for="oldPassword">Old password:</label><br>
<input type="password" name="oldPassword"/><br>

<label for="newPassword">New password:</label><br>
<input type="password" name="newPassword"/><br>

<input type="submit" value="Change password" />

</form:form>
</body>
</html>