package org.codesignal.exercises.matrix.traverse.zigzag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a 2D array of integer values where each cell represents a unique integer.
The size of the matrix, n x n, ranges from 1 x 1 to 10 x 10, and each integer cell value, v, ranges from 1 to 100, inclusive.

Your task is to traverse the matrix in a unique way: Start from the top-left cell and move right until you hit the upper right corner.
Then, move downward one cell and start moving to the left until you hit the left boundary.
Upon hitting the left boundary, move down one cell and start moving right until you hit the right boundary.
When you hit the right boundary, move down one cell and start moving left again.
Continue this pattern until you have traversed every cell in the matrix.

Having completed this zigzag traversal, you will gather a list of traversed cell values.
Your task now is to process this list and identify the values of the prime numbers and their indices.
Therefore, implement the function zigzagTraverseAndPrimes(int[][] matrix) that returns a map where each key-value pair
represents an index and the prime number found at that index from the traversed list.

For instance, suppose you have a 4x4 matrix:
    {
        {10, 11, 4, 3},
        {6, 7, 15, 13},
        {8, 14, 1, 2},
        {5, 9, 12, 19}
    }

Upon completing the zigzag traversal, you obtain the list: {10, 11, 4, 3, 13, 15, 7, 6, 8, 14, 1, 2, 19, 12, 9, 5}.
From this list, we observe that 11, 3, 13, 7, 2, 19, and 5 are prime numbers,
and they are located at the 1st, 3rd, 4th, 6th, 11th, 12th, and 15th positions (0-indexed) in the list.
Our function should return: {1: 11, 3: 3, 4: 13, 6: 7, 11: 2, 12: 19, 15: 5}.

Remember, a prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
The first few prime numbers are 2, 3, 5, 7, 11, and so on.
 */

public class ZigZagTraverseAndPrimes {

    private static boolean isPrime(int n) {
        // 1. Numbers less than or equal to 1 are not prime
        if (n <= 1) {
            return false;
        }

        // 2. 2 is the only even prime number
        if (n == 2) {
            return true;
        }

        // 3. Exclude all other even numbers
        if (n % 2 == 0) {
            return false;
        }

        // 4. Check odd (impar) factors up to the square root of n
        // Incrementing by 2 skips checking even numbers entirely
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false; // Found a factor, so it's not prime
            }
        }

        return true; // No factors found, it is prime
    }


    public Map<Integer, Integer> zigzagTraverseAndPrimes(int[][] matrix) {
        List<Integer> zigZagTraversal = new ArrayList<>();
        Map<Integer, Integer> primePositions = new HashMap<>();

        for(int i = 0; i < matrix.length; i++){
            //From left to right
            if(i % 2 == 0){
                for(int j = 0; j < matrix[i].length; j++){
                    zigZagTraversal.add(matrix[i][j]);
                }
            }else{ //From right to left
                for(int j = matrix[i].length - 1; j >= 0; j--){
                    zigZagTraversal.add(matrix[i][j]);
                }
            }
        }

        for(int z = 0; z < zigZagTraversal.size(); z++){
            if(isPrime(zigZagTraversal.get(z))){
                primePositions.put(z, zigZagTraversal.get(z));
            }
        }

        return primePositions;
    }

    public static void main(String[] args){
        int[][] matrix1 = {
            {10, 11, 4, 3},
            {6, 7, 15, 13},
            {8, 14, 1, 2},
            {5, 9, 12, 19}
        };

        System.out.println("\nMatrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        ZigZagTraverseAndPrimes solution = new ZigZagTraverseAndPrimes();
        System.out.println("The positions of prime numbers after a zig zag traversal are: " +
                solution.zigzagTraverseAndPrimes(matrix1));
    }
}
