<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomeLibrary</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
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
                <option value="isbn">по ISBN</option>
                <option value="author">по автору</option>
            </select>
            <button class="btn btn-outline-success" type="submit">Найти</button>
        </form>
        <div class="col-1">
            <a href="authorization?command=logout">Logout</a>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-3">
        <ul class="flex-container nav nav-pills nav-stacked bg-light">
            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Добавление/удаление
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item addBook" href="add" onclick="return false">Добавить книгу</a></li>
                <li><a class="dropdown-item addAuthor" href="#" onclick="return false">Добавить автора</a></li>
                <li><a class="dropdown-item deleteBook" href="#" onclick="return false">Удалить книгу</a></li>
                <li><a class="dropdown-item deleteAuthor" href="#" onclick="return false">Удалить автора</a></li>
            </ul>
            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Опции поиска
            </button>
            <ul class="dropdown-menu">
            <li><a class="dropdown-item searchByYears" href="#" onclick="return false">По диапазону годов</a></li>
            <li><a class="dropdown-item searchByYearPagesname" href="#" onclick="return false">По году, количеству
                страниц и названию</a></li>
        </ul>
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Закладки
        </button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item addBookmark" href="#" onclick="return false">Добавить закладку</a></li>
            <li><a class="dropdown-item deleteBookmark" href="#" onclick="return false">Удалить закладку</a></li>
            <li><a class="dropdown-item showBookmarks" href="#" onclick="return false">Показать закладки</a></li>
        </ul>


        <c:if test="${role eq 'ADMIN'}">
            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">
                Администрирование
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item addUser" href="#" onclick="return false">Добавить пользователя</a></li>
                <li><a class="dropdown-item deleteUser" href="#" onclick="return false">Заблокировать пользователя</a>
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
                <label for="year" class="col-form-label">ISBN:</label>
                <input type="text" class="form-control" name="year" id="year">
            </div>
            <div class="mb-3">
                <label for="ISBNumber" class="col-form-label">Год:</label>
                <input type="text" class="form-control" name="ISBNumber" id="ISBNumber">
            </div>
            <div class="mb-3">
                <label for="pages" class="col-form-label">Кол-во страниц:</label>
                <input type="text" class="form-control" name="pages" id="pagesToAddBook">
            </div>
            <button id="addBookButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить
            </button>
        </form>

        <form id="removeBook" action="controller" method="POST">
            <input type="hidden" name="command" value="removeBook">
            <div class="mb-3">
                <label for="name" class="col-form-label">Название книги:</label>
                <input type="text" class="form-control" name="name" id="nameToRemoveBook">
            </div>
            <button id="removeBookButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить
            </button>
        </form>

        <form id="removeBookByAuthor" action="controller" method="POST">
            <input type="hidden" name="command" value="removeBookByAuthor">
            <div class="mb-3">
                <label for="name" class="col-form-label">Автор книги:</label>
                <input type="text" class="form-control" name="author" id="authorOfRemovedBOok">
            </div>
            <button id="removeBookByAuthorButton" type="submit" class="btn btn-outline-success" disabled="disabled">
                Отправить
            </button>
        </form>

        <form id="addBookmark" id="addBookmark" action="controller" method="POST">
            <input type="hidden" name="command" value="addBookmark">
            <div class="mb-3">
                <label for="name" class="col-form-label">Название книги:</label>
                <input type="text" class="form-control" name="name" id="nameOfBookToAddBookmark">
            </div>
            <div class="mb-3">
                <label for="pages" class="col-form-label">Номер страницы:</label>
                <input type="text" class="form-control" name="pages" id="pages">
            </div>
            <button id="addBookmarkButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить
            </button>
        </form>

        <form id="removeBookmark" action="controller" method="POST">
            <input type="hidden" name="command" value="removeBookmark">
            <div class="mb-3">
                <label for="name" class="col-form-label">Название книги:</label>
                <input type="text" class="form-control" name="name" id="nameOfBookToRemoveBookmark">
            </div>
            <button id="removeBookmarkButton" type="submit" class="btn btn-outline-success" disabled="disabled">
                Отправить
            </button>
        </form>

        <form id="searchBookByName" action="controller">
            <input type="hidden" name="command" value="searchBookByName">
            <div class="mb-3">
                <label for="name" class="col-form-label">Название книги:</label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
            <button id="searchBookByNameButton" type="submit" class="btn btn-outline-success" disabled="disabled">
                Отправить
            </button>
        </form>

        <form id="searchBookByAuthor" action="controller">
            <input type="hidden" name="command" value="searchBookByAuthor">
            <div class="mb-3">
                <label for="author" class="col-form-label">Имя автора:</label>
                <input type="text" class="form-control" name="author" id="author">
            </div>
            <button id="searchBookByAuthorButton" type="submit" class="btn btn-outline-success" disabled="disabled">
                Отправить
            </button>
        </form>

        <form id="searchBookByISBN" action="controller">
            <input type="hidden" name="command" value="searchBookByISBN">
            <div class="mb-3">
                <label for="ISBN" class="col-form-label">ISBN:</label>
                <input type="text" class="form-control" name="ISBN" id="ISBN">
            </div>
            <button id="searchBookByISBNButton" type="submit" class="btn btn-outline-success" disabled="disabled">
                Отправить
            </button>
        </form>

        <form id="searchBookInRangeOfYears" action="controller">
            <input type="hidden" name="command" value="searchBookInRangeOfYears">
            <div class="mb-3">
                <label for="yearFrom" class="form-label">С года:</label>
                <input type="text" class="form-control" name="yearFrom" id="yearFrom">
            </div>
            <div class="mb-3">
                <label for="yearTo" class="form-label">По год:</label>
                <input type="text" class="form-control" name="yearTo" id="yearTo">
            </div>
            <button id="searchBookInRangeOfYearsButton" type="submit" class="btn btn-outline-success"
                    disabled="disabled">Отправить
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
                    disabled="disabled">Отправить
            </button>
        </form>

        <form id="searchBookWithBookmarks" action="controller">
            <input type="hidden" name="command" value="searchBookWithBookmarks">
        </form>

        <form id="getListOfBooksFromDB" action="controller">
            <input type="hidden" name="command" value="getListOfBooksFromDB">
        </form>

        <form id="getListOfBookmarksFromDB" action="controller">
            <input type="hidden" name="command" value="getListOfBookMarksFromDB">
        </form>

        <form id="getListOfUserFromDB" action="controller">
            <input type="hidden" name="command" value="getListOfUserFromDB">
        </form>

    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</body>

</html>