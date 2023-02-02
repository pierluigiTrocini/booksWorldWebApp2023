package it.books_world.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.RecensioneDao;
import it.books_world.persistenza.dao.SegnalazioneDao;
import it.books_world.persistenza.model.Recensione;
import it.books_world.persistenza.model.Segnalazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SegnalazioniController {

    @GetMapping("/segnalazioni")
    public void getSegnalazioni(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        SegnalazioneDao segnalazioneDao = DBManager.getInstance().getSegnalazioneDao();
        List<Segnalazione> segnalazioni = segnalazioneDao.findAll();
        List<Recensione> recensioni = new ArrayList<>();
        for (Segnalazione segnalazione : segnalazioni) {
            RecensioneDao recensioneDao = DBManager.getInstance().getRecensioneDao();
            Recensione recensione = recensioneDao.FindByPrimaryKey(segnalazione.getRecensione());
            recensioni.add(recensione);
        }
        req.setAttribute("recensioni", recensioni);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/segnalazioni.html");
        dispatcher.forward(req, resp);
    }

}
