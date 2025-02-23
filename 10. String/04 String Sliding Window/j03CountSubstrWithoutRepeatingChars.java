/**
 * Problem Statement:
 * 
 *     Given a string `s`, the task is to find the number of substrings of `s` that do not contain repeating characters.
 *     A substring is defined as any contiguous part of the string.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of lowercase English letters.
 * 
 * Output:
 *     - An integer representing the number of substrings without repeating characters.
 * 
 * Example:
 *     Input:
 *     "abcabcbb"
 *     Output:
 *     10
 * 
 *     Explanation:
 *     - The substrings without repeating characters are: "a", "b", "c", "ab", "bc", "abc", "bca", "cab", "abc", "b".
 *     - The total count is 10.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j03CountSubstrWithoutRepeatingChars {
    public static void main(String args[]) {
        // Reading input string
        Scanner in = new Scanner(System.in);
        String s = in.next();

        // Calling the efficient solution to count substrings without repeating
        // characters
        System.out.println(countSubstringEfficient(s));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach: Sliding Window with HashMap
     * 
     * Intuition:
     * - To count the substrings without repeating characters, we can use a sliding window technique.
     * - We maintain a window that contains only unique characters. 
     * - For each character, we count how many substrings end at that character without repeating characters.
     * - We use a HashMap to store the count of characters in the current window.
     * 
     * Explanation:
     * - We maintain a window of valid substrings where no character repeats.
     * - For each character `s[i]`, we move the start of the window (`j`) forward until we remove any repeating characters.
     * - At each position, the number of valid substrings that end at `i` is `i - j + 1`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We iterate through the string once with the sliding window approach.
     * 
     * Space Complexity:
     * - O(k), where `k` is the size of the character set (for lowercase English letters, `k = 26`).
     * 
     * @param s The input string.
     * @return The count of substrings without repeating characters.
     */
    public static int countSubstringEfficient(String s) {
        int count = 0; // Initialize the count of valid substrings
        int j = 0; // Start of the sliding window
        HashMap<Character, Integer> map = new HashMap<>(); // Map to store the frequency of characters in the current
                                                           // window

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Current character
            map.put(ch, map.getOrDefault(ch, 0) + 1); // Increment the count of the current character

            // If the current character repeats, shrink the window from the left
            while (map.get(ch) > 1) {
                char ch1 = s.charAt(j); // Character at the start of the window
                map.put(ch1, map.getOrDefault(ch1, 0) - 1); // Decrement its count
                j++; // Move the start of the window forward
            }

            // Add the number of valid substrings ending at position `i`
            count += (i - j + 1);
        }

        return count; // Return the total count of substrings
    }
}
