package org.codesignal.exercises.optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Deep within the kingdom of Million Numbers, a challenge was set before the esteemed Java developer.
This kingdom was a land of diversity, filled with n elements, each an integer between $-10^6$ and $10^6$.
The count of these elements was no small number either - ranging anywhere from $1$ to a whopping $10^6$.

The Java developer was handed a scroll that represented these elements as a list of integers.
The Java developer's task? To delve deep into this pool of numbers and find the smallest absolute difference between any pair of elements.

In the kingdom of Million Numbers, the absolute difference was measured as |a - b|, where a and b signified any two elements from the list.

The Java developer was asked to weave a magical function named public int solution(List<Integer> nums).
This Java function would take in the list of integers 'nums', representative of all elements in the kingdom;
it would parse through the sea of numbers and elegantly return the smallest absolute difference among all possible pairs.

Although the kingdom was grand in scale, efficiency was revered.
The Java developer's magic had to be potent and swift - an optimized spell capable of working at a time complexity of $O(n \log n)$ or better,
to ensure the quickest resolution to the kingdom's interesting challenge.

Notes:|
    If n = 1, the minimum absolute difference is 0, since there is only one number, leaving no other number to pair it with.
    It is guaranteed that the list nums contains no duplicate numbers.

 */

public class SmallestAbsoluteDifference {
    public int solution(List<Integer> nums) {
        Collections.sort(nums);
        int minimum = Integer.MAX_VALUE;

        if(nums.size() >= 2){
            for(int i = 0; i < nums.size() - 1; i++){
                minimum = Math.min(minimum, Math.abs(nums.get(i + 1) - nums.get(i)));
            }
            return minimum;
        }

        return 0;
    }

    public static void main(String[] args){
        List<List<Integer>> testCases = new ArrayList<>();

        testCases.add(Arrays.asList(1, 2, 3, 4, 5));
        testCases.add(Arrays.asList(1));
        testCases.add(Arrays.asList(3, 1));
        testCases.add(Arrays.asList(-1, 0, 1));
        testCases.add(Arrays.asList(-100));
        testCases.add(Arrays.asList(1, 10, 36, 60));
        testCases.add(Arrays.asList(-10, -1, 0, 1, 15));
        testCases.add(Arrays.asList(69, -98, 34, 21, -19));
        testCases.add(Arrays.asList(0, 0));

        SmallestAbsoluteDifference solution = new SmallestAbsoluteDifference();

        for(int i = 0; i < testCases.size(); i++){
            System.out.printf("%d.- For array %s the minimum difference is %d%n", i + 1, testCases.get(i), solution.solution(testCases.get(i)));
        }
    }
}
