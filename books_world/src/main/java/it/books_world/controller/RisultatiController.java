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
        if( req.getParameter("searchText") == null || req.getParameter("searchText").trim().isEmpty() ){
            res.sendRedirect("http://localhost:8080/");
        }
        else{
            long startIndex = (( req.getParameter("startIndex") == null || req.getParameter("startIndex").trim().isEmpty()) ? 0 : Long.parseLong(req.getParameter("startIndex")));

            Books service = new Books.Builder(new NetHttpTransport(), new GsonFactory(), null).build();
            List resultList = service.volumes().list(req.getParameter("searchText")).setStartIndex(startIndex * 10).setMaxResults((long) 10);
            Volumes volumes = resultList.execute();
            if(volumes.getItems() != null){
                volumes.getItems().removeIf((v) -> {
                    return v.getVolumeInfo().getIndustryIdentifiers() == null ||
                        !v.getVolumeInfo().getIndustryIdentifiers().get(0).getType().contains("ISBN");
                });
                req.setAttribute("volumes", volumes.getItems());
            }


            int lenght = (volumes.getItems() == null) ? 0 : volumes.getItems().size();

            if( req.getSession().getAttribute("user") != null ) req.setAttribute("jsessionid", req.getSession().getId());
            else req.setAttribute("jsessionid", "");

            req.setAttribute("lenght", lenght);
            req.setAttribute("startIndex", startIndex);
            req.setAttribute("searchText", req.getParameter("searchText"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/risultatiRicerca.html");
            dispatcher.forward(req, res);
        }
    }
}
