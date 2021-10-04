package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Bookmark;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GetListOfBookmarksFromDBAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();

    @Override
    public void execute(HttpServletRequest request) {
        List<Bookmark> listOfBookmarksFromDB = libraryDAO.getListOfBookmarksFromDB();
        StringBuilder stringBuilder = new StringBuilder();
        if (!listOfBookmarksFromDB.isEmpty()) {
            for (Bookmark bookmarkFromList : listOfBookmarksFromDB) {
                stringBuilder.append(bookmarkFromList).append(".\n");
                request.setAttribute("bookmarkFromList", stringBuilder.toString());
            }
        } else {
            request.setAttribute("bookmarkFromList", "нет такой");
        }
    }
}
