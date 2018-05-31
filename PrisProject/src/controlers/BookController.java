package controlers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Book;

@SessionScoped
@ManagedBean
public class BookController {
	private List<Book> books;
	private BookDbUtil bookDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public BookController() throws Exception {
		books = new ArrayList<>();
		bookDbUtil = BookDbUtil.getInstance();
	}
	public void loadBooks() {

		logger.info("Loading Books");
		
		books.clear();

		try {
			// get all Books from database
			books = bookDbUtil.getBooks(); 
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading Books", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
		}
		
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
