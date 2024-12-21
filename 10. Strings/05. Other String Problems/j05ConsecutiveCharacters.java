/**
 * Problem Statement:
 * 
 *     Given a string `s`, the task is to find the length of the longest substring that consists of the same character
 *     appearing consecutively.
 *     A substring is defined as a sequence of characters in a string. We are interested in the longest contiguous segment
 *     where the characters are the same.
 * 
 * Input:
 *     - A string `s` of length `n` (1 <= n <= 1000).
 * 
 * Output:
 *     - An integer representing the length of the longest consecutive character segment in `s`.
 * 
 * Example:
 *     Input:
 *     "abbcccddddeee"
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The longest sequence of consecutive characters is "dddd", which has a length of 4.
 */

import java.util.Scanner;

public class j05ConsecutiveCharacters {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the string
        System.out.println(maxPower(s)); // Call to find the longest consecutive character segment
        in.close();
    }

    /**
     * Approach: Single Pass with a Power Tracker
     * 
     * Intuition:
     * - The goal is to iterate through the string while keeping track of the length of the longest sequence of consecutive
     *   characters that are the same.
     * - We initialize two variables: `power` to track the maximum length of consecutive characters found so far, and 
     *   `currentPower` to track the length of the current sequence of identical characters.
     * - As we iterate through the string:
     *   - If the current character is the same as the previous one, we increment the `currentPower`.
     *   - If the current character is different from the previous one, we compare the `currentPower` with the `power`
     *     and update the maximum if necessary.
     *   - We continue until we have processed the entire string.
     * - The key idea is to only pass through the string once, making this approach efficient.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We traverse the string once.
     * 
     * Space Complexity:
     * - O(1), since we only use a fixed amount of space for tracking the powers and no additional data structures.
     * 
     * @param s The input string.
     * @return The length of the longest consecutive character sequence.
     */
    public static int maxPower(String s) {
        int power = 1; // To keep track of the longest sequence
        int currentPower = 1; // To track the length of the current sequence of identical characters

        // Start iterating from the second character
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // If the current character matches the previous one, increment the current
                // power
                currentPower++;
            } else {
                // If the current character doesn't match, update the maximum power
                power = Math.max(power, currentPower);
                currentPower = 1; // Reset the current power for the next sequence
            }
        }

        // After the loop, we need to check once more in case the longest sequence ends
        // at the last character
        power = Math.max(power, currentPower);

        return power; // Return the length of the longest sequence
    }
}
