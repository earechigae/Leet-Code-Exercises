package org.codesignal.exercises.matrix.traverse.grid;

import java.util.ArrayList;
import java.util.List;

public class GridTraversal {
    public static List<Integer> pathTraverse(int[][] grid, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check the validity of the input
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols) {
            System.err.println("Invalid input");
            return new ArrayList<>();
        }

        // Define all four possible directions of movement
        int[][] directions = {
                { -1, 0 },  // Up
                { 1, 0 },   // Down
                { 0, -1 },  // Left
                { 0, 1 }    // Right
        };

        // Start with the value at the starting cell
        List<Integer> visited = new ArrayList<>();
        visited.add(grid[startRow][startCol]);

        while (true) {
            // Initialize the current maximum as negative one
            int currMax = -1;
            int nextRow = -1, nextCol = -1;

            // Loop over each adjacent cell in all the directions
            for (int[] dir : directions) {
                // Calculate the new cell's row and column indices
                int newRow = startRow + dir[0];
                int newCol = startCol + dir[1];

                // If the new cell is out of the grid boundary, ignore it
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                // If the new cell's value is greater than the current maximum
                if (grid[newRow][newCol] > currMax) {
                    // Save it as the next cell to visit
                    nextRow = newRow;
                    nextCol = newCol;
                    currMax = grid[newRow][newCol];
                }
            }

            // If we don't have any valid cell to visit, break from the loop
            if (currMax <= grid[startRow][startCol]) {
                break;
            }

            // Otherwise, go to the next cell
            startRow = nextRow;
            startCol = nextCol;

            // Append the cell's value to the result list
            visited.add(currMax);
        }

        // Return the list of visited cells' values
        return visited;
    }

    public static List<int[]> pathTraverseCoordinates(int[][] grid, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check the validity of the input
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols) {
            System.err.println("Invalid input");
            return new ArrayList<>();
        }

        // Define all four possible directions of movement
        int[][] directions = {
                { -1, 0 },  // Up
                { 1, 0 },   // Down
                { 0, -1 },  // Left
                { 0, 1 }    // Right
        };

        // Start with the value at the starting cell
        List<int[]> visited = new ArrayList<>();
        visited.add(new int[]{startRow, startCol});

        while (true) {
            // Initialize the current maximum as negative one
            int currMax = -1;
            int nextRow = -1, nextCol = -1;
            int [] currMaxPosition = null;

            // Loop over each adjacent cell in all the directions
            for (int[] dir : directions) {
                // Calculate the new cell's row and column indices
                int newRow = startRow + dir[0];
                int newCol = startCol + dir[1];

                // If the new cell is out of the grid boundary, ignore it
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                // If the new cell's value is greater than the current maximum
                if (grid[newRow][newCol] > currMax) {
                    // Save it as the next cell to visit
                    nextRow = newRow;
                    nextCol = newCol;
                    currMax = grid[newRow][newCol];
                    currMaxPosition = new int[]{nextRow, nextCol};
                }
            }

            // If we don't have any valid cell to visit, break from the loop
            if (currMax <= grid[startRow][startCol]) {
                break;
            }

            // Otherwise, go to the next cell
            startRow = nextRow;
            startCol = nextCol;

            // Append the cell's value to the result list
            //visited.add(currMax);
            visited.add(currMaxPosition);
        }

        // Return the list of visited cells' values
        return visited;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> res = pathTraverse(grid, 1, 1);
        List<int[]> coordinates = pathTraverseCoordinates(grid, 1, 1);
        for (int val : res) {
            System.out.print(val + " ");
        }
        System.out.println();
        for (int[] coord : coordinates) {
            System.out.print("(" + coord[0] + "," + coord[1] + ") ");
        }
        System.out.println();
    }
}
