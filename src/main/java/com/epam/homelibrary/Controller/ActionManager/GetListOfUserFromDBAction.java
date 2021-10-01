package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GetListOfUserFromDBAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        List<User> listOfUserFromDB = libraryDAO.getListOfUserFromDB();
        StringBuilder stringBuilder = new StringBuilder();
        if (!listOfUserFromDB.isEmpty()) {
            for (User userFromList : listOfUserFromDB) {
                stringBuilder.append(userFromList).append(".\n");
                request.setAttribute("userFromList", stringBuilder.toString());
            }
        } else {
            request.setAttribute("userFromList", "нету никого");
        }
        return "jsp/MainMenu.jsp";
    }
}
