<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="connection.jsp"%>
<%
	try{
		String custno = request.getParameter("custno"); 
		String custname = request.getParameter("custname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		
		sql = "update member_tbl set custname = ?, gender = ?,phone = ?,address = ?,joindate = ?,grade = ?,city = ? where custno = ?";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, custname);
		ps.setString(2, gender);
		ps.setString(3, phone);
		ps.setString(4, address);
		ps.setString(5, joindate);
		ps.setString(6, grade);
		ps.setString(7, city);
		ps.setString(8, custno);
		
		ps.executeUpdate();
		
		con.commit();
		//성공 알림창
		%>
			<script type="text/javascript">
				alert('회원수정이 완료되었습니다');
				location.href = 'MemberSelect.jsp';
			</script>
		<%
		
	}catch(Exception e){
		
		con.rollback();
		
		//실패 알림창
		e.printStackTrace();
		
		%>
			<script type="text/javascript">
				alert('회원수정이 실패되었습니다');
				history.back();
			</script>
		<%
		
	}finally{
		try{
			if(con!=null)con.close();
			if(ps!=null)ps.close();
			if(rs!=null)rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


%>