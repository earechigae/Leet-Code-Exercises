package org.codesignal.exercises.matrix.traverse.diagonal;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMatrixTraversal {
    public static List<Integer> diagonalTraverseAndSquares(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        List<Integer> traversal = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        int row = 0, col = 0, dir = 1;
        for (int i = 0; i < rows * cols; ++i) {  // Loop runs for the total number of cells in the matrix.
            traversal.add(matrix[row][col]);  // Append the current cell value to traversal.

            // Logic to control direction based on edges:
            if (dir == 1) {  // Moving down-left
                if (row == rows - 1) {
                    col += 1;
                    dir = -1;
                } else if (col == 0) {
                    row += 1;
                    dir = -1;
                } else {
                    row += 1;
                    col -= 1;
                }
            } else {  // Moving up-right
                if (col == cols - 1) {
                    row += 1;
                    dir = 1;
                } else if (row == 0) {
                    col += 1;
                    dir = 1;
                } else {
                    row -= 1;
                    col += 1;
                }
            }
        }

        for (int idx = 0; idx < traversal.size(); ++idx) {
            int val = traversal.get(idx);
            int root = (int) Math.sqrt(val);
            if (root * root == val) {  // Check if the value is a perfect square number.
                results.add(idx);
            }
        }

        return results;
    }

    public static void main(String args[]){
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(diagonalTraverseAndSquares(matrix));
    }
}
