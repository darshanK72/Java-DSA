/**
 * LeetCode 358. Rearrange String k Distance Apart
 * 
 * Problem Statement:
 *     Given a string s and an integer k, check if it's possible to rearrange the string such that
 *     the same characters are at least distance k from each other. Return 1 if possible, 0 if not.
 * 
 * Input:
 *     - s (String): Input string to be rearranged
 *     - k (int): Minimum distance required between same characters
 * 
 * Output:
 *     - int: 1 if rearrangement is possible, 0 if not
 * 
 * Example:
 *     Input: s = "aabbcc", k = 3
 *     Output: 1
 * 
 *     Explanation:
 *     - One possible arrangement: "abcabc"
 *     - Each character is at least 3 positions apart from its duplicates
 *     - 'a' appears at positions 0 and 3
 *     - 'b' appears at positions 1 and 4
 *     - 'c' appears at positions 2 and 5
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class j04ReorganizeStringII {

    /**
     * Approach: Greedy with Max Heap
     * 
     * Intuition:
     * - We need to check if it's possible to arrange characters k distance apart
     * - The most frequent character determines if arrangement is possible
     * - For a character with frequency f, we need at least (f-1)*k + 1 positions
     * - If most frequent character's frequency > (n+k-1)/k, arrangement is impossible
     * 
     * Explanation:
     * - Step 1: Count frequency of each character using HashMap
     * - Step 2: Create max heap to get most frequent character
     * - Step 3: Check if most frequent character's frequency exceeds limit
     * - Step 4: Return 1 if possible, 0 if not
     * 
     * Time Complexity: O(n) where n is length of string
     *                  - O(n) for frequency counting
     *                  - O(n) for heap operations
     * Space Complexity: O(n) for storing frequency map and heap
     * 
     * @param s    Input string to be rearranged
     * @param k    Minimum distance required between same characters
     * @return     1 if rearrangement is possible, 0 if not
     */
    public static int favString(String s, int k) {
        // Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Create max heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(freqMap.entrySet());
        
        int n = s.length();

        // Check if most frequent character's frequency exceeds limit
        if (maxHeap.peek().getValue() > (n + k - 1)/k) {
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case - possible arrangement
        System.out.println("\nBasic Test Case - Possible:");
        System.out.println("Input: s = \"aabbcc\", k = 3");
        System.out.println("Expected: 1, Output: " + favString("aabbcc", 3));

        // Test Case 2: Basic case - impossible arrangement
        System.out.println("\nBasic Test Case - Impossible:");
        System.out.println("Input: s = \"aaabc\", k = 3");
        System.out.println("Expected: 0, Output: " + favString("aaabc", 3));

        // Test Case 3: Edge case - empty string
        System.out.println("\nEdge Case - Empty String:");
        System.out.println("Input: s = \"\", k = 2");
        System.out.println("Expected: 1, Output: " + favString("", 2));

        // Test Case 4: Edge case - single character
        System.out.println("\nEdge Case - Single Character:");
        System.out.println("Input: s = \"a\", k = 2");
        System.out.println("Expected: 1, Output: " + favString("a", 2));

        // Test Case 5: Special case - all same characters
        System.out.println("\nSpecial Case - All Same Characters:");
        System.out.println("Input: s = \"aaaa\", k = 2");
        System.out.println("Expected: 0, Output: " + favString("aaaa", 2));
    }
}
