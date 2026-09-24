package org.codesignal.exercises.javahashsets;

import java.util.*;

/*
You will be given a string s consisting of lowercase English letters, and a character array letters.
Both the string s and the array letters have lengths ranging from $1$ to $1000000$.
Your task is to return an array containing the characters that are common to both the character string s and the character array letters.

Each element of the character string and each element in the array letters are characters ranging from a to z.

Suppose you are given a string:
    String s = "hello";

and an array of characters:
    char[] letters = {'h', 'a', 'e', 'i', 'o', 'u'};

Your function solution(s, letters) should sift through both the character array and the array,
extracting the common characters between them.

The expected output in this case should be:
    char[]{'e', 'h', 'o'};

You should return the result in alphabetical order.
 */

public class CommonCharacters {

    public char[] solution(String s, char[] letters) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> solutionSet = new TreeSet<>();

        for(int i = 0; i < s.length(); i ++){
            set1.add(s.charAt(i));
        }

        for(int j = 0; j < letters.length; j++){
            if(set1.contains(letters[j])){
                solutionSet.add(letters[j]);
            }
        }

        return solutionSet.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .toCharArray();
    }

    public static void main(String[] args) {
        CommonCharacters cc = new CommonCharacters();
        List<String> testCasesStr = new ArrayList<>();
        List<char[]> testCasesLetters = new ArrayList<>();
        List<char[]> testCasesExpectedResult = new ArrayList<>();

        testCasesStr.add("hello");
        testCasesLetters.add(new char[]{'h', 'a', 'e', 'i', 'o', 'u'});
        testCasesExpectedResult.add(new char[]{'e', 'h', 'o'});

        testCasesStr.add("world");
        testCasesLetters.add(new char[]{'w', 'o', 'r', 'l', 'd'});
        testCasesExpectedResult.add(new char[]{'d', 'l', 'o', 'r', 'w'});

        testCasesStr.add(new String(new char[500]).replace('\0', 'a'));
        testCasesLetters.add(new char[]{'a', 'b', 'c'});
        testCasesExpectedResult.add(new char[]{'a'});

        testCasesStr.add("");
        testCasesLetters.add(new char[]{'a', 'b', 'c'});
        testCasesExpectedResult.add(new char[]{});

        testCasesStr.add("python");
        testCasesLetters.add(new char[]{'a', 'b', 'c'});
        testCasesExpectedResult.add(new char[]{});

        for(int i = 0; i < testCasesStr.size(); i++){
            char[] result = cc.solution(testCasesStr.get(i), testCasesLetters.get(i));
            System.out.printf("%d.- Input string: [%s], input letters: [%s] --> Common sorted characters --> Expected: [%s], calculated: [%s]%n",
                    i + 1, testCasesStr.get(i), new String(testCasesLetters.get(i)), new String(testCasesExpectedResult.get(i)), new String(result));
        }
    }
}