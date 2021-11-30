<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>
	<h1>파일 게시판 Main page</h1>
	<br>
	<input type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/list'">&nbsp;&nbsp;
	<input type="button" value="파일 업로드" onclick="window.location='${pageContext.request.contextPath}/upload'">
</body>
</html>