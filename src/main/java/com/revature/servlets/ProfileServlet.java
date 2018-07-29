package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Profile;
import com.revature.dao.ProfileDao;

public class ProfileServlet extends HttpServlet  {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Profile profile = ProfileDao.getProfile(username, password);
		
		if(profile == null) {
			resp.setContentType("text/html");
			resp.getWriter().write("make account?");
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("profile.html");
			/*PrintWriter pw = resp.getWriter();
			pw.println("<h1>hi</h1>");
			pw.println("<script alert('hi')></script>");
			pw.close();
			*/
			
			rd.forward(req, resp);
		}
		
	}
	/*
	 * handles login attempts
	 * prevent going to a profile page if a profile is invalid
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Profile profile = ProfileDao.getProfile(username, password);
		
		if(profile == null) {
			RequestDispatcher rd = req.getRequestDispatcher("login");
			resp.setContentType("text/HTML");
			resp.getWriter().write("<h1>invalid login</h1>");
			rd.include(req, resp);
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("profile.html");
			rd.forward(req, resp);
		}
		
	}
	
	private String buildLoginHTML(String addition) {
		StringBuilder html = new StringBuilder();
		html.append( 
				"<html>\r\n" + 
				"    <head>\r\n" + 
				"            <title>Overlook Hotel</title>\r\n" + 
				"            <meta name=\"author\" content=\"tim\">\r\n" + 
				"            <meta name=\"keywords\" content=\"hotel\">\r\n" + 
				"            <meta name=\"viewport\" content=\"width=device-width\">\r\n" + 
				"            <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\">\r\n" + 
				"			\r\n" + 
				"    </head>\r\n" + 
				"    <body>\r\n" + 
				"    	\r\n" + 
				"        <Header>\r\n" + 
				"        <div class=\"navbar\">\r\n" + 
				"			<button type=\"button\" onclick=\"getHome()\" id=\"home\" class=\"navbarbutton\">Home</button>\r\n" + 
				"			<button type=\"button\" onclick=\"goToAccount()\" class=\"navbarbutton\">Account</button>\r\n" + 
				"		</div>\r\n" + 
				"			\r\n" + 
				"        </Header>\r\n" + 
				"        \r\n" + 
				"        <div>\r\n" + 
				"        	<h1 id=\"pagetitle\">Login Page</h1>\r\n" + 
				"        	<p id=\"loginbody\">change me</p>\r\n" + 
				"        	<div align=\"center\">\r\n" + 
				"		        <form action=\"login\" method=\"post\">\r\n" + 
				"		        	username:\r\n" + 
				"		        	<br>\r\n" + 
				"		        	<input  type=\"text\" name=\"username\">\r\n" + 
				"		        	<br>\r\n" + 
				"		        	password:\r\n" + 
				"		        	<br>\r\n" + 
				"		        	<input type=\"password\" name=\"password\">\r\n" + 
				"		        	<br>\r\n" + 
				"		        	<input type=\"submit\" value=\"login\" onclick=\"getLoginInfo\">\r\n" + 
				"		        </form>\r\n" + 
				"       		 </div>\r\n" + 
				"       		 <p id=\"b\">sign in</p>\r\n" + 
				"        </div>\r\n" + 
				"    </body>\r\n" + 
				"	<script src=\"js/login.js\"></script>\r\n" + 
				"</html>");
		
		return html.toString();
	}
}
