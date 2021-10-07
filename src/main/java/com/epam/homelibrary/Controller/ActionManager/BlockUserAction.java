package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.UserDAO;
import com.epam.homelibrary.DAO.impl.UserDataBaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class BlockUserAction implements Action {
    private final UserDAO userDAO = new UserDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        userDAO.blockUser(username);
    }
}
