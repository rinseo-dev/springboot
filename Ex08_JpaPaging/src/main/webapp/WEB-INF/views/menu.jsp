<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JPA PAGING</h1>
   
	<a href="/insert">데이터 추가</a></p><br>
	<a href="/selectAll">전체 조회</a></p><br>
	    
	<a href="/selectByNameLike?name=user&page=1">Name Like 조회 : 1페이지</a></p><br>     
	<a href="/selectByNameLike?name=user&page=2">Name Like 조회 : 2페이지</a></p><br>     
	<a href="/selectByNameLike?name=user&page=3">Name Like 조회 : 3페이지</a></p><br>     
   
</body>
</html>