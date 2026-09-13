package org.codesignal.exercises.matrix.submatrices;

/*
Warren, an innovator in mathematical problems, challenges you to solve a complex task involving matrix manipulation.
He provides you with a 2D array, M, with dimensions m x n, where m and n range from 1 to 500, inclusive.
Each element in the matrix ranges from -100 to 100, inclusive.

Warren further provides you with the coordinates describing two sub-matrices within M,
denoted as S1 and S2. He asks you to write a Java function, say submatrixSwap(),
which takes as inputs the matrix M and the coordinates specifying the sub-matrices S1 and S2.
This function is required to swap the positions of S1 and S2 within M.

The swapping of sub-matrices is subject to the following constraints:
    1. The sub-matrices do not overlap.
    2. S1 and S2 must have identical dimensions, i.e., the number of rows and columns in S1 must equal the number of
       rows and columns in S2.
    3. The coordinates of each submatrix are given by 4 coordinates - {row_l, row_r, col_l, col_r},
       which correspond to a valid submatrix with rows in [row_l, row_r) and columns in [col_l, col_r).

Example:
    Let's consider an example to clarify the task:
    Suppose 'M' is:
    int[][] M = {
        {   1,    2, [3], [4],  5},
        {   6,    7, [8], [9], 10},
        {[11], [12],  13, 14,  15},
        {[16], [17],  18, 19,  20},
        {  21,   22,  23, 24,  25}
    };
    With sub-matrix S1 defined by the coordinates 0, 2, 2, 4 (indicating that it spans from rows 0 to 1 and columns 2 to 3),
    and S2 given the coordinates 2, 4, 0, 2.

    Our function submatrixSwap(matrix, new int[]{0, 2, 2, 4}, new int[]{2, 4, 0, 2}) should obtain the following swapped matrix:
    M = {
        {  1,   2, [11], [12],  5},
        {  6,   7, [16], [17], 10},
        {[3], [4],   13,   14, 15},
        {[8], [9],   18,   19, 20},
        { 21,  22,   23,   24, 25}
    };

    Explanation:
    In this scenario, the sub-matrix S1 spans rows 0 to 1 and columns 2 to 3 (0-indexed) and includes the elements 3, 4, 8, 9.
    The sub-matrix S2 spans rows 2 to 3 and columns 0 to 1 (0-indexed) and includes the elements 11, 12, 16, 17.

    The function submatrixSwap() swaps the positions of S1 and S2 within the matrix M.
    As a result, the columns 2 and 3 in rows 0 and 1 have been replaced by S2, and the columns 0 and 1 in rows 2 and 3 have been replaced by S1.
 */
public class SubMatrixSwap {
    public void submatrixSwap(int[][] matrix, int[] coord_S1, int[] coord_S2) {
        int[][] subMatrixForCoord_S1 = extractSubMatrix(matrix, coord_S1);
        int[][] subMatrixForCoord_S2 = extractSubMatrix(matrix, coord_S2);

        writeSubmatrix(matrix, subMatrixForCoord_S1, coord_S2);
        writeSubmatrix(matrix, subMatrixForCoord_S2, coord_S1);
    }

    protected int[][] extractSubMatrix(int[][] matrix, int[] coords){
        int startRow = coords[0];
        int endRow = coords[1] - 1; //In this exercise the value is more about the length than the position
        int startCol = coords[2];
        int endCol = coords[3] - 1; //In this exercise the value is more about the length than the position

        int numRows = endRow - startRow + 1;
        int numCols = endCol - startCol + 1;

        int[][] submatrix = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                submatrix[i][j] = matrix[startRow + i][startCol + j];
            }
        }
        return submatrix;
    }

    protected void writeSubmatrix(int[][] matrix, int[][] submatrix, int[] coords){
        int startRow = coords[0];
        int startCol = coords[2];

        for (int i = 0; i < submatrix.length; i++) {
            for (int j = 0; j < submatrix[0].length; j++) {
                matrix[startRow + i][startCol + j] = submatrix[i][j];
            }
        }
    }

    public static void main(String[] args){
        SubMatrixSwap sms = new SubMatrixSwap();
        int[][] matrix = {
                { 1,  2,  3,  4,  5},
                { 6,  7,  8,  9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        int[] coord_S1 = {0, 2, 0, 2};
        int[] coord_S2 = {3, 5, 0, 2};
        /*
        int[][] expected = {
            {16, 17, 3, 4, 5},
            {21, 22, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {1, 2, 18, 19, 20},
            {6, 7, 23, 24, 25}
        };
         */
        System.out.println("\nThe original Matrix:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        sms.submatrixSwap(matrix, coord_S1, coord_S2);
        System.out.println("Matrix after swapping:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        matrix = new int[][]{
                {1, 2},
                {3, 4}
        };
        coord_S1 = new int[]{0, 1, 0, 1};
        coord_S2 = new int[]{1, 2, 1, 2};
        /*
        int[][] expected = {
            {4, 2},
            {3, 1}};
         */
        System.out.println("\nThe original Matrix:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        sms.submatrixSwap(matrix, coord_S1, coord_S2);
        System.out.println("Matrix after swapping:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
