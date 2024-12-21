/**
 * Problem Statement:
 * 
 * Given a string `s` and a string `p`, the task is to find all starting indices of `p`'s anagrams in `s`.
 * An anagram is a word formed by rearranging the letters of another word, using all the original letters exactly once.
 * 
 * Input:
 * - A string `s` representing the main string.
 * - A string `p` representing the pattern whose anagrams we need to find in `s`.
 * 
 * Output:
 * - A list of starting indices in `s` where anagrams of `p` begin.
 * 
 * Example:
 *     Input:
 *     "cbaebabacd"
 *     "abc"
 *     Output:
 *     [0, 6]
 *     
 * Explanation:
 * The substrings with start index 0 and 6 are anagrams of "abc".
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j07FindAllAnagrams {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(findAllAnagrams(s1, s2)); // Call brute force approach
        System.out.println(findAllAnagramsHashMap(s1, s2)); // Call optimized approach using HashMap
        in.close();
    }

    /**
     * Approach 1: Brute Force (Using substring and checking each possible anagram)
     * 
     * Intuition:
     * - Iterate over every possible substring of length `p.length()` in `s` and check if it is an anagram of `p`.
     * - This is done by checking if the character frequencies of the substring match those of `p`.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the length of `s` and `m` is the length of `p`. This is because for every substring of length `m`, 
     *   we check if it is an anagram of `p`.
     * 
     * Space Complexity:
     * - O(1), since we only need two arrays of size 26 for character counting.
     * 
     * @param s The main string.
     * @param p The pattern string whose anagrams need to be found in `s`.
     * @return A list of starting indices where anagrams of `p` begin in `s`.
     */
    public static ArrayList<String> findAllAnagrams(String s, String p) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            // Ensure substring length doesn't exceed s's length
            if (i + p.length() <= s.length()) {
                String str = s.substring(i, i + p.length());
                if (isAnagram(str, p)) {
                    out.add(str);
                }
            }
        }
        return out;
    }

    /**
     * Helper function: Checks if two strings are anagrams.
     * 
     * @param s1 First string.
     * @param s2 Second string.
     * @return true if `s1` and `s2` are anagrams, false otherwise.
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            hash2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (hash1[i] != hash2[i])
                return false;
        }
        return true;
    }

    /**
     * Approach 2: Optimized using HashMap and Sliding Window
     * 
     * Intuition:
     * - Use a sliding window of size `p.length()` over `s`.
     * - Maintain a frequency map for the current window and the pattern `p`.
     * - As the window slides over `s`, update the map by adding the new character and removing the old character.
     * - If the map for the current window matches the map for `p`, it means the substring is an anagram of `p`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of `s`. We only pass over `s` once.
     * 
     * Space Complexity:
     * - O(1), since we only use HashMaps of size 26 (constant size for lowercase English letters).
     * 
     * @param s The main string.
     * @param p The pattern string whose anagrams need to be found in `s`.
     * @return A list of starting indices where anagrams of `p` begin in `s`.
     */
    public static ArrayList<String> findAllAnagramsHashMap(String s, String p) {
        // Frequency maps for pattern and sliding window
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> pmap = new HashMap<>();

        // Populate the pattern's frequency map
        for (int i = 0; i < p.length(); i++) {
            pmap.put(p.charAt(i), pmap.getOrDefault(p.charAt(i), 0) + 1);
        }

        // Initialize the frequency map for the first window in `s`
        for (int i = 0; i < p.length(); i++) {
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
        }

        ArrayList<String> out = new ArrayList<>();
        int i = p.length(); // This marks the end of the sliding window

        while (i < s.length()) {
            // Compare current window map with the pattern map
            if (compare(pmap, smap)) {
                out.add(s.substring(i - p.length(), i));
            }

            // Slide the window: add the new character at `i` and remove the character at `i
            // - p.length()`
            char toAc = s.charAt(i); // New character to add
            smap.put(toAc, smap.getOrDefault(toAc, 0) + 1);

            char toRe = s.charAt(i - p.length()); // Character to remove
            smap.put(toRe, smap.get(toRe) - 1);
            if (smap.get(toRe) == 0) {
                smap.remove(toRe);
            }

            i++;
        }

        // Check for the last window
        if (compare(pmap, smap)) {
            out.add(s.substring(i - p.length(), i));
        }

        return out;
    }

    /**
     * Helper function to compare two HashMaps.
     * 
     * @param map1 First map.
     * @param map2 Second map.
     * @return true if both maps are equal, false otherwise.
     */
    public static boolean compare(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        if (map1.size() != map2.size())
            return false;
        for (Character key : map1.keySet()) {
            if (!map2.containsKey(key))
                return false;
            else if (!map1.get(key).equals(map2.get(key)))
                return false;
        }
        return true;
    }
}
