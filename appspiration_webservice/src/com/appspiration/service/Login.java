package com.appspiration.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login {
	
	@GET 
	@Path("/dologin")
	@Produces(MediaType.APPLICATION_JSON) 
	public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
		String response = "";
		if(checkCredentials(uname, pwd)){
			response = Utility.constructJSON("login",true);
			System.out.println("login successful");
		}else{
			response = Utility.constructJSON("login", false, "Incorrect Email or Password");
			System.out.println("login failed");
		}
	return response;		
	}
	
	private boolean checkCredentials(String uname, String pwd){
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
			try {
				result = DBConnection.checkLogin(uname, pwd);
			} catch (Exception e) {
				System.out.println("Inside checkCredentials catch");
				result = false;
			}
		}else{
			result = false;
		}
			
		return result;
	}
	
}
