package com.epam.homelibrary.Controller.Servlets;

import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;

import java.util.List;

public enum Actions {
    addBook, removeBook, removeBookByAuthor, addBookmark, removeBookmark, searchBookByName, searchBookByAuthor,
    searchBookByISBN, searchBookInRangeOfYears, searchBookByYearPagesName, searchBookWithBookmarks, getListOfBooksFromDB,
    getListOfBookMarksFromDB, getListOfUserFromDB
}
