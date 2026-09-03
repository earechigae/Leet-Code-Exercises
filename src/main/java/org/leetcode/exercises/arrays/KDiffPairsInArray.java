package org.leetcode.exercises.arrays;

/*
532. K-diff Pairs in an Array

Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
    0 <= i, j < nums.length
    i != j
    |nums[i] - nums[j]| == k

Notice that |val| denotes the absolute value of val.

Example 1:
    Input: nums = [3,1,4,1,5], k = 2
    Output: 2
    Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
    Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:
    Input: nums = [1,2,3,4,5], k = 1
    Output: 4
    Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:
    Input: nums = [1,3,1,5,4], k = 0
    Output: 1
    Explanation: There is one 0-diff pair in the array, (1, 1).

Constraints:
    1 <= nums.length <= 104
    -107 <= nums[i] <= 107
    0 <= k <= 107
 */

import java.util.HashMap;
import java.util.Map;

public class KDiffPairsInArray {
    public int findPairs(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        for(int key : freqMap.keySet()){
            if(k != 0){
                int sum = key + k;
                if(freqMap.containsKey(sum)) count++;
            }else{ //k == 0
                if(freqMap.get(key) >= 2) count++;
            }
        }

        return count;
    }

    public static void main(String args[]){
        KDiffPairsInArray kDiffPairsInArray = new KDiffPairsInArray();
        int k = 0;
        int [] arr1 = new int[]{3,1,4,1,5};
        int [] arr2 = new int[]{1,2,3,4,5};
        int [] arr3 = new int[]{1,3,1,5,4};

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        k = 2;
        System.out.println(". The number of unique pairs where |nums[i] - nums[j]| ==  " + k + " are " + kDiffPairsInArray.findPairs(arr1, k));

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        k = 1;
        System.out.println(". The number of unique pairs where |nums[i] - nums[j]| ==  " + k + " are " + kDiffPairsInArray.findPairs(arr2, k));

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        k = 0;
        System.out.println(". The number of unique pairs where |nums[i] - nums[j]| ==  " + k + " are " + kDiffPairsInArray.findPairs(arr3, k));
    }
}
