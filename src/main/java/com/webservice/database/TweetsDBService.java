package com.webservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.webservice.model.Tweet;

/**
 * Class which provides methods which can be used to save data into database.
 * JDBC used
 * 
 * @author Marcin
 *
 */
public class TweetsDBService {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/tweets";

	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "postgres";

	public void saveObjectsToDB(List<Tweet> objectsToSave) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			for(Tweet currentTweet : objectsToSave)
			{
				sql = "INSERT INTO tweets(candidate, tweet_time, sentiment) VALUES ("+currentTweet.getCandidate()+", "+currentTweet.getTime()+", "+currentTweet.getSentiment()+");";
				int updatResult = stmt.executeUpdate(sql);
			}
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
	}

	public List<Tweet> getAllObjectsFromDB() {
			
		   List<Tweet> allTweetsFromDB = new ArrayList<Tweet>();
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT candidate, tweet_time, sentiment FROM tweets;";
		      
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		        
		    	  String candidate = rs.getString("candidate");
		    	  String tweet_time = rs.getString("tweet_time");
		    	  Boolean sentiment = rs.getBoolean("sentiment");

		    	  Tweet tweet = new Tweet(candidate, tweet_time, sentiment);
		    	  allTweetsFromDB.add(tweet);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		return allTweetsFromDB;
	}
}
