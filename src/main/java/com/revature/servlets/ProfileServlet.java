package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ProfileDao;

public class ProfileServlet extends HttpServlet  {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(ProfileDao.getProfile(username, password) != null) {
			resp.sendRedirect("profile.html");
		} else {
			resp.sendRedirect("login.html");
		}
	}
}
