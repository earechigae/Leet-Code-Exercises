package org.leetcode.exercises.matrix.sudoku;

/*
36. Valid Sudoku (https://leetcode.com/problems/valid-sudoku/description/)

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
    1. Each row must contain the digits 1-9 without repetition.
    2. Each column must contain the digits 1-9 without repetition.
    3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
    1. A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    2. Only the filled cells need to be validated according to the mentioned rules.

Example 1:
    Input: board =
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: true

Example 2:
    Input: board =
    [["8","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: false
    Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
    Since there are two 8's in the top left 3x3 sub-box, it is invalid.

Constraints:
    board.length == 9
    board[i].length == 9
    board[i][j] is a digit 1-9 or '.'.
 */

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                char num = board[i][j];

                if(num != '.'){
                    if(!seen.add(num + " at row " + i) ||
                        !seen.add(num + " at col " + j) ||
                        !seen.add(num + " at box [" + i/3 + "][" + j/3 + "]")){
                        return false;
                    }
                }

            }
        }

        return true;
    }

    public static void main(String args[]){
        ValidSudoku validSudoku = new ValidSudoku();
        int boxI = 0, boxJ = 0;
        char[][] matrix1 = {
                {'5', '3', '.',  '.', '7', '.',  '.', '.', '.'},
                {'6', '.', '.',  '1', '9', '5',  '.', '.', '.'},
                {'.', '9', '8',  '.', '.', '.',  '.', '6', '.'},

                {'8', '.', '.',  '.', '6', '.',  '.', '.', '3'},
                {'4', '.', '.',  '8', '.', '3',  '.', '.', '1'},
                {'7', '.', '.',  '.', '2', '.',  '.', '.', '6'},
                
                {'.', '6', '.',  '.', '.', '.',  '2', '8', '.'},
                {'.', '.', '.',  '4', '1', '9',  '.', '.', '5'},
                {'.', '.', '.',  '.', '8', '.',  '.', '7', '9'},
        };

        char [][] matrix2 = {
                {'8', '3', '.',  '.','7','.',  '.','.','.'},
                {'6', '.', '.',  '1','9','5',  '.','.','.'},
                {'.', '9', '8',  '.','.','.',  '.','6','.'},
                
                {'8', '.', '.',  '.','6','.',  '.','.','3'},
                {'4', '.', '.',  '8','.','3',  '.','.','1'},
                {'7', '.', '.',  '.','2','.',  '.','.','6'},
                
                {'.', '6', '.',  '.','.','.',  '2','8','.'},
                {'.', '.', '.',  '4','1','9',  '.','.','5'},
                {'.', '.', '.',  '.','8','.',  '.','7','9'}
        };

        System.out.println("Matrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            if(boxI != i/3 && i/3 != 0){
                System.out.println("\n");
            }
            for(int j = 0; j < matrix1[0].length; j++){
                if(boxJ != j/3 && j/3 != 0){
                    System.out.print("\t\t");
                }
                System.out.print(matrix1[i][j] + "\t");
                boxJ = j/3;
            }

            System.out.println();
            boxI = i/3;
        }
        System.out.println("\nIs matrix 1 a valid sudoku?: " + validSudoku.isValidSudoku(matrix1) + "\n\n");

        System.out.println("Matrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            if(boxI != i/3 && i/3 != 0){
                System.out.println("\n");
            }
            for(int j = 0; j < matrix2[0].length; j++){
                if(boxJ != j/3 && j/3 != 0){
                    System.out.print("\t\t");
                }
                System.out.print(matrix2[i][j] + "\t");
                boxJ = j/3;
            }

            System.out.println();
            boxI = i/3;
        }
        System.out.println("\nIs matrix 2 a valid sudoku?: " + validSudoku.isValidSudoku(matrix2) + "\n\n");
    }
}
