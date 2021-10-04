package com.epam.homelibrary.Controller.ActionManager;

import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface Action {
    void execute(HttpServletRequest request);
}
