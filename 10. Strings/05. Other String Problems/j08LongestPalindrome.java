/**
 * Problem Statement:
 * 
 *     Given a string `s`, find the longest palindromic substring in `s`. A palindromic string is one that reads the same forward and backward.
 * 
 * Input:
 *     - A string `s` of length `n` (1 <= n <= 10^3), where each character in `s` satisfies (1 <= s[i] <= 10^5).
 * 
 * Output:
 *     - The longest palindromic substring in `s`.
 * 
 * Example:
 *     Input:
 *     "babad"
 *     Output:
 *     "bab" (or "aba")
 * 
 *     Explanation:
 *     The longest palindromic substring is "bab" or "aba". Both are valid answers.
 */

import java.util.Scanner;

public class j08LongestPalindrome {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String str = in.nextLine(); // Input: the string to find the longest palindrome
        System.out.println(longestPalindrome(str)); // Output for Approach 1
        System.out.println(longestPalindromeEfficient(str)); // Output for Approach 2
        System.out.println(longestPalindromeEfficientExpandFromCenter(str)); // Output for Approach 3
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - We can iterate over all possible substrings of the given string and check whether each one is a palindrome.
     * - If the substring is a palindrome and is longer than the previously found palindrome, update the result.
     * - This approach checks each substring individually for being a palindrome, leading to a time complexity of O(n^3).
     * 
     * Time Complexity:
     * - O(n^3) (due to nested loops and palindrome checking).
     * 
     * Space Complexity:
     * - O(n) (used to store substrings).
     * 
     * @param s The input string.
     * @return The longest palindromic substring.
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 1)
            return s;
        String out = "";
        for (int i = 0; i < s.length(); i++) {
            StringBuilder substr = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                substr.append(s.charAt(j));
                if (isPalindrome(substr.toString()) && substr.length() > out.length()) {
                    out = substr.toString();
                }
            }
        }
        return out;
    }

    /**
     * Approach 2: Efficient Approach with Substring Optimization
     * 
     * Intuition:
     * - This approach improves upon the brute force approach by keeping track of the longest palindrome found so far.
     * - Instead of checking all substrings, we only check those that are longer than the current longest palindrome.
     * - This leads to a reduction in the number of unnecessary checks, improving the performance compared to the brute force approach.
     * 
     * Time Complexity:
     * - O(n^2) (nested loop to check substrings and palindrome checking).
     * 
     * Space Complexity:
     * - O(n) (used to store the longest palindrome).
     * 
     * @param s The input string.
     * @return The longest palindromic substring.
     */
    public static String longestPalindromeEfficient(String s) {
        if (s.length() == 1)
            return s;
        int maxLen = 1;
        String out = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + maxLen; j <= s.length(); j++) {
                if (j - i > maxLen && isPalindrome(s.substring(i, j))) {
                    maxLen = j - i;
                    out = s.substring(i, j);
                }
            }
        }
        return out;
    }

    /**
     * Approach 3: Expand Around Center (Optimal Approach)
     * 
     * Intuition:
     * - The optimal solution for this problem is to expand around each possible center.
     * - A palindrome can be expanded from its center, which is either a single character (for odd length palindromes) or two characters (for even length palindromes).
     * - For each index, we attempt to expand the palindrome in both directions (left and right) and update the result if we find a longer palindrome.
     * - This approach minimizes unnecessary checks and provides a more efficient solution.
     * 
     * Time Complexity:
     * - O(n^2) (due to expansion around each center).
     * 
     * Space Complexity:
     * - O(1) (only a few variables for the current palindrome and result).
     * 
     * @param s The input string.
     * @return The longest palindromic substring.
     */
    public static String longestPalindromeEfficientExpandFromCenter(String s) {
        if (s.length() == 1)
            return s;
        String maxStr = "";
        for (int i = 0; i < s.length() - 1; i++) {
            String odd = expandFromCenter(s, i, i); // Odd length palindrome
            String even = expandFromCenter(s, i, i + 1); // Even length palindrome

            if (odd.length() > maxStr.length()) {
                maxStr = odd;
            }
            if (even.length() > maxStr.length()) {
                maxStr = even;
            }
        }
        return maxStr;
    }

    /**
     * Helper Method: Expand Around Center
     * 
     * This method expands around a given center and returns the longest palindrome substring.
     * 
     * @param str The input string.
     * @param s The start index.
     * @param e The end index.
     * @return The longest palindromic substring found by expanding around the center.
     */
    public static String expandFromCenter(String str, int s, int e) {
        while ((s >= 0 && e < str.length()) && str.charAt(s) == str.charAt(e)) {
            s--;
            e++;
        }
        return str.substring(s + 1, e);
    }

    /**
     * Helper Method: Check if a String is a Palindrome
     * 
     * This method checks if a given string is a palindrome by comparing characters from both ends.
     * 
     * @param str The input string to check.
     * @return True if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String str) {
        int s = 0;
        int e = str.length() - 1;
        while (s < e) {
            if (str.charAt(s) != str.charAt(e))
                return false;
            s++;
            e--;
        }
        return true;
    }
}
