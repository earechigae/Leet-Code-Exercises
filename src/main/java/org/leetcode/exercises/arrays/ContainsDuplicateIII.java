package org.leetcode.exercises.arrays;

/*
220. Contains Duplicate III (https://www.youtube.com/watch?v=Cu7g9ovYHNI)

You are given an integer array nums and two integers indexDiff and valueDiff.
Find a pair of indices (i, j) such that:

* i != j,
* abs(i - j) <= indexDiff.
* abs(nums[i] - nums[j]) <= valueDiff, and

Return true if such pair exists or false otherwise.

Example 1:
    Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
    Output: true
    Explanation: We can choose (i, j) = (0, 3).
    We satisfy the three conditions:
    i != j --> 0 != 3
    abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
    abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0

Example 2:
    Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
    Output: false
    Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.

Constraints:

    2 <= nums.length <= 105
    -109 <= nums[i] <= 109
    1 <= indexDiff <= nums.length
    0 <= valueDiff <= 109

 */

import java.util.TreeSet;

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff /*k*/, int valueDiff /*t*/) {
        if(nums == null || nums.length < 2 || indexDiff < 1) return false;
        TreeSet<Long> set = new TreeSet<>();  //The tree set is a self balancing binary tree. Better efficiency O(log(n))

        for(int i = 0; i < nums.length; i++){
            long l = (long) nums[i];

            // Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
            Long floor = set.floor(l);  // Think of it as the Max value less than or equal to l

            // Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
            Long ceil = set.ceiling(l); // Think of it as the Min value greater than or equal to l

            if((floor != null && l - floor <= valueDiff) || (ceil != null && ceil - l <= valueDiff) ) {
                return true;
            }

            set.add(l);

            if(i >= indexDiff)
                set.remove((long)nums[i - indexDiff]);

        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII cd = new ContainsDuplicateIII();
        int[] arr1 = {1, 2, 3, 1};
        int[] arr2 = {1, 5, 9, 1, 5, 9};
        int indexDiff = 3, valueDiff = 0;

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        indexDiff = 3;
        valueDiff = 0;
        System.out.println(" -> Are there any pair of indices (i, j) such that \n" +
                "* i != j,\n" +
                "* abs(i - j) <= " + indexDiff +".\n" +
                "* abs(nums[i] - nums[j]) <= "+ valueDiff +" ? -----> " + cd.containsNearbyAlmostDuplicate(arr1, indexDiff, valueDiff));

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        indexDiff = 2;
        valueDiff = 3;
        System.out.println(" -> Are there any pair of indices (i, j) such that \n" +
                "* i != j,\n" +
                "* abs(i - j) <= " + indexDiff +".\n" +
                "* abs(nums[i] - nums[j]) <= "+ valueDiff +" ? -----> " + cd.containsNearbyAlmostDuplicate(arr2, indexDiff, valueDiff));
    }
}
