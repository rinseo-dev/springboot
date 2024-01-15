<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<form action="write" method="post">
		<div class="mb-3">
	  		<label for="exampleformControlInput1" class="form-label">제목</label>
	    	<input name="title" class="form-control" id="exampleformControlInput1">
		</div>
	  
		<div class="mb-3">
	    	<label for="exampleformControlInput1" class="form-label">작성자</label>
	    	<input name="writer" class="form-control" id="exampleformControlInput1">
	  </div>
	  
	  <div class="mb-3">
	    <label for="exampleformControlInput1" class="form-label">내용</label>
	    <textarea name="content" class="form-control" id="exampleformControlInput1" rows="5"></textarea>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	  <button type="button" class="btn btn-danger" onclick="location.href='list'">목록보기</button>
	</form>
</body>
</html>