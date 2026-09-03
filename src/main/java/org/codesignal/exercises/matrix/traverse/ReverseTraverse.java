package org.codesignal.exercises.matrix.traverse;

/*
Reverse Traverse

Given an m x n matrix, return all elements of the matrix backwards.

Example 1:
    Input: matrix = [[1,2,3],
                     [4,5,6],
                     [7,8,9]]
    Output: [9,8,7,6,5,4,3,2,1]

 Example 2:
    Input: matrix = [[1,2,3,4],
                     [5,6,7,8],
                     [9,10,11,12]]
    Output: [12,11,10,9,8,7,6,5,4,3,2,1]

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100
 */

public class ReverseTraverse {
    int[] reverseTraverse(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] output = new int[rows * cols];
        int index = 0;

        for(int row = rows - 1; row >= 0; --row){
            for(int col = cols - 1; col >= 0; --col){
                output[index++] = matrix[row][col];
            }
        }

        return output;
    }

    public static void main(String[] args) {
        ReverseTraverse rt = new ReverseTraverse();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[] result = null;

        System.out.println("\nMatrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        result = rt.reverseTraverse(matrix1);
        System.out.print(" ******* Reverse traverse: [");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == (result.length -1)? "]" : ", "));
        }
        System.out.println();

        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        result = rt.reverseTraverse(matrix2);
        System.out.print(" ******* Reverse traverse: [");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == (result.length -1)? "]" : ", "));
        }
        System.out.println();
    }
}
