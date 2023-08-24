<%@page import="com.mysql.cj.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<div>
		<%-- <h3>회원님의 아이디는 ${u_id}</h3> --%>
		
		<% 
		String u_id = (String)request.getAttribute("u_id"); 
		//앞글자 4글자만 보이고 나머지 뒷부분은 * 로 대체 
		
		//String u_id = "감자튀김먹고싶다";
		u_id = String.format("%-"+u_id.length()+"s", u_id.substring(0, 4)).replace(" ", "*");
		%>
		<%= u_id %>
		<br>
		<a href="">[확인]</a>
	</div>
</body>
</html>