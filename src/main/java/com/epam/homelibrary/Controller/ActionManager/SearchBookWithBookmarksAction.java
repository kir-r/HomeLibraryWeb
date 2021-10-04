package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.Controller.Servlets.AuthorizationServlet;
import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookWithBookmarksAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        List<Book> listOfBookWithBookmarks = libraryDAO.searchBookWithBookmarks(AuthorizationServlet.getUser());
        StringBuilder stringBuilder = new StringBuilder();
        if (!listOfBookWithBookmarks.isEmpty()) {
            for (Book bookFromList : listOfBookWithBookmarks) {
                stringBuilder.append(bookFromList).append(".\n");
            }
            request.setAttribute("bookFromList", stringBuilder.toString());
        } else {
            request.setAttribute("bookFromList", "нет такой");
        }
    }
}
