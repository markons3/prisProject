package controlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.Book;

public class BookDbUtil {
	private static BookDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/pris";
	
	public static BookDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new BookDbUtil();
		}
		
		return instance;
	}
	
	private BookDbUtil() throws Exception {		
		dataSource = getDataSource();
	}
	
	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	public List<Book> getBooks() throws Exception {

		List<Book> books = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from Book order by id";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String author = myRs.getString("author");
				String field = myRs.getString("field");
				String descritption = myRs.getString("descritption");

				// create new Book object
				Book tempBook = new Book(id, title, author, field, descritption);

				// add it to the list of Books
				books.add(tempBook);
			}
			
			return books;		
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
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
