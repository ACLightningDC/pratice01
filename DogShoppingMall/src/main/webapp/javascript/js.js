/**
 * 
 */
function checkAll(theForm){
	alert('checkAll');
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
function checkQtyupId(id){
	window.location.href="dogCartQtyUp.dog?id="+id;
}
//항목 수량 1감소 : 단 ,현재수량이 2이상일 때만 수량 감소 (1일때는 수량감소X)
function checkQtyDownId(id, qty){
	if(qty != 1){
		window.location.href="dogCartQtyUp.dog?id="+id;	
	}
}
//방법-2 : kind로 개상품 구분 
function checkQtyupKind(){
	window.location.href="dogCartQtyUp.dog?kind="+encodeURIcomponent(kind);
}

function checkQtyDownKind(){
	if(qty != 1){
		window.location.href="dogCartQtyUp.dog?kind="+encodeURIcomponent(kind);
	}
}