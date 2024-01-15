<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<h1>게시판</h1>
	<table>
		<tr>
			<td colspan="4">총 레코드 수 : ${totalRecord }</td>
			<!-- Model로 넘긴 key값 totalRecord사용 -->
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>삭제</th>
		</tr>
		<!-- Model로 넘긴 list값에 있는걸 하나씩 꺼내와서 blist에 넣음
			blist.필드이름 으로 각각 값을 출력해줄 수 있음 -->
		<c:forEach items="${list }" var="blist">
		<tr>
			<td>${blist.no }</td>
			<td><a href="detail?no=${blist.no}">${blist.title }</a></td>
			<td>${blist.writer }</td>
			<td>${blist.writer }</td>
			<td>
				<button type="button" class="btn btn-secondary" onclick="location.href='delete?no=${blist.no}'">삭제</button>
			</td>
		</tr>
		</c:forEach>
	</table>
	<a href="writeForm"><button type="button">글작성</button></a>
	<!-- WEB-INF 하위 주소로는 링크로 갈 수 없으므로 Controller에서 링크 해줘야함 -->
</body>
</html>