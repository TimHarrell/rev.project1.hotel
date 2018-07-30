package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Inquiry;
import com.revature.util.ConnectionUtil;

public class InquiryDao {
	
	public static Inquiry makeInquiry(String topic, String userId ) {
		System.out.println("connecting...");
		Inquiry inq = new Inquiry();
		inq.setTopic(topic);
		inq.setUserId(userId);
		inq.setActive(true);
		
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			// add the inquiry to the main inquiry table
			String sql = "INSERT INTO INQUIRIES "
					+ "(inqId, active, userId, topic) "
					+ "VALUES (" 
					+ 1 + ", " // replace with sequence later, inquiry number will increment
					+ 1 + ", " 
					+ inq.getUserId() + ", " 
					+ inq.getTopic() + ")"; 
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			
			// make a new table, that holds all of the messages
				// messages are added as they respond
			sql = "CREATE TABLE INQUERY" + 1 + " (" // replace 1 with inqId
					+ "messageNumber INTEGER," // message number should increment
					+ "message VARCHAR2(1000), "
					+ "userId VARCHAR2(20), "
					+ "CONSTRAINT PK_messageNumber PRIMARY KEY (messageNumber)"
					+ " );";
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			System.out.println("SQL issue");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return inq;
		
	}
	
	public static Inquiry getInquiryById(Integer inqId) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		Inquiry inq = new Inquiry();
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM INQUIRES WHERE userId='" + inqId + "'";
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
			System.out.println("SQL issue");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
