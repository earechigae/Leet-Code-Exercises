package org.leetcode.exercises.arrays;

import java.util.HashMap;
import java.util.Map;

/*
923. 3Sum With Multiplicity

Given an integer array arr, and an integer target, return the number of
tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
As the answer can be very large, return it modulo 109 + 7.

Example 1:
    Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
    Output: 20
    Explanation:
    Enumerating by the values (arr[i], arr[j], arr[k]):
    (1, 2, 5) occurs 8 times;
    (1, 3, 4) occurs 8 times;
    (2, 2, 4) occurs 2 times;
    (2, 3, 3) occurs 2 times.

Example 2:
    Input: arr = [1,1,2,2,2,2], target = 5
    Output: 12
    Explanation:
    arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
    We choose one 1 from [1,1] in 2 ways,
    and two 2s from [2,2,2,2] in 6 ways.

Example 3:
    Input: arr = [2,1,3], target = 6
    Output: 1
    Explanation: (1, 2, 3) occured one time in the array so we return 1.

Constraints:
    3 <= arr.length <= 3000
    0 <= arr[i] <= 100
    0 <= target <= 300
 */

public class ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        Map<Integer, Long> frequencyMap = new HashMap<>();

        for(int i: arr){
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0L) + 1);
        }

        long ans = 0;
        for(Integer x: frequencyMap.keySet())
        {
            for(Integer y: frequencyMap.keySet())
            {
                int z = target - x - y;
                if(frequencyMap.containsKey(z)){
                    long xFreq = frequencyMap.get(x);
                    long yFreq = frequencyMap.get(y);
                    long zFreq = frequencyMap.get(z);

                    if(x == y && x == z){ // If all 3 numbers are equals
                        ans += ((xFreq)*(xFreq -1)*(xFreq - 2))/6;
                    }else if(x == y && x != z){ // If 2 numbers are equals and 1 different
                        ans += (((xFreq)*(xFreq - 1))/2) * zFreq;
                    }else if( x < y && y < z){ // All 3 numbers are different
                        ans += xFreq * yFreq * zFreq;
                    }
                }
            }
        }

        ans = ans % 1000000007;
        return (int)ans;
    }

    public static void main(String args[]){
        ThreeSumWithMultiplicity threeSumWithMultiplicity = new ThreeSumWithMultiplicity();
        int target = 0;
        int [] arr1 = new int[]{1,1,2,2,3,3,4,4,5,5};
        int [] arr2 = new int[]{1,1,2,2,2,2};
        int [] arr3 = new int[]{2,1,3};

        System.out.print("\narr1: [");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + (i == (arr1.length -1)? "]" : ", "));
        }
        target = 8;
        System.out.println(" The number of tuplets of 3 elements that sum " + target + " are " + threeSumWithMultiplicity.threeSumMulti(arr1, target));

        System.out.print("\narr2: [");
        for(int i = 0; i < arr2.length; i++){
            System.out.print(arr2[i] + (i == (arr2.length -1)? "]" : ", "));
        }
        target = 5;
        System.out.println(" The number of tuplets of 3 elements that sum " + target + " are " + threeSumWithMultiplicity.threeSumMulti(arr2, target));

        System.out.print("\narr3: [");
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + (i == (arr3.length -1)? "]" : ", "));
        }
        target = 6;
        System.out.println(" The number of tuplets of 3 elements that sum " + target + " are " + threeSumWithMultiplicity.threeSumMulti(arr3, target));
    }
}



