package org.codesignal.exercises.javahashmaps;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyTables {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("blue");

        HashMap<String, Integer> colorMap = new HashMap<>();

        // Iterate over each color and increase its count
        for (String color : colors) {
            colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);
        }

        // Print our HashMap with counts
        for (String key : colorMap.keySet()) {
            System.out.println(key + ": " + colorMap.get(key));
        }
    }
}
