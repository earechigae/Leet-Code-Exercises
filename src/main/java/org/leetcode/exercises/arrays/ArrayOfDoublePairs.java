package org.leetcode.exercises.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
954. Array of Doubled Pairs (https://www.youtube.com/watch?v=Q0WKzdpR74o)

Given an integer array of even length arr, return true if it is possible to reorder arr such that
arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.

Example 1:
    Input: arr = [3,1,3,6]
    Output: false

Example 2:
    Input: arr = [2,1,2,6]
    Output: false

Example 3:
    Input: arr = [4,-2,2,-4]
    Output: true
    Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].

Constraints:
    2 <= arr.length <= 3 * 104
    arr.length is even.
    -10^5 <= arr[i] <= 10^5
 */

public class ArrayOfDoublePairs {
    public boolean canReorderDoubled(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Integer [] sortedArr = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++){
            sortedArr[i] = arr[i];
        }
        Arrays.sort(sortedArr, Comparator.comparingInt(Math::abs));
        for(int num : sortedArr){
            if(map.get(num) == 0){
                continue;
            }
            if(map.getOrDefault(2 * num, 0) <= 0){
                return false;
            }
            map.put(2 * num, map.get(2 * num) - 1);
            map.put(num, map.get(num) - 1);
        }

        return true;
    }

    public static void main(String args[]){
        ArrayOfDoublePairs arrayOfDoublePairs = new ArrayOfDoublePairs();
        int [] arr1 = new int[]{3,  1,  3,  6};
        int [] arr2 = new int[]{2,  1,  2,  6};
        int [] arr3 = new int[]{4, -2,  2, -4};


        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        System.out.println(" Does it have an array of double pairs? " + arrayOfDoublePairs.canReorderDoubled(arr1));

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        System.out.println(" Does it have an array of double pairs? " + arrayOfDoublePairs.canReorderDoubled(arr2));

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        System.out.println(" Does it have an array of double pairs? " + arrayOfDoublePairs.canReorderDoubled(arr3));
    }
}
