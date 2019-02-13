package com.abhay.project.webservice.model;

import java.sql.Date;

public class Comment {
	private int userID; //this comment is posted by which user
	private Date date; // date and time on which it was posted
	private String comment; // the comment message
	
	
	public int getUserID() {
		return userID;
	}
	public Date getDate() {
		return date;
	}
	public String getComment() {
		return comment;
	}
	
	
	public Comment(int userID, String comment) {
		super();
		this.userID = userID;
		this.date = new java.sql.Date(new java.util.Date().getTime());
		this.comment = comment;
	}
	
}
