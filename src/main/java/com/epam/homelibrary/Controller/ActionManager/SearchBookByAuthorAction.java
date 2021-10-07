package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookByAuthorAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        String author = request.getParameter("author");
        List<Book> listOfBooksFromDB = libraryDAO.searchBookByAuthor(author);
        StringBuilder stringBuilder = new StringBuilder();
        if (!listOfBooksFromDB.isEmpty()) {
            for (Book bookFromList : listOfBooksFromDB) {
                stringBuilder.append(bookFromList).append(".\n");
            }
            request.setAttribute("bookFromList", stringBuilder.toString());
        } else {
            request.setAttribute("bookFromList", "нет такой");
        }
    }
}
