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
		sql = "select max(custno)+1 , to_char(sysdate , 'YYYY-MM-DD') nowDate from member_tbl";
		ps = con.prepareStatement(sql);
		rs= ps.executeQuery(sql);
		int custnoLast = 0;
		String nowDate = null;
		if(rs.next()){
			custnoLast = rs.getInt(1);
			nowDate = rs.getString(2);
		}
		%>
		<section>
			<div>
				<form action="insertMemberAction.jsp" method="post" name="f">
					<table>
						<tr>
							<td>회원번호</td>
							<td><input name="custno" type="text" value="<%=custnoLast %>" readonly="readonly"></td>
						<tr>
						<tr>
							<td>회원성명</td>
							<td><input name="custname" type="text" value="" ></td>
						</tr>
						<tr>
							<td>성별</td>
							<td><label><input name="gender" type="radio" value="F" >여</label></td>
							<td><label><input name="gender" type="radio" value="M" >남</label></td>
						</tr>
						<tr>
							<td>회원전화</td>
							<td><input name="phone" type="text" value="" ></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input name="address" type="text" value="" ></td>
						</tr>
						<tr>
							<td>가입일자</td>
							<td><input name="joindate" type="text" value="<%= nowDate  %>" readonly="readonly" ></td>
						</tr>
						<tr>
							<td>고객등급[A:VIP, B:일반 , C:직원]</td>
							<td><input name="grade" type="text" value="" ></td>
						<tr>
							<td>거주도시</td>
							<td><input name="city" type="text" value="" ></td> 숫자 두자리 입력
						</tr>
					</table>
					<input type="submit" value="등록" onclick="insertMemberSubmit(); return false;">
					<input type="reset" value="다시쓰기" onclick="insertMemberResetAlert(); return false;">
				</form>
			</div>
		</section>
			
		
	<%@ include file="footer.jsp" %>
</body>
</html>