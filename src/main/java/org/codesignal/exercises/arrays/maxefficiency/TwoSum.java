package org.codesignal.exercises.arrays.maxefficiency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class TwoSum {

    public int[] solution(int[] arr, int target) {
        int[] tuple = {};
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (seen.containsKey(complement)) {
                tuple = new int[2];
                tuple[0] = complement;
                tuple[1] = arr[i];
                break;
            }
            seen.put(arr[i], i);
        }

        return tuple;
    }

    public static void main(String[] args){
        TwoSum ts = new TwoSum();
        List<int[]> inputArr = new ArrayList<>();
        List<Integer> targets = new ArrayList<>();
        List<int[]> expectedResults = new ArrayList<>();

        inputArr.add(new int[]{1, 2, 3, 4, 9, 10});
        targets.add(13);
        expectedResults.add(new int[]{4, 9});

        inputArr.add(new int[]{50, -50});
        targets.add(100);
        expectedResults.add(new int[]{});

        inputArr.add(new int[]{0, 0, 0, 0, 0});
        targets.add(0);
        expectedResults.add(new int[]{0, 0});

        inputArr.add(new int[]{-100, -50, 0, 50, 100});
        targets.add(-150);
        expectedResults.add(new int[]{-100, -50});

        inputArr.add(new int[]{-66, 45, 95, -84, -35, -70, 26, 94, 15, 20, 66, -3, -47, -76, 24, -93, -1, 10, 55, 95, 96,
                -100, 78, 14, -32, 84, -42, 51, -74, -19, -93, -95, -94, 66, 38, -98, -3, 75, -45, 8, 85, -93, 35, -44,
                95, 12, 26, 41, -41, -12, -41, 73, -44, 94, 17, -26, -95, 6, 42, 64, -75, -53, 61, 85, -25, -70, 90, -15,
                84, 82, 28, 8, 29, 71, -52, -23, -28, 50, 27, 29, 0, 50, -92, 22, -38, 90, 3, 6, 70, -56, -7, 40, 79, 98,
                72, 88, -5, -78, 12, 69});
        targets.add(-10);
        expectedResults.add(new int[]{66, -76});

        int[] arr = new int[500];
        for (int i = 0; i < 500; ++i) arr[i] = i + 1;
        inputArr.add(arr);
        targets.add(629);
        expectedResults.add(new int[]{314, 315});

        arr = new int[500];
        for (int i = 0; i < 500; ++i) arr[i] = -100;
        inputArr.add(arr);
        targets.add(-200);
        expectedResults.add(new int[]{314, 315});


        for(int z = 0; z < inputArr.size(); z++){
            int[] result = ts.solution(inputArr.get(z), targets.get(z));
            System.out.printf("%d.- For input array %s, the two values that sum %d are Expected: %s, Calculated: %s%n",
                    z + 1, Arrays.toString(inputArr.get(z)), targets.get(z), Arrays.toString(expectedResults.get(z)),
                    Arrays.toString(result));

        }

    }
}
