/**
 * Problem Statement:
 * 
 *     Given two sentences, s1 and s2, return an array of the uncommon words between the two sentences. 
 *     A word is considered uncommon if it appears exactly once in either s1 or s2, but not in both.
 * 
 * Input:
 *     - Two strings s1 and s2 representing sentences. Each sentence contains a sequence of words separated by spaces.
 * 
 * Output:
 *     - An array of strings containing the uncommon words in either of the two sentences.
 * 
 * Example:
 *     Input:
 *     "this apple is sweet"
 *     "this orange is sweet"
 *     Output:
 *     ["apple", "orange"]
 * 
 *     Explanation:
 *     - "apple" is uncommon because it appears only in s1.
 *     - "orange" is uncommon because it appears only in s2.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j21UncommonWords {

    public static void main(String args[]) {
        // Reading input sentences
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine(); // First sentence
        String s2 = in.nextLine(); // Second sentence

        // Calling the function to get the uncommon words
        System.out.println(Arrays.toString(uncommonFromSentences(s1, s2)));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach: Using HashMap to count word frequencies.
     * 
     * Intuition:
     * - We will split both sentences into words and use a HashMap to count the occurrences of each word.
     * - After populating the HashMap, we will iterate through it to find words that appear exactly once in either sentence.
     * 
     * Time Complexity:
     * - O(n + m), where n is the length of sentence s1 and m is the length of sentence s2. 
     * - The time complexity is dominated by the time taken to split both sentences into words and count their frequencies.
     * 
     * Space Complexity:
     * - O(k), where k is the number of unique words in both sentences.
     * 
     * @param s1 The first input sentence.
     * @param s2 The second input sentence.
     * @return An array of uncommon words from both sentences.
     */
    public static String[] uncommonFromSentences(String s1, String s2) {
        // Splitting both sentences into words
        String[] strs1 = s1.split(" ");
        String[] strs2 = s2.split(" ");

        // HashMap to store the frequency of each word
        HashMap<String, Integer> map = new HashMap<>();

        // Counting words from the first sentence
        for (String s : strs1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        // Counting words from the second sentence
        for (String s : strs2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        // List to store the uncommon words
        ArrayList<String> temp = new ArrayList<>();

        // Checking which words appear exactly once
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                temp.add(key);
            }
        }

        // Converting the list of uncommon words to an array
        String[] out = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            out[i] = temp.get(i);
        }
        return out;
    }
}
