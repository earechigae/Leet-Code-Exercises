package org.leetcode.exercises.matrix.spiral;

/*
59. Spiral Matrix II

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
    Input: n = 3
    Output: [[1,2,3],
             [8,9,4],
             [7,6,5]]

Example 2:
    Input: n = 1
    Output: [[1]]

Constraints:
    1 <= n <= 20
 */
public class SpiralMatrix02 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int i = 0, j = 0;
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        int num = 1;

        while((left <= right) && (top <= bottom)){
            // From left to right
            for(j = left; j <= right; j++){
                matrix[top][j] = num++;
            }
            top++;

            //From top to bottom
            for(i = top; i <= bottom; i++){
                matrix[i][right] = num++;
            }
            right--;

            // From right to left
            if(top <= bottom) {
                for (j = right; j >= left; j--) {
                    matrix[bottom][j] = num++;
                }
                bottom--;
            }

            // From bottom to top
            if(left <= right) {
                for (i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        SpiralMatrix02 sm = new SpiralMatrix02();
        int n = 3;
        int[][] matrix1 = sm.generateMatrix(n);
        int[][] matrix2 = sm.generateMatrix(1);

        System.out.println("\nGenerated spiral Matrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nGenerated spiral Matrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
