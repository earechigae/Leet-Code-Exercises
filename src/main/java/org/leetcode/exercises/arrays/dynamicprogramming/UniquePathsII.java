package org.leetcode.exercises.arrays.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] pathAgr = new int[obstacleGrid.length][obstacleGrid[0].length];

        //If the top-left corner or the bottom right corners have an obstacle, there are no more possible paths
        if(obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1){
            return 0;
        }

        pathAgr[0][0] = 1; //We initialize the first cell with a value of 1 possible path

        for(int i = 0; i < obstacleGrid.length; i++){
            for(int j = 0; j < obstacleGrid[0].length; j++){
                //If there is an obstacle the number of paths to get into that cell is Zero
                if(obstacleGrid[i][j] == 1){
                    pathAgr[i][j] = 0;
                }else if(i == 0 || j == 0){ // I am at an edge of the matrix (top row or left-most column)
                        if(i == 0 && j != 0){ //If I am traversing the top row
                            pathAgr[i][j] = pathAgr[i][j - 1]; //The only previous cell is the one on the left
                        }else
                            if(i != 0 && j == 0){ //If I am traversing the left-most column
                                pathAgr[i][j] = pathAgr[i-1][j];  //The only previous cell is the upper one
                        }
                }else{
                    //If there is no edge case or an obstacle, the number of paths to get into this cell is:
                    //numPaths =  num paths of previous left cell + num paths of previous upper cell
                    pathAgr[i][j] = pathAgr[i][j - 1] + pathAgr[i - 1][j];
                }
            }
        }

        return pathAgr[pathAgr.length - 1][pathAgr[0].length -1];
    }

    public static void main(String[] args){
        UniquePathsII solution = new UniquePathsII();
        List<int[][]> testCases = new ArrayList<>();
        testCases.add(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        });
        testCases.add(new int[][]{
                {0,1},
                {0,0}
        });
        testCases.add(new int[][]{
                {1,0,0},
                {0,0,0},
                {0,0,0}
        });
        testCases.add(new int[][]{
                {0,0,0,0},
                {0,0,1,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,1,0,0}
        });

        for(int z = 0; z < testCases.size(); z++){
            int m = testCases.get(z).length;
            int n = testCases.get(z)[0].length;
            int [][] obstacleGrid = testCases.get(z);
            System.out.printf("%nMatrix %d of %d x %d where a value of 1 in a cell is an obstacle %n", z + 1, m, n);
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    System.out.print(obstacleGrid[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.printf("The Number of unique paths for a %d x %d matrix from top-left[0][0] to right-bottom[%d][%d]"
                    + " considering a value of 1 as an obstacle: %d%n",
                    m, n, m - 1, n - 1, solution.uniquePathsWithObstacles(obstacleGrid));
        }
    }
}
