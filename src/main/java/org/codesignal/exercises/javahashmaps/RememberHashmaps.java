package org.codesignal.exercises.javahashmaps;

import java.util.HashMap;
import java.util.Map;

public class RememberHashmaps {
    public static void main(String[] args) {
        // Creating a catalog for the library using HashMap with initialization
        Map<String, String> libraryCatalog = new HashMap<>(Map.of(
                "book1", "A Tale of Two Cities",
                "book2", "To Kill a Mockingbird",
                "book3", "1984"
        ));

        // Accessing a book's title
        String title1 = libraryCatalog.get("book1");
        if (title1 != null)
            System.out.println(title1); // Output: "A Tale of Two Cities"
        else
            System.out.println("Key not found");

        // Accessing a nonexistent key
        String titleNonexistent = libraryCatalog.get("book100");
        if (titleNonexistent != null)
            System.out.println(titleNonexistent);
        else
            System.out.println("Key not found"); // Output: "Key not found"

        // Updating an existing book's title
        libraryCatalog.put("book1", "The Tell-Tale Heart");
        System.out.println("Updated book1: " + libraryCatalog.get("book1")); // Output: "Updated book1: The Tell-Tale Heart"

        // Adding a new book to the catalog
        libraryCatalog.put("book4", "Pride and Prejudice");
        System.out.println("Added book4: " + libraryCatalog.get("book4")); // Output: "Added book4: Pride and Prejudice"

        // Removing an existing book from the catalog
        libraryCatalog.remove("book1");
        System.out.println("Removed book1: " + libraryCatalog.get("book1"));

        // Looping over the HashMap
        for (Map.Entry<String, String> entry : libraryCatalog.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
