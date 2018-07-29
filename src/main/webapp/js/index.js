
function getHome() {
	
	changeInner("text/titles/home.txt", "pagetitle");
	changeInner("text/hello.txt", "loginbody");
}

function getInfo() {
	
	changeInner("text/titles/roominfo.txt", "pagetitle");
	changeInner("hello.txt", "loginbody");
	changesrc("text/src/image.txt", "image");
}

function getAbout() {
	
	changeInner("text/titles/about.txt", "pagetitle");
	changeInner("text/hello.txt", "loginbody");
}

function goToAccount() {
	location.href = "login.html";
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



