package org.codesignal.exercises.matrix.positions.gamepieces;

public class CountSubmatricesWithE_Corners {
    public static int countSubmatricesWithE(char[][] board) {
        // TODO: Initialize a count variable to keep track of 3x3 submatrices with 'E's in all four corners
        int submatricesCount = 0;
        int rows = board.length;
        int cols = board[0].length;

        // TODO: Use a nested loop to go through each element that can be the top-left corner of a 3x3 submatrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // TODO: Check if the current 3x3 submatrix has 'E's in all four corners
                // If it does, increment the count
                if ((i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == 'E') &&  // Up-Left
                    (i - 1 >= 0 && j + 1 < cols && board[i - 1][j + 1] == 'E') &&  // Up-Right
                    (i + 1 < rows && j - 1 >= 0 && board[i + 1][j - 1] == 'E') &&  // Down-Left
                    (i + 1 < rows && j + 1 < cols && board[i + 1][j + 1] == 'E')) {  // Down-Right
                    submatricesCount++;
                }
            }
        }

        // TODO: Return the count of submatrices with 'E's in all four corners
        return submatricesCount;
    }

    public static void main(String[] args) {
        // Test case 1: Original board with 2 valid submatrices
        char[][] board1 = {
                {'E', 'P', 'E', 'P'},
                {'P', 'E', 'P', 'E'},
                {'E', 'P', 'E', 'P'},
                {'P', 'E', 'P', 'E'}
        };
        int result1 = countSubmatricesWithE(board1);
        System.out.println("Test 1: " + result1);
        assert result1 == 2 : "Expected 2, but got " + result1;

        // Test case 2: Board with no valid submatrices
        char[][] board2 = {
                {'P', 'P', 'P', 'P'},
                {'P', 'E', 'E', 'P'},
                {'P', 'E', 'E', 'P'},
                {'P', 'P', 'P', 'P'}
        };
        int result2 = countSubmatricesWithE(board2);
        System.out.println("Test 2: " + result2);
        assert result2 == 0 : "Expected 0, but got " + result2;

        // Test case 3: Minimal 3x3 board with E's in all corners
        char[][] board3 = {
                {'E', 'P', 'E'},
                {'P', 'P', 'P'},
                {'E', 'P', 'E'}
        };
        int result3 = countSubmatricesWithE(board3);
        System.out.println("Test 3: " + result3);
        assert result3 == 1 : "Expected 1, but got " + result3;

        // Test case 4: Board with all E's
        char[][] board4 = {
                {'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E'}
        };
        int result4 = countSubmatricesWithE(board4);
        System.out.println("Test 4: " + result4);
        assert result4 == 4 : "Expected 4, but got " + result4;

        // Test case 5: Larger board with multiple valid submatrices
        char[][] board5 = {
                {'E', 'P', 'E', 'P', 'E'},
                {'P', 'P', 'P', 'P', 'P'},
                {'E', 'P', 'E', 'P', 'E'},
                {'P', 'P', 'P', 'P', 'P'},
                {'E', 'P', 'E', 'P', 'E'}
        };
        int result5 = countSubmatricesWithE(board5);
        System.out.println("Test 5: " + result5);
        assert result5 == 4 : "Expected 4, but got " + result5;

        System.out.println("All tests passed!");
    }
}
