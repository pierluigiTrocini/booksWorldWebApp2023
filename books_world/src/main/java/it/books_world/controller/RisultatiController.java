package it.books_world.controller;


import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volumes;
import com.google.api.services.books.v1.Books.Volumes.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RisultatiController {
    
    @GetMapping("/risultati")
    @CrossOrigin("http://localhost:4200/")
    public void risultatiRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        Books service = new Books.Builder(new NetHttpTransport(), new GsonFactory(), null).build();

        List resulList = service.volumes().list(req.getParameter("searchText")).setMaxResults((long) 40);
        Volumes volumes = resulList.execute();

        req.setAttribute("volumes", volumes.getItems());
        req.setAttribute("searchText", req.getParameter("searchText"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/risultatiRicerca.html");
        dispatcher.forward(req, res);
    }   
}
