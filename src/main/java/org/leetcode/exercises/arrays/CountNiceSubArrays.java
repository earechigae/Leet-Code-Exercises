package org.leetcode.exercises.arrays;


/*
1248. Count Number of Nice Subarrays

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
Return the number of nice sub-arrays.

Example 1:
    Input: nums = [1,1,2,1,1], k = 3
    Output: 2
    Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
    Input: nums = [2,4,6], k = 1
    Output: 0
    Explanation: There are no odd numbers in the array.

Example 3:
    Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
    Output: 16

Constraints:
    1 <= nums.length <= 50000
    1 <= nums[i] <= 10^5
    1 <= k <= nums.length
 */

public class CountNiceSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int right = 0;
        int left = 0;
        int oddNumbersCount = 0;
        int tempResult = 0;
        int result = 0;

        while(right < nums.length){
            if(nums[right] % 2 == 1){ //Odd number (Impar)
                oddNumbersCount++;
                tempResult = 0;
            }

            while(oddNumbersCount == k){
                tempResult++;
                if(nums[left] % 2 == 1){ //Odd number (Impar)
                    oddNumbersCount--;
                }

                left++;
            }

            result += tempResult;
            right++;
        }

        return result;
    }

    public static void main(String args[]){
        CountNiceSubArrays countNiceSubArrays = new CountNiceSubArrays();
        int target = 0;
        int [] arr1 = new int[]{1,1,2,1,1};
        int [] arr2 = new int[]{2,4,6};
        int [] arr3 = new int[]{2,2,2,1,2,2,1,2,2,2};

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        target = 3;
        System.out.println(" The number of sub-arrays with " + target + " odd numbers are " + countNiceSubArrays.numberOfSubarrays(arr1, target));

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        target = 1;
        System.out.println(" The number of sub-arrays with " + target + " odd numbers are " + countNiceSubArrays.numberOfSubarrays(arr2, target));

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        target = 2;
        System.out.println(" The number of sub-arrays with " + target + " odd numbers are " + countNiceSubArrays.numberOfSubarrays(arr3, target));
    }
}
