package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookByYearPagesNameAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        String nameOfBook = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        List<Book> listOfBooksFromDB = libraryDAO.searchBookByYearPagesName(nameOfBook, year, pages);
        if (!listOfBooksFromDB.isEmpty()) {
            for (Book bookFromList : listOfBooksFromDB) {
                request.setAttribute("bookFromList", bookFromList);
            }
        } else {
            request.setAttribute("bookFromList", "нет такой");
        }
    }
}
