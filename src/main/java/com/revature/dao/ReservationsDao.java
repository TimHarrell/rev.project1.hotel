package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.PendingReservation;
import com.revature.beans.Reservation;
import com.revature.util.ConnectionUtil;

public class ReservationsDao {
	public static ArrayList<PendingReservation> getAllReservations() {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		ArrayList<PendingReservation> reservations = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM PENDING_RESERVATIONS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { // if the result set could not find a row, the account does not exist
				int tn = rs.getInt("transactionNumber");
				String userId = rs.getString("userId");
				Date rd = rs.getDate("reservationDate");
				int rn = rs.getInt("roomNumber");
				
				reservations.add(new PendingReservation(rd, userId, rn, tn));
			}
				
			rs.close();
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
			return reservations;
	}
	
	public static Boolean reservationExists(String userId, Date d, int rm) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM  RESERVATIONS WHERE userId=?"
					+ "AND reservationDate=? AND roomNumber=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,  userId);
			ps.setDate(2, d);
			ps.setInt(3, rm);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) { // if the result set could not find a row, the account does not exist
				rs.close();
				ps.close();
				return true;
			}
				
			rs.close();
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
			return false;
	}
	
	public static PendingReservation getReservationByTN(int tn) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		PendingReservation reservation = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM PENDING_RESERVATIONS WHERE transactionNumber=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tn);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) { // if the result set could not find a row, the trans does not exist
				rs.close();
				ps.close();
				System.out.println("no transaction with that id");
				return null;
				
			}
			
			String userId = rs.getString("userId");
			Date rd = rs.getDate("reservationDate");
			int rn = rs.getInt("roomNumber");
			int trn = rs.getInt("transactionNumber");
			reservation = new PendingReservation(rd, userId, rn, trn);
			
			rs.close();
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
			return reservation;
	}
	public static Boolean addToPendingReservations(String userId, Date d, int rm) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    if(reservationExists(userId, d, rm)) return false;
			String sql = "INSERT INTO PENDING_RESERVATIONS (userId, reservationDate, roomNumber) VALUES (?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setDate(2, d);
			ps.setInt(3, rm);
		
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
			
			return true;
	}
	public static Boolean approveReservation(int tn) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "INSERT INTO RESERVATIONS ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tn);
			ResultSet rs = ps.executeQuery();
			
			
			rs.close();
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
			
			return true;
	}
	
	public static Boolean denyReservationByTN(int tn) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		PendingReservation reservation = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "DELETE FROM PENDING_RESERVATIONS WHERE transactionNumber=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tn);
			
			ps.close();
		}
		catch(SQLException sql) {
			sql.printStackTrace();
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
			
			return true;
	}
}
