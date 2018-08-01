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
public class HostInquiryHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// handle resolve
		String inputReso = req.getParameter("inqIdResolve");
		String inputResp = req.getParameter("inqIdRespond");
		Integer inqId;
		if(inputReso != null) { // if resolving a conflict
			inqId = Integer.parseInt(inputReso);
			resp.sendRedirect("HostConnectedServlet");
			InquiryDao.setInqInvalidById(inqId);
		}
		else if(inputResp != null){ // if a respond button was clicked
			inqId = Integer.parseInt(inputResp);
			
			resp.sendRedirect("HostConnectedServlet");
		}
		else { // if both are null, which will never happen
			resp.sendRedirect("HostConnectedServlet");
		}
		
		resp.sendRedirect("HostConnectedServlet");
		
		//RequestDispatcher rd = req.getRequestDispatcher("HostConnectedServlet");
		//rd.forward(req, resp);
	}

}
