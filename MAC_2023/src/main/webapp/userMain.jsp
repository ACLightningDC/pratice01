<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맥 카페</title>
<meta name="viewport" content="width=device-width , initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<%-- 팝업창 띄우기 --%>
<script type="text/javascript">

//name 으로 저장된 쿠키 읽어오는 함수
function getCookie(name) {
  alert("[getCookie()] " + document.cookie);//테스트위해 추가 : 생성된 쿠키객체가 출력	
  let matches = document.cookie.match(new RegExp(
		    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));  
 
  //정규식에 맞는 값이 있으면 다시 decoding하여 리턴
  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function openPopup(url){
	
	//저장된 쿠키 읽어 오기 -> 팝업창 생성유무 설정 정보 리턴 받아 "N" 이 아니면 팝업창을 뛰움
	var cookieCheck = getCookie("popupYN");
	//console.log(cookieCheck);
	if(cookieCheck != "N"){//[리턴 방법-2]에서 undefined 는 n 이아니므로 참이됨
		alert(cookieCheck);
		window.open(url,'공지사항','width=450,height=750,left=0,top=0');
	}
}





</script>
</head>
<body onload="javascript:openPopup('popUp.html')">
	<jsp:include page="userHeader.jsp"></jsp:include>
	
	<div>
		<section>
			<h3>움직이는 배너나 홍보 이미지</h3>
		</section>
	</div>
	<h3>메인 부분에 올라갈 이미지나 글 입력</h3>
	<a href="<%=request.getContextPath()%>/menuView.kiosk?m_id=1955">
	<img src="<%=request.getContextPath()%>/images/user_img/user_main/1955.png"/>
	</a>
	<a href="<%=request.getContextPath()%>/menuView.kiosk?m_id=Big Mac">
	<img src="<%=request.getContextPath()%>/images/user_img/user_main/Big Mac.png"/>
	</a>
	<jsp:include page="userFooter.jsp"></jsp:include><%--변화가 있는것 --%>
</body>
</html>