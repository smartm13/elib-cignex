package controller;
import java.sql.*;
import java.util.*;
import controller.DAO.*;

public class IssueDao {
 
    public Issue createValueObject() {
          return new Issue(0, 0, 0, 0, null, null, false);
    }


     
    public Issue getObject(Connection conn, int iid) throws NotFoundException, SQLException {

          Issue valueObject = createValueObject();
          valueObject.setIid(iid);
          load(conn, valueObject);
          return valueObject;
    }


     
    public void load(Connection conn, Issue valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM issue WHERE (iid = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getIid()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


     
    public List<Issue> loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM issue ORDER BY iid ASC ";
          List<Issue> searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



     
    public synchronized void create(Connection conn, Issue valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;

          try {
               sql = "INSERT INTO issue ( iid, sid, bid, "
               + "cid, issueon, releaseon, "
               + "isactive) VALUES (?, ?, ?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getIid()); 
               stmt.setInt(2, valueObject.getSid()); 
               stmt.setInt(3, valueObject.getBid()); 
               stmt.setInt(4, valueObject.getCid()); 
               stmt.setDate(5, valueObject.getIssueon()); 
               stmt.setDate(6, valueObject.getReleaseon()); 
               stmt.setBoolean(7, valueObject.getIsactive()); 

               int rowcount = databaseUpdate(conn, stmt);
               if (rowcount != 1) {
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }


     
    public void save(Connection conn, Issue valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE issue SET sid = ?, bid = ?, cid = ?, "
               + "issueon = ?, releaseon = ?, isactive = ? WHERE (iid = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getSid()); 
              stmt.setInt(2, valueObject.getBid()); 
              stmt.setInt(3, valueObject.getCid()); 
              stmt.setDate(4, valueObject.getIssueon()); 
              stmt.setDate(5, valueObject.getReleaseon()); 
              stmt.setBoolean(6, valueObject.getIsactive()); 

              stmt.setInt(7, valueObject.getIid()); 

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


     
    public void delete(Connection conn, Issue valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM issue WHERE (iid = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getIid()); 

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

          String sql = "DELETE FROM issue";
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

          String sql = "SELECT count(*) FROM issue";
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


     
    public List searchMatching(Connection conn, Issue valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM issue WHERE 1=1 ");

          if (valueObject.getIid() != 0) {
              if (first) { first = false; }
              sql.append("AND iid = ").append(valueObject.getIid()).append(" ");
          }

          if (valueObject.getSid() != 0) {
              if (first) { first = false; }
              sql.append("AND sid = ").append(valueObject.getSid()).append(" ");
          }

          if (valueObject.getBid() != 0) {
              if (first) { first = false; }
              sql.append("AND bid = ").append(valueObject.getBid()).append(" ");
          }

          if (valueObject.getCid() != 0) {
              if (first) { first = false; }
              sql.append("AND cid = ").append(valueObject.getCid()).append(" ");
          }

          if (valueObject.getIssueon() != null) {
              if (first) { first = false; }
              sql.append("AND issueon = '").append(valueObject.getIssueon()).append("' ");
          }

          if (valueObject.getReleaseon() != null) {
              if (first) { first = false; }
              sql.append("AND releaseon = '").append(valueObject.getReleaseon()).append("' ");
          }

          if (valueObject.getIsactive() != false) {
              if (first) { first = false; }
              sql.append("AND isactive LIKE '").append(valueObject.getIsactive()).append("%' ");
          }


          sql.append("ORDER BY iid ASC ");

           
           
          if (first)
               searchResults = new ArrayList<Object>();
          else
               searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

          return searchResults;
    }


     
    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }


     
    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {

          int result = stmt.executeUpdate();

          return result;
    }



     
    protected void singleQuery(Connection conn, PreparedStatement stmt, Issue valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setIid(result.getInt("iid")); 
                   valueObject.setSid(result.getInt("sid")); 
                   valueObject.setBid(result.getInt("bid")); 
                   valueObject.setCid(result.getInt("cid")); 
                   valueObject.setIssueon(result.getDate("issueon")); 
                   valueObject.setReleaseon(result.getDate("releaseon")); 
                   valueObject.setIsactive(result.getBoolean("isactive")); 

              } else {
                     
                    throw new NotFoundException("Issue Object Not Found!");
              }
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }


     
    protected List<Issue> listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

          ArrayList<Issue> searchResults = new ArrayList<Issue>();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Issue temp = createValueObject();

                   temp.setIid(result.getInt("iid")); 
                   temp.setSid(result.getInt("sid")); 
                   temp.setBid(result.getInt("bid")); 
                   temp.setCid(result.getInt("cid")); 
                   temp.setIssueon(result.getDate("issueon")); 
                   temp.setReleaseon(result.getDate("releaseon")); 
                   temp.setIsactive(result.getBoolean("isactive")); 

                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List<Issue>)searchResults;
    }


}