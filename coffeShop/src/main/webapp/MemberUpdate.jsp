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
		<% 
		
		
		sql = "select custno, custname , gender, phone , address , to_char(joindate , 'YYYY-MM-DD'), grade, city from member_tbl where custno = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, request.getParameter("custno"));
		rs= ps.executeQuery();
		rs.next();
		%>
		<section>
			<div>
				<form action="UpdateMemberAction.jsp" method="post" name="f">
					<table>
						<tr>
							<td>회원번호</td>
							<td><input name="custno" type="text" value="<%=rs.getString(1) %>" readonly="readonly"></td>
						<tr>
						<tr>
							<td>회원성명</td>
							<td><input name="custname" type="text" value="<%=rs.getString(2) %>" ></td>
						</tr>
						<tr>
							<td>성별 </td>
							<td><label><input name="gender" type="radio" value="F" <%= rs.getString(3).equals("F") ? "checked":""%>>여</label></td>
							<td><label><input name="gender" type="radio" value="M" <%= rs.getString(3).equals("M") ? "checked":""%>>남</label></td>
						</tr>
						<tr>
							<td>회원전화</td>
							<td><input name="phone" type="text" value="<%=rs.getString(4) %>" ></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input name="address" type="text" value="<%=rs.getString(5) %>" ></td>
						</tr>
						<tr>
							<td>가입일자</td>
							<td><input name="joindate" type="text" value="<%=rs.getString(6) %>" readonly="readonly" ></td>
						</tr>
						<tr>
							<td>고객등급[A:VIP, B:일반 , C:직원]</td>
							<td><input name="grade" type="text" value="<%=rs.getString(7) %>" ></td>
						<tr>
							<td>거주도시</td>
							<td><input name="city" type="text" value="<%=rs.getString(8) %>" ></td> 숫자 두자리 입력
						</tr>
					</table>
					<input type="submit" value="수정" onclick="insertMemberSubmit(); return false;">
					<input type="button" value="조회" onclick="location.href ='MemberSelect.jsp'">
				</form>
			</div>
		</section>
			
		
	<%@ include file="footer.jsp" %>
</body>
</html>