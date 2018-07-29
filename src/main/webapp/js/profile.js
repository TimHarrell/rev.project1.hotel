var loginportal = document.getElementById("movetologin");

loginportal.onclick = function () {
	location.href='login.html';
}

function checkUser() {
	if(profile == undefined) {
		alert("login");
		location.href = "login.html"
	}
}
class profile {
	constructor(userId, firstname, lastname) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
}


function logout() {
	location.href = "login.html"
}

function profileinfo() {
	
	changeInner("text/titles/roominfo.txt", "pagetitle");
	changeInner("hello.txt", "loginbody");
	changesrc("text/src/image.txt", "image");
}

function reservations() {
	
	changeInner("text/titles/about.txt", "pagetitle");
	changeInner("text/hello.txt", "loginbody");
}

function hostspeak() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			document.getElementById("b").innerHTML = xhttp.responseText;
		}
	}
	xhttp.open("GET", "rev.project1.hotel/ProfileServlet");
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
