/**
 * 
 */

function insertMemberSubmit(){
	if(f.custno.value == ""){
		alert("회원번호가 입력되지 않았습니다");
		return f.custno.focus();
	}
	if(f.custname.value == ""){
		alert("회원이름이 입력되지 않았습니다");
		return f.custname.focus();
	}
	if(f.gender[0].checked == false && f.gender[1].checked == false){
		alert("셩별이 선택되지않았습니다");
		return f.custno.focus();
	}
	if(f.phone.value == ""){
		alert("회원전화가 입력되지 않았습니다");
		return f.phone.focus();
	}
	if(f.address.value == ""){
		alert("주소가 입력되지 않았습니다");
		return f.address.focus();
	}
	if(f.grade.value == ""){
		alert("고객등급이 입력되지 않았습니다");
		return f.grade.focus();
	}
	if(f.city.value == ""){
		alert("거주 도시가 입력되지 않았습니다");
		return f.city.focus();
		location.href
	}
	
	f.submit();
}
function insertMemberResetAlert(){
	if(confirm("모든 항목의 정보를 지우시겠습니가 지우면 확인해 주세요")){
		f.reset();
		f.f.custno.focus();
	}
}

function insertCoffeSubmit(){
	if(f.pcode.value == ""){
		alert("상품코드가 입력되지 않았습니다");
		return f.pcode.focus();
	}
	if(f.pname.value == ""){
		alert("상품명이 입력되지 않았습니다");
		return f.pname.focus();
	}
	if(f.pcost.value == ""){
		alert("상풍단가가 입력되지 않았습니다");
		return f.pcost.focus();
	}
	
	f.submit();
}
