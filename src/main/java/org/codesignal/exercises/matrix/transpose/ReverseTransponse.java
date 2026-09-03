package org.codesignal.exercises.matrix.transpose;

public class ReverseTransponse {

    public static int[][] transformMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0][0];

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        // TODO: Modify the loop to transpose the matrix in reverse order
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                transposed[cols - 1 - j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    public static int[][] transformMatrix1(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0][0];

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        // TODO: Modify the loop to transpose the matrix in reverse order
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                transposed[j][i] = matrix[i][j];
            }
        }

        //Reflect the matrix horizontally
        for (int i = 0; i < transposed.length; i++) {
            for (int j = 0; j < transposed[0].length / 2; j++) {
                int temp = transposed[i][j];
                transposed[i][j] = transposed[i][transposed[0].length - 1 - j];
                transposed[i][transposed[0].length - 1 - j] = temp;
            }
        }
        return transposed;
    }

    public static void main(String[] args) {
        int[][] seatingChart = {
                {101, 102, 103, 104},
                {201, 202, 203, 204},
                {301, 302, 303, 304}
        };

        int[][] matrixTransposed = ReverseTransponse.transformMatrix(seatingChart);
        System.out.println("Transposed Matrix 1:");
        for(int i = 0; i < matrixTransposed.length; i++){
            for(int j = 0; j < matrixTransposed[0].length; j++){
                System.out.print(matrixTransposed[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
