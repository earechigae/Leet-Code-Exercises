package org.leetcode.exercises.strings;

/*
3. Longest Substring Without Repeating Characters (https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)

Given a string s, find the length of the longest without duplicate characters.

Example 1:
    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

Example 2:
    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.

Example 3:
    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:
    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.

 */

import java.util.HashMap;
import java.util.Map;

public class LongestNonRepeatingSubstring {
    // Best solution O(n) using the sliding window technique
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        for(int right = 0, left = 0; right < s.length(); right++) {
            int indexOfFisrtAppearanceInSubstring = s.indexOf(s.charAt(right), left);

            //The character is repeated in the substring
            if(indexOfFisrtAppearanceInSubstring != right) {
                left = indexOfFisrtAppearanceInSubstring + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);

        }

        return maxLength;
    }


    // Better solution O(n) using the sliding window technique and a Map
    public int lengthOfLongestSubstringBetterSolution(String s) {
        int maxLength = 0;
        Map<Character, Integer> visitedCharacters = new HashMap<>();

        for(int right = 0, left = 0; right < s.length(); right++) {
            char currentCharacter = s.charAt(right);
            //The character is repeated in the substring
            if(visitedCharacters.containsKey(currentCharacter) && visitedCharacters.get(currentCharacter) >= left){
                left = visitedCharacters.get(currentCharacter) + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            visitedCharacters.put(currentCharacter, right);
        }

        return maxLength;
    }

    //This is the brute force O(n^2) solution
    public int lengthOfLongestSubstringNotGreat(String s) {
        int maxLength = 0;

        for(int i = 0; i < s.length(); i++){
            StringBuilder currentSubstring = new StringBuilder();
            for(int j = 1; j < s.length(); j++){
                if(currentSubstring.indexOf(String.valueOf(s.charAt(j))) != -1){
                    break;
                }
                currentSubstring.append(s.charAt(j));
                maxLength = Math.max(maxLength, currentSubstring.length());
            }

        }

        return maxLength;
    }

    public static void main(String ars[]){
        int lenght = 0;
        String input = null;
        LongestNonRepeatingSubstring longestNonRepeatingSubstring = new LongestNonRepeatingSubstring();

        input = "abcabcbb";
        lenght = longestNonRepeatingSubstring.lengthOfLongestSubstring(input);
        System.out.println("The longest substring without duplicate characters of string \"" + input + "\" is " + lenght);

        input = "bbbbb";
        lenght = longestNonRepeatingSubstring.lengthOfLongestSubstring(input);
        System.out.println("The longest substring without duplicate characters of string \"" + input + "\" is " + lenght);

        input = "pwwkew";
        lenght = longestNonRepeatingSubstring.lengthOfLongestSubstring(input);
        System.out.println("The longest substring without duplicate characters of string \"" + input + "\" is " + lenght);
    }
}

