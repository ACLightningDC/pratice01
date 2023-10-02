<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
		<div>
			<form action="insertCoffeAction.jsp" method="post" name="f">
				<table>
					<tr>
						<th>상품코드</th>
						<td><input name="pcode" type="text" value="" >4글자 내로 입력하세요 ex)A101</td>
					<tr>
					<tr>
						<th>상품명</th>
						<td><input name="pname" type="text" value="" ></td>
					</tr>
					<tr>
						<th>상품단가</th>
						<td><input name="pcost" type="text" value="" >가격만 입력하시오 ex)6500</td>
					</tr>
				</table>
				<input type="submit" value="등록" onclick="insertCoffeSubmit(); return false;">
				<input type="reset" value="다시쓰기" onclick="insertMemberResetAlert(); return false;">
			</form>
		</div>
			
		
	<%@ include file="footer.jsp" %>
</body>
</html>