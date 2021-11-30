<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 목록</title>
<style type="text/css">
	table{
		width:700px;
	}
	.upload {
		text-align:right;
	}
</style>
</head>
<body>
	<h2>파일 목록</h2>
	<table class="upload">
		<tr>
			<td><a href="${pageContext.request.contextPath}/upload">파일 올리기</a></td>
		</tr>
	</table>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>파일 설명</th>
			<th>파일 이름</th>
			<th>업로더</th>
			<th>최근 업데이트 시간</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="article" items="${ls}">
			<tr>
				<td>${article.num }</td>
				<td>
					<a href="${pageContext.request.contextPath}/content?num=${article.num}">${article.description}</a>
				</td>
				<td>${article.filename }</td>
				<td>${article.uploader }</td>
				<td>${article.regdate }</td>
				<td>${article.readcount }</td>
			</tr>
		</c:forEach>
	<tr>
		<td colspan="6" align="center">
			<input type="button" value="메인으로" onclick="window.location='${pageContext.request.contextPath}/'">&nbsp;
			<input type="button" value="파일 올리기" onclick="window.location='${pageContext.request.contextPath}/upload'">
		</td>
	</tr>
	</table>
</body>
</html>