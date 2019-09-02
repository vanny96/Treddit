<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>New User</h1>
<form:form action="${pageContext.request.contextPath}/users" method="post" modelAttribute="user">
<form:hidden path="id"/>

<form:label path="username">Username</form:label><br>
<form:input path="username"/><br>
<form:errors path="username" cssStyle="color:red"/><br>

<form:label path="password">Password</form:label><br>
<form:password path="password"/><br>
<form:errors path="password" cssStyle="color:red"/><br>

<input type="submit" value="Registrati">

</form:form>
</body>
</html>