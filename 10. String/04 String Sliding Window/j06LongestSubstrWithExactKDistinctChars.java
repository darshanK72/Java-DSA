/**
 * Problem Statement:
 * 
 *     Given a string `s` and an integer `k`, find the length of the longest substring that contains exactly `k`
 *     distinct characters. If there is no such substring, return -1.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of lowercase English letters.
 *     - An integer `k` (1 <= k <= 26), the exact number of distinct characters required in the substring.
 * 
 * Output:
 *     - An integer representing the length of the longest substring with exactly `k` distinct characters. 
 *       If no such substring exists, return -1.
 * 
 * Example:
 *     Input:
 *     "aabacbebebe", k = 3
 *     Output:
 *     7
 * 
 *     Explanation:
 *     - The longest substring with exactly 3 distinct characters is "cbebebe", and its length is 7.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j06LongestSubstrWithExactKDistinctChars {
    public static void main(String args[]) {
        // Reading input string and the integer k
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();

        // Calling the basic solution to find the longest substring with exactly k
        // distinct characters
        System.out.println(longestkSubstr(s, k));

        // Calling the efficient solution to find the longest substring with exactly k
        // distinct characters
        System.out.println(longestkSubstrEfficient(s, k));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force Solution
     * 
     * Intuition:
     * - We generate all possible substrings from every starting index (`i`) and check if the number of distinct characters
     *   in each substring is exactly `k`. If the condition holds, we update the maximum length.
     * - This approach is simple but inefficient as it involves generating all substrings and checking distinct characters,
     *   which is time-consuming.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the string. We generate all possible substrings, and for each one, 
     *   we check the number of distinct characters, which takes O(n) time in the worst case.
     * 
     * Space Complexity:
     * - O(k), where `k` is the maximum number of distinct characters (as we use a HashSet to store distinct characters).
     * 
     * @param s The input string.
     * @param k The exact number of distinct characters allowed in the substring.
     * @return The length of the longest substring with exactly `k` distinct characters.
     */
    public static int longestkSubstr(String s, int k) {
        int maxL = -1; // Initialize the maximum length of the valid substring
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>(); // HashSet to store distinct characters in the substring
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j); // Current character
                set.add(ch); // Add character to the set
                if (set.size() > k) // If the number of distinct characters exceeds `k`, break the loop
                    break;
                else if (set.size() == k) { // If we have exactly `k` distinct characters
                    maxL = Math.max(maxL, j - i + 1); // Update the maximum length
                }
            }
        }
        return maxL; // Return the result
    }

    /**
     * Approach 2: Sliding Window with HashMap (Optimized Solution)
     * 
     * Intuition:
     * - We use the sliding window technique with two pointers: `i` (right pointer) and `j` (left pointer).
     * - As we expand the window by moving `i`, we use a HashMap to keep track of the frequency of characters in the window.
     * - If the size of the HashMap (i.e., number of distinct characters) exceeds `k`, we move the left pointer (`j`) 
     *   to shrink the window until it contains exactly `k` distinct characters.
     * - At each step, we check if the window has exactly `k` distinct characters and update the maximum length.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. Both pointers (`i` and `j`) traverse the string once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the size of the HashMap (i.e., the number of distinct characters in the window).
     * 
     * @param s The input string.
     * @param k The exact number of distinct characters allowed in the substring.
     * @return The length of the longest substring with exactly `k` distinct characters.
     */
    public static int longestkSubstrEfficient(String s, int k) {
        int maxL = -1; // Initialize the maximum length of the valid substring
        int j = 0; // Left pointer of the sliding window
        HashMap<Character, Integer> map = new HashMap<>(); // HashMap to store the frequency of characters in the window

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Current character at the right pointer
            map.put(ch, map.getOrDefault(ch, 0) + 1); // Increment the frequency of the current character

            // If the number of distinct characters exceeds `k`, move the left pointer `j`
            // to shrink the window
            while (map.size() > k) {
                char ch1 = s.charAt(j); // Character at the left pointer
                map.put(ch1, map.get(ch1) - 1); // Decrease the frequency of the character at `j`
                if (map.get(ch1) == 0) {
                    map.remove(ch1); // Remove the character if its frequency becomes zero
                }
                j++; // Move the left pointer to shrink the window
            }

            // If the window has exactly `k` distinct characters, update the maximum length
            if (map.size() == k) {
                maxL = Math.max(maxL, i - j + 1); // Update the result
            }
        }

        return maxL; // Return the result
    }
}
