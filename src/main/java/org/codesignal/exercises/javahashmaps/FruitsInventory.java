package org.codesignal.exercises.javahashmaps;

import java.util.HashMap;
import java.util.Map;

/*
Now that you've got a handle on managing a fruit store's inventory with HashMap in Java, let's explore stock levels relative to the average.
Your task is to determine how each fruit type's count compares to the average count in the store. Write Java code to calculate
the difference between the fruit count and the store's average, and print out whether each fruit type has more, less, or exactly the average amount of stock.
 */
public class FruitsInventory {
    public static void main(String[] args) {
        // Creating a simple inventory containing a count of different fruit types
        HashMap<String, Integer> fruitInventory = new HashMap<>();
        fruitInventory.put("apples", 30);
        fruitInventory.put("bananas", 15);
        fruitInventory.put("cherries", 45);

        // TODO: Calculate the average count of the inventory items
        int totalInventory = 0;
        double averageCount = 0.0d;
        for(Integer value: fruitInventory.values()){
            totalInventory += value;
        }
        averageCount = (double) totalInventory / fruitInventory.size();
        System.out.printf("The average is %.2f%n", averageCount);

        // Finding how much each fruit type's count differs from the average
        for (Map.Entry<String, Integer> fruit : fruitInventory.entrySet()) {
            double differenceFromAverage = fruit.getValue() - averageCount;
            String statusMessage;
            // TODO: Check if the count is greater, less, or equal to the average and set the appropriate message
            //System.out.printf("Difference from average is %.2f ", differenceFromAverage);
            if(differenceFromAverage > 0.0d){
                statusMessage = "greater";
            }else if(differenceFromAverage < 0.0d){
                statusMessage = "less";
            }else{
                statusMessage = "equal";
            }
            System.out.println(fruit.getKey() + " has " + statusMessage);
        }
    }
}
