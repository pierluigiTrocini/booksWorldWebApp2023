package it.books_world.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;
import com.google.api.services.books.v1.Books.Volumes.List;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.model.Carrello;
import it.books_world.persistenza.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mostraCarrello")
public class MostraCarrello extends HttpServlet {

    class VolumeQuantita {
        Volume volume;
        Integer quantita;
        String isbn;

        public Volume getVolume() {
            return volume;
        }
        public void setVolume(Volume volume) {
            this.volume = volume;
        }
        public Integer getQuantita() {
            return quantita;
        }
        public void setQuantita(Integer quantita) {
            this.quantita = quantita;
        }
        public String getIsbn() {
            return isbn;
        }
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Books service = new Books.Builder(new NetHttpTransport(), new GsonFactory(), null)
                        .setApplicationName("BooksWorldApplication")
                        .build();
        HttpSession session = req.getSession();
        if (session != null) {
            CarrelloDao carrelloDao = DBManager.getInstance().getCarrelloDao();
            Utente utente = (Utente) session.getAttribute("user");
            if (utente != null) {
                Carrello carrello = carrelloDao.UserChart(utente.getUsername());
                Map<String, Integer> libri = carrello.getLibriInCarrello();
                if (libri.isEmpty()) {
                    req.setAttribute("sessionid", session.getId());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("views/carrelloVuoto.html");
                    dispatcher.forward(req, resp);
                    return;
                }
                ArrayList<VolumeQuantita> volumi = new ArrayList<>();
                for (String isbn : libri.keySet()) {
                    List volumeList = service.volumes().list(isbn);
                    Volumes volumes = volumeList.execute();
                    Volume volume = volumes.getItems().get(0);
                    VolumeQuantita vq = new VolumeQuantita();
                    vq.setVolume(volume);
                    vq.setQuantita(libri.get(isbn));
                    vq.setIsbn(isbn);
                    volumi.add(vq);
                }
                req.setAttribute("sessionid", req.getSession().getId());
                req.setAttribute("vQuantita", volumi);
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/mostraCarrello.html");
                dispatcher.forward(req, resp);
            }
            else {
                resp.sendRedirect("/login.html");
            }
        }
    }

}
