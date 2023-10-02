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
		sql = "select custno, custname , decode(gender ,'M' , '남자'	,'F','여자') as gender, phone , address , to_char(joindate , 'YYYY-MM-DD'), decode(grade, 'A' ,'VIP' ,'B','일반', 'C','직원') as grade, city from member_tbl";
		ps = con.prepareStatement(sql);
		rs= ps.executeQuery(sql);
		%>
<body>
	<section>
		<div>
			<h2>회원목록 조회/수정</h2>
		</div>
		<div>
			<table>
				<tr>
					<th>회원번호</th>
					<th>회원성명</th>
					<th>성별</th>
					<th>회원전화</th>
					<th>주소</th>
					<th>가입일자</th>
					<th>고객등급</th>
					<th>거주도시</th>
				</tr>
				<% while(rs.next()){ %>
					<tr>
						<td><a href="MemberUpdate.jsp?custno=<%= rs.getString(1)%>"><%= rs.getString(1)%></a></td>					
						<td><%= rs.getString(2)%></td>					
						<td><%= rs.getString(3)%></td>					
						<td><%= rs.getString(4)%></td>					
						<td><%= rs.getString(5)%></td>					
						<td><%= rs.getString(6)%></td>					
						<td><%= rs.getString(7)%></td>					
						<td><%= rs.getString(8)%></td>					
					</tr>
				<% }%>
				
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>