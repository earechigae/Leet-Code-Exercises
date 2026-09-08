package org.leetcode.exercises.arrays;

import java.util.*;
/*
15. 3Sum (https://leetcode.com/problems/3sum/description/)

Given an integer array nums, return all the triplets
[nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

Example 1:
    Input: nums = [-1,0,1,2,-1,-4]
    Output: [[-1,-1,2],[-1,0,1]]
    Explanation:
    nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
    nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
    nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
    The distinct triplets are [-1,0,1] and [-1,-1,2].
    Notice that the order of the output and the order of the triplets does not matter.

Example 2:
    Input: nums = [0,1,1]
    Output: []
    Explanation: The only possible triplet does not sum up to 0.

Example 3:
    Input: nums = [0,0,0]
    Output: [[0,0,0]]
    Explanation: The only possible triplet sums up to 0.



Constraints:
    3 <= nums.length <= 3000
    -105 <= nums[i] <= 105
 */

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Long> frequencyMap = new HashMap<>();
        Set<List<Integer>> triplets = new HashSet<>();

        for(int i: nums){
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0L) + 1);
        }

        for(Integer x: frequencyMap.keySet()){
            for(Integer y: frequencyMap.keySet()){
                int z = -x - y;
                if(frequencyMap.containsKey(z)){
                    long xFreq = frequencyMap.get(x);
                    long yFreq = frequencyMap.get(y);
                    long zFreq = frequencyMap.get(z);

                    List<Integer> triplet = new ArrayList<>();

                    if(x == y && x == z){ // All 3 numbers are equal
                        if(xFreq >= 3 && yFreq >= 3 && zFreq >= 3){
                            triplet.add(x);
                            triplet.add(y);
                            triplet.add(z);
                        }
                    }else if(x == y && x != z){  //If 2 numbers are equals and 1 different
                        if(xFreq >= 2 && yFreq >= 2){
                            triplet.add(x);
                            triplet.add(y);
                            triplet.add(z);
                        }
                    }else if(x < y && y < z){ // All 3 numbers are different
                        if(xFreq >= 1 && yFreq >= 1 && zFreq >= 1){
                            triplet.add(x);
                            triplet.add(y);
                            triplet.add(z);
                        }
                    }

                    if(!triplet.isEmpty()){
                        Collections.sort(triplet);
                        triplets.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(triplets);
    }

    public static void main(String args[]){
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> triplets = null;
        int [] arr1 = new int[]{-1,0,1,2,-1,-4};
        int [] arr2 = new int[]{0,1,1};
        int [] arr3 = new int[]{0,0,0};

        System.out.print("\nFor arr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        triplets = threeSum.threeSum(arr1);
        System.out.print(". The 3 elements that sum 0 (zero) are: [[");
        if(triplets.size() <= 0){
            System.out.print("]]");
        }
        for(int j = 0; j < triplets.size(); j++){
            List<Integer> triplet = triplets.get(j);
            for(int i = 0; i < triplet.size(); i++){
                System.out.print(triplet.get(i) + (i == (triplet.size() -1)? "]" : ", "));
            }
            System.out.print((j == (triplets.size() -1)? "]" : ", ["));
        }

        System.out.print("\nFor arr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        triplets = threeSum.threeSum(arr2);
        System.out.print(". The 3 elements that sum 0 (zero) are: [[");
        if(triplets.size() <= 0){
            System.out.print("]]");
        }
        for(int j = 0; j < triplets.size(); j++){
            List<Integer> triplet = triplets.get(j);
            for(int i = 0; i < triplet.size(); i++){
                System.out.print(triplet.get(i) + (i == (triplet.size() -1)? "]" : ", "));
            }
            System.out.print((j == (triplets.size() -1)? "]" : ", ["));
        }

        System.out.print("\nFor arr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        triplets = threeSum.threeSum(arr3);
        System.out.print(". The 3 elements that sum 0 (zero) are: [[");
        if(triplets.size() <= 0){
            System.out.print("]]");
        }
        for(int j = 0; j < triplets.size(); j++){
            List<Integer> triplet = triplets.get(j);
            for(int i = 0; i < triplet.size(); i++){
                System.out.print(triplet.get(i) + (i == (triplet.size() -1)? "]" : ", "));
            }
            System.out.print((j == (triplets.size() -1)? "]" : ", ["));
        }
    }
}
