package org.leetcode.exercises.matrix.spiral;
import java.util.ArrayList;
import java.util.List;

/*
54. Spiral Matrix

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
    Input: matrix = [[1,2,3],
                     [4,5,6],
                     [7,8,9]]
    Output: [1,2,3,6,9,8,7,4,5]

 Example 2:
    Input: matrix = [[1,2,3,4],
                     [5,6,7,8],
                     [9,10,11,12]]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100
 */

public class SpiralMatrix01 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int i = 0, j = 0;
        List <Integer> output = new ArrayList<>();
        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;

        while((left <= right) && (top <= bottom)){
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
        SpiralMatrix01 sm = new SpiralMatrix01();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println("\nMatrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(" ******* Spiral traverse: " + sm.spiralOrder(matrix1)); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(" ******* Spiral traverse: " + sm.spiralOrder(matrix2));
    }
}

