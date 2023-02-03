package it.books_world.persistenza.model;

import java.util.HashMap;

public class Carrello {
	
	private String username;
	private HashMap<String,Integer> LibriInCarrello; //da dao-->se id non nullo lo metto poi in part. se il libro e gia in mappa lo aggiorno altrimenti lo creo e metto in mappa

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public HashMap<String, Integer> getLibriInCarrello() {
		return LibriInCarrello;
	}
	public void setLibriInCarrello(HashMap<String, Integer> libriInCarrello) {
		LibriInCarrello = libriInCarrello;
	}

}
