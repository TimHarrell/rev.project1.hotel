package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.revature.beans.Profile;
import com.revature.util.ConnectionUtil;

public class ProfileDao {

	
	/*
	 * needs work
	 * it grabs an account from the database, only checking if it exists
	 */
	public static Profile getProfile(String userId, String passwrd) {
		System.out.println("connecting...");
		PreparedStatement ps = null;
		Profile profile = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		    
			String sql = "SELECT * FROM Accounts WHERE password=" + passwrd;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
				String id = rs.getString("userID");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String password = rs.getString("password");
				
				profile = new Profile(id, firstname, lastname, password);
				
			
			
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return profile;
	}
	
	/*
	 * needs works
	 * checks to see if the userId and the provided password are a match,
	 * otherwise it will return false
	 */
	public static Boolean validateProfileLogin(String userId, String password) {
		
	}
}
