package com.abhay.project.webservice;


import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.hk2.api.messaging.MessageReceiver;

import com.abhay.project.webservice.model.Comment;
import com.abhay.project.webservice.model.Gender;
import com.abhay.project.webservice.model.User;
import com.abhay.project.webservice.model.DAOs.UserDAO;

@Path("useroperations")
//all the user related operations are performed here
public class UserOperations {
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	//this method lets user add comment to comment list
	public String test() {
		return "Created By Abhay Soni";
	}
	
	
	//this method is to be called when user is logging in
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(@FormParam("email") String email, @FormParam("pass") String pass) {
		UserDAO.initializeCon();
		User user = UserDAO.getUser(email, pass);
		return user;
	}
	
	
	//this method is letting any new user register itself
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registration(@FormParam("fname") String fname, @FormParam("lname") String lname,
							    @FormParam("mobile") double mobile, @FormParam("email") String email, 
							    @FormParam("age") int age, @FormParam("country") String country, 
							    @FormParam("gender") String gender, @FormParam("pass") String  pass, 
							    @FormParam("repass") String repass) {
		boolean status = false;
		UserDAO.initializeCon();
		System.out.println(fname);
		if(ValidateFields.ComparePassAndRepass(pass, repass)) {
			if(ValidateFields.validatePassword(pass) && ValidateFields.validateEmail(email) &&  ValidateFields.validateAge(age)){
				status = UserDAO.registerUser(fname, lname, gender, email, country, age, mobile, pass);
				
			}else {
			return status;
			}
		}
		return status;
	}
	
	
	//for letting user logout
//	public String logout() {
//		
//		
//		return null;
//	}
	
	
	@GET
	@Path("/upd-mob")
	@Produces(MediaType.APPLICATION_JSON)
	//this method lets user add comment to comment list
	public int updateUserMobile(@QueryParam("userID") int userID, @QueryParam("mobile") double mobile) {
		UserDAO.initializeCon();
		if(UserDAO.updateUserMobile(userID, mobile)==1) {
			return 1;
		}
		return 0;
	}
	
	
	@GET
	@Path("/upd-email")
	@Produces(MediaType.APPLICATION_JSON)
	//this method lets user add comment to comment list
	public int updateUserEmail(@QueryParam("userID") int userID, @QueryParam("email") String email) {
		UserDAO.initializeCon();
		if(ValidateFields.validateEmail(email)) {
			if(UserDAO.updateUserEmail(userID, email)==1) {
				return 1;
			}else {
				return 8;
			}
		}else {
			return 9;
		}
	}
	
	@GET
	@Path("/upd-country")
	@Produces(MediaType.APPLICATION_JSON)
	//this method lets user add comment to comment list
	public int updateUserCountry(@QueryParam("userID") int userID, @QueryParam("country") String country) {
		UserDAO.initializeCon();
			if(UserDAO.updateCountry(userID, country)==1) {
				return 1;
			}else {
				return 18;
			}
		}
	

}
