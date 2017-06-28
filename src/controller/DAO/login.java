package controller.DAO;

public class login {
	
	private int id;
	private String pass;
	private String firstName;
	private String email;
	
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public login(int id ,String firstName,  String email,String pass) {
		this.firstName = firstName;
		this.id = id;
		this.email = email;
		this.pass = pass;
	}

	
	
}
