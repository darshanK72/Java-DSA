/**
 * Problem Statement:
 * 
 *     Given a list of words and a pattern string, return all the words that match the pattern.
 *     A word matches the pattern if there is a one-to-one mapping between each character in the word and each character in the pattern, 
 *     such that each character in the pattern corresponds to exactly one character in the word, and vice versa.
 * 
 * Input:
 *     - An array of strings `words[]` where each string consists of lowercase English letters.
 *     - A string `pattern` (1 <= words.length, pattern.length <= 100).
 * 
 * Output:
 *     - A list of strings from the input `words[]` that match the pattern.
 * 
 * Example:
 *     Input:
 *     ["abc", "deq", "mee", "aqq", "dkd", "ccc"]
 *     "abb"
 *     Output:
 *     ["mee", "aqq"]
 * 
 *     Explanation:
 *     - "mee" and "aqq" match the pattern "abb", where 'm' maps to 'a', 'e' maps to 'b', and 'e' maps to 'b' again.
 *     - Other words do not match the pattern due to differing character mappings.
 */

import java.util.ArrayList;
import java.util.List;

public class j11FindAndReplacePattern {
    public static void main(String args[]) {
        String[] words = new String[] { "abc", "deq", "mee", "aqq", "dkd", "ccc" };
        String pattern = "abb";
        System.out.println(findAndReplacePattern(words, pattern));
    }

    /**
     * Approach: Find Words Matching the Pattern
     * 
     * Intuition:
     * - We iterate through each word in the input `words[]` and check if it is isomorphic to the `pattern`.
     * - For this, we use the helper function `isIsomorphic`, which checks if two strings have a valid one-to-one character mapping.
     * - If the word matches the pattern, we add it to the result list.
     * 
     * Time Complexity:
     * - O(n * k), where n is the number of words and k is the average length of the words. For each word, we check if it is isomorphic to the pattern.
     * 
     * Space Complexity:
     * - O(k), for storing the hash mapping in `mapToHash`.
     * 
     * @param words The list of input words.
     * @param pattern The pattern string.
     * @return List of words that match the pattern.
     */
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        ArrayList<String> out = new ArrayList<>();
        for (String s : words) {
            if (isIsomorphic(s, pattern)) {
                out.add(s);
            }
        }
        return out;
    }

    /**
     * Helper Function: isIsomorphic
     * 
     * Intuition:
     * - This function checks if two strings `s` and `t` are isomorphic, meaning that there exists a valid one-to-one character mapping between them.
     * - We use a hash array to track the mapping of characters from `s` to `t`.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string.
     * 
     * Space Complexity:
     * - O(1) as the size of the hash array is constant (256 entries for ASCII characters).
     * 
     * @param s The first string.
     * @param t The second string.
     * @return true if the strings are isomorphic, false otherwise.
     */
    public static boolean isIsomorphic(String s, String t) {
        return mapToHash(s, t) && mapToHash(t, s);
    }

    /**
     * Helper Function: mapToHash
     * 
     * Intuition:
     * - This function helps to check if there is a valid one-to-one mapping from characters in string `s` to string `t`.
     * - We maintain a hash array to track the character mappings from `s` to `t`.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string.
     * 
     * Space Complexity:
     * - O(1), as the size of the hash array is constant (256 entries for ASCII characters).
     * 
     * @param s The first string.
     * @param t The second string.
     * @return true if the mapping is valid, false otherwise.
     */
    public static boolean mapToHash(String s, String t) {
        char[] hash = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (hash[c1] != '\0' && hash[c1] != c2)
                return false;
            hash[c1] = c2;
        }
        return true;
    }
}
