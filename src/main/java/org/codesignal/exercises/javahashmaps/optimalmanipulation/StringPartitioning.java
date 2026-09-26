package org.codesignal.exercises.javahashmaps.optimalmanipulation;

import java.util.*;

/*
In the legendary kingdom of Alphaland, exists a cryptic scroll named s, inscribed with a string of elegant lowercase letters.
As a renowned linguist, your quest is to decipher this scroll by partitioning it into as many chapters (substrings) as your wit allows,
under the condition that each letter graces only a maximum of one chapter.

This is a sacred task, for the chapters must follow the progression of the original text, reflecting the flow of the narrative embedded within it.
To illustrate, if you were to partition the text "abacdcd" into the chapters "aba" and "cdcd",
your resultant report of chapter lengths should list as [3, 4], preserving the original sequence.

The ancient scroll s stretches on to a length n, varying anywhere from 1 to 1000000 characters.
Its diverse inhabitants range from the scribed characters from a to z.

Your mission, dear linguist, is to draw the linguistics of Alphaland in the form of the Java method public List<Integer> solution(String s).
This magical method shall read the string of characters and spin out a list of integers representing the lengths of each chapter,
forming the crucial code to decipher the legend of Alphaland. More formally, the task involves partitioning a string into as many substrings
as possible while ensuring that each character appears in only one substring (no substrings have common characters).
The substrings should retain the original order of characters from the input string.

Constraints:
    The length of the input string n will be in the range of 1 ≤ n ≤ 1000000.
    All characters in the string will be lower-case English alphabets ('a' to 'z').
    The time complexity of the solution should be $O(n)$.

Hint
    Let's look at the string "ababcbacadefegdehijhklij".

    If we start with 'a', we see its last occurrence is at index 8.
    This means our first chapter must at least extend to index 8 to ensure all 'a's are included.

    As we scan toward index 8, we encounter 'b' at index 1, whose last occurrence is at index 5.
    Since 5 is within our current range, we continue until we reach the furthest "last occurrence" of any character we've met!
 */

public class StringPartitioning {
    public List<Integer> solution(String s) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> charLastIndexMap = new HashMap<>();
        int currentFurthestLastIndex = -1;
        int currentSubStringLength = 0;

        for(int i = 0; i < s.length(); i++){
            char letter = s.charAt(i);
            int letterLastIndex = 0;

            if(!charLastIndexMap.containsKey(letter)){
                letterLastIndex = s.lastIndexOf(letter);
                charLastIndexMap.put(letter, letterLastIndex);
            }else{
                letterLastIndex = charLastIndexMap.get(letter);
            }

            currentFurthestLastIndex = Math.max(currentFurthestLastIndex, letterLastIndex);
            if(i == currentFurthestLastIndex){
                result.add(currentSubStringLength + 1);
                currentSubStringLength = 0;
            }else{
                currentSubStringLength++;
            }
        }

        return result;
    }

    public static void main(String[] args){
        StringPartitioning sp = new StringPartitioning();
        List<String> testCases = Arrays.asList(
                "abacbc",
                "a",
                "abc",
                "aaabbbccc",
                "zabacbcz",
                "abcabcabc",
                "abacdcd",
                "feepplkpadaasdr");
        List<List<Integer>> expectedOutputs = Arrays.asList(
                Arrays.asList(6),
                Arrays.asList(1),
                Arrays.asList(1, 1, 1),
                Arrays.asList(3, 3, 3),
                Arrays.asList(8),
                Arrays.asList(9),
                Arrays.asList(3, 4),
                Arrays.asList(1, 2, 5, 6, 1)
        );

        for (int i = 0; i < testCases.size(); i++) {
            System.out.printf("%d.- For input string \"%s\", the sizes of the substrings are: Expected: %s, Calculated: %s%n",
                    i + 1, testCases.get(i), expectedOutputs.get(i), sp.solution(testCases.get(i)));

        }

    }
}
