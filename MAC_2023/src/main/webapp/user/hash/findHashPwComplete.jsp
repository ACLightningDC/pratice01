<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>고객님의 임시비밀번호는 ${random_password}입니다.</h3>
	<a href="userLogin.usr">[로그인]</a><!-- 다시 '로그인 폼 보기'요청 -->
	
	<!-- 로그인 후 '암호화된 비밀번호 변경'하도록 수정함  -->
	<a href="userHashPwChangeForm.usr">[암호화된 비밀번호 변경]</a>
</body>
</html>