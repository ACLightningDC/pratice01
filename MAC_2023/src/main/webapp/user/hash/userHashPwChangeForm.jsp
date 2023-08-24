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
		<form method="post" action="userHashPwChangeAction.usr">
			보안을 위해 비밀번호를 바꿔주세요
			
			<table>
		<tr>
			<td>
				임시 비빌번호 입력
			</td>
			<td>
				<input type="text" name="temporary_password" >
			</td>
		</tr>
		<tr>
			<td>
				바꿀 비밀번호 입력
			</td>
			<td>
				<input type="text" name="password" >
			</td>
		</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="비밀번호 바꾸기">
				</th>
			</tr>
		</table>
		</form>
	</div>

</body>
</html>