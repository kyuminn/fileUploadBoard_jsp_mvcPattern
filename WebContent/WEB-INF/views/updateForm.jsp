<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
</head>
<body>
	<h2>수정 페이지</h2>
	<form action="${pageContext.request.contextPath}/updateProc?num=${vo.num}" method="post" encType="multipart/form-data">
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
				<th>새로 올릴 파일</th>
				<td><input type="file" name="updateFile"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정"> &nbsp;
					<input type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/list'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>