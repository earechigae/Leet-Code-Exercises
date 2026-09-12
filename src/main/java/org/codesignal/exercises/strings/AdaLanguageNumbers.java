package org.codesignal.exercises.strings;

public class AdaLanguageNumbers {

    public boolean solution(String line) {
        boolean atLeastOneDigit = false;
        if (line.charAt(line.length() - 1) == '#') {
            int i = 0;
            int base = 0;

            int firstHash = line.indexOf("#");
            if(firstHash == -1) {
                return false;
            }
            String baseString = line.substring(0, firstHash).replaceAll("_", "");
            try{
                base = Integer.parseInt(baseString);
            } catch (NumberFormatException e) {
                return false;
            }
            if(firstHash + 1 >= line.length() - 1){
                return false;
            }
            line = line.substring(firstHash + 1, line.length() - 1);
            if(line.length() == 1){
                line+="_";
            }

            if (base < 2 || base > 16) {
                return false;
            }
            while (i < line.length() - 1) {
                if (line.charAt(i) != '_') {
                    int digit = -1;
                    if ('a' <= line.charAt(i) && line.charAt(i) <= 'f') {
                        digit = (int)line.charAt(i) - (int)'a' + 10;
                    }
                    if ('A' <= line.charAt(i) && line.charAt(i) <= 'F') {
                        digit = (int)line.charAt(i) - (int)'A' + 10;
                    }
                    if ('0' <= line.charAt(i) && line.charAt(i) <= '9') {
                        digit = line.charAt(i) - (int)'0';
                    }
                    if (0 <= digit && digit < base) {
                        atLeastOneDigit = true;
                    }
                    else {
                        return false;
                    }
                }
                i++;
            }
        }
        else {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != '_') {
                    if ('0' <= line.charAt(i) && line.charAt(i) <= '9') {
                        atLeastOneDigit = true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return atLeastOneDigit;
    }

    public static void main(String args[]){
        String [] adaNumbers = {"123_456_789", "16#123abc#", "10#123abc#", "10#10#123ABC#", "10#0#", "10##", "16#1234567890ABCDEFabcdef#"};
        //String [] adaNumbers = { "10#0#" };
        AdaLanguageNumbers adaLanguageNumbers = new AdaLanguageNumbers();

        int line = 1;
        for (String adaNumber : adaNumbers) {
            System.out.println((line++) + ".- Is " + adaNumber + " a valid ADA Language number? " + adaLanguageNumbers.solution(adaNumber));
        }
    }
}
