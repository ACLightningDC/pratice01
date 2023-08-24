<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호화된 비밀번호 찾기</title>
</head>
<body>
	<form action="userHashPwFindAction.usr" method="post">
		<table>
		<tr>
			<td>
				아이디 입력
			</td>
			<td>
				<input type="text" name="id" >
			</td>
		</tr>
		<tr>
			<td>
				아이디 입력
			</td>
			<td>
				<input type="text" name="email" >
			</td>
		</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="암호화된 비밀번호 찾기">
				</th>
			</tr>
		</table>
	</form>

</body>
</html>