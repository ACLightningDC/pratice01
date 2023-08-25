<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
/* String cookie = request.getHeader("Cookie");
if(cookie!=null){
	
	Cookie[] cookies = request.getCookies();
	for(int i = 0 ;i <cookies.length;i++){
		String cookieName = cookies[i].getName();
		
		if(cookieName.equals("u_id")){
			id = cookies[i].getValue();
		}
		
		if(cookieName.equals("checkbox")){
			checked=cookies[i].getValue();
		}
	}
} */
String id="";//초기화 중요(null로 초기화 하면 안됨)
String checked="";

String cookie = request.getHeader("Cookie");
if(cookie !=null){
	Cookie[] cookies = request.getCookies();
	for(int i=0;i<cookies.length;i++){
		String cookieName = cookies[i].getName();
		
		if(cookieName.equals("u_id")){
			id=cookies[i].getValue();
		}
		
		if(cookieName.equals("checkbox")){
			checked=cookies[i].getValue();
		}
	}
}

Cookie[] cookies = request.getCookies();
if(cookies !=null&& cookies.length > 0){
	for(int i =0; i<cookies.length;i++){
		if(cookies[i].getName().equals("u_id")){
			id=cookies[i].getValue();
			break;
		}
		
	}
}


%>

<form action="userLoginAction.usr" method="post">
		<input type="hidden" name="grade">
		<table>
			<tr>
				<th colspan="2"><h1>로그인</h1></th>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
				<input type="text" name="id" size="30" value="${cookie.u_id.value}" placeholder="아이디입력(필수)"/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
				<input type="password" name="password" size="31" placeholder="비밀번호입력(필수)"/> <br>
				</td>
			</tr>
			<tr>
			<th colspan="2">
				<input type="checkbox" name="checkbox" />아이디 저장
				<input type="submit" value="로그인"/>
				</th>
			</tr>
			
			
			<tr>
			<th colspan="2">
				<a href="userIdFindForm.usr">[아이디 찾기]</a>
				<a href="userPwFindForm.usr">[비밀번호 찾기]</a>
				<a href="userHashPwFindForm.usr">[암호화된 비밀번호 찾기]</a>
				
				</th>
			</tr>

		</table>
		
	</form>
</body>
</html>