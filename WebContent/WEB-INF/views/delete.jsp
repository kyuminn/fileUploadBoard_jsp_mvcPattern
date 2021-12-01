<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
 <script>
	function passCheck(){
		if (document.delForm.password.value ==""){
			alert("비밀번호를 입력하세요!");
		}
	}
</script> 

</head>
<body>
	<h2>삭제 페이지</h2>
	<h4>게시글과 올린 파일이 모두 삭제됩니다!</h4>
	<form name="delForm" action="${pageContext.request.contextPath}/deleteProc.do?num=${num}" method="post" onsubmit="return passCheck()">
		<table border="1">
			<tr>
				<th>파일 이름</th>
				<td>${fileName }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="삭제하기">
					<input type="hidden" name="fileName" value="${fileName}">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>