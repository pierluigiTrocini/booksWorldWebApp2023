package it.books_world.controller;


import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private Volumes getVolumes(String content) throws IOException{
        Books service = new Books.Builder(new NetHttpTransport(), new GsonFactory(), null).build();
        List resulList = service.volumes().list(content).setMaxResults((long) 40);
        Volumes volumes = resulList.execute();

        volumes.getItems().removeIf((volume) -> {
            return volume.getVolumeInfo().getIndustryIdentifiers() == null ||
                !volume.getVolumeInfo().getIndustryIdentifiers().get(0).getType().contains("ISBN");
        });

        return volumes;
    }

    @GetMapping("/risultati")
    @CrossOrigin("http://localhost:4200/")
    public void risultatiRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

        Volumes volumes = getVolumes(req.getParameter("searchText"));

        req.setAttribute("volumes", volumes.getItems());
        if(volumes.getItems() == null)
            req.setAttribute("lenght", 0);
        else
            req.setAttribute("lenght", volumes.getItems().size());
            
        req.setAttribute("searchText", req.getParameter("searchText"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/risultatiRicerca.html");
        dispatcher.forward(req, res);
    }

    @PostMapping("/ordineAlfabetico")
    public void ordineAlfabetico( @RequestBody JsonResponse response, HttpServletRequest req, HttpServletResponse res) throws IOException{
        Volumes volumes = getVolumes(response.getContent());
        if( response.getValue() ){
            //ordine alfabetico
        }
        else{
            //ordine inverso
        }


    }

    @PostMapping("/googleRating")
    public void googleRating ( @RequestBody JsonResponse response, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        Volumes volumes = getVolumes(response.getContent());
        if( response.getValue() ){
            //visualizza solo i votati da google
            volumes.getItems().removeIf((volume) -> {
                return volume.getVolumeInfo().getAverageRating() == null;
            });
        }
        else{
            volumes.getItems().removeIf((volume) -> {
                return volume.getVolumeInfo().getAverageRating() != null;
            });
        }

        req.setAttribute("volumes", volumes.getItems());
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/risultatiRicerca.html");
        dispatcher.forward(req, res);

    }

    @PostMapping("/filtroLingua")
    public void filtroLingua( @RequestBody JsonResponse response, HttpServletRequest req, HttpServletResponse res) throws IOException{
        Volumes volumes = getVolumes(response.getContent());

    }

}
