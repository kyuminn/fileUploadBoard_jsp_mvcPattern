<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
<script src="${pageContext.request.contextPath}/script.js"></script>
</head>
<body>
	<h2>수정 페이지</h2>
	<form action="${pageContext.request.contextPath}/updateProc.do?num=${vo.num}" method="post" encType="multipart/form-data" 
	name="updateForm" onsubmit="return UpdatePassCheck()">
		<table border="1">
			<tr>
				<th width="150px">파일 설명</th>
				<td>
					<textarea name="description" rows="5" cols="30">${vo.description }</textarea>
				</td>
			</tr>
			<tr>
				<th>업로드된 파일</th>
				<td>
					<a href="/upload/${vo.filename }" download>${vo.filename }</a>
					<input type="hidden" name="uploadedFile" value="${vo.filename}">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password"></td>
				<!-- 파일보다 비밀번호 먼저 받아서 검증할 수 있도록 순서 조정함 -->
			</tr>
			<tr>
				<th>새로 올릴 파일</th>
				<td><input type="file" name="updateFile"></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정"> &nbsp;
					<input type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/list.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>