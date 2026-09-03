package org.leetcode.exercises.arrays;

/*
128. Longest Consecutive Sequence

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.

Example 1:
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9

Example 3:
    Input: nums = [1,0,1,2]
    Output: 3

Constraints:
    0 <= nums.length <= 105
    -10^9 <= nums[i] <= 10^9
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.IntStream;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        //Converting it to a Set is time-consuming in Java. Option 1
        Set<Integer> numsSet = new HashSet<>(Arrays.asList(IntStream.of(nums).boxed().toArray(Integer[]::new)));

        //Converting it to a Set is time-consuming in Java. Option 2
        /*
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }
        */

        for (int num : nums) {
            //Identifying the start of a sequence
            if (!numsSet.contains(num - 1)) {
                int length = 0;
                while (numsSet.contains(num + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }

        /*
        for (int i = 0; i < nums.length; i++) {
            //Identifying the start of a sequence
            if (!numsSet.contains(nums[i] - 1)) {
                int length = 0;
                while (numsSet.contains(nums[i] + length)) {
                    length++;
                    i = (i + 1 < nums.length)? i++ : i;
                }
                longest = Math.max(longest, length);
            }
        }*/

        return longest;
    }

    public int longestConsecutiveFastest(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - 1)) {
                map.put(nums[i], false); //The number is not the start of sequence
            } else {
                map.put(nums[i], true); //Identifies the start of sequence
            }
            if (map.containsKey(nums[i] + 1)) {
                map.put(nums[i] + 1, false); //The number is not the start of sequence
            }

        }
        int ans = 0;
        for (int key : map.keySet()) {
            if (map.get(key)) { //Checks if the number is the beginning of a sequence
                int count = 0;
                while (map.containsKey(key)) {
                    count++;
                    key++;
                }
                ans = Math.max(ans, count);
            }

        }
        return ans;
    }

    public static void main(String args[]){
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();

        int[] array1 = {100, 4, 200, 1, 3, 2};
        int[] array2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] array3 = {1, 0, 1, 2};

        System.out.print("\n* The longest consecutive sequence of array1 [");
        for(int i = 0; i < array1.length; i++){
            System.out.print(array1[i] + (i == (array1.length -1)? "]" : ", "));
        }
        System.out.print(" is: " + longestConsecutiveSequence.longestConsecutive(array1));

        System.out.print("\n* The longest consecutive sequence of array2 [");
        for(int i = 0; i < array2.length; i++){
            System.out.print(array2[i] + (i == (array2.length -1)? "]" : ", "));
        }
        System.out.print(" is: " + longestConsecutiveSequence.longestConsecutiveFastest(array2));

        System.out.print("\n* The longest consecutive sequence of array3 [");
        for(int i = 0; i < array3.length; i++){
            System.out.print(array3[i] + (i == (array3.length -1)? "]" : ", "));
        }
        System.out.print(" is: " + longestConsecutiveSequence.longestConsecutive(array3));
    }
}
