<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="vo.Dog" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
#viewForm{
	margin:auto;
	width:640px;
	border:1px solid red;
}

h2{
	text-align:center;
}

img{
 	width: 280px;
 	height: 280px;
 	border: none;
}

# content_main{
	height:300px;
}

#content_left{
	width: 300px;
	float:left;
}

#content_right{
	width: 340px;
	float:left;
}

#desc{
	height: 170px;
	background: skyblue;
}

#commandList{
	text-align: center;
}
</style>
<body>

<section id ="viewForm">
	<h2>${dogView.kind }의 상세정보</h2>
	<section id ="content_main">
		<section id="content_left">
		<img alt="${dogView.kind }" src="${pageContext.request.contextPath }/images/${dogView.image}">
		</section>
		<section id="content_right">
		<b>품좀:</b>${dogView.kind}<br>
		<b>가격:</b>${dogView.price}<br>
		<b>신장:</b>${dogView.height}<br>
		<b>체중:</b>${dogView.weight}<br>
		<b>원산지:</b>${dogView.country}<br>
		<section id="desc">
		내용:${dogView.content}
		</section>
		</section>
	</section>
</section>
	<section id="#commandList">
<a href="dogList.dog">쇼핑계속하기</a>
<a href="dogCartAdd.dog?id=${dogView.id}">장바구니에 담기</a>
	</section>




</body>
</html>