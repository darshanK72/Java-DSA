/**
 * Problem Statement:
 * 
 *     Given a pattern string and a string s, return true if s follows the same pattern.
 *     Here, each character in the pattern represents a word in the string s. The words in s
 *     must match the pattern such that:
 *     1. Each character in the pattern maps to a unique word in the string.
 *     2. Each word in the string maps to a unique character in the pattern.
 * 
 * Input:
 *     - A string `pattern` consisting of lowercase letters.
 *     - A string `s` consisting of words separated by spaces.
 * 
 * Output:
 *     - Return true if the string s follows the pattern, otherwise return false.
 * 
 * Example:
 * Input:
 *     "abba"
 *     "dog cat cat dog"
 * Output:
 *     true
 * 
 * Explanation:
 *     - The pattern "abba" matches the string "dog cat cat dog" because each character in the pattern
 *       corresponds to a unique word in the string. 'a' maps to "dog" and 'b' maps to "cat".
 */

import java.util.HashMap;
import java.util.Scanner;

public class j13WordPatternI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine(); // Input: the pattern string
        String s2 = in.nextLine(); // Input: the string s
        System.out.println(wordPattern(s1, s2)); // Output the result
        in.close();
    }

    /**
     * Approach 1: HashMap Based Solution
     * 
     * Intuition:
     * - We can solve this problem by maintaining two maps:
     *     1. A map from character to word (for the pattern to words mapping).
     *     2. A map from word to a boolean value (to ensure no two characters map to the same word).
     * - As we traverse the pattern and the corresponding words in the string, we check if there is any
     *   conflict in the mapping.
     * - If we encounter any conflict, we return false, otherwise we return true.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the pattern or the number of words in the string.
     * 
     * Space Complexity:
     * - O(n), for the space used by the maps (mapping characters to words and vice versa).
     * 
     * @param pattern The pattern string consisting of characters.
     * @param s The string s consisting of words.
     * @return True if the pattern matches the string s, otherwise false.
     */
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" "); // Split the string into words
        if (pattern.length() != words.length) // If the number of characters does not match the number of words
            return false;

        HashMap<Character, String> map = new HashMap<>(); // Map for pattern to word mapping
        HashMap<String, Boolean> bmap = new HashMap<>(); // Map for word to boolean check (for one-to-one mapping)

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i); // Get the current character from the pattern

            // Check if the character is already mapped
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(words[i])) // If it doesn't match the word at the current index
                    return false;
            } else {
                if (bmap.containsKey(words[i])) // If the word is already mapped to another character
                    return false;
                else {
                    map.put(ch, words[i]); // Map the character to the word
                    bmap.put(words[i], true); // Mark the word as used
                }
            }
        }
        return true;
    }
}
