package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.DAO.publisher;

public class publisherDTO {

	private DataSource dataSource;

	public publisherDTO(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	

	public List<?> getPublisher() throws Exception {
	
	List<publisher> publisher_list = new ArrayList<>();
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select * from publisher order by pid";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			
			// retrieve data from result set row
			int id = myRs.getInt("pid");
			String publisherName = myRs.getString("name");
			
			// create new student object
			publisher tempStudent = new publisher(id, publisherName);
			
			// add it to the list of students
			publisher_list.add(tempStudent);				
		}
		
		return publisher_list;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		
}

	
	public publisher getPublisher(String thePublisherId) throws Exception {

		publisher thePublisher = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int publisherId;
		
		try {
			// convert student id to int
			publisherId = Integer.parseInt(thePublisherId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from publisher where pid=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, publisherId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("name");
				int id = myRs.getInt("pid");

				// use the studentId during construction
				thePublisher = new publisher(id, firstName);
			}
			else {
				throw new Exception("Could not find publisher id: " + publisherId);
			}				
			
			return thePublisher;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	
	
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   
				}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addPublisher(publisher a) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into publisher "
					   + "(name, pid) "
					   + "select ?,1+MAX(pid) from publisher";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, a.getName());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deletePublisher(String thePublisherId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int publisherId = Integer.parseInt(thePublisherId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from publisher where pid=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, publisherId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
	
	public void updatePublisher(publisher thePublisher) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update publisher "
						+ "set name=?, pid=? "
						+ "where pid=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, thePublisher.getPid());
			myStmt.setString(2, thePublisher.getName());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
		
}