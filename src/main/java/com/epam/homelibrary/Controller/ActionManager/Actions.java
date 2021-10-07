package com.epam.homelibrary.Controller.ActionManager;

public enum Actions {
    addBook(new AddBookAction()), removeBook(new RemoveBookAction()), removeBookByAuthor(new RemoveBookByAuthorAction()),
    addBookmark(new AddBookmarkAction()), removeBookmark(new RemoveBookmarkAction()), searchBookByName(new SearchBookByNameAction()),
    searchBookByAuthor(new SearchBookByAuthorAction()), searchBookByISBN(new SearchBookByISBNAction()),
    searchBookInRangeOfYears(new SearchBookInRangeOfYearsAction()), searchBookByYearPagesName(new SearchBookByYearPagesNameAction()),
    searchBookWithBookmarks(new SearchBookWithBookmarksAction()), getListOfBooksFromDB(new GetListOfBooksFromDBAction()),
    getListOfBookmarksFromDB(new GetListOfBookmarksFromDBAction()), getListOfUserFromDB(new GetListOfUserFromDBAction()),
    createUser(new CreateUserAction()), blockUser(new BlockUserAction());

    Actions(Action action) {
    }
}
