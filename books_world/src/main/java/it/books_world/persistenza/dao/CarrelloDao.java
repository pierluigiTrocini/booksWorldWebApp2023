package it.books_world.persistenza.dao;

import it.books_world.persistenza.model.Carrello;

public interface CarrelloDao {
	public Carrello UserChart(String username);
	public void InsertorUpdate(String username,String ISBN);
	public void DeleteorUpdate(String username,String ISBN);
	public Integer UserHasBook(String username,String ISBN);
	
	

}
