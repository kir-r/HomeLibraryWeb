package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface Action {
    String execute(HttpServletRequest request);

//    addBookAction, removeBookAction, removeBookByAuthorAction, addBookmarkAction, removeBookmarkAction, searchBookByNameAction, searchBookByAuthorAction,
//    searchBookByISBNAction, searchBookInRangeOfYearsAction, searchBookByYearPagesNameAction, searchBookWithBookmarksAction, getListOfBooksFromDBAction,
//    getListOfBookMarksFromDBAction, getListOfUserFromDBAction
}
