/*-
 * LeetCode 451: Sort Characters By Frequency
 * 
 * Problem Statement:
 *     Given a string s, sort it in decreasing order based on the frequency of
 *     characters. The frequency of a character is the number of times it appears
 *     in the string. Return the sorted string. If there are multiple answers,
 *     return any of them.
 * 
 * Input:
 *     - s: String containing any valid ASCII characters
 * 
 * Output:
 *     - String: Characters sorted by frequency (decreasing order)
 * 
 * Example:
 *     Input: s = "tree"
 *     Output: "eert" or "eetr"
 * 
 *     Explanation:
 *     'e' appears twice while 'r' and 't' both appear once.
 *     So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 */

import java.util.HashMap;
import java.util.PriorityQueue;

public class j08SortCharsByFrequency {

    /**
     * Helper Class: Result
     * 
     * Stores character frequency information:
     * - c: The character
     * - freq: Frequency of the character in the string
     * 
     * Used to maintain character-frequency pairs in the priority queue
     */
    static class Result {
        char c;
        int freq;

        Result(char c, int f) {
            this.c = c;
            this.freq = f;
        }
    }

    /**
     * Approach: Max Heap with Frequency Count
     * 
     * Intuition:
     * - Count frequency of each character using HashMap
     * - Use max heap to maintain characters sorted by frequency
     * - Build result string by appending characters based on frequency
     * 
     * Explanation:
     * 1. Count frequencies:
     *    - Create HashMap to store character frequencies
     *    - Iterate through string to count occurrences
     * 2. Build max heap:
     *    - Create max heap with custom comparator
     *    - Add each character-frequency pair to heap
     * 3. Construct result:
     *    - Extract characters from heap
     *    - Append each character frequency times
     * 
     * Time Complexity: O(n log n) where n is string length
     *                  - O(n) to count frequencies
     *                  - O(log n) for each heap operation
     *                  - O(n) to build result string
     * Space Complexity: O(n) for HashMap and heap
     * 
     * @param s    Input string to be sorted by frequency
     * @return     String with characters sorted by frequency (decreasing)
     */
    public static String frequencySort(String s) {
        // Count frequency of each character
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Initialize max heap with custom comparator
        // Sort by frequency in descending order
        PriorityQueue<Result> pq = new PriorityQueue<>((a, b) -> {
            return b.freq - a.freq;
        });

        // Add all character-frequency pairs to heap
        for (char key : map.keySet()) {
            pq.add(new Result(key, map.get(key)));
        }

        // Build result string
        StringBuilder out = new StringBuilder();
        while (!pq.isEmpty()) {
            Result res = pq.remove();
            // Append character frequency times
            for (int i = 0; i < res.freq; i++)
                out.append(res.c);
        }
        return out.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        String s1 = "tree";
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: \"" + frequencySort(s1) + "\"");

        // Test Case 2: Single character
        System.out.println("\nSingle Character Test:");
        String s2 = "a";
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: \"" + frequencySort(s2) + "\"");

        // Test Case 3: All characters have same frequency
        System.out.println("\nEqual Frequency Test:");
        String s3 = "abcd";
        System.out.println("Input: s = \"" + s3 + "\"");
        System.out.println("Output: \"" + frequencySort(s3) + "\"");

        // Test Case 4: Special characters
        System.out.println("\nSpecial Characters Test:");
        String s4 = "Aabb!";
        System.out.println("Input: s = \"" + s4 + "\"");
        System.out.println("Output: \"" + frequencySort(s4) + "\"");
    }
}
