<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/hash/userHashPwChangeFromJavaScript.js">
</script>
</head>
<body>
	<div>
		<form method="post" action="userHashPwChangeAction.usr" name = "f">
			보안을 위해 비밀번호를 바꿔주세요
			
			<table>
		<tr>
			<td>
				임시 비빌번호 입력
			</td>
			<td>
				<input type="text" name="pre_password" >
			</td>
		</tr>
		<tr>
			<td>
				바꿀 비밀번호 입력
			</td>
			<td>
				<input type="text" name="new_password" >
			</td>
		</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="비밀번호 바꾸기 onclick 없는거 ">
				</th>
			</tr>
		<tr>
			<td>
				바꿀 비밀번호 재확인
			</td>
			<td>
				<input type="text" name="passwordCheck" >
			</td>
		</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="비밀번호 바꾸기" onclick="check(); return false;">
				</th>
			</tr>
		</table>
		</form>
	</div>

</body>
</html>