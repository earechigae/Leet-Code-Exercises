package org.codesignal.exercises.javahashmaps;

import java.util.Collections;
import java.util.HashMap;

public class DataAggregation {
    public static void main(String[] args) {
        HashMap<String, Integer> fruitBasket = new HashMap<>();
        fruitBasket.put("apples", 5);
        fruitBasket.put("bananas", 4);
        fruitBasket.put("oranges", 8);
        // A HashMap representing our fruit basket

        // Summing the values in the HashMap
        int totalFruits = 0;
        for (Integer value : fruitBasket.values()) {
            totalFruits += value;
        }

        System.out.println("The total number of fruits in the basket is: " + totalFruits);
        // It outputs: "The total number of fruits in the basket is: 17"

        // Counting the elements in the HashMap
        int countFruits = fruitBasket.size();
        System.out.println("The number of fruit types in the basket is: " + countFruits);
        // It outputs: "The number of fruit types in the basket is: 3"

        // Finding the maximum value
        int maxFruit = Collections.max(fruitBasket.values());
        System.out.println("The highest quantity of fruits is: " + maxFruit);
        // It outputs: "The highest quantity of fruits is: 8"

        // Finding the minimum value
        int minFruit = Collections.min(fruitBasket.values());
        System.out.println("The lowest quantity of fruits is: " + minFruit);
        // It outputs: "The lowest quantity of fruits is: 4"

        // Calculating the average
        double averageFruits = (double) totalFruits / fruitBasket.size();
        System.out.printf("The average number of each type of fruit in the basket is: %.2f%n", averageFruits);
        // It outputs: "The average number of each type of fruit in the basket is: 5.67"
    }
}
