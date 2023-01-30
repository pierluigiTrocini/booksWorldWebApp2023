package it.books_world.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.books_world.persistenza.DBManager;

@Controller
public class AcquistaController {

    @GetMapping("/acquista")
    public String acquista(Model model, String usernameCarrello, String intestatario,
                    String numeroCarta, String dataScadenza, String cvv) {
        Map<String, Integer> libri = DBManager.getInstance().getCarrelloDao()
                                    .UserChart(usernameCarrello).getLibriInCarrello();
        for (String isbn : libri.keySet()) libri.remove(isbn);
        usernameCarrello = "pit0500";
        model.addAttribute("username", usernameCarrello);
        return "ringraziamenti.html";
    }

}
