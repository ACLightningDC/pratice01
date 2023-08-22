<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 목록</title>
<style type="text/css">
#listForm {
   width: 640px;
   border: 1px red solid;
   margin: auto;
   
}

h2 {
   text-align: center;
}

table {
   width: 550px;
   margin: auto;
}

.tr_top {
   background-color: lime;
}

.div_empty {
   text-align: center;
   background-color:
}

.td_command {
   text-align: right;
}
#todayImageList{
   text-align: center;
}
#productImage{
   width:150px;
   height:150px;
   border:none;
}
#cartImage{
   width:70px;
   height:70px;
   border:none;
}
#select{
	text-align: right;
}
#commandList{
	text-align: center;
}
#upImage{
	width: 15px;
}
#downImage{
	width: 15px;
}
</style>
</head>
<script>
alert('경고');
</script>
<script type="text/javascript">
function checkAll(theForm){
	if(theForm.remove.length == undefined){ //개상품이 1개만 있다면
		theForm.remove.checked = theForm.allCheck.checked;//checked="checked"
	}else{//개상품이 2개 이상 있다면 -> 배열로 생성(checkbox가 같은 이름이므로 배열로 생성);
		for(var i=0;i<theForm.remove.length ;i++ ){
			theForm.remove[i].checked = theForm.allCheck.checked;
		}	
	}
}
//방벙-1 : id 로 개상품 구분
//항목 수량 1 증가
function checkQtyupID(id){
	window.location.href="dogCartQtyup.dog?id="+id;
}
//항목 수량 1감소 : 단 ,현재수량이 2이상일 때만 수량 감소 (1일때는 수량감소X)
function checkQtydownID(id, qty){
	if(qty != 1){
		window.location.href="dogCartQtydown.dog?id="+id;	
	}else{
		alert("더이상 수량을 감소시킬수 없습니다");
	}
}
//방법-2 : kind로 개상품 구분 
function checkQtyupKind(){
	window.location.href="dogCartQtyUp.dog?kind="+encodeURIcomponent(kind);
}

function checkQtyDownKind(){
	if(qty != 1){
		window.location.href="dogCartQtydown.dog?kind="+encodeURIcomponent(kind);
	}
}
</script>
<body>

<!-- startMoney(최하) endmoney(최고) 값을 검색할 대 사용하는데,
검색작업을 하지 않고 장바구니 목록보기 펭지가 실행된 경우는 이 값들이null이기 때문에
해당 속성들을 사용할 때 NullPointExeption 이 발생한다. 따라서, 발생하지 않도록 if 문으로 처리-->
	


	<c:if test="${startMoney !=null}">
		<c:set var="startMoney" value="${startMoney}"/>
	</c:if>
	
<!-- startMoney(최하) -->
	<c:if test="${endMoney !=null}">
		<c:set var="endMoney" value="${endMoney}"/>
	</c:if>

	

	<section id="listForm">
	<!-- 반드시 Scope 명 생략하기 이유? 속성("cartList")를 처음에는 sessionScope 에서 찾다가 최하 ~최고가격 [검색]시 requestScope에서 -->
	<%-- <c:if test="${!empty cartList}"> --%>
	<%-- <c:if test="${cartList != null and cartList.size()>0 }"> --%>
	<h2>장바구니 목록</h2>
	<form method="post">
		<table>
			<tr>
				<td colspan="6">
					<select name="statrtMoney" id="startMoney">
						<option>=최하=</option>
						<!-- switch문과 비슷 -->
						<c:choose>
							<c:when test="${startMoney == 1000}">
								<option selected="selected">1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney == 2000}">
								<option >1000</option>
								<option selected="selected">2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney == 3000}">
								<option>1000</option>
								<option>2000</option>
								<option selected="selected">3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney == 4000}">
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option selected="selected">4000</option>
							</c:when>
							<c:otherwise>
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:otherwise>
						</c:choose>
					</select>
					
					<select name="endMoney" id="endMoney">
						<option>=최고=</option>
						<!-- switch문과 비슷 -->
						<c:choose>
							<c:when test="${endMoney == 1000}">
								<option selected="selected">1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney == 2000}">
								<option >1000</option>
								<option selected="selected">2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney == 3000}">
								<option>1000</option>
								<option>2000</option>
								<option selected="selected">3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney == 4000}">
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option selected="selected">4000</option>
							</c:when>
							<c:otherwise>
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:otherwise>
						</c:choose>
					</select>
					
					<input type="submit" value="검색" formaction="dogCartSearch.dog"/>
				</td>
			</tr>
			
			<!-------------------------------------------------------------->
			<tr class="tr_top">
				<th> <!-- 전체 체크 박스 -->
					<input type="checkbox" name ="allCheck" onclick="checkAll(this.form)" />
				</th>
				<th>번호</th>
				<th>상품 이미지</th>
				<th>상품명</th>
				<th>가격</th>
				<th>수량</th>
			</tr>
			
			<%-- 이때 request Scope 생략가능 (이유? Scope 생략하면 pageScope >requestScope >sessionScope > applicationScope --%>
						<c:forEach var="cart" items="${cartList}" varStatus="status" >
				<tr>
				<%-- 삭제할 기준 id  --%>
					<td><input type ="checkbox" name="remove" value="${cart.id}"/></td>
					<td>${status.count}</td>
					<td><img  src="images/${cart.image}" id="cartImage"/></td>
					<td>${cart.kind }</td>
					<td>${cart.price }</td>
					<td>
					<%--구분 -1 :  id 로 개상품 구분 (권장)--%>
						<%-- 방법-1 : --%>
						<a href="dogCartQtyup.dog?id=${cart.id}&qty=${cart.qty}" onclick="this.click(function(e)){if('${cart.qty}'==1){alert('더 이상 값을 줄일수 없습니다.')}"><img  src="images/up.jpg" id="upImage" border="0" /></a>
						
						<%-- 방법-2 : 자바스크립트--%>
						<a href="javascript:checkQtyupID(${cart.id},${cart.qty})" ><img  src="images/up.jpg" id="upImage" border="0" /></a>
					<%--구분 -2 kind 로 개상품 구분 --%>	
						<%-- 방법-1 : --%>
						<a href="dogCartQtyup.dog?kind=${cart.encodingKind}&qty=${cart.qty}"><img  src="images/up.jpg" id="upImage" border="0" /></a>
						
						<%-- 방법-2 : 자바스크립트--%>
						<a href="javascript:checkQtyupKind('${cart.kind}',${cart.qty})"><img  src="images/up.jpg" id="upImage" border="0" /></a>
						<br>
						${cart.qty }
						<br>
											<%-- id 로 개상품 구분 (권장)--%>
						<%-- 방법-1 : 링크 get방식 (qty 전달이유? 전달받은 쪽에서 qty 값이 1이면 감소여부 판단)--%>
						<a href="dogCartQtydown.dog?id=${cart.id}&qty=${cart.qty}" onclick="this.click(function(e)){if('${cart.qty}'==1){alert('더 이상 값을 줄일수 없습니다.'; e.preventDefault();)}"><img  src="images/down.jpg" id="downImage" border="0" /></a>
						
						<%-- 방법-2 : 자바스크립트--%>
						<a href="javascript:checkQtydownID(${cart.id},${cart.qty})"><img  src="images/down.jpg" id="downImage" border="0" /></a>
					<%-- kind 로 개상품 구분 --%>	
						<%-- 방법-1 : 링크 get방식(qty 전달이유? 전달받은 쪽에서 qty 값이 1이면 감소여부 판단)--%>
						<a href="dogCartQtydown.dog?kind=${cart.kind}&qty=${cart.qty}"><img  src="images/down.jpg" id="downImage" border="0" /></a>
						
						<%-- 방법-2 : 자바스크립트--%>
						<a href="javascript:checkQtydownKind('${cart.kind}',${cart.qty})"><img  src="images/down.jpg" id="downImage" border="0" /></a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" align="center"> 총금액 : ${requestScope.totalMoney}원</td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="submit" value="삭제" formaction="dogCartRemove.dog"/>
				<input type="submit" value="주문" formaction="dogOrder.dog"/></td>
			</tr>

		</table>
	</form>
	<c:if test="${startMoney !=null and endMoney != null and empty searchCartList }">
		<%
			out.println("<script>");
			out.println("alert('장바구니에 해당 검색 사항은 없습니다.');");
			out.println("history.back();");//out.println("history.go(-1)");
			out.println("</script>");
		%>
		</c:if>
	<c:if test="${startMoney ==null and endMoney == nul and cartList == null }">
		<section class="div_empty"> 장바구니가 비어있습니다.</section>
		<nav id ="commandList">
		<a href="dpgList.dog">쇼핑계속하기</a>
		</nav>
	</c:if >
	<c:if test="${cartList ==null }">
		<section class="div_empty">장바구니가 비어있습니다</section>
	</c:if>
	<c:if test="${empty cartList }">
	<section class="div_empty">장바구니가 비어있습니다</section>
	</c:if>
	
	<nav id="commandList">
		<a href="dogList.dog">쇼핑 계속하기</a>
	</nav>
	</section>
</body>
</html>