package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class RemoveBookmarkAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        String nameOfBook = request.getParameter("name");
        List<Book> listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
        if (!listOfBooksFromDB.isEmpty()) {
            libraryDAO.removeBookmark(listOfBooksFromDB.get(0));
            request.setAttribute("removedBookmark", nameOfBook);
        } else {
            request.setAttribute("removedBookmark", "нет такой");
        }
    }
}
