var indexportal = document.getElementById("movetoindex");

indexportal.onclick = function () {
	location.href='index.html';
}

var profileportal = document.getElementById("movetoprofile");

profileportal.onclick = function () {
	/*location.href='profile.html' */
}


function getRoomInfo() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			document.getElementById("loginbody").innerHTML = xhttp.responseText;
		}
	}
	xhttp.open("GET", "hello.txt");
	xhttp.send();
}



