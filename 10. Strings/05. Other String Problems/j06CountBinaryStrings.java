/**
 * Problem Statement:
 * 
 *     Given a binary string `s`, the task is to find the number of binary substrings that follow the pattern where the number of
 *     consecutive `0`s is equal to the number of consecutive `1`s. 
 *     In other words, a valid substring must have two consecutive groups, one containing only `0`s and the other containing only `1`s,
 *     where both groups have the same length.
 * 
 * Input:
 *     - A binary string `s` of length `n` (1 <= n <= 1000).
 * 
 * Output:
 *     - An integer representing the number of valid binary substrings in `s`.
 * 
 * Example:
 *     Input:
 *     "00110011"
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The valid binary substrings are:
 *     "0011", "01", "1100", "10", "0011", and "01".
 */

import java.util.Scanner;

public class j06CountBinaryStrings {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the binary string
        System.out.println(countBinarySubstrings(s)); // Call to count binary substrings
        in.close();
    }

    /**
     * Approach: Count the Consecutive Blocks of 0's and 1's
     * 
     * Intuition:
     * - To solve this problem, we need to break the binary string into blocks of consecutive identical characters ('0's or '1's).
     * - The key observation is that a valid binary substring consists of two consecutive blocks, one of `0`s and one of `1`s, where
     *   both blocks have the same length.
     * - We can iterate through the string and count the lengths of these consecutive blocks.
     * - Every time we encounter a switch from one character to another, we add the minimum of the current block's length and the previous
     *   block's length to the total count. This is because the number of valid substrings between two blocks is limited by the shorter block.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We iterate through the string once.
     * 
     * Space Complexity:
     * - O(1), since we only use a constant amount of space for variables like `prevLen`, `currLen`, and `count`.
     * 
     * @param s The binary string.
     * @return The number of valid binary substrings.
     */
    public static int countBinarySubstrings(String s) {
        int prevLen = 0; // Length of the previous block
        int currLen = 1; // Length of the current block
        int count = 0; // To store the count of valid substrings

        // Iterate through the string, starting from the second character
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // If the current character is the same as the previous one, increment the
                // current block length
                currLen++;
            } else {
                // If we switch to a different character, add the minimum of the current and
                // previous block lengths to the count
                count += Math.min(currLen, prevLen);
                prevLen = currLen; // Set the previous block length to the current block length
                currLen = 1; // Start a new block
            }
        }

        // After the loop, we need to check once more for the last block
        count += Math.min(currLen, prevLen);

        return count; // Return the total count of valid binary substrings
    }
}
