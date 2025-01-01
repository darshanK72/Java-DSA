/**
 * Problem Statement:
 * 
 *     Given a string `s`, generate all possible subsequences (non-empty) of the string and print them in lexicographically sorted order.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 15), consisting of lowercase English characters.
 * 
 * Output:
 *     - An array of strings, where each string is a subsequence of `s`, and all subsequences are sorted lexicographically.
 * 
 * Example:
 *     Input:
 *         abc
 *     Output:
 *         [a, ab, abc, ac, b, bc, c]
 * 
 *     Explanation:
 *         The subsequences of the string "abc" are "a", "ab", "abc", "ac", "b", "bc", and "c", sorted in lexicographical order.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class j13PowerSet {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.next(); // Input: the string s
        System.out.println(getAllPossibleSubsequences(s)); // Call to get all subsequences and print the result
        in.close();
    }

    /**
     * Approach : Generating All Subsequences Using Bit Manipulation
     * 
     * Intuition:
     * - The problem can be solved by considering all possible combinations of characters from the string.
     * - Each subsequence can be represented by a binary number where each bit indicates whether a character is included or not.
     * - We iterate over all possible binary numbers from 1 to (2^n - 1) where `n` is the length of the string.
     * 
     * Time Complexity:
     * - O(2^n * n), where `n` is the length of the string. We generate 2^n subsequences and each subsequence can have at most `n` characters.
     * 
     * Space Complexity:
     * - O(2^n), as we store all the subsequences in an array list.
     * 
     * @param s The input string.
     * @return A list containing all possible subsequences, sorted lexicographically.
     */
    public static ArrayList<String> getAllPossibleSubsequences(String s) {
        int n = s.length();
        ArrayList<String> output = new ArrayList<String>();
        // Generate all subsequences using bit manipulation
        for (int i = 1; i < Math.pow(2, n); i++) {
            StringBuilder res = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    res.append(s.charAt(j));
                }
            }
            output.add(res.toString());
        }
        Collections.sort(output); // Sorting the subsequences in lexicographical order
        return output;
    }
}
