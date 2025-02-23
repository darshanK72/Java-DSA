/**
 * Problem Statement:
 * 
 *     Given a string `s` and an integer `k`, return the number of substrings that contain exactly `k` distinct characters.
 *     A substring is defined as a contiguous sequence of characters within the string.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of lowercase English letters.
 *     - An integer `k` (1 <= k <= 26), the exact number of distinct characters required in the substrings.
 * 
 * Output:
 *     - An integer representing the number of substrings with exactly `k` distinct characters.
 * 
 * Example:
 *     Input:
 *     "abcabc", k = 2
 *     Output:
 *     7
 * 
 *     Explanation:
 *     - The substrings with exactly 2 distinct characters are: "ab", "bc", "ca", "ab", "bc", "ab", "bc", resulting in a total of 7.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j07CountSubstrWithExactKDistinctChars {
    public static void main(String args[]) {
        // Reading input string and the integer k
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();

        // Calling the solution method to count substrings with exactly k distinct
        // characters
        System.out.println(countSubstrWithExactKDistinct(s, k));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Using Count of Substrings with At Most K Distinct Characters
     * 
     * Intuition:
     * - The key idea here is to calculate the number of substrings with at most `k` distinct characters and the number of 
     *   substrings with at most `k-1` distinct characters.
     * - The difference between these two counts gives the number of substrings with exactly `k` distinct characters.
     * - We use a sliding window approach with two pointers (`i` and `j`) to maintain a valid window of substrings, 
     *   where the number of distinct characters in the window is tracked using a HashMap.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. The sliding window approach ensures that we traverse the string only once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of distinct characters in the HashMap. In the worst case, the HashMap stores up to `k` distinct characters.
     * 
     * @param s The input string.
     * @param k The exact number of distinct characters required in the substrings.
     * @return The number of substrings with exactly `k` distinct characters.
     */
    public static int countSubstrWithExactKDistinct(String s, int k) {
        // Count substrings with at most `k` distinct characters
        // Subtract the count of substrings with at most `k-1` distinct characters
        return countAtMostK(k, s) - countAtMostK(k - 1, s);
    }

    /**
     * Helper Function: Count Substrings with At Most K Distinct Characters
     * 
     * Intuition:
     * - This helper function calculates the number of substrings with at most `k` distinct characters by using a sliding window 
     *   with two pointers (`i` and `j`).
     * - As we expand the window by moving the right pointer (`i`), we maintain a HashMap to track the frequency of characters.
     * - If the number of distinct characters exceeds `k`, we shrink the window from the left by moving the left pointer (`j`).
     * - The number of substrings that can end at the right pointer `i` is `i - j + 1`, and we accumulate this count.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. The sliding window approach ensures that we traverse the string only once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of distinct characters in the HashMap.
     * 
     * @param k The number of distinct characters to be considered.
     * @param s The input string.
     * @return The number of substrings with at most `k` distinct characters.
     */
    public static int countAtMostK(int k, String s) {
        int count = 0; // Initialize the count of valid substrings
        int j = 0; // Left pointer of the sliding window
        HashMap<Character, Integer> map = new HashMap<>(); // HashMap to track the frequency of characters

        // Traverse the string with the right pointer `i`
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

            // Add the number of substrings that can end at the current right pointer `i`
            count += i - j + 1;
        }

        return count; // Return the result
    }
}
