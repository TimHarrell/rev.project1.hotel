var loginportal = document.getElementById("movetologin");

loginportal.onclick = function () {
	location.href='login.html';
}

var indexportal = document.getElementById("movetoindex");

indexportal.onclick = function () {
	location.href='index.html';
}

var profileportal = document.getElementById("movetoprofile");

profileportal.onclick = function () {
	/*location.href='profile.html' */
}


function getInfo() {
	changeInner("titles/roominfo.txt", "pagetitle");
	changeInner("hello.txt", "loginbody");
	changeimg("image.txt", "image");
}

function getHome() {
	changeInner("titles/home.txt", "pagetitle");
	changeInner("hello.txt", "loginbody");
}

function getAbout() {
	changeInner("titles/about.txt", "pagetitle");
	changeInner("hello.txt", "loginbody");
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



function changeimg(file, id) {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			document.getElementById(id).setAttribute( "src", xhttp.responseText);
		}
	}
	xhttp.open("GET", file);
	xhttp.send();
}



