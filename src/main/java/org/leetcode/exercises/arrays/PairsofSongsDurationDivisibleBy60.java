package org.leetcode.exercises.arrays;

import java.util.*;

/*
1010. Pairs of Songs With Total Durations Divisible by 60

You are given a list of songs where the ith song has a duration of time[i] seconds.
Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally,
we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:
    Input: time = [30,20,150,100,40]
    Output: 3
    Explanation: Three pairs have a total duration divisible by 60:
    (time[0] = 30, time[2] = 150): total duration 180
    (time[1] = 20, time[3] = 100): total duration 120
    (time[1] = 20, time[4] = 40): total duration 60

Example 2:
    Input: time = [60,60,60]
    Output: 3
    Explanation: All three pairs have a total duration of 120, which is divisible by 60.

Constraints:
    1 <= time.length <= 6 * 104
    1 <= time[i] <= 500
 */

public class PairsofSongsDurationDivisibleBy60 {

    // O(n^2)
    public int numPairsDivisibleBy60Slow(int[] time) {
        int count = 0;

        for (int i = 0; i < time.length; i++) {
            for (int j = 0; j < time.length; j++) {
                if ((i != j) && (((time[i] + time[j]) % 60) == 0)) {
                    count++;
                }
            }
        }

        return count/2;
    }

    // O(n^2)
    public int numPairsDivisibleBy60LessSlow(int[] time) {
        int count = 0;
        Set<String> tuples = new HashSet<>();

        for(int i = 0; i < time.length; i++){
            for(int j = 0; j < time.length; j++){
                if((i != j)) {
                    if (tuples.contains(i + "," + j) || tuples.contains(j + "," + i)) {
                        continue;
                    } else if ((((time[i] + time[j]) % 60) == 0)) {
                        tuples.add(i + "," + j);
                        tuples.add(j + "," + i);
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // O(n)
    public int numPairsDivisibleBy60(int[] time) {
        int[] reminderMap = new int[60];
        int count = 0;

        for(int i = 0; i < time.length; i++){
            int x = time[i];
            int reminder = x % 60;
            if(reminder == 0) count += reminderMap[0];
            else count += reminderMap[60 - reminder];
            reminderMap[reminder]++;
        }

        return count;
    }

    public static void main(String args[]){
        PairsofSongsDurationDivisibleBy60 posddb60 = new PairsofSongsDurationDivisibleBy60();
        int[] time = new int[]{30, 20, 150, 100, 40};


        System.out.print("\nTime array: [");
        for(int i = 0; i < time.length; i++){
            System.out.print(time[i] + (i == (time.length -1)? "]" : ", "));
        }
        System.out.print(". The number of pair of songs with total duration divisible by 60 are: " + posddb60.numPairsDivisibleBy60(time));


        time = null;
        time = new int[]{60, 60, 60};
        System.out.print("\nTime array: [");
        for(int i = 0; i < time.length; i++){
            System.out.print(time[i] + (i == (time.length -1)? "]" : ", "));
        }
        System.out.print(". The number of pair of songs with total duration divisible by 60 are: " + posddb60.numPairsDivisibleBy60(time));
    }

}
