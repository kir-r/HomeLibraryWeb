package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import jakarta.servlet.http.HttpServletRequest;

public class AddBookAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String nameOfBook = request.getParameter("name");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("year"));
        long ISBN = Long.parseLong(request.getParameter("ISBN"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        Book book = new Book(nameOfBook, author, year, ISBN, pages);
        libraryDAO.addBook(book);
        String completeAction = book.getName();
        request.setAttribute("addedBook", completeAction);
        return "jsp/MainMenu.jsp";
    }
}
