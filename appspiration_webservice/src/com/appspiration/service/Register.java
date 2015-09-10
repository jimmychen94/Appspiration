package com.appspiration.service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/register")
public class Register {
	
	@GET
	@Path("/doregister")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String doRegister(@QueryParam("name") String name, @QueryParam("username") String uname, @QueryParam("password") String pwd){
		String response = "";
		int retCode = registerUser(name, uname, pwd);
		if(retCode == 0){
			response = Utility.constructJSON("register",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("register",false, "You are already registered");
		}else if(retCode == 2){
			response = Utility.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
		}else if(retCode == 3){
			response = Utility.constructJSON("register",false, "Error occured");
		}
		return response;
	}
	
	private int registerUser(String name, String uname, String pwd){
		System.out.println("Inside checkCredentials");
		int result = 3;
		if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
			try {
				if(DBConnection.insertUser(name, uname, pwd)){
					System.out.println("RegisterUser successful");
					result = 0;
				}
			} catch(SQLException sqle){
				System.out.println("RegisterUSer catch sqle");
				if(sqle.getErrorCode() == 1062){
					result = 1;
				} 
				else if(sqle.getErrorCode() == 1064){
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			}
			catch (Exception e) {
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = 3;
		}
			
		return result;
	}
	
}
