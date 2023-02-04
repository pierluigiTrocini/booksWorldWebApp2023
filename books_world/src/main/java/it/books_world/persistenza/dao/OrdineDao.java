package it.books_world.persistenza.dao;

import java.util.List;

import it.books_world.persistenza.model.Ordine;
import it.books_world.persistenza.model.Utente;

public interface OrdineDao {
	
	public void save(Ordine ordine);
	
	public List<Ordine> findAll();
	
	public boolean userOwnsBook(String username, String isbn);
	
	public List<Ordine> findByUser(Utente utente);
	
	public void delete(Ordine ordine);
	
    
}
