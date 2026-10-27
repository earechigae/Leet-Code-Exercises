package org.codesignal.exercises.arrays.maxefficiency;

import java.util.*;

/*
You are given two arrays, arrA and arrB, each containing n integers.
Your task is to write a function that returns a pair of distinct indices [i, j] such that the sum of the
elements at i and j in array arrA equals the sum of the elements at the same indices in array arrB.
If there are multiple valid pairs, return the pair in which i is minimal.
If there is still ambiguity, choose the one with minimal j among the latter.

Consider arrA = [2, 5, 1, 4] and arrB = [3, 6, 3, 2]. In this case,
the output should be [2, 3] because arrA[2] + arrA[3] = arrB[2] + arrB[3], i.e., 1 + 4 = 3 + 2.

Your solution should work within 1 second for each test.

Constraints:
    * 1 ≤ n ≤ 1000000
    * -1000000 ≤ arrA[i], arrB[i] ≤ 1000000 for all valid i
    * At least one pair [i, j] exists such that arrA[i] + arrA[j] = arrB[i] + arrB[j]

Hint 1
    Let's rearrange the equation!

    Starting with: arrA[i] + arrA[j] = arrB[i] + arrB[j]

    Can you rearrange the terms so that all elements related to index i are on one side, and all elements related to
    index j are on the other? What relationship do you notice between the differences at i and j?

    arrA[i] + arrB[i]  =  arrB[j] + arrA[j]
    arrA[i] + arrB[i]  = - (arrA[j] + arrB[j])

Hint 2
    Can you use a HashMap to store differences you've seen so far to find the matching pair in a single pass O(n)?

    As you iterate through the arrays with index j, you want to quickly know if there is an earlier index i where
    arrA[i] - arrB[i] == -(arrA[j] - arrB[j]).

    How could you use a HashMap mapping differences to their earliest index to check this in O(1)
    time instead of using that inner loop?
 */

public class DistinctIndicesSum {
    public int[] findIndices(int[] arrA, int[] arrB) {
        Map<Integer, Integer> earliestIndexByDiff = new HashMap<>();
        int[] bestPair = null;

        for (int j = 0; j < arrA.length; j++) {
            int diff = arrA[j] - arrB[j];
            Integer i = earliestIndexByDiff.get(-diff);

            if (i != null && (bestPair == null || i < bestPair[0]
                    || (i == bestPair[0] && j < bestPair[1]))) {
                bestPair = new int[]{i, j};
            }

            earliestIndexByDiff.putIfAbsent(diff, j);
        }

        return (bestPair == null) ? new int[]{-1, -1} : bestPair;
    }

    //This is not the optimal solution
    public int[] findIndicesOn2(int[] arrA, int[] arrB) {
        List<int[]> indices = new ArrayList<>();

        for(int i = 0; i < arrA.length; i++){
            for(int j = 0; j < arrB.length; j++){
                if((arrA[i] - arrB[i] == arrB[j] - arrA[j]) && (i < j)){
                    indices.add(new int[]{i, j});
                }
            }
        }

        if(indices.isEmpty()){
            return new int[]{-1, -1};
        }

        Collections.sort(indices, (a, b) -> {
            int iA = a[0], jA = a[1];
            int iB = b[0], jB = b[1];

            if(iA == iB){
                return Integer.compare(jA, jB);
            }
            return Integer.compare(iA, iB);
        });

        return indices.get(0); // Return the first valid pair of indices.
    }


    public static void main(String[] args){
        DistinctIndicesSum dic = new DistinctIndicesSum();
        List<int[]> arrAs = new ArrayList<>();
        List<int[]> arrBs = new ArrayList<>();
        List<int[]> expectedResults = new ArrayList<>();

        arrAs.add(new int[]{2, 1});
        arrBs.add(new int[]{1, 2});
        expectedResults.add(new int[]{0, 1});

        arrAs.add(new int[]{0, 1, 0, 3, 5});
        arrBs.add(new int[]{4, 0, 2, 0, 1});
        expectedResults.add(new int[]{0, 4});

        arrAs.add(new int[]{-55, -18, -96, 17, -9, -73, 95, 0, -92, -49, -81, 70});
        arrBs.add(new int[]{-13, -15, 61, -56, 40, -57, 56, -4, -53, -49, 45, 11});
        expectedResults.add(new int[]{6, 8});

        arrAs.add(new int[]{62, 56, 54, 2, 42, 68, -53, 65, 31, -52, 56, -33, 16, 69, -19, 44});
        arrBs.add(new int[]{62, 50, 54, 2, 42, 68, -53, 65, 31, -52, 56, -33, 16, 69, -19, 44});
        expectedResults.add(new int[]{0, 2});

        arrAs.add(new int[]{-224, 92, 71, -59, 89, 3, -97, -65, 2, -32, 94, 1, -52, -1, 29, 9, -64, -78, -19, -54, -10, -20, 62, -70, 23, -86, -63, 80, -17, 32, -26, 35, 93, 36, 3, -84, 32, -65, 77, -82, 11, 89, 98, -90, 0, 19, -97, 4, -39, -70, -18, 1, -15, -46, 64, 68, -76, 59, -40, 66, 68, 74, 34, 65, 94, 46, 55, -17, 46, 92, 30, -41, -93, 27, -22, 97, -64, -91, -47, -15, 42, -53, 61, -39, -49, 73, -23, 3, 1, -60, 12, -27, -39, 35, -26, 81, 4, 8, 98, 98, 10, 82, -65, -82, -74, -27, -9, 63, 40, -46, -80, 54, 95, -43, -58, -71, -39, -92, -83, 19, 1, 13, 66, -36, -55, -26, -29, -51, 20, -70, -14, 2, 84, -49, -15, -43, 36, 20, 50, -1, -2, -45, 29, 59, 43, 41, 51, 62, -21, -67, 41, 6, 27, 80, -71, -34, -60, 49, -61, 38, -6, 93, -24, -22, -94, -44, 51, -15, 85, -17, -14, 69, 36, 56, 10, 20, 19, -22, 25, -24, -68, -97, -36, -37, 56, 45, 10, 62, -76, 80, -24, -41, 29, -50, -65, 77, -70, 61, 63, 39, -75, 47, 83, 96, 74, 19, 52, 9, -59, 50, -23, -71, 34, 0, 28, 39, 91, 95, 100, -91, -82, 32, 72, 97, 10, -25, 83, 24, 75, 69, 36, 99, -49, -69, 84, 19, -93, -29, -92, 80, 39, 1, 81, 67, 92, 36, -85, 4, -83, 45, 9, 8, -60, 9, -92, -33, -40, 76, -100, -49, 57, 62, 98, 41, 46, -11, 10, -41, -1, -32, -67, 57, 51, -69, 54, 28, 73, -65, -92, 11, -73, 18, -93, -74, -47, 41, 38, -22, 18, 88, 91, -91, -62, -21, -4, 87, 9, -59, 87, -22, -97, 26, -2, -100, 98, 96, 11, -44, 9, -83, -60, -19, -22, 77, -34, -3, 63, 7, 91, 31, 100, 0, -8, 90, 9, -3, -10, -68, 21, -87, 31, -8, -73, -81, 49, 17, -96, 78, 90, -82, -89, 34, 20, -84, -17, 30, 62, -49, 41, -56, -56, -2, 95, 86, 56, 36, -21, -48, -12, -23, 26, -43, 25, 61, 2, 89, -25, -25, 60, 61, 49, 61, 63, 57, -80, -85, 54, 39, -89, -51, -14, -36, -86, 86, 3, 74, -79, -55, 75, 3, -67, 41, -69, 41, -94, 53, -83, -26, 24, 76, -91, -91, 33, 91, -81, 49, -81, 64, 76, -7, 32, -27, 94, -11, -62, -92, 42, -28, 15, -86, -73, 72, 50, -63, -47, 86, -35, -60, 100, -34, 100, 39, 5, -63, 64, -32, 99, 96, 67, -97, -58, 95, 17, -20, -79, 63, 0, 44, 26, 40, 19, 99, 34, 4, 1, -13, 50, 1, -26, -56, -36, 3, -41, 40, -44, -78, 94, -31, -23, 91, -30, 60, -32, -63, -53, -24, 85, -94, -33, 62, -93, -34, 35, 60, 43, -67, 52, 47, -95, 78, 5, 70, -17, -56, -87, 52, 43, 70, 40, -19});
        arrBs.add(new int[]{92, -63, 68, -65, -27, -96, -55, 74, 61, -82, -84, 7, -22, 14, 2, 75, 29, -22, -59, -3, 89, -31, -88, 10, 36, -12, 31, -77, 0, 100, 21, -25, 79, 78, -90, -47, 17, 89, -71, -68, 99, 12, 8, 51, 60, -43, 38, -12, -49, -68, -55, 56, 22, -28, -97, -95, -64, -8, 90, -75, 35, 17, 53, 4, 5, 84, -97, -55, -84, -3, 73, -74, -4, 64, 50, -48, 0, 96, 87, 80, -5, -49, -39, -76, -45, 97, 0, 25, 92, -95, 37, 28, 7, -4, -56, 37, 31, 39, 22, 4, 61, 71, 51, -12, -83, 9, 97, 6, -62, -99, -75, -51, -99, -39, -98, -81, -97, 38, 23, -28, 95, 91, -3, -17, 15, 86, 54, -1, 44, 44, 97, -47, 49, 74, 30, -13, -90, -54, 10, 44, 60, -80, 67, -95, 1, -98, -69, 56, 80, 78, -33, 18, 28, -42, 78, -24, 30, -49, -18, -28, -93, 77, -70, 53, -36, -92, -91, -68, 1, 58, -30, -80, -92, -40, 48, 54, 88, 52, -41, -66, 58, -2, -8, -39, -29, 82, -74, -14, 56, 9, 76, -26, -63, 15, 77, -77, 34, -81, 0, 67, -17, 95, 93, -87, 3, -69, -54, -71, -94, 67, 71, 78, 69, -26, -85, -74, 57, -31, 45, -34, 38, 40, 92, -21, 92, -46, -85, 32, 71, 18, 3, 75, -43, 92, 81, -55, -78, 85, -4, -49, 48, 97, 81, 60, 68, 8, 23, -89, -38, -79, 69, -22, 57, -32, 73, -1, -96, 91, 10, 2, -47, 95, 34, -73, 45, -93, 51, 49, 79, 24, 0, 52, 5, -94, -53, -15, 70, 79, 31, 34, -30, 2, -12, 55, 14, -41, -63, -79, 54, -17, 61, -57, 59, 89, -32, 51, -11, -67, -37, -88, -9, 79, -63, 7, -20, 45, -36, -36, 68, 35, 87, -39, 47, -55, -51, 65, 56, 42, 12, 93, 9, 81, 75, -23, -69, -6, -77, 47, -58, 55, 70, -22, -31, 56, 14, 23, 71, -13, -61, 24, 52, 99, -85, -53, 96, -46, -68, -11, 15, -46, -95, -43, -44, -10, -2, -5, -17, 51, 1, -51, -6, 0, -92, 2, 58, -35, 77, 92, 89, -55, 24, -37, 24, 20, 70, -77, -24, 20, 0, 86, -44, -9, -63, 48, 24, -28, 97, 6, -60, -30, 37, 7, 61, 17, -13, 6, -80, -8, 15, -1, -13, 74, -92, 69, -32, -1, -98, 53, 25, -38, 78, 82, 1, 39, -59, 30, 85, 84, -79, -22, 36, -79, -70, 67, 29, 56, -97, 76, 20, -77, -47, -75, -52, -8, -28, 61, -90, 89, -47, 34, -19, 55, -45, -30, 27, 94, -27, 94, -31, 55, 20, 81, -48, -55, -55, 15, -84, -32, 82, -29, 99, 74, -40, -18, 10, 58, -70, -33, 3, -19, 56, -24, -99, 27, -70, -9, 30, 53, 49, 85, 89, 85, -56, -16, -81, 66, -69, -10, 25, 9, 96, 89, 29, -11, 3, -18, 61, -78, -11, 54});
        expectedResults.add(new int[]{2, 396});

        arrAs.add(new int[]{20, 4, 94, 49, -74, -20, 66, -80, -86, 23, -45, 69, 27, 51, -34, 37, 60, -84, 94, -72, 88, 39, -78, -73, -87, 50, -31, -15, -96, -72, 53, 30, -98, -57, -72, -22, 48, 17, 15, -14, 67, 20, -81, 22, 73, -2, -53, -11, 56, -23, 74, 57, -52, 17, -87, -27, -94, -37, -43, -100, 35, -93, -81, 62, -92, 78, -75, 72, -88, -79, -41, -66, -49, 7, 89, -1, -91, -73, -24, 0, 84, 57, -66, -50, 11, -44, -32, 78, -93, -94, 16, 33});
        arrBs.add(new int[]{4, 50, 94, -26, -86, -58, 37, 33, -33, 62, -54, -62, -27, -67, 88, 45, -38, 14, -96, -57, -70, -91, 76, 18, 20, 88, 88, 59, 91, 45, 87, -73, 32, 100, -7, 73, 77, -18, -55, 15, 87, 82, 67, -53, 51, 54, -37, 98, 0, 33, -86, 15, 45, 13, -2, -29, 52, -86, 37, -24, 24, 43, 59, -39, -5, 8, 100, 14, 13, 22, 98, -46, 77, -90, 60, -6, -24, -56, 48, 63, 39, 73, -61, 47, 49, -79, -31, -44, 37, 18, -57, 37});
        expectedResults.add(new int[]{0, 46});

        /*
        int[] arrayA = new int[1_000_000];
        int[] arrayB = new int[1_000_000];
        for (int i = 0; i < 1_000_000; i++) {
            arrayA[i] = i;
            arrayB[i] = -1_000_000 + 2 * i;
        }
        arrayA[325411] -= 1098244;
        arrAs.add(arrayA);
        arrBs.add(arrayB);
        expectedResults.add(new int[]{325411, 576345});
         */

        for(int z = 0; z < arrAs.size(); z++){
            int[] arrA = arrAs.get(z);
            int[] arrB = arrBs.get(z);
            int[] expected = expectedResults.get(z);
            int[] calculated = dic.findIndices(arrA, arrB);
            System.out.printf("%nTest case %d.- %narrA: %s %narrB: %s %nExpected: %s %nCalculated %s%n", (z + 1),
                    Arrays.toString(arrA), Arrays.toString(arrB), Arrays.toString(expected), Arrays.toString(calculated));
        }

    }
}
