/**
 * Problem Statement:
 * 
 *     Given two arrays of strings `words1` and `words2`, we need to find all the words in `words1` that are universal words.
 *     A word is considered universal if for every word in `words2`, the word in `words1` contains all the characters from that word in `words2` with at least the same frequency.
 * 
 * Input:
 *     - An array `words1` of size `n1` (1 <= n1 <= 100) where each element is a string.
 *     - An array `words2` of size `n2` (1 <= n2 <= 100) where each element is a string.
 * 
 * Output:
 *     - A list of strings from `words1` that are universal words as described.
 * 
 * Example:
 * Input:
 *     ["amazon", "apple", "facebook", "google", "leetcode"]
 *     ["e", "o"]
 * Output:
 *     ["facebook", "google", "leetcode"]
 * 
 * Explanation:
 *     - "facebook", "google", and "leetcode" are the words that contain all the characters from the words "e" and "o" with at least the same frequency.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class j15WordSubsets {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: size of first array (words1)
        String[] words1 = new String[n1];
        for (int i = 0; i < n1; i++) {
            words1[i] = in.next(); // Input: elements of words1 array
        }

        int n2 = in.nextInt(); // Input: size of second array (words2)
        String[] words2 = new String[n2];
        for (int i = 0; i < n2; i++) {
            words2[i] = in.next(); // Input: elements of words2 array
        }

        System.out.println(wordSubsets(words1, words2)); // Output result for first approach
        System.out.println(wordSubsetsEfficient(words1, words2)); // Output result for efficient approach
        in.close();
    }

    /**
     * Approach 1: Word Subsets with HashMap
     * 
     * Intuition:
     * - In this approach, we create a list of HashMaps that store the frequency of each character for every word in words2.
     * - Then, for each word in words1, we create a frequency map and check if it satisfies the frequency condition for all the words in words2.
     * - We check that for every character in a word from words2, the corresponding word in words1 must have equal or greater frequency of that character.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the total number of characters in words1 and `n` is the total number of characters in words2.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of unique characters in the word, typically a small constant (e.g., 26 for lowercase English letters).
     * 
     * @param words1 The first array of words.
     * @param words2 The second array of words to compare against.
     * @return A list of words from words1 that are universal according to the given condition.
     */
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        // List to store character frequency maps for each word in words2
        ArrayList<HashMap<Character, Integer>> mapList = new ArrayList<>();

        // Creating frequency map for each word in words2
        for (String word : words2) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : word.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            mapList.add(map);
        }

        // List to store the universal words from words1
        ArrayList<String> out = new ArrayList<>();

        // Checking each word in words1
        for (String word : words1) {
            HashMap<Character, Integer> tmap = new HashMap<>();
            for (char c : word.toCharArray()) {
                tmap.put(c, tmap.getOrDefault(c, 0) + 1);
            }

            boolean isUni = true;
            // Check if the current word in words1 is universal for all words in words2
            for (HashMap<Character, Integer> map : mapList) {
                if (!isUniversal(tmap, map)) {
                    isUni = false;
                    break;
                }
            }
            if (isUni) {
                out.add(word); // Add to result if the word is universal
            }
        }
        return out;
    }

    /**
     * Helper method to check if a word in words1 is universal for a word in words2
     * 
     * @param tmap The frequency map of a word in words1.
     * @param map The frequency map of a word in words2.
     * @return True if the word in words1 is universal for the word in words2.
     */
    public static boolean isUniversal(HashMap<Character, Integer> tmap, HashMap<Character, Integer> map) {
        for (char key : map.keySet()) {
            if (!tmap.containsKey(key) || tmap.get(key) < map.get(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Approach 2: Efficient Word Subsets using Max Frequency Array
     * 
     * Intuition:
     * - In this approach, we optimize the previous method by directly storing the maximum frequency of each character across all words in words2.
     * - This reduces the complexity because we no longer need to compare individual frequency maps for each word in words2 for each word in words1.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of words in words1 and `m` is the average length of words in words1 and words2.
     * 
     * Space Complexity:
     * - O(1), as we only maintain a fixed-size array of size 26 for character frequencies.
     * 
     * @param words1 The first array of words.
     * @param words2 The second array of words to compare against.
     * @return A list of words from words1 that are universal according to the given condition.
     */
    public static List<String> wordSubsetsEfficient(String[] words1, String[] words2) {
        // Array to store the maximum frequency of each character across all words in
        // words2
        int[] maxFreq = new int[26];

        // Process each word in words2 to calculate maximum frequency of each character
        for (String s : words2) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Update the maximum frequencies
            for (int i = 0; i < 26; i++) {
                maxFreq[i] = Math.max(maxFreq[i], freq[i]);
            }
        }

        // List to store the universal words from words1
        ArrayList<String> out = new ArrayList<>();

        // Checking each word in words1
        for (String word : words1) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            boolean isUniversal = true;
            // Check if the current word in words1 satisfies the universal condition
            for (int i = 0; i < 26; i++) {
                if (freq[i] < maxFreq[i]) {
                    isUniversal = false;
                    break;
                }
            }

            if (isUniversal) {
                out.add(word); // Add to result if the word is universal
            }
        }
        return out;
    }
}
