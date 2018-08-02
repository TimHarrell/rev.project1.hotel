package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Message;
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
		if(inputReso != null) { // if resolving an inquiry
			Integer inqId = Integer.parseInt(inputReso);
			InquiryDao.setInqInvalidById(inqId);
			HttpSession session = req.getSession();
			session.setAttribute("input", "inquiry");
			resp.sendRedirect("HostConnectedServlet");
			
		}
		else if(inputResp != null){ // if a respond button was clicked
			Integer inqId = Integer.parseInt(inputResp);
			HttpSession session = req.getSession();
			session.setAttribute("inqId", inqId);
			resp.setContentType("text/HTML");
			resp.getWriter().write(makeHostResponseHtml(inqId));
		}
		else { // if both are null, which will never happen
			resp.sendRedirect("HostConnectedServlet");
		}
	}
	
private String makeHostResponseHtml(int inqId) {
		StringBuilder addition = new StringBuilder();
		ArrayList<Message> messages = InquiryDao.getConversationById(inqId);
		for(Message m : messages) {
			addition.append(
							"<p style='max-width:300px;color:yellow;word-wrap:break-word;padding-left:30px'>" + m.getSender() + ": " +
							m.getMessage() + "</p>"
					);
		}
		
		addition.append("<form action='HostResponseServlet' method='post'>\r\n" + 
				"				<input type='text' name='response' maxlength='1000'><br>\r\n" + 
				"       			<br>\r\n" + 
				"       			<input  type='file'\r\n" + 
				"               		id='image' name='image' \r\n" + 
				"               		accept='image/png, image/jpeg' />\r\n" + 
				"       			<button type='submit'>submit response</button>\r\n" + 
				"       		</form>"
				);
		return HtmlBuilder.makeHostProfileHtml(addition.toString(), "Respond to Inquiry ID" + inqId);
	}

}
