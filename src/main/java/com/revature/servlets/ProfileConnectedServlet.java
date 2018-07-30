package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			if(input == null) {
				PrintWriter pw = resp.getWriter();
				pw.println(makeProfileHtml(""));
				pw.close();
				System.out.println("no input");
			}
			else if(input.equals("logout")) {
				resp.sendRedirect("LogoutServlet");
			}
			else if(input.equals("dashboard")) {
				PrintWriter pw = resp.getWriter();
				pw.println(makeProfileHtml(""));
				pw.close();
			}
			else if(input.equals("reservations")) {
				PrintWriter pw = resp.getWriter();
				pw.println(makeProfileHtml(""));
				pw.close();
			}
			else if(input.equals("inquiry")) {
				PrintWriter pw = resp.getWriter();
				pw.println(makeProfileHtml(""));
				pw.close();
			}
			else if(input.equals("profile")) {
				PrintWriter pw = resp.getWriter();
				pw.println(makeProfileHtml(""));
				pw.close();
			}
			else {
				System.out.println("sopmething went wrong with seleection in ProfileConnection Servlet");
			}
		}
	}

	private String makeProfileHtml(String addition) {
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
		
				html.append(addition);
				
				html.append(
				"       	</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>"
				
				);
		return html.toString();
	}
}
