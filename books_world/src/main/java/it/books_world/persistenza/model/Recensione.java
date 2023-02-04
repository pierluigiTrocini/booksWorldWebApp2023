package it.books_world.persistenza.model;


public class Recensione {
	 Long id;
	 String titolo;
	 String testo;
	 Integer numeroMiPiace;
	 Integer numeroNonMiPiace;
	 Utente scrittaDa;
	 String IBSN;
	 Integer NumeroStelle;
	 Boolean Segnalabile;
	public Recensione() {
		numeroMiPiace=0;
		numeroNonMiPiace=0;
		Segnalabile=true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public Integer getNumeroMiPiace() {
		return numeroMiPiace;
	}
	public void setNumeroMiPiace(Integer numeroMiPiace) {
		this.numeroMiPiace = numeroMiPiace;
	}
	public Integer getNumeroNonMiPiace() {
		return numeroNonMiPiace;
	}
	public void setNumeroNonMiPiace(Integer numeroNonMiPiace) {
		this.numeroNonMiPiace = numeroNonMiPiace;
	}
	public Utente getScrittaDa() {
		return scrittaDa;
	}
	public void setScrittaDa(Utente scrittaDa) {
		this.scrittaDa = scrittaDa;
	}
	public String getIBSN() {
		return IBSN;
	}
	public void setIBSN(String iBSN) {
		IBSN = iBSN;
	}
	public Integer getNumeroStelle() {
		return NumeroStelle;
	}
	public void setNumeroStelle(Integer numeroStelle) {
		NumeroStelle = numeroStelle;
	}
	public Boolean getSegnalabile() {
		return Segnalabile;
	}
	public void setSegnalabile(Boolean segnalabile) {
		Segnalabile = segnalabile;
	}



}
