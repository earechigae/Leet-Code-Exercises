package org.codesignal.exercises.optimization;

import java.util.*;

/*
You are given two arrays, array A and array B, each containing n integers where n can range from $2$ to $100000$, inclusive.
All elements of both arrays are integers that can range from $-10^{10}$ to $10^{10}$, inclusive.

Your task is to create a function optimizedReplace that returns a new array, C.
For each index i, C[i] should contain a specific value from array A - A[j],
determined by the condition that B[i] is the closest number to B[j] from array B.

This means that array C will have the same length as A and B.
C[i] will contain the value corresponding to the j-th index from array A,
where the value at the j-th index in array B is closest to the value at the i-th index of array B.

Assume that there is no ambiguity.
The array B is given in a way that no two elements in array B have the same minimal absolute difference with B[i].

Remember, the number of elements, n, and the range of the elements, imply that brute force solutions will not be efficient.
You should aim to leverage optimized algorithms and techniques to solve this task efficiently.

Example:

    Suppose we have A = {10, 20, 30, 40, 50} and B = {7, 5, 1, 2, 4}.

    The function optimizedReplace(A, B) should work as follows:

        For B[0] = 7, the closest number in B is 5 at index 1. Hence, C[0] = A[1] = 20.

        For B[1] = 5, the closest number in B is 4 at index 4. Thus, C[1] = A[4] = 50.

        For B[2] = 1, the closest number in B is 2 at index 3. Hence, C[2] = A[3] = 40.

        For B[3] = 2, the closest number in B is 1 at index 2. So, C[3] = A[2] = 30.

        Lastly, for B[4] = 4, the closest number in B is 5 at index 1. We have C[4] = A[1] = 20.

    Thus, the function optimizedReplace({10, 20, 30, 40, 50}, {7, 5, 1, 2, 4}) should return {20, 50, 40, 30, 20}.
 */

public class OptimizedReplace {

    public long[] optimizedReplace(long[] A, long[] B) {
        long[] C = new long[A.length];
        long[] sortedB = new long[B.length];
        Map<Long, Integer> valueToIndexForArrayB = new HashMap<>();

        //System.arraycopy(B, 0, sortedB, 0, B.length);
        //Arrays.sort(sortedB);
        for(int i = 0; i < B.length; i++){
            sortedB[i] = B[i];
            valueToIndexForArrayB.put(B[i], i);
        }
        Arrays.sort(sortedB);

        for(int i = 0; i < B.length; i++){
            //Get the index of the closest number of B[i]
            int index = Arrays.binarySearch(sortedB, B[i]);
            long closetNumber = 0;

            if(index == 0 && sortedB.length >= 2){
                closetNumber = sortedB[1];
            }else if(index == sortedB.length - 1){
                closetNumber = sortedB[sortedB.length - 2];
            }else{
                long diff1 = Math.abs(sortedB[index] - sortedB[index - 1]);
                long diff2 = Math.abs(sortedB[index + 1] - sortedB[index]);
                closetNumber = (diff1 <= diff2)? sortedB[index - 1] : sortedB[index + 1];
            }

            /*
            for(int j = 0; j < B.length; j++) {
                if(B[j] == closetNumber){
                    C[i] = A[j];
                    break;
                }
            }*/
            C[i] = A[valueToIndexForArrayB.get(closetNumber)];
        }

        return C;
    }

    public static void main(String[] args){
        List<long[]>  arraysA = new ArrayList<>();
        List<long[]>  arraysB = new ArrayList<>();
        List<long[]>  expectedOutput = new ArrayList<>();
        OptimizedReplace or = new OptimizedReplace();

        arraysA.add(new long[]{10, 20, 30, 40, 50});
        arraysB.add(new long[]{7, 5, 1, 2, 4});
        expectedOutput.add(new long[] {20, 50, 40, 30, 20});

        arraysA.add(new long[]{-3, -2, -1});
        arraysB.add(new long[]{-1, -2, -4});
        expectedOutput.add(new long[] {-2, -3, -2});

        arraysA.add(new long[]{2, 0, 3});
        arraysB.add(new long[]{5, 10, 7});
        expectedOutput.add(new long[] {3, 3, 2});

        arraysA.add(new long[]{-1000, 1000, 800, 0, 550});
        arraysB.add(new long[]{-800, 801, 0, -550, 1000});
        expectedOutput.add(new long[] {0, 550, 0, -1000, 1000});

        arraysA.add(new long[]{5, 8, 0, 12, -17});
        arraysB.add(new long[]{9, 2, 4, -100, 8});
        expectedOutput.add(new long[] {-17, 0, 8, 8, 5});

        arraysA.add(new long[]{-1, 3, 7, 0, 2});
        arraysB.add(new long[]{4, 5, 7, 10, 1});
        expectedOutput.add(new long[] {3, -1, 3, 7, -1});

        for(int z = 0; z < expectedOutput.size(); z++){
            System.out.printf("%d.- A: %s, B: %s --> Expected: %s, Calculated one: %s%n", z + 1,
                    Arrays.toString(arraysA.get(z)),
                    Arrays.toString(arraysB.get(z)),
                    Arrays.toString(expectedOutput.get(z)),
                    Arrays.toString(or.optimizedReplace(arraysA.get(z), arraysB.get(z))));
        }

    }
}
