package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.DAO.login;




public class studentDTO {
	
	private DataSource dataSource;

	public studentDTO(DataSource theDataSource) {
		dataSource = theDataSource;
	}

		public List<login> getStudents() throws Exception {
		
		List<login> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from login order by sid";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("sid");
				String firstName = myRs.getString("fname");
				String email = myRs.getString("email");
				String pass = myRs.getString("password");

				// create new student object
				login tempStudent = new login(id, firstName, email,pass);
				
				// add it to the list of students
				students.add(tempStudent);				
			}
			
			return students;		
		}
		finally {
			// close JDBC objects
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

	public void addStudent(login user) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into login "
					   + "(fname, email,password,sid) "
					   + "select ?,?,?,1+MAX(sid) from login";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, user.getFirstName());
			myStmt.setString(2, user.getEmail());
			myStmt.setString(3, user.getPass());

			//myStmt.setInt(4,user.getId() );;
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public login getStudent(String theStudentId) throws Exception {

		login theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from login where sid=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("fname");
				int id = myRs.getInt("sid");
				String email = myRs.getString("email");
				String pass = myRs.getString("pass");

				// use the studentId during construction
				theStudent = new login(id, firstName, email,pass);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}				
			
			return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	
	
	
	public login getUser(String email,String pass) throws Exception {

		login theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from login where email=? and password =?  ";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, email);
			myStmt.setString(2, pass);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("fname");
				int id = myRs.getInt("sid");
				String email1 = myRs.getString("email");
				String pass1 = myRs.getString("password");

				// use the studentId during construction
				theStudent = new login(id, firstName, email1,pass1);
			}
			else {
//				throw new Exception("Could not find Student email: " + email);
				return null;
			}				
			
			return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateStudent(login theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update login "
						+ "set fname=?, sid=?, email=? "
						+ "where sid=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, theStudent.getId());
			myStmt.setString(2, theStudent.getFirstName());
			myStmt.setString(3, theStudent.getEmail());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from login where sid=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}
