package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Profile;

/**
 * Servlet implementation class ProfileConnected
 */
public class ProfileConnectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			else if(input.equals("logout")) {
				RequestDispatcher rd = req.getRequestDispatcher("LogoutServlet");
				rd.forward(req, resp);
				//resp.sendRedirect("LogoutServlet");
			}
			else if(input.equals("dashboard")) {
				
			}
			else if(input.equals("reservations")) {
				
			}
			else if(input.equals("inquiry")) { // Inquiry selected
				if(currUser.isHost()) {
					//resp.sendRedirect("hostInquiry.html");
					resp.setContentType("text/HTML");
					resp.getWriter().write(HtmlBuilder.hostInquiry());
				}
				else {
					resp.sendRedirect("guestInquiry.html");
				}
			}
			else if(input.equals("makeInquiry")) { // inquiry sub select make
				
			}
			else if(input.equals("viewInquiry")) { // inquiry sub select view
				
			}
			else if(input.equals("profile")) {
			}
			
			else {
				System.out.println("sopmething went wrong with seleection in ProfileConnection Servlet");
			}
		}
	}

	private String makeProfileHtml(String buttons, String title, String mainPage) {
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
				"				<button type=\"submit\"  id=\"profile\" class=\"navbarbutton\" name=\"input\" value=\"profile\">Profile</button>");
				
				html.append(buttons);
				
				html.append(
				"				</form>" +
				"			</div>\r\n" + 
				"		</Header>\r\n" + 
				"		\r\n" + 
				"		\r\n" +
				"		<div>\r\n" +
				"	<h1 id='pagetitle'>" +
				title +
				"</h1>"
				); 
		
				html.append(title);
				
				html.append(mainPage);
				
				html.append(
				"       	</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>"
				
				);
		return html.toString();
	}
	
	private String makeInquiryGuestHtml() {
		// buttons
		StringBuilder buttons = new StringBuilder();
		
		// main page
		StringBuilder mainPage = new StringBuilder();
		return makeProfileHtml(buttons.toString(), "Inquires", mainPage.toString());
	}
	
	private String makeInquiryGuestMakeHtml() {
		// buttons
		StringBuilder buttons = new StringBuilder();
		
		// main page
		StringBuilder mainPage = new StringBuilder();
		return makeProfileHtml(buttons.toString(), "Inqueries: make", mainPage.toString());
	}
	
	private String makeInquiryHostHtml() {
		return null;
	}
}
