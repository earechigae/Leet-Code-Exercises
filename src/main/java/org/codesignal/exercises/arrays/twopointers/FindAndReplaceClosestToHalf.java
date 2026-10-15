package org.codesignal.exercises.arrays.twopointers;

/*
You are given two arrays, X and Y, each with a length between 1 and 500000, inclusive.
Each array contains unique positive integers from 1 up to 100000. Additionally, Y should be at least as long as X to ensure
all indices in X correspond with an index in Y.

Your task is to write a Java function that identifies, for each number in the Y array, the number in X that is closest
to half of that number. The closest number can be less than, greater than, or equal to half of the Y[i] value.
When identifying the closest number in X, if multiple numbers are equally close to half of the Y[i] value,
select the first occurring one in X.

Once the numbers in X that are closest and meet the half-of-Y[i] condition are found, one for each Y[i],
get their corresponding numbers in Y, maintaining the original ordering.

In other words, when, say for Y[i], the closest number to Y[i] / 2 in X is X[j], add Y[j] to a new array.
Repeat this process, following the original Y order, for all elements in Y.

The final function should return this newly created array with the corresponding Y[j] values for each closest
half-of-Y[i] number in X. It is essential to ensure that in case of multiple equally close numbers in X,
the one with the smallest index is chosen.

Here is an illustrative example:

int[] X = {4, 12, 9, 20};
int[] Y = {10, 20, 40, 50};

For Y[0] (=10), the half is 5. The function identifies X[0] (=4) as the closest value. As X[0] has been selected,
it includes the corresponding Y[0] value (10) in the new array.

Next, for Y[1] (=20), the half is 10. The function identifies X[2] (=9) as the closest value. As X[2] has been selected,
it includes the corresponding Y[2] value (40) in the new array. Now, the new array is {10, 40}.

For Y[2] (=40), the half is 20. The function identifies X[3] (=20), which matches exactly the half. As X[3] has been selected,
it includes the corresponding Y[3] value (50) in the new array. Now, the new array is {10, 40, 50}.

Finally, for Y[3] (=50), the half is 25. The function identifies X[3] (=20) as the closest value.
As X[3] has been chosen, it includes the corresponding Y[3] value (50) in the new array.
The final new array is {10, 40, 50, 50} and is the output of the function for this given input.

The solution should have an overall time complexity of O(NlogN), where N is max(len(X), len(Y)).
 */

import java.util.*;

public class FindAndReplaceClosestToHalf {

    public int[] solution(int[] X, int[] Y) {
        int[] res = new int[Y.length];
        List<AbstractMap.SimpleEntry<Integer, Integer>> X_sorted = new ArrayList<>();
        List<AbstractMap.SimpleEntry<Integer, Integer>> Y_sorted = new ArrayList<>();

        for (int i = 0; i < X.length; i++) {
            X_sorted.add(new AbstractMap.SimpleEntry<>(X[i], i));
        }
        X_sorted.sort(Comparator.comparingInt(AbstractMap.SimpleEntry::getKey));

        for (int i = 0; i < Y.length; i++) {
            Y_sorted.add(new AbstractMap.SimpleEntry<>(Y[i], i));
        }
        Y_sorted.sort(Comparator.comparingInt(AbstractMap.SimpleEntry::getKey));

        int j = 0;  // Initialize right pointer
        for (int i = 0; i < Y_sorted.size(); i++) {
            /*
                Avoid integer division for the half. Y[i] / 2 rounds down, so for odd values it can hide a real tie.
                For example, if Y[i] == 5, values 2 and 3 are equally close to 2.5, but comparing to 5 / 2 == 2 makes 2 look closer.

                Use doubled distances to compare against the exact half,
                and use the original indices to break ties.
            */
            int y = Y_sorted.get(i).getKey();

            while (j < X_sorted.size() - 1
                    && 2L * X_sorted.get(j + 1).getKey() < y) {
                j++;
            }

            if (j < X_sorted.size() - 1) {
                // The 2L keeps the comparison exact and avoids integer-division rounding.
                long leftDiff = Math.abs(2L * X_sorted.get(j).getKey() - y);
                long rightDiff = Math.abs(2L * X_sorted.get(j + 1).getKey() - y);

                if (rightDiff < leftDiff
                        || (rightDiff == leftDiff
                        && X_sorted.get(j + 1).getValue() < X_sorted.get(j).getValue())) {
                    j++;
                }
            }

            res[Y_sorted.get(i).getValue()] = Y[X_sorted.get(j).getValue()];
            // Collect the corresponding element from Y at the same index as the closest number in X_sorted
        }

        return res;
    }

    public static void main(String[] args){
        FindAndReplaceClosestToHalf solution = new FindAndReplaceClosestToHalf();

        List<int[]> xInputList = new ArrayList<>();
        List<int[]> yInputList = new ArrayList<>();
        List<int[]> expectedResultsList = new ArrayList<>();

        xInputList.add(new int[]{4, 12, 9, 20});
        yInputList.add(new int[]{10, 20, 40, 50});
        expectedResultsList.add(new int[]{10, 40, 50, 50});

        xInputList.add(new int[]{4, 12, 3, 9, 6, 1, 5, 8, 37, 25, 100});
        yInputList.add(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 112, 113, 114, 115, 116});
        expectedResultsList.add(new int[]{70, 40, 20, 100, 100, 100, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90});

        xInputList.add(new int[]{1, 5, 3, 4, 2});
        yInputList.add(new int[]{1, 2, 3, 4, 5});
        expectedResultsList.add(new int[]{1, 1, 1, 5, 3});

        xInputList.add(new int[]{10, 24, 30, 40, 50});
        yInputList.add(new int[]{18, 19, 20, 21, 22});
        expectedResultsList.add(new int[]{18, 18, 18, 18, 18});

        xInputList.add(new int[]{91, 85, 75, 60, 120, 150, 125});
        yInputList.add(new int[]{24, 48, 150, 210, 76, 98, 45, 97, 77, 107});
        expectedResultsList.add(new int[]{210, 210, 150, 24, 210, 210, 210, 210, 210, 210});

        xInputList.add(new int[]{1, 2, 3, 4, 5});
        yInputList.add(new int[]{6, 7, 8, 9, 10});
        expectedResultsList.add(new int[]{8, 8, 9, 9, 10});

        xInputList.add(new int[]{100, 200, 130, 170, 160, 180});
        yInputList.add(new int[]{1000, 2000, 500, 3000, 1500, 3500});
        expectedResultsList.add(new int[]{2000, 2000, 2000, 2000, 2000, 2000});

        xInputList.add(new int[]{24, 43, 77, 89, 23, 456, 778, 123, 55, 33});
        yInputList.add(new int[]{140, 230, 340, 450, 250, 289, 49, 364, 221, 145, 340, 456, 445, 456, 567});
        expectedResultsList.add(new int[]{340, 364, 364, 364, 364, 364, 140, 364, 364, 340, 364, 364, 364, 364, 364});

        for(int z = 0; z < xInputList.size(); z++){
            int[] result = solution.solution(xInputList.get(z), yInputList.get(z));
            System.out.println("* Test case " + (z + 1) + ": ");
            System.out.println("\tInput array X: " + Arrays.toString(xInputList.get(z)));
            System.out.println("\tInput array Y: " + Arrays.toString(yInputList.get(z)));
            System.out.println("\tExpected result: " + Arrays.toString(expectedResultsList.get(z)));
            System.out.println("\tActual result:   " + Arrays.toString(result));
            System.out.println();
        }
    }
}
