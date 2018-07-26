package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.beans.Profile;
import com.revature.exceptions.InvalidLoginCredentials;
import com.revature.util.ConnectionUtil;

public class ProfileDao {
	/*
	 * needs work
	 * it grabs an account from the database, only checking if it exists
	 */
	public static Profile getProfile(String userIdIn, String passwordIn) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		Profile profile = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM PROFILES WHERE userId='" + userIdIn + "'";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) { // if the result set could not find a row, the account does not exist
				rs.close();
				ps.close();
				System.out.println("could not find account");
				throw new InvalidLoginCredentials();
			}
			
				String storedUserId = rs.getString("userID");
				String storedFirstname = rs.getString("firstname");
				String storedLastname = rs.getString("lastname");
				String storedPassword = rs.getString("password");
			
			rs.close();
			ps.close();
			
			if(!storedUserId.equals(userIdIn)) {
				System.out.println("invalid password");
				throw new InvalidLoginCredentials();
			}
			
			profile = new Profile(storedUserId, storedFirstname, storedLastname, storedPassword);
				
		} catch (InvalidLoginCredentials e) {
			
			return null;
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
		
		return profile;
	}
	
	/*
	 * needs works
	 * checks to see if the userId and the provided password are a match,
	 * otherwise it will return false
	 */
	public static Boolean validateProfileLogin(String userId, String password) {
		return false;
	}
}
