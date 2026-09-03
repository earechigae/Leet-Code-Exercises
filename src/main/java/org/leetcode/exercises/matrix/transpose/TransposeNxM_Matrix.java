package org.leetcode.exercises.matrix.transpose;

/*
867. Transpose Matrix

Given a 2D integer array matrix, return the transpose of matrix.
The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

Example 1:
    Input: matrix = [[1,2,3],
                     [4,5,6],
                     [7,8,9]]
    Output: [[1,4,7],
             [2,5,8],
             [3,6,9]]

Example 2:
    Input: matrix = [[1,2,3],
                     [4,5,6]]
    Output: [[1,4],
             [2,5],
             [3,6]]

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 1000
    1 <= m * n <= 105
    -109 <= matrix[i][j] <= 109
 */

public class TransposeNxM_Matrix {
    public int[][] transpose(int[][] matrix) {
        int rowsLength = matrix.length;
        int colsLength = matrix[0].length;

        int[][] transposeMatrix = new int[colsLength][rowsLength];

        for(int i = 0; i < rowsLength; i++){
            for(int j = 0; j < colsLength; j++){
                transposeMatrix[j][i] = matrix[i][j];
            }
        }

        return transposeMatrix;
    }

    public static void main(String[] args) {
        TransposeNxM_Matrix transpose = new TransposeNxM_Matrix();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
        };

        int[][] matrix1Transposed = transpose.transpose(matrix1);
        System.out.println("Transposed Matrix 1:");
        for(int i = 0; i < matrix1Transposed.length; i++){
            for(int j = 0; j < matrix1Transposed[0].length; j++){
                System.out.print(matrix1Transposed[i][j] + "\t");
            }
            System.out.println();
        }

        int[][] matrix2Transposed = transpose.transpose(matrix2);
        System.out.println("\n\nTransponsed Matrix 2:");
        for(int i = 0; i < matrix2Transposed.length; i++){
            for(int j = 0; j < matrix2Transposed[0].length; j++){
                System.out.print(matrix2Transposed[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
