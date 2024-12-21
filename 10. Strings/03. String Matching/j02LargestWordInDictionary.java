/**
 * Problem Statement:
 * 
 *     Given a string `s` and a list of words `d`, return the longest word in `d` that is a subsequence of `s`. 
 *     If there are multiple possible answers, return the lexicographically smallest one.
 * 
 * Input:
 *     - A string `s` of length `m` (1 <= m <= 1000), where each character is a lowercase English letter.
 *     - An integer `n` (1 <= n <= 100) representing the number of words in the dictionary.
 *     - A list `d` of `n` strings where each string is a lowercase English word of length at most 100.
 * 
 * Output:
 *     - A string representing the longest word in `d` that is a subsequence of `s`. If there are multiple, return the lexicographically smallest one.
 * 
 * Example:
 *     Input:
 *     "abpcplea"
 *     4
 *     ["ale", "apple", "monkey", "plea"]
 *     Output:
 *     "apple"
 * 
 *     Explanation:
 *     "apple" is the longest word in the dictionary that can be formed by deleting some characters from "abpcplea".
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j02LargestWordInDictionary {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input string s
        int n = in.nextInt(); // Input number of words in dictionary
        ArrayList<String> d = new ArrayList<>();

        // Reading dictionary words
        for (int i = 0; i < n; i++) {
            d.add(in.next()); // Input each word in dictionary
        }

        System.out.println(findLongestWord(s, d)); // Output the longest subsequence word
        in.close();
    }

    /**
     * Approach 1: Brute Force with Subsequence Check
     * 
     * Intuition:
     * - The problem can be approached by checking each word in the dictionary to see if it is a subsequence of `s`. 
     * - We maintain a variable `output` to store the longest subsequence word found so far.
     * - For each word `w` in the dictionary, if `w` is a subsequence of `s`, we compare its length with the current `output`. 
     * - If `w` is longer, we update `output`. If the lengths are the same, we compare lexicographically to keep the smallest word.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of words in the dictionary, and `m` is the average length of the words. 
     * - For each word, we check if it is a subsequence of `s` in O(m) time.
     * 
     * Space Complexity:
     * - O(n), for storing the list of words.
     * 
     * @param S The input string `s`.
     * @param d The list of dictionary words.
     * @return The longest subsequence word or the lexicographically smallest one.
     */
    public static String findLongestWord(String S, List<String> d) {
        String output = "";

        // Iterate over each word in the dictionary
        for (String s : d) {
            // Check if the word is a subsequence of S
            if (isSubsequence(s, S)) {
                // Update output based on length and lexicographical order
                if (s.length() > output.length() || (s.length() == output.length() && s.compareTo(output) < 0)) {
                    output = s;
                }
            }
        }

        return output; // Return the longest or lexicographically smallest subsequence word
    }

    /**
     * Approach 2: Two Pointer Subsequence Check
     * 
     * Intuition:
     * - To check if one string is a subsequence of another, we can use two pointers.
     * - One pointer iterates over the characters of the word `s1`, and the other pointer iterates over `s2`.
     * - Whenever characters match, we move the pointer for `s1`. If all characters of `s1` are matched, `s1` is a subsequence of `s2`.
     * 
     * Time Complexity:
     * - O(m + n), where `m` is the length of `s1` and `n` is the length of `s2`.
     * 
     * Space Complexity:
     * - O(1), since only two pointers are used.
     * 
     * @param s1 The string we are checking as a subsequence.
     * @param s2 The string in which we are checking the subsequence.
     * @return A boolean indicating whether `s1` is a subsequence of `s2`.
     */
    public static boolean isSubsequence(String s1, String s2) {
        int i = 0;
        int j = 0;

        // Traverse both strings
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++; // Move pointer for s1 if characters match
            }
            j++; // Always move pointer for s2
        }

        return i == s1.length(); // If all characters in s1 are matched, return true
    }
}
