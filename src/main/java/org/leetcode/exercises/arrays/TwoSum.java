package org.leetcode.exercises.arrays;

/*
1. Two Sum (https://leetcode.com/problems/two-sum/description/)

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] tuple = new int[2];
        Map<Integer, List<Integer>> positionMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){

            if(positionMap.containsKey(nums[i])){
                List <Integer> arrList = positionMap.get(nums[i]);
                arrList.add(i);
                positionMap.put(nums[i], arrList);
            }else {
                List<Integer> arrList = new ArrayList<>();
                arrList.add(i);
                positionMap.put(nums[i], arrList);
            }
        }

        for(Integer x: positionMap.keySet()){
            Integer num = target - x;
            if(positionMap.containsKey(num)){
                tuple[0] = positionMap.get(x).get(0);

                if(positionMap.get(num).size() == 1) {
                    tuple[1] = positionMap.get(num).get(0);
                }else{
                    tuple[1] = positionMap.get(num).get(1);
                }
                break;
            }
        }

        return tuple;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] arr1 = new int[]{2, 7, 11, 15};
        int[] arr2 = new int[]{3, 2, 4};
        int[] arr3 = new int[]{3, 3};
        int[] result = null;
        int target = 0;

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        target = 9;
        System.out.print(" -> The positions in the array that sum " + target + " are [" );
        result = twoSum.twoSum(arr1, target);
        for(int j = 0; j < result.length; j++){
            System.out.print(result[j] + (j == (result.length -1)? "]" : ", "));
        }
        System.out.print("\n");

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        target = 6;
        System.out.print(" -> The positions in the array that sum " + target + " are [" );
        result = twoSum.twoSum(arr2, target);
        for(int j = 0; j < result.length; j++){
            System.out.print(result[j] + (j == (result.length -1)? "]" : ", "));
        }
        System.out.print("\n");

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        target = 6;
        System.out.print(" -> The positions in the array that sum " + target + " are [" );
        result = twoSum.twoSum(arr3, target);
        for(int j = 0; j < result.length; j++){
            System.out.print(result[j] + (j == (result.length -1)? "]" : ", "));
        }
        System.out.print("\n");

    }
}


