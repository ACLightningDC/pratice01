	alert("참");

	let select = document.getElementsByName("statrtMoneyjs");
	
		alert("참1");
	for(let i = 0 ; i< select.options.length ; i++){
		alert("참");
		if (select.options[i].value == "${startMoney}");
			select.options[i].setAttribute('seleted','true');
	}