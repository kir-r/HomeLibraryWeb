package com.epam.homelibrary.Controller.Servlets;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.DAO.UserDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.DAO.impl.UserDataBaseDAO;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ActionResolver {
    private final LibraryDAO libraryDAO = new LibraryDataBaseDAO();
    private final UserDAO userDAO = new UserDataBaseDAO();

    public String execute(HttpServletRequest request) {
        String nameOfBook;
        String author;
        int pages;
        List<Book> listOfBooksFromDB;
        User user;
        String command = request.getParameter("command");
        System.out.println("command: " + command);

        switch (command) {
            case ("addBook"):
                nameOfBook = request.getParameter("name");
                author = request.getParameter("author");
                int year = Integer.parseInt(request.getParameter("year"));
                long ISBN = Long.parseLong(request.getParameter("ISBN"));
                pages = Integer.parseInt(request.getParameter("pages"));
                Book book = new Book(nameOfBook, author, year, ISBN, pages);
                libraryDAO.addBook(book);
                String completeAction = book.getName();
                request.setAttribute("addedBook", completeAction);
                break;
            case ("removeBook"):
                nameOfBook = request.getParameter("name");
                libraryDAO.removeBook(nameOfBook);
                request.setAttribute("removedBook", nameOfBook);
                break;
            case ("removeBookByAuthor"):
                author = request.getParameter("author");
                libraryDAO.removeBookByAuthor(author);
                request.setAttribute("removedBook", "by " + author);
                break;
            case ("addBookmark"):
                nameOfBook = request.getParameter("name");
                pages = Integer.parseInt(request.getParameter("pages"));
                listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
                Bookmark bookmark = new Bookmark();
                bookmark.setPage(pages);
                bookmark.setBook(listOfBooksFromDB.get(0));
                libraryDAO.addBookmark(bookmark);
                request.setAttribute("addedBookmark", bookmark);
                break;
            case ("removeBookmark"):
                nameOfBook = request.getParameter("name");
                listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
                if (!listOfBooksFromDB.isEmpty()) {
                    libraryDAO.removeBookmark(listOfBooksFromDB.get(0));
                    request.setAttribute("removedBookmark", nameOfBook);
                } else {
                    request.setAttribute("removedBookmark", "нет такой");
                }
                break;
            case ("searchBookByName"):
                nameOfBook = request.getParameter("name");
                listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        request.setAttribute("bookFromList", bookFromList);
                    }
                } else {
                    request.setAttribute("bookFromList", "нет такой");
                }
                break;
            case ("searchBookByAuthor"):
                author = request.getParameter("author");
                listOfBooksFromDB = libraryDAO.searchBookByAuthor(author);
                StringBuilder stringBuilder1 = new StringBuilder();
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        stringBuilder1.append(bookFromList).append(".\n");
                    }
                    request.setAttribute("bookFromList", stringBuilder1.toString());
                } else {
                    request.setAttribute("bookFromList", "нет такой");
                }
                break;
            case ("searchBookByISBN"):
                ISBN = Long.parseLong(request.getParameter("ISBN"));
                listOfBooksFromDB = libraryDAO.searchBookByISBN(ISBN);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        request.setAttribute("bookFromList", bookFromList);
                    }
                } else {
                    request.setAttribute("bookFromList", "нет такой");
                }
                break;
            case ("searchBookInRangeOfYears"):
                int yearFrom = Integer.parseInt(request.getParameter("yearFrom"));
                int yearTo = Integer.parseInt(request.getParameter("yearTo"));
                StringBuilder stringBuilder2 = new StringBuilder();
                if (yearFrom <= yearTo) {
                    listOfBooksFromDB = libraryDAO.searchBookInRangeOfYears(yearFrom, yearTo);
                    if (!listOfBooksFromDB.isEmpty()) {
                        for (Book bookFromList : listOfBooksFromDB) {
                            stringBuilder2.append(bookFromList).append(".\n");
                        }
                        request.setAttribute("bookFromList", stringBuilder2.toString());
                    } else {
                        request.setAttribute("bookFromList", "нет такой");
                    }
                }
                break;
            case ("searchBookByYearPagesName"):
                nameOfBook = request.getParameter("name");
                year = Integer.parseInt(request.getParameter("year"));
                pages = Integer.parseInt(request.getParameter("pages"));
                listOfBooksFromDB = libraryDAO.searchBookByYearPagesName(nameOfBook, year, pages);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        request.setAttribute("bookFromList", bookFromList);
                    }
                } else {
                    request.setAttribute("bookFromList", "нет такой");
                }
                break;
            case ("searchBookWithBookmarks"):
                List<Book> listOfBookWithBookmarks = libraryDAO.searchBookWithBookmarks(AuthorizationServlet.getUser());
                StringBuilder stringBuilder3 = new StringBuilder();
                if (!listOfBookWithBookmarks.isEmpty()) {
                    for (Book bookFromList : listOfBookWithBookmarks) {
                        stringBuilder3.append(bookFromList).append(".\n");
                    }
                    request.setAttribute("bookFromList", stringBuilder3.toString());
                } else {
                    request.setAttribute("bookFromList", "нет такой");
                }
                break;
            case ("getListOfBooksFromDB"):
                listOfBooksFromDB = libraryDAO.getListOfBooksFromDB();
                StringBuilder stringBuilder4 = new StringBuilder();
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        stringBuilder4.append(bookFromList).append(".\n");
                        request.setAttribute("bookFromList", stringBuilder4.toString());
                    }
                } else {
                    request.setAttribute("bookFromList", "нет такой");
                }
                break;
            case ("getListOfBookmarksFromDB"):
                List<Bookmark> listOfBookmarksFromDB = libraryDAO.getListOfBookMarksFromDB();
                StringBuilder stringBuilder5 = new StringBuilder();
                if (!listOfBookmarksFromDB.isEmpty()) {
                    for (Bookmark bookmarkFromList : listOfBookmarksFromDB) {
                        stringBuilder5.append(bookmarkFromList).append(".\n");
                        request.setAttribute("bookmarkFromList", stringBuilder5.toString());
                    }
                } else {
                    request.setAttribute("bookmarkFromList", "нет такой");
                }
                break;
            case ("getListOfUserFromDB"):
                List<User> listOfUserFromDB = libraryDAO.getListOfUserFromDB();
                StringBuilder stringBuilder6 = new StringBuilder();
                if (!listOfUserFromDB.isEmpty()) {
                    for (User userFromList : listOfUserFromDB) {
                        stringBuilder6.append(userFromList).append(".\n");
                        request.setAttribute("userFromList", stringBuilder6.toString());
                    }
                } else {
                    request.setAttribute("userFromList", "нету никого");
                }
                break;
            case ("createUser"):
                String login = request.getParameter("login");
                System.out.println("createUser login: " + login);
                String password = request.getParameter("password");
                System.out.println("createUser password: " + password);
                user = new User();
                user.setAdmin(false);
                user.setLogin(login);
                user.setPassword(password);
                userDAO.createUser(user);
                break;
            case ("blockUser"):
                String username = request.getParameter("username");
                userDAO.blockUser(username);
                break;
        }
        return "jsp/MainMenu.jsp";
    }
}
