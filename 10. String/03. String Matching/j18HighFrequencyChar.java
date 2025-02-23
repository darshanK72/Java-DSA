/**
 * Problem Statement:
 * 
 *     Given a string `s`, find the character that appears the most frequently in the string.
 *     If there are multiple characters with the same frequency, return the one that appears first in the string.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5), consisting of lowercase English letters.
 * 
 * Output:
 *     - Return the character that appears the most frequently in the string. If there are multiple characters with the same frequency, return the first one in the string.
 * 
 * Example:
 * Input: "abacbcd"
 * Output: 'b'
 * 
 * Input: "aaabbcc"
 * Output: 'a'
 * 
 * Explanation:
 *     - In the first example, 'b' has the highest frequency (2), appearing before 'a' and 'c' with equal frequency.
 *     - In the second example, 'a' has the highest frequency (3).
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j18HighFrequencyChar {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next(); // Input: string s
        System.out.println(highestFrequencyChar(s)); // Output using the first approach
        System.out.println(highestFrequencyCharArrayHashing(s)); // Output using the second approach
        in.close();
    }

    /**
     * Approach 1: Using HashMap to store character frequencies.
     * 
     * Intuition:
     * - We iterate over the string and store the frequency of each character in a HashMap.
     * - After counting the frequencies, we iterate over the map and find the character with the highest frequency.
     * - If multiple characters have the same frequency, we return the one that appears first in the string.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string, due to two passes over the string (one for counting and another for finding the max).
     * 
     * Space Complexity:
     * - O(26), as we store the frequency of each character in a HashMap with a maximum size of 26.
     * 
     * @param s The input string.
     * @return The character with the highest frequency.
     */
    public static char highestFrequencyChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // First pass: Count the frequency of each character
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1); // Increment frequency
        }

        // Second pass: Find the character with the highest frequency
        char out = s.charAt(0); // Initialize with the first character
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > map.get(out)) {
                out = entry.getKey(); // Update if we find a higher frequency
            }
        }
        return out;
    }

    /**
     * Approach 2: Using an array of size 26 for hashing.
     * 
     * Intuition:
     * - We can use an array of size 26 to store the frequency of each character. Since the input contains only lowercase English letters,
     *   we can use the index `0-25` to represent characters `'a'-'z'`.
     * - After counting the frequencies, we iterate through the array and find the character with the highest frequency.
     * - This approach avoids the overhead of using a HashMap and works faster in terms of space and time complexity.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We perform two passes over the string: one to count the frequencies and another to find the max.
     * 
     * Space Complexity:
     * - O(26), as we use a fixed-size array of 26 to store the frequency of each character.
     * 
     * @param s The input string.
     * @return The character with the highest frequency.
     */
    public static char highestFrequencyCharArrayHashing(String s) {
        int[] hash = new int[26];

        // First pass: Count the frequency of each character
        for (char c : s.toCharArray()) {
            hash[c - 'a']++; // Increment frequency of character 'c'
        }

        // Second pass: Find the character with the highest frequency
        char out = s.charAt(0); // Initialize with the first character
        for (int i = 0; i < 26; i++) {
            if (hash[i] > hash[out - 'a']) {
                out = (char) ('a' + i); // Update if we find a higher frequency
            }
        }
        return out;
    }
}
