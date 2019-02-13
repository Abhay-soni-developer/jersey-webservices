package com.abhay.project.webservice.model;

public class User {

	private String fname;  //users first name
	private String lname;	// users last name
	private double mobile;	//users mobile number
	private String email;	//users email id  
	private int id;	// this is a id generated automatically in database which is
					//unique for each user
	private int age; //user current age at time of joining
	private String Country;  //user is residing in which country
	private Gender gender; //users gender
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public double getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public int getAge() {
		return age;
	}
	public String getCountry() {
		return Country;
	}
	public Gender getGender() {
		return gender;
	}
	public void setMobile(double mobile) {
		this.mobile = mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCountry(String country) {
		Country = country;
	}

	
	public User(String fname, String lname, double mobile, String email, int age, String country, Gender gender) {
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.email = email;
		this.age = age;
		Country = country;
		this.gender = gender;
	}
	
	//this constructor is called when you 
	public User(int id, String fname, String lname, double mobile, String email, int age, String country, Gender gender) {
		this(fname, lname, mobile, email, age, country, gender);
		this.id = id;
	}
	
	
	
	
}
