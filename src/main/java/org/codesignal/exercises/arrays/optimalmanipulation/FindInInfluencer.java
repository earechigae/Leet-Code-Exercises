package org.codesignal.exercises.arrays.optimalmanipulation;

import java.util.*;

/*
You have been given a list of pairs, each representing a connection between two people.
Every person is represented by a unique positive integer that falls within the range of $1$ to $1001$, inclusive.

A pair (a, b) indicates that person a connects with person b. This connection is not direction-sensitive,
meaning that (a, b) and (b, a) represent the same connection. The list might include both forms.

Your task is to identify the person who has the potential to connect the most people within two degrees of connection.
In other words, you aim to find the person with the most extensive network of friends of friends.

For instance, consider the following sample of connections:
List<int[]> connections = Arrays.asList(new int[]{100, 200}, new int[]{200, 300}, new int[]{100, 300},
                                        new int[]{100, 400}, new int[]{400, 500}, new int[]{500, 600},
                                        new int[]{200, 600});

Person 100 connects with 200, 300, and 400. The friends of friends are 600 (from 200), 500 (from 400).
Therefore, person 100 is connected to five different people within two degrees.

Person 200 connects with 100, 300, and 600. The friends of friends are 400 (from 100), and 500 (from 600).
Therefore, person 200 is connected to five people within two degrees.

Person 300 connects with 100 and 200. The friends of friends are 400 (from 100), and 600 (from 200).
Therefore, person 300 is connected to four people within two degrees, and so on.

In this scenario, you should return 100, 200, 400, or 600, as they connect with the highest number of people within
two degrees. However, the order of preference is for the lower integer. Therefore, you should return 100.

Please write a Java function findInfluencer:

The function should take a list of pairs as its parameter, where each pair comprises a pair of positive integers
representing two connected persons. It should return another integer representing the person with the most extensive
network of connections within two degrees.

Efficiency is the key. The function should have a time complexity of about $O(n x m)$ where m is the length of
connections and n is the number of people.

The list of connections will have at least one connection and at the most $1000$ connections.
Each person is represented by a unique positive integer not exceeding $1001$.

Hint:
    To get started, think about how you can store the connections so you can easily look up a person's friends.
    A Map<Integer, Set<Integer>> where the key is the person and the value is a set of their direct friends might
    be very helpful!

    Once you have that, how would you find the "friends of friends" for a specific person while making sure you
    don't count the same person twice?
 */

public class FindInInfluencer {
    public static int findInfluencer(List<int[]> connections){
        int influencer = -1;
        Map<Integer, Set<Integer>> friendMap = new HashMap<>();
        Map<Integer, Set<Integer>> twoDegreesFriendMap = new HashMap<>();

        // Build the friend map
        for (int[] connection : connections) {
            int person1 = connection[0];
            int person2 = connection[1];
            friendMap.computeIfAbsent(person1, k -> new HashSet<>()).add(person2);
            friendMap.computeIfAbsent(person2, k -> new HashSet<>()).add(person1);

            twoDegreesFriendMap.computeIfAbsent(person1, k -> new HashSet<>()).add(person2);
            twoDegreesFriendMap.computeIfAbsent(person2, k -> new HashSet<>()).add(person1);
        }

        for(int person: friendMap.keySet()){
            Set<Integer> twoDegrees = new HashSet<>(friendMap.get(person));

            Iterator<Integer> iterator = twoDegrees.iterator();
            while(iterator.hasNext()){
                int friend = iterator.next();
                Set<Integer> friendFriends = new HashSet<>(friendMap.get(friend));

                //This can be seen as a set difference: friendFriends - twoDegrees
                friendFriends.removeAll(twoDegrees);
                friendFriends.remove(person);
                twoDegreesFriendMap.get(person).addAll(friendFriends);
            }
        }

        int max2DegreeFriends = twoDegreesFriendMap.values().stream().mapToInt(Set::size).max().orElse(0);
        influencer = twoDegreesFriendMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() == max2DegreeFriends)
                .map(Map.Entry::getKey)
                .min(Integer::compareTo)
                .orElse(-1);

        return influencer;
    }

    public static void main(String[] args){
        List<List<int[]>> inputConnecitonsTestCases = new ArrayList<>();
        List<Integer> expectedOutput = new ArrayList<>();

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{100, 200}, new int[]{200, 300}, new int[]{100, 300},
                new int[]{100, 400}, new int[]{400, 500}, new int[]{500, 600},
                new int[]{200, 600}));
        expectedOutput.add(100);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{500, 1000}, new int[]{500, 100}, new int[]{100, 300},
                new int[]{100, 200}, new int[]{200, 600}, new int[]{600, 1000}));
        expectedOutput.add(100);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 5}));
        expectedOutput.add(3);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{100, 200}, new int[]{200, 300}, new int[]{200, 400}, new int[]{200, 500}));
        expectedOutput.add(100);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{1, 2}));
        expectedOutput.add(1);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{1, 2}, new int[]{1, 3}, new int[]{1, 4},
                new int[]{2, 5}, new int[]{3, 6}, new int[]{4, 7}));
        expectedOutput.add(1);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{1, 2}, new int[]{2, 1}));
        expectedOutput.add(1);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{1, 2}, new int[]{2, 3}));
        expectedOutput.add(1);

        inputConnecitonsTestCases.add( Arrays.asList(
                new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 5}, new int[]{5, 1}));
        expectedOutput.add(1);

        for(int z = 0; z < inputConnecitonsTestCases.size(); z++){
            System.out.println("\nTest Case " + (z + 1) + ": Expected: " + expectedOutput.get(z));

            StringBuilder arrayBuilder = new StringBuilder("{");
            for(int i = 0; i < inputConnecitonsTestCases.get(z).size(); i++){
                arrayBuilder.append(Arrays.toString(inputConnecitonsTestCases.get(z).get(i)));
                if(i < inputConnecitonsTestCases.get(z).size() - 1){
                    arrayBuilder.append(", ");
                }
            }
            arrayBuilder.append("}");
            System.out.println("Influencer connections list: " + arrayBuilder.toString());

            int result = findInfluencer(inputConnecitonsTestCases.get(z));
            System.out.println("The influencer id with the biggest 2-degree connections is: " + result + ". (calculated)");
        }
    }
}
