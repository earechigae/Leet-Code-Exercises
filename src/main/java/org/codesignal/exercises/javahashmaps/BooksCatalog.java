package org.codesignal.exercises.javahashmaps;

import java.util.HashMap;

public class BooksCatalog {
    public static void main(String[] args) {
        // Initializing a HashMap
        HashMap<String, HashMap<String, String>> libraryCatalog = new HashMap<>();

        // Details of a book
        String bookId = "123";
        // Creating a HashMap to store details of the book
        HashMap<String, String> bookDetails = new HashMap<>();
        bookDetails.put("title", "To Kill a Mockingbird");
        bookDetails.put("author", "Harper Lee");
        bookDetails.put("year_published", "1960");

        libraryCatalog.put(bookId, bookDetails);  // Adding a book to library catalog, where value itself is a HashMap

        // Searching for a book
        if (libraryCatalog.containsKey(bookId)) {
            System.out.println("Title: " + libraryCatalog.get(bookId).get("title") +
                    ", Author: " + libraryCatalog.get(bookId).get("author") +
                    ", Year Published: " + libraryCatalog.get(bookId).get("year_published"));
        }

        libraryCatalog.remove(bookId);  // Removing a book from the library
    }
}
