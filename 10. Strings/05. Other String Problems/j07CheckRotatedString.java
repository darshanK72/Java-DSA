/**
 * Problem Statement:
 * 
 *     Given two strings `s` and `goal`, determine if one string is a rotation of the other.
 *     A string is considered a rotation of another if it can be obtained by taking some substring from the start of the string 
 *     and moving it to the end. For example, "abc" is a rotation of "cab" but not of "acd".
 * 
 * Input:
 *     - Two strings `s` and `goal` of length `n` (1 <= n <= 100).
 * 
 * Output:
 *     - Return `true` if `goal` is a rotation of `s`, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     "abc", "cab"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     "cab" can be obtained by rotating "abc" once.
 */

import java.util.Scanner;

public class j07CheckRotatedString {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine(); // First input string
        String s2 = in.nextLine(); // Second input string
        System.out.println(rotateString(s1, s2)); // Call to brute force method
        System.out.println(rotateStringEfficient(s1, s2)); // Call to efficient method
        in.close();
    }

    /**
     * Approach 1: Brute Force (Shifting Strings)
     * 
     * Intuition:
     * - One approach to determine if one string is a rotation of another is by performing a series of rotations on the first string.
     * - For each rotation, we check if it matches the second string.
     * - If any rotation matches, we return `true`; otherwise, we return `false`.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the string. We perform `n` shifts, and each shift requires copying a substring of length `n`.
     * 
     * Space Complexity:
     * - O(n), for storing the rotated string.
     * 
     * @param s The original string.
     * @param goal The string to compare against.
     * @return `true` if `goal` is a rotation of `s`, otherwise `false`.
     */
    public static boolean rotateString(String s, String goal) {
        // If the lengths are different, they can't be rotations
        if (s.length() != goal.length())
            return false;

        int i = 0;
        while (i < s.length()) {
            // Perform the shift operation
            s = shift(s);
            // If the shifted string matches the goal string, return true
            if (s.equals(goal))
                return true;
            i++;
        }
        return false;
    }

    /**
     * Approach 2: Efficient String Search (Doubling the Goal String)
     * 
     * Intuition:
     * - A more efficient approach involves concatenating the goal string to itself.
     * - If `s` is a rotation of `goal`, then `s` must appear as a substring of `goal + goal`.
     * - This avoids the need to explicitly shift the string multiple times.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We are concatenating the goal string with itself and performing a substring search.
     * 
     * Space Complexity:
     * - O(n), for storing the doubled `goal` string.
     * 
     * @param s The original string.
     * @param goal The string to compare against.
     * @return `true` if `goal` is a rotation of `s`, otherwise `false`.
     */
    public static boolean rotateStringEfficient(String s, String goal) {
        // If the lengths are different, they can't be rotations
        if (s.length() != goal.length())
            return false;

        // Concatenate the goal string with itself
        goal += goal;

        // Check if the original string `s` appears in the doubled goal string
        return goal.indexOf(s) != -1;
    }

    /**
     * Helper Method: Shift the String by One Character
     * 
     * This method shifts the input string `s` to the right by one character.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string, as we are creating a new string with the shifted characters.
     * 
     * Space Complexity:
     * - O(n), for storing the shifted string.
     * 
     * @param s The original string.
     * @return The shifted string.
     */
    public static String shift(String s) {
        // Take the last character and prepend it to the substring from the beginning to
        // the second-to-last character
        String out = s.substring(s.length() - 1);
        out += s.substring(0, s.length() - 1);
        return out;
    }
}
