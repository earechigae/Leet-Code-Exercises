package org.codesignal.exercises.matrix.positions.gamepieces;
import java.util.ArrayList;
import java.util.List;


public class PositionsFourDirections {
    public static List<int[]> findPositions(char[][] board) {
        List<int[]> positions = new ArrayList<>();

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'E') {
                    if ((i > 0 && board[i - 1][j] == 'E') ||  //Up
                            (i < rows - 1 && board[i + 1][j] == 'E') ||  //Down
                            (j > 0 && board[i][j - 1] == 'E') || // Left
                            (j < cols - 1 && board[i][j + 1] == 'E')) {  //Right
                        positions.add(new int[]{i, j});
                    }
                }
            }
        }
        return positions;
    }

    private static boolean isPositionStrategic(char[][] board, int row, int col) {
        // TODO: Complete the remaining conditions for down, left, and right
        return ((row > 0 && board[row - 1][col] == 'E') /* Up */ ||
                (row < board.length -1 && board[row + 1][col] == 'E') ||  /* Down */
                (col > 0 && board[row][col - 1] == 'E') /*Left*/ ||
                (col < board[0].length -1 && board[row][col + 1] == 'E')); /*Right*/
    }

    public static boolean evaluateMove(char[][] board, int row, int col) {
        // TODO: Check if a move to the given cell is possible; write a condition to check if the cell is empty.
        // TODO: Check if at least one neighboring cell is empty (not diagonally).
        return (((board[row][col] == 'E') &&
                ((row > 0 && board[row - 1][col] == 'E') /* Up */ ||
                        (row < board.length -1 && board[row + 1][col] == 'E') ||  /* Down */
                        (col > 0 && board[row][col - 1] == 'E') /*Left*/ ||
                        (col < board[0].length -1 && board[row][col + 1] == 'E') /*Right*/)));
    }

    public static void main(String[] args) {
        char[][] board = {
                {'P', 'E', 'E', 'P'},
                {'E', 'P', 'E', 'P'},
                {'P', 'E', 'P', 'P'},
                {'P', 'E', 'P', 'E'}
        };

        List<int[]> positions = findPositions(board);

        for (int[] pos : positions) {
            System.out.println("(" + pos[0] + ", " + pos[1] + ")");
        }
    }
}
