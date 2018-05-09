package user;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class User {
	private String username;
	private String password;
	private Integer isAdmin;
	
	public User() {
		
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
	
	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAdminUser(Integer isAdmin) {
		if (isAdmin == 1) {
			return true;
		}
		return false;
	}

}
