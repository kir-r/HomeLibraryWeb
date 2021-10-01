package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AddBookmarkAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String nameOfBook = request.getParameter("name");
        int pages = Integer.parseInt(request.getParameter("pages"));
        List<Book> listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
        Bookmark bookmark = new Bookmark();
        bookmark.setPage(pages);
        bookmark.setBook(listOfBooksFromDB.get(0));
        libraryDAO.addBookmark(bookmark);
        request.setAttribute("addedBookmark", bookmark);
        return "jsp/MainMenu.jsp";
    }
}
