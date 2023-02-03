package it.books_world.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.RecensioneDao;
import it.books_world.persistenza.dao.SegnalazioneDao;
import it.books_world.persistenza.model.Recensione;
import it.books_world.persistenza.model.Segnalazione;

@Controller
public class SegnalazioniController {

    @GetMapping("/segnalazioni")
    public String getSegnalazioni(Model model) {
        SegnalazioneDao segnalazioneDao = DBManager.getInstance().getSegnalazioneDao();
        List<Segnalazione> segnalazioni = segnalazioneDao.findAll();
        List<Recensione> recensioni = new ArrayList<>();
        for (Segnalazione segnalazione : segnalazioni) {
            RecensioneDao recensioneDao = DBManager.getInstance().getRecensioneDao();
            Recensione recensione = recensioneDao.FindByPrimaryKey(segnalazione.getRecensione());
            recensioni.add(recensione);
        }
        model.addAttribute("recensioni", recensioni);
        return "segnalazioni.html";
    }

}
