/**
 * 
 */

 function check(){
		
		const regPass = /^[a-zA-Z0-9]{8,12}$/;
		
		//변수 사용해서 코딩하면 효율적 : 길이차이가 많이 날때 사용
		//let pre_password = document.f.pre_password;
		
		
		if(!document.f.pre_password.value.trim()){
			alert("비밀번호를 입력하세요");
			document.f.pre_password.focus();
			return false;
		}else if (!regPass.test(document.f.pre_password.value.trim())){
			alert("비밀번호는 8~12 자의 영어 대소문자와 숫자로만 입력가능합니다.");
			document.f.pre_password.select();
			return false;
		}else
		if(!document.f.new_password.value.trim()){
			alert("비밀번호를 입력하세요");
			document.f.new_password.focus();
			return false;
		}else if (!regPass.test(document.f.new_password.value.trim())){
			alert("비밀번호는 8~12 자의 영어 대소문자와 숫자로만 입력가능합니다.");
			document.f.new_password.select();
			return false;
		}else
		if(!document.f.passwordCheck.value.trim()){
			alert("비밀번호를 입력하세요");
			document.f.passwordCheck.focus();
			return false;
		}else if (!regPass.test(document.f.passwordCheck.value.trim())){
			alert("비밀번호는 8~12 자의 영어 대소문자와 숫자로만 입력가능합니다.");
			document.f.passwordCheck.select();
			return false;
		}else
			
		if(document.f.new_password.value.trim() == document.f.pre_password.value.trim()){//이전 비밀번호 '와 ' 새 비밀번호'가 일치하는지 
			alert("이전 비밀번호와 일치합니다");
			document.f.pre_password.select();
			return false;			
		}else 
			
		if(document.f.new_password.value.trim() == document.f.passwordCheck.value.trim()){//새 비밀번호 확인 - 이전 비밀번호가 새 비밀번호와 일치하는지 
			
		}else{
			alert("비밀번호가 일치하지 않습니다");
			document.f.new_password.select();
			return false;
			
		}
		
		//위의조건이 거짓이면 
		documnet.f.submit();
		
	}