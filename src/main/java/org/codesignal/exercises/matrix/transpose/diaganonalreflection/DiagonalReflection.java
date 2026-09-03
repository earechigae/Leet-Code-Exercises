package org.codesignal.exercises.matrix.transpose.diaganonalreflection;

public class DiagonalReflection {
    public static int[][] reflectOverSecondaryDiagonal(int[][] matrix) {
        int size = matrix.length;
        int[][] newMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newMatrix[size - 1 - j][size - 1 - i] = matrix[i][j];
            }
        }
        /*
        // Loop through the elements strictly above the secondary diagonal
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                // Calculate the target reflection coordinates
                int targetRow = size - 1 - j;
                int targetCol = size - 1 - i;

                // Swap matrix[i][j] with matrix[targetRow][targetCol]
                int temp = matrix[i][j];
                newMatrix[i][j] = matrix[targetRow][targetCol];
                newMatrix[targetRow][targetCol] = temp;
            }
        }
        */

        return newMatrix;
    }

    public static void main(String[] args) {
        // Example square matrix to reflect over the secondary diagonal
        int[][] squareMatrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] transformedMatrix = DiagonalReflection.reflectOverSecondaryDiagonal(squareMatrix);
        System.out.println("\n\nTransformed Matrix:");
        for(int i = 0; i < transformedMatrix.length; i++){
            for(int j = 0; j < transformedMatrix[0].length; j++){
                System.out.print(transformedMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
