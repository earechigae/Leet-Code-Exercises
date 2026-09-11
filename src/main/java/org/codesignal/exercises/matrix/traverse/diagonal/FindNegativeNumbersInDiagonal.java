package org.codesignal.exercises.matrix.traverse.diagonal;

import java.util.ArrayList;
import java.util.List;

/*
Given a matrix of n x m integers, where n represents the number of rows and m represents the number of columns.
Both n and m range from 1 to 100, inclusive.

The matrix cells may contain either a positive, a negative integer, or zero, with values ranging from -100 to 100, inclusive.
In this task, you are required to traverse the matrix diagonally from the top-left cell to the bottom-right cell in a zigzag pattern.
Start from the top-left cell, move one cell to the right (if it exists), then move one step diagonally down-left.
After reaching a left (bottom) boundary, move one step down (right) and start moving diagonally up-right.
Continue this pattern until you reach the last cell of the matrix. Your task is to return a list of tuples,
each tuple containing the index pair (in 0-based indexing format) of cells with negative integers encountered during your traversal.

For example, consider a 3 x 4 matrix:
    {{ 1, -2,  3,  -4},
     { 5, -6,  7,   8},
     {-9, 10, -11, 12}}

The traversal in a zigzag pattern will result in: {1, -2, 5, -9, -6, 3, -4, 7, 10, -11, 8, 12}.
The negative integers in this sequence and their corresponding positions in the matrix are: {-2, -9, -6, -4, -11},
with indices: {{0, 1}, {2, 0}, {1, 1}, {0, 3}, {2, 2}}.

Your function, solution(matrix), should then return these indices as a list of arrays: {{0, 1}, {2, 0}, {1, 1}, {0, 3}, {2, 2}}.

 */

public class FindNegativeNumbersInDiagonal {
    public List<int[]> solution(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<int[]> traversal = new ArrayList<>();

        int row = 0, col = 0;
        for (int i = 0; i < rows * cols; ++i) {  // Loop runs for the total number of cells in the matrix.
            if(matrix[row][col] < 0){
                traversal.add(new int[]{row, col});
            }

            /* How to determine the direction?
             (0, 0)   (0, 1)   (0, 2)       (0 + 0) = 0    (0 + 1) = 1    (0 + 2) = 2    0  1  2
                                                                                         ↗     ↗
             (1, 0)   (1, 1)   (1, 2)    =  (1 + 0) = 1    (1 + 1) = 2    (1 + 2) = 3 =  1  2  3
                                                                                            ↗
             (2, 0)   (2, 1)   (2, 2)        (2 + 0) = 2    (2 + 1) = 3    (2 + 2) = 4   2  3  4
                                                                                         ↗     ↗
             If the sum of the indices is even, the direction is upward. Otherwise, it is downward.
             */

            // Upward direction
            if((row + col) % 2 == 0){
                // If we are at the last column, we can only go down
                if(col == cols - 1){
                    row++;
                }
                // If we are at the first row, we can only go right
                else if(row == 0){
                    col++;
                }
                // Otherwise, we can go up diagonally
                else{
                    row--;
                    col++;
                }
            }else{ // Downward direction
                // If we are at the last row, we can only go right
                if(row == rows - 1){
                    col++;
                }
                // If we are at the first column, we can only go down
                else if(col == 0){
                    row++;
                }
                // Otherwise, we can go down diagonally
                else{
                    row++;
                    col--;
                }
            }
        }

        return traversal;
    }

    public static void main(String[] args) {
        FindNegativeNumbersInDiagonal findNegativeNumbersInDiagonal = new FindNegativeNumbersInDiagonal();
        int[][] matrix1 = {
                {1, -2, 3, -4},
                {5, -6, 7, 8},
                {-9, 10, -11, 12}
        };

        int[][] matrix2 = {
                {1,   2, -3,  4},
                {5,  -6,  7,  8},
                {-9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        List<int[]> result = findNegativeNumbersInDiagonal.solution(matrix1);
        System.out.println("Matrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.print("Diagonal Traverse result: [ ");
        for(int[] coords : result){
            System.out.print("(" + coords[0] + ", " + coords[1] + "), ");
        }
        System.out.println("]");

        result = findNegativeNumbersInDiagonal.solution(matrix2);
        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.print("Diagonal Traverse result: [ ");
        for(int[] coords : result){
            System.out.print("(" + coords[0] + ", " + coords[1] + "), ");
        }
        System.out.println("]");

    }
}
