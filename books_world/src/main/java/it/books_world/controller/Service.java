package it.books_world.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.dao.OrdineDao;
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
	public boolean IncrementaLikes(@RequestParam String id) {
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=dao.FindByPrimaryKey(Long.parseLong(id));
		try {
			dao.IncrementLikes(recensione);
		} catch (Exception e) {
			return false;
		}
		return true;
		
		
		
	}
	@GetMapping("/IncrementaDislikes")
	public boolean IncrementaDislikes(@RequestParam String id) {
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=dao.FindByPrimaryKey(Long.parseLong(id));
		try {
			dao.IncrementDislikes(recensione);
		} catch (Exception e) {
			return false;
		}
		return true;
		
		
	}
	@GetMapping("/getRecensioni")
	public List<Recensione> getRecensioni(@RequestParam String ISBN){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		return dao.AllBookReviews(ISBN);
		
	}
	
	@GetMapping("/removeReview")
	public boolean rimuoviRecensione(@RequestParam String id){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=dao.FindByPrimaryKey(Long.parseLong(id));
		return dao.Delete(recensione);
		
	}
	
	@GetMapping("/segnalaRecensione")
	public boolean segnalaRecensione(@RequestParam String id){
		RecensioneDao rdao=DBManager.getInstance().getRecensioneDao();
		Recensione recensione=rdao.FindByPrimaryKey(Long.parseLong(id));
		SegnalazioneDao sdao=DBManager.getInstance().getSegnalazioneDao();
		Segnalazione segnalazione=sdao.FindByPrimaryKey(Long.parseLong(id));
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
		try {
			sdao.saveOrUpdate(segnalazione);
		} catch (Exception e) {
			return false;
		}
		return true;
		
		
		
		
	}
	
	@GetMapping("/addToCart")
	public boolean aggiungiAlCarrello(@RequestParam String username,@RequestParam String isbn){
		CarrelloDao dao=DBManager.getInstance().getCarrelloDao();
		try {
		dao.InsertorUpdate(username, isbn);}
		catch(Exception e){
			return false;
		}
		return true;
		
	}
	
	@GetMapping("/proprietaLibro")
	public boolean proprietaLibro(@RequestParam String isbn,@RequestParam String username){
		OrdineDao dao = DBManager.getInstance().getOrdineDao();
		return dao.userOwnsBook(username, isbn);
	}
	
	
	@GetMapping("/postataRecensione")
	public boolean postataRecensione(@RequestParam String isbn,@RequestParam String username){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		
		return dao.WrittenReview(username, isbn);
		
	}
	
	@GetMapping("/addToReviews")
	public boolean aggiungiRecensione(HttpServletRequest req,@RequestParam String isbn,@RequestParam String username,@RequestParam String titolo,@RequestParam String testo,@RequestParam String numStelle){
		RecensioneDao dao=DBManager.getInstance().getRecensioneDao();
		UtenteDao udao=DBManager.getInstance().getUtenteDao();
		Recensione recensione=new Recensione();
		recensione.setIBSN(isbn);
		recensione.setScrittaDa(udao.FindByUsername(username));
		recensione.setTitolo(titolo);
		recensione.setTesto(testo);
		recensione.setNumeroStelle(Integer.parseInt(numStelle));
		
		try {
			dao.Save(recensione);
		} catch (Exception e) {
			
			return false;
		}
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
