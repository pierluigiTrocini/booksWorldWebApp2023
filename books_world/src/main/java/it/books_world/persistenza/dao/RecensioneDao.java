package it.books_world.persistenza.dao;

import java.util.List;

import it.books_world.persistenza.model.Recensione;

public interface RecensioneDao {
	public void Save(Recensione recensione);
	public boolean Delete(Recensione recensione);
	public List<Recensione>AllBookReviews(String ISBN);
	public void IncrementLikes(Recensione recensione);
	public void IncrementDislikes(Recensione recensione);
	public Recensione FindByPrimaryKey(Long id);
	public boolean WrittenReview(String username,String ISBN);
}
