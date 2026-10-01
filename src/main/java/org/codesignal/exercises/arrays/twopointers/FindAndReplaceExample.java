package org.codesignal.exercises.arrays.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

/*
Task Statement

Alright, our task is as follows. Suppose you have two equally long arrays, A and B, with a length varying from 1 to 1000,
with each element being a unique positive integer ranging from 1 up to 10^6.
Your challenge is to craft a Java function that identifies the closest number in array B to 2⋅B[i]2⋅B[i] for each i.
Once this number is identified, say, for the specific i, it is B[j].
We want to create an array from A[j]s in the order of increasing i.

To illustrate this, let's consider an example. We have:
int[] A = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};
int[] B = {4, 12, 3, 9, 6, 1, 5, 8, 37, 25, 100};

After running your function, the resulting array should look like this:
int[] result = {80, 100, 50, 20, 20, 60, 40, 20, 110, 90, 110};

Let's walk through the first few steps:
The first item in B is 4 at index=0. Double this number is 8. The closest number to 8 in array B is 8, which is at index=7.
The number at the same index in array A is 80, so we add 80 to our new array.

The second item in B is 12 at index=1. Double this number is 24. The closest number to 24 in B is 25, which is at index=9.
The corresponding index in A has the number 100. So, we add 100 to our new array.

The third item in B is 3 at index=2. Double this number is 6. The closest number to 6 in B is 6, which is at index=4.
The corresponding index in A has the number 50. So, we add 50 to our new array.

We continue this process for the rest of the elements in B.
 */

public class FindAndReplaceExample {
    public static int[] findAndReplace(int[] A, int[] B) {
        List<SimpleEntry<Integer, Integer>> B_sorted = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            B_sorted.add(new SimpleEntry<>(B[i], i));
        }
        B_sorted.sort(Comparator.comparingInt(SimpleEntry::getKey));

        int j = 0;  // Initialize right pointer
        int[] res = new int[A.length];  // Initialize the result array
        for (int i = 0; i < B.length; i++) {
            int target = 2 * B_sorted.get(i).getKey();  // The target is twice the current number in the sorted B
            while (j < B.length - 1 && B_sorted.get(j + 1).getKey() < target) {
                j++;  // Move the right pointer to find a number smaller than or equal to the target
            }
            if (j < B.length - 1 &&
                    Math.abs(B_sorted.get(j + 1).getKey() - target) < Math.abs(target - B_sorted.get(j).getKey())) {
                j++;  // Move the right pointer one more step if the next number is closer to the target
            }
            res[B_sorted.get(i).getValue()] = A[B_sorted.get(j).getValue()];
            // Collect the corresponding element from A at the same index as the closest number in B_sorted
        }

        return res;
    }

    public static void main(String[] args) {
        int[] A = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};
        int[] B = {4, 12, 3, 9, 6, 1, 5, 8, 37, 25, 100};
        int[] result = findAndReplace(A, B);
        System.out.println(Arrays.toString(result));
    }

}
