<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맥 카페</title>
<meta name="viewport" content="width=device-width , initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body onload="">
	<h1>MAC_CAFE</h1>
	
	<a href="<%=request.getContextPath()%>/userMain.jsp" target="_blank">사용자모드</a> 
	|
	<a href="${pageContext.request.contextPath}/adminMain.jsp" target="_blank">관리자모드</a> 
</body>
</html>