<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>Upload Form</h2>
	<form name="uploadForm" action="${pageContext.request.contextPath}/uploadProc" method="post" enctype="multipart/form-data" onsubmit="return uploadCheck()">
		<table border="1">

			<tr>
				<th>게시자</th>
				<td><input type="text" name="uploader"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="file" name="uploadFile">
				</td>
			</tr>
			<tr>
				<th>파일 설명</th>
				<td><textarea name="description" rows="7" cols="25" ></textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="업로드">
					<input type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/list'">
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>