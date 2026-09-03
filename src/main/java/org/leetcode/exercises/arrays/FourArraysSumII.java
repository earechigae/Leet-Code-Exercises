package org.leetcode.exercises.arrays;

import java.util.HashMap;
import java.util.Map;
/*
454. 4Sum II

Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

* Example 1:
    Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
    Output: 2
    Explanation:
    The two tuples are:
    1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

* Example 2:
    Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
    Output: 1

Constraints:
    n == nums1.length
    n == nums2.length
    n == nums3.length
    n == nums4.length
    1 <= n <= 200
    -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */

public class FourArraysSumII {


    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        /*
        nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

         - (nums1[i] + nums2[j]) = (nums3[k] + nums4[l])
         */
        for(int i: nums1){
            for(int j: nums2){
                int sum = i + j;
                //  - (nums1[i] + nums2[j]) --> In the map key,  number of occurrences as the value in the map that increments by 1
                freqMap.put(-sum, freqMap.getOrDefault(-sum, 0) + 1);
            }
        }

        int count = 0;
        for(int k: nums3){
            for(int l: nums4){
                int sum = k + l;
                count += freqMap.getOrDefault(sum, 0);
            }
        }

        return count;
    }

    public static void main(String[] args){
        FourArraysSumII fourArraysSumII = new FourArraysSumII();

        int[] nums1 = new int[]{1,2}, nums2 = new int[]{-2,-1}, nums3 = new int[]{-1,2}, nums4 = new int[]{0,2};

        System.out.print("\nnums1: [");
        for(int i = 0; i < nums1.length; i++){
            System.out.print(nums1[i] + (i == (nums1.length -1)? "]" : ", "));
        }
        System.out.print("\nnums2: [");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(nums2[i] + (i == (nums2.length -1)? "]" : ", "));
        }
        System.out.print("\nnums3: [");
        for(int i = 0; i < nums3.length; i++){
            System.out.print(nums3[i] + (i == (nums3.length -1)? "]" : ", "));
        }
        System.out.print("\nnums4: [");
        for(int i = 0; i < nums4.length; i++){
            System.out.print(nums4[i] + (i == (nums4.length -1)? "]" : ", "));
        }
        System.out.println("\nThe number of tuples (combinations) that sum 0 are: " + fourArraysSumII.fourSumCount(nums1, nums2, nums3, nums4));

        nums1 = new int[]{0};
        nums2 = new int[]{0};
        nums3 = new int[]{0};
        nums4 = new int[]{0};

        System.out.print("\n\nnums1: [");
        for(int i = 0; i < nums1.length; i++){
            System.out.print(nums1[i] + (i == (nums1.length -1)? "]" : ", "));
        }
        System.out.print("\nnums2: [");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(nums2[i] + (i == (nums2.length -1)? "]" : ", "));
        }
        System.out.print("\nnums3: [");
        for(int i = 0; i < nums3.length; i++){
            System.out.print(nums3[i] + (i == (nums3.length -1)? "]" : ", "));
        }
        System.out.print("\nnums4: [");
        for(int i = 0; i < nums4.length; i++){
            System.out.print(nums4[i] + (i == (nums4.length -1)? "]" : ", "));
        }
        System.out.println("\nThe number of tuples (combinations) that sum 0 are: " + fourArraysSumII.fourSumCount(nums1, nums2, nums3, nums4));
    }
}
