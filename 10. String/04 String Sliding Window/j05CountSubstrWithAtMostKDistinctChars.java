/**
 * Problem Statement:
 * 
 *     Given a string `s` and an integer `k`, find the total number of substrings of `s` that contain at most `k`
 *     distinct characters.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of lowercase English letters.
 *     - An integer `k` (1 <= k <= 26), the maximum number of distinct characters allowed in the substring.
 * 
 * Output:
 *     - An integer representing the total number of substrings that contain at most `k` distinct characters.
 * 
 * Example:
 *     Input:
 *     "eceba", k = 2
 *     Output:
 *     10
 * 
 *     Explanation:
 *     - The substrings with at most 2 distinct characters are: "e", "ec", "ce", "ece", "eb", "ba", "e", "c", "e", "b".
 *     - The total number of valid substrings is 10.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j05CountSubstrWithAtMostKDistinctChars {
    public static void main(String args[]) {
        // Reading input string and the integer k
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();

        // Calling the basic solution to get the count of substrings
        System.out.println(getCountOfSubstring(k, s));

        // Calling the efficient solution to get the count of substrings
        System.out.println(getCountOfSubstringEfficient(k, s));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force Solution
     * 
     * Intuition:
     * - We generate all possible substrings and check if the number of distinct characters in each substring is at most `k`.
     * - If the condition holds, we count that substring.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the string. We are generating all possible substrings, and for each one, 
     *   we check the number of distinct characters, which takes O(n) time in the worst case.
     * 
     * Space Complexity:
     * - O(k), where `k` is the maximum number of distinct characters (as we use a HashSet to store distinct characters).
     * 
     * @param k The maximum number of distinct characters allowed in the substring.
     * @param s The input string.
     * @return The total count of substrings with at most `k` distinct characters.
     */
    public static int getCountOfSubstring(int k, String s) {
        int count = 0; // Initialize the count of valid substrings
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>(); // HashSet to store distinct characters in the substring
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j); // Current character
                set.add(ch); // Add character to the set
                if (set.size() > k) // If the number of distinct characters exceeds k, break the loop
                    break;
                else {
                    count++; // Increment the count for valid substrings
                }
            }
        }
        return count; // Return the result
    }

    /**
     * Approach 2: Sliding Window with HashMap (Optimized Solution)
     * 
     * Intuition:
     * - We use the sliding window technique with two pointers (`i` and `j`). 
     * - We expand the window by moving `i` and include characters in the window, keeping track of the frequency of each character in the HashMap.
     * - When the size of the HashMap (i.e., number of distinct characters) exceeds `k`, we move the left pointer (`j`) to shrink the window.
     * - The window always maintains at most `k` distinct characters, and we calculate the count of valid substrings as we expand the window.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. Both pointers (`i` and `j`) traverse the string once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the size of the HashMap (i.e., the number of distinct characters in the window).
     * 
     * @param k The maximum number of distinct characters allowed in the substring.
     * @param s The input string.
     * @return The total count of substrings with at most `k` distinct characters.
     */
    public static int getCountOfSubstringEfficient(int k, String s) {
        int count = 0; // Initialize the count of valid substrings
        int j = 0; // Left pointer of the sliding window
        HashMap<Character, Integer> map = new HashMap<>(); // HashMap to store the frequency of characters in the window

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Current character at the right pointer
            map.put(ch, map.getOrDefault(ch, 0) + 1); // Increment the frequency of the current character

            // If the number of distinct characters exceeds `k`, move the left pointer `j`
            // to shrink the window
            while (map.size() > k) {
                char ch1 = s.charAt(j); // Character at the left pointer
                map.put(ch1, map.get(ch1) - 1); // Decrease the frequency of the character at `j`
                if (map.get(ch1) == 0) {
                    map.remove(ch1); // Remove the character if its frequency becomes zero
                }
                j++; // Move the left pointer to shrink the window
            }

            // Update the count of valid substrings
            count += i - j + 1; // All substrings from `j` to `i` are valid
        }

        return count; // Return the result
    }
}
