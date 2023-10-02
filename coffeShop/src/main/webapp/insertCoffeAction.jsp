<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="connection.jsp"%>
<%
	try{
		String pcode = request.getParameter("pcode"); 
		String pname = request.getParameter("pname");
		String pcost = request.getParameter("pcost");
		
		sql = "INSERT INTO coffee_tbl values(?,?,?)";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, pcode);
		ps.setString(2, pname);
		ps.setString(3, pcost);
		
		ps.executeUpdate();
		
		con.commit();//트랙잭션 완료 : 영구 저장
		
		//성공 알림창
		%>
			<script type="text/javascript">
				alert('커피등록이 완료되었습니다');
				location.href('insertMember.jsp');
			</script>
		<%
		
	}catch(Exception e){
		
		//실패 알림창
		
		con.rollback();//트랜잭션 취소 : 이전 상태로
		
		%>
			<script type="text/javascript">
				alert('커피등록이 실패되었습니다');
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