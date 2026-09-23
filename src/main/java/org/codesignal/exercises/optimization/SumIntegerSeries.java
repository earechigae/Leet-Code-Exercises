package org.codesignal.exercises.optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
In the realm of Integerland, there exist two towns named Town A and Town B. The towns are positioned along a straight road,
at points corresponding to different integers. The fascinating fact about Integerland is that the locations of the towns could change.
They can take any integer value between $1$ and $10^9$.

Your mission, should you choose to accept it, is to create a very special kind of map – a function or method, so to speak –
that can calculate the total value of all the points (integer numbers) along the road between Town A and Town B,
both towns included, for a number of different queries.

You might find towns A and B at the same location (i.e., a equals b) or Town A could be located before or after Town B along the road.

Just to give you an idea of what you're getting into, let's consider an instance when Town A is at point 1 (i.e., a=1) and Town B is at point 5 (so b=5).
Here, the summation of all the points from Town A to Town B inclusive, which are 1, 2, 3, 4, 5, comes up to 15.
We hope your map (or function) could calculate this total in similar situations.

Of course, in the best interests of Integerland and the occasional travelers, we'd like you to optimize this map.
It is essential that it works as efficiently as possible.
Your understanding of complexity analysis and various ways of optimizing this task will certainly come in handy.

Best of luck on your endeavor!

Input Specification:

    The function will receive a single argument, which is a list of pairs of unsigned integers.
    Each pair (a, b) represents a query describing the bounds of the road to be considered.

Output Specification:
    For each query, output the total value of all the points (integer numbers) between Town A and Town B, inclusive, in a list.

Constraints
    Both a and b are integers.
    $1 ≤ a, b ≤ 1000000000$.
    $1 ≤ queries.size() ≤ 100000$.
 */
public class SumIntegerSeries {
    public List<Long> sumNumbers(List<int[]> queries) {
        List<Long> sumNumbers = new  ArrayList<Long>();

        for(int[] pair: queries){
            int min = Arrays.stream(pair).min().orElse(1);
            int max = Arrays.stream(pair).max().orElse(1);

            if(min == max){
                sumNumbers.add((long)min);
            }else if(min == 1 && max > 1){
                long maxSum = (long) max * (max + 1) / 2;
                sumNumbers.add(maxSum);
            }else{
                min--;
                long minSum = (long) min * (min + 1) / 2;
                long maxSum = (long) max * (max + 1) / 2;
                sumNumbers.add(maxSum - minSum);
            }
        }
        return sumNumbers;
    }

    public static void main(String[] args){
        List<int[]> queries = Arrays.asList(
                new int[]{1, 5},
                new int[]{5, 1},
                new int[]{1, 1},
                new int[]{500, 500},
                new int[]{1, 500},
                new int[]{123, 321},
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 4},
                new int[]{4, 5},
                new int[]{5, 6},
                new int[]{1000000, 2000000},
                new int[]{2000000, 3000000},
                new int[]{3000000, 4000000},
                new int[]{500000, 1500000},
                new int[]{2500000, 1000000},
                new int[]{1, 1000000000},
                new int[]{1, 1000000},
                new int[]{1000000, 1},
                new int[]{500000, 1500000},
                new int[]{1500000, 500000},
                new int[]{1, 1},
                new int[]{1000000000, 1000000000}
        );
        SumIntegerSeries solution = new SumIntegerSeries();
        List<Long> results = solution.sumNumbers(queries);

        for(int i = 0; i < results.size(); i++){
            System.out.printf("%d.- The total value of summing from point A: %d to point B %d: is %d\n", i + 1, queries.get(i)[0], queries.get(i)[1], results.get(i));
        }
    }
}
