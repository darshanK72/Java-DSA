/**
 * LeetCode 767. Reorganize String
 * 
 * Problem Statement:
 *     Given a string s, rearrange the characters of s so that no two adjacent
 *     characters are the same. If it is not possible to rearrange the string,
 *     return an empty string.
 * 
 * Input:
 *     - s (String): Input string containing only lowercase English letters
 *     - 1 <= s.length <= 500
 * 
 * Output:
 *     - Rearranged string where no two adjacent characters are the same
 *     - Empty string if rearrangement is not possible
 * 
 * Example:
 *     Input: s = "aab"
 *     Output: "aba"
 * 
 *     Explanation:
 *     It is possible to rearrange the string so that no two adjacent characters
 *     are the same. One possible arrangement is "aba".
 */

import java.util.PriorityQueue;

public class j03ReorganizeString {
    /**
     * Helper class to store character and its frequency
     */
    static class Pair {
        char c;      // Character
        int count;   // Frequency of character

        Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    /**
     * Approach: Greedy with Priority Queue
     * 
     * Intuition:
     * - To avoid adjacent same characters, we should alternate between different
     *   characters
     * - We should prioritize characters with higher frequencies first
     * - Use a max heap to always get the character with highest remaining frequency
     * - Keep track of the last used character to avoid repetition
     * 
     * Explanation:
     * 1. Count frequency of each character
     * 2. Create a max heap of character-frequency pairs
     * 3. Build result string by:
     *    - Taking character with highest frequency
     *    - Adding it to result
     *    - Decrementing its frequency
     *    - Putting back the previous character if it still has frequency
     * 4. Handle the last character separately
     * 
     * Time Complexity: O(n log k) where n is string length and k is number of
     *                  unique characters (max 26)
     * Space Complexity: O(k) for storing character frequencies and priority queue
     * 
     * @param s Input string
     * @return  Rearranged string or empty string if not possible
     */
    public static String reorganizeString(String s) {
        // Count frequency of each character
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }

        // Create max heap of character-frequency pairs
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return b.count - a.count;
        });

        // Add all characters with non-zero frequency to heap
        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0)
                pq.add(new Pair((char) ('a' + i), chars[i]));
        }

        // Build result string
        Pair prev = new Pair('#', 0);  // Dummy previous character
        StringBuilder out = new StringBuilder();

        // Process characters until heap is empty
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            out.append(curr.c);
            curr.count--;
            
            // Add previous character back if it still has frequency
            if (prev.count > 0)
                pq.add(prev);
            prev = curr;
        }

        // Handle the last character
        if (prev.count > 0) {
            if (prev.c == out.charAt(out.length() - 1))
                return "";  // Not possible to rearrange
            else
                out.append(prev.c);
        }

        return out.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        String s1 = "aab";
        System.out.println("Input: " + s1);
        System.out.println("Expected: aba, Output: " + reorganizeString(s1));

        // Test Case 2: Impossible case
        System.out.println("\nImpossible Test Case:");
        String s2 = "aaab";
        System.out.println("Input: " + s2);
        System.out.println("Expected: , Output: " + reorganizeString(s2));

        // Test Case 3: Single character
        System.out.println("\nSingle Character Test Case:");
        String s3 = "a";
        System.out.println("Input: " + s3);
        System.out.println("Expected: a, Output: " + reorganizeString(s3));

        // Test Case 4: All same characters
        System.out.println("\nAll Same Characters Test Case:");
        String s4 = "aaa";
        System.out.println("Input: " + s4);
        System.out.println("Expected: , Output: " + reorganizeString(s4));
    }
}
