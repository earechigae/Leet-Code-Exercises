package org.leetcode.exercises.strings;

/*
438. Find All Anagrams in a String (https://leetcode.com/problems/find-all-anagrams-in-a-string/description/)
Solution: https://www.youtube.com/watch?v=egPDpu26q0M

Given two strings s and p, return an array of all the start indices of p's in s. You may return the answer in any order.
Example 1:
    Input: s = "cbaebabacd", p = "abc"
    Output: [0,6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
    Input: s = "abab", p = "ab"
    Output: [0,1,2]
    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:
    1 <= s.length, p.length <= 3 * 104
    s and p consist of lowercase English letters.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        List<Integer> result = new ArrayList<>();

        //An approach to think bout an anagram is that the character frequency is the same in the string comparison
        //Count frequency of characters in p
        for(char c: p.toCharArray()){
            pCount[c - 'a']++;
        }

        //Sliding window to count frequency of character in s
        for(int i = 0; i < s.length(); i++){
            sCount[s.charAt(i) - 'a']++;

            //Remove the character that is out of the window
            if(i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }

            //Compare frequency arrays
            if(Arrays.equals(pCount, sCount)){
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    public static void main(String[] args){
        String s = "cbaebabacd", p = "abc";
        List<Integer> result = null;
        FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();

        System.out.print("\n* The character positions where there are anagrams of string \"" + p + "\" in string \"" + s +
                "\" are: [");
        result = findAllAnagramsInAString.findAnagrams(s, p);
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + (i == (result.size() -1)? "]" : ", "));
        }

        s = "abab";
        p = "ab";
        System.out.print("\n* The character positions where there are anagrams of string \"" + p + "\" in string \"" + s +
                "\" are: [");
        result = findAllAnagramsInAString.findAnagrams(s, p);
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + (i == (result.size() -1)? "]" : ", "));
        }
    }
}
