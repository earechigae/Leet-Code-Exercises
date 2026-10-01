package org.codesignal.exercises.arrays.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

/*
Welcome to this unit's coding session! We're going to take a deep dive into an exciting technique — the two-pointer technique.
This technique is a crucial skill for enhancing your algorithmic problem-solving abilities, especially when dealing with arrays.
In this unit, we'll apply this technique to a problem that involves an array of integers and a target value.
Let's get started!

Task Statement
Envision the problem at hand: we've been given an array of distinct integers and a target value.
The task is to find all pairs of integers from the given array that sum up to the target value using the two-pointer technique.
The function findPairs should take this array of integers and a target value as parameters.
It should return an ArrayList containing Map.Entry pairs of numbers, sorted in ascending order by the first element of each pair.
If no pairs satisfy this requirement, the function should return an empty ArrayList.

Consider, for instance, an example in which you're given an array, int[] numbers = {1, 3, 5, 2, 8, -2}, and target = 6.
In this case, the function should return [{ -2, 8 }, { 1, 5 }] because only these pairs from the input array of integers
add up to the target value.

The Naive Approach
    The naive approach to solve this problem would be to use a pair of nested loops to check each pair of numbers.

    This would have a time complexity of O(n^2) and a space complexity of O(n).
    The naive approach can be time-consuming and inefficient, particularly for large arrays.

    Comparatively, the two-pointer technique makes the problem-solving process more efficient by eliminating unnecessary
    operations (repeatedly checking pairs that can't sum to the target), thus enhancing the overall performance and
    efficiency of the solution.

The Algorithm
    We first sort the array and then apply the two-pointer technique:

    Two pointers, one at the start (left) and the other at the end (right), are initialized.

    In each iteration, we calculate the sum of the two numbers pointed to by the two respective pointers.
    If the sum is equal to the target value, the pair is added to the output list, and both pointers are moved towards the center.
    This is because we know there are no other potential pairs with the current values of numbers[left] and numbers[right]
    (since the numbers are distinct).

    When the sum is less than the target, we move the left pointer to the right (increasing the value of numbers[left]),
    and when the sum is greater than the target, we move the right pointer to the left
    (decreasing the value of numbers[right]). This process continues until the left pointer crosses the right pointer.
    At this point, all potential pairs have already been identified and added to the return list.

Complexity Analysis
    The time complexity of this solution is O(nlog⁡n)due to the sorting of the array.
    After the sorting operation, the two-pointer traversal is linear, so it doesn't affect the overall time complexity.
    Sorting is the most expensive operation in this case.

    The space complexity is O(n) because in the worst-case scenario (when all elements form a pair summing up to the target),
    the return list would require space for n/2 pairs.
 */

public class FindPairsForSumTwoPointerExample {

    public static List<Entry<Integer, Integer>> findPairs(int[] numbers, int target) {
        Arrays.sort(numbers);
        int left = 0;
        int right = numbers.length - 1;
        List<Entry<Integer, Integer>> pairs = new ArrayList<>();

        while (left < right) {
            int total = numbers[left] + numbers[right];

            if (total == target) {
                pairs.add(new SimpleEntry<>(numbers[left], numbers[right]));
                left++;
                right--;
            } else if (total < target) {
                left++;
            } else {
                right--;
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 2, 8, -2};
        List<Entry<Integer, Integer>> result = findPairs(numbers, 6);
        for (Entry<Integer, Integer> pair : result) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
