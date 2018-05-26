package controlers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.User;

public class UserDbUtil {

	private static UserDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/pris";
	
	public static UserDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new UserDbUtil();
		}
		
		return instance;
	}
	
	private UserDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<User> getUsers() throws Exception {

		List<User> Users = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from User order by lastName";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				int isAdmin = myRs.getInt("isAdmin");

				// create new User object
				User tempUser = new User(id, firstName, lastName, username, password, isAdmin);

				// add it to the list of Users
				Users.add(tempUser);
			}
			
			return Users;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addUser(User theUser) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into User (firstName, lastName, username, password, isAdmin) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getUsername());
			myStmt.setString(4, theUser.getPassword());
			myStmt.setInt(5, theUser.getIsAdmin());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public User getUser(int UserId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from User where id=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, UserId);
			
			myRs = myStmt.executeQuery();

			User theUser = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				int isAdmin = myRs.getInt("isAdmin");
				

				theUser = new User(id, firstName, lastName, username, password, isAdmin);
			}
			else {
				throw new Exception("Could not find User id: " + UserId);
			}

			return theUser;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void deleteUser(int UserId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from User where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, UserId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	
}
