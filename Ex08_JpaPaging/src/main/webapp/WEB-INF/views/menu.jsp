<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Member JPA #02</h1>
   
	<a href="/insert">데이터 추가</a></p><br>
	<a href="/selectAll">전체 조회</a></p><br>
	
	<a href="/selectById?id=1">개별 조회-byId</a></p><br>
	<a href="/selectByEmail?email=test7@test.com">개별 조회-byEmail</a></p><br>     
	<a href="/selectByName?name=연개소문">개별 조회-byName</a></p><br>   
	<a href="/selectByNameLike?name=김">개별 조회-Name Like</a></p><br>     
	<a href="/selectByNameLikeDesc?name=김">개별 조회-Name Like Desc</a></p><br>     
	<a href="/selectByNameLikeSort?name=김">개별 조회-Name Like Sort</a></p><br>     
</body>
</html>