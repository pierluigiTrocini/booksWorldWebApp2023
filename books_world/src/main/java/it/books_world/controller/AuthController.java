package it.books_world.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginOrRedirect(HttpServletRequest req, Model model, String jsessionid){
        Boolean registrato = false;
        if(!registrato)
            return "redirect:login.html";
        else
            return "redirect:http://localhost:4200";
    }

    @GetMapping("/signin")
    public String signInRedirect(Model model){
        return "redirect:signin.html";
    }

    @PostMapping(value="/confirmRegistration")
    public String accountRegistration(){


        return null;
    } 
    
    
}
