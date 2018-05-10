package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import models.Book;

@Named(value = "booksOutput")
@ApplicationScoped
public class BooksBean {
	private List<Book> books = new ArrayList<>();
	
	public BooksBean() {
	}

	public List<Book> getBooks() {
		return books;
	}

}
