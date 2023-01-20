package it.books_world.persistenza.model;

public class Segnalazione {
	
	private Long recensione;
	
	private Long num_segnalazioni;
	
	private Long voti_favorevoli_eliminazione;
	
	private Long voti_sfavorevoli_eliminazione;
	

	public Long getRecensione() {
		return recensione;
	}

	public void setRecensione(Long recensione) {
		this.recensione = recensione;
	}

	public Long getNum_segnalazioni() {
		return num_segnalazioni;
	}

	public void setNum_segnalazioni(Long num_segnalazioni) {
		this.num_segnalazioni = num_segnalazioni;
	}

	public Long getVoti_favorevoli_eliminazione() {
		return voti_favorevoli_eliminazione;
	}

	public void setVoti_favorevoli_eliminazione(Long voti_favorevoli_eliminazione) {
		this.voti_favorevoli_eliminazione = voti_favorevoli_eliminazione;
	}

	public Long getVoti_sfavorevoli_eliminazione() {
		return voti_sfavorevoli_eliminazione;
	}

	public void setVoti_sfavorevoli_eliminazione(Long voti_sfavorevoli_eliminazione) {
		this.voti_sfavorevoli_eliminazione = voti_sfavorevoli_eliminazione;
	}

}
