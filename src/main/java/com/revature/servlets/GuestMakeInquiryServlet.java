package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Profile;
import com.revature.dao.InquiryDao;

/**
 * Servlet implementation class GuestMakeInquiryServlet
 */
public class GuestMakeInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			if(input.equals("Submit Inquiry")) {
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
}
