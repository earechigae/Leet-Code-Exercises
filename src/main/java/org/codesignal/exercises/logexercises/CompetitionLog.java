package org.codesignal.exercises.logexercises;

/*
There is a school hosting an online programming competition. Each problem is assigned a unique level of difficulty.
Every time a student successfully solves a problem, their score is updated based on the problem's difficulty level.
However, if a student makes an unsuccessful attempt, they incur a penalty.
The competition logs every action of each student in a string.

Your task is to create a Java function named analyzeCompetition().
It will take a string of logs as input and output a list of arrays, representing the students' score,
the number of successful attempts, and the total penalties.
The arrays should be sorted by the decreasing order of scores of their respective students.
It is guaranteed that there will be no students with the same positive score.
Don't include students in the output who haven't solved any problem.

For example, if you have logs like this:
"1 solve 09:00 50, 2 solve 10:00 60, 1 fail 11:00, 3 solve 13:00 40, 2 fail 14:00, 3 solve 15:00 70",
your function should return: [[3, 110, 2, 0], [2, 60, 1, 1], [1, 50, 1, 1]].

All log entries are separated by a comma and a space.
It is guaranteed that the log entries are sorted in chronological order.
 */

import java.util.*;

public class CompetitionLog {
    public List<int[]> analyzeCompetition(String logs) {
        List<int[]> competitionSummary = new ArrayList<>();
        Map<String, Map<String, Integer>> studentData = new HashMap<>();

        String problems[] = logs.split(",");
        for(String problem: problems){
            String[] details = problem.trim().split(" ");
            String studentId = details[0].trim();
            String outcome = details[1].trim();
            String timeStamp = details[2].trim();
            String difficulty = "0";

            if(details.length > 3){
                difficulty = details[3].trim();
            }

            if(studentData.containsKey(studentId)){
                Integer points = studentData.get(studentId).get("points");
                Integer problemsSolved = studentData.get(studentId).get("problemsSolved");
                Integer penalties = studentData.get(studentId).get("penalties");

                if("solve".equalsIgnoreCase(outcome)){
                    studentData.get(studentId).put("points", points + Integer.parseInt(difficulty));
                    studentData.get(studentId).put("problemsSolved", problemsSolved + 1);
                } else if("fail".equalsIgnoreCase(outcome)){
                    studentData.get(studentId).put("points", points - Integer.parseInt(difficulty));
                    studentData.get(studentId).put("penalties", penalties + 1);
                }
            } else {
                Map<String, Integer> studentInfo = new HashMap<>();
                if("solve".equalsIgnoreCase(outcome)){
                    studentInfo.put("points", Integer.parseInt(difficulty));
                    studentInfo.put("problemsSolved", 1);
                    studentInfo.put("penalties", 0);
                } else if("fail".equalsIgnoreCase(outcome)){
                    studentInfo.put("points", - Integer.parseInt(difficulty));
                    studentInfo.put("problemsSolved", 0);
                    studentInfo.put("penalties", 1);
                }
                studentData.put(studentId, studentInfo);
            }
        }

        for(Map.Entry<String, Map<String, Integer>> entry : studentData.entrySet()){
            String studentId = entry.getKey();
            Map<String, Integer> studentInfo = entry.getValue();
            competitionSummary.add(new int[]{Integer.parseInt(studentId), studentInfo.get("points"), studentInfo.get("problemsSolved"), studentInfo.get("penalties")});
        }

        competitionSummary.sort((a, b) -> {
            Integer pointsA = a[1];
            Integer pointsB = b[1];
            return pointsB.compareTo(pointsA); //Sort in descending order based on the points
        });

        return competitionSummary;
    }

    public static void main(String args[]){
        CompetitionLog cl = new CompetitionLog();
        String logs = "1 solve 09:00 50, 2 solve 10:00 60, 1 fail 11:00, 3 solve 13:00 40, 2 fail 14:00, 3 solve 15:00 70";
        System.out.printf("\nFor logs \"%s\" the competition results are: %n", logs);
        for(int[] studentSummar : cl.analyzeCompetition(logs)){
            System.out.println("Student ID: " + studentSummar[0] + ", Points: " + studentSummar[1] + ", Problems Solved: " + studentSummar[2] + ", Penalties: " + studentSummar[3]);
        }

        logs = "1 fail 09:00, 1 solve 10:00 50, 2 solve 11:00 60, 3 solve 12:00 70, 2 fail 13:00, 3 fail 14:00";
        System.out.printf("\nFor logs \"%s\" the competition results are: %n", logs);
        for(int[] studentSummar : cl.analyzeCompetition(logs)){
            System.out.println("Student ID: " + studentSummar[0] + ", Points: " + studentSummar[1] + ", Problems Solved: " + studentSummar[2] + ", Penalties: " + studentSummar[3]);
        }
    }
}
