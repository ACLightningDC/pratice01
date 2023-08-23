<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
ul > li {
	float:left;
	margin-right:20px;
}
ul > li >ul >li{
	clear: both;
}
ul , li{
	padding-left:0; margin-left:0;
	margin-right:20px;
	list-style-type:none;
}

</style>
</head>
<body>
<div>
<a href="<%=request.getContextPath()%>/userMain.jsp">
	<img src="${pageContext.request.contextPath}/images/logo/maclogo.png" width="50px"/>
	</a>
</div>
<section>
	<div class="nav">
		<ul>
			<li>
			메뉴
				<ul>
					<li>세트</li>
					<li>버거</li>
				</ul>
			</li>
			<li>
				<span><a href="<%=request.getContextPath() %>/menu.kiosk">주문하기</a></span>
				<ul>
					<li>
					<input type="radio" name="tabs" checked id="tab1">
					<label for="tab1">
					<a href="<%=request.getContextPath() %>/set.kiosk">세트</a>
					</label>
					</li>
					<li>
					<input type="radio" name="tabs" checked id="tab2">
					<label for="tab2">
					<a href="<%=request.getContextPath() %>/buger.kiosk">버거</a>
					</label>
					</li>
					<li>
					<input type="radio" name="tabs" id="tab3">
					<label for="tab3">
					<a href="<%=request.getContextPath() %>/drink.kiosk">음료</a>
					</label>
					</li>
					<li>
					<input type="radio" name="tabs" id="tab4">
					<label for="tab4">
					<a href="<%=request.getContextPath() %>/side.kiosk">사이드</a>
					</label>
					</li>
					<li>
					<input type="radio" name="tabs" id="tab5">
					<label for="tab5">
					<a href="<%=request.getContextPath() %>/dessert.kiosk">디저트</a>
					</label>
					</li>
				</ul>
			</li>
			<li>이벤트</li>
			<li>가맹점 문의</li>
		</ul>
		
		<div class="header3">
			<!-- 자바: if~else 구현(JSTL에는 else 문이 없으므로) -->
			<!-- 방법-1 : choose, 방법-2 : if문 2번 사용 -->
			<c:choose>
				<c:when test="${sessionScope.u_id eq null }">
				<ul>
					<a href="<%=request.getContextPath() %>/userLogin.usr">로그인</a>
					|
					<a href="<%=request.getContextPath() %>/userJoin.jsp">회원가입</a>
				</ul>
				</c:when>
				<c:otherwise>
				<ul>
					${sessionScope.u_name}님 환영합니다.<br>
					<a href="<%=request.getContextPath() %>/userLogout.usr">로그 아웃</a>
					|
					<a href="<%=request.getContextPath() %>/myOrder.kiosk">주문내역보기</a>
					|
					<a href="<%=request.getContextPath() %>/userView.usr">회원정보관리</a>
					|
					<a href="<%=request.getContextPath() %>/userLogout.usr">고객문의</a>
				</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</section>
	
</body>
</html>