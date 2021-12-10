<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내용</title>
</head>
<body>
<!-- 수정123 -->
	<h2>Content</h2>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${vo.num }</td>
			<th>업로더</th>
			<td width="100px">${vo.uploader }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${vo.regdate }</td>
			<th>조회수</th>
			<td>${vo.readcount }</td>
		</tr>

		<tr>
			<th>파일 설명</th>
			<td colspan="3">${vo.description }</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<c:forEach var="fileName" items="${fileNames }">
					<c:if test="${fileName eq vo.filename}">
						<!-- <a href="${pageContext.request.contextPath}/WEB-INF/upload/${fileName}" download>${fileName} 다운로드</a>-->
						<!-- 브라우저에서 WEB-INF 안에 직접 접근할 수 없음! -->
						<!-- Servers >context.xml 에 프로젝트 외부 경로와 mount 설정 해주기 -->
						<a href="/upload/${fileName}" download>${fileName}  다운로드</a>
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="button" value="수정" onclick="window.location='${pageContext.request.contextPath}/update.do?num=${vo.num}'">&nbsp;&nbsp;
				<input type="button" value="삭제" onclick="window.location='${pageContext.request.contextPath}/delete.do?num=${vo.num}'">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/list.do'">
			</td>
		</tr>
	</table>
</body>
</html>