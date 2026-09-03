package org.codesignal.exercises.matrix.traverse.grid;

public class AllDirectionsTraversal {
    public static Integer findNextUphill(int[][] grid, int row, int col) {
        // Up, down, left, right
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {1, -1}, {-1, 1}, {1, 1} //Up-Left, Down-Left, Up-Right, Down-Right
        };
        int nextVal = grid[row][col];
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                    && grid[newRow][newCol] > nextVal) {
                nextVal = grid[newRow][newCol];
            }
        }
        return nextVal != grid[row][col] ? nextVal : null;
    }

    public static void main(String[] args) {
        int[][] trailGrid = {
                {1, 2, 3},
                {6, 5, 8},
                {7, 4, 9}
        };
        int startRow = 1, startCol = 1;
        // Prints the value uphill from the start position or null if there's no uphill
        System.out.println(findNextUphill(trailGrid, startRow, startCol));
    }
}
