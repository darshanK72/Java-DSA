/**
 * Problem Statement:
 * 
 *     Given two strings `s1` and `s2`, return `true` if `s2` is an anagram of `s1`. Otherwise, return `false`.
 *     An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.
 * 
 * Input:
 *     - Two strings `s1` and `s2` where each string has length at most 10^5.
 * 
 * Output:
 *     - A boolean value indicating whether `s2` is an anagram of `s1`.
 * 
 * Example:
 *     Input:
 *     "anagram"
 *     "nagaram"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     "nagaram" is an anagram of "anagram" since it contains the same characters with the same frequency.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j04ValidAnagram {
    public static void main(String args[]) {
        // Reading input strings
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();

        // Checking if s2 is an anagram of s1 using both methods
        System.out.println(isAnagram(s1, s2)); // Approach 1
        System.out.println(isAnagramHashMap(s1, s2)); // Approach 2

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Using Character Frequency Arrays
     * 
     * Intuition:
     * - The key observation here is that two strings are anagrams if they have the same characters with the same frequency.
     * - We can use two frequency arrays to count the occurrences of each character in both strings.
     * - If the frequency arrays for both strings are identical, then the strings are anagrams.
     * 
     * Time Complexity:
     * - O(s1.length + s2.length), where `s1.length` and `s2.length` are the lengths of the input strings.
     * - We traverse both strings once to populate the frequency arrays and then compare the arrays.
     * 
     * Space Complexity:
     * - O(1), since the frequency arrays always have a fixed size of 26 (one for each lowercase letter).
     * 
     * @param s1 The first string.
     * @param s2 The second string.
     * @return true if `s2` is an anagram of `s1`, false otherwise.
     */
    public static boolean isAnagram(String s1, String s2) {
        // If lengths don't match, they cannot be anagrams
        if (s1.length() != s2.length())
            return false;

        // Arrays to count frequency of characters in both strings
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];

        // Count frequency of characters in s1
        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
        }

        // Count frequency of characters in s2
        for (int i = 0; i < s2.length(); i++) {
            hash2[s2.charAt(i) - 'a']++;
        }

        // Compare the frequency arrays
        for (int i = 0; i < 26; i++) {
            if (hash1[i] != hash2[i])
                return false;
        }

        return true; // Strings are anagrams
    }

    /**
     * Approach 2: Using a HashMap
     * 
     * Intuition:
     * - Another way to count the frequency of characters in the strings is by using a `HashMap`.
     * - For each character in `s1`, we increment its count in the map, and for each character in `s2`, we decrement its count.
     * - If the map ends up empty, meaning all characters have been matched and their counts balanced, then `s2` is an anagram of `s1`.
     * 
     * Time Complexity:
     * - O(s1.length + s2.length), as we traverse both strings once to update the map.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of unique characters in the input strings. In the worst case, this would be O(26) for lowercase English letters.
     * 
     * @param s1 The first string.
     * @param s2 The second string.
     * @return true if `s2` is an anagram of `s1`, false otherwise.
     */
    public static boolean isAnagramHashMap(String s1, String s2) {
        // If lengths don't match, they cannot be anagrams
        if (s1.length() != s2.length())
            return false;

        // HashMap to store frequency of characters in s1
        HashMap<Character, Integer> map = new HashMap<>();

        // Increment frequency for characters in s1
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Decrement frequency for characters in s2
        for (char c : s2.toCharArray()) {
            if (!map.containsKey(c))
                return false; // Character in s2 not found in s1
            if (map.get(c) == 1)
                map.remove(c); // Remove character from map if count reaches 0
            else
                map.put(c, map.get(c) - 1);
        }

        // If map is empty, the strings are anagrams
        return map.size() == 0;
    }
}
