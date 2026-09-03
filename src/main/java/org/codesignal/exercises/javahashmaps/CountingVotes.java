package org.codesignal.exercises.javahashmaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingVotes {
    public static void main(String[] args) {
        List<String> votesList = Arrays.asList("Alice", "Bob", "Alice", "Charlie", "Bob", "Alice");  // Cast votes
        HashMap<String, Integer> voteCounts = new HashMap<>();  // Initializing a HashMap

        // Counting the votes
        for (String name : votesList) {
            voteCounts.put(name, voteCounts.getOrDefault(name, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        // Prints: Alice: 3, Bob: 2, Charlie: 1
    }
}
