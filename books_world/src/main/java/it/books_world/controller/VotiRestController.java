package it.books_world.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.SegnalazioneDao;
import it.books_world.persistenza.model.Segnalazione;

@RestController
public class VotiRestController {

    @PostMapping("/votaAFavore")
    public Boolean votaAFavore(Long idRecensione) {
        SegnalazioneDao segnalazioneDao = DBManager.getInstance().getSegnalazioneDao();
        Segnalazione segnalazione = segnalazioneDao.FindByPrimaryKey(idRecensione);
        segnalazione.setVoti_sfavorevoli_eliminazione(segnalazione.getVoti_sfavorevoli_eliminazione()+1);
        segnalazioneDao.saveOrUpdate(segnalazione);
        return true;
    }

    @PostMapping("/votaContro")
    public Boolean votaContro(Long idRecensione) {
        SegnalazioneDao segnalazioneDao = DBManager.getInstance().getSegnalazioneDao();
        Segnalazione segnalazione = segnalazioneDao.FindByPrimaryKey(idRecensione);
        segnalazione.setVoti_favorevoli_eliminazione(segnalazione.getVoti_favorevoli_eliminazione()+1);
        segnalazioneDao.saveOrUpdate(segnalazione);
        return true;
    }

}
