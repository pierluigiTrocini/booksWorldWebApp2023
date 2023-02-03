package it.books_world.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;
import com.google.api.services.books.v1.Books.Volumes.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RisultatiController {

    private ArrayList<Volume> getVolumes(String content) throws IOException{
        Books service = new Books.Builder(new NetHttpTransport(), new GsonFactory(), null).build();

        ArrayList<Volume> list = new ArrayList<Volume>();

        for( long i = 0; i <= 160; i += 40 ){
            List resultList = service.volumes().list(content).setStartIndex(i + 1).setMaxResults((long)40);
            Volumes volumes = resultList.execute();

            if( volumes.getItems() != null ){
                ArrayList<Volume> vol = (ArrayList<Volume>) volumes.getItems();
                vol.removeIf((v) -> {
                    return v.getVolumeInfo().getIndustryIdentifiers() == null ||
                        !v.getVolumeInfo().getIndustryIdentifiers().get(0).getType().contains("ISBN");
                });

                list.addAll(vol);
            }
        }

        return list;
    }

    @GetMapping("/risultati")
    @CrossOrigin("http://localhost:4200/")
    public void risultatiRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

        ArrayList<Volume> volumes = getVolumes(req.getParameter("searchText"));

        req.setAttribute("volumes", volumes);
        if(volumes == null)
            req.setAttribute("lenght", 0);
        else
            req.setAttribute("lenght", volumes.size());

        req.setAttribute("searchText", req.getParameter("searchText"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/risultatiRicerca.html");
        dispatcher.forward(req, res);
    }
}
