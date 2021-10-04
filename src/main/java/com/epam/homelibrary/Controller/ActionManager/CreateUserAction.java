package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.UserDAO;
import com.epam.homelibrary.DAO.impl.UserDataBaseDAO;
import com.epam.homelibrary.models.User;
import jakarta.servlet.http.HttpServletRequest;

public class CreateUserAction implements Action {
    private final UserDAO userDAO = new UserDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        System.out.println("createUser login: " + login);
        String password = request.getParameter("password");
        System.out.println("createUser password: " + password);
        User user = new User();
        user.setAdmin(false);
        user.setLogin(login);
        user.setPassword(password);
        userDAO.createUser(user);
    }
}
