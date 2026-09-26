package org.codesignal.exercises.javahashmaps.optimalmanipulation;

import java.util.*;

/*
You are presented with a string containing n characters, ranging from $1$ to $500$ inclusive,
with each character from the ASCII printable character set (decimal from 32 to 126 inclusive).
Each character in the string could be a space, a punctuation mark, a number, or a letter, and is case-sensitive.

The task asks you to return a pair containing two elements. The first element should be a character from the string,
and the second element is an integer calculated as follows: Identify all unique words in the string,
where a word is defined as a sequence of non-space characters separated by one or more spaces.
The character you should return is the one whose removal (removal of all occurrences of that letter) would lead to the
maximum number of unique words broken in the string, where a broken word is a word from which at least one letter is removed.
The integer in the pair is the resulting maximum number of words.

A word can contain any number of characters, ranging from $1$ to the length of the original string,
and it might include punctuation marks and numbers. For example, the words in "Life is like a box of chocolates" are:
{"Life", "is", "like", "a", "box", "of", "chocolates"}, while the words in the string “1... 2... 3... Go!”
are: {"1...", "2...", "3...", "Go!"}.

For example, given the string "Hello, world!", the function should return: ('l', 2),
since selecting and removing the character 'l' would result in the maximum number of unique broken words.
 2 words would broke, so the integer in the pair should be 2.

Note: In the case that there are multiple characters that could be selected, return the one that appears first in the string.
For the above example, o is also a character that breaks maximum number of words when removed, but l appears earlier.

The solution should have a time complexity of O(n).

Example:
    Consider the string "apple banana apple".
    * The unique words are {"apple", "banana"}.
    * Removing 'a' breaks both unique words, so its count is 2.
    * Removing 'p' only breaks "apple", so its count is 1.

    The result would be ('a', 2) because 'a' breaks the most unique words.
 */

public class MostFrequentUniqueLetterInSentence {
    public Map.Entry<Character, Integer> solution(String s) {
        Map<Character, Integer> lettersInWordsFreqMap = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        String[] words = s.split(" ");
        for(String word: words){
            uniqueWords.add(word);
        }

        Iterator<String> uniqueWordsItera = uniqueWords.iterator();
        while(uniqueWordsItera.hasNext()){
            String uniqueWord = uniqueWordsItera.next();
            Set<Character> uniqueCharactersInUniqueWord = new HashSet<>();

            for(int i = 0; i < uniqueWord.length(); i++){
                char letter = uniqueWord.charAt(i);
                uniqueCharactersInUniqueWord.add(letter);
            }

            Iterator<Character> uniqueCharactersInUniqueWordItera = uniqueCharactersInUniqueWord.iterator();
            while(uniqueCharactersInUniqueWordItera.hasNext()){
                Character uniqueCharacter = uniqueCharactersInUniqueWordItera.next();
                lettersInWordsFreqMap.put(uniqueCharacter, lettersInWordsFreqMap.getOrDefault(uniqueCharacter, 0) + 1);
            }
        }

        int maxUniqueLetterInAllWords = Collections.max(lettersInWordsFreqMap.values());
        int earlierIndex = s.length();
        Map.Entry<Character, Integer> result = null;

        for(Map.Entry<Character, Integer> entry : lettersInWordsFreqMap.entrySet()){
            if(entry.getValue() == maxUniqueLetterInAllWords){
                int letterIndex = s.indexOf(entry.getKey());
                if(letterIndex < earlierIndex){
                    earlierIndex = letterIndex;
                    result = entry;
                }
            }
        }

        return result;
    }

    public static void main(String[] args){
        List<String> testCases = Arrays.asList(
            "Hello, world!",
            "Life is like a box of chocolates",
            "1... 2... 3... Go!",
            "A quick brown fox jumps over the lazy dog.",
            "Python is fun!",
            "To be, or not to be: that is the question.",
            "Winners never quit and quitters never win.",
            "May the force be with you.",
            "In the end, it's not the years in your life that count. It's the life in your years.",
            "Whether you think you can or you think you can’t, you’re right."
        );

        List<Map.Entry<Character, Integer>> expectedResults = new ArrayList<>();
        expectedResults.add(new AbstractMap.SimpleEntry<>('l', 2));
        expectedResults.add(new AbstractMap.SimpleEntry<>('i', 3));
        expectedResults.add(new AbstractMap.SimpleEntry<>('.', 3));
        expectedResults.add(new AbstractMap.SimpleEntry<>('o', 4));
        expectedResults.add(new AbstractMap.SimpleEntry<>('n', 2));
        expectedResults.add(new AbstractMap.SimpleEntry<>('o', 5));
        expectedResults.add(new AbstractMap.SimpleEntry<>('i', 4));
        expectedResults.add(new AbstractMap.SimpleEntry<>('e', 3));
        expectedResults.add(new AbstractMap.SimpleEntry<>('t', 6));
        expectedResults.add(new AbstractMap.SimpleEntry<>('t', 4));

        MostFrequentUniqueLetterInSentence mfulis = new MostFrequentUniqueLetterInSentence();

        for(int z = 0; z< testCases.size(); z++){
            String testCase = testCases.get(z);
            String expectedResult = "['" + expectedResults.get(z).getKey() + "', " + expectedResults.get(z).getValue() + "]";
            String calculatedResult = "['" + mfulis.solution(testCase).getKey() + "', " + mfulis.solution(testCase).getValue() + "]";
            System.out.printf("%d.- Input: \"%s\" --> Most frequent letter in the every word in the sentence is [letter, frequency]: " +
                    "Expected: %s, Calculated: %s %n", z + 1, testCase, expectedResult, calculatedResult);
        }
    }
}
