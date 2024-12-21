/**
 * Problem Statement:
 * 
 *     Given a string `s` containing words and a `pattern` string, determine if the string s follows the same pattern
 *     as the given pattern. The words in the string are separated by spaces, and each letter in the pattern corresponds
 *     to a specific word in the string.
 * 
 * Input:
 *     - A string `pattern` consisting of lowercase letters.
 *     - A string `s` where words are separated by spaces, and the words are made up of lowercase letters and uppercase letters.
 * 
 * Output:
 *     - Return true if the string `s` follows the pattern `pattern`, otherwise return false.
 * 
 * Example:
 * Input:
 *     "abba"
 *     "dog cat cat dog"
 * Output:
 *     true
 * 
 * Explanation:
 *     - The pattern "abba" matches the string "dog cat cat dog" because:
 *         'a' corresponds to "dog" and 'b' corresponds to "cat".
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j14WordPatternII {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine(); // Input: the pattern string
        String s2 = in.nextLine(); // Input: the string s
        System.out.println(wordPatternII(s1, s2)); // Output the result
        in.close();
    }

    /**
     * Approach 1: Word Pattern Matching with HashMaps
     * 
     * Intuition:
     * - In this problem, the string `s` contains words separated by spaces, and each character in the pattern corresponds
     *   to a specific word. We need to map each character to a word and ensure that no two characters map to the same word
     *   and vice versa.
     * - We can achieve this by iterating over both the `pattern` and the words in the string `s` using two hash maps:
     *     1. One map for the mapping from characters to words.
     *     2. Another map for checking that no two characters map to the same word.
     * - We also need to process the string `s` to extract words and match them with the characters in the pattern.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string `s` since we iterate over the string to extract words and map them.
     * 
     * Space Complexity:
     * - O(n), where `n` is the number of words in the string (the number of entries in the hash maps).
     * 
     * @param pattern The pattern string consisting of characters.
     * @param s The string `s` consisting of words.
     * @return True if the string `s` matches the pattern, otherwise false.
     */
    public static boolean wordPatternII(String s, String pattern) {
        // Extract words from the string s
        ArrayList<String> words = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j + 1 < s.length() && !Character.isUpperCase(s.charAt(j + 1))) {
                j++;
            }
            words.add(s.substring(i, j));
            i = j + 1;
        }

        // If number of words does not match the length of the pattern
        if (pattern.length() != words.size())
            return false;

        // Maps to check for the pattern and words mapping
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Boolean> bmap = new HashMap<>();

        // Check the pattern to words mapping
        for (int k = 0; k < pattern.length(); k++) {
            char ch = pattern.charAt(k);

            // If character is already mapped, check for consistency
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(words.get(k)))
                    return false;
            } else {
                // If word is already mapped, return false (duplicate word)
                if (bmap.containsKey(words.get(k)))
                    return false;
                else {
                    map.put(ch, words.get(k)); // Map the character to the word
                    bmap.put(words.get(k), true); // Mark the word as used
                }
            }
        }
        return true;
    }
}
