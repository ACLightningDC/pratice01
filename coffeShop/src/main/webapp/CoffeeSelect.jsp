<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="header.jsp" %>
		<% 
		sql = "select pcode , pname ,pcost from coffee_tbl";
		ps = con.prepareStatement(sql);
		rs= ps.executeQuery();
		%>
<body>
	<section>
		<div>
			<h2>커피 목록 조회</h2>
		</div>
		<div>
			<table>
				<tr>
					<th>상품코드</th>
					<th>상품명</th>
					<th>상품단가</th>
				</tr>
				<% while(rs.next()){ %>
					<tr>
						<td><a href="CoffeeUpdate.jsp?pcode=<%= rs.getString(1)%>"><%= rs.getString(1)%></a></td>					
						<td><%= rs.getString(2)%></td>					
						<td><%= rs.getString(3)%></td>					
					</tr>
				<% }%>
				
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>