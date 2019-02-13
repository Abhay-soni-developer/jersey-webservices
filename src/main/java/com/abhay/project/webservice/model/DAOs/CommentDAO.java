package com.abhay.project.webservice.model.DAOs;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.abhay.project.webservice.model.Comment;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CommentDAO {

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
		
		
	//bring  lattest comments or based on pagination
		public static ArrayList<Comment> getComments(int limit) {
			PreparedStatement prepStmt = null;
			ResultSet rs = null;
			ArrayList<Comment> commentList = new ArrayList<>();
			try {
				prepStmt = (PreparedStatement)con.prepareStatement("select * from commentBox limit ?");
				prepStmt.setInt(1, limit);
				rs = prepStmt.executeQuery();
				while(rs.next()) {
					Comment comment = UtilityDAO.createComment(rs);
					commentList.add(comment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return commentList;
		}
		
		//this method is gonna get specific user comments
		public static ArrayList<Comment> getUserSpecificComments(int userID, int limit) {
			PreparedStatement prepStmt = null;
			ResultSet rs = null;
			ArrayList<Comment> commentList = new ArrayList<>();
			try {
				prepStmt = (PreparedStatement)con.prepareStatement("select * from commentBox where id=? limit ?;");
				prepStmt.setInt(1, userID);
				prepStmt.setInt(2, limit);
				rs = prepStmt.executeQuery();
				int i = 0;
				while(rs.next()) {
					Comment comment = UtilityDAO.createComment(rs);
					commentList.add(comment);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return commentList;
		}
		
		
		//this method adds user comments in database
		public static int putComment(Comment comment) {
			PreparedStatement prepStmt = null;
			int effectedRow = 0;
			try {
				prepStmt = (PreparedStatement)con.prepareStatement("INSERT INTO commentBox (id, comment, date) values (?, ?, ?)");
				prepStmt.setInt(1, comment.getUserID());
				prepStmt.setString(2, comment.getComment());
				prepStmt.setDate(3, comment.getDate());
				effectedRow = prepStmt.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getErrorCode() + " :: " + e.getMessage());
				}
			System.out.println(effectedRow);
			return effectedRow;
		}
		
	
}
