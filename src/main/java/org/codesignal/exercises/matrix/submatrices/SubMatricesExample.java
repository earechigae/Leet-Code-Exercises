package org.codesignal.exercises.matrix.submatrices;


/*
Task Statement

Imagine having two different 2D arrays, A and B. Our job is to devise a Java method — let's name it submatrixConcatenation() —
which takes these two matrices as inputs, along with the coordinates specifying submatrices within A and B.
The coordinate system uses 1-based indexing (meaning the first row and column are numbered 1, not 0).
This method is expected to stitch the two chosen submatrices together, forming a new one, C.
Notably, the submatrices from A and B should have the same number of rows, and in the final matrix C,
elements from A's submatrix should be on the left and those from B's submatrix on the right.

Let's visualize this with a couple of matrices.
Given the matrix A as:
{
     {1, 2, 3, 4},
     {5, 6, 7, 8},
     {9, 10, 11, 12}
 }

and the matrix B as:
{
    {11, 12, 13},
    {14, 15, 16},
    {17, 18, 19}
}

If we select 2x2 submatrices from each (comprising the 2nd to 3rd rows and 2nd to 3rd columns from A,
and 1st to 2nd rows and 1st to 2nd columns from B), their concatenation would look like:

{
    {6, 7, 11, 12},
    {10, 11, 14, 15}
}
 */

public class SubMatricesExample {
    public static int[][] submatrixConcatenation(
            int[][] matrixA,
            int[][] matrixB,
            int[][] submatrixCoords) {

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
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColsA; j++) {
                submatrixA[i][j] = matrixA[startRowA + i - 1][startColA + j - 1];
            }
        }

        int[][] submatrixB = new int[numRows][numColsB];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColsB; j++) {
                submatrixB[i][j] = matrixB[startRowB + i - 1][startColB + j - 1];
            }
        }

        // At this point, we have extracted submatrices from matrixA and matrixB

        // The part for the concatenation process
        int[][] resultMatrix = new int[numRows][numColsA + numColsB];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColsA; j++) {
                resultMatrix[i][j] = submatrixA[i][j];
            }
            for (int j = 0; j < numColsB; j++) {
                resultMatrix[i][j + numColsA] = submatrixB[i][j];
            }
        }

        return resultMatrix;
    }

    public static void main(String[] args) {
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
                {2, 3, 2, 3},
                {1, 2, 1, 2}
        };

        int[][] result = submatrixConcatenation(matrixA, matrixB, submatrixCoords);
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
