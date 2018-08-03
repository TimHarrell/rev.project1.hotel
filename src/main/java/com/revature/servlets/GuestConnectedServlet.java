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

/**
 * Servlet implementation class GuestConnectedServlet
 */
public class GuestConnectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			
			if(input == null) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeGuestDashBoardHtml());
				System.out.println("no input");
			}
			else if(input.equals("logout")) {
				RequestDispatcher rd = req.getRequestDispatcher("LogoutServlet");
				rd.forward(req, resp);
				//resp.sendRedirect("LogoutServlet");
			}
			else if(input.equals("reservations")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeReservationGuestHtml());
				
			}
			else if(input.equals("inquiry")) { // Inquiry selected
					//resp.sendRedirect("hostInquiry.html");
					resp.setContentType("text/HTML");
					resp.getWriter().write(makeInquiryGuestHtml());
			}
			else if(input.equals("makeinq")) { // inquiry sub select make
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeInquiryGuestMakeHtml());
			}
			else if(input.equals("viewinq")) { // inquiry sub select view
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeInquiryGuestViewHtml(currUser));
			}
			else if(input.equals("profile")) {
				
			}
			else if(input.equals("pending")) {
				
			}
			else if(input.equals("submit")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeGuestReservationSubmit());
			}
			else {
				System.out.println("sopmething went wrong with seleection in ProfileConnection Servlet");
			}
		}
	}

	

	private String makeInquiryGuestHtml() {
		// buttons
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='makeinq'>make</button>" +
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='viewinq'>view</button>"
				);
		// main page
		StringBuilder mainPage = new StringBuilder();
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Inquires", mainPage.toString());
	}
	
	private String makeInquiryGuestMakeHtml() {
		// buttons
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='makeinq'>make</button>" +
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='viewinq'>view</button>"
				);
		
		// main page
		StringBuilder mainPage = new StringBuilder();
		
		mainPage.append("<div align='center'>\r\n" + 
				"				<form action='GuestMakeInquiryServlet' method='post'>\r\n" + 
				"					topic:\r\n" + 
				"					<br>\r\n" + 
				"					<input style='width:20%;maxlength=20;' id='topic' type='text' name='topic'>\r\n" + 
				"					<br>\r\n" + 
				"					body:\r\n" + 
				"					<br>\r\n" + 
				"					<textarea style='width:20%;height:150px;resize:none' id='body' type='text' name ='body'></textarea>\r\n" + 
				"					<br>\r\n" + 
				"					<input type='submit' name='input' value='Submit Inquiry'>\r\n" + 
				"				</form>\r\n" + 
				"			</div>"
				);
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Inqueries: make", mainPage.toString());
	}
	
	private String makeInquiryGuestViewHtml(Profile profile) {
		// buttons
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='makeinq'>make</button>" +
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='viewinq'>view</button>"
				);
				
		// main page
		StringBuilder mainPage = new StringBuilder();
		mainPage.append("<form action='GuestInquiryHandlerServlet' method='post'>" +
				"<table>"
				);
		
		ArrayList<Inquiry> list = InquiryDao.getInqbyUserId(profile.getUserId());
		mainPage.append("<tr>" +
				"<th>Inquiry ID</th>\r\n" + 
				"<th>Topic</th>\r\n" + 
				"<th>Mark Resolved</th>" +
				"<th>Respond</th>" +
				"</tr>");
		for(Inquiry inq : list) {
			mainPage.append("<tr>" +
					"<td>" + inq.getId() + "</td>" +
					"<td>" + inq.getTopic() + "</td>" +
					"<td>" + "<button" + " type='submit' name='inqIdRespond' value=" + inq.getId() + ">Reply</button>" + "</td>" +
					"</tr>");
		}
		
		mainPage.append(
				"</form>" +
				"</table>");
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Inqueries: view", mainPage.toString());
	}
	
	private String makeGuestDashBoardHtml() {
		return HtmlBuilder.makeGuestProfileHtml("", "DashBoard", "");
	}
	
	private String makeReservationGuestHtml() {
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"		<button type='submit' class='subnavbarbutton' name='input' value='pending'>Pending</button>\r\n" + 
				"		<button type='submit' class='subnavbarbutton' name='input' value='submit'>Submit</button>\r\n"  
				);
		StringBuilder body = new StringBuilder();
		
		
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Reservations", body.toString());
	}
	
	private String makeGuestReservationSubmit() {
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"	<button type='submit' class='subnavbarbutton' name='input' value='pending'>Pending</button>\r\n" + 
				"	<button type='submit' class='subnavbarbutton' name='input' value='submit'>Submit</button>\r\n"	
				);
		
		StringBuilder body = new StringBuilder();
		body.append(
				"	<form action='GuestReservationSubmitServlet' method='post'>\r\n" + 
				"		<input type='date' name='date'>\r\n" + 
				"		<br>\r\n" + 
				"		<input type='submit' name='input' value='Submit'>\r\n" + 
				"	</form>"
						);
		
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Reservations", body.toString());
	}
	
	
}
