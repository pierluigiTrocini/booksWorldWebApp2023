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
        CarrelloDao carrelloDao = DBManager.getInstance().getCarrelloDao();
        HttpSession session = req.getSession();
        if (session != null) {
            Utente utente = (Utente) session.getAttribute("user");
            Carrello carrello = carrelloDao.UserChart(utente.getUsername());
            Map<String, Integer> libri = carrello.getLibriInCarrello();
            ArrayList<VolumeQuantita> volumi = new ArrayList<>();
            for (String isbn : libri.keySet()) {
                List volumeList = service.volumes().list(isbn);
                Volumes volumes = volumeList.execute();
                Volume volume = volumes.getItems().get(0);
                VolumeQuantita vq = new VolumeQuantita();
                vq.setVolume(volume);
                vq.setQuantita(libri.get(isbn));
                for (var id : volume.getVolumeInfo().getIndustryIdentifiers()) {
                    if (id.getType().startsWith("ISBN"))
                        vq.setIsbn(id.getIdentifier());
                }
                volumi.add(vq);
            }
            req.setAttribute("vQuantita", volumi);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/mostraCarrello.html");
            dispatcher.forward(req, resp);
        }
    }

}
