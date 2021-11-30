<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:if test="${result==1}"> <!--  비밀번호 일치 시 -->
 	<meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/list">
 </c:if>
 <c:if test="${result==0 }">
 	<script>
 		alert("비밀번호가 다릅니다!");
 		history.go(-1);
 	</script>
 </c:if>
