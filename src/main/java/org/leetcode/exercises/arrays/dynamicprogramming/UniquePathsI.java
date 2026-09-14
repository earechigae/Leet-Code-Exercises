package org.leetcode.exercises.arrays.dynamicprogramming;

/*
62. Unique Paths I (https://leetcode.com/problems/unique-paths/description/)

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:{
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 }
    }
    Input: m = 3, n = 7
    Output: 28

Example 2:{
        {0, 0}
        {0, 0}
        {0, 0}
    }
    Input: m = 3, n = 2
    Output: 3
    Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    1. Right -> Down -> Down
    2. Down -> Down -> Right
    3. Down -> Right -> Down

Constraints:
    1 <= m, n <= 100
 */

public class UniquePathsI {
    public int uniquePaths(int m, int n) {
        int[][] pathAgr = new int[m][n];

        for(int i = 0; i < pathAgr.length; i++){
            for(int j = 0; j < pathAgr[0].length; j++){
                if((i - 1) < 0 || (j - 1) < 0){ // I am at an edge of the matrix
                    pathAgr[i][j] = 1; //We initialize the cell with a value of 1 possible path
                }else{
                    //If there is no edge case, the number of paths to get into this cell is:
                    //numPaths = num paths of previous upper cell + num paths of previous left cell
                    pathAgr[i][j] = pathAgr[i][j - 1] + pathAgr[i - 1][j];
                }
            }
        }

        return pathAgr[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePathsI solution = new UniquePathsI();
        int[] m = {3, 3, 1}, n = {7, 2, 1};

        for(int z = 0; z < m.length; z++){
            System.out.printf("%nMatrix %d of %d x %d:%n", z + 1, m[z], n[z]);
            for(int i = 0; i < m[z]; i++){
                for(int j = 0; j < n[z]; j++){
                    System.out.print("0\t");
                }
                System.out.println();
            }
            System.out.printf("The Number of unique paths for a %d x %d matrix from top-left[0][0] to right-bottom[%d][%d]: %d%n",
                    m[z], n[z], m[z] - 1, n[z] - 1, solution.uniquePaths(m[z], n[z]));
        }
    }
}
