/**
 * Problem Statement:
 * 
 *     Two strings `s1` and `s2` are said to be K-anagrams if, by changing at most `k` characters in `s1`, 
 *     we can get `s2`. We are given two strings and an integer `k`. The task is to determine if the two strings 
 *     are K-anagrams of each other or not.
 * 
 * Input:
 *     - Two strings `s1` and `s2` (1 <= s1.length, s2.length <= 1000), containing lowercase English letters.
 *     - An integer `k` (0 <= k <= s1.length), representing the maximum allowed changes to make the strings K-anagrams.
 * 
 * Output:
 *     - Return `true` if the two strings are K-anagrams, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     "abcde"
 *     "edcba"
 *     0
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The strings can be rearranged without any changes to be equal, so they are K-anagrams with k=0.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j06KAnagrams {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        int k = in.nextInt();
        System.out.println(areKAnagrams(s1, s2, k)); // Call first approach
        System.out.println(areKAnagramsHashMap(s1, s2, k)); // Call second approach
        in.close();
    }

    /**
     * Approach 1: Using Character Frequency Arrays
     * 
     * Intuition:
     * - We compare the frequency of characters in both strings using two arrays of size 26 (for each letter of the alphabet).
     * - The number of changes required to convert one string into the other is the difference in the frequencies of the characters.
     * - If the sum of required changes is less than or equal to `k`, the strings are K-anagrams.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the strings, because we are iterating over the strings to build frequency arrays.
     * 
     * Space Complexity:
     * - O(1), since the size of the frequency arrays is fixed at 26.
     * 
     * @param s1 The first string.
     * @param s2 The second string.
     * @param k The maximum allowed number of character changes.
     * @return true if the strings are K-anagrams, false otherwise.
     */
    public static boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int hash1[] = new int[26]; // Frequency array for s1
        int hash2[] = new int[26]; // Frequency array for s2

        // Count character frequencies for both strings
        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
            hash2[s2.charAt(i) - 'a']++;
        }

        // Count the number of changes needed
        int changes = 0;
        for (int i = 0; i < 26; i++) {
            if (hash1[i] > hash2[i]) {
                changes += hash1[i] - hash2[i];
            }
        }

        // If the changes needed are less than or equal to k, return true
        return changes <= k;
    }

    /**
     * Approach 2: Using HashMap
     * 
     * Intuition:
     * - Use a HashMap to store the frequency of characters in `s1`.
     * - For each character in `s2`, decrease the frequency in the map. If the frequency is zero, remove the character.
     * - After processing both strings, the remaining values in the map will give the count of characters that need to be changed.
     * - If the sum of these remaining changes is less than or equal to `k`, the strings are K-anagrams.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the strings, since we are iterating over the strings twice.
     * 
     * Space Complexity:
     * - O(1), since the size of the map is limited to 26 characters (the lowercase English alphabet).
     * 
     * @param s1 The first string.
     * @param s2 The second string.
     * @param k The maximum allowed number of character changes.
     * @return true if the strings are K-anagrams, false otherwise.
     */
    public static boolean areKAnagramsHashMap(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        // Create a frequency map for the characters in s1
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Update the map by reducing frequencies based on s2
        for (char c : s2.toCharArray()) {
            if (map.getOrDefault(c, 0) > 0) {
                map.put(c, map.getOrDefault(c, 0) - 1);
            }
        }

        // Count the number of unmatched characters
        int count = 0;
        for (char key : map.keySet()) {
            count += map.get(key);
        }

        // If the total count of unmatched characters is less than or equal to k, return
        // true
        return count <= k;
    }
}
