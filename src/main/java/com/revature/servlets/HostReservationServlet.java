package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.PendingReservation;
import com.revature.beans.Reservation;
import com.revature.beans.Room;
import com.revature.dao.ReservationsDao;

/**
 * Servlet implementation class HostReservationServlet
 */
public class HostReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String approve = req.getParameter("approve");
		String deny = req.getParameter("deny");
		
		if(approve != null) {
			int tn = Integer.parseInt(approve);
			ReservationsDao.approveReservationByTN(tn);
			resp.sendRedirect("HostConnectServlet");
		}
		else if(deny != null) {
			int tn = Integer.parseInt(deny);
			ReservationsDao.denyReservationByTN(tn);
			resp.sendRedirect("HostConnectServlet");
		}
		else {
			resp.setContentType("text/html");
			resp.getWriter().write(displayAll());
		}
	}
	
	private String displayAll() {

		ArrayList<PendingReservation> reservations = ReservationsDao.getAllPendingReservations();
		StringBuilder addition = new StringBuilder();
		if(reservations == null) {
			addition.append("no pending reservations");
		}
		else {
			
			addition.append(
					"<form action='HostReservationServlet' method='post'>" +
					"<table>\r\n" + 
					"					<tr>\r\n" + 
					"						<th>id</th>\r\n" + 
					"						<th>date</th>\r\n" + 
					"						<th>room number</th>\r\n" +
					"						<th>user id</th>" +	
					"					</tr>\r\n"  
					); 
			
			for(PendingReservation a : reservations) {
				addition.append("<tr>" + 
						"<td>" + a.getTransactionNumber() + "</td>" + 
						"<td>" + a.getDate() + "</td>" + 
						"<td>" + a.getRoomNumber() + "</td>" +
						"<td>" + a.getUserId() + "</td>"
						);
					
				addition.append(
							"<td><button type='submit' name='approve' value='" + a.getTransactionNumber() + "'>approve</button></td>" +
							"<td><button type='submit' name='deny' value='" + a.getTransactionNumber() + "'>deny</button></td>" );
				addition.append("</tr>");
			}
				
			addition.append("</table></form>");
		}
				
		return HtmlBuilder.makeHostProfileHtml("Submit", addition.toString());
	
	}
}
