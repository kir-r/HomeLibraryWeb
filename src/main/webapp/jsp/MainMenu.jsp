<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomeLibrary</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
          integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link rel="stylesheet" href="css/mainMenuStyle.css">
</head>

<body>
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand">Home Library</a>
        <form class="d-flex col-sm-8" action="controller">
            <input class="form-control" type="search" placeholder="Напишите что-нибудь..."
                   aria-label="Search" name="name">
            <input class="action" type="hidden" name="command" value="searchBookByName">
            <select class="form-select mr-3">
                <option value="name" selected>по имени</option>
                <option value="ISBN">по ISBN</option>
                <option value="author">по автору</option>
            </select>
            <button class="btn btn-outline-success" type="submit">Найти</button>
        </form>
        <div class="col-1">
            <a href="authorization?command=logout">Logout</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <p>Найденная книга: ${bookFromList}</p>
    <p>Добавленная книга: ${addedBook}</p>
    <p>Удаленная книга: ${removedBook}</p>
    <p>Добавленная закладка: ${addedBookmark}</p>
    <p>Удалена закладка из книги: ${removedBookmark}</p>
    <p>Закладки в базе данных: ${bookmarkFromList}</p>
    <p>Все пользователи в базе данных: ${userFromList}</p>
</div>

<div class="row">
    <div class="col-3">
        <ul class="flex-container nav nav-pills nav-stacked bg-light">
            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Добавление/удаление
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item addBook" href="#" onclick="return false">Добавить книгу</a></li>
                <li><a class="dropdown-item removeBook" href="#" onclick="return false">Удалить книгу</a></li>
                <li><a class="dropdown-item removeBookByAuthor" href="#" onclick="return false">Удалить книгу по
                    автору</a>
                </li>
            </ul>
        </ul>
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Опции поиска
        </button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item searchBookInRangeOfYears" href="#" onclick="return false">По диапазону
                годов</a></li>
            <li><a class="dropdown-item searchBookByYearPagesName" href="#" onclick="return false">По году,
                количеству
                страниц и названию</a></li>
        </ul>
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Закладки
        </button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item addBookmark" href="#" onclick="return false">Добавить закладку</a></li>
            <li><a class="dropdown-item removeBookmark" href="#" onclick="return false">Удалить закладку</a></li>
            <li><a class="dropdown-item getListOfBookmarksFromDB" href="#" onclick="return false">Показать
                закладки</a></li>
        </ul>
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Что в библиотеке?
        </button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item getListOfBooksFromDB" href="#" onclick="return false">Посмотреть книги в
                базе данных</a></li>
        </ul>

        <c:if test="${role eq 'ADMIN'}">
            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">
                Администрирование
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item createUser" href="#" onclick="return false">Добавить пользователя</a>
                </li>
                <li><a class="dropdown-item blockUser" href="#" onclick="return false">Заблокировать
                    пользователя</a>
                </li>
                <li><a class="dropdown-item getListOfUserFromDB" href="#" onclick="return false">Посмотреть всех
                    пользователей</a>
                </li>
            </ul>
        </c:if>
        </ul>
    </div>
    <div class="col-2"></div>
    <div class="col-5">
        <form name="addBook" id="addBook" action="controller" method="POST">
            <input type="hidden" name="command" value="addBook">
            <div class="mb-2">
                <label for="name" class="col-form-label">Название книги:</label>
                <input type="text" class="form-control" name="name" id="nameToAddBook">
            </div>
            <div class="mb-1">
                <label for="author" class="col-form-label">Автор:</label>
                <input type="text" class="form-control" name="author" id="authorName">
            </div>
            <div class="mb-3">
                <label for="year" class="col-form-label">Год:</label>
                <input type="text" class="form-control" name="year" id="year">
            </div>
            <div class="mb-3">
                <label for="ISBN" class="col-form-label">ISBN:</label>
                <input type="text" class="form-control" name="ISBN" id="ISBNumber">
            </div>
            <div class="mb-3">
                <label for="pages" class="col-form-label">Кол-во страниц:</label>
                <input type="text" class="form-control" name="pages" id="pagesToAddBook">
            </div>
            <button id="addBookButton" type="submit" class="btn btn-outline-success">Добавить
            </button>
        </form>

        <form id="removeBook" action="controller" method="POST">
            <input type="hidden" name="command" value="removeBook">
            <div class="mb-3">
                <label for="name" class="col-form-label">Удалить книгу по названию:</label>
                <input type="text" class="form-control" name="name" id="nameToRemoveBook">
            </div>
            <button id="removeBookButton" type="submit" class="btn btn-outline-success">Удалить
            </button>
        </form>

        <form id="removeBookByAuthor" action="controller" method="POST">
            <input type="hidden" name="command" value="removeBookByAuthor">
            <div class="mb-3">
                <label for="name" class="col-form-label">Удалить книгу автора:</label>
                <input type="text" class="form-control" name="author" id="authorOfRemovedBOok">
            </div>
            <button id="removeBookByAuthorButton" type="submit" class="btn btn-outline-success">
                Удалить
            </button>
        </form>

        <form id="addBookmark" id="addBookmark" action="controller" method="POST">
            <input type="hidden" name="command" value="addBookmark">
            <div class="mb-3">
                <label for="name" class="col-form-label">Добавить закладку в книгу:</label>
                <input type="text" class="form-control" name="name" id="nameOfBookToAddBookmark">
            </div>
            <div class="mb-3">
                <label for="pages" class="col-form-label">Номер страницы:</label>
                <input type="text" class="form-control" name="pages" id="pages">
            </div>
            <button id="addBookmarkButton" type="submit" class="btn btn-outline-success">Добавить
            </button>
        </form>

        <form id="removeBookmark" action="controller" method="POST">
            <input type="hidden" name="command" value="removeBookmark">
            <div class="mb-3">
                <label for="name" class="col-form-label">Удалить закладку из книги:</label>
                <input type="text" class="form-control" name="name" id="nameOfBookToRemoveBookmark">
            </div>
            <button id="removeBookmarkButton" type="submit" class="btn btn-outline-success">
                Удалить
            </button>
        </form>

        <form id="searchBookByName" action="controller">
            <input type="hidden" name="command" value="searchBookByName">
            <div class="mb-3">
                <label for="name" class="col-form-label">Искать книгу:</label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
            <button id="searchBookByNameButton" type="submit" class="btn btn-outline-success">
                Найти
            </button>
        </form>

        <form id="searchBookByAuthor" action="controller">
            <input type="hidden" name="command" value="searchBookByAuthor">
            <div class="mb-3">
                <label for="author" class="col-form-label">Искать книгу автора:</label>
                <input type="text" class="form-control" name="author" id="author">
            </div>
            <button id="searchBookByAuthorButton" type="submit" class="btn btn-outline-success">
                Найти
            </button>
        </form>

        <form id="searchBookByISBN" action="controller">
            <input type="hidden" name="command" value="searchBookByISBN">
            <div class="mb-3">
                <label for="ISBN" class="col-form-label">Искать по ISBN:</label>
                <input type="text" class="form-control" name="ISBN" id="ISBN">
            </div>
            <button id="searchBookByISBNButton" type="submit" class="btn btn-outline-success">
                Найти
            </button>
        </form>

        <form id="searchBookInRangeOfYears" action="controller">
            <input type="hidden" name="command" value="searchBookInRangeOfYears">
            <div class="mb-3">
                <label for="yearFrom" class="form-label">Искать книги с года:</label>
                <input type="text" class="form-control" name="yearFrom" id="yearFrom">
            </div>
            <div class="mb-3">
                <label for="yearTo" class="form-label">По год:</label>
                <input type="text" class="form-control" name="yearTo" id="yearTo">
            </div>
            <button id="searchBookInRangeOfYearsButton" type="submit" class="btn btn-outline-success"
            >Найти в этом промежутке
            </button>
        </form>

        <form id="searchBookByYearPagesName" action="controller" method="POST">
            <input type="hidden" name="command" value="searchBookByYearPagesName">
            <div class="mb-3">
                <label for="name" class="col-form-label">Название книги:</label>
                <input type="text" class="form-control" name="name" id="nameToSearch">
            </div>
            <div class="mb-3">
                <label for="year" class="col-form-label">Год:</label>
                <input type="text" class="form-control" name="year" id="yearToSearch">
            </div>
            <div class="mb-3">
                <label for="pages" class="col-form-label">Кол-во страниц:</label>
                <input type="text" class="form-control" name="pages" id="pagesToSearch">
            </div>
            <button id="searchBookByYearPagesNameButton" type="submit" class="btn btn-outline-success"
            >Найти по году, страницам и имени
            </button>
        </form>

        <form id="searchBookWithBookmarks" action="controller">
            <input type="hidden" name="command" value="searchBookWithBookmarks">
            <button id="searchBookWithBookmarksButton" type="submit" class="btn btn-outline-success"
            >Найти книги с закладками
            </button>
        </form>

        <form id="getListOfBooksFromDB" action="controller">
            <input type="hidden" name="command" value="getListOfBooksFromDB">
            <button id="getListOfBooksFromDBButton" type="submit" class="btn btn-outline-success"
            >Cмотреть
            </button>
        </form>

        <form id="getListOfBookmarksFromDB" action="controller">
            <input type="hidden" name="command" value="getListOfBookmarksFromDB">
            <button id="getListOfBookmarksFromDBButton" type="submit" class="btn btn-outline-success"
            >Посмотреть закладки в базе данных
            </button>
        </form>

        <form id="getListOfUserFromDB" action="controller">
            <input type="hidden" name="command" value="getListOfUserFromDB">
            <button id="getListOfUserFromDBButton" type="submit" class="btn btn-outline-success"
            >Посмотреть всех пользователей
            </button>
        </form>

        <form id="createUser" action="controller">
            <input type="hidden" name="command" value="createUser">
            <button id="createUserButton" type="submit" class="btn btn-outline-success"
            >Добавить пользователя
            </button>
        </form>

        <form id="blockUser" action="controller">
            <input type="hidden" name="command" value="blockUser">
            <button id="blockUserButton" type="submit" class="btn btn-outline-success"
            >Забанить пользователя
            </button>
        </form>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
        integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
        crossorigin="anonymous"></script>
<script src="src/form-visibility.js"></script>
</body>
</html>
