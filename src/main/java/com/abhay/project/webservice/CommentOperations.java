package com.abhay.project.webservice;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.abhay.project.webservice.model.Comment;
import com.abhay.project.webservice.model.DAOs.UserDAO;
import com.abhay.project.webservice.model.DAOs.CommentDAO;


@Path("useroperations")
//all the comment related operations are performed here
public class CommentOperations {

	
	@GET
	@Path("/getcomments")
	@Produces(MediaType.APPLICATION_JSON)
	//gets the lattest added 10 comments
	public ArrayList<Comment> getAllComments(@QueryParam("limit") int limit) {
		UserDAO.initializeCon();
		ArrayList<Comment> commentList = CommentDAO.getComments(limit);
		return commentList;
	}
	
	
	@GET
	@Path("/getusercomment")
	@Produces(MediaType.APPLICATION_JSON)
	//gets user Specific comments
	public ArrayList<Comment> getUserComments(@QueryParam("userID") int userID, @QueryParam("limit") int limit) {
		UserDAO.initializeCon();
		ArrayList<Comment> commentList = CommentDAO.getUserSpecificComments(userID, limit);
		return commentList;
	}
	
	
	@GET
	@Path("/addcomment")
	@Produces(MediaType.APPLICATION_JSON)
	//this method lets user add comment to comment list
	public Comment addUserComments(@QueryParam("userID") int userID, @QueryParam("newComment") String newComment) {
		UserDAO.initializeCon();
		System.out.println("user id :: "+ userID + "  comment ::  "+ newComment);
		Comment comment = new Comment(userID, newComment);
		if(CommentDAO.putComment(comment)==1) {
			System.out.println("this is success");
			return comment;
		}
		System.out.println("something went wrong");
		return null;
	}
	
	
}
