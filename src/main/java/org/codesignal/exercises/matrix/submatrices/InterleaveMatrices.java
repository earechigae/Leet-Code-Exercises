package org.codesignal.exercises.matrix.submatrices;

/*
Your task is to write a function, interleaveMatrices,
that takes two matrices (2D arrays) and a start and end range for rows and columns for each matrix as inputs.
Instead of concatenating submatrices together, this task requires interleaving the columns from the submatrices within the final matrix.

If A and B are your two matrices, and the respective submatrices selected from them based on the given ranges are sub_A and sub_B,
then the task is to form a new matrix C by interleaving columns from sub_A and sub_B.
Starting with the first column of sub_A, alternately include a column from sub_A and a column from sub_B until
all columns from both submatrices are included.

All matrices are filled with integers. The size of each matrix, A and B, ranges between 1×11×1 and 10×1010×10, inclusive,
and each element in the matrix is from the range of −100−100 to 100100, inclusive.
The start and end ranges for rows and columns for each matrix are provided as an array
{start_row, end_row, start_column, end_column}, and these are 0-based indices.

For example, if A is:
    {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12}
    }

and B is:
    {
        {11, 12, 13},
        {14, 15, 16},
        {17, 18, 19}
    }

If we select 2x2 submatrices from each (comprising the 2nd to the 3rd rows and the 2nd to the 3rd columns from A,
and the 1st to the 2nd rows and the 1st to the 2nd columns from B), their interleaved combination would look like this:

    {
        {6, 11, 7, 12},
        {10, 14, 11, 15}
    }

Note that in the output, columns from sub_A and sub_B are interwoven.

It is guaranteed that the given submatrices have pairwise equal dimensions.
 */

public class InterleaveMatrices {
    public int[][] interleaveMatrices(int[][] matrixA, int[][] matrixB, int[][] submatrixCoords) {
        int startRowA = submatrixCoords[0][0];
        int endRowA = submatrixCoords[0][1];
        int startColA = submatrixCoords[0][2];
        int endColA = submatrixCoords[0][3];
        int startRowB = submatrixCoords[1][0];
        int endRowB = submatrixCoords[1][1];
        int startColB = submatrixCoords[1][2];
        int endColB = submatrixCoords[1][3];

        int numRows = endRowA - startRowA + 1;
        int numColsA = endColA - startColA + 1;
        int numColsB = endColB - startColB + 1;
        int[][] submatrixA = new int[numRows][numColsA];
        int[][] submatrixB = new int[numRows][numColsB];

        // Fill submatrixA
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColsA; j++) {
                submatrixA[i][j] = matrixA[startRowA + i][startColA + j];
            }
        }

        // Fill submatrixB
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColsB; j++) {
                submatrixB[i][j] = matrixB[startRowB + i][startColB + j];
            }
        }

        // Interleave the columns
        int[][] result = new int[numRows][numColsA + numColsB];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColsA; j++) {
                result[i][j * 2] = submatrixA[i][j]; // Place elements from submatrixA
            }
            for (int j = 0; j < numColsB; j++) {
                result[i][j * 2 + 1] = submatrixB[i][j]; // Place elements from submatrixB
            }
        }

        return result;
    }

    public static void main(String[] args) {
        InterleaveMatrices interleaveMatrices = new InterleaveMatrices();
        int[][] matrixA = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        int[][] matrixB = {
                {11, 12, 13},
                {14, 15, 16},
                {17, 18, 19}
        };

        int[][] submatrixCoords = {
                {1, 2, 1, 2},
                {0, 1, 0, 1}
        };

        int[][] result = null;

        result = interleaveMatrices.interleaveMatrices(matrixA, matrixB, submatrixCoords);
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("");
        }
        System.out.println("");

        matrixA = new int[][]{
                {89, -34, 23},
                {1, -3, 0}
        };
        matrixB = new int[][]{
                {-12, -8, 2},
                {7, -6, 10}
        };
        submatrixCoords = new int[][]{
                {1, 1, 0, 2},
                {0, 0, 0, 2}
        };
        int[][] expected = new int[][]{{1, -12, -3, -8, 0, 2}};

        result = interleaveMatrices.interleaveMatrices(matrixA, matrixB, submatrixCoords);
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
