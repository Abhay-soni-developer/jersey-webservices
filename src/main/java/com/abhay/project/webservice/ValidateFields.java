package com.abhay.project.webservice;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

public class ValidateFields {
	
	public static boolean validateEmail(String email) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	}
	
	
	public static boolean ComparePassAndRepass(String pass, String repass) {
		System.out.println(pass + "  ::  "+ repass);
		return pass.equals(repass);
	}
	
	public static boolean validateAge(int age) {
		if(age>18 || age<70) {
			return true;
		}
		return false;
	}
	
	public static boolean validatePassword(String pass) {
		return true;
	}
	
	
}
