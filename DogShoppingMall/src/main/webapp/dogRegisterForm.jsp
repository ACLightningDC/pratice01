<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#registForm{
	margin:auto;
	width:500px;
	height:600px;
	border:1px solid red;
}

h2{
	text-align:center;
}

table{
	margin: auto;
	width: 450px;
}

.td_left{
	width: 150px;
	background: orange;
}

.td_right{
	width: 300px;
	background: skyblue;
}

#commandList{
	text-align: center;
}

</style>
</head>
<body>
	<a href="dogList.dog">개상품목록보기</a>
	
	<section id="registForm">
		<header><h2>개 상품 등록</h2></header>
		<form action="dogRegist.dog" name="writeForm" method="post" enctype="multipart/form-data">
		<table>
		<!-- id는 DB에서 자동생성되므로 여기는 없어야 함 -->
			<tr>
				<td class="td_left"><b>품종:</b></td>
				<td class="td_right"><input type="text" name="kind" required="required"/> </td>
			</tr>
			<tr>
				<td  class="td_left" ><b>원산지:</b></td>
				<td class="td_right"><input type="text" name="country" required="required"/> </td>
			</tr>
			<tr>
				<td class="td_left" ><b>가격:</b></td>
				<td class="td_right"><input type="text" name="price" required="required"/> </td>
			</tr>
			<tr>
				<td class="td_left" ><b>신장:</b></td>
				<td class="td_right"><input type="text" name="height" /> </td>
			</tr>
			<tr>
				<td class="td_left" ><b>체중:</b></td>
				<td class="td_right"><input type="text" name="weight" /> </td>
			</tr>
			<tr>
				<td class="td_left" ><b>글내용:</b></td>
				<td class="td_right"><textarea name="content" rows="13" cols="40" wrap="soft"></textarea></td>
			</tr>
			<tr>
				<td class="td_left" ><b>상품이미지:</b></td>
				<td class="td_right"><input type="file" name="image" required="required"/> </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type ="submit" value="개상품등록"/>
					<input type ="reset" value="다시작성"/>
					<input type ="button" value="개상품목록보기" onclick="window.location.href='dogList.dog'"/>
				</td>
			</tr>
		</table>
	</form>
</section>
</body>
</html>