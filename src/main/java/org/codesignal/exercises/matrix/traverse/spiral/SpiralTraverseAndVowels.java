package org.codesignal.exercises.matrix.traverse.spiral;

import org.leetcode.exercises.matrix.spiral.SpiralMatrix01;

import java.util.List;
import java.util.ArrayList;

public class SpiralTraverseAndVowels {
    public int[] spiralTraverseAndVowels(char[][] grid) {
        int i = 0, j = 0;
        int left = 0, right = grid[0].length - 1;
        int top = 0, bottom = grid.length - 1;
        List<Character> spiral = new ArrayList<>();
        List<Integer> vowelsPositions = new ArrayList<>();

        while((left <= right) && (top <= bottom)){
            //From left to right
            for(j = left; j <= right; j++){
                spiral.add(grid[top][j]);
            }
            top++;

            //From top to bottom
            for(i = top; i <= bottom; i++){
                spiral.add(grid[i][right]);
            }
            right--;

            //From right to left
            if(top <= bottom) { // This if is necessary in case of matrices with ONLY one column
                for (j = right; j >= left; j--) {
                    spiral.add(grid[bottom][j]);
                }
                bottom--;
            }

            //From bottom to top
            if(left <= right) { // This if is necessary in case of matrices with ONLY one row
                for (i = bottom; i >= top; i--) {
                    spiral.add(grid[i][left]);
                }
                left++;
            }
        }

        for(int z = 0; z < spiral.size(); z++){
            if(spiral.get(z) == 'a' || spiral.get(z) == 'e' || spiral.get(z) == 'i' || spiral.get(z) == 'o' || spiral.get(z) == 'u'){
                vowelsPositions.add(z);
            }
        }

        return vowelsPositions.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String args[]){
        SpiralTraverseAndVowels stav = new SpiralTraverseAndVowels();
        int[] result = null;
        char[][] matrix1 = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'}
        };
        char[][] matrix2 = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };
        char[][] matrix3 = {
                {'o'},
                {'r'},
                {'a'},
                {'l'}
        };
        char[][] matrix4 = {
                {'a', 'b', 'c', 'd'}
        };


        System.out.println("\nMatrix 1:");
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                System.out.print(matrix1[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print(" ******* Spiral traverse where the positions contains vowels: [");
        result = stav.spiralTraverseAndVowels(matrix1);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == result.length - 1 ? "]\n" : ", "));
        }

        System.out.println("\nMatrix 2:");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print(" ******* Spiral traverse where the positions contains vowels: [");
        result = stav.spiralTraverseAndVowels(matrix2);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == result.length - 1 ? "]\n" : ", "));
        }

        System.out.println("\nMatrix 3:");
        for(int i = 0; i < matrix3.length; i++){
            for(int j = 0; j < matrix3[0].length; j++){
                System.out.print(matrix3[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print(" ******* Spiral traverse where the positions contains vowels: [");
        result = stav.spiralTraverseAndVowels(matrix3);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == result.length - 1 ? "]\n" : ", "));
        }

        System.out.println("\nMatrix 4:");
        for(int i = 0; i < matrix4.length; i++){
            for(int j = 0; j < matrix4[0].length; j++){
                System.out.print(matrix4[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print(" ******* Spiral traverse where the positions contains vowels: [");
        result = stav.spiralTraverseAndVowels(matrix4);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i] + (i == result.length - 1 ? "]\n" : ", "));
        }
    }
}
