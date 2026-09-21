package org.codesignal.exercises.parsingnestedstrings;

/*
You are given a string representation of a nested JSON object.
Each JSON object is represented by key-value pairs enclosed within curly braces {}.
Keys and values are separated by colons :, and distinct entries in an object are separated by commas ,.
A value in a JSON object can be a string, a number, or another nested JSON object.
For simplicity, we will not consider arrays or null values in this task.

For example, the string "{\"key1\": \"value1\", \"key2\": {\"key3\": \"value3\", \"key4\": \"value4\"}, \"key5\": \"value5\"}"
represents the following JSON object:

{
  "key1": "value1",
  "key2": {
    "key3": "value3",
    "key4": "value4"
  },
  "key5": "value5"
}

Your task is to transform the given string into a nested Java map structure and then update a specific key-value pair within the map.
You should parse the JSON string into a Java nested map and then update the value associated with the key "key4" to the given update_value.
The string and the new value will be provided as input to your function.
The key "key4" could either be in the outer map or inside a nested map. Your function should return the updated map.

Note: If a value in the outer map is not a map itself but a string,
convert it to a map where the original string is the key and an empty string "" is the value.
For example, "key": "value" should be converted to "key": {"value": ""}.

The input string will contain from 1 to 500 characters, inclusive. For this task, we'll assume that all keys in the JSON object are unique.

Example Input: "{\"key1\": \"value1\", \"key2\": {\"key3\": \"value3\", \"key4\": \"value4\"}, \"key5\": \"value5\"}", "newValue"

Expected Output:
{
    "key1": {"value1": ""},
    "key2": {
        "key3": "value3",
        "key4": "newValue"
    },
    "key5": {"value5": ""}
}
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsingSimplerJson {
    public Map<String, Map<String, String>> solution(String jsonString, String updateValue) {
        Map<String, Map<String, String>> parsedMap = new HashMap<>();
        String inputString = null;
        String key = ""; // to store the outer map key
        HashMap<String, String> innerJasonMap = new HashMap<>(); // to store the inner map
        boolean inInnerJasonMap = false; // flag to check if we are inside an inner map
        int i = 0; // to iterate through the string

        if(jsonString.indexOf("{") == 0 && jsonString.lastIndexOf("}") == jsonString.length() - 1) {
            inputString = jsonString.substring(1, jsonString.lastIndexOf("}"));
        }else{
            return parsedMap;
        }

        while (i < inputString.length()) {
            if (inputString.charAt(i) == '{') {
                // Entering an inner map
                inInnerJasonMap = true;
                i++; // Skip the '{'
            } else if (inputString.charAt(i) == '}') {
                // Exiting an inner map
                parsedMap.put(key, innerJasonMap);
                innerJasonMap = new HashMap<>();
                inInnerJasonMap = false;
                i++; // Skip the '}'
                if (i < inputString.length() && inputString.charAt(i) == ',') {
                    i++; // Skip the ',' after '}'
                }
            }
            else if (!inInnerJasonMap) {
                // Parsing key-value pairs in the outer map
                int colomnPos = inputString.indexOf(':', i);
                int commaPos = inputString.indexOf(',', colomnPos);
                if (commaPos == -1) commaPos = inputString.length();

                key = inputString.substring(i, colomnPos).trim().replace("\"", "");;
                String value = inputString.substring(colomnPos + 1, commaPos).trim().replace("\"", "");;

                if (value.contains("{")) {
                    // Value is a nested map, will be processed separately
                    // The { character may be preceeded by spaces
                    int spaces = inputString.substring(colomnPos + 1, commaPos).indexOf("{");
                    i = colomnPos + spaces + 1; // Move forward to process the nested part
                } else {
                    // Value is a simple string, add to parsedMap map
                    parsedMap.put(key, new HashMap<String, String>() {{
                        put(value, "");
                    }});
                    i = commaPos + 1; // Move past the comma
                }
            }
            else if (inInnerJasonMap) {
                // Parsing key-value pairs inside the inner map
                int colomnPos = inputString.indexOf(':', i);
                int commaPos = inputString.indexOf(',', colomnPos);
                int bracePos = inputString.indexOf('}', colomnPos);

                // Determine the next delimiter that ends the current key-value pair
                int endPos = Math.min((commaPos < bracePos && commaPos >= 0)? commaPos : inputString.length(), bracePos);
                if (endPos == inputString.length()) endPos = Math.max(commaPos, bracePos);

                String innerKey = inputString.substring(i, colomnPos);
                String innerValue = inputString.substring(colomnPos + 1, endPos);
                innerKey = innerKey.trim().replace("\"", "");
                innerValue = innerValue.trim().replace("\"", "");
                innerJasonMap.put(innerKey, innerValue);

                i = endPos;
                if (i < inputString.length() && inputString.charAt(i) == ',') {
                    i++; // Skip the comma
                }
            }
        }  

        updateMap(parsedMap, "key4", updateValue);
        return parsedMap;
    }

    public static void updateMap(Map<String, Map<String, String>> map, String key, String value) {
        if(map.containsKey(key)){
            map.put(key, new HashMap<String, String>() {{
                put(value, "");
            }});
        }else {
            for (Map<String, String> innerMap : map.values()) {
                if (innerMap.containsKey(key)) {
                    // Key found, update the value
                    innerMap.put(key, value);
                    return;
                }
            }
        }
    }

    public static void main(String[] args){
        ParsingSimplerJson psj = new ParsingSimplerJson();
        List<String> testCases = new ArrayList<>();
        int counter = 1;

        testCases.add("{\"key1\": \"value1\", \"key2\": {\"key3\": \"value3\", \"key4\": \"value4\"}, \"key5\": \"value5\"}");
        testCases.add("{\"key1\": \"value1\", \"key2\": {\"key3\": \"value3\", \"key4\": \"value4\"}}");
        testCases.add("{\"key4\": \"value4\"}");
        testCases.add("{\"key1\": {\"key2\": \"value3\"}, \"key4\": \"value4\"}");
        testCases.add("{\"key1\": {\"key3\": \"value3\", \"key4\": \"value4\"}, \"key2\": \"value2\"}");


        for(String jsonStr : testCases){
            System.out.printf("JSON string #%d has a value of: %n%s%n", counter++, jsonStr);
            Map<String, Map<String, String>> parsedMap = psj.solution(jsonStr, "Test-Key4-Value");
            System.out.println("The corresponding HashMap after parsing the JSON and updating key4 is " + parsedMap + "\n");
        }
    }
}
