package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookByNameAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String nameOfBook = request.getParameter("name");
        List<Book> listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
        if (!listOfBooksFromDB.isEmpty()) {
            for (Book bookFromList : listOfBooksFromDB) {
                request.setAttribute("bookFromList", bookFromList);
            }
        } else {
            request.setAttribute("bookFromList", "нет такой");
        }
        return "jsp/MainMenu.jsp";
    }
}
