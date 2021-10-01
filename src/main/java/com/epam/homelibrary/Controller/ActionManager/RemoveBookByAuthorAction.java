package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class RemoveBookByAuthorAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String author = request.getParameter("author");
        libraryDAO.removeBookByAuthor(author);
        request.setAttribute("removedBook", "by " + author);
        return "jsp/MainMenu.jsp";
    }
}
