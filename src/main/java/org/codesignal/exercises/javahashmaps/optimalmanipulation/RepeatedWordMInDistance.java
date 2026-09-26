package org.codesignal.exercises.javahashmaps.optimalmanipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Your job requires you to construct a Java method named solution(). This method should receive a list of n words,
with n ranging from $1$ to $10^5$, inclusive. The task mandates that your method return a map,
where each key is a unique word from the list, and the corresponding value is the shortest distance between
two occurrences of that word in the list.

Each word in the list is composed solely of lowercase and uppercase English alphabets,
and the length of each word can range from $1$ to $50$, inclusive.

Calculating the distance between two occurrences of a word involves subtracting the position of the first occurrence
from that of the subsequent occurrence. For example, in the list {"dog", "cat", "bird", "cat", "dog", "elephant", "dog"},
the distance between the first and second occurrences of "dog" is $4$, and the distance between the second and third
occurrences of "dog" is $2$. Therefore, the shortest distance for "dog" should be considered $2$. The word "cat" appears
twice in the list in positions that are $2$ elements apart, so for "cat", the answer should be $2$.

For words that appear only once where there is no second occurrence, the shortest distance should be considered undefined,
and such words should be excluded from the output map. Hence, for the example above, the output should be

{
  "dog": 2,
  "cat": 2
}

Be sure to utilize a HashMap structure efficiently to track the positions of word occurrences and to assist in performing
the necessary calculations to solve the task optimally.
 */

public class RepeatedWordMInDistance {

    public Map<String, Integer> solution(List<String> wordList) {
        Map<String, Integer> repeatedWordsMinDistance = new HashMap<>();
        Map<String, Integer> wordLastIndex = new HashMap<>();

        for(int i = 0; i < wordList.size(); i++){
            String word = wordList.get(i);
            if(!wordLastIndex.containsKey(word)){
                wordLastIndex.put(word, i);
            }else{
                int distance = i - wordLastIndex.get(word);
                if(repeatedWordsMinDistance.containsKey(word)){
                    repeatedWordsMinDistance.put(word, Math.min(distance, repeatedWordsMinDistance.get(word)));
                }else{
                    repeatedWordsMinDistance.put(word, distance);
                }
                wordLastIndex.put(word, i);
            }
        }

        return repeatedWordsMinDistance;
    }

    public static void main(String[] args){
         RepeatedWordMInDistance rpwd = new RepeatedWordMInDistance();
         List<List<String>> testCases = new ArrayList<>();
         List<Map<String, Integer>> expectedResults = new ArrayList<>();

        testCases.add(List.of("dog", "cat", "bird", "cat", "dog", "elephant", "dog"));
        expectedResults.add(Map.of("dog", 2, "cat", 2));

        testCases.add(List.of("rain", "rain", "go", "away"));
        expectedResults.add(Map.of("rain", 1));

        testCases.add(List.of("foo", "bar", "foo", "bar", "foo"));
        expectedResults.add(Map.of("foo", 2, "bar", 2));

        testCases.add(List.of("a", "b", "c", "a", "b", "c", "a"));
        expectedResults.add(Map.of("a", 3, "b", 3, "c", 3));

        testCases.add(List.of("apple", "banana", "carrot", "apple", "eggplant", "banana", "apple"));
        expectedResults.add(Map.of("banana", 4, "apple", 3));

        testCases.add(List.of("x", "y", "z", "x", "y", "z", "x", "y", "x", "y"));
        expectedResults.add(Map.of("x", 2, "y", 2, "z", 3));

        testCases.add(List.of("python", "java", "csharp", "javascript", "python", "java", "python"));
        expectedResults.add(Map.of("python", 2, "java", 4));

        testCases.add(List.of("python", "python", "python", "python", "python"));
        expectedResults.add(Map.of("python", 1));

        testCases.add(List.of("python", "python", "python", "python", "python"));
        expectedResults.add(Map.of("python", 1));

        for(int z = 0; z < testCases.size(); z++){
            System.out.printf("%d.- Input string list %s --> The minimum distance between repeated words in the list are -->" +
                            " Expected %s, Calculated %s%n",
                    z + 1, testCases.get(z), expectedResults.get(z), rpwd.solution(testCases.get(z)));
        }
    }
}
