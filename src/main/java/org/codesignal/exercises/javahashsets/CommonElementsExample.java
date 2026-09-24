package org.codesignal.exercises.javahashsets;

import java.util.*;

/*
The task for this unit is to devise a Java function that accepts two lists containing unique integers and returns another
list containing the elements common to both input lists. This task provides an intriguing perspective on deriving
similarities between two data sequences, a scenario commonly encountered in data comparisons and analytics.

Introduction to HashSet Solution

Since efficiency is a concern in our previous approach, we want to reduce the time complexity by minimizing the number
of operations we perform to reach a solution. The Java Collections Framework HashSet comes to our rescue here.

HashSet internally uses hash tables, which allows operations like insertion, removal, and search to be performed in
average constant time, i.e., O(1)O(1). This provides a significant performance boost compared to the nested loop approach.
Our overall time complexity will be reduced to O(n+m)O(n+m) for traversing both lists and storing their elements into HashSets.
 */

public class CommonElementsExample {
    public static List<Integer> commonElements(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);

        List<Integer> common = new ArrayList<>();
        for (Integer num : list2) {
            if (set1.contains(num)) {
                common.add(num);
            }
        }
        return common;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34);
        List<Integer> list2 = Arrays.asList(2, 3, 5, 7, 13, 21, 31);

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        List<Integer> result = commonElements(list1, list2);
        System.out.println("Common elements: " + result);  // Prints: [2, 3, 5, 13, 21]


        Set<Integer> resultSet = new HashSet<>(result);
        resultSet.add(20); // O(1)
        resultSet.add(31);  // O(1) but this will have no effect since 31 is already in the s
        System.out.println("After adding a couple of elements in the set: " + resultSet);

        resultSet.remove(3); // O(1)
        resultSet.remove(35); // O(1) but this will have no effect since 35 is not in the se
        System.out.println("After removing a couple elements in the set: " + resultSet);

        System.out.printf("Does the set contains the following values [%d, %d]? [%b, %b]%n", 2, 35, resultSet.contains(2), resultSet.contains(35));
        System.out.println("The result set size is " + resultSet.size());

        resultSet.clear();
        System.out.println("After clearing the result set. The result set size is " + resultSet.size());

    }
}
