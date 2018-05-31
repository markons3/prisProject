package models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Book {
	private int id;
	private String title;
	private String author;
	private String field;
	private String description;
	
	public Book() {
	}
	
	public Book(Integer id, String title, String author, String field, String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.field = field;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	
	
}
