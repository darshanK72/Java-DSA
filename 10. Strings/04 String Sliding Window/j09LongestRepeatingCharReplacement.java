/**
 * Problem Statement:
 * 
 *     Given a string s that consists of only uppercase English letters, you can perform 
 *     at most k character replacements. Return the length of the longest substring 
 *     containing the same letter you can get after performing at most k replacements.
 * 
 * Input:
 *     - An integer `k` (1 <= k <= 1000), representing the number of character replacements allowed.
 *     - A string `s` of length `n` (1 <= n <= 10^5), consisting of uppercase English letters (A-Z).
 * 
 * Output:
 *     - The length of the longest substring after performing at most `k` replacements.
 * 
 * Example:
 *     Input:
 *     "ABAB", k = 2
 *     Output:
 *     4
 * 
 *     Explanation:
 *     Replace the two 'B's with 'A's, the longest substring is "AAAA", which has a length of 4.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j09LongestRepeatingCharReplacement {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.next(); // Input: the string s
        int k = in.nextInt(); // Input: the number of allowed replacements
        System.out.println(characterReplacement(s, k)); // Call to the first approach
        System.out.println(characterReplacementEfficient(s, k)); // Call to the second approach
        System.out.println(characterReplacementMoreEfficient(s, k)); // Call to the third approach
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - This approach iterates through every possible substring of s and checks if it can
     *   be transformed into a string with all the same characters by performing at most `k`
     *   replacements. The idea is to calculate the frequency of each character and check if the
     *   number of characters that are not the same as the most frequent character in the 
     *   substring is less than or equal to `k`. If so, the substring is valid.
     * 
     * Time Complexity:
     * - O(n^2), as we check all possible substrings of `s` and for each substring, we perform
     *   an operation that takes O(n) time.
     * 
     * Space Complexity:
     * - O(26), which is the size of the frequency array to store counts for each letter in the alphabet.
     * 
     * @param s The input string.
     * @param k The number of allowed replacements.
     * @return The length of the longest valid substring.
     */
    public static int characterReplacement(String s, int k) {
        int maxL = 0;
        int[] hash = new int[26]; // Frequency array for characters
        for (int i = 0; i < s.length(); i++) {
            int maxF = 0;
            Arrays.fill(hash, 0); // Reset frequency array
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'A']++;
                maxF = Math.max(maxF, hash[s.charAt(j) - 'A']);
                // Check if number of characters to replace is less than or equal to k
                if ((j - i + 1) - maxF <= k) {
                    maxL = Math.max(maxL, j - i + 1);
                } else {
                    break; // No need to check further if we already exceed the allowed replacements
                }
            }
        }
        return maxL;
    }

    /**
     * Approach 2: Sliding Window for Each Character
     * 
     * Intuition:
     * - We use a sliding window approach but try to optimize by considering each character 
     *   from 'A' to 'Z' separately. For each character, we try to find the longest valid 
     *   substring where all characters are the same, and the number of replacements needed 
     *   is at most `k`. This approach reduces the number of substrings we need to check.
     * 
     * Time Complexity:
     * - O(26 * n), where we try to slide the window for each of the 26 characters.
     * 
     * Space Complexity:
     * - O(1), since we only need a few variables to track the window and counts.
     * 
     * @param s The input string.
     * @param k The number of allowed replacements.
     * @return The length of the longest valid substring.
     */
    public static int characterReplacementEfficient(String s, int k) {
        int maxL = 0;
        for (char c = 'A'; c <= 'Z'; c++) { // Iterate over each character in the alphabet
            int j = 0;
            int tempL = 0;
            int canChange = 0; // To count the number of changes made
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != c) {
                    canChange++; // Increment the number of changes
                }
                while (canChange > k && j <= i) {
                    if (s.charAt(j) != c) {
                        canChange--; // Reduce the number of changes
                    }
                    j++; // Shrink the window from the left
                }
                tempL = Math.max(tempL, i - j + 1); // Update the max length found
            }
            maxL = Math.max(maxL, tempL); // Update the overall maximum length
        }
        return maxL;
    }

    /**
     * Approach 3: Optimized Sliding Window
     * 
     * Intuition:
     * - This approach improves further by maintaining a frequency count of characters in the current window.
     *   We track the maximum frequency of a character and use this to decide if the window is valid 
     *   without needing to check the entire substring. If the length of the window minus the maximum frequency 
     *   exceeds `k`, we shrink the window from the left.
     * 
     * Time Complexity:
     * - O(n), where we slide the window over the string once while maintaining the frequency count.
     * 
     * Space Complexity:
     * - O(26), for the frequency count array.
     * 
     * @param s The input string.
     * @param k The number of allowed replacements.
     * @return The length of the longest valid substring.
     */
    public static int characterReplacementMoreEfficient(String s, int k) {
        int maxL = 0;
        int maxF = 0; // Maximum frequency of any character in the current window
        int[] hash = new int[26]; // Frequency array for characters in the window
        int j = 0; // Left pointer for the sliding window
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'A']++; // Update frequency of the current character
            maxF = Math.max(maxF, hash[s.charAt(i) - 'A']); // Update the max frequency
            // If the window size minus the maximum frequency exceeds k, shrink the window
            if ((i - j + 1) - maxF > k) {
                hash[s.charAt(j) - 'A']--; // Shrink the window from the left
                maxF = 0; // Reset max frequency
                for (int l = 0; l < 26; l++) {
                    maxF = Math.max(maxF, hash[l]); // Recompute the max frequency
                }
                j++; // Move left pointer to shrink the window
            }
            // If the window is valid, update the result
            if ((i - j + 1) - maxF <= k) {
                maxL = Math.max(maxL, (i - j + 1));
            }
        }
        return maxL;
    }
}
