package org.leetcode.exercises.arrays.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/*
239. Sliding Window Maximum (https://www.youtube.com/watch?v=WcTMo1SHV_s)

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    Explanation:
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

Example 2:
    Input: nums = [1], k = 1
    Output: [1]

Constraints:
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length

 */

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        //Step1: Initialize the deque and result array
        //Deque variable stores INDICES, not values
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[n - k + 1];

        //Step 2: Setup deque for the first k elements
        for(int i = 0; i < k; i++){
            //Remove all smaller elements from the back
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast(); // Removes last element from the queue
            }
            deque.offerLast(i); // Adds element to the rear of the queue
        }

        //The front of the deque is the max of the first window
        result[0] = nums[deque.peekFirst()];

        //Step 3: Process the remaining elements
        for(int i = k; i < n; i++){
            //Remove the element that has slid out of the window
            if(deque.peekFirst() <= i - k){
                deque.pollFirst();
            }

            // Remove all elements smaller than the incoming element
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            // Add curents element's index
            deque.offerLast(i);

            // The front of the deque is always the max of the sliding window
            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }

    public static void main(String args[]){
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();

        int[] arr1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int[] arr2 = { 1 };
        int[] result = null;
        int slidingWindowSize = 0;

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        slidingWindowSize = 3;
        result = slidingWindowMaximum.maxSlidingWindow(arr1, slidingWindowSize);
        System.out.print("\nThe max of each sliding window of size "+ slidingWindowSize + " is: [");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == (result.length -1)? "]" : ", "));
        }

        System.out.print("\n\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        slidingWindowSize = 1;
        result = slidingWindowMaximum.maxSlidingWindow(arr2, slidingWindowSize);
        System.out.print("\nThe max of each sliding window of size "+ slidingWindowSize + " is: [");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == (result.length -1)? "]" : ", "));
        }
    }
}
