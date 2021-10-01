package com.epam.homelibrary.Controller.Servlets;

import com.epam.homelibrary.Controller.ActionManager.ActionResolver;
import com.epam.homelibrary.Controller.TokenManager.CookieManager;
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
    private final CookieManager cookieManager = new CookieManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (cookieManager.verifyToken(request)) {
            ActionResolver actionResolver = new ActionResolver();
            String result = actionResolver.execute(request);
            request.getRequestDispatcher(result).forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/LoginPage.jsp").forward(request, response);
        }
    }
}
