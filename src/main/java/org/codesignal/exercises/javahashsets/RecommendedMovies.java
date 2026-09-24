package org.codesignal.exercises.javahashsets;

import java.util.*;
/*
You are developing an application that provides users with movie recommendations based on their past viewing history.
The goal is to identify a list of popular movies the user has not yet watched.
You are provided with three arrays: user_history, popular_movies, and unpopular_movies.

The user_history array consists of unique integers representing the IDs of movies the user has already watched.
The popular_movies array contains unique integers representing the IDs of movies that are currently popular according to some source.
The unpopular_movies array contains unique integers representing movies that are not popular according to some other source.
Note that it is possible to have an element that exists both in array popular_movies and in unpopular_movies.

Your task is to develop a Java method named recommendMovies that takes these three arrays as inputs and returns a new array.
This array should contain the IDs of the popular movies that the user has not yet watched, a
nd which are not included in the unpopular_movies array.

The resulting array should be sorted in ascending order, and each ID in the array should be unique (no repeats).

Note: The input arrays can contain between $1$ and $1000000$ elements (inclusive) and the elements are between $1$ and $1000000$ inclusive.
 */

public class RecommendedMovies {

    public int[] recommendMovies(int[] userHistory, int[] popularMovies, int[] unpopularMovies) {
        // TODO: implement the method to recommend movies.
        Set<Integer> userHistorySet = new HashSet<>();
        Set<Integer> unPopularMoviesSet = new HashSet<>();
        Set<Integer> resultSet = new TreeSet<>();

        for(int userHistoryEntry: userHistory){
            userHistorySet.add(userHistoryEntry);
        }

        for(int unpopularMovie: unpopularMovies){
            unPopularMoviesSet.add(unpopularMovie);
        }

        for(int popularMovie: popularMovies){
            if(!userHistorySet.contains(popularMovie) && !unPopularMoviesSet.contains(popularMovie)){
                resultSet.add(popularMovie);
            }
        }

        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public static String convertArrayToStr(int[] array){
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
        RecommendedMovies rm = new RecommendedMovies();

        List<int[]> userHistoryTestCases = new ArrayList<>();
        List<int[]> popularMoviesTestCases = new ArrayList<>();
        List<int[]> unPopularMoviesTestCases = new ArrayList<>();
        List<int[]> expectedResults = new ArrayList<>();

        userHistoryTestCases.add(new int[]{1, 2, 3, 4, 5});
        popularMoviesTestCases.add(new int[]{1, 2, 3, 6, 7, 8, 9, 10});
        unPopularMoviesTestCases.add(new int[]{4, 5, 11});
        expectedResults.add(new int[]{6, 7, 8, 9, 10});

        userHistoryTestCases.add(new int[]{10, 20, 30, 40, 50});
        popularMoviesTestCases.add(new int[]{50, 100, 200, 300, 400, 500});
        unPopularMoviesTestCases.add(new int[]{30, 40, 500});
        expectedResults.add(new int[]{100, 200, 300, 400});

        userHistoryTestCases.add(new int[]{});
        popularMoviesTestCases.add(new int[]{1, 2, 3, 4, 5});
        unPopularMoviesTestCases.add(new int[]{});
        expectedResults.add(new int[]{1, 2, 3, 4, 5});

        userHistoryTestCases.add(new int[]{1, 2, 3, 4, 5});
        popularMoviesTestCases.add(new int[]{});
        unPopularMoviesTestCases.add(new int[]{});
        expectedResults.add(new int[]{});

        userHistoryTestCases.add(new int[]{1, 2, 3, 4, 5});
        popularMoviesTestCases.add(new int[]{1, 2, 3, 4, 5});
        unPopularMoviesTestCases.add(new int[]{1, 2, 3, 4, 5});
        expectedResults.add(new int[]{});

        userHistoryTestCases.add(new int[]{});
        popularMoviesTestCases.add(new int[]{});
        unPopularMoviesTestCases.add(new int[]{});
        expectedResults.add(new int[]{});

        for(int z = 0; z < expectedResults.size(); z++){
            int[] result = rm.recommendMovies(userHistoryTestCases.get(z), popularMoviesTestCases.get(z), unPopularMoviesTestCases.get(z));

            System.out.printf("%d.- User History: %s, Popular Movies: %s, Unpopular Movies: %s --> Recommended Movies --> Expected: %s, calculated: %s%n",
                    (z + 1), convertArrayToStr(userHistoryTestCases.get(z)), convertArrayToStr(popularMoviesTestCases.get(z)), convertArrayToStr(unPopularMoviesTestCases.get(z)),
                    convertArrayToStr(expectedResults.get(z)), convertArrayToStr(result));
        }
    }
}
