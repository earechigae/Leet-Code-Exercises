package org.codesignal.exercises.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Containers {
    private List<Integer> sortedList = null;
    public Containers() {
        // write your code here
        sortedList = new ArrayList<>();
    }

    public void add(int value) {
        // 1. Find the position using binary search
        int index = Collections.binarySearch(sortedList, value);

        // 2. If the element is not found, binarySearch returns (-(insertion point) - 1)
        if (index < 0) {
            index = -index - 1;
        }

        // 3. Insert the element at the calculated index
        sortedList.add(index, value);
    }


    public boolean delete(int value) {
        boolean deleted = false;

        if(sortedList.contains(Integer.valueOf(value))){
            sortedList.remove(Integer.valueOf(value));
            deleted = true;
        }

        return deleted;
    }

    public int getMedian() {
        int position = 0, value = 0;
        if(sortedList.isEmpty()){
            throw new RuntimeException("The list is empty");
        }
        position = (sortedList.size() % 2 == 0)? sortedList.size()/2 - 1 :  sortedList.size() / 2;
        value = sortedList.get(position);
        return value;
    }

    public static void main(String args[]){
        Containers containers = new Containers();
        containers.add(1);
        containers.add(2);
        containers.add(5);
        containers.add(4);
        System.out.println("The median is: " + containers.getMedian()); // Output: 2
    }
}
