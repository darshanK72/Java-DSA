/**
 * Problem Statement:
 * 
 *     Given a string `s`, determine if it is a valid palindrome by:
 *     - Ignoring all non-alphanumeric characters (e.g., punctuation, spaces).
 *     - Considering uppercase and lowercase characters as the same (case-insensitive).
 * 
 * Input:
 *     - A string `s` with a length of n (1 <= s.length <= 2 * 10^5).
 * 
 * Output:
 *     - A boolean value: `true` if the string is a valid palindrome after processing the non-alphanumeric characters, otherwise `false`.
 * 
 * Example:
 *     Input: "A man, a plan, a canal: Panama"
 *     Output: true
 * 
 *     Explanation: After ignoring the non-alphanumeric characters, the string becomes "amanaplanacanalpanama", which is a palindrome.
 */

import java.util.Scanner;

public class j06ValidPalindrome1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Read the input string
        System.out.println(isPalindrome(s)); // Output whether the string is a valid palindrome
        in.close();
    }

    /**
     * Approach:
     * 
     * The idea is to check whether the string is a valid palindrome by:
     * 1. Ignoring non-alphanumeric characters.
     * 2. Treating uppercase and lowercase characters as equal.
     * 
     * We use two pointers: one starting from the beginning and the other from the end of the string.
     * - If either pointer points to a non-alphanumeric character, we move it to the next valid character.
     * - If the characters at both pointers match, we move both pointers inward.
     * - If the characters don't match, return false.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We perform a single pass through the string with two pointers.
     * 
     * Space Complexity:
     * - O(1), since no extra space is used other than the two pointers.
     * 
     * @param str The input string.
     * @return True if the string is a valid palindrome, false otherwise.
     */
    public static boolean isPalindrome(String str) {
        str = str.toLowerCase(); // Convert the string to lowercase to ensure case insensitivity
        int s = 0; // Start pointer
        int e = str.length() - 1; // End pointer

        // Loop until the pointers meet
        while (s < e) {
            char c1 = str.charAt(s);
            char c2 = str.charAt(e);

            // Skip non-alphanumeric characters
            if (!isAlphaNumeric(c1)) {
                s++; // Move start pointer forward
                continue;
            }
            if (!isAlphaNumeric(c2)) {
                e--; // Move end pointer backward
                continue;
            }

            // If characters don't match, return false
            if (c1 != c2) {
                return false;
            } else {
                s++; // Move start pointer forward
                e--; // Move end pointer backward
            }
        }
        return true; // Return true if the string is a valid palindrome
    }

    /**
     * Helper Function: Check if a character is alphanumeric.
     * 
     * Intuition:
     * - This function checks if a given character is alphanumeric (either a letter or a digit).
     * 
     * Time Complexity:
     * - O(1), since checking the character range is a constant time operation.
     * 
     * @param c The character to check.
     * @return True if the character is alphanumeric, false otherwise.
     */
    public static boolean isAlphaNumeric(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    /**
     * Helper Function: Check if a string is a palindrome using recursion.
     * 
     * Intuition:
     * - This function is a recursive version of checking if a substring is a palindrome. It ensures that the characters at both ends of the string match.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string.
     * 
     * @param str The input string.
     * @param s The starting index of the substring.
     * @param e The ending index of the substring.
     * @return True if the substring is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String str, int s, int e) {
        if (s >= e) {
            return true; // Base case: If start pointer is greater than or equal to end pointer, it's a
                         // valid palindrome
        }
        return (str.charAt(s) == str.charAt(e)) && isPalindrome(str, ++s, --e); // Check characters at both pointers and
                                                                                // recurse
    }
}
