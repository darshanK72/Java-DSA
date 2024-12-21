/**
 * Problem Statement:
 * 
 *     Given two strings `s1` and `s2`, return `true` if `s1` is a subsequence of `s2`, otherwise return `false`.
 *     A subsequence of a string is a new string that is formed by deleting some (or no) characters from the original string without changing the relative order of the remaining characters.
 * 
 * Input:
 *     - A string `s1` of length `m` (1 <= m <= 100) and a string `s2` of length `n` (1 <= n <= 100), where each string consists of lowercase English letters.
 * 
 * Output:
 *     - A boolean value `true` if `s1` is a subsequence of `s2`, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     "abc"
 *     "ahbgdc"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     "abc" is a subsequence of "ahbgdc" because we can form "abc" by deleting the letters "h", "g", and "d" from "ahbgdc".
 */

import java.util.Scanner;

public class j01IsSubsequence {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine(); // Input: string s1
        String s2 = in.nextLine(); // Input: string s2
        System.out.println(isSubsequence(s1, s2)); // Output the result of subsequence check
        in.close();
    }

    /**
     * Approach: Two Pointer Approach
     * 
     * Intuition:
     * - We can solve this problem by using two pointers to iterate through both strings. 
     * - We initialize two pointers `i` and `j` starting at 0, representing the current characters of `s1` and `s2` respectively.
     * - We iterate through string `s2` and whenever a character in `s1` matches the character in `s2`, we move the pointer `i` to the next character of `s1`.
     * - If we are able to iterate through all characters of `s1` (i.e., `i` reaches the length of `s1`), we return `true`, indicating that `s1` is a subsequence of `s2`.
     * - If we finish iterating through `s2` and haven't matched all characters of `s1`, we return `false`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of string `s2`. We only traverse `s2` once and the comparison between characters is O(1).
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of extra space for the two pointers.
     * 
     * @param s1 The first input string.
     * @param s2 The second input string.
     * @return A boolean indicating if `s1` is a subsequence of `s2`.
     */
    public static boolean isSubsequence(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++; // Move pointer for s1 when a match is found
            }
            j++; // Always move pointer for s2
        }
        return i == s1.length(); // If all characters in s1 are matched, return true
    }
}
