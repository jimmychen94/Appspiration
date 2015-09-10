package com.appspiration.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;

public class DBConnection {

	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}
	
	public static boolean checkLogin(String uname, String pwd) throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM user WHERE username = '" + uname+ "' AND password=" + "'" + pwd + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	}

	public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
					+ uname + "','" + pwd + "')";
			int records = stmt.executeUpdate(query);
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}

	public static boolean insertThread(String op, String title, String content, String category) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into thread(op, title, content, category) values('"+op+ "','"+ title + "','" + content + "','" + category + "')";
			System.out.println(query);
			int records = stmt.executeUpdate(query);
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;	
	}

	public static boolean removeThread(String op, String title) throws SQLException, Exception {
		boolean deleteStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "DELETE FROM thread WHERE op='" +op+ "' AND title='" +title+"'";
			System.out.println(query);
			int records = stmt.executeUpdate(query);
			if (records > 0) {
				deleteStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return deleteStatus;	
	}
	
	public static boolean changeThread(String title, String username, String newTitle, String newContent, String newCategory)throws SQLException, Exception {
		boolean editStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE thread SET title='"+newTitle+"', content='"+newContent+"', category='"+newCategory+"' WHERE title='" +title+"' AND op='" +username+"'";
			System.out.println(query);
			int records = stmt.executeUpdate(query);
			if (records > 0) {
				editStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return editStatus;	
	}

	public static boolean insertVote(String voter, String thread, String op) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into voters(name, thread_voted_title, thread_voted_op) values('"+voter+ "','"+thread+ "','" + op + "')";
			System.out.println(query);
			int records = stmt.executeUpdate(query);
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;	
	}

	public static boolean deleteVote(String voter, String thread, String op) throws SQLException, Exception {
		boolean deleteStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "DELETE FROM voters WHERE name='" +voter+ "' AND thread_voted_title='" +thread+"' AND thread_voted_op='" +op+"'";
			System.out.println(query);
			int records = stmt.executeUpdate(query);
			if (records > 0) {
				deleteStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return deleteStatus;	
	}

	public static String getAllThreads() throws SQLException, Exception {
		Connection dbConn = null;
		String jsonThreads = "";
		JSONArray ja = new JSONArray();
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM thread";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getDate("date"));
				String category = rs.getString("category");
				String op = rs.getString("op");
				int votes = rs.getInt("votes");
				System.out.println("Title: "+title+"  OP: "+op);
				ja.put(Utility.constructJSONThread(title, content, time, category, op, votes));
			}
			jsonThreads = Utility.constructJSON(ja);
			rs.close();
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return jsonThreads;	
	}

	// gathers all threads in an array to update vote count
	public static void updateThreadVotes() throws SQLException, Exception {
		Connection dbConn = null;
		ArrayList<Triple> threadList = new ArrayList<Triple>();
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM thread";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String title = rs.getString("title");				
				String op = rs.getString("op");
				threadList.add(new Triple(title, op, 0));
			}
			rs.close();
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		countVotes(threadList);
	}

	// counts the number of votes for each thread
	private static void countVotes(ArrayList<Triple> threadList) throws SQLException, Exception {
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			for (Triple p: threadList) {
				String query = "SELECT COUNT(DISTINCT name) FROM voters WHERE thread_voted_title='" +p.getTitle()+"' AND thread_voted_op='" +p.getOp()+"'";
				ResultSet rs = stmt.executeQuery(query);
				if (rs.next()) {
					int count = rs.getInt(1);
					p.setVotes(count);
				} else {
					rs.close();
				}
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		updateVotes(threadList);
	}

	// update the number of votes for each thread in the database
	private static void updateVotes(ArrayList<Triple> threadList) throws SQLException, Exception {
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			for (Triple p: threadList) {
				String query = "UPDATE thread SET votes='"+p.getVotes()+"' WHERE title='" +p.getTitle()+"' AND op='" +p.getOp()+"'";
				stmt.executeUpdate(query);
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
	}
	

}
