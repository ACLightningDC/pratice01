<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="header.jsp" %>
	<%
		sql="select pcode , pname ,pcost from coffee_tbl where pcode= ?";
	
		ps =  con.prepareStatement(sql);
		ps.setString(1, request.getParameter("pcode"));
		
		rs= ps.executeQuery();
		rs.next();
	%>
</head>
<body>
	<section>
		<div>
			<h2>커피목록 수정</h2>
		</div>
		<div>
			<form  method="post">
				<table>
					<tr>
						<th>상품코드</th>
						<td><input type="text" name ="pcode" value="<%= rs.getString(1) %>">4 글자 내로 입력하세요 ex) A101</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><input type="text" name ="pname" value="<%= rs.getString(2) %>"></td>
					</tr>
					<tr>
						<th>상품단가</th>
						<td><input type="text" name ="pcost" value="<%= rs.getString(3) %>">가격만 입력하시오 ex) 6500</td>
					</tr>	
				</table>
				<input type="submit" value="수정" formaction="CoffeeUpdateAction">
				<input type="submit" value="삭제" formaction="CoffeeDeleteAction">
			</form>
		</div>
	
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>