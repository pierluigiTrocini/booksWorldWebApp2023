package it.books_world.persistenza.dao;

import it.books_world.persistenza.model.Utente;

public interface UtenteDao {
	public Utente FindByUsername(String username);
	public Boolean Insert(Utente utente);
	public void UpdateModerator(Utente utente);
	
}
