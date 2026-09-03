package org.codesignal.exercises.matrix.traverse;

/*
Columns Traverse

Given an m x n matrix, return all elements of the matrix traversing by columns from the last element of the last column.

Example 1:
    Input: matrix = [[1,2,3],
                     [4,5,6],
                     [7,8,9]]
    Output: [9,6,3,2,5,8,7,4,1]

 Example 2:
    Input: matrix = [[1,2,3,4],
                     [5,6,7,8],
                     [9,10,11,12]]
    Output: [12,8,4,3,7,11,10,6,2,1,5,9]

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100
 */

public class ColumnTraverse {

    int[] columnTraverse(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] output = new int[rows * cols];
        String direction = "up";
        int row = rows - 1;
        int col = cols - 1;
        int index = 0;

        while(index < rows * cols){
            output[index++] = matrix[row][col];
            if(direction.equals("up")){
                if(row - 1 < 0){ // I reached the top column
                    direction = "down"; //Change direction to downward
                    col--; //Move one column to the left
                }else{
                    row--;
                }
            }else{
                if(row + 1 == rows){ // I reached the bottom column
                    direction = "up"; //Change direction to upward
                    col--; //Move one column to the left
                }else{
                    row++;
                }
            }
        }

        return output;
    }

    public static void main(String[] args) {
        ColumnTraverse ct = new ColumnTraverse();
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
        int[] result = null;

        System.out.println("\nMatrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        result = ct.columnTraverse(matrix1);
        System.out.print(" ******* Column traverse: [");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == (result.length -1)? "]" : ", "));
        }
        System.out.println();

        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        result = ct.columnTraverse(matrix2);
        System.out.print(" ******* Column traverse: [");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == (result.length -1)? "]" : ", "));
        }
        System.out.println();
    }
}
