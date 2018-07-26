package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String op1 = req.getParameter("userId");
		String op2 = req.getParameter("password");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<h1>Hello World</h1>");
		pw.println(Integer.parseInt(op1) + Integer.parseInt(op2));
		pw.println(req.getParameter("FavoriteColor"));
		pw.close();
	}
}
