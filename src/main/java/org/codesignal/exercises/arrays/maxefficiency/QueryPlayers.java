package org.codesignal.exercises.arrays.maxefficiency;

import java.util.*;

/*
You are given two lists, player_ids and player_scores, where each ID in player_ids corresponds to a score in player_scores,
i.e. the score of player_ids[i] is player_scores[i]. Additionally, you have a list named queries, containing player IDs.

Your task is to implement a function, solution, that returns a list of scores for the players specified in the queries list,
using player_ids and player_scores for reference. If a player ID from the queries list does not exist, skip that query.

Constraints:

    * 1≤len(player_ids)≤10000001≤len(player_ids)≤1000000, where len(player_ids) denotes the number of players.
    * player_ids contain unique integer identifiers for each player.
    * −10^6≤ player_scores[i] ≤10^6, where player_scores[i] represents the score of the i-th player.
    * 1≤ len(queries)≤ 10000001, where len(queries) indicates the size of the queries list.
    * Each element in the queries list is an integer.

Example:
    If player_ids = [1, 2, 3, 4, 5], player_scores = [100, 200, 150, 50, 300], and queries = [2, 5, 1],
    the output should be [200, 300, 100].

Hint:
    Since there can be up to 1,000,000 players and queries, searching the list for each query would take too long.
    What data structure could you use to map each player's ID to their score so you can look it up in O(1)O(1) average time? 🚀
 */

public class QueryPlayers {
    public List<Integer> solution(List<Integer> player_ids, List<Integer> player_scores, List<Integer> queries) {
        Map<Integer, Integer> playerScores = new HashMap<>();
        List<Integer> queriesResult = new ArrayList<>();

        for(int i = 0; i < player_ids.size(); i++){
            playerScores.put(player_ids.get(i), player_scores.get(i));
        }

        for(Integer playerId: queries){
            if(playerScores.containsKey(playerId)){
                queriesResult.add(playerScores.get(playerId));
            }
        }

        return queriesResult;
    }

    public static void main(String[] args) {
        QueryPlayers qp = new QueryPlayers();
        List<List<Integer>> playerIdsList = new ArrayList<>();
        List<List<Integer>> playerScoresList = new ArrayList<>();
        List<List<Integer>> queriesList = new ArrayList<>();
        List<List<Integer>> expectedResults = new ArrayList<>();

        List<Integer> player_ids = Collections.singletonList(1);
        List<Integer> player_scores = Collections.singletonList(-1000000);
        List<Integer> queries = Collections.singletonList(1);
        List<Integer> expected_result = Collections.singletonList(-1000000);
        playerIdsList.add(player_ids);
        playerScoresList.add(player_scores);
        queriesList.add(queries);
        expectedResults.add(expected_result);

        player_ids = Collections.singletonList(1);
        player_scores = Collections.singletonList(563666);
        queries = Arrays.asList(1, 1, 1, 1, 1);
        expected_result = Arrays.asList(563666, 563666, 563666, 563666, 563666);
        playerIdsList.add(player_ids);
        playerScoresList.add(player_scores);
        queriesList.add(queries);
        expectedResults.add(expected_result);

        player_ids = Arrays.asList(65, 39, 78, 97, 60, 46, 49, 37, 82, 9, 80, 41, 96, 50, 63, 87, 34, 25, 66, 40, 26, 69, 91, 86, 76, 45, 47, 4, 30, 21, 27, 18, 56, 59, 24, 88, 14, 99, 54, 52, 19, 22, 31, 2, 55, 6, 15, 29, 35, 5, 71, 10, 43, 61, 90, 62, 1, 51, 95, 13, 92, 32, 98, 77, 17, 58, 100, 72, 85, 44, 38, 3, 20, 74, 36, 79, 11, 12, 64, 33, 83, 57, 67, 84, 42, 28, 81, 16, 70, 89, 7, 53, 8, 75, 48, 68, 93, 23, 73, 94);
        player_scores = Arrays.asList(-580580, -761043, 365022, -731995, -893409, -652345, -675665, 316976, 882712, -304176, -301299, -902380, 600977, -394076, -517712, 658246, 452749, -248836, -664689, 250180, 189576, -459846, -572023, -46613, -516404, 188157, -351918, -325686, 896225, 169355, -930455, -261075, 504694, 888665, 716056, 731392, -982642, -772047, -330340, -515251, 318, -780633, -764334, -783506, -127955, 430566, 655118, -490425, 258545, 17521, 449558, -865654, 606361, -523392, -931046, 688489, 922587, 827609, -711732, -932233, -770604, 276241, -410759, -788533, 906260, 782858, -689580, -275164, 290034, -464446, 75602, -448363, 284061, -835424, 304247, 775593, 841004, 721379, 810003, 693839, 954439, 940330, 201718, 444586, -795865, -234213, 811251, 960218, 209416, 981853, 476580, 823590, 908807, 70422, -490550, -426841, -855238, 178517, 688108, 485024);
        queries = Arrays.asList(67, 82, 95, 32, 66, 56, 77, 89, 59, 18, 26, 31, 30, 47, 91, 15, 63, 42, 51, 50, 9, 71, 61, 54, 38, 6, 65, 84, 28, 10, 72, 4, 88, 69, 21, 73, 36, 100, 64, 80, 97, 93, 43, 86, 14, 37, 76, 34, 45, 70);
        expected_result = Arrays.asList(201718, 882712, -711732, 276241, -664689, 504694, -788533, 981853, 888665, -261075, 189576, -764334, 896225, -351918, -572023, 655118, -517712, -795865, 827609, -394076, -304176, 449558, -523392, -330340, 75602, 430566, -580580, 444586, -234213, -865654, -275164, -325686, 731392, -459846, 169355, 688108, 304247, -689580, 810003, -301299, -731995, -855238, 606361, -46613, -982642, 316976, -516404, 452749, 188157, 209416);
        playerIdsList.add(player_ids);
        playerScoresList.add(player_scores);
        queriesList.add(queries);
        expectedResults.add(expected_result);

        player_ids = Arrays.asList(57, 92, 19, 28, 70, 52, 48, 62, 72, 95, 86, 58, 60, 10, 98, 30, 87, 4, 100, 41, 5, 79, 65, 2, 31, 96, 14, 71, 91, 21, 3, 27, 35, 38, 9, 64, 59, 47, 90, 11, 34, 53, 80, 33, 15, 55, 12, 97, 8, 16, 37, 51, 61, 75, 17, 50, 56, 73, 88, 78, 20, 89, 25, 69, 82, 6, 40, 45, 74, 46, 24, 32, 67, 1, 93, 22, 36, 44, 77, 42, 99, 76, 26, 84, 39, 49, 85, 81, 13, 94, 29, 54, 63, 68, 23, 43, 7, 18, 83, 66);
        player_scores = Arrays.asList(255973, 326890, 399265, 612599, 86839, 643248, -989677, -651115, -675891, 73521, 783582, 839610, -322297, 981306, -575396, -177516, -295723, -884999, -135836, -395591, 921359, 890819, 252244, -23601, -347525, 96941, 834756, 825342, -765044, -512531, -497194, 849103, 749482, -514919, -987941, 860894, 168681, 423176, -701566, -560958, 821690, 280999, 402346, -894708, 322148, 550602, -62855, 799518, -45307, 803707, 541569, -320954, 156743, -638534, 890617, -764134, 930389, -760143, -287151, -886602, -987695, 268141, -283176, -97988, 873059, -102802, -83350, -966273, 19386, -613419, -18965, 434348, 492847, -913455, -1869, -583971, 18381, -79559, -534374, 163391, 123593, -583916, 196081, 83208, -973103, 301450, -677991, 715727, -873992, 538078, -502157, 216061, 322704, -317333, -242738, -283259, 256465, -95657, -410238, -308645);
        queries = Arrays.asList(101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120);
        expected_result = Collections.emptyList();
        playerIdsList.add(player_ids);
        playerScoresList.add(player_scores);
        queriesList.add(queries);
        expectedResults.add(expected_result);


        player_ids = Arrays.asList(8, 9, 10, 7, 5, 2, 4, 6, 3, 1);
        player_scores = Arrays.asList(1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000);
        queries = Arrays.asList(7, 5, 8, 9, 3);
        expected_result = Arrays.asList(1000000, 1000000, 1000000, 1000000, 1000000);
        playerIdsList.add(player_ids);
        playerScoresList.add(player_scores);
        queriesList.add(queries);
        expectedResults.add(expected_result);

        for(int z = 0; z < playerIdsList.size(); z++){
            List<Integer> playerIds = playerIdsList.get(z);
            List<Integer> playerScores = playerScoresList.get(z);
            List<Integer> queriesForThisCase = queriesList.get(z);
            List<Integer> expected = expectedResults.get(z);

            List<Integer> calculated = qp.solution(playerIds, playerScores, queriesForThisCase);
            System.out.printf("%nTest Case %d.- %nPlayersIds: %s, %nPlayersScores: %s, %nQueries: %s, %nExpected:   %s, %nCalculated: %s%n",
                    z + 1, playerIds, playerScores, queriesForThisCase, expected, calculated);
        }
    }
}
