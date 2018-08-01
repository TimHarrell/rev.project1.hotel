package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.InquiryDao;

/**
 * Servlet implementation class HostInquiryResolveServlet
 */
public class HostInquiryResolveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// handle resolve
		String input = req.getParameter("inqId");
		Integer inqId = Integer.parseInt(input);
		InquiryDao.setInqInvalidById(inqId);
		resp.sendRedirect("HostConnectedServlet");
		
		//RequestDispatcher rd = req.getRequestDispatcher("HostConnectedServlet");
		//rd.forward(req, resp);
	}

}
