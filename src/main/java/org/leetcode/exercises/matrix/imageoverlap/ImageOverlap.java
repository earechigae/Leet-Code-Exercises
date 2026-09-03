package org.leetcode.exercises.matrix.imageoverlap;

/*
835. Image Overlap

You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top
of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
Return the largest possible overlap.

Example 1:
    Input: img1 = [[1,1,0],
                   [0,1,0],
                   [0,1,0]],
           img2 = [[0,0,0],
                   [0,1,1],
                   [0,0,1]]
    Output: 3
    Explanation: We translate img1 to right by 1 unit and down by 1 unit.
    he number of positions that have a 1 in both images is 3 (shown in red).

Example 2:
    Input: img1 = [[1]], img2 = [[1]]
    Output: 1

Example 3:
    Input: img1 = [[0]], img2 = [[0]]
    Output: 0

Constraints:
    n == img1.length == img1[i].length
    n == img2.length == img2[i].length
    1 <= n <= 30
    img1[i][j] is either 0 or 1.
    img2[i][j] is either 0 or 1.
 */

import java.util.List;
import java.util.ArrayList;

public class ImageOverlap {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> pos1 = new ArrayList<>();
        List<int[]> pos2 = new ArrayList<>();
        int n = img1.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(img1[i][j] == 1){
                    pos1.add(new int[]{i,j});
                }
                if(img2[i][j] == 1){
                    pos2.add(new int[]{i,j});
                }
            }
        }
        int ans=0;
        int[][] freq = new int[2*n-1][2*n-1];
        for(int[] p1: pos1){
            for(int[] p2: pos2){
                int r = p1[0] - p2[0];
                int c = p1[1] - p2[1];
                freq[r+n-1][c+n-1]++;
                ans=Math.max(ans,freq[r+n-1][c+n-1]);
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        ImageOverlap imgOverlap = new ImageOverlap();
        int[][] matrixA = {
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        int[][] matrixB = {
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };

        int[][] matrixA1 = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] matrixB1 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}
        };

        System.out.println("\nMatrix A:");
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nMatrix B:");
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                System.out.print(matrixB[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nMaximum image overlap between A & B: " + imgOverlap.largestOverlap(matrixA, matrixB));

        System.out.println("\nMatrix A1:");
        for (int i = 0; i < matrixA1.length; i++) {
            for (int j = 0; j < matrixA1[0].length; j++) {
                System.out.print(matrixA1[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nMatrix B1:");
        for (int i = 0; i < matrixB1.length; i++) {
            for (int j = 0; j < matrixB1[0].length; j++) {
                System.out.print(matrixB1[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nMaximum image overlap between A1 & B1: " + imgOverlap.largestOverlap(matrixA1, matrixB1));
    }
}