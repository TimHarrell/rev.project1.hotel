package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Profile;

/**
 * Servlet implementation class GuestReservationHandlerServlet
 */
public class GuestReservationHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null || !((Profile) session.getAttribute("profile")).isHost()) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			
			if(input == null) {
				
				resp.sendRedirect("GuestConnectedServlet");
			}
			else if(input.equals("submit")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(available());
			}
			else if(input.equals("pending")) {
				
			}
		}
	}

		private String available() {
			StringBuilder buttons = new StringBuilder();
			buttons.append(
					"<form action='GuestReservationHandlerServlet' method='post'>\r\n" + 
					"				<button type='submit' class='subnavbarbutton' name='input' value='pending'>Pending</button>\r\n" + 
					"				<button type='submit' class='subnavbarbutton' name='input' value='submit'>Submit</button>\r\n" + 
					"				</form>");
			
			StringBuilder addition = new StringBuilder();
			addition.append(
					"<form action='GuestReservationAvailableServlet'>\r\n" + 
					"				<input type='date' name='date'>\r\n" + 
					"				<input type='submit' name='Submit Date' value='Submit Date'>\r\n" + 
					"</form>" 
					);
			
					
			return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Available", addition.toString());
		}
		
		private String availableDate() {
			StringBuilder buttons = new StringBuilder();
			buttons.append(
					"<form action='GuestReservationHandlerServlet' method='post'>\r\n" + 
					"				<button type='submit' class='subnavbarbutton' name='input' value='available'>Available</button>\r\n" + 
					"				<button type='submit' class='subnavbarbutton' name='input' value='pending'>Pending</button>\r\n" + 
					"				<button type='submit' class='subnavbarbutton' name='input' value='submit'>Submit</button>\r\n" + 
					"				</form>");
			
			StringBuilder addition = new StringBuilder();
			addition.append("" // display the rooms
					);
			
					
			return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Available", addition.toString());
		
		}
		
		
		
	
}
