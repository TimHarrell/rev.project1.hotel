package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Inquiry;
import com.revature.beans.Profile;
import com.revature.dao.InquiryDao;

public class GuestInquiryServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			if(input == null) {
				resp.sendRedirect("profile.html");
				System.out.println("no input");
			}
			else if(input.equals("makeInquiry")) {
				resp.setContentType("text/html");
				resp.getWriter().write(makeInquiryHtml());
			}
			else if(input.equals("viewInquiry")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(viewInquiryHtml(currUser));
			}
			else if(input.equals("Submit Inquiry")) {
				String topic = req.getParameter("topic");
				String body = req.getParameter("body");
				
				if(InquiryDao.makeInquiry(topic, body, currUser.getUserId()) == null) {
					RequestDispatcher rd = req.getRequestDispatcher("ProfileConnectedServlet");
					rd.forward(req, resp); // if the inquiry already exists
				}
				else {
				RequestDispatcher rd = req.getRequestDispatcher("ProfileConnectedServlet");
				rd.forward(req, resp);
				}
			}
		}
	}
	
	private static String makeInquiryHtml() {
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
				"			<form class='navbar' action =\"GuestInquiry\" method='post'>\r\n" + 
				"				<!-- more changes here --> \r\n" + 
				"				<button type=\"submit\" class=\"subnavbarbutton\" name=\"input\" value=\"makeInquiry\">make</button>\r\n" + 
				"       			<button type=\"submit\" class=\"subnavbarbutton\" name=\"input\" value=\"viewInquiry\">view</button>\r\n" + 
				"			</form>" +
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"		<h1 id=\"pagetitle\">Inquiries</h1>" +
				"		\r\n" + 
				"		\r\n" +
				"		<div>\r\n"
				); 
				
		html.append(
				"<div align=\"center\">\r\n" + 
				"<form action='GuestInquiry' method='post'>\r\n" + 
				"					topic:\r\n" + 
				"					<br>\r\n" + 
				"					<input id='topic' type='text' name='topic'>\r\n" + 
				"					<br>\r\n" + 
				"					body:\r\n" + 
				"					<br>\r\n" + 
				"					<input id ='body' type='text' name ='body'>\r\n" + 
				"					<br>\r\n" + 
				"					<input type='submit' name='input' value='Submit Inquiry'>\r\n" + 
				"				</form>" +
				"		        </form>\r\n" + 
				"       		 </div>"
				);
				
		
				html.append(
				"       	</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>"
				);
		
		return html.toString();
	}
	
	private static String viewInquiryHtml(Profile profile) {
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
					"			<form class='navbar' action =\"GuestInquiry\" method='post'>\r\n" + 
					"				<!-- more changes here --> \r\n" + 
					"				<button type=\"submit\" class=\"subnavbarbutton\" name=\"input\" value=\"makeInquiry\">make</button>\r\n" + 
					"       			<button type=\"submit\" class=\"subnavbarbutton\" name=\"input\" value=\"viewInquiry\">view</button>\r\n" + 
					"			</form>" +
					"			</div>\r\n" + 
					"		</Header>\r\n" + 
					"		\r\n" + 
					"		\r\n" +
					"		<div>\r\n"
					); 
					StringBuilder inqs = new StringBuilder();
					ArrayList<Inquiry> list = InquiryDao.getInqbyUserId(profile.getUserId());
					
					html.append("<form><ul>");
					for(Inquiry inq : list) {
						inqs.append("<li><a>"
								+ inq.getId()
								+ "</a></li>");
					}
					html.append(inqs.toString());
					html.append("</ul> </form>");
					html.append(
					"       	</div>\r\n" + 
					"	</body>\r\n" + 
					"	<script src=\"js/profile.js\"></script>\r\n" + 
					"</html>"
					
					);
			
			return html.toString();
	}
	
	private static String respondHtml(Profile profile) {
		
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
				"			<form class='navbar' action =\"GuestInquiry\" method='post'>\r\n" + 
				"				<!-- more changes here --> \r\n" + 
				"				<button type=\"submit\" class=\"subnavbarbutton\" name=\"input\" value=\"makeInquiry\">make</button>\r\n" + 
				"       			<button type=\"submit\" class=\"subnavbarbutton\" name=\"input\" value=\"viewInquiry\">view</button>\r\n" + 
				"			</form>" +
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
