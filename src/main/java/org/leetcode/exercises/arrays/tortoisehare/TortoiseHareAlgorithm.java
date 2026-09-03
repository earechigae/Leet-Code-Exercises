package org.leetcode.exercises.arrays.tortoisehare;

/*
287. Find the Duplicate Number

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and using only constant extra space.

Example 1:
    Input: nums = [1,3,4,2,2]
    Output: 2

Example 2:
    Input: nums = [3,1,3,4,2]
    Output: 3

Example 3:
    Input: nums = [3,3,3,3,3]
    Output: 3

Constraints:
    * 1 <= n <= 105
    * nums.length == n + 1
    * 1 <= nums[i] <= n
    * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 */

public class TortoiseHareAlgorithm {
    public static int findDuplicate(int[] nums) {
        // Phase 1: Locate the intersection point inside the cycle.
        // In other words, detect a cycle
        int tortoise = nums[0]; // Slow pointer
        int hare = nums[0]; // Fast pointer

        do {
            tortoise = nums[tortoise];          // Moves 1 step
            hare = nums[nums[hare]];            // Moves 2 steps
        } while (tortoise != hare);

        // Phase 2: Find the entrance to the cycle (the duplicate number)
        tortoise = nums[0];                     // Reset tortoise to start

        while (tortoise != hare) {
            tortoise = nums[tortoise];          // Both move 1 step now
            hare = nums[hare];
        }

        return tortoise;                        // Or return hare; both are the duplicate
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 2, 2};
        int[] arr2 = {3, 1, 3, 4, 2};
        int[] arr3 = {3, 3, 3, 3, 3};

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        System.out.println(". The duplicate number is: " + TortoiseHareAlgorithm.findDuplicate(arr1));

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        System.out.println(". The duplicate number is: " + TortoiseHareAlgorithm.findDuplicate(arr2));

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        System.out.println(". The duplicate number is: " + TortoiseHareAlgorithm.findDuplicate(arr3));
    }
}
