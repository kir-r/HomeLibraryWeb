package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookByISBNAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();
    List<Book> listOfBooksFromDB;

    @Override
    public void execute(HttpServletRequest request) {
        long ISBN = Long.parseLong(request.getParameter("ISBN"));
        listOfBooksFromDB = libraryDAO.searchBookByISBN(ISBN);
        if (!listOfBooksFromDB.isEmpty()) {
            for (Book bookFromList : listOfBooksFromDB) {
                request.setAttribute("bookFromList", bookFromList);
            }
        } else {
            request.setAttribute("bookFromList", "нет такой");
        }
    }
}
