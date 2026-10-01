package org.codesignal.exercises.arrays.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an array of n positive integers, where n ranges from $1$ to $500000$, inclusive,
and each number in the array is between $1$ and $1000$.
Your task is to find the longest subarray such that the sum of its elements equals a predetermined number, k.

You have to write a Java method getLongestSubarray that will receive the list of integers, array,
and the predetermined number, k, as inputs, and will return a List representing the longest subarray whose sum of elements equals k.
The length of the LongestSubarray should be maximized.

If no such subarray is found, the function should return an empty List.
If there are multiple longest subarrays with a sum equal to k, return the one that occurs earlier.

For example, if the given array is {1, 2, 3, 4, 5} and k is 5, the method should return {2, 3} because this subarray is
the longest that sums up to the k value.

When solving this task, you can use a two-pointer technique where both pointers move towards the right.
This approach will help to solve the task using linear time complexity, achieving the expected time complexity of O(n).
Try to construct your solution keeping these key points in mind; your solution should be both efficient and effective.
 */

public class GetLongestSubArray {

    public List<Integer> getLongestSubarray(List<Integer> array, int k) {
        long biggestLength = Integer.MIN_VALUE;
        int biggestLengthLeft = 0;
        int biggestLengthRight = 0;
        List<Integer> result = new ArrayList<>();

        int left = 0;
        long valSum = 0;

        for(int right = 0; right < array.size(); right++){
            valSum += array.get(right);

            while(valSum > k){
                int comingOut = array.get(left);
                valSum -= comingOut;
                left++;
            }

            if(valSum == k){
                long length = right + 1 - left;
                if(length > biggestLength){
                    biggestLength = length;
                    biggestLengthLeft = left;
                    biggestLengthRight = right + 1;
                }
            }
        }

        if(biggestLengthLeft < biggestLengthRight){
            for(int z = biggestLengthLeft; z < biggestLengthRight; z++){
                result.add(array.get(z));
            }
        }

        return result;
    }

    public static void main(String[] args){
        GetLongestSubArray glsa = new GetLongestSubArray();
        List<List<Integer>> testCases = new ArrayList<>();
        List<Integer> kTargetSum = new ArrayList<>();
        
        testCases.add(Arrays.asList(1, 2, 3, 4, 5));
        kTargetSum.add(5);

        testCases.add(Arrays.asList(1, 2, 3, 7));
        kTargetSum.add(6);

        testCases.add(Arrays.asList(10, 2, 7, 1, 3, 4, 10));
        kTargetSum.add(10);

        testCases.add(Arrays.asList(1, 2, 3, 4, 5));
        kTargetSum.add(15);

        testCases.add(Arrays.asList(1, 1000, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1));
        kTargetSum.add(10);

        testCases.add(Arrays.asList(1000, 2000, 3000, 4000, 5000));
        kTargetSum.add(7000);

        testCases.add(Arrays.asList(1, 3, 3, 3, 1, 4, 4, 1));
        kTargetSum.add(7);

        testCases.add(Arrays.asList(5, 1, 1, 5, 1, 1, 1));
        kTargetSum.add(5);

        for(int z = 0; z < testCases.size(); z++){
            List<Integer> result = glsa.getLongestSubarray(testCases.get(z), kTargetSum.get(z));
            System.out.println("%d.- For array %s, the longest subarray with sum %d is: %s"
                    .formatted(z + 1, testCases.get(z), kTargetSum.get(z), result));
        }
    }
}
