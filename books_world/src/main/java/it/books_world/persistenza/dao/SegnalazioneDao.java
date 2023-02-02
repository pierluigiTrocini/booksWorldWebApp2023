package it.books_world.persistenza.dao;

import java.util.List;

import it.books_world.persistenza.model.Segnalazione;


public interface SegnalazioneDao {

	public Segnalazione FindByPrimaryKey(Long id);

    public void saveOrUpdate(Segnalazione segnalazione);

    public List<Segnalazione> findAll();

	public void voteAgainst(Segnalazione segnalazione);

	public void voteFor(Segnalazione segnalazione);

	// Per i voti favorevoli e sfavorevoli, un admin può spammare 1000 voti positivi e poi 1000 negativi, come facciamo a fermarlo?
	// va fatta funzione aggiunta e rimozione voti. (forse potremmo fare che puo votare solo una volta? E come facciamo a capire chi ha gia votato?)

	public void delete(Segnalazione segnalazione);

}
