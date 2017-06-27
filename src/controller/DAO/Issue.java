package controller.DAO;



import java.io.*;

public class Issue {

     
    private int iid;
    private int sid;
    private int bid;
    private int cid;
    private java.sql.Date issueon;
    private java.sql.Date releaseon;
    private boolean isactive;

    
    public Issue (int iidIn,
            int sidIn,
            int bidIn,
            int cidIn,
            java.sql.Date issueonIn,
            java.sql.Date releaseonIn,
            boolean isactiveIn) {
            this.iid = iidIn;
            this.sid = sidIn;
            this.bid = bidIn;
            this.cid = cidIn;
            this.issueon = issueonIn;
            this.releaseon = releaseonIn;
            this.isactive = isactiveIn;
      }
    public int getIid() {
          return this.iid;
    }
    public void setIid(int iidIn) {
          this.iid = iidIn;
    }

    public int getSid() {
          return this.sid;
    }
    public void setSid(int sidIn) {
          this.sid = sidIn;
    }

    public int getBid() {
          return this.bid;
    }
    public void setBid(int bidIn) {
          this.bid = bidIn;
    }

    public int getCid() {
          return this.cid;
    }
    public void setCid(int cidIn) {
          this.cid = cidIn;
    }

    public java.sql.Date getIssueon() {
          return this.issueon;
    }
    public void setIssueon(java.sql.Date issueonIn) {
          this.issueon = issueonIn;
    }

    public java.sql.Date getReleaseon() {
          return this.releaseon;
    }
    public void setReleaseon(java.sql.Date releaseonIn) {
          this.releaseon = releaseonIn;
    }

    public boolean getIsactive() {
          return this.isactive;
    }
    public void setIsactive(boolean isactiveIn) {
          this.isactive = isactiveIn;
    }   
    public String toString() {
        StringBuffer out = new StringBuffer(" ");
        out.append("\nclass Issue, mapping to table Issue\n");
        out.append("Persistent attributes: \n"); 
        out.append("iid = " + this.iid + "\n"); 
        out.append("sid = " + this.sid + "\n"); 
        out.append("bid = " + this.bid + "\n"); 
        out.append("cid = " + this.cid + "\n"); 
        out.append("issueon = " + this.issueon + "\n"); 
        out.append("releaseon = " + this.releaseon + "\n"); 
        out.append("isactive = " + this.isactive + "\n"); 
        return out.toString();
    }
  

}