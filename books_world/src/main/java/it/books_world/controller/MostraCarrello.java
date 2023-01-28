package it.books_world.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MostraCarrello {

    @GetMapping("/mostraCarrello")
    public String getCarrello(HttpServletRequest req, String jsessionid, Model model) {
        List<String> lista_isbn = new ArrayList<>();
        lista_isbn.add("982345389");
        model.addAttribute("list", lista_isbn);
        return "mostraCarrello.html";
    }


}
