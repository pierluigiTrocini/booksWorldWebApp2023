package it.books_world.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class QuantitaController {

    @PostMapping("/aumentaQuantita")
    public Integer aumenta(HttpServletRequest req, String isbn) {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("user");
        CarrelloDao carrelloDao = DBManager.getInstance().getCarrelloDao();
        carrelloDao.InsertorUpdate(utente.getUsername(), isbn);
        return carrelloDao.UserChart(utente.getUsername()).getLibriInCarrello().get(isbn);
    }

    @PostMapping("/diminuisciQuantita")
    public Integer diminuisci(HttpServletRequest req, String isbn) {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("user");
        CarrelloDao carrelloDao = DBManager.getInstance().getCarrelloDao();
        carrelloDao.DeleteorUpdate(utente.getUsername(), isbn);
        return carrelloDao.UserChart(utente.getUsername()).getLibriInCarrello().get(isbn);
    }

}
