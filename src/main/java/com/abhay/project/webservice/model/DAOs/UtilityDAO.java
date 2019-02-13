package com.abhay.project.webservice.model.DAOs;

import com.abhay.project.webservice.model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UtilityDAO {
	
	
	public static User createUser(ResultSet rs) {
		User user = null;
		try {
			if(rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				int id = rs.getInt("id");
				double mobile = rs.getDouble("mobile_num");
				Gender gender = Gender.MALE;
				String country = rs.getString("country");
				user = new User(id, fname, lname, mobile, email, age, country, gender);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " :: " + e.getMessage());
		}
		return user;
	}
	
	public static Comment createComment(ResultSet rs) {
		Comment commentObj = null;
		try {
			int id = rs.getInt("id");
			Date date = rs.getDate("date");
			String comment = rs.getString("comment");
			commentObj = new Comment(id, comment);
		}catch (SQLException e) {
			System.out.println(e.getErrorCode() + " :: " + e.getMessage());
		}
		return commentObj;
	}
	
}
