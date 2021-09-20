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

        switch (command) {
            case ("addBook"):
                nameOfBook = request.getParameter("name");
                author = request.getParameter("author");
                int year = Integer.parseInt(request.getParameter("year"));
                long ISBN = Long.parseLong(request.getParameter("ISBN"));
                pages = Integer.parseInt(request.getParameter("pages"));
                Book book = new Book(nameOfBook, author, year, ISBN, pages);
                libraryDAO.addBook(book);
                String completeAction = book.getName() + " added";
                break;
            case ("removeBook"):
                nameOfBook = request.getParameter("name");
                libraryDAO.removeBook(nameOfBook);
                break;
            case ("removeBookByAuthor"):
                author = request.getParameter("author");
                libraryDAO.removeBookByAuthor(author);
                break;
            case ("addBookmark"):
                nameOfBook = request.getParameter("name");
                pages = Integer.parseInt(request.getParameter("pages"));
                listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
                Bookmark bookmark = new Bookmark();
                bookmark.setPage(pages);
                bookmark.setBook(listOfBooksFromDB.get(0));
                libraryDAO.addBookmark(bookmark);
                break;
            case ("removeBookmark"):
                nameOfBook = request.getParameter("name");
                listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
                if (!listOfBooksFromDB.isEmpty()) {
                    libraryDAO.removeBookmark(listOfBooksFromDB.get(0));
                }
                break;
            case ("searchBookByName"):
                nameOfBook = request.getParameter("name");
                listOfBooksFromDB = libraryDAO.searchBookByName(nameOfBook);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        System.out.println(bookFromList); //Куда вывести?
                    }
                }
                break;
            case ("searchBookByAuthor"):
                author = request.getParameter("author");
                listOfBooksFromDB = libraryDAO.searchBookByAuthor(author);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        System.out.println(bookFromList); //Куда вывести?
                    }
                }
                break;
            case ("searchBookByISBN"):
               ISBN = Long.parseLong(request.getParameter("ISBN"));
               listOfBooksFromDB = libraryDAO.searchBookByISBN(ISBN);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book bookFromList : listOfBooksFromDB) {
                        System.out.println(bookFromList); //Куда вывести?
                    }
                }
                break;
            case ("searchBookInRangeOfYears"):
                int yearFrom = Integer.parseInt(request.getParameter("yearFrom"));
                int yearTo = Integer.parseInt(request.getParameter("yearTo"));
                if (yearFrom <= yearTo) {
                    listOfBooksFromDB = libraryDAO.searchBookInRangeOfYears(yearFrom, yearTo);
                    if (!listOfBooksFromDB.isEmpty()) {
                        for (Book bookFromList : listOfBooksFromDB) {
                            System.out.println(bookFromList);  //Куда вывести?
                        }
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

                        System.out.println(bookFromList);  //Куда вывести?
                    }
                }
                break;
            case ("searchBookWithBookmarks"):
//                String login = (String) request.getSession().getAttribute("login");
                List<Book> listOfBookWithBookmarks = libraryDAO.searchBookWithBookmarks(AuthorizationServlet.getUser()); //user authenticate?
                break;
            case ("getListOfBooksFromDB"):

                break;
            case ("getListOfBookMarksFromDB"):

                break;
            case ("getListOfUserFromDB"):

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

                break;


        }
        return "jsp/MainMenu.jsp";
    }
}
