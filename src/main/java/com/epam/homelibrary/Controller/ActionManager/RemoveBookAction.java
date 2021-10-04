package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class RemoveBookAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        String nameOfBook = request.getParameter("name");
        libraryDAO.removeBook(nameOfBook);
        request.setAttribute("removedBook", nameOfBook);
    }
}
