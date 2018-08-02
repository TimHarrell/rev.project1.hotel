package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Message;
import com.revature.dao.InquiryDao;

/**
 * Servlet implementation class GuestInquiryHandlerServlet
 */
public class GuestInquiryHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputResp = req.getParameter("inqIdRespond");
		if(inputResp != null){ // if a respond button was clicked
			Integer inqId = Integer.parseInt(inputResp);
			HttpSession session = req.getSession();
			session.setAttribute("inqId", inqId);
			resp.setContentType("text/HTML");
			resp.getWriter().write(makeGuestResponseHtml(inqId));
		}
		else { // if both are null, which will never happen
			resp.sendRedirect("ProfileConnectedServlet");
		}
	}
	
	private String makeGuestResponseHtml(int inqId) {
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='makeinq'>make</button>" +
				"<button type='submit' id='makeinq' class='subnavbarbutton' name='input' value='viewinq'>view</button>"
				);
		
		StringBuilder addition = new StringBuilder();
		ArrayList<Message> messages = InquiryDao.getConversationById(inqId);
		ArrayList<Message> sorted = new ArrayList<>();
		
		for(int j = 1; j <= messages.size(); j++) {
			for(Message m : messages) { 
				if(m.getMessageNumber() == j) sorted.add(m);
			}
		}
		
		for(Message m : sorted) {
			addition.append(
							"<p style='max-width:300px;color:yellow;word-wrap:break-word;padding-left:30px'>" + m.getSender() + ": " +
							m.getMessage() + "</p>"
					);
		}
		
		addition.append("<form action='GuestResponseServlet' method='post'>\r\n" + 
				"				<input type='text' name='response' maxlength='1000'><br>\r\n" + 
				"       			<br>\r\n" + 
				"       			<button type='submit'>submit response</button>\r\n" + 
				"       		</form>"
				);
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Response", addition.toString() );
	}

}
