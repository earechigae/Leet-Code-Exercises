package org.codesignal.exercises.parsingnestedstrings;

import java.util.*;

/*
Imagine that you are a database manager dealing with a data structure in the form of a complex nested string.
This string contains user data and is structured in such a way that user attributes are separated by semicolons (;),
and within each user, the attribute-value pairs are separated by colons (:).
Some of the user attributes themselves contain nested attribute-value pairs, which are enclosed in curly braces ({}).

Here's an example of such a string:
"User1:Age1=21;Location1=USA;Preferences1={Food1=Italian; Sport1=Fencing};User2:Age2=30; Location2=Canada; Preferences2={Music2=Jazz; Color2=Blue}".

You need to write a Java function that will convert the string into a nested map, following the structure shown in the string.
After the string has been converted into a map, the function should update the value of a user-preference pair for any user to a
requested value and return the updated map.

In this string, the keys representing the user names contain numbers (User1, User2, etc.).
You should also provide an option to find users by their numerical indices following the "User" keyword,
such as 1 for User1, 2 for User2, and so on.

Your function should take the input string, the user index, the preference key, and the new value for the preference pair,
and should return the updated map in the end.

The size of the input string will be less than or equal to 500 characters.

Example
    Given:
    String input = "User1:Age1=21;Location1=USA;Preferences1={Food1=Italian;Sport1=Fencing};User2:Age2=30;Location2=Canada;Preferences2={Music2=Jazz;Color2=Blue}";
    int userIndex = 1;
    String prefKey = "Sport1";
    String newValue = "Hockey";

    The function should return:
    Map<String, Map<String, String>> result = new HashMap<>();
    result.put("User1", Map.of("Age1", "21", "Location1", "USA", "Preferences1", "{Food1=Italian;Sport1=Hockey}"));
    result.put("User2", Map.of("Age2", "30", "Location2", "Canada", "Preferences2", "{Music2=Jazz;Color2=Blue}"));
 */


public class ParsingNestedStrings {
    public Map<String, Map<String, String>> updatePreference(String inputString, int userIndex, String prefKey, String newValue) {
        Map<String, Map<String, String>> result = new HashMap<>();
        int fromIndex = 0;
        String userRecord = "";

        int colon = inputString.indexOf(':', fromIndex);
        if(colon == -1){
            return result;
        }

        while(fromIndex < inputString.length()){
            int nextColon = inputString.indexOf(':', colon + 1);
            if(nextColon == -1){
                userRecord = inputString.substring(fromIndex,  inputString.length());
                fromIndex = inputString.length();
            }else{
                //Need to calculate the position of the last semicolon ;
                userRecord = inputString.substring(fromIndex, nextColon);
                int lastSemicolon = userRecord.lastIndexOf(';');
                userRecord = userRecord.substring(0, lastSemicolon);
                fromIndex += userRecord.length() + 1;
            }
            colon = nextColon;

            String[] keyDetails = userRecord.split(":");
            String recordKey = keyDetails[0];
            String attributesToExtract = keyDetails[1];

            Map<String, String> userAttributes = new HashMap<>();
            String[] attributesKeyValue = attributesToExtract.split(";");
            String attrKey = null;
            String attrValue = null;
            boolean inInnerMap = false;
            String innerAttrKey = null;
            StringBuilder innerAttrValue = new StringBuilder();
            for(String attrKeyValue : attributesKeyValue){  // Comma Split

                String[] attrKeyValueArr = attrKeyValue.split("=");

                if(attrKeyValueArr.length > 2){ // If the length > 2, it means the string is like Preferences1={Food1=Italian;
                    innerAttrKey = attrKeyValueArr[0];
                    innerAttrValue.append(attrKeyValueArr[1]).append("=").append(attrKeyValueArr[2]).append(";");
                    inInnerMap = true;
                }else if(inInnerMap && attrKeyValueArr.length == 2){ //If in innerMap and there are 2 attributes
                    innerAttrValue.append(attrKeyValueArr[0]).append("=").append(attrKeyValueArr[1]);
                    if(attrKeyValueArr[1].contains("}")){ //If closing brackets
                        attrKey = innerAttrKey;
                        attrValue = innerAttrValue.toString();
                        inInnerMap = false;
                        innerAttrKey = null;
                        innerAttrValue.setLength(0); //innerAttrValue = new StringBuilder();
                    }else { //If no closing braces it means there are more inner attributes to consider
                        innerAttrValue.append(";");
                    }
                }else{
                    attrKey = attrKeyValueArr[0];
                    attrValue = attrKeyValueArr[1];
                }

                if (!inInnerMap && !attrKey.trim().isEmpty()) {
                    userAttributes.put(attrKey, attrValue);
                }
            }
            result.put(recordKey, userAttributes);
        }

        updateMap(result, userIndex, prefKey, newValue);

        return result;
    }

    public static void updateMap(Map<String, Map<String, String>> map, int userIndex, String prefKey, String newValue) {
        String userToLookFor = "User" + userIndex;
        if(map.containsKey(userToLookFor)){
            if(map.get(userToLookFor).containsKey(prefKey)){
                map.get(userToLookFor).put(prefKey, newValue);
            }else{
                Map<String, String> innerMap = map.get(userToLookFor);
                for (Map.Entry<String, String>  entry: map.get(userToLookFor).entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if(value.contains(prefKey)){
                        StringBuilder newPrefValue = new StringBuilder(value);

                        String strToLook = prefKey + "=";
                        int initialPos = value.indexOf(strToLook) + strToLook.length();
                        int semicolonPos = value.indexOf(';', initialPos);
                        int closeBracePos = value.indexOf('}', initialPos);
                        semicolonPos = (semicolonPos < 0)? value.length(): semicolonPos;
                        int finalPos = Math.min(semicolonPos, closeBracePos);
                        newPrefValue.replace(initialPos, finalPos, newValue);
                        map.get(userToLookFor).put(key, newPrefValue.toString());
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        ParsingNestedStrings pns = new ParsingNestedStrings();
        List<String> testCases = new ArrayList<>();
        int counter = 1;

        testCases.add("User1:Age1=21;Location1=USA;Preferences1={Food1=Italian;Sport1=Fencing};User2:Age2=30;Location2=Canada;Preferences2={Music2=Jazz;Color2=Blue}");
        testCases.add("User1:Age1=21;Location1=USA;Preferences1={Food1=Italian;Sport1=Fencing;Hobby1=Read};");
        testCases.add("User1:Age1=21;Location1=USA;Preferences1={Food1=Italian;Sport1=Fencing};");
        testCases.add("User1:Age1=45;Location1=UK;Preferences1={Food1=Asian;Sport1=Golf};User2:Age2=22;Location2=Africa;Preferences2={Music2=Reggae;Color2=Yellow}");
        testCases.add("User1:Age1=65;Location1=Australia;Preferences1={Food1=Seafood;Sport1=Surfing};");
        testCases.add("User1:Age1=30;Location1=Germany;Preferences1={Food1=Bavarian;Sport1=Football};User2:Age2=25;Location2=India;Preferences2={Music2=Classical;Color2=Violet}");


        for(String jsonStr : testCases){
            System.out.printf("#%d. String to parse has a value of: %n%s%n", counter++, jsonStr);
            Map<String, Map<String, String>> parsedMap = pns.updatePreference(jsonStr, 1, "Food1", "Mexican");
            System.out.printf("The corresponding HashMap after parsing the string and updating %s with a value of %s:%n" + parsedMap + "\n\n", "Food1", "Mexican");
        }
    }
}
