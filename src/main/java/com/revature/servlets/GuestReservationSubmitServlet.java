package com.revature.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Profile;
import com.revature.beans.Reservation;
import com.revature.beans.Room;
import com.revature.dao.ReservationsDao;

/**
 * Servlet implementation class GuestReservationSubmitServlet
 */
public class GuestReservationSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
			
			try {
				String date = req.getParameter("date");
				HttpSession session = req.getSession();
				Profile profile = (Profile) session.getAttribute("profile");
				
				if(req.getParameter("roomNumber") == null && date != null) {
					java.sql.Date datein = java.sql.Date.valueOf(date);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date sqldate;
					sqldate = sdf.parse(date);
					datein = new java.sql.Date(sqldate.getTime());
					session.setAttribute("date", datein);
					resp.setContentType("text/Html");
					resp.getWriter().write(submit(datein));
				}
				else {
					java.sql.Date datein = (java.sql.Date) session.getAttribute("date");
					int roomNumber = Integer.parseInt(req.getParameter("roomNumber"));
					ReservationsDao.addToPendingReservations(profile.getUserId(), datein, roomNumber );
					resp.sendRedirect("GuestConnectedServlet");
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	private String submit(java.sql.Date date) {
		StringBuilder buttons = new StringBuilder();
		buttons.append(
				"<form action='GuestReservationHandlerServlet' method='post'>\r\n" + 
				"				<button type='submit' class='subnavbarbutton' name='input' value='pending'>Pending</button>\r\n" + 
				"				<button type='submit' class='subnavbarbutton' name='input' value='submit'>Submit</button>\r\n" + 
				"				</form>");
		
		ArrayList<Reservation> reservations = ReservationsDao.getAllReservationsByDate(date);
		ArrayList<Room> reserv = new ArrayList<>();
		if(reservations != null) {
			for(Reservation a : reservations) {
				reserv.add(new Room(a.getRoomNum()));
			}
		}
		ArrayList<Room> rooms = Reservation.Rooms();
		StringBuilder addition = new StringBuilder();
		addition.append("<form action='GuestReservationSubmitServlet' method='post'>" +
				"<table>\r\n" + 
				"					<tr>\r\n" + 
				"						<th>room number</th>\r\n" + 
				"						<th>bed count</th>\r\n" + 
				"						<th>smoking</th>\r\n" +
				"						<th>Available</th>" +	
				"					</tr>\r\n"  
				); 
		
		for(Room a : rooms) {
			addition.append("<tr>" + 
					"<td>" + a.getRoomNumber() + "</td>" + 
					"<td>" + a.getNumBeds() + "</td>" + 
					"<td>" + a.getSmoking() + "</td>"
					);
			if(reserv.contains(a)) {
				addition.append("<td>unavailable</td>");
			}
			else {
				addition.append(""
						+ "<td><button type='submit' name='roomNumber' value='" + a.getRoomNumber() + "'> Reserve</button></td>" );
			}
			
			addition.append("</tr>");
		}
		addition.append("</table></form>");
		
				
		return HtmlBuilder.makeGuestProfileHtml(buttons.toString(), "Submit", addition.toString());
	
	}

}
