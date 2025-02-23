/**
 * Problem Statement:
 * 
 *     Given two strings `s1` and `s2`, write a function to check if `s2` contains a permutation of `s1`. 
 *     In other words, return true if one of the permutations of `s1` is the substring of `s2`, otherwise return false.
 * 
 * Input:
 *     - Two strings `s1` and `s2` (1 <= s1.length, s2.length <= 10^5).
 * 
 * Output:
 *     - A boolean value indicating whether `s2` contains any permutation of `s1`.
 * 
 * Example:
 *     Input:
 *     "ab", "eidbaooo"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - The substring "ba" is a permutation of "ab", so the output is true.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j01PermutationInString {
    public static void main(String args[]) {
        // Reading input strings
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();

        // Calling all approaches and printing their results
        System.out.println(checkInclusion(s1, s2));
        System.out.println(checkInclusionEfficient(s1, s2));
        System.out.println(checkInclusionMoreEfficient(s1, s2));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force with Sorting
     * 
     * Intuition:
     * - We generate all substrings of `s2` that are of the same length as `s1`.
     * - For each substring, we sort both the substring and `s1` and compare them.
     * - If any substring matches `s1` in sorted order, we return true.
     * 
     * Time Complexity:
     * - O(n * m log m), where n is the length of `s2` and m is the length of `s1`.
     * - For each substring of length m, we sort it, which takes O(m log m) time.
     * 
     * Space Complexity:
     * - O(m), where m is the length of `s1`, due to the space needed for sorting.
     * 
     * @param s1 The input string to find a permutation of.
     * @param s2 The string in which to check for the permutation.
     * @return True if `s2` contains a permutation of `s1`, false otherwise.
     */
    public static boolean checkInclusion(String s1, String s2) {
        int windowSize = s1.length();
        char[] s = s1.toCharArray();
        Arrays.sort(s);
        s1 = new String(s);

        // Iterate through all substrings of s2 of length equal to s1
        for (int i = 0; i <= s2.length() - windowSize; i++) {
            char[] ts = s2.substring(i, i + windowSize).toCharArray();
            Arrays.sort(ts); // Sort the current substring
            if (s1.equals(new String(ts))) {
                return true; // Return true if a matching permutation is found
            }
        }
        return false; // No matching permutation found
    }

    /**
     * Approach 2: HashMap for Frequency Counting
     * 
     * Intuition:
     * - We count the frequency of characters in `s1` and `s2`.
     * - We use two hash maps: one for `s1` and one for the sliding window of `s2`.
     * - We slide the window over `s2`, adjusting the map and checking for a match.
     * - If the frequency maps match at any point, a permutation of `s1` is found in `s2`.
     * 
     * Time Complexity:
     * - O(n), where n is the length of `s2`. We perform a single pass through `s2` while updating and comparing frequency maps.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space (a map for 26 letters).
     * 
     * @param s1 The input string to find a permutation of.
     * @param s2 The string in which to check for the permutation.
     * @return True if `s2` contains a permutation of `s1`, false otherwise.
     */
    public static boolean checkInclusionEfficient(String s1, String s2) {
        int windowSize = s1.length();
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        // Populate the map for s1
        for (char c : s1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        // If s2 is smaller than s1, no permutation can exist
        if (windowSize > s2.length()) {
            return false;
        }

        // Initialize the map for the first window in s2
        for (int i = 0; i < windowSize; i++) {
            char c = s2.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        // Check if the first window matches the frequency map of s1
        if (map1.equals(map2)) {
            return true;
        }

        // Slide the window across s2
        for (int i = windowSize; i < s2.length(); i++) {
            char c1 = s2.charAt(i - windowSize); // Remove the character that is sliding out
            int val = map2.get(c1);
            if (val == 1) {
                map2.remove(c1);
            } else {
                map2.put(c1, val - 1);
            }
            char c2 = s2.charAt(i); // Add the new character
            map2.put(c2, map2.getOrDefault(c2, 0) + 1);
            if (map1.equals(map2)) {
                return true; // Return true if a matching permutation is found
            }
        }

        return false; // No matching permutation found
    }

    /**
     * Approach 3: Optimized with Frequency Array
     * 
     * Intuition:
     * - Instead of using HashMaps, we use arrays to store character frequencies.
     * - We compare the character frequency arrays for the sliding window in `s2` and `s1`.
     * - This reduces the overhead of using hashmaps and provides a faster solution.
     * 
     * Time Complexity:
     * - O(n), where n is the length of `s2`. We perform a single pass through `s2`, adjusting the frequency arrays.
     * 
     * Space Complexity:
     * - O(1), as the space used for frequency arrays is constant (only 26 entries for the English alphabet).
     * 
     * @param s1 The input string to find a permutation of.
     * @param s2 The string in which to check for the permutation.
     * @return True if `s2` contains a permutation of `s1`, false otherwise.
     */
    public static boolean checkInclusionMoreEfficient(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        // If s1 is longer than s2, no permutation is possible
        if (n1 > n2) {
            return false;
        }

        // Frequency arrays for s1 and the first window in s2
        int[] map1 = new int[26];
        for (int i = 0; i < n1; i++) {
            map1[s1.charAt(i) - 'a']++;
        }

        int[] map2 = new int[26];
        for (int i = 0; i < n1; i++) {
            map2[s2.charAt(i) - 'a']++;
        }

        // Check if the frequency arrays are equal
        if (isEqual(map1, map2)) {
            return true;
        }

        // Slide the window across s2
        for (int i = n1; i < n2; i++) {
            map2[s2.charAt(i - n1) - 'a']--; // Remove the character that is sliding out
            map2[s2.charAt(i) - 'a']++; // Add the new character
            if (isEqual(map1, map2)) {
                return true; // Return true if a matching permutation is found
            }
        }

        return false; // No matching permutation found
    }

    /**
     * Helper function to compare two frequency arrays.
     * 
     * @param arr1 The first frequency array.
     * @param arr2 The second frequency array.
     * @return True if both frequency arrays are equal, false otherwise.
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false; // Return false if the frequencies do not match
            }
        }
        return true; // Return true if the frequencies match
    }
}
