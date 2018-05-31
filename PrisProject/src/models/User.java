package models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Integer isAdmin;
	private String outputMsg;
	


	public User() {
	}


	public User(Integer id, String firstName, String lastName, String username, String password, Integer isAdmin) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	
	public int getId() {
		return id;
	}


	public String getOutputMsg() {
		return outputMsg;
	}


	public void setOutputMsg(String outputMsg) {
		this.outputMsg = outputMsg;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdministrator() {
		if (isAdmin == 1) {
			return true;
		}
		return false;
	}
}
