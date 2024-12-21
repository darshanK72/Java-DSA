/**
 * Problem Statement:
 * 
 *     Given a string `s`, determine if it can be a palindrome by deleting at most one character.
 *     A palindrome is a string that reads the same forward and backward.
 * 
 * Input:
 *     - A string `s` of length `n` (1 <= s.length <= 1000).
 * 
 * Output:
 *     - A boolean value: `true` if the string can be a palindrome after deleting at most one character, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     "abca"
 *     Output:
 *     true
 * 
 *     Explanation: The string "abca" can become a palindrome by removing the character 'b' or 'c'.
 */

import java.util.Scanner;

public class j07ValidPalindrome2 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Read the input string
        System.out.println(validPalindrome(s)); // Output the result of the check
        in.close();
    }

    /**
     * Approach:
     * 
     * The idea is to use a two-pointer approach to check if the string can become a palindrome
     * by removing at most one character.
     * 
     * - We start with two pointers, one at the beginning (`s`) and one at the end (`e`) of the string.
     * - If the characters at both pointers match, we move both pointers inward.
     * - If they don't match, we check whether either the substring obtained by removing the character at `s`
     *   or the substring obtained by removing the character at `e` is a palindrome.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We traverse the string once and check palindromes in O(n) time.
     * 
     * Space Complexity:
     * - O(1), since no additional space (other than the two pointers) is used.
     * 
     * @param string The input string.
     * @return True if the string can become a palindrome by deleting at most one character.
     */
    public static boolean validPalindrome(String string) {
        char[] str = string.toCharArray(); // Convert the string to a character array
        int s = 0; // Start pointer
        int e = string.length() - 1; // End pointer

        // Loop until the pointers meet
        while (s < e) {
            // If characters don't match, check if removing one character makes the
            // substring a palindrome
            if (str[s] != str[e]) {
                return isPalindrome(str, s + 1, e) || isPalindrome(str, s, e - 1);
            }
            s++; // Move start pointer forward
            e--; // Move end pointer backward
        }
        return true; // Return true if the string is already a palindrome
    }

    /**
     * Helper Function: Check if a substring is a palindrome.
     * 
     * Intuition:
     * - This function checks if the substring from index `s` to `e` is a palindrome.
     * - It uses a simple two-pointer approach to compare the characters at both ends of the substring.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the substring. We check each character once.
     * 
     * @param str The input character array.
     * @param s The starting index of the substring.
     * @param e The ending index of the substring.
     * @return True if the substring is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(char[] str, int s, int e) {
        while (s < e) {
            if (str[s] != str[e]) {
                return false; // If characters don't match, return false
            }
            s++; // Move start pointer forward
            e--; // Move end pointer backward
        }
        return true; // Return true if the substring is a palindrome
    }
}
