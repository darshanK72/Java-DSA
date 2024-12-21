/**
 * Problem Statement:
 * 
 *     Given a string `s`, the task is to calculate the sum of the beauty of all substrings of `s`. 
 *     The beauty of a substring is defined as the difference between the maximum frequency and the minimum frequency 
 *     of characters in the substring.
 * 
 *     For example, for the string "aab", its substrings are: "a", "a", "b", "aa", "ab", "aab".
 *     The beauty of each of these substrings is calculated and then summed up.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 100) consisting of lowercase English letters.
 * 
 * Output:
 *     - An integer representing the sum of beauties of all substrings of `s`.
 * 
 * Example:
 *     Input:
 *     "aab"
 *     Output:
 *     8
 * 
 *     Explanation:
 *     - The substrings are: "a", "a", "b", "aa", "ab", "aab".
 *     - Their beauty values are: 0, 0, 0, 0, 1, 1.
 *     - The sum is: 0 + 0 + 0 + 0 + 1 + 1 = 8.
 */

import java.util.Scanner;

public class j23SumOfButyOfSubstring {

    public static void main(String args[]) {
        // Reading input string
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        // Calling the function to calculate the sum of beauty of substrings
        System.out.println(beautySum(str));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach: Brute Force with Frequency Calculation
     * 
     * Intuition:
     * - We need to find all substrings of the given string `s`. For each substring, we calculate its beauty.
     * - The beauty of a substring is the difference between the maximum and minimum frequencies of characters.
     * - We iterate over all possible substrings, calculate their beauty, and sum them up.
     * 
     * Time Complexity:
     * - O(n^3), where n is the length of the string. 
     * - The outer loops take O(n^2) to generate all substrings, and for each substring, calculating the beauty takes O(n).
     * 
     * Space Complexity:
     * - O(1), as we are only using a fixed-size array to track character frequencies.
     * 
     * @param s The input string.
     * @return The sum of beauties of all substrings of the string.
     */
    public static int beautySum(String s) {
        int ans = 0;

        // Generating all substrings of the string s
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                // Calculate the beauty of the current substring and add it to the result
                ans += getBeauty(s.substring(i, j));
            }
        }

        return ans;
    }

    /**
     * Helper function to calculate the beauty of a substring.
     * 
     * Intuition:
     * - For each substring, we need to count the frequency of each character, 
     *   and then compute the maximum and minimum frequencies.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the substring. We traverse the substring once and calculate character frequencies.
     * 
     * Space Complexity:
     * - O(1), as we use a fixed-size array to store frequencies (only 26 letters).
     * 
     * @param s The substring for which we want to calculate the beauty.
     * @return The beauty of the substring (max frequency - min frequency).
     */
    public static int getBeauty(String s) {
        // Array to store the frequency of each character (26 lowercase English letters)
        int[] hash = new int[26];

        // Calculate the frequency of each character in the substring
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
        }

        // Find the maximum and minimum frequency
        int min = Integer.MAX_VALUE;
        int max = 0;

        // Traverse the frequency array to find min and max frequencies
        for (int i = 0; i < 26; i++) {
            if (hash[i] > 0) {
                if (hash[i] > max) {
                    max = hash[i];
                }
                if (hash[i] < min) {
                    min = hash[i];
                }
            }
        }

        // Return the beauty of the substring (max - min)
        return max - min;
    }
}
