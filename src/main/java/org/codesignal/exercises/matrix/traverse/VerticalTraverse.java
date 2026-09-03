package org.codesignal.exercises.matrix.traverse;

/*
Vertical Traverse

Given an m x n matrix, return all elements of the matrix traversing by columns vertically from the last element of the last column.

Example 1:
    Input: matrix = [[1,2,3],
                     [4,5,6],
                     [7,8,9]]
    Output: [9,6,3,8,5,2,7,4,1]

 Example 2:
    Input: matrix = [[1,2,3,4],
                     [5,6,7,8],
                     [9,10,11,12]]
    Output: [12,8,4,11,7,3,10,6,2,9,5,1]

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100
 */

import java.util.ArrayList;
import java.util.List;

public class VerticalTraverse {
    public List<Integer> verticalTraverse(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Integer> result = new ArrayList<>();

        // TODO: Append each element's value to the result list by following the vertical pattern.
        for(int col = cols - 1; col >= 0; --col){
            for(int row = rows - 1; row >= 0; --row){
                result.add(matrix[row][col]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        VerticalTraverse vt = new VerticalTraverse();
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
        List<Integer>  result = null;

        System.out.println("\nMatrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        result = vt.verticalTraverse(matrix1);
        System.out.print(" ******* Column traverse: [");
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + (i == (result.size() -1)? "]" : ", "));
        }
        System.out.println();

        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        result = vt.verticalTraverse(matrix2);
        System.out.print(" ******* Column traverse: [");
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + (i == (result.size() -1)? "]" : ", "));
        }
        System.out.println();
    }
}
