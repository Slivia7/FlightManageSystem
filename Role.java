package code;

public class Role {
	protected String name;
	protected String password;
	public Role(String name,String password) {
		this.name=name;
		this.password=password;
	}
	public Role() {
		
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}

}
