package org.leetcode.exercises.arrays.slidingwindow;

/*
209. Minimum Size Subarray Sum (https://leetcode.com/problems/minimum-size-subarray-sum/description/)


Given an array of positive integers nums and a positive integer target, return the minimal length of a whose sum is greater than or equal to target.
If there is no such subarray, return 0 instead.

Example 1:
    Input: target = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
    Input: target = 4, nums = [1,4,4]
    Output: 1

Example 3:
    Input: target = 11, nums = [1,1,1,1,1,1,1,1]
    Output: 0

Constraints:
    1 <= target <= 109
    1 <= nums.length <= 105
    1 <= nums[i] <= 104

Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;

        int left = 0;
        int valSum = 0;

        for(int i = 0; i < nums.length; i++){
            valSum += nums[i];

            while(valSum >= target){
                result = Math.min(result, i+1 -left);
                valSum -= nums[left];
                left++;
            }
        }

        return (result != Integer.MAX_VALUE)? result : 0;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
        int[] arr1 = {2,3,1,2,4,3};
        int[] arr2 = {1,4,4};
        int[] arr3 = {1,1,1,1,1,1,1,1};
        int target = 0, minLength = 0;

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        target = 7;
        minLength = minimumSizeSubarraySum.minSubArrayLen(target, arr1);
        System.out.print(" -> The the minimal length of a whose sum is greater than or equal to " + target + " is " + minLength);

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        target = 4;
        minLength = minimumSizeSubarraySum.minSubArrayLen(target, arr2);
        System.out.print(" -> The the minimal length of a whose sum is greater than or equal to " + target + " is " + minLength);

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        target = 11;
        minLength = minimumSizeSubarraySum.minSubArrayLen(target, arr3);
        System.out.print(" -> The the minimal length of a whose sum is greater than or equal to " + target + " is " + minLength);
    }
}
