package it.books_world.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/chi_siamo")
public class ChiSiamo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("jsessionid", req.getSession().getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/chi_siamo.html");
        dispatcher.forward(req, resp);
    }

}
