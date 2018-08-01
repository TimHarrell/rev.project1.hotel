package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class hostUsersServlet
 */
public class HostUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			if(input.equals("reservations")) {
				
			}
			else if(input.equals("inquiries")) {
				
			}
			else if(input.equals("users")) {
				
			}
			else if(input.equals("profile")) {
				
			}
			else {
				System.out.println("unrecognized input");
			}
		}
	}
	
	
	
}
