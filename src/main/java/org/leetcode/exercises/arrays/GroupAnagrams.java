package org.leetcode.exercises.arrays;

import java.util.*;

/*
49. Group Anagrams (https://leetcode.com/problems/group-anagrams/description/)

Given an array of strings strs, group the together. You can return the answer in any order.

Example 1:
    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:
    There is no string in strs that can be rearranged to form "bat".
    The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
    The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
    Input: strs = [""]
    Output: [[""]]

Example 3:
    Input: strs = ["a"]
    Output: [["a"]]

Constraints:
    1 <= strs.length <= 104
    0 <= strs[i].length <= 100
    strs[i] consists of lowercase English letters.
 */

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWordKey = new String(chars);

            if(!map.containsKey(sortedWordKey)) {
                map.put(sortedWordKey, new ArrayList<>());
            }

            map.get(sortedWordKey).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        List<List<String>> groupedAnagrams = null;
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};

        System.out.print("\nstrs1: [");
        for(int i = 0; i < strs1.length; i++){
            System.out.print(strs1[i] + (i == (strs1.length -1)? "]" : ", "));
        }
        System.out.print(" The grouped anagrams are ["  );
        groupedAnagrams = ga.groupAnagrams(strs1);
        for(int i = 0; i < groupedAnagrams.size(); i++) {
            List<String> anagrams = groupedAnagrams.get(i);
            System.out.print("[");
            for(int j = 0; j < anagrams.size(); j++) {
                System.out.print(anagrams.get(j) + (j == (anagrams.size() - 1)? "]" : ", "));
            }
        }
        System.out.println("]"  );


        System.out.print("\nstrs2: [");
        for(int i = 0; i < strs2.length; i++){
            System.out.print(strs2[i] + (i == (strs2.length -1)? "]" : ", "));
        }
        System.out.print(" The grouped anagrams are ["  );
        groupedAnagrams = ga.groupAnagrams(strs2);
        for(int i = 0; i < groupedAnagrams.size(); i++) {
            List<String> anagrams = groupedAnagrams.get(i);
            System.out.print("[");
            for(int j = 0; j < anagrams.size(); j++) {
                System.out.print(anagrams.get(j) + (j == (anagrams.size() - 1)? "]" : ", "));
            }
        }
        System.out.println("]"  );


        System.out.print("\nstrs3: [");
        for(int i = 0; i < strs3.length; i++){
            System.out.print(strs3[i] + (i == (strs2.length -1)? "]" : ", "));
        }
        System.out.print(" The grouped anagrams are ["  );
        groupedAnagrams = ga.groupAnagrams(strs3);
        for(int i = 0; i < groupedAnagrams.size(); i++) {
            List<String> anagrams = groupedAnagrams.get(i);
            System.out.print("[");
            for(int j = 0; j < anagrams.size(); j++) {
                System.out.print(anagrams.get(j) + (j == (anagrams.size() - 1)? "]" : ", "));
            }
        }
        System.out.println("]"  );
    }
}
