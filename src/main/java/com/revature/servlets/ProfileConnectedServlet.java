package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
			else if(input.equals("reservations")) {
				resp.setContentType("text/HTML");
				
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
		StringBuilder inqs = new StringBuilder();
		ArrayList<Inquiry> list = InquiryDao.getInqbyUserId(profile.getUserId());
		
		mainPage.append("<form><ul>");
		for(Inquiry inq : list) {
			inqs.append("<li><a>"
					+ inq.getId()
					+ "</a></li>");
		}
		mainPage.append(inqs.toString());
		mainPage.append("</ul> </form>");
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Inqueries: view", mainPage.toString());
	}
	
	
}
