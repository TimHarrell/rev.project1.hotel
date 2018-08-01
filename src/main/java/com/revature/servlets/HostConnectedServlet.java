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
import com.revature.dao.ProfileDao;

/**
 * Servlet implementation class HostConnectedServlet
 */
public class HostConnectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null || !((Profile) session.getAttribute("profile")).isHost()) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			
			if(input == null) {
				resp.sendRedirect("hostProfile.html");
				System.out.println("no input");
			}
			else if(input.equals("logout")) {
				RequestDispatcher rd = req.getRequestDispatcher("LogoutServlet");
				rd.forward(req, resp);
				//resp.sendRedirect("LogoutServlet");
			}
			else if(input.equals("reservations")) {
				
			}
			else if(input.equals("inquiry")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostInquiriesHtml());
				
			}
			else if(input.equals("users")) { // Inquiry selected
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostUserHtml());
			}
			else if(input.equals("profile")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostProfileHtml(currUser));
			}
			
			else {
				System.out.println("sopmething went wrong with seleection in ProfileConnection Servlet");
			}
		}
	}

	private String makeHostInquiriesHtml() {
		StringBuilder html = new StringBuilder();
		html.append(
				"<!DOCTYPE html>\r\n" + 
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
				"            <link type=\"text/css\" rel=\"stylesheet\" href=\"css/hostUsers.css\">\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<Header>\r\n" + 
				"		\r\n" + 
				"			<div class=\"navbar\">\r\n" + 
				"				<form action=\"HostConnectedServlet\">\r\n" + 
				"				<form action=\"HostConnectedServlet\" method=\"get\">\r\n" + 
				"				<button type=\"submit\" id=\"logout\" class=\"navbarbutton\" name=\"input\" value=\"logout\">Logout</button>\r\n" + 
				"				<button type=\"submit\" id=\"reservations\" class=\"navbarbutton\" name=\"input\" value=\"reservations\">Reservations</button>\r\n" + 
				"				<button type=\"submit\" id=\"hostspeak\" class=\"navbarbutton\" name=\"input\" value=\"inquiry\">Inquiries</button>\r\n" + 
				"				<button type=\"submit\" id=\"dashboard\" class=\"navbarbutton\" name=\"input\" value=\"users\">Users</button>\r\n" + 
				"				<button type=\"submit\" id=\"profile\" class=\"navbarbutton\" name=\"input\" value=\"profile\">Profile</button>\r\n" + 
				"				</form>" +
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"			<h1 id=\"pagetitle\">Inquiries</h1>" +
				"			<div class=\"body\">" +
				""
				);
		
		html.append(
				"<table>"
				);
		
		ArrayList<Inquiry> list = InquiryDao.getActiveInquiries();
		
		html.append("<tr>" +
				"<th>Inquiry ID</th>\r\n" + 
				"<th>User ID</th>\r\n" + 
				"<th>Topic</th>\r\n" + 
				"<th>Mark Resolved</th>" +
				"</tr>");
		for(Inquiry inq : list) {
			html.append("<tr>" +
					"<td>" + inq.getId() + "</td>" +
					"<td>" + inq.getUserId() + "</td>" +
					"<td>" + inq.getTopic() + "</td>" +
					"<td>" + "<button>resolve</button>" + "</td>" +
					"</tr>");
		}
		
		html.append("</table>");
		html.append("</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>");
		
		return html.toString();
	}
	
	private String makeHostUserHtml() {
		StringBuilder html = new StringBuilder();
		html.append(
				"<!DOCTYPE html>\r\n" + 
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
				"            <link type=\"text/css\" rel=\"stylesheet\" href=\"css/hostUsers.css\">\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<Header>\r\n" + 
				"		\r\n" + 
				"			<div class=\"navbar\">\r\n" + 
				"				<form action=\"HostConnectedServlet\">\r\n" + 
				"				<form action=\"HostConnectedServlet\" method=\"get\">\r\n" + 
				"				<button type=\"submit\" id=\"logout\" class=\"navbarbutton\" name=\"input\" value=\"logout\">Logout</button>\r\n" + 
				"				<button type=\"submit\" id=\"reservations\" class=\"navbarbutton\" name=\"input\" value=\"reservations\">Reservations</button>\r\n" + 
				"				<button type=\"submit\" id=\"hostspeak\" class=\"navbarbutton\" name=\"input\" value=\"inquiry\">Inquiries</button>\r\n" + 
				"				<button type=\"submit\" id=\"dashboard\" class=\"navbarbutton\" name=\"input\" value=\"users\">Users</button>\r\n" + 
				"				<button type=\"submit\" id=\"profile\" class=\"navbarbutton\" name=\"input\" value=\"profile\">Profile</button>\r\n" + 
				"				</form>" +
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"			<h1 id=\"pagetitle\">Users</h1>" +
				"			<div class=\"body\">" +
				""
				);
		
		html.append(
				"<table>"
				);
		
		ArrayList<Profile> list = ProfileDao.getAllProfiles();
		
		html.append("<tr>" +
				"<th>User Id</th>\r\n" + 
				"<th>First Name</th>\r\n" + 
				"<th>Last Name</th>\r\n" + 
				"<th>Password</th>" + 
				"</tr>");
		for(Profile prof : list) {
			html.append("<tr>" +
					"<td>" + prof.getUserId() + "</td>" +
					"<td>" + prof.getFirstName() + "</td>" +
					"<td>" + prof.getLastName() + "</td>" +
					"<td>" + prof.getPassword() + "</td>" +
					"</tr>");
		}
		
		html.append("</table>");
		html.append("</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>");
		
		return html.toString();
	}
	
	private String makeHostProfileHtml(Profile curr) {
		StringBuilder html = new StringBuilder();
		html.append(
				"<!DOCTYPE html>\r\n" + 
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
				"            <link type=\"text/css\" rel=\"stylesheet\" href=\"css/hostUsers.css\">\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<Header>\r\n" + 
				"		\r\n" + 
				"			<div class=\"navbar\">\r\n" + 
				"				<form action=\"HostConnectedServlet\">\r\n" + 
				"				<form action=\"HostConnectedServlet\" method=\"get\">\r\n" + 
				"				<button type=\"submit\" id=\"logout\" class=\"navbarbutton\" name=\"input\" value=\"logout\">Logout</button>\r\n" + 
				"				<button type=\"submit\" id=\"reservations\" class=\"navbarbutton\" name=\"input\" value=\"reservations\">Reservations</button>\r\n" + 
				"				<button type=\"submit\" id=\"hostspeak\" class=\"navbarbutton\" name=\"input\" value=\"inquiry\">Inquiries</button>\r\n" + 
				"				<button type=\"submit\" id=\"dashboard\" class=\"navbarbutton\" name=\"input\" value=\"users\">Users</button>\r\n" + 
				"				<button type=\"submit\" id=\"profile\" class=\"navbarbutton\" name=\"input\" value=\"profile\">Profile</button>\r\n" + 
				"				</form>" +
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"			<h1 id=\"pagetitle\">Profile</h1>" +
				"			<div class=\"body\">" +
				""
				);
		
		html.append(
				"<table>"
				);
		
		html.append("<tr>" +
				"<th>User Id</th>\r\n" + 
				"<th>First Name</th>\r\n" + 
				"<th>Last Name</th>\r\n" + 
				"<th>Password</th>" + 
				"</tr>");
		html.append("<tr>" +
					"<td>" + curr.getUserId() + "</td>" +
					"<td>" + curr.getFirstName() + "</td>" +
					"<td>" + curr.getLastName() + "</td>" +
					"<td>" + curr.getPassword() + "</td>" +
					"</tr>");
		
		
		html.append("</table>");
		html.append("</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>");
		
		return html.toString();
	}
	private String makeInquiryHostHtml() {
		return null;
	}
}
