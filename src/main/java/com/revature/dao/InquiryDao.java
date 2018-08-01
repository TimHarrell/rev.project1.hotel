package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import com.revature.beans.Inquiry;
import com.revature.beans.Message;
import com.revature.exceptions.InvalidLoginCredentials;
import com.revature.util.ConnectionUtil;

public class InquiryDao {
	
	/*
	 * add a new inquiry when the user wants to
	 */
	public static Inquiry makeInquiry(String topic, String body, String userId ) {
		System.out.println("connecting...");
		Inquiry inq = new Inquiry();
		inq.setTopic(topic);
		inq.setUserId(userId);
		inq.setActive(true);
		
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			if(!insertIntoInq(1, inq.getUserId(), inq.getTopic())) {
				throw new SQLSyntaxErrorException();
			};
			Integer inqId = getInqByParts(1, inq.getUserId(), inq.getTopic());
			
			// make a new table, that holds all of the messages
				// messages are added as they respond
			String sql = "CREATE TABLE INQUIRY" + inqId + " ("
					//+ "messageNumber INTEGER, "
					+ "message VARCHAR2(1000), "
					+ "userId VARCHAR2(20) "
					//+ ", CONSTRAINT PK_messageNumber PRIMARY KEY (messageNumber)"
					+ " )";
			
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			ps.close();
			
			submitMessage(inqId, body, userId);
		}
		catch(SQLSyntaxErrorException name) {
			name.printStackTrace();
			System.out.println("already exists");
			return null;
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue makeInquiry");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return inq;
		
	}
	
	/*
	 * get an inquiry by finding primary key inquiryid
	 */
	public static Inquiry getInquiryById(Integer inqId) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		Inquiry inq = new Inquiry();
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM INQUIRES WHERE userId=" + inqId;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			if(!rs.next()) { // if the result set could not find a row, the inquiry
				rs.close();
				ps.close();
				System.out.println("could not find inquiry");
				inq = null;
			}
			else {
				// make sql table based on this
				// gets everything from conversation except the messages
				inq.setId(rs.getInt("inqId"));
				inq.setUserId(rs.getString("userId"));
				inq.setTopic(rs.getString("topic"));
				Integer storedActive = rs.getInt("active");
				if(storedActive == 1) {
					inq.setActive(true);
				}
				else {
					inq.setActive(false);
				}
				
				
			
				rs.close();
				ps.close();
				
				// get messages here
					// make table with message and message number
					// each message has an id and each message after each one is incremented
					sql = " SELECT message FROM " + inq.getId();
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					ArrayList<String> convo = new ArrayList<>();
					int i = 0;
					while(rs.next()) {
						convo.add(rs.getString(i));
						i++;
					}
			}
			return inq;
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue getInqById");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static ArrayList<Inquiry> getActiveInquiries() {
		System.out.println("connecting...");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Inquiry> inqs = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			String sql = "SELECT * FROM INQUIRIES WHERE"
					+ " active= " + 1;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer inqId = rs.getInt("inqId");
				Integer act = rs.getInt("active");
				String usId = rs.getString("userId");
				String top = rs.getString("topic");
				ArrayList<Message> messages = getConversationById(inqId);
				inqs.add(new Inquiry(top, messages, usId, inqId, act));
			}

			rs.close();
			ps.close();
			
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue getActive Inquiries");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
		return inqs;
	}
	/*
	 * create a new row in the INquiry table with the
	 */
	public static Boolean insertIntoInq(int active, String userId, String topic) {
			System.out.println("connecting...");
	
			PreparedStatement ps = null;
			
			try(Connection conn = ConnectionUtil.getConnection()) {
				if(getInqByParts(1, userId, topic) != -1) throw new SQLException();
				// add the inquiry to the main inquiry table
				String sql = "INSERT INTO INQUIRIES (active, userId, topic) VALUES ( ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, 1);
				ps.setString(2, userId);
				ps.setString(3, topic);
				
				ps.execute();
				
			}
			catch(SQLException sql) {
				sql.printStackTrace();
				System.out.println("SQL issue insertIntoInq");
				return false;
			}
			catch(Exception ex) {
				ex.printStackTrace();
				return false;
			}
			
			return true;
		}
	
	public static ArrayList<Inquiry> getInqbyUserId(String userId) {
		System.out.println("connecting...");
		
		ArrayList<Inquiry> inqs = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			String sql = "SELECT * FROM INQUIRIES WHERE active=1 AND userId='" + userId +"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer inqId = rs.getInt("inqId");
				Integer act = rs.getInt("active");
				String usId = rs.getString("userId");
				String top = rs.getString("topic");
				ArrayList<Message> messages = getConversationById(inqId);
				inqs.add(new Inquiry(top, messages, usId, inqId, act));
			}
			
			rs.close();
			ps.close();
			
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue getInqUserById");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
		return inqs;
	}
	
	/*
	 * get an inquiry row by using active, userId, and topid
	 * issue: if all three of these happen to be the same in somehwere else two rows will be grabbed
	 */
	public static int getInqByParts(int active, String userId, String topic) {
		System.out.println("connecting...");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Inquiry inq = null;
		Integer inqId = -1;
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			String sql = "SELECT * FROM INQUIRIES WHERE active=? AND userId=? AND topic=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, userId);
			ps.setString(3, topic);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) { // if the result set could not find a row, the inq does not exist
				rs.close();
				ps.close();
				System.out.println("could not find row");
				throw new SQLException();
			}
			
			Integer act = rs.getInt("active");
			String usId = rs.getString("userId");
			String top = rs.getString("topic");
			inqId = rs.getInt("inqId");
			
			if(rs.next()) throw new SQLException(); // if the inq already exists return -1
			rs.close();
			ps.close();
			
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue getinqbyParts");
			return -1;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		
		
		return inqId;
	}
	
	public static void setInqInvalidById(int inqId) {
		System.out.println("connecting...");
		
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			String sql = "UPDATE INQUIRIES SET" 
					+ " active=" + 0 
					+ " WHERE" 
					+ "inqId =" + inqId;
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue");
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Message submitMessage(int inqId, String body, String userId) {
		System.out.println("connecting...");
		
		Message message = new Message(body, userId);
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
				String sql = "INSERT INTO INQUIRY" + inqId + " (message, userId) VALUES (?, ?)";
				ps = conn.prepareCall(sql);
				ps.setString(1, body);
				ps.setString(2, userId);
				
				ps.execute();
				ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue setInqInvalidById");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return message;
	}
	public static ArrayList<Message> getConversationById(int id) {
		System.out.println("connecting...");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Inquiry inq = null;
		Integer inqId = -1;
		ArrayList<Message> messages = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			String sql = "SELECT * FROM INQUIRY" + id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) { 
				String message = rs.getString("message");
				String user = rs.getString("userId");
				messages.add(new Message(message, user));
			}
			
			rs.close();
			ps.close();
			
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue getConversationById");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
		return messages;
	}
	
}
