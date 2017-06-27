package controller;


import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import controller.DAO.Books;

public class BooksDTO {



	private DataSource dataSource;

	public BooksDTO(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
		
	
    public Books createValueObject() {
          return new Books(0, null, 0, 0, 0);
    }


     
    public Books getObject(Connection conn, int bid) throws NotFoundException, SQLException {
    	conn = dataSource.getConnection();	
          Books valueObject = createValueObject();
          valueObject.setBid(bid);
          load(conn, valueObject);
          return valueObject;
    }


     
    public void load(Connection conn, Books valueObject) throws NotFoundException, SQLException {
    	conn = dataSource.getConnection();
          String sql = "SELECT * FROM books WHERE (bid = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getBid()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


     
    public List<Books> loadAll(Connection conn) throws SQLException {
    	conn = dataSource.getConnection();
          String sql = "SELECT * FROM books ORDER BY bid ASC ";
          List<Books> searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



     
    public synchronized void create(Connection conn, Books valueObject) throws SQLException {
    	conn = dataSource.getConnection();
          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO books ( bid, name, isbn, "
               + "pid, price) VALUES (?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getBid()); 
               stmt.setString(2, valueObject.getName()); 
               stmt.setLong(3, valueObject.getIsbn()); 
               stmt.setInt(4, valueObject.getPid()); 
               stmt.setInt(5, valueObject.getPrice()); 

               int rowcount = databaseUpdate(conn, stmt);
               if (rowcount != 1) {
                     
                     throw new SQLException("PrimaryKeyError when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }


     
    public void save(Connection conn, Books valueObject) 
          throws NotFoundException, SQLException {
    	conn = dataSource.getConnection();
          String sql = "UPDATE books SET name = ?, isbn = ?, pid = ?, "
               + "price = ? WHERE (bid = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 
              stmt.setLong(2, valueObject.getIsbn()); 
              stmt.setInt(3, valueObject.getPid()); 
              stmt.setInt(4, valueObject.getPrice()); 

              stmt.setInt(5, valueObject.getBid()); 

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                    
                   throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                    
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


     
    public void delete(Connection conn, Books valueObject) 
          throws NotFoundException, SQLException {
    	conn = dataSource.getConnection();
          String sql = "DELETE FROM books WHERE (bid = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getBid()); 

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                    
                   throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                    
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


     
    public void deleteAll(Connection conn) throws SQLException {
    	conn = dataSource.getConnection();
          String sql = "DELETE FROM books";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              int rowcount = databaseUpdate(conn, stmt);
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


     
    public int countAll(Connection conn) throws SQLException {
    	conn = dataSource.getConnection();
          String sql = "SELECT count(*) FROM books";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try {
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next())
                  allRows = result.getInt(1);
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
          return allRows;
    }


     
    public List<?> searchMatching(Connection conn, Books valueObject) throws SQLException {
    	conn = dataSource.getConnection();
          List<?> searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM books WHERE 1=1 ");

          if (valueObject.getBid() != 0) {
              if (first) { first = false; }
              sql.append("AND bid = ").append(valueObject.getBid()).append(" ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getIsbn() != 0) {
              if (first) { first = false; }
              sql.append("AND isbn = ").append(valueObject.getIsbn()).append(" ");
          }

          if (valueObject.getPid() != 0) {
              if (first) { first = false; }
              sql.append("AND pid = ").append(valueObject.getPid()).append(" ");
          }

          if (valueObject.getPrice() != 0) {
              if (first) { first = false; }
              sql.append("AND price = ").append(valueObject.getPrice()).append(" ");
          }


          sql.append("ORDER BY bid ASC ");

           
           
          if (first)
               searchResults = new ArrayList();
          else
               searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

          return searchResults;
    }


     
    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {
    	conn = dataSource.getConnection();
          int result = stmt.executeUpdate();

          return result;
    }



     
    protected void singleQuery(Connection conn, PreparedStatement stmt, Books valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;
          conn = dataSource.getConnection();
          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setBid(result.getInt("bid")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setIsbn(result.getLong("isbn")); 
                   valueObject.setPid(result.getInt("pid")); 
                   valueObject.setPrice(result.getInt("price")); 

              } else {
                    throw new NotFoundException("Books Object Not Found!");
              }
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }


     
    protected List<Books> listQuery(Connection conn, PreparedStatement stmt) throws SQLException {
    	conn = dataSource.getConnection();
          ArrayList searchResults = new ArrayList();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Books temp = createValueObject();

                   temp.setBid(result.getInt("bid")); 
                   temp.setName(result.getString("name")); 
                   temp.setIsbn(result.getLong("isbn")); 
                   temp.setPid(result.getInt("pid")); 
                   temp.setPrice(result.getInt("price")); 

                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List)searchResults;
    }


}