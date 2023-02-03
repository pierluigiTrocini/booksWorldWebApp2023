package it.books_world.controller;

import java.io.IOException;
import java.util.Map;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/acquista")
public class AcquistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("user");
        String username = utente.getUsername();
        Map<String, Integer> libri = DBManager.getInstance().getCarrelloDao()
                                    .UserChart(username).getLibriInCarrello();
        for (String isbn : libri.keySet()) {
            for (int i = 0; i < libri.get(isbn); ++i) {
                DBManager.getInstance().getCarrelloDao().DeleteorUpdate(username, isbn);
            }
        }
        req.setAttribute("username", username);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/ringraziamenti.html");
        dispatcher.forward(req, resp);
    }

}
