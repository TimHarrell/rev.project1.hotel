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
import com.revature.dao.ProfileDao;

public class ProfileServlet extends HttpServlet  {
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Profile profile = ProfileDao.getProfile(username, password);
		HttpSession session = req.getSession();
		session.setAttribute("profile", profile);
		if(session.getAttribute("profile")== null) {
			session.invalidate();
			resp.sendRedirect("login.html");
		}
		else {
			if( ((Profile)session.getAttribute("profile")).isHost())
				resp.sendRedirect("HostConnectedServlet");
			else {
				resp.sendRedirect("GuestConnectedServlet");
			}
		}
		
	}
	
}
