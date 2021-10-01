package com.epam.homelibrary.Controller.ActionManager;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class ActionResolver {
    private final Map<String, Action> mapOfActions = new HashMap<>();

    {
        mapOfActions.put("addBook", new AddBookAction());
        mapOfActions.put("removeBook", new RemoveBookAction());
        mapOfActions.put("removeBookByAuthor", new RemoveBookByAuthorAction());
        mapOfActions.put("addBookmark", new AddBookmarkAction());
        mapOfActions.put("removeBookmark", new RemoveBookmarkAction());
        mapOfActions.put("searchBookByName", new SearchBookByNameAction());
        mapOfActions.put("searchBookByAuthor", new SearchBookByAuthorAction());
        mapOfActions.put("searchBookByISBN", new SearchBookByISBNAction());
        mapOfActions.put("searchBookInRangeOfYears", new SearchBookInRangeOfYearsAction());
        mapOfActions.put("searchBookByYearPagesName", new SearchBookByYearPagesNameAction());
        mapOfActions.put("searchBookWithBookmarks", new SearchBookWithBookmarksAction());
        mapOfActions.put("getListOfBooksFromDB", new GetListOfBooksFromDBAction());
        mapOfActions.put("getListOfBookmarksFromDB", new GetListOfBookmarksFromDBAction());
        mapOfActions.put("getListOfUserFromDB", new GetListOfUserFromDBAction());
        mapOfActions.put("createUser", new CreateUserAction());
        mapOfActions.put("blockUser", new BlockUserAction());
    }

    public String execute(HttpServletRequest request) {
        String command = request.getParameter("command");
        System.out.println("command: " + command);
        mapOfActions.get(command).execute(request);

//        Через ENUM
//        Actions[] actionsArray = Actions.values();
//        for (Actions actions : actionsArray) {//
//        }

        return "jsp/MainMenu.jsp";
    }
}
