package controller.DAO;

public class Books {

    private int bid;
    private String name;
    private long isbn;
    private int pid;
    private int price;




    public Books(int bid, String name, long isbn, int pid, int price) {
		super();
		this.bid = bid;
		this.name = name;
		this.isbn = isbn;
		this.pid = pid;
		this.price = price;
	}

	public Books (int bidIn) {

          this.bid = bidIn;

    }

    public int getBid() {
          return this.bid;
    }
    public void setBid(int bidIn) {
          this.bid = bidIn;
    }

    public String getName() {
          return this.name;
    }
    public void setName(String nameIn) {
          this.name = nameIn;
    }

    public long getIsbn() {
          return this.isbn;
    }
    public void setIsbn(long isbnIn) {
          this.isbn = isbnIn;
    }

    public int getPid() {
          return this.pid;
    }
    public void setPid(int pidIn) {
          this.pid = pidIn;
    }

    public int getPrice() {
          return this.price;
    }
    public void setPrice(int priceIn) {
          this.price = priceIn;
    }
  
    public String toString() {
        StringBuffer out = new StringBuffer(" ");
        out.append("\nclass Books, mapping to table books\n");
        out.append("Persistent attributes: \n"); 
        out.append("bid = " + this.bid + "\n"); 
        out.append("name = " + this.name + "\n"); 
        out.append("isbn = " + this.isbn + "\n"); 
        out.append("pid = " + this.pid + "\n"); 
        out.append("price = " + this.price + "\n"); 
        return out.toString();
    }


     
    
}