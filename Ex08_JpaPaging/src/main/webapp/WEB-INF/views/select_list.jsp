<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select_list</title>
</head>
<body>
	<h1>JPA PAGING - NAME LIKE PAGING</h1>

	총 글의 개수 : ${totalElements }<br>
	총 페이지 : ${totalPages }<br>
	한 페이지당 글의 수 : ${size }<br>
	현재 페이지   : ${pageNumber }<br>
	현재 페이지 글의 개수 : ${numberOfElements }
	<hr>
	<c:forEach items="${members }" var="member">
		아이디 : ${member.id }<br>
		이름 : ${member.name }<br>
		이메일 : ${member.email }
		<hr>
	</c:forEach>
</body>
</html>