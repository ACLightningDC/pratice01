<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet">
<script type="text/javascript" src="javascript.js"></script>
</head>
<body>
<%@include file="connection.jsp" %>
	<header>
		<div id="headerTitleDiv">
			<h1>커피숍 회원관리</h1>
		</div>
	<nav>
		<div id="navDiv">
			<ul>
				<li><a href="insertMember.jsp">회원등록</a>
				<li><a href="insertCoffe.jsp">커피등록</a>
				<li><a href="MemberSelect.jsp">회원목록 조회/수정</a>
				<li><a href="CoffeeSelect.jsp">커피 목록 조회</a>
				<li><a href="selectSales.jsp">매출 현황 조회</a>
				<li><a href="index.jsp">홈으로.</a>
			</ul>
		</div>	
	</nav>
	</header>
	
</body>
</html>