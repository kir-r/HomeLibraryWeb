package com.epam.homelibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LibraryAPI libraryAPI = new LibraryAPI();
        libraryAPI.operate();
    }
}
