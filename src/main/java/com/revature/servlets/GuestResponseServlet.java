package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Profile;
import com.revature.dao.InquiryDao;

/**
 * Servlet implementation class GuestResponseServlet
 */
public class GuestResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Profile profile = (Profile) session.getAttribute("profile");
		
		
		Integer inqId = (Integer) (session.getAttribute("inqId"));
		String response = req.getParameter("response");
		String currUser = (String) (profile.getUserId());
		InquiryDao.submitMessage(inqId, response, currUser );
		//req.setAttribute("input", "inquiry");
		resp.sendRedirect("GuestConnectedServlet");
	}

}
