package it.books_world.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.dao.RecensioneDao;
import it.books_world.persistenza.dao.SegnalazioneDao;
import it.books_world.persistenza.dao.UtenteDao;
import it.books_world.persistenza.model.Recensione;
import it.books_world.persistenza.model.Segnalazione;
import it.books_world.persistenza.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200/")

public class Service {
	
	@GetMapping("/IncrementaLikes")
	public boolean IncrementaLikes(@RequestParam String Id) {
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=dao.FindByPrimaryKey(Long.parseLong(Id));
		dao.IncrementLikes(recensione);
		return true;
		
		
		
	}
	@GetMapping("/IncrementaDislikes")
	public boolean IncrementaDislikes(@RequestParam String Id) {
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=dao.FindByPrimaryKey(Long.parseLong(Id));
		dao.IncrementDislikes(recensione);
		return true;
		
		
	}
	@GetMapping("/getRecensioni")
	public List<Recensione> getRecensioni(@RequestParam String ISBN){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		return dao.AllBookReviews(ISBN);
		
	}
	
	@GetMapping("/removeReview")
	public boolean rimuoviRecensione(@RequestParam String Id){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=dao.FindByPrimaryKey(Long.parseLong(Id));
		return dao.Delete(recensione);
		
	}
	
	@GetMapping("/segnalaRecensione")
	public boolean segnalaRecensione(@RequestParam String Id){
		RecensioneDao rdao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=rdao.FindByPrimaryKey(Long.parseLong(Id));
		SegnalazioneDao sdao=DBManager.getInstance().getSegnalazioneDao();
		Segnalazione segnalazione=sdao.FindByPrimaryKey(Long.parseLong(Id));
		if(segnalazione==null) {
			segnalazione=new Segnalazione();
			segnalazione.setNum_segnalazioni((long) 1);
			segnalazione.setVoti_favorevoli_eliminazione((long) 0);
			segnalazione.setVoti_sfavorevoli_eliminazione((long) 0);
			segnalazione.setRecensione(recensione.getId());
			segnalazione.setScrittore(recensione.getScrittaDa().getUsername());
			segnalazione.setTitolo(recensione.getTitolo());
			segnalazione.setTesto(recensione.getTesto());
			
			
			
		}
		sdao.saveOrUpdate(segnalazione);
		return true;
		
		
		
		
	}
	
	@GetMapping("/addToCart")
	public boolean aggiungiAlCarrello(@RequestParam String username,@RequestParam String ISBN){
		CarrelloDao dao=DBManager.getInstance().getCarrelloDao();
		dao.InsertorUpdate(username, ISBN);
		return true;
		
	}
	
	//@GetMapping("/proprietaLibro")
	//public boolean proprietaLibro(@RequestParam String ISBN,@RequestParam String Username){
		//TODO: modificare ordine dao per controllare proprieta libro
		
		
	//}
	
	@GetMapping("/postataRecensione")
	public boolean postataRecensione(@RequestParam String ISBN,@RequestParam String username){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		return dao.WrittenReview(username, ISBN);
	}
	
	@GetMapping("/addToReviews")
	public boolean aggiungiRecensione(HttpServletRequest req,@RequestParam String ISBN,@RequestParam String username,@RequestParam String titolo,@RequestParam String testo,@RequestParam String numStelle){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		UtenteDao udao=DBManager.getInstance().getUtenteDao();
		Recensione recensione=new Recensione();
		recensione.setIBSN(ISBN);
		recensione.setScrittaDa(udao.FindByUsername(username));
		recensione.setTitolo(titolo);
		recensione.setTesto(testo);
		recensione.setNumeroStelle(Integer.parseInt(numStelle));
		dao.Save(recensione);
		return true;
		
	}
	
	@GetMapping("/utente")
	public Utente UtenteDaSessione(HttpServletRequest req, @RequestParam String sessionId) {
		Utente utente=null;
		try {
		 HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
         utente = (Utente) session.getAttribute("user");
         
		} catch(Exception e){
             return utente;
         }
		return utente;
		//possibile problema con date di angular
	}
	
	
	
	
	
	
	
	
	
	

}
