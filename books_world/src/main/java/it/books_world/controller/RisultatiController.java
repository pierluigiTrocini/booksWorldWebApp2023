package it.books_world.controller;


import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RisultatiController {
    
    @GetMapping("/risultati")
    @CrossOrigin("http://localhost:4200/")
    public void risultatiRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException{
        System.out.println( req.getAttribute("searchText") );
        res.sendRedirect("views/risultatiRicerca.html");
    }   
}
