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
		
		sql = "INSERT INTO member_tbl values(?,?,?,?,?,?,?,?)";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, custno);
		ps.setString(2, custname);
		ps.setString(3, gender);
		ps.setString(4, phone);
		ps.setString(5, address);
		ps.setString(6, joindate);
		ps.setString(7, grade);
		ps.setString(8, city);
		
		ps.executeUpdate();
		
		con.commit();
		//성공 알림창
		%>
			<script type="text/javascript">
				alert('회원등록이 완료되었습니다');
				location='insertMember.jsp' ;
			</script>
		<%
		
	}catch(Exception e){
		
		//실패 알림창
		con.rollback();
		%>
			<script type="text/javascript">
				alert('회원등록이 실패되었습니다');
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