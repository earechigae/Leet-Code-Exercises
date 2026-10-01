package org.codesignal.exercises.arrays.twopointers;

import java.util.*;

/*
You have been offered n different types of chocolates, where n is an integer that can range anywhere from $1$ to $2 \cdot 10^5$, inclusive.
For each type of chocolate, you are given its sweetness level, an integer ranging from $-10^5$ to $10^5$, inclusive.
Your task is to use the two-pointer algorithm technique to identify all the pairs of chocolate types whose total sweetness level equals zero.

As orders might vary, it has been decided that the sweetness level in each pair should be presented in decreasing order.
In the case of multiple pairs, they should overall be sorted in increasing order by the first element in the pair.

Write a function findChocPairs(int[] sweetness) that will take an array of n integers, representing the sweetness levels of the chocolates,
and should return an array of pairs. Each pair should contain the pair of chocolates whose sweetness levels sum up to zero,
sorted in decreasing order of sweetness levels.

For example, if the given array is {4, 3, -5, 5, -3, -4}, the function should return {(3, -3), (4, -4), (5, -5)}.

In this instance, the pairs of chocolate types whose total sweetness levels sum up to zero are: 5 and -5, 4 and -4, and 3 and -3.
After sorting each pair in decreasing order and sorting all pairs by the first element in each pair,
the result is {(3, -3), (4, -4), (5, -5)}.

As an edge case, if there are no pairs of chocolates whose sweetness level totals zero, your function should return an empty array.

*/

public class FindChocolatePairs {

    public List<int[]> findChocPairs(int[] sweetness) {
        int left = 0;
        int right = sweetness.length - 1;
        List<int []> result = new ArrayList<>();
        Comparator<int[]> pairComparator = Comparator.comparingInt(a -> a[0]);

        Arrays.sort(sweetness);
        while(left < right){
            int sum = sweetness[left] + sweetness[right];
            if(sum == 0){
                int[] foundPair = new int[]{sweetness[right], sweetness[left]};
                result.add(foundPair);
                left++;
                right--;
            } else if(sum < 0){
                left++;
            } else {
                right--;
            }
        }

        Collections.sort(result, pairComparator);
        return result;
    }

    /*
    public List<int[]> findChocPairsNotSoFast(int[] sweetness) {
        int left = 0;
        int right = sweetness.length - 1;
        List<int []> result = new ArrayList<>();

        Arrays.sort(sweetness);
        while(left < right){
            int sum = sweetness[left] + sweetness[right];
            if(sum == 0){
                int[] foundPair = {sweetness[right], sweetness[left]};
                //0. Define a comparator
                Comparator<int[]> pairComparator = Comparator.comparingInt(a -> a[0]);

                // 1. Find the position using binary search
                int index = Collections.binarySearch(result, foundPair, pairComparator);

                // 2. If the element is not found, binarySearch returns (-(insertion point) - 1)
                if (index < 0) {
                    index = -index - 1;
                }

                // 3. Insert the element at the calculated index
                result.add(index, new int[]{sweetness[right], sweetness[left]});
                left++;
                right--;
            } else if(sum < 0){
                left++;
            } else {
                right--;
            }
        }

        return result;
    }
    */

    public String toString(List<int[]> list){
        StringBuilder sb = new StringBuilder("");
        if(list.isEmpty()){
            sb.append("[]");
        }
        for(int[] element : list){
            sb.append("[").append(element[0]).append(",").append(element[1]).append("]");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FindChocolatePairs fcp = new FindChocolatePairs();
        List<int[]> testCasesPairs = new ArrayList<>();
        List<List<int[]>> expectedResults = new ArrayList<>();

        testCasesPairs.add(new int[]{4, 3, -5, 5, -3, -4});
        expectedResults.add(Arrays.asList(new int[]{3, -3}, new int[]{4, -4}, new int[]{5, -5}));

        testCasesPairs.add(new int[]{3, 2, -3, -2});
        expectedResults.add(Arrays.asList(new int[]{2, -2}, new int[]{3, -3}));

        testCasesPairs.add(new int[]{5, -5, 100});
        expectedResults.add(Arrays.asList(new int[]{5, -5}));

        testCasesPairs.add(new int[]{1});
        expectedResults.add(new ArrayList<>());

        testCasesPairs.add(new int[]{1, -1});
        expectedResults.add(Arrays.asList(new int[]{1, -1}));

        testCasesPairs.add(new int[]{2, 3, 4});
        expectedResults.add(new ArrayList<>());

        testCasesPairs.add(new int[]{-100, 100, -50, 40, 10});
        expectedResults.add(Arrays.asList(new int[]{100, -100}));

        for(int z = 0; z < testCasesPairs.size(); z++){
            List<int[]> actualResult = fcp.findChocPairs(testCasesPairs.get(z));
            String expectedResultsStr = fcp.toString(expectedResults.get(z));
            String calculatedResult = fcp.toString(actualResult);

            System.out.printf("%d.- Input array: %s --> Pairs in the array that sum 0 --> Expected %s, Calculated %s%n",
                    (z + 1), Arrays.toString(testCasesPairs.get(z)), expectedResultsStr, calculatedResult);
        }
    }

}
