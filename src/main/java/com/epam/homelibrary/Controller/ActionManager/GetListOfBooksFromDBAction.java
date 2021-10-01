package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GetListOfBooksFromDBAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        List<Book> listOfBooksFromDB = libraryDAO.getListOfBooksFromDB();
        StringBuilder stringBuilder = new StringBuilder();
        if (!listOfBooksFromDB.isEmpty()) {
            for (Book bookFromList : listOfBooksFromDB) {
                stringBuilder.append(bookFromList).append(".\n");
                request.setAttribute("bookFromList", stringBuilder.toString());
            }
        } else {
            request.setAttribute("bookFromList", "нет такой");
        }
        return "jsp/MainMenu.jsp";
    }
}
