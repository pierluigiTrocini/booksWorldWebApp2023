package it.books_world.controller;


import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.model.Utente;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@RestController
public class AuthController {
    @GetMapping("/login")
    public void loginOrRedirect(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        if( req.getSession().getAttribute("user") == null )
            resp.sendRedirect("login.html");
    }

    @GetMapping("/signin")
    public void signInRedirect(HttpServletResponse resp) throws IOException{
        resp.sendRedirect("signin.html");
    }

    @PostMapping(value = "/confirmRegistration")
    public String accountRegistration( @RequestBody Utente utente ){
        //preliminarmente: controllare se postgres è connesso
        if( DBManager.getInstance().getConnection() == null ) return "SERVICE_UNAVAILABLE";

        //controllo di eventuali campi nulli
        if( utente.getNome() == null || utente.getNome().trim().isEmpty() ) return "EMPTY_ATTR";
        if( utente.getCognome() == null || utente.getCognome().trim().isEmpty() ) return "EMPTY_ATTR";
        if( utente.getData_di_nascita() == null ) return "EMPTY_ATTR";
        if( utente.getUsername() == null || utente.getUsername().trim().isEmpty() ) return "EMPTY_ATTR";
        if( utente.getEmail() == null || utente.getEmail().trim().isEmpty() ) return "EMPTY_ATTR";
        if( utente.getPassword() == null || utente.getPassword().trim().isEmpty() ) return "EMPTY_ATTR";

        //controllare se la mail è già stata usata
        if( DBManager.getInstance().getUtenteDao().findByEmail(utente.getEmail()) != null ) return "USED_EMAIL";
        // //controllare se lo username è già stato usato
        if( DBManager.getInstance().getUtenteDao().FindByUsername(utente.getUsername()) != null ) return "USED_USERNAME";

        //criptare la password
        utente.setPassword( PasswordEncrypter.getInstance().encryptPassword( utente.getPassword() ) );

        //inserire i campi nel database
        if( DBManager.getInstance().getUtenteDao().Insert(utente) ) return "USER_SAVED";

        return null;
    }

    @PostMapping(value = "/doLogin")
    public void accountLogin( HttpServletRequest req, HttpServletResponse res ) throws IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Utente utente = DBManager.getInstance().getUtenteDao().findByEmail(email);
        boolean login;
        HttpSession session = req.getSession();
        if( utente == null ) login = false;
        else{
            if( BCrypt.checkpw(password, utente.getPassword() ) ){
                login = true;
                session.setAttribute("user", utente);
                session.setAttribute("sessionId", session.getId());

                req.getServletContext().setAttribute(session.getId(), session);
            }
            else login = false;
        }

        if(login){
            res.sendRedirect("http://localhost:4200/?jsessionid="+session.getId());
        }
        else{
            res.sendRedirect("http://localhost:8080/invalid.html");
        }
    }


    @GetMapping("/checkisLogged")
    @ResponseBody
    @CrossOrigin("http://localhost:4200/")
    public boolean checkLoggedIn(HttpServletRequest req, HttpServletResponse resp, @RequestParam String sessionId){
        ServletContext context = req.getServletContext();
        HttpSession session = (HttpSession) context.getAttribute(sessionId);
        Utente utente = (Utente) session.getAttribute("user");
        if(utente != null){
            return true;
        }
        return false;
    }



}
