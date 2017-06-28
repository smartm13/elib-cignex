package controller.DAO;

public class publisher {
    private int pid;
    private String name;
    
	public publisher(int id, String publisherName) {
		this.name = publisherName;
		this.pid = id;	
		}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}    
}
