package org.leetcode.exercises.arrays;

import java.util.HashMap;
import java.util.Map;

/*
2131. Longest Palindrome by Concatenating Two-Letter Words
You are given an array of strings words. Each element of words consists of two lowercase English letters.
You can select some elements from words and concatenate them in any order to form a palindrome.

Please return the length of the longest palindrome that you can create.

If it is impossible to create any palindrome, return 0.

Example 1:
    Input: words = ["lc","cl","gg"]
    Output: 6
    Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
    Note that "clgglc" is another longest palindrome that can be created.

Example 2:
    Input: words = ["ab","ty","yt","lc","cl","ab"]
    Output: 8
    Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
    Note that "lcyttycl" is another longest palindrome that can be created.

Example 3:
    Input: words = ["cc","ll","xx"]
    Output: 2
    Explanation: One longest palindrome is "cc", of length 2.
    Note that "ll" is another longest palindrome that can be created, and so is "xx".

Constraints:
    1 <= words.length <= 105
    words[i].length == 2
    words[i] consists of lowercase English letters.
 */

public class LongestPalindromeConcat2LetterWords {
    public int longestPalindrome(String[] words) {
        int len = 0;
        int n = words.length;
        Map<String,Integer> map = new HashMap<>(); //[word,freq] freq words can exists more than 1 times.

        for(int i = 0; i < n; i++){
            String rev = (new StringBuilder(words[i])).reverse().toString() ;
            if(map.containsKey(rev) && map.get(rev) > 0) {
                len += 4;
                map.put(rev, map.get(rev) - 1);
            } else {
                map.put(words[i], map.getOrDefault(words[i],0) + 1);
            }
        }

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            if(key.equals((new StringBuilder(key)).reverse().toString()) && entry.getValue() == 1){
                len += 2;
                break;
            }
        }

        return len;
    }

    public static void main(String args[]) {
        LongestPalindromeConcat2LetterWords longestPalindromeConcat2LetterWords = new LongestPalindromeConcat2LetterWords();

        String[] array1 = {"lc", "cl", "gg"};
        String[] array2 = {"ab", "ty", "yt", "lc", "cl", "ab"};
        String[] array3 = {"cc", "ll", "xx"};
        String[] array4 = {"qw","rr","ll","vv","iw","wq","cc","wi","jj","iw","pp","iw","mm","ss","bb","oo","wi","dd","wq","ff","qi","qw","qi","qi","zz","wq","iw","wi","qq","qw","wi","hh","qi","pp","vv","wi","wq","wi","wi","wi","iw","qi","bb","qw","qi","rr"};

        System.out.print("\n* The longest palindrome by concatenating 2 letter words of array [");
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + (i == (array1.length - 1) ? "]" : ", "));
        }
        System.out.print(" is: " + longestPalindromeConcat2LetterWords.longestPalindrome(array1));


        System.out.print("\n* The longest palindrome by concatenating 2 letter words of array [");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + (i == (array2.length - 1) ? "]" : ", "));
        }
        System.out.print(" is: " + longestPalindromeConcat2LetterWords.longestPalindrome(array2));


        System.out.print("\n* The longest palindrome by concatenating 2 letter words of array [");
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i] + (i == (array3.length - 1) ? "]" : ", "));
        }
        System.out.print(" is: " + longestPalindromeConcat2LetterWords.longestPalindrome(array3));

        System.out.print("\n* The longest palindrome by concatenating 2 letter words of array [");
        for (int i = 0; i < array4.length; i++) {
            System.out.print(array4[i] + (i == (array4.length - 1) ? "]" : ", "));
        }
        System.out.print(" is: " + longestPalindromeConcat2LetterWords.longestPalindrome(array4));
    }
}
