package it.books_world.controller;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AcquistaController {

    @GetMapping("/acquista")
    public String acquista(Model model, String usernameCarrello, String intestatario,
                    String numeroCarta, String dataScadenza, String cvv) {
        Map<String, Integer> libri = DBManager.getInstance().getCarrelloDao()
                                    .UserChart(usernameCarrello).getLibriInCarrello();
        for (String isbn : libri.keySet()) libri.remove(isbn);
        model.addAttribute("username", usernameCarrello);
        return "ringraziamenti.html";
    }

    @PostMapping("/aumentaQuantita")
    public Integer aumenta(HttpServletRequest req, String isbn) {
        String [] sessionIdParam = req.getQueryString().split("&")[0].split("=");
        String sessionId = sessionIdParam[1];
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente utente = (Utente) session.getAttribute("user");
        DBManager.getInstance().getCarrelloDao().InsertorUpdate(utente.getUsername(), isbn);
        CarrelloDao carrelloDao = DBManager.getInstance().getCarrelloDao();
        carrelloDao.InsertorUpdate(utente.getUsername(), isbn);
        return carrelloDao.UserChart(utente.getUsername()).getLibriInCarrello().get(isbn);
    }

    @PostMapping("/diminuisciQuantita")
    public Integer diminuisci(HttpServletRequest req, String isbn) {
        String [] sessionIdParam = req.getQueryString().split("&")[0].split("=");
        String sessionId = sessionIdParam[1];
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente utente = (Utente) session.getAttribute("user");
        CarrelloDao carrelloDao = DBManager.getInstance().getCarrelloDao();
        carrelloDao.DeleteorUpdate(utente.getUsername(), isbn);
        return carrelloDao.UserChart(utente.getUsername()).getLibriInCarrello().get(isbn);
    }

}
