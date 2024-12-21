/**
 * Problem Statement:
 * 
 *     Given a string `s` consisting of lowercase English letters, return the number of substrings that contain exactly three distinct characters.
 *     A substring is defined as a contiguous sequence of characters within the string.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of lowercase English letters.
 * 
 * Output:
 *     - An integer representing the number of substrings with exactly three distinct characters.
 * 
 * Example:
 *     Input:
 *     "abcabc", 
 *     Output:
 *     10
 * 
 *     Explanation:
 *     - The substrings with exactly 3 distinct characters are: "abc", "bca", "cab", "abc", "bca", "cab", "abc", "bca", "cab", "abc".
 *       So, there are 10 such substrings.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j08CountSubstrWithThreeDistinctChars {
    public static void main(String args[]) {
        // Reading the input string
        Scanner in = new Scanner(System.in);
        String s = in.next();

        // Calling different solution methods for counting substrings with exactly 3
        // distinct characters
        System.out.println(numberOfSubstrings(s));
        System.out.println(numberOfSubstringsEfficient1(s));
        System.out.println(numberOfSubstringsEfficient2(s));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - In this approach, we generate all possible substrings starting from each index in the string.
     * - For each substring, we maintain an array `hash` of size 3 to track the presence of the three distinct characters.
     * - If the sum of `hash` equals 3 (indicating all three characters are present), we count that substring.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the string. This is because we generate all substrings with two nested loops.
     * 
     * Space Complexity:
     * - O(1), because we only use a fixed-size array to track the characters (size 3).
     * 
     * @param s The input string.
     * @return The number of substrings with exactly three distinct characters.
     */
    public static int numberOfSubstrings(String s) {
        int ans = 0; // Initialize the answer variable
        // Traverse each possible starting index for the substrings
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[3]; // Array to track the presence of the three distinct characters
            // Traverse each possible ending index for the substrings
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'a'] = 1; // Mark the character at index j as present
                // If all three distinct characters are found, increment the count
                if (hash[0] + hash[1] + hash[2] == 3) {
                    ans++;
                }
            }
        }
        return ans; // Return the final count
    }

    /**
     * Approach 2: Efficient Approach 1 - Using Last Occurrences of Each Character
     * 
     * Intuition:
     * - In this approach, we maintain an array `hash` that stores the last occurrence index of each of the three characters.
     * - For each character in the string, we update its last occurrence in the `hash` array.
     * - Once all three characters have been encountered, we calculate the number of substrings ending at the current position.
     * - The number of valid substrings is the minimum of the last occurrences of the three characters, plus one.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string, since we traverse the string once and update the last occurrences.
     * 
     * Space Complexity:
     * - O(1), because we only use a fixed-size array of size 3 to track the last occurrences of the characters.
     * 
     * @param s The input string.
     * @return The number of substrings with exactly three distinct characters.
     */
    public static int numberOfSubstringsEfficient1(String s) {
        int ans = 0; // Initialize the answer variable
        int[] hash = new int[] { -1, -1, -1 }; // Array to store the last occurrence of each of the 3 distinct
                                               // characters
        // Traverse through the string
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i; // Update the last occurrence of the current character
            // If all three characters have occurred at least once
            if (hash[0] != -1 && hash[1] != -1 && hash[2] != -1) {
                // The number of valid substrings is determined by the smallest last occurrence
                // index + 1
                ans += Math.min(hash[0], Math.min(hash[1], hash[2])) + 1;
            }
        }
        return ans; // Return the final count
    }

    /**
     * Approach 3: Efficient Approach 2 - Using the Sliding Window Technique
     * 
     * Intuition:
     * - In this approach, we count the number of substrings with at most 3 distinct characters and subtract the number of substrings 
     *   with at most 2 distinct characters. This gives the number of substrings with exactly 3 distinct characters.
     * - We use the sliding window technique with two pointers (`i` and `j`) to maintain a window with at most 3 distinct characters.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. The sliding window ensures that each character is processed only once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of distinct characters (3 in this case). The HashMap stores up to `k` distinct characters.
     * 
     * @param s The input string.
     * @return The number of substrings with exactly three distinct characters.
     */
    public static int numberOfSubstringsEfficient2(String s) {
        // Count substrings with at most 3 distinct characters and subtract those with
        // at most 2 distinct characters
        return getCountOfSubstringEfficient(3, s) - getCountOfSubstringEfficient(2, s);
    }

    /**
     * Helper Function: Count Substrings with At Most K Distinct Characters
     * 
     * Intuition:
     * - This helper function calculates the number of substrings with at most `k` distinct characters using a sliding window.
     * - The window expands by moving the right pointer (`i`) and shrinks by moving the left pointer (`j`) whenever the number of distinct 
     *   characters exceeds `k`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. The sliding window technique ensures that each character is processed only once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of distinct characters. The HashMap stores up to `k` distinct characters.
     * 
     * @param k The number of distinct characters to be considered.
     * @param s The input string.
     * @return The number of substrings with at most `k` distinct characters.
     */
    public static int getCountOfSubstringEfficient(int k, String s) {
        int count = 0; // Initialize the count of valid substrings
        int j = 0; // Left pointer of the sliding window
        HashMap<Character, Integer> map = new HashMap<>(); // HashMap to track the frequency of characters

        // Traverse the string with the right pointer `i`
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

            // Add the number of substrings that can end at the current right pointer `i`
            count += i - j + 1;
        }

        return count; // Return the result
    }
}
