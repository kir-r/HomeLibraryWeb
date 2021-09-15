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
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        String parameterFromUser = request.getParameter("name"); //author?
        String result = libraryDAO.command + "(" + parameterFromUser + ")";

    }
}
