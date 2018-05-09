package models;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class Book {
	private String title;
	private String author;
	private String field;
	private String descritption;
	
	public Book() {
		
	}

	public String getNaslov() {
		return title;
	}

	public void setNaslov(String title) {
		this.title = title;
	}

	public String getAutor() {
		return author;
	}

	public void setAutor(String autor) {
		this.author = autor;
	}

	public String getOblast() {
		return field;
	}

	public void setOblast(String field) {
		this.field = field;
	}

	public String getOpis() {
		return descritption;
	}

	public void setOpis(String descritption) {
		this.descritption = descritption;
	}
	
	
}
