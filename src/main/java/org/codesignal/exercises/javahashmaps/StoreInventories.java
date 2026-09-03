package org.codesignal.exercises.javahashmaps;

import java.util.HashMap;

public class StoreInventories {
    public static void main(String[] args) {
        HashMap<String, Integer> storeInventory = new HashMap<>();
        storeInventory.put("Apples", 100);
        storeInventory.put("Bananas", 80);
        storeInventory.put("Oranges", 90);  // Initializing an inventory

        storeInventory.put("Pears", 50);  // Adding a new product to inventory and setting its quantity

        storeInventory.put("Apples", storeInventory.get("Apples") + 20);  // Updating the number of apples in inventory

        String prod = "Apples";  // A product to be checked
        System.out.println("Total " + prod + " in stock: " + storeInventory.get(prod));

        // Check if a product is in stock
        prod = "Mangoes";
        if (storeInventory.containsKey(prod)) {  // If mangoes exist in inventory
            System.out.println(prod + " are in stock.");
        } else {  // If mangoes don't exist in inventory
            System.out.println(prod + " are out of stock.");
        }
    }
}
