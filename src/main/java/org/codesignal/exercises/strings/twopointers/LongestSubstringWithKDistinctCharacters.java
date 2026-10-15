package org.codesignal.exercises.strings.twopointers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
You are tasked with an intriguing string manipulation problem. You are provided with a string s and an integer K.
The string s contains at most $100000$ alphanumeric (A-Za-z0-9) characters and has at least one character.
The integer K ranges from $1$ to $100000$, inclusive.

You need to write a Java function that identifies the length of the longest possible substring within s
that contains at most K distinct characters. If multiple substrings share the longest length,
you should select the substring that appears first.

Your solution should have a time complexity of $O(n)$, where n is the length of the string s,
to ensure efficiency given the constraints.

To offer more clarity, let's consider an example. Suppose s is "acaabcc" and K is 2.
The longest possible substring with at most 2 distinct characters is "acaa",
so your function should return 4, which is the length of "acaa".

Remember, the function should not return the actual substring but the length of the longest possible substring
that meets the specified criteria.

Note: Alphanumeric characters are case-sensitive, i.e., 'A' and 'a' are treated as different characters.

Hint:
    To achieve O(n) time, consider using the sliding window (two-pointer) technique.

    How could you use a HashMap to keep track of the characters and their frequencies within your
    current window between pointers i and j?

 */

public class LongestSubstringWithKDistinctCharacters {

    public int solution(String s, int K) {
        int maxLength = 0;
        Set<Character> visitedCharacters = new HashSet<>();

        for(int right = 0, left = 0; right < s.length(); right++) {
            char currentCharacter = s.charAt(right);

            visitedCharacters.add(currentCharacter);

            if(visitedCharacters.size() > K){
                maxLength = Math.max(maxLength, right - left);
                left = right;
                visitedCharacters.removeIf(character -> character != currentCharacter);
            }

            if(visitedCharacters.size() <= K && right == s.length() - 1){
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args){
        LongestSubstringWithKDistinctCharacters lswkdc = new LongestSubstringWithKDistinctCharacters();

        List<String> inputStrings = new ArrayList<>();
        List<Integer> inputK = new ArrayList<>();
        List<Integer> expectedResults = new ArrayList<>();

        inputStrings.add("caaa");
        inputK.add(1);
        expectedResults.add(3);

        inputStrings.add("abac");
        inputK.add(2);
        expectedResults.add(3);

        inputStrings.add("acaabcc");
        inputK.add(2);
        expectedResults.add(4);

        inputStrings.add("aaaaaaa");
        inputK.add(1);
        expectedResults.add(7);

        inputStrings.add("abcabcabcabc");
        inputK.add(3);
        expectedResults.add(12);

        inputStrings.add("abcabcabcabc");
        inputK.add(2);
        expectedResults.add(2);

        inputStrings.add("AaAaAaAa");
        inputK.add(1);
        expectedResults.add(1);

        inputStrings.add("1234567890");
        inputK.add(10);
        expectedResults.add(10);

        inputStrings.add("");
        inputK.add(1);
        expectedResults.add(0);

        inputStrings.add("abcabcabcabc");
        inputK.add(4);
        expectedResults.add(12);

        for(int z= 0; z < inputStrings.size(); z++){
            int result = lswkdc.solution(inputStrings.get(z), inputK.get(z));
            System.out.println((z + 1) + ".- Input: \"" + inputStrings.get(z) + "\", K: " + inputK.get(z) + ", Expected: "
                    + expectedResults.get(z) + ", Calculated: " + result);
        }
    }

}
