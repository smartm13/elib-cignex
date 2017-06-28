package controller.DAO;

public class author {
    private int aid;
    private String name;
	public author(int id, String authorName) {
		this.name = authorName;
		this.aid = id;	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
