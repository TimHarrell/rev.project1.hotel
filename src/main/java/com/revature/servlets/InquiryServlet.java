package com.revature.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InquiryServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		
	}
	
	private String makeInquiryHtml(String addition) {
		StringBuilder html = new StringBuilder();
		
		html.append( 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		\r\n" + 
				"		<head>\r\n" + 
				"		<meta charset=\"ISO-8859-1\">\r\n" + 
				"		 <title>Overlook Hotel</title>\r\n" + 
				"            <meta name=\"author\" content=\"tim\">\r\n" + 
				"            <meta name=\"keywords\" content=\"hotel\">\r\n" + 
				"            <meta name=\"viewport\" content=\"width=device-width\">\r\n" + 
				"            <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\">\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<Header>\r\n" + 
				"			<div class=\"navbar\">\r\n" +  
				"				<form action=\"ProfileConnected\">\r\n" + 
				"				<button type=\"submit\"  id=\"logout\" class=\"navbarbutton\" name=\"input\" value=\"logout\">Logout</button>\r\n" + 
				"				<button type=\"submit\"  id=\"dashboard\" class=\"navbarbutton\" name=\"input\" value=\"dashboard\">Dash board</button>\r\n" + 
				"				<button type=\"submit\"  id=\"reservations\" class=\"navbarbutton\" name=\"input\" value=\"reservations\">Reservations</button>\r\n" + 
				"				<button type=\"submit\"  id=\"hostspeak\" class=\"navbarbutton\" name=\"input\" value=\"inquiry\">Inquiry</button>\r\n" + 
				"				<button type=\"submit\"  id=\"profile\" class=\"navbarbutton\" name=\"input\" value=\"profile\">Profile</button>" +
				"				</form>" +
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"		\r\n" + 
				"		\r\n" +
				"		<div>\r\n"
				); 
		
				
				html.append(
				"       	</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>"
				
				);
		
		return html.toString();
	}
}
