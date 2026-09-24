package org.codesignal.exercises.javahashsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
You are working on a NASA project and have been provided with two arrays of celestial bodies.
Array1 and Array2 each contain names of celestial bodies, where the lengths vary from 1 to 1000000, inclusive.

You need to generate an array to indicate whether a celestial body from Array1 is found in Array2 and eventually return this array.
The condition is to optimize your solution using Java's HashSet data structure, rather than simple array iteration.

For the sake of simplicity and to keep our data manageable,
let's assume the names of celestial bodies are represented as lowercase English alphabets and can vary from 1 to 10 characters in length, inclusive.

The order of entries in your result should directly correspond to the order of celestial bodies in Array1.
For example, the first boolean in your array should denote whether the first celestial body of Array1 is present in Array2 or not.

For instance, let's consider two arrays:
    String[] Array1 = {"mars", "jupiter", "venus", "earth"};
    String[] Array2 = {"earth", "mars", "neptune"};

Your function, to be named solution, should return:
    Boolean[] output = {true, false, false, true};

This indicates that "mars" and "earth" from Array1 are found in Array2.

Your task involves figuring out a method to accurately and efficiently perform this query,
utilizing the properties of Java's HashSet data structure to optimize the execution time of your code compared to the
straightforward iteration over members of Array1 for their presence in Array2.

 */

public class IsStringInBothArrays {

    public Boolean[] solution(String[] array1, String[] array2) {
        //Set<String> celestialBodiesInArray2 = Set.of(array2);

        Set<String> celestialBodiesInArray2 = new HashSet<>();

        for(String celestialArray2: array2){
            if(celestialArray2 != null){
                celestialBodiesInArray2.add(celestialArray2);
            }
        }

        Boolean[] output = new Boolean[array1.length];
        int index = 0;

        for(String celestialBody: array1){
            output[index++] = celestialBodiesInArray2.contains(celestialBody);
        }

        return output;
    }

    public static String convertArrayToStr(Object[] array){
        StringBuilder arrayStr = new StringBuilder("[");
        for(int i = 0; i < array.length; i++){
            arrayStr.append(array[i]);
            if(i < array.length - 1){
                arrayStr.append(", ");
            }
        }
        arrayStr.append("]");
        return arrayStr.toString();
    }

    public static void main(String[] args){
        IsStringInBothArrays is = new IsStringInBothArrays();
        List<String[]> arrays1 = new ArrayList<>();
        List<String[]> arrays2 = new ArrayList<>();
        List<Boolean[]> expectedResults = new ArrayList<>();

        arrays1.add(new String[]{"mars", "jupiter", "venus", "earth"});
        arrays2.add(new String[]{"earth", "mars", "neptune"});
        expectedResults.add(new Boolean[]{true, false, false, true});

        arrays1.add(new String[]{"venus"});
        arrays2.add(new String[]{"venus"});
        expectedResults.add(new Boolean[]{true});

        arrays1.add(new String[]{"earth", "mars"});
        arrays2.add(new String[]{"mars", "earth"});
        expectedResults.add(new Boolean[]{true, true});

        arrays1.add(new String[]{"jupiter", "saturn", "neptune"});
        arrays2.add(new String[]{"neptune"});
        expectedResults.add(new Boolean[]{false, false, true});

        arrays1.add(new String[]{"mars"});
        arrays2.add(new String[]{});
        expectedResults.add(new Boolean[]{false});

        arrays1.add(new String[]{"jupiter", "jupiter"});
        arrays2.add(new String[]{"jupiter"});
        expectedResults.add(new Boolean[]{true, true});

        arrays1.add(new String[]{"mars", "venus", "earth", "neptune", "saturn"});
        arrays2.add(new String[]{"mars", "jupiter", "saturn"});
        expectedResults.add(new Boolean[]{true, false, false, false, true});

        for(int z = 0; z < arrays1.size(); z++){
            Boolean[] result = is.solution(arrays1.get(z), arrays2.get(z));

            System.out.printf("%d.- Array1: %s, Array2: %s --> Is string in array2 also in array1? --> Expected: %s, calculated %s%n",
                    (z + 1), convertArrayToStr(arrays1.get(z)), convertArrayToStr(arrays2.get(z)),
                    convertArrayToStr(expectedResults.get(z)), convertArrayToStr(result));
        }
    }
}
