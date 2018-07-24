
	var add = document.getElementById("dropdownaccount");
	
	add.onclick = function() {
		if(add.innerHTML == "account") {
			add.innerHTML = "choose";
			
		}
		else {
			add.innerHTML = "account";
		}
		
		add.classList.toggle("show");
	}
	
	
	
	




	