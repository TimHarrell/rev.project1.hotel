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

public class NewProfileServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		String username = req.getParameter("username");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String password = req.getParameter("password");
		Profile profile = ProfileDao.makeProfile(username, firstname, lastname, password);
		if(profile != null) {
			resp.sendRedirect("login.html");
		} else {
			resp.sendRedirect("createprofile.html");
		}
	}
}
