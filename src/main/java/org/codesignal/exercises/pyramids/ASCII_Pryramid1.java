package org.codesignal.exercises.pyramids;

public class ASCII_Pryramid1 {
    public static void main(String[] args) {
        ASCII_Pryramid1 sol = new ASCII_Pryramid1();
        sol.pyramid(21);
    }

    void pyramid(int n_levels){
        if(n_levels <= 0){
            throw new IllegalArgumentException("Number of levels must be positive");
        }
        for(int actual_level = 1; actual_level <= n_levels; actual_level++){
            int spaces = n_levels - actual_level;
            int asterisks = (actual_level * 2) - 1;
            for(int space = 1; space <= spaces; space++){
                System.out.print("_");
            }
            for(int asterisk = 1; asterisk <= asterisks; asterisk++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
