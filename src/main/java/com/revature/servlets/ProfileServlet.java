package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Profile;
import com.revature.dao.ProfileDao;

public class ProfileServlet extends HttpServlet  {
	
	/*
	 * handles login attempts
	 * prevent going to a profile page if a profile is invalid
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Profile profile = ProfileDao.getProfile(username, password);
		if(profile != null) {
			if(profile.isHost()) {
				resp.sendRedirect("host.html");
			} 
			else {
				resp.sendRedirect("profile.html");
			}
		}
		else {
			resp.sendRedirect("login.html");
		}
	}
}
