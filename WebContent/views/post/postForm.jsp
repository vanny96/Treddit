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
<h1>New Post</h1>
<form:form action="${pageContext.request.contextPath}/posts/new" method="post" modelAttribute="post">
<form:hidden path="id"/>

<form:label path="title">Title</form:label><br>
<form:input path="title"/><br>
<form:errors path="title" cssStyle="color:red"/><br>

<form:label path="body">Body</form:label><br>
<form:textarea path="body"/><br>
<form:errors path="body" cssStyle="color:red"/><br>

<input type="submit" value="Crea Post">

</form:form>
</body>
</html>