<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c tag의 경우 contextPath 가 자동으로 붙어서 나옴 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${flag==true}">
	<c:redirect url="/list"></c:redirect>
</c:if>
