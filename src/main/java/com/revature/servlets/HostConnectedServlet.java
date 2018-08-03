package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Inquiry;
import com.revature.beans.PendingReservation;
import com.revature.beans.Profile;
import com.revature.beans.Room;
import com.revature.dao.InquiryDao;
import com.revature.dao.ProfileDao;
import com.revature.dao.ReservationsDao;

/**
 * Servlet implementation class HostConnectedServlet
 */
public class HostConnectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input");
		HttpSession session = req.getSession();
		if(session.getAttribute("profile") == null || !((Profile) session.getAttribute("profile")).isHost()) {
			resp.sendRedirect("login.html");
		}
		else {
			Profile currUser = (Profile) session.getAttribute("profile");
			
			if(input == null) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostDashBoardHtml());
				System.out.println("no input");
			}
			else if(input.equals("logout")) {
				RequestDispatcher rd = req.getRequestDispatcher("LogoutServlet");
				rd.forward(req, resp);
				//resp.sendRedirect("LogoutServlet");
			}
			else if(input.equals("reservations")) {
				resp.getWriter().write(makeHostReservationHtml());
			}
			else if(input.equals("inquiry")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostInquiriesHtml());
				
			}
			else if(input.equals("users")) { // Inquiry selected
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostUserHtml());
			}
			else if(input.equals("profile")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostProfileHtml(currUser));
			}
			else if(input.contentEquals("rooms")) {
				resp.setContentType("text/HTML");
				resp.getWriter().write(makeHostRoomsHtml());
			}
			else {
				System.out.println("sopmething went wrong with seleection in ProfileConnection Servlet");
			}
		}
	}

	private String makeHostInquiriesHtml() {
		StringBuilder addition = new StringBuilder();
		
		addition.append("<form action='HostInquiryHandlerServlet' method='post'>" +
				"<table>"
				);
		
		ArrayList<Inquiry> list = InquiryDao.getActiveInquiries();
		
		addition.append("<tr>" +
				"<th>Inquiry ID</th>\r\n" + 
				"<th>User ID</th>\r\n" + 
				"<th>Topic</th>\r\n" + 
				"<th>Mark Resolved</th>" +
				"<th>Respond</th>" +
				"</tr>");
		for(Inquiry inq : list) {
			addition.append("<tr>" +
					"<td>" + inq.getId() + "</td>" +
					"<td>" + inq.getUserId() + "</td>" +
					"<td>" + inq.getTopic() + "</td>" +
					"<td>" + "<button" + " type='submit' name='inqIdResolve' value=" + inq.getId() + ">resolve</button>" + "</td>" +
					"<td>" + "<button" + " type='submit' name='inqIdRespond' value=" + inq.getId() + ">respond</button>" + "</td>" +
					"</tr>");
		}
		
		addition.append(
				"</form>" +
				"</table>");
		
		
		return HtmlBuilder.makeHostProfileHtml(addition.toString(), "Inquiries");
	}
	
	private String makeHostUserHtml() {
		StringBuilder addition = new StringBuilder();
		
		addition.append("<form action='HostInquiryHandlerServlet' method='post'>" +
				"<table>"
				);
		
		ArrayList<Profile> list = ProfileDao.getAllProfiles();
		
		addition.append("<tr>" +
				"<th>User Id</th>\r\n" + 
				"<th>First Name</th>\r\n" + 
				"<th>Last Name</th>\r\n" + 
				"<th>Password</th>" + 
				"</tr>");
		for(Profile prof : list) {
			addition.append("<tr>" +
					"<td>" + prof.getUserId() + "</td>" +
					"<td>" + prof.getFirstName() + "</td>" +
					"<td>" + prof.getLastName() + "</td>" +
					"<td>" + prof.getPassword() + "</td>" +
					"</tr>");
		}
		
		addition.append("</table></form>");
		
		return HtmlBuilder.makeHostProfileHtml(addition.toString(), "Users") ;
	}
	
	private String makeHostProfileHtml(Profile curr) {
		StringBuilder addition = new StringBuilder();
		
		addition.append(
				"<table>"
				);
		
		addition.append("<tr>" +
				"<th>User Id</th>\r\n" + 
				"<th>First Name</th>\r\n" + 
				"<th>Last Name</th>\r\n" + 
				"<th>Password</th>" + 
				"</tr>");
		addition.append("<tr>" +
					"<td>" + curr.getUserId() + "</td>" +
					"<td>" + curr.getFirstName() + "</td>" +
					"<td>" + curr.getLastName() + "</td>" +
					"<td>" + curr.getPassword() + "</td>" +
					"</tr>");
		
		
		addition.append("</table>");
		addition.append("</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>");
		
		return HtmlBuilder.makeHostProfileHtml(addition.toString(), "Profile");
	}
	
	private String makeHostReservationHtml() {

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
				
		return HtmlBuilder.makeHostProfileHtml( addition.toString(), "Reservations");
	
	}
	
	private String makeHostRoomsHtml() {
		StringBuilder addition = new StringBuilder();
		ArrayList<Room> rooms = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			rooms.add(new Room(i));
		}
		addition.append(
				"<table>"
				);
		
		addition.append("<tr>" +
				"<th>Room Number</th>\r\n" + 
				"<th>Number of Beds</th>\r\n" + 
				"<th>Smoking Allowed</th>\r\n" + 
				"<th>Cost</th>" + 
				"</tr>");
		for(Room room : rooms)
		addition.append("<tr>" +
					"<td>" + room.getRoomNumber() + "</td>" +
					"<td>" + room.getNumBeds() + "</td>" +
					"<td>" + room.getSmoking() + "</td>" +
					"<td>" + "$ " + room.getPrice() + "/night" + "</td>" +
					"</tr>");
		
		
		addition.append("</table>");
		addition.append("</div>\r\n" + 
				"	</body>\r\n" + 
				"	<script src=\"js/profile.js\"></script>\r\n" + 
				"</html>");
		
		return HtmlBuilder.makeHostProfileHtml(addition.toString(), "Rooms");
	}
	
	private String makeInquiryHostHtml() {
		return null;
	}
	private String makeHostDashBoardHtml() {
		return HtmlBuilder.makeHostProfileHtml("", "Dashboard");
	}
}
