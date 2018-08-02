package com.revature.servlets;

import com.revature.beans.Inquiry;
import com.revature.dao.InquiryDao;

public class HtmlBuilder {
	
	public static String makeHostProfileHtml(String addition, String title) {
		StringBuilder html = new StringBuilder();
		html.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<meta charset='ISO-8859-1'>\r\n" + 
				"		 <title>Overlook Hotel</title>\r\n" + 
				"            <meta name='author\" content='tim'>\r\n" + 
				"            <meta name='keywords' content='hotel'>\r\n" + 
				"            <meta name='viewport' content='width=device-width'>\r\n" + 
				"            <link type='text/css' rel='stylesheet' href='css/style.css'>\r\n" + 
				"			 <link type='text/css' rel='stylesheet' href='css/HostTables.css>'" +
				"			 <link type='text/css' rel='stylesheet' href='css/messageformat.css>'" +
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<header>\r\n" + 
				"		\r\n" + 
				"			<div class=\"navbar\">\r\n" + 
				"				<form action=\"HostConnectedServlet\" method=\"get\">\r\n" + 
				"				<button type='submit' id='logout' class='navbarbutton' name='input' value='logout'>Logout</button>\r\n" + 
				"				<button type='submit' id='reservations' class='navbarbutton' name='input' value='reservations'>Reservations</button>\r\n" + 
				"				<button type='submit' id='hostspeak' class='navbarbutton' name='input' value='inquiry'>Inquiries</button>\r\n" + 
				"				<button type='submit' id='dashboard' class='navbarbutton' name='input' value='users'>Users</button>\r\n" + 
				"				<button type='submit' id='profile' class='navbarbutton' name='input' value='profile'>Profile</button>\r\n" + 
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</header>\r\n");
		
		//Changes go here
		html.append("<h1 id='pagetitle'>" + title + "</h1>" +
				"		<div>\r\n");
		
		html.append(addition);
		
		html.append(
				"       </div>\r\n" + 
				"	<script src='js/profile.js'></script>\r\n" + 
				"</body></html>");
		
		return html.toString();
	}
	
	public static String makeGuestProfileHtml( String buttons, String title, String addition) {
		StringBuilder html = new StringBuilder();
		html.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		<meta charset='ISO-8859-1'>\r\n" + 
				"		 <title>Overlook Hotel</title>\r\n" + 
				"            <meta name='author' content='tim'>\r\n" + 
				"            <meta name='keywords' content='hotel'>\r\n" + 
				"            <meta name='viewport' content='width=device-width'>\r\n" + 
				"            <link type='text/css' rel='stylesheet' href='css/style.css'>\r\n" + 
				"			 <link type='text/css' rel='stylesheet' href='css/HostTables.css>'" +
				"			 <link type='text/css' rel='stylesheet' href='css/messageformat.css>'" +
				"			 <link type='text/css' rel='stylesheet' href='css/GuestProfile.css'>" +
				"	</head>\r\n" + 
				"	<body>" +
				"		<header>\r\n" + 
				"		\r\n" + 
				"			<div class='navbar'>\r\n" + 
				"				<form action='ProfileConnectedServlet' method='get'>\r\n" + 
				"				<button type='submit' id='logout' class='navbarbutton' name='input' value='logout'>Logout</button>\r\n" + 
				"				<button type='submit' id='reservations' class='navbarbutton' name='input' value='reservations'>Reservations</button>\r\n" + 
				"				<button type='submit' id='hostspeak' class='navbarbutton' name='input' value='inquiry'>Inquiry</button>\r\n" + 
				"				<button type='submit' id='profile' class='navbarbutton' name='input' value='profile'>Profile</button>\r\n"
				);
				
				html.append(buttons);
				
				html.append(
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</header>\r\n" + 
				"		<div>\r\n"
				);
				html.append("<h1 class='pagetitle'>" + title + "</h1>");
				
				html.append(addition);
				
				html.append(
				"       	\r\n" + 
				"       	\r\n" + 
				"       	</div>\r\n" + 
				"	\r\n" + 
				"	<script src='js/profile.js'></script>\r\n" + 
				"</body></html>");
				
				return html.toString();
	}
	
}
