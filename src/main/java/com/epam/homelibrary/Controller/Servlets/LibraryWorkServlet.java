package com.epam.homelibrary.Controller.Servlets;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
public class LibraryWorkServlet extends HttpServlet {
    public static final Logger logger = LogManager.getLogger(LibraryWorkServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionResolver actionResolver = new ActionResolver();
        String result = actionResolver.execute(request);
        request.getRequestDispatcher(result).forward(request, response);
    }
}


