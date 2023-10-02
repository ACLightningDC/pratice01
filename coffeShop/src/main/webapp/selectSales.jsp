<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@include file="header.jsp" %>
	<%
		sql = "select pname , pcost , sum(amount) as amount , pcost*sum(amount) from sale_tbl sale join coffee_tbl coffe on sale.pcode = coffe.pcode group by pname , pcost order by pcost desc";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
	%>
	<table>
		<tr>
			<th>상품명</th>
			<th>상품단가</th>
			<th>총판매수량</th>
			<th>총판매금액</th>
		</tr>
		<%while(rs.next()){ %>
		<tr>
			<td><%=rs.getString(1) %></td>
			<td><%=rs.getString(2) %></td>
			<td><%=rs.getString(3) %></td>
			<td><%=rs.getString(4) %></td>
		</tr>
		<%} %>
	</table>
	<%@include file="footer.jsp" %>
</head>
<body>
	
</body>
</html>