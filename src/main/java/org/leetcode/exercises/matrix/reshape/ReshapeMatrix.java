package org.leetcode.exercises.matrix.reshape;

/*
566. Reshape the Matrix

In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns of the wanted reshaped matrix.
The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
    Input: mat = [[1,2],
                  [3,4]], r = 1, c = 4
    Output: [[1,2,3,4]]

Example 2:
    Input: mat = [[1,2],
                  [3,4]], r = 2, c = 4
    Output: [[1,2],
             [3,4]]

Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 100
    -1000 <= mat[i][j] <= 1000
    1 <= r, c <= 300
 */

public class ReshapeMatrix {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if(mat == null || ((mat.length * mat[0].length) != (r * c))){
            return mat;
        }
        int[][] reshaped = new int[r][c];
        for(int i = 0; i < (r * c); i++ ){
            reshaped[i / c][i % c] = mat[i / mat[0].length][i % mat[0].length];
        }

        return reshaped;
    }

    public static void main(String args[]){
        ReshapeMatrix rm = new ReshapeMatrix();
        int[][] matrix1 = {
                {1, 2},
                {3, 4}
        };

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10,11,12},
                {13,14,15,16}
        };

        int[][]reshapedMatrix1 = rm.matrixReshape(matrix1, 1, 4);
        System.out.println("Reshaped Matrix 1:");
        for(int i = 0; i < reshapedMatrix1.length; i++){
            for(int j = 0; j < reshapedMatrix1[0].length; j++){
                System.out.print(reshapedMatrix1[i][j] + "\t");
            }
            System.out.println();
        }

        int[][]reshapedMatrix2 = rm.matrixReshape(matrix2, 2, 8);
        System.out.println("\n\nReshaped Matrix 2:");
        for(int i = 0; i < reshapedMatrix2.length; i++){
            for(int j = 0; j < reshapedMatrix2[0].length; j++){
                System.out.print(reshapedMatrix2[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
