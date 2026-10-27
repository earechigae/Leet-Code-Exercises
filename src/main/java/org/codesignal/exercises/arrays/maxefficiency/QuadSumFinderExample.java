package org.codesignal.exercises.arrays.maxefficiency;

import java.util.*;

/*
Task Statement

Here's the task at hand: You have to write a Java function that accepts a list of distinct integers and a target sum as input.
 The aim is to identify exactly four numbers in the list that, when summed, equal this target.
 Should there be multiple sets that meet this condition, your function should return any one of them.
 If no such quad exists, the function should return an empty list.

Consider this list as an example: [5, 15, 2, 7, 8, 4]. If your target sum is 24,
a four-number set that adds up to this value could be [5, 7, 4, 8].

The input list will contain at least 4 and at most 1,000 distinct integers.
The input integers will be in the range −10^6 to 10^6.
The target sum will also be in the range of −10^6 to 10^6.
There is a time limit for the solution to evaluate within 3 seconds.

Estimating Program Evaluation Time

The simplest solution is the brute-force solution that iterates over every quadruple of numbers in the list.
Obviously, the complexity of this solution is O(N4)O(N4).

Assuming that performing an elementary operation in Java can take around 100 nanoseconds,
if we have a list of a thousand distinct integers,
the total time to perform our O(N^4) operation on our list would be around 100⋅1000^4=10^14 nanoseconds, which is over 27 hours.
This is definitely not an optimal solution.

But what if we have a solution with a time complexity of O(N^3)?
This solution can be achieved by iterating over only three elements and checking if target - element1 - element2 - element3
exists in the given list using a HashMap or HashSet. With an input list of a thousand integers,
the operation time reduces significantly to approximately 100 seconds — better, but we can still optimize!

Ultimately, if we have a solution with a complexity of O(N^2) (like the one we will build in our lesson),
the operation time on a thousand-integer list becomes really quick — approximately 1 second.

Of course, we don't always perform elementary operations, and some operations can multiply our estimated time by a constant factor.
Still, since this constant is usually low, it will generally be enough to fit within 3 seconds.

These estimated time evaluations give us an essential aspect of why crafting optimized solutions to improve time complexity is critical.
Our arranged solution (with a time complexity of O(N^2)) will be considerably faster even for larger inputs,
making it highly useful and efficient.

Solution Explanation

To effectively solve this problem using Java, we employ an optimized approach with a time complexity of O(N2)O(N2), leveraging hash maps for swift lookups.

Conceptual Breakdown:

    1. Store Pair Sums: We initialize a HashMap to keep track of all possible pairs of numbers and their sums.
        This map's keys will be these sums, and the values will be the pairs of indices that make up the sums.
    2. Finding Complement Pairs: For each pair of numbers in the array,
        calculate the difference between the target sum and the current pair’s sum.
        This difference represents the sum needed from another pair of numbers.
    3. Verify Distinct Indices: Using our map, check if there exists a pair in the array which adds up to this difference
        and ensure that none of these indices are overlapping with the initial pair.
        If such pairs exist, we return these four numbers as our result.

Why This Works:

    1. Efficiency: Using a HashMap allows for constant time complexity on average for insertion and lookup operations,
        dramatically speeding up our process compared to a brute-force solution.
    2. Scalability: Even with the maximum limit of 1000 entries, this method ensures prompt execution well within the
        acceptable limits.
 */

public class QuadSumFinderExample {
    public static List<Integer> findQuadSum(int targetSum, List<Integer> numbers) {
        int length = numbers.size();
        Map<Integer, List<int[]>> sumMap = new HashMap<>();

        // Step 2: Populate the HashMap with the sums of all pairs
        for (int i = 0; i < length - 1; ++i) {
            for (int j = i + 1; j < length; ++j) {
                int pairwiseSum = numbers.get(i) + numbers.get(j);
                sumMap.computeIfAbsent(pairwiseSum, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        // Step 3: Iterate over the sums
        for (int sum : sumMap.keySet()) {
            int complement = targetSum - sum;
            if (sumMap.containsKey(complement)) {
                List<int[]> pairs1 = sumMap.get(sum);
                List<int[]> pairs2 = sumMap.get(complement);

                for (int[] pair1 : pairs1) {
                    for (int[] pair2 : pairs2) {
                        int a = pair1[0], b = pair1[1];
                        int c = pair2[0], d = pair2[1];

                        // Ensure all indices are distinct
                        if (a != c && a != d && b != c && b != d) {
                            return Arrays.asList(numbers.get(a), numbers.get(b), numbers.get(c), numbers.get(d));
                        }
                    }
                }
            }
        }

        return Collections.emptyList();
    }
    
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 15, 2, 7, 8, 4);
        int target = 24;
        System.out.println(findQuadSum(target, numbers));
    }
}
