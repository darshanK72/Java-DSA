/**
 * Problem Statement:
 *     First Non-Repeating Character in a Stream
 * 
 *     Given a string A denoting a stream of lowercase alphabets, find the first 
 *     non-repeating character in the stream at every index. If no such character 
 *     exists, append '#'.
 * 
 * Input:
 *     - String A containing lowercase letters
 *     - 1 <= length of String <= 10^5
 * 
 * Output:
 *     - String where each character represents first non-repeating character at that point
 * 
 * Example:
 *     Input: A = "aabcbc"
 *     Output: "a#bcbc"
 *     
 *     Explanation:
 *     - "a" -> first non-repeating is 'a'
 *     - "aa" -> no non-repeating char, so '#'
 *     - "aab" -> first non-repeating is 'b'
 *     - "aabc" -> first non-repeating is 'b'
 *     - "aabcb" -> first non-repeating is 'c'
 *     - "aabcbc" -> first non-repeating is 'c'
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class j04StreemFirstNonRepeatingCharacter {

    public static void main(String args[]) {
        // Test cases with different scenarios
        String[] testCases = {
            "aabcbc",           // Basic case
            "abcabcababcc",     // Longer sequence
            "aabbcc",           // No non-repeating characters
            "",                 // Empty string
            "xyzxyz"           // Repeating characters
        };

        // Test each case
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("First Non-Repeating: " + firstNonRepeating(test));
            System.out.println();
        }
    }

    /**
     * Approach: Using Queue and HashMap
     * 
     * Intuition:
     * - Use HashMap to track frequency of each character
     * - Use Queue to maintain order of non-repeating characters
     * - For each character in stream:
     *   * Update frequency in map
     *   * Add to queue if first occurrence
     *   * Remove repeating characters from queue front
     *   * First character in queue is first non-repeating
     * 
     * Time Complexity: O(n)
     * - Single pass through string
     * - Queue operations are O(1)
     * - HashMap operations are O(1)
     * 
     * Space Complexity: O(1)
     * - HashMap stores at most 26 characters
     * - Queue stores at most 26 characters
     * - StringBuilder for output of length n
     */
    public static String firstNonRepeating(String s) {
        StringBuilder out = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();

        // Process each character in stream
        for (char c : s.toCharArray()) {
            // Update frequency in map
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            // Add to queue if first occurrence
            if (map.get(c) < 2) {
                queue.add(c);
            }

            // Remove repeating characters from queue front
            while (!queue.isEmpty() && map.get(queue.peek()) > 1) {
                queue.remove();
            }

            // Append current first non-repeating character
            if (queue.isEmpty())
                out.append("#");
            else
                out.append(queue.peek());
        }

        return out.toString();
    }
}
