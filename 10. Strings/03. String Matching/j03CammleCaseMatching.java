/**
 * Problem Statement:
 * 
 *     Given a list of strings `queries` and a string `pattern`, return a list of booleans where each boolean represents 
 *     whether the string in `queries` matches the camel case pattern `pattern`.
 *     A string `s` matches the pattern `p` if we can obtain `s` by deleting some characters of `s` such that the remaining 
 *     characters are in camel case format and follow the pattern.
 * 
 *     A camel case pattern is such that:
 *         - The letters of the pattern must appear in the string in the same order.
 *         - The characters in the pattern must be uppercase letters, and the rest of the string may contain any characters 
 *           (capital or lowercase).
 * 
 * Input:
 *     - An array of strings `queries` where each string is of length at most 100.
 *     - A string `pattern` of length at most 100.
 * 
 * Output:
 *     - A list of booleans, where each boolean indicates if the string in `queries` matches the camel case pattern `pattern`.
 * 
 * Example:
 *     Input:
 *     ["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"]
 *     "FoBaT"
 *     Output:
 *     [true, true, false, true, true]
 * 
 *     Explanation:
 *     "FooBar" matches the pattern "FoBaT" (First F and B match).
 *     "FooBarTest" matches the pattern "FoBaT" (First F, B and T match).
 *     "FootBall" does not match because 'o' and 't' do not match the pattern.
 *     "FrameBuffer" matches the pattern "FoBaT" (First F, B and T match).
 *     "ForceFeedBack" matches the pattern "FoBaT" (First F, B and T match).
 */

import java.util.ArrayList;
import java.util.List;

public class j03CammleCaseMatching {

    public static void main(String args[]) {
        // Sample input
        String[] strs = new String[] { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
        String pattern = "FoBaT";

        // Call camelMatch function to check each query against the pattern
        System.out.println(camelMatch(strs, pattern));
    }

    /**
     * Approach 1: Greedy Matching for Camel Case
     * 
     * Intuition:
     * - The approach involves scanning each string and matching it against the pattern using a greedy technique.
     * - For each string, we maintain a pointer `i` that tracks the position in the pattern.
     * - As we iterate over each character `c` in the string `s`, we compare it with the current character in the pattern.
     * - If the character matches, we move the pointer `i` in the pattern.
     * - If the character is uppercase and it doesn't match the current pattern character, we return `false` because we can't 
     *   skip uppercase letters.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the length of the `queries` array and `m` is the average length of the strings in `queries`. 
     *   We perform a linear scan for each string to check if it matches the pattern.
     * 
     * Space Complexity:
     * - O(n), for the output list to store the boolean results for each query string.
     * 
     * @param queries The list of query strings.
     * @param pattern The pattern string to match against.
     * @return A list of booleans indicating whether each query matches the pattern.
     */
    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        ArrayList<Boolean> out = new ArrayList<>();

        // Check each query string
        for (String s : queries) {
            out.add(isMatch(s, pattern)); // Check if current string matches the pattern
        }

        return out; // Return the list of results
    }

    /**
     * Approach 2: Matching a Single String to Pattern
     * 
     * Intuition:
     * - To check if a single string `s` matches the pattern, we iterate over each character in `s`.
     * - Whenever we find a matching uppercase character from `pattern`, we increment the pattern pointer.
     * - If we encounter an uppercase character that does not match the current character in `pattern`, we return `false`.
     * - If we finish scanning the string and successfully matched all characters in the pattern, we return `true`.
     * 
     * Time Complexity:
     * - O(m), where `m` is the length of string `s`. We check each character of the string once.
     * 
     * Space Complexity:
     * - O(1), no additional space is used other than variables for iteration.
     * 
     * @param s The string to check for a match.
     * @param pattern The pattern to match against.
     * @return A boolean indicating if the string `s` matches the camel case pattern.
     */
    public static boolean isMatch(String s, String pattern) {
        int i = 0; // Pointer for pattern

        // Iterate over each character in the string
        for (char c : s.toCharArray()) {
            // If characters match, increment the pattern pointer
            if (i < pattern.length() && c == pattern.charAt(i)) {
                i++;
            }
            // If we encounter an uppercase character that doesn't match the pattern, return
            // false
            else if (Character.isUpperCase(c)) {
                return false;
            }
        }

        // If we have matched all the characters in the pattern, return true
        return i == pattern.length();
    }
}
