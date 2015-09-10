package com.appspiration.service;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/thread")
public class Threads {
	
	@GET
	@Path("/submitthread")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String submitThread(@QueryParam("op") String op, @QueryParam("title") String title, 
			@QueryParam("content") String content, @QueryParam("category") String category){
		String response = "";
		int retCode = checkThread(op, title, content, category);
		if(retCode == 0){
			response = Utility.constructJSON("threadSubmit",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("threadSubmit",false, "You already have a thread with the same title");
		}else if(retCode == 2){
			response = Utility.constructJSON("threadSubmit",false, "Error occured");
		}
		return response;	
	}

	private int checkThread(String op, String title, String content, String category){
		int result = 2;
		if(Utility.isNotNull(title) && Utility.isNotNull(content) && Utility.isNotNull(category)){
			try {
				if(DBConnection.insertThread(op, title, content, category)){
					System.out.println("insert thread successful");
					result = 0;
				}
			} catch(SQLException sqle){
				System.out.println("insert thread catch sqle");
				System.out.println(sqle.getErrorCode());
				if(sqle.getErrorCode() == 1062){
					result = 1;
				} 
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside insert thread catch e ");
				result = 2;
			}
		}else{
			System.out.println("Inside insert thread else");
			result = 2;
		}
		return result;
	}
	
	@GET
	@Path("/deletethread")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String deleteThread(@QueryParam("op") String op, @QueryParam("title") String title){
		String response = "";
		int retCode = checkThread(op, title);
		if(retCode == 0){
			response = Utility.constructJSON("threadDelete",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("threadDelete",false, "Error deleting thread");
		}
		return response;	
	}

	private int checkThread(String op, String title) {
		int result = 1;
			try {
				if(DBConnection.removeThread(op, title)){
					System.out.println("delete thread successful");
					result = 0;
				}
			} catch(SQLException sqle){
				System.out.println("delete thread catch sqle");
				System.out.println(sqle.getErrorCode());
					result = 1;
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside delete thread catch e ");
				result = 1;
			}
		return result;
	}

	@GET 
	@Path("/editthread")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String editThread(@QueryParam("title") String title, @QueryParam("username") String username, @QueryParam("newtitle") String newTitle, 
			@QueryParam("newcontent") String newContent, @QueryParam("newcategory") String newCategory){
		String response = "";
		int retCode = checkThread(title, username, newTitle, newContent, newCategory);
		if(retCode == 0){
			response = Utility.constructJSON("threadEdit",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("threadEdit",false, "You already have a thread with the same title");
		}else if(retCode == 2){
			response = Utility.constructJSON("threadEdit",false, "Error occured");
		}
		return response;	
	}
	
	private int checkThread(String title, String username, String newTitle, String newContent, String newCategory){
		int result = 2;
		if(Utility.isNotNull(newTitle) && Utility.isNotNull(newContent) && Utility.isNotNull(newCategory)){
			try {
				if(DBConnection.changeThread(title, username, newTitle, newContent, newCategory)){
					System.out.println("change thread successful");
					result = 0;
				}
			} catch(SQLException sqle){
				System.out.println("change thread catch sqle");
				System.out.println(sqle.getErrorCode());
				if(sqle.getErrorCode() == 1062){
					result = 1;
				} 
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside change thread catch e ");
				result = 2;
			}
		}else{
			System.out.println("Inside change thread else");
			result = 2;
		}
		return result;
	}
	
	@GET
	@Path("/dovote")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String doVote(@QueryParam("voter") String voter, @QueryParam("thread") String thread, @QueryParam("op") String op){
		String response = "";
		int retCode = voteThread(voter, thread, op);
		if(retCode == 0){
			response = Utility.constructJSON("voteThread",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("voteThread",false, "Error occured, vote did not go through");
		}
		return response;	
	}

	private int voteThread(String voter, String thread, String op){
		int result = 2;
		try {
			if(DBConnection.insertVote(voter, thread, op)){
				System.out.println("vote successful");
				result = 0;
			}
		} catch(SQLException sqle){
			System.out.println("vote thread catch sqle");
			System.out.println("Error code: " +sqle.getErrorCode());
			result = 1;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Inside votethread catch e ");
			result = 1;
		}
		return result;
	}
	
	@GET
	@Path("/unvote")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String unvote(@QueryParam("voter") String voter, @QueryParam("thread") String thread, @QueryParam("op") String op){
		String response = "";
		int retCode = unvoteThread(voter, thread, op);
		if(retCode == 0){
			response = Utility.constructJSON("unvoteThread",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("unvoteThread",false, "Error occured, vote did not go through");
		}
		return response;	
	}
	
	private int unvoteThread(String voter, String thread, String op){
		int result = 2;
		try {
			if(DBConnection.deleteVote(voter, thread, op)){
				System.out.println("unvote successful");
				result = 0;
			}
		} catch(SQLException sqle){
			System.out.println("unvote thread catch sqle");
			System.out.println("Error code: " +sqle.getErrorCode());
			result = 1;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Inside unvotethread catch e ");
			result = 1;
		}
		return result;
	}
	
	@GET
	@Path("/getthreads")  
	@Produces(MediaType.APPLICATION_JSON) 
	public String getThreads(){
		String response = "";
		try {
			response = DBConnection.getAllThreads();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;	
	}
	
	

}
