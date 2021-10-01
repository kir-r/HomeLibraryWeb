package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookInRangeOfYearsAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        int yearFrom = Integer.parseInt(request.getParameter("yearFrom"));
        int yearTo = Integer.parseInt(request.getParameter("yearTo"));
        StringBuilder stringBuilder = new StringBuilder();
        if (yearFrom <= yearTo) {
            List<Book> listOfBooksFromDB = libraryDAO.searchBookInRangeOfYears(yearFrom, yearTo);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book bookFromList : listOfBooksFromDB) {
                    stringBuilder.append(bookFromList).append(".\n");
                }
                request.setAttribute("bookFromList", stringBuilder.toString());
            } else {
                request.setAttribute("bookFromList", "нет такой");
            }
        }
        return "jsp/MainMenu.jsp";
    }
}
