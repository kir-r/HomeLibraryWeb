package com.epam.homelibrary.Controller.TokenManager;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
    private final TokenManager tokenManager = new TokenManager();

    public void addTokenToCookies(HttpServletResponse response, String login) {
        String signature = tokenManager.encodeToken(login);
        Cookie cookie = new Cookie("token", signature);
        response.addCookie(cookie);
    }
    public boolean verifyToken(HttpServletRequest request) {
        Cookie cookie = getCookie(request);
        if (cookie != null) {
            return tokenManager.decodeToken(cookie.getValue());
        }
        return false;
    }


    private Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request);
        assert cookie != null;
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
