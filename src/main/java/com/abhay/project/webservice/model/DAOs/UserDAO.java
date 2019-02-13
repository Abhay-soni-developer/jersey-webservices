package com.abhay.project.webservice.model.DAOs;

import com.abhay.project.webservice.model.DAOs.*;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.abhay.project.webservice.model.Comment;
import com.abhay.project.webservice.model.Gender;
import com.abhay.project.webservice.model.User;

public class UserDAO {
	
	private static final String dbHost = "jdbc:mysql://localhost:3306/commentEngine";
	private static final String dbUser = "root";
	private static final String dbPass = "1234";
	private static Connection con = null;
	
	
	public static void initializeCon() {
		if(con==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = (Connection) DriverManager.getConnection(dbHost,dbUser,dbPass);  
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//the method is for getting user data from database
	//null is returned when user is not found
	public static User getUser(String email, String pass) {
//		System.out.println("email :: " + email + " pass :: " + pass );
		User user = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			prepStmt = (PreparedStatement)con.prepareStatement("select * from users where email = ? and pass = ?;");
			prepStmt.setString(1, email);
			prepStmt.setString(2, pass);
			rs = prepStmt.executeQuery();
			user = UtilityDAO.createUser(rs);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " :: " + e.getMessage());
			}
		return user;
	}
	
	//this method is for registering any new user
	public static boolean registerUser(String fname, String lname, String gender, String email, String country, int age, double mobile_num, String pass) {
		PreparedStatement prepStmt = null;
		boolean status = false;
		try {
			prepStmt = (PreparedStatement)con.prepareStatement("INSERT INTO users (fname, lname, gender, email, country, age, mobile_num, pass) values (?, ?, ? ,?, ?, ?, ?, ?);");
			prepStmt.setString(1, fname);
			prepStmt.setString(2, lname);

			if(gender.equalsIgnoreCase("MALE") ) {
				prepStmt.setString(3, "MALE");
			}else{
				prepStmt.setString(3, "FEMALE");
			}
			
			prepStmt.setString(4, email);
			prepStmt.setString(5, country);
			prepStmt.setInt(6, age);
			prepStmt.setDouble(7, mobile_num);
			prepStmt.setString(8, pass);
			status = prepStmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	
	
	
	//this method update specific user email id in database
	public static int updateUserEmail(int userID, String email) {
		PreparedStatement prepStmt = null;
		int rowsEffected = 0;
		try {
			prepStmt = (PreparedStatement)con.prepareStatement("update users set email=? where id=?;");
			prepStmt.setString(1, email);
			prepStmt.setInt(2, userID);
			rowsEffected = prepStmt.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		return rowsEffected;
	}
	
	//this method update user mobile number in database
	public static int updateUserMobile(int userID, double mobile) {
		PreparedStatement prepStmt = null;
		int rowsEffected = 0;
		try {
			prepStmt = (PreparedStatement)con.prepareStatement("update users set mobile_num=? where id=? ");
			prepStmt.setDouble(1, mobile);
			prepStmt.setInt(2, userID);
			rowsEffected = prepStmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode() + " :: " + e.getMessage());
			}
		return rowsEffected;
	}
	
	
	
//	this method is for updating user country
	public static int updateCountry(int userID, String country) {
		PreparedStatement prepStmt = null;
		int rowsEffected = 0;
		try {
			prepStmt = (PreparedStatement)con.prepareStatement("update users set country=? where id=? ");
			prepStmt.setString(1, country);
			prepStmt.setInt(2, userID);
			rowsEffected = prepStmt.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		return rowsEffected;
	}
	
	
}
