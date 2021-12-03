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
	<c:if test="${ls == null }">
	<table>
		<tr>
			<td>
				게시판에 저장된 글이 없습니다.
			</td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/upload.do">파일 올리기</a></td>
		</tr>
	</table>
	</c:if>
	
<c:if test="${ls !=null }">
	<h2>파일 목록[${page.totalRowCount }개의 파일]</h2>
	<table class="upload">
		<tr>
			<td><a href="${pageContext.request.contextPath}/upload.do">파일 올리기</a></td>
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
					<a href="${pageContext.request.contextPath}/content.do?num=${article.num}">${article.description}</a>
				</td>
				<td>${article.filename }</td>
				<td>${article.uploader }</td>
				<td>${article.regdate }</td>
				<td>${article.readcount }</td>
			</tr>
		</c:forEach>
	<tr>
		<td colspan="6" align="center">
			<input type="button" value="메인으로" onclick="window.location='${pageContext.request.contextPath}/main.do'">&nbsp;
			<input type="button" value="파일 올리기" onclick="window.location='${pageContext.request.contextPath}/upload.do'">
		</td>
	</tr>
	</table>
	</c:if>
	
	<c:if test="${firstBlock == true}">
		<a href="${pageContext.request.contextPath}/list.do?pageNum=${page.back}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPageNum}" end="${page.lastPageNum}" >
		<a href="${pageContext.request.contextPath}/list.do?pageNum=${i }">[${i}]</a>&nbsp;&nbsp;
	</c:forEach>
	<c:if test="${lastBlock == true }">
		<a href="${pageContext.request.contextPath}/list.do?pageNum=${page.forward}">[다음]</a>
	</c:if>
</body>
</html>