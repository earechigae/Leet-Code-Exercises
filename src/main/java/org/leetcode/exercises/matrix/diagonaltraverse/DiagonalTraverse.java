package org.leetcode.exercises.matrix.diagonaltraverse;

/*
498. Diagonal Traverse

Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

Example 1:
    Input: mat = [[1,2,3],
                  [4,5,6],
                  [7,8,9]]
    Output: [1,2,4,7,5,3,6,8,9]

Example 2:
    Input: mat = [[1,2],
                  [3,4]]
    Output: [1,2,3,4]

Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 104
    1 <= m * n <= 104
    -10^5 <= mat[i][j] <= 10^5
 */

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        if(mat == null || mat.length == 0) return new int[0];

        int rows = mat.length;
        int cols = mat[0].length;
        int[] result = new int[rows * cols];
        int r = 0, c = 0;

        for(int i = 0; i < result.length; i++){
            result[i] = mat[r][c];

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
            if((r + c) % 2 == 0){
                // If we are at the last column, we can only go down
                if(c == cols - 1){
                    r++;
                }
                // If we are at the first row, we can only go right
                else if(r == 0){
                    c++;
                }
                // Otherwise, we can go up diagonally
                else{
                    r--;
                    c++;
                }
            }else{ // Downward direction
                // If we are at the last row, we can only go right
                if(r == rows - 1){
                    c++;
                }
                // If we are at the first column, we can only go down
                else if(c == 0){
                    r++;
                }
                // Otherwise, we can go down diagonally
                else{
                    r++;
                    c--;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        DiagonalTraverse dt = new DiagonalTraverse();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10,11,12},
                {13,14,15,16}
        };

        int[] result = dt.findDiagonalOrder(matrix1);
        System.out.println("Matrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.print("Diagonal Traverse result: [ ");
        for(int num : result){
            System.out.print(num + ", ");
        }
        System.out.println("]");

        result = dt.findDiagonalOrder(matrix2);
        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.print("Diagonal Traverse result: [ ");
        for(int num : result){
            System.out.print(num + ", ");
        }
        System.out.println("]");
    }

    public int[] findDiagonalOrderAICopilot(int[][] mat) {
        if(mat == null || mat.length == 0) return new int[0];

        int rows = mat.length;
        int cols = mat[0].length;
        int[] result = new int[rows * cols];
        int r = 0, c = 0;

        for(int i = 0; i < result.length; i++){
            result[i] = mat[r][c];

            // Moving up
            if((r + c) % 2 == 0){
                if(c == cols - 1){
                    r++; // Move down if at last column
                } else if(r == 0){
                    c++; // Move right if at first row
                } else {
                    r--; // Move up
                    c++; // Move right
                }
            }
            // Moving down
            else {
                if(r == rows - 1){
                    c++; // Move right if at last row
                } else if(c == 0){
                    r++; // Move down if at first column
                } else {
                    r++; // Move down
                    c--; // Move left
                }
            }
        }

        return result;
    }

}
