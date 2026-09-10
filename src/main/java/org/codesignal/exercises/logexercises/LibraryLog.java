package org.codesignal.exercises.logexercises;

/*
You are provided with log data from a library's digital system, stored in string format.
The log represents books' borrowing activities, including the book ID and the time a book is borrowed and returned.
The structure of a log entry is as follows: <book_id> borrow <time>, <book_id> return <time>.

The time is given in the HH:MM 24-hour format, and the book ID is a positive integer between 1 and 500.
The logs are separated by a comma, followed by a space (", ").

Your task is to create a Java function named solution().
This function will take as input a string of logs and output a list of strings representing the books with the longest borrowed duration.
Each string contains the book ID and the book's borrowed duration, concatenated by a space.
By 'borrowed duration,' we mean the period from when the book is borrowed until it is returned.
If a book has been borrowed and returned multiple times, the borrowed duration is the total cumulative sum of those durations.
If multiple books share the same longest borrowed duration, the function should return all such books in ascending order of their IDs.
 */

import java.util.*;

public class LibraryLog {
    public List<String> solution(String logs) {
        List<String> booksReport = new ArrayList<>();
        HashMap<Integer, int[]> timeDict = new HashMap<>();  // HashMap to record the borrow moment for each book in minutes
        TreeMap<Integer, Integer> lifeDict = new TreeMap<>(); // TreeMap to record the borrowed duration for each book in minutes

        String[] logEntries = logs.split(", ");

        for(String logEntry : logEntries){
            String[] logEntryDetails = logEntry.split(" ");
            Integer bookId = Integer.parseInt(logEntryDetails[0].trim());
            String action = logEntryDetails[1].trim();
            String time = logEntryDetails[2].trim();

            // Parsing the time from HH:MM format
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));
            int currentTime = hour * 60 + minute; // Time in minutes from start of day

            if (action.equals("borrow")) {
                timeDict.put(bookId, new int[]{hour, minute}); // If the book is borrowed, log the borrow time.
            } else {
                if (timeDict.containsKey(bookId)) {
                    // If the book is returned, calculate its borrowed duration and remove it from the records.
                    int borrowTime = timeDict.get(bookId)[0] * 60 + timeDict.get(bookId)[1];
                    int lifetime = currentTime - borrowTime;
                    lifeDict.put(bookId, lifeDict.getOrDefault(bookId, 0) + lifetime);
                    timeDict.remove(bookId);
                }
            }
        }

        // Find the longest borrow time
        int maxLife = Collections.max(lifeDict.entrySet(), Map.Entry.comparingByValue()).getValue();

        // Building the result list where each item is a string of "book Id  borrow time" if it has the longest lifetime.
        for (Map.Entry<Integer, Integer> entry : lifeDict.entrySet()) {
            if (entry.getValue() == maxLife) {
                int hours = entry.getValue() / 60;
                int minutes = entry.getValue() % 60;
                String timeString = String.format("%02d:%02d", hours, minutes);
                booksReport.add(entry.getKey() + " " + timeString);
            }
        }

        return booksReport;
    }

    public static void main(String args[]){
        String booksLog = "1 borrow 09:00, 2 borrow 10:00, 1 return 12:00, 3 borrow 13:00, 2 return 15:00, 3 return 16:00";
        LibraryLog libraryLog = new LibraryLog();
        List<String> result = libraryLog.solution(booksLog);
        System.out.println(result);
    }
}
