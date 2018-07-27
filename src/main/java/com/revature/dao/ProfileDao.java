package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import com.revature.beans.Profile;
import com.revature.exceptions.InvalidLoginCredentials;
import com.revature.util.ConnectionUtil;

public class ProfileDao {
	/*
	 * it grabs an account from the database, only checking if it exists
	 * REMEMBER TO ADD STUFF FOR INVALIDCHARACTERS
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
				String storedPassword = rs.getString("pssword");
				Boolean storedHost;
				if(rs.getInt("hst") == 1) {
					storedHost = true;
				}
				else {
					storedHost = false;
				}
				
			
			rs.close();
			ps.close();
			
			if(!storedUserId.equals(userIdIn)) {
				System.out.println("invalid password");
				throw new InvalidLoginCredentials();
			}
			
			profile = new Profile(storedUserId, storedFirstname, storedLastname, storedPassword, storedHost);
				
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
	 * REMEMBER TO ADD STUFF FOR INVALIDCHARACTERS, make the escape character invalid
	 */
	public static Profile makeProfile(String userId, String firstname, String lastname, String password) {
		CallableStatement cs = null;
		System.out.println("connecting...");
		Profile newProfile; 
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO PROFILES (userID, firstname, lastname, pssword) VALUES (?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, userId);
			cs.setString(2, firstname);
			cs.setString(3, lastname);
			cs.setString(4, password);
			
			Boolean result = cs.execute();
			if (!result) {
				System.out.println("Successful profile submission.");
				newProfile = new Profile(userId, firstname, lastname, password);
			} else {
				System.out.println("Failed");
				newProfile = null;
			}
			
			cs.close();
		} catch(SQLIntegrityConstraintViolationException ex) {
			System.out.println("That username already exists.");
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return newProfile;
	}
}
