<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/login">Login</a>
<a href="${pageContext.request.contextPath}/users/new">Sign Up</a>
<a href="${pageContext.request.contextPath}/posts/new">New post</a>
<c:forEach items="${posts}" var="post">
<h4><a href="${pageContext.request.contextPath}/posts/${post.id}">${post.title}</a></h4><br>
<p>${post.body}</p>
</c:forEach>
</body>
</html>