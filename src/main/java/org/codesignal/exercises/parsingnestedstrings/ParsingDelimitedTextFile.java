package org.codesignal.exercises.parsingnestedstrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a delimited text file consisting of user information. Each row represents a user,
with a unique identifier followed by the user's details, structured as a comma-separated key-value string.
However, this data is not flat. Some values in the key-value pair can be another key-value string,
enclosed in parentheses and separated by semicolons.

The input structure for each line is as follows:
    "ID,Key1=Value1,Key2=(SubKey1=SubValue1;SubKey2=SubValue2),Key3=Value3,..."

For example,
    "001,Address=(Street=Main St;City=NY;Zip=10001),Age=25,Name=John,Email=john@gmail.com"

Users are divided by \n.

Your task is to write a Java function that will parse this data and output a list of maps, one map for each user's information.
Each map should translate the user's details from the string into key-value pairs, including the nested structure.
Then, you need to update a specific user's detail, identified by the unique identifier and the key name.
Let's assume you need to update the Email of the user with ID "001".

Your Java function should first parse these lines into the above maps, then locate the appropriate user and detail to update.
The function should ultimately return the updated list of maps.

Constraints:
    The number of users in the file is limited to 500.
    Each ID is unique and comprises alphanumeric characters only.
    Each Key (and SubKey) in the user data is an alphanumeric string without spaces.
    Each Value (and SubValue) can be any alphanumeric string and may contain spaces.
    The SubValue strings may contain other nested key-value pairs but not more than one level deep (i.e., no nested key-value pairs within SubValue strings).
    The key-value pairs do not repeat. If one user’s data includes a key-value pair, no other user’s data will have the same key-value pair.
    All strings are case-sensitive.

Consider the following input data:
    String data = "001,Age=25,Name=John,Address=(Street=Main St;City=NY;Zip=10001),Email=john@gmail.com\n002,Age=30,Name=Jane,Address=(Street=2nd St;City=LA;Zip=90001),Email=jane@hotmail.com";
    String userid = "001";
    String key = "Email";
    String newValue = "johndoe@gmail.com";

To update the email of the user with ID "001" to "johndoe@gmail.com", the function would transform the data into:
    {
      {
        "001", {
          {"Age", "25"},
          {"Name", "John"},
          {"Address", "Street=Main St;City=NY;Zip=10001"},
          {"Email", "johndoe@gmail.com"}
        }
      },
      {
        "002", {
          {"Age", "30"},
          {"Name", "Jane"},
          {"Address", "Street=2nd St;City=LA;Zip=90001"},
          {"Email", "jane@hotmail.com"}
        }
      }
    }
 */

public class ParsingDelimitedTextFile {

    public static List<Map<String, Map<String, String>>> solution(String data, String userid, String key, String newValue) {
        List<Map<String, Map<String, String>>> result = new ArrayList<>();

        String[] userRecords = data.split("\n");

        for(String userRecord : userRecords){
            String subValueKey = null;
            String subValueValue = null;

            if(userRecord.contains("(") && userRecord.contains(")")){
                StringBuilder userRecordBuilder = new StringBuilder(userRecord);
                int openParenthesis = userRecord.indexOf("(");
                int closeParenthesis= userRecord.indexOf(")");
                int coma = 0;
                int nextComa = userRecord.indexOf(",", coma);

                while(nextComa < openParenthesis){
                    coma = nextComa;
                    nextComa = userRecord.indexOf(",", coma + 1);
                }

                String subValueKeyValue = userRecord.substring(coma + 1, closeParenthesis + 1);
                subValueKey = subValueKeyValue.substring(0, subValueKeyValue.indexOf("="));
                subValueValue = subValueKeyValue.substring(subValueKeyValue.indexOf("=") + 1);

                userRecordBuilder.replace(coma, closeParenthesis + 1, "");
                userRecord = userRecordBuilder.toString();
            }

            Map<String, String> recordAttributesMap = new HashMap<>();
            String[] attributes = userRecord.split(",");
            String recordKey = attributes[0];

            for(int i = 1; i < attributes.length; i++){
                String[] keyValue = attributes[i].split("=", 2);
                if(keyValue.length == 2){
                    recordAttributesMap.put(keyValue[0], keyValue[1]);
                }
            }

            if(subValueKey != null && subValueValue != null){
                recordAttributesMap.put(subValueKey, subValueValue);
            }

            Map<String, Map<String, String>> userRecordMap = new HashMap<>();

            userRecordMap.put(recordKey, recordAttributesMap);
            result.add(userRecordMap);
        }

        updateMap(result, userid, key, newValue);
        return result;
    }

    public static void updateMap(List<Map<String, Map<String, String>>> map, String userid, String key, String newValue) {
        for (Map<String, Map<String, String>> userMap : map) {
            if (userMap.containsKey(userid)) {
                if (userMap.get(userid).containsKey(key)) {
                    userMap.get(userid).put(key, newValue);
                }
            }
        }
    }

    public static void main(String[] args){
        ParsingDelimitedTextFile pdtf = new ParsingDelimitedTextFile();
        List<String> testCases = new ArrayList<>();
        List<String> testUserId = new ArrayList<>();
        List<String> testKey =  new ArrayList<>();
        List<String> testnewValue = new ArrayList<>();

        testCases.add("001,Age=25,Name=John,Address=(Street=Main St;City=NY;Zip=10001),Email=john@gmail.com\n002,Age=30,Name=Jane,Address=(Street=2nd St;City=LA;Zip=90001),Email=jane@hotmail.com");
        testUserId.add("001");
        testKey.add("Email");
        testnewValue.add("johndoe@gmail.com");

        testCases.add("001,Address=(Street=Main St;City=NY;Zip=10001),Age=25,Name=John,Email=john@gmail.com\n002,Age=30,Name=Jane,Address=(Street=2nd St;City=LA;Zip=90001),Email=jane@hotmail.com");
        testUserId.add("001");
        testKey.add("Email");
        testnewValue.add("johndoe@gmail.com");

        testCases.add("001,Age=25,Name=John,Address=(Street=Main St;City=NY;Zip=10001),Email=john@gmail.com\n002,Age=30,Name=Jane,Address=(Street=2nd St;City=LA;Zip=90001),Email=jane@hotmail.com");
        testUserId.add("001");
        testKey.add("Age");
        testnewValue.add("30");

        testCases.add("001,Age=25,Name=John,Email=john@gmail.com");
        testUserId.add("001");
        testKey.add("Score");
        testnewValue.add("80");

        testCases.add("001,Score=80,Subject=(Maths=85;English=75;History=90),Email=john@gmail.com");
        testUserId.add("001");
        testKey.add("Score");
        testnewValue.add("80");

        testCases.add("001,Name=John,Email=john@gmail.com\n002,Name=Jane,Email=jane@hotmail.com\n003,Name=Bob,Email=bob@gmail.com");
        testUserId.add("003");
        testKey.add("Email");
        testnewValue.add("bob@yahoo.com");

        testCases.add("001,Name=John,Address=(Street=Main St;City=NY;Zip=10001),Email=john@gmail.com\n002,Name=Jane,Address=(Street=2nd St;City=LA;Zip=90001),Email=jane@hotmail.com");
        testUserId.add("002");
        testKey.add("Address");
        testnewValue.add("(Street=3rd Ave;City=SF;Zip=94101)");

        testCases.add("001,Name=John,Email=john@gmail.com");
        testUserId.add("001");
        testKey.add("Name");
        testnewValue.add("Johnny");

        for(int z = 0; z < testCases.size(); z++) {
            System.out.printf("%n%d.- Input string %s%n", z + 1, testCases.get(z));
            System.out.printf("To change key: %s with value: %s for userId: %s%n", testKey.get(z), testnewValue.get(z), testUserId.get(z));
            List<Map<String, Map<String, String>>> result = pdtf.solution(testCases.get(z),  testUserId.get(z), testKey.get(z), testnewValue.get(z));
            System.out.println(result);
        }
    }
}
