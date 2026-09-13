package org.codesignal.exercises.matrix.submatrices;

import java.util.ArrayList;
import java.util.List;

/*
You are tasked with creating a Java method named matrixBoundaryConcatenation().
This method should accept two 2D matrices, matrixA and matrixB, and the number of boundary layers, n,
to extract from both matrices.

In this context, a boundary layer refers to the elements that form the outer contour of a matrix.
For instance, the first layer of the following 4x4 matrix includes the elements
1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, and 5:

    1  2  3  4
    5  6  7  8
    9  10 11 12
    13 14 15 16

Your method should extract the first n boundary layers from both matrixA and matrixB.
It should then concatenate these extracted layers into a new list,
ensuring that the layers from matrixA precede those from matrixB in the resultant list.

The matrices matrixA and matrixB will be square matrices, with each side's length ranging from 1 to 10.
The number of layers n will be less than or equal to the side length of the square matrices.

The elements in the input matrices can be any integer between -100 and 100.

Example
    Consider the following input to our method:

 */
public class MatrixBoundaryConcatenation {
    public List<Integer> matrixBoundaryConcatenation(int[][] matrixA, int[][] matrixB, int n) {
        List<Integer> result = new ArrayList<>();

        // Extract boundary layers from matrixA
        result.addAll(spiralOrderByLayers(matrixA, n));

        // Extract boundary layers from matrixB
        result.addAll(spiralOrderByLayers(matrixB, n));

        // merges them into a single list and then returns this new list.
        return result;
    }

    protected List<Integer> spiralOrderByLayers(int[][] matrix, int maxLayers) {
        int i = 0, j = 0;
        List <Integer> output = new ArrayList<>();
        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;
        int currentLayer = 0;

        while((left <= right) && (top <= bottom)){
            currentLayer++;
            if(currentLayer > maxLayers){
                break;
            }
            // From left to right
            for(j = left; j <= right; j++){
                output.add(matrix[top][j]);
            }
            top++;

            //From top to bottom
            for(i = top; i <= bottom; i++){
                output.add(matrix[i][right]);
            }
            right--;

            // From right to left
            if(top <= bottom) {
                for (j = right; j >= left; j--) {
                    output.add(matrix[bottom][j]);
                }
                bottom--;
            }

            // From bottom to top
            if(left <= right) {
                for (i = bottom; i >= top; i--) {
                    output.add(matrix[i][left]);
                }
                left++;
            }
        }

        return output;
    }

    public static void main(String[] args) {
        MatrixBoundaryConcatenation solution = new MatrixBoundaryConcatenation();
        // Example usage
        int[][] matrixA = {
                {1, 2},
                {3, 4}};
        int[][] matrixB = {
                {5, 6},
                {7, 8}};
        int n = 1;

        System.out.println("Matrix A:");
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixA[0].length; j++){
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("Matrix B:");
        for(int i = 0; i < matrixB.length; i++){
            for(int j = 0; j < matrixB[0].length; j++){
                System.out.print(matrixB[i][j] + "\t");
            }
            System.out.println();
        }

        List<Integer> result = solution.matrixBoundaryConcatenation(matrixA, matrixB, n);
        System.out.println("The concatenation of " + n + " boundary layers for matrices A and B is: " + result + "\n");


        matrixA = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        matrixB = new int[][]{
                {10, 11, 12},
                {13, 14, 15},
                {16, 17, 18}};
        n = 2;

        System.out.println("Matrix A:");
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixA[0].length; j++){
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("Matrix B:");
        for(int i = 0; i < matrixB.length; i++){
            for(int j = 0; j < matrixB[0].length; j++){
                System.out.print(matrixB[i][j] + "\t");
            }
            System.out.println();
        }
        result = solution.matrixBoundaryConcatenation(matrixA, matrixB, n);
        System.out.println("The concatenation of " + n + " boundary layers for matrices A and B is: " + result + "\n");


        matrixA = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        matrixB = new int[][] {
                {26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35},
                {36, 37, 38, 39, 40},
                {41, 42, 43, 44, 45},
                {46, 47, 48, 49, 50}};
        n = 3;

        System.out.println("Matrix A:");
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixA[0].length; j++){
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("Matrix B:");
        for(int i = 0; i < matrixB.length; i++){
            for(int j = 0; j < matrixB[0].length; j++){
                System.out.print(matrixB[i][j] + "\t");
            }
            System.out.println();
        }
        result = solution.matrixBoundaryConcatenation(matrixA, matrixB, n);
        System.out.println("The concatenation of " + n + " boundary layers for matrices A and B is: " + result + "\n");
    }
}
