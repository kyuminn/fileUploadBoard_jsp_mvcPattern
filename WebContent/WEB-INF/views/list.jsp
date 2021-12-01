<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<h2>파일 목록[${count }개의 파일]</h2>
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
	
<c:if test="${count>0 }">
	<c:set var="imsi" value="${count%pageSize ==0? 0:1 }"/>
	<c:set var="pageCount" value="${count/pageSize +imsi }"/>
	<fmt:parseNumber var ="pageCount" value="${pageCount}" integerOnly="true"/>
	<c:set var="pageBlock" value="${3}"/>
	<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock }" integerOnly ="true"/>
	<c:set var="startPage" value="${result*pageBlock +1 }"/>
	<c:set var="endPage" value="${startPage+pageBlock -1 }"/>
	<c:if test="${endPage>pageCount }">
		<c:set var="endPage" value="${pageCount }"/>
	</c:if>
	<c:if test="${startPage > pageBlock }">
		<a href="${pageContext.request.contextPath}/list.do?pageNum=${startPage-pageBlock}">이전</a>
	</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="${pageContext.request.contextPath}/list.do?pageNum=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<a href="${pageContext.request.contextPath}/list.do?pageNum=${startPage+pageBlock}">다음</a>
	</c:if>
</c:if>
</body>
</html>