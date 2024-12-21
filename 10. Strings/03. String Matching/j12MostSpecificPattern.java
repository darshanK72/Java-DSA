/**
 * Problem Statement:
 * 
 *     Given a list of words and a pattern string, find all the words in the list that match the given pattern.
 *     A word matches the pattern if there is a one-to-one mapping between each character of the word and the pattern, such that:
 *     1. Every character in the pattern is mapped to exactly one character in the word.
 *     2. Every character in the word is mapped to exactly one character in the pattern.
 *     3. The order of characters in the word and pattern should be preserved.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number of words in the dictionary.
 *     - An array `dict` of size `n` where each element is a string, representing the dictionary of words.
 *     - A string `pattern` representing the pattern to match.
 * 
 * Output:
 *     - A list of words from `dict` that match the given `pattern`.
 * 
 * Example:
 * Input:
 *     5
 *     abc def ghi xyz
 *     abb
 * Output:
 *     [xyz, def]
 * 
 * Explanation:
 *     - "xyz" and "def" both match the pattern "abb" because there is a one-to-one mapping between characters in the pattern and characters in these words.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j12MostSpecificPattern {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        ArrayList<String> dict = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dict.add(in.next()); // Input: elements of the array
        }
        String pattern = in.next();
        System.out.println(findMatchedWords(n, dict, pattern));
        System.out.println(findMatchedWordsEfficient(n, dict, pattern));
        in.close();
    }

    /**
     * Approach 1: Brute Force Method
     * 
     * Intuition:
     * - In this approach, we iterate through all the words in the dictionary and check if they match the pattern.
     * - To check if two strings are isomorphic, we compare their characters using two maps to ensure a one-to-one mapping.
     * - The time complexity is O(n * m), where n is the number of words and m is the length of the word/pattern.
     * 
     * Time Complexity:
     * - O(n * m), where n is the number of words and m is the length of each word/pattern.
     * 
     * Space Complexity:
     * - O(m), where m is the length of the pattern/word (for the space used by the maps).
     * 
     * @param n The number of words in the dictionary.
     * @param dict The list of words in the dictionary.
     * @param pattern The pattern to match.
     * @return A list of words from the dictionary that match the pattern.
     */
    public static ArrayList<String> findMatchedWords(int n, ArrayList<String> dict, String pattern) {
        ArrayList<String> out = new ArrayList<>();
        for (String s : dict) {
            if (isIsomorphic(s, pattern))
                out.add(s);
        }
        return out;
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Character> cmap = new HashMap<>();
        HashMap<Character, Boolean> bmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if (cmap.containsKey(ch1)) {
                if (cmap.get(ch1) != ch2)
                    return false;
            } else {
                if (bmap.containsKey(ch2))
                    return false;
                else {
                    cmap.put(ch1, ch2);
                    bmap.put(ch2, true);
                }
            }
        }
        return true;
    }

    /**
     * Approach 2: Optimized Encoding Method
     * 
     * Intuition:
     * - In this approach, we map each character in the word/pattern to its last occurrence index.
     * - By encoding the pattern and comparing the encoded string of each word, we can quickly identify if a word matches the pattern without using character mapping for every comparison.
     * - This reduces the time complexity to O(n * m), where n is the number of words and m is the length of each word/pattern, but is much faster in practice due to direct string comparison.
     * 
     * Time Complexity:
     * - O(n * m), where n is the number of words and m is the length of the word/pattern.
     * 
     * Space Complexity:
     * - O(m), where m is the length of the word/pattern (for the space used by the encoded string).
     * 
     * @param n The number of words in the dictionary.
     * @param dict The list of words in the dictionary.
     * @param pattern The pattern to match.
     * @return A list of words from the dictionary that match the pattern.
     */
    public static ArrayList<String> findMatchedWordsEfficient(int n, ArrayList<String> dict, String pattern) {
        ArrayList<String> out = new ArrayList<>();
        int[] lastIndex = new int[256];
        String encodedPattern = encodeString(pattern, lastIndex);

        for (String word : dict) {
            if (word.length() == pattern.length() && encodeString(word, lastIndex).equals(encodedPattern)) {
                out.add(word);
            }
        }
        return out;
    }

    private static String encodeString(String str, int[] lastIndex) {
        Arrays.fill(lastIndex, -1);
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (lastIndex[c] == -1) {
                lastIndex[c] = i;
            }
            encoded.append(lastIndex[c]);
        }
        return encoded.toString();
    }
}
