package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.DAO.author;

public class authorDTO {
	private DataSource dataSource;

	public authorDTO(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
		public List<?> getAuthor() throws Exception {
		
		List<author> author_list = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from author order by aid";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("aid");
				String authorName = myRs.getString("name");
				
				// create new student object
				author tempStudent = new author(id, authorName);
				
				// add it to the list of students
				author_list.add(tempStudent);				
			}
			
			return author_list;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

		
		public author getAuthor(String theAuthorId) throws Exception {

			author theAuthor = null;
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int authorId;
			
			try {
				// convert student id to int
				authorId = Integer.parseInt(theAuthorId);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to get selected student
				String sql = "select * from author where aid=?";
				
				// create prepared statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, authorId);
				
				// execute statement
				myRs = myStmt.executeQuery();
				
				// retrieve data from result set row
				if (myRs.next()) {
					String firstName = myRs.getString("name");
					int id = myRs.getInt("aid");

					// use the studentId during construction
					theAuthor = new author(id, firstName);
				}
				else {
					throw new Exception("Could not find author id: " + authorId);
				}				
				
				return theAuthor;
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
	
		public void addAuthor(author a) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create sql for insert
				String sql = "insert into author "
						   + "(name, aid) "
						   + "select ?,1+MAX(aid) from author";
				
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

		public void deleteAuthor(String theAuthorId) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// convert student id to int
				int authorId = Integer.parseInt(theAuthorId);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to delete student
				String sql = "delete from author where aid=?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, authorId);
				
				// execute sql statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC code
				close(myConn, myStmt, null);
			}	
		}
		
		public void updateAuthor(author theAuthor) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;

			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create SQL update statement
				String sql = "update author "
							+ "set name=?, aid=? "
							+ "where aid=?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, theAuthor.getAid());
				myStmt.setString(2, theAuthor.getName());
				
				// execute SQL statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
		}
		
		
		
		
	}

