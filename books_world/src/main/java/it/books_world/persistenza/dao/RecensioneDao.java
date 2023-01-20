package it.books_world.persistenza.dao;

import java.util.List;

import it.books_world.persistenza.model.Recensione;

public interface RecensioneDao {
	public void Save(Recensione recensione);
	public void Delete(Recensione recensione);
	public List<Recensione>AllBookReviews(String ISBN);
	
}
