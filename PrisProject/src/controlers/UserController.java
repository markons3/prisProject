package controlers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.User;



@SessionScoped
@ManagedBean
public class UserController {

	private List<User> users;
	private UserDbUtil userDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	private String msg = "";

	public UserController() throws Exception {
		users = new ArrayList<>();
		userDbUtil = UserDbUtil.getInstance();
	}
	
	public List<User> getUsers() {
		return users;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void loadUsers() {

		logger.info("Loading Users");
		
		users.clear();

		try {
			// get all Users from database
			users = userDbUtil.getUsers();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading Users", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
		}
		
	}
				
	public String addUser(User user) {
		logger.info("Adding user: " + user);
		try {
			userDbUtil.addUser(user);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error adding user", e);
			addErrorMessage(e);
			return null;
		}
		return "firstPage?faces-redirect=true";
	}
	
	public String loginUser(String usrname, String password) {
		logger.info("Logging user with username: " + usrname);
		try { 
			if (userDbUtil.loginUser(usrname,password) != null) {
				return "loggedInPage?faces-redirect=true";
			} else {
				this.msg = "Invalid User!";
				this.users = new ArrayList<>();
				return "login";
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error logging user", e);
			addErrorMessage(e);
			return null;
		}
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}