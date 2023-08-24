<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script >
	function findAddr(){
		//카카오 지도 발생-> 주소 입력 후 [검색]-> 찾는 주소[선택]
		new daum.Postcode({
			oncomplete : function(data) {//[선택]시 입력값 세팅
				console.log(data);
				//alert(data);
				
				document.getElementById("postcode").value = data.zonecode;
				
				let roadAddr = data.roadAddress;//도로명 주소
				let JibunAddr = data.JibunAddress;//도로명 주소
				
				if(roadAddr != ''){//도로명 주소가 있으면 도로명 주소가 등록되고
					document.getElementById("address1").value = roadAddr;
				}else if(jibunAddr != ''){//도로명 주소가 없고 지번주소만 있으면 지번주소가 등록함
					document.getElementById("address1").value = jibunAddr;
				}
				
				document.getElementById("address2").focus();//커서 둠 가이 
				
				
			}
		}).open();
	}
</script>
<form action="userUpdate.usr" method="post" name="f">



<input type="hidden" name="grade" value="NORMAL"/>
	<div style="border: solid 1px black;">
			<h5>당신의 등급은 일반등급인 ${user.grade}입니다.</h5>
			<h5>(※VIp 등급조건 :지난 달 총주문 금액100,000 이상)</h5>
			
			<table  style="width:100%;text-align:center; border: solid 1px black ; margin-left:auto; margin-right:auto">
				<tr>
					<td>
					아이디
					</td>
					<td colspan="2" style="text-align:center; " align="center">
					<div><input name= "id" type="text" id="id" id="id"size="40" value="${user.id}" placeholder="8-12자 영문과 문자조합을 입력하세요" readonly="readonly"/>
					<input type="button" name="idck" id="idck"value="아이디 중복확인" onclick="window.open('idCheck/idCheck.jsp?openInit=true','아이디중복확인','top=10, left=10, width=500, height=300')"/></div>
					
					</td>
				</tr>
				<tr>
<!-- 비번 제외 -->					
				</tr>
				<tr>
					<td>
					이름
					</td>
					<td>
					<div><input name= "name" type="text" placeholder="한글 또는 영문만 입력하세요(특수문자 제외)" value="${user.name}"/></div>
					</td>
				</tr>
				<tr>
					<td>
					이메일
					</td>
					<td>
					<div><input name= "email" type="email" placeholder="(ex)aaa@naver.com" value="${user.email}"/></div>
					</td>
				</tr>
				<tr>
					<td>
					휴대전환
					</td>
					<td>
					<div><input name= "phone" type="text" placeholder="-없이 숫자만 입력하세요" value="${user.phone}"/></div>
					</td>
				</tr>
				<tr>
					<td rowspan="3">
					주소
					</td>
					<td>
					<div>
					<input name= "postcode" id= "postcode" type="text" placeholder="우편번호만입력" c/></div>
					<input name= "postcode"  type="button" value="우편번호 찾기" onclick="findAddr()" value="${addr.postcode}"/>
					</td>
				<tr>
					<td>
					<div><input name= "address1" id="address1" type="text" placeholder="주소" value="${addr.address1}"/></div>
					</td>
				</tr>
				<tr>
					<td>
					<div><input name= "address2" id="address2" type="text" placeholder="상세주소" value="${addr.address2}"/></div>
					</td>
				</tr>
			</table>
			<input type="submit" value="가입하기" onclick="check();return false;"></div>
			<!-- [방법-1] id를 get방식으로 보내어 회원찾아 제거 -->
			<a href="userDelete.usr?id=${user.id}">회원탈퇴</a>
			
			<!-- [방법-1] id를 session에서 u_id로 회원찾아 제거 -->
			<a href="userDelete.usr">회원탈퇴</a>
</form>
	
</body>
</html>