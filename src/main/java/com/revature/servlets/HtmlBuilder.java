package com.revature.servlets;

import java.util.ArrayList;

import com.revature.beans.Inquiry;
import com.revature.dao.InquiryDao;

public class HtmlBuilder {
	public static String hostInquiry() {
		StringBuilder page = new StringBuilder();
		page.append("<!DOCTYPE html>\r\n" + 
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
				"		\r\n" + 
				"			<div class=\"navbar\">\r\n" + 
				"				<form action=\"ProfileServlet\">\r\n" + 
				"				<button type=\"submit\"  id=\"logout\" class=\"navbarbutton\" name=\"input\" value=\"logout\">Logout</button>\r\n" + 
				"				<button type=\"submit\"  id=\"dashboard\" class=\"navbarbutton\" name=\"input\" value=\"dashboard\">Dash board</button>\r\n" + 
				"				<button type=\"submit\"  id=\"reservations\" class=\"navbarbutton\" name=\"input\" value=\"reservations\">Reservations</button>\r\n" + 
				"				<button type=\"submit\"  id=\"hostspeak\" class=\"navbarbutton\" name=\"input\" value=\"inqiry\">Inquiry</button>\r\n" + 
				"				<button type=\"submit\"  id=\"profile\" class=\"navbarbutton\" name=\"input\" value=\"profile\">Profile</button>\r\n" + 
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"		<div>\r\n");
				StringBuilder inqs = new StringBuilder();
				ArrayList<Inquiry> list = InquiryDao.getActiveInquiries();
				
				for(Inquiry inq : list) {
					inqs.append("<a>"
							+ inq.getId()
							+ "</a>");
				}
				page.append(inqs.toString());
				page.append(
				"			<h1 id=\"pagetitle\">Inquiries</h1>\r\n" + 
				"       		</div>\r\n" + 
				"       	</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>");
				return page.toString();
	}
}
