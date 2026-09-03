package org.leetcode.exercises.matrix.toeplitz;

/*
766. Toeplitz Matrix

Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

Example 1:
    Input: matrix = [[1,2,3,4],
                     [5,1,2,3],
                     [9,5,1,2]]
    Output: true
    Explanation:
    In the above grid, the diagonals are:
    "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
    In each diagonal all elements are the same, so the answer is True.

Example 2:
    Input: matrix = [[1,2],
                     [2,2]]
    Output: false
    Explanation:
    The diagonal "[1, 2]" has different elements.

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 20
    0 <= matrix[i][j] <= 99
 */

public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int r = rows - 1, c = 0;
        int prevValue = matrix[r][c];

        //System.out.println();
        for(int i = 0; i < (rows * cols); i++) {
            //System.out.print(matrix[r][c] + "\t");
            if(prevValue != matrix[r][c]) return false;

            // Downward direction
            if ((r + c) % 2 == 0) {
                // If we are at the last row and not at the last column, we can only go right
                if(r == (rows - 1) && c != (cols - 1) ){
                    c++;
                    // There is a diagonal change
                    prevValue = (r >= 0 && r < rows && c >=0 && c < cols)? matrix[r][c] : 0;
                }
                // If we are at the last colum, we can only go up
                else if(c == (cols - 1)){
                    r--;
                    // There is a diagonal change
                    prevValue = (r >= 0 && r < rows && c >=0 && c < cols)? matrix[r][c] : 0;
                }
                // Otherwise, we can go down diagonally
                else {
                    r++;
                    c++;
                }
            } else { // Upward direction
                // If we are at the first column and not at the first row, we can only go up
                if (c == 0 && r != 0) {
                    r--;
                    // There is a diagonal change
                    prevValue = (r >= 0 && r < rows && c >=0 && c < cols)? matrix[r][c] : 0;
                }
                // If we are at the first row and not at the first column, we can only go right
                else if (r == 0 && c != (cols -1)) {
                    c++;
                    // There is a diagonal change
                    prevValue = (r >= 0 && r < rows && c >=0 && c < cols)? matrix[r][c] : 0;
                }
                // Otherwise, we can go up diagonally
                else {
                    r--;
                    c--;
                }
            }
        }

        return true;
    }

    public boolean isToeplitzMatrixQuickerMethod(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++)
            for (int j = 1; j < matrix[0].length; j++)
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
        return true;
    }

    public static void main(String args[]){
        ToeplitzMatrix tm = new ToeplitzMatrix();
        int[][] matrix1 = {
                {1, 2},
                {2, 2}
        };

        int[][] matrix2 = {
                {1,  2,  4,  6},
                {3,  1,  2,  4},
                {5,  3,  1,  2},
                {7,  5,  3,  1}
        };

        int[][] matrix3 = {
                {1, 2, 4},
                {3, 1, 2},
                {5, 3, 1}
        };


        System.out.println("Matrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nIs matrix 1 Toeplitz?: " + tm.isToeplitzMatrix(matrix1));

        System.out.println("\n\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nIs matrix 2 Toeplitz?: " + tm.isToeplitzMatrix(matrix2));


        System.out.println("\n\nMatrix 3:");
        for(int i = 0; i < matrix3.length; i++){
            for(int j = 0; j < matrix3[0].length; j++){
                System.out.print(matrix3[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nIs matrix 2 Toeplitz?: " + tm.isToeplitzMatrix(matrix3));

    }

}
