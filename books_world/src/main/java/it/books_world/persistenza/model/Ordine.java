package it.books_world.persistenza.model;

import java.util.Date;

public class Ordine {
	
	private Long id;
	
	private String utente;
	
	private Date data;
	
	private String isbn_libro;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getIsbn_libro() {
		return isbn_libro;
	}
	public void setIsbn_libro(String isbn_libro) {
		this.isbn_libro = isbn_libro;
	}
	
}
