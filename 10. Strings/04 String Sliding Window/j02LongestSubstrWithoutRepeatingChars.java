/**
 * Problem Statement:
 * 
 *     Given a string `s`, find the length of the longest substring without repeating characters.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of ASCII characters.
 * 
 * Output:
 *     - An integer representing the length of the longest substring of `s` that does not contain repeating characters.
 * 
 * Example:
 *     Input:
 *     "abcabcbb"
 *     Output:
 *     3
 * 
 *     Explanation:
 *     - The longest substring without repeating characters is "abc", which has a length of 3.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j02LongestSubstrWithoutRepeatingChars {
    public static void main(String args[]) {
        // Reading input string
        Scanner in = new Scanner(System.in);
        String s = in.next();

        // Calling all approaches and printing their results
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstringEfficient1(s));
        System.out.println(lengthOfLongestSubstringEfficient2(s));
        System.out.println(lengthOfLongestSubstringEfficient3(s));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force - Check every substring
     * 
     * Intuition:
     * - We check every possible substring of the input string `s` and see if it contains repeating characters.
     * - For each substring without duplicates, we calculate its length and keep track of the maximum length.
     * 
     * Time Complexity:
     * - O(n^3), where n is the length of the string. This is because we generate all substrings (O(n^2)) and check if they contain duplicates (O(n) for each substring).
     * 
     * Space Complexity:
     * - O(n), where n is the length of the string, due to the space used by the hash set to store characters.
     * 
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxL = 0;

        // Generate all substrings
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                // If the character already exists, break the loop
                if (set.contains(s.charAt(j))) {
                    break;
                } else {
                    // Update the maximum length
                    maxL = Math.max(maxL, j - i + 1);
                    set.add(s.charAt(j)); // Add character to the set
                }
            }
        }

        return maxL; // Return the maximum length of substring without repeating characters
    }

    /**
     * Approach 2: Sliding Window using HashSet
     * 
     * Intuition:
     * - Use a sliding window to keep track of the longest substring without repeating characters.
     * - As we move the window from left to right, we add the current character to a HashSet and adjust the window size.
     * - If a character is repeated, we move the left boundary of the window to the right until there are no duplicates.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string. We make a single pass through the string.
     * 
     * Space Complexity:
     * - O(n), due to the space used by the HashSet.
     * 
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int lengthOfLongestSubstringEfficient1(String s) {
        int maxL = 0;
        int j = 0;
        HashSet<Character> set = new HashSet<>();

        // Sliding window approach
        for (int i = 0; i < s.length(); i++) {
            // If the character is already in the set, shrink the window from the left
            while (set.contains(s.charAt(i))) {
                set.remove(s.charAt(j));
                j++; // Move the left pointer to the right
            }
            set.add(s.charAt(i)); // Add the current character to the set
            maxL = Math.max(maxL, i - j + 1); // Update max length
        }

        return maxL;
    }

    /**
     * Approach 3: Sliding Window using HashMap
     * 
     * Intuition:
     * - We use a HashMap to store the frequency of characters in the current window.
     * - We adjust the left boundary of the window when we encounter a character that appears more than once.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string. We iterate through the string once, updating the window boundaries as needed.
     * 
     * Space Complexity:
     * - O(n), due to the space used by the HashMap to store the frequencies of characters.
     * 
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int lengthOfLongestSubstringEfficient2(String s) {
        int maxL = 0;
        int j = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        // Sliding window approach with frequency count
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // If character appears more than once, move the left pointer to the right
            while (map.get(ch) > 1) {
                char ch1 = s.charAt(j);
                map.put(ch1, map.getOrDefault(ch1, 0) - 1);
                j++; // Move the left pointer
            }

            maxL = Math.max(maxL, i - j + 1); // Update max length
        }

        return maxL;
    }

    /**
     * Approach 4: Sliding Window with Fixed-size Array
     * 
     * Intuition:
     * - Instead of using a HashMap, we use a fixed-size array to count the frequencies of characters in the current window.
     * - This reduces the space complexity as we only need an array of size 256 (for all ASCII characters).
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string. We perform a single pass through the string.
     * 
     * Space Complexity:
     * - O(1), as the space used by the frequency array is constant (256 entries).
     * 
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int lengthOfLongestSubstringEfficient3(String s) {
        int maxL = 0;
        int j = 0;
        int[] hash = new int[256]; // Array to store character frequencies for all ASCII characters

        // Sliding window with fixed-size frequency array
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hash[ch]++; // Increment the count for the current character

            // If character appears more than once, move the left pointer
            while (hash[ch] > 1) {
                char ch1 = s.charAt(j);
                hash[ch1]--; // Decrement the count for the character at the left boundary
                j++; // Move the left pointer
            }

            maxL = Math.max(maxL, i - j + 1); // Update the maximum length
        }

        return maxL;
    }
}
