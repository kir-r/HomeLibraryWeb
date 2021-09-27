package com.epam.homelibrary.Controller.Servlets;

import com.epam.homelibrary.DAO.UserDAO;
import com.epam.homelibrary.DAO.impl.UserDataBaseDAO;
import com.epam.homelibrary.DAO.impl.UserJsonDAO;
import com.epam.homelibrary.Main;
import com.epam.homelibrary.models.Admin;
import com.epam.homelibrary.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    public static final Logger logger = LogManager.getLogger(AuthorizationServlet.class);
    private final UserDAO userDAO = new UserDataBaseDAO();
    private static User user;

    public static User getUser() {
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("from doPost before login");
        String result = login(request, response);
        try {
            request.getRequestDispatcher(result).forward(request, response);
            //Метод forward() класса RequestDispatcher позволяет перенаправить запрос из сервлета на другой сервлет,
            // html-страницу или страницу jsp
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = logout(request, response);
        try {
            request.getRequestDispatcher(result).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        user = userDAO.authenticate(login, password);

        if (user != null) {
            request.getSession().setAttribute("login", user.getLogin());
            System.out.println("Hello, " + user.getName());

            if (user.isAdmin()) {
                request.getSession().setAttribute("role", "ADMIN");
            } else {
                request.getSession().setAttribute("role", "USER");
            }

            //Когда вы используете request.getSession().setAttribute() , вы сохраняете что-то для этого
            // конкретного сеанса пользователя . Вы можете использовать этот атрибут в любое время,
            // если срок действия сеанса пользователя еще не истек .
            return "jsp/MainMenu.jsp";
        }
        else {
            return "jsp/LoginPage.jsp";
        }
    }

    private String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "/index.jsp";
    }
}
