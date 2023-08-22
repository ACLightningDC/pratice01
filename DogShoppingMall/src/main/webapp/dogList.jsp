<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    <%
    //ArrayList<Dog> dogList = (ArrayList<Dog>)request.getAttribute("dogList");
    //ArrayList<String> 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#listForm{
	margin: auto;
	width: 700px;
	height: 500px
	border: 1px solid red;
}

h2{
	text-align:center;
}

table{
	margin: auto;
	width: 550px;
}

/*각 상품의 이미지 스타일 */
#productImage{
 	width: 150px;
 	height: 150px;
 	border: none;
}

#todayImageList{
	text-align: center;
}

/*오늘 본 상품의 이미지 스타일 */
#todayImage{
 	width: 100px;
 	height: 100px;
 	border: none;
}

.div_empty{	
	width: 100%;
	height: 100%;
	text-align:	center;
	background-color: yellow;
}
</style>
</head>
<body>
	<section id="listForm">
	
	<a href="dogRegisterForm.dog">개상품등록(관리자모드)</a>
	
	<!-- [윗쪽 : 개상품 목록]------------------------------------------- -->
	<c:if test="${dogList != null}">
		<h2>개 상품 목록 </h2>
		<table>
			<tr>
			<c:forEach var="dog" items="${dogList}" varStatus="status">
				<td>
					<a href="dogView.dog?id=${dog.id}"><img alt="" src="images/${dog.image}" id="productImage"></a>
					상품명 : ${dog.kind}	<br>
					가격 : ${dog.price}	<br>
				</td>
				
				<c:if test="${(status.count mod 4)==0}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
			
			</tr>
		</table>
	</c:if>
	
	<c:if test="${dogList == null}">
		<div class="div_empty">개상품이 없습니다. 분양불가</div>
	</c:if>
	
	<!--  [아랫쪽 : 오늘 본 개 상품 목록  --------------------------------->
	<c:if test="${(todayImageList !=null) && (dogList != null)}">
		<div id="todayImageList">
			<h2>오늘 본 개상품 목록</h2>
			<table>
				<tr>
				
				<c:forEach var="todayImage" items="${todayImageList}" varStatus="status">
				<td>
					<img alt="" src="images/${todayImage}" id="todayImage">
				</td>
				
				<c:if test="${status.count mod 4==0}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
				
				</tr>
			</table>
		</div>
	</c:if>
	</section>
</body>
</html>