
function getHome() {
	if(location.href != "index.html") {
		location.href = "index.html";
	}
	changeInner("text/titles/home.txt", "pagetitle");
	changeInner("text/hello.txt", "loginbody");
}

function goToAccount() {
	location.href = "login.html";
}

function getLoginInfo() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			document.getElementById("b").innerHTML = xhttp.responseText;
		}
	}
	
	xhttp.send();
}

function changeInner(file, id) {
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			document.getElementById(id).innerHTML = xhttp.responseText;
		}
	}
	xhttp.open("GET", file);
	xhttp.send();
}

function changesrc(file, id) {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			document.getElementById(id).setAttribute( "src", xhttp.responseText);
		}
	}
	xhttp.open("GET", file);
	xhttp.send();
}


function loginAlert() {
	window.alert('invalid login');
}


$.ajax({
	
});


