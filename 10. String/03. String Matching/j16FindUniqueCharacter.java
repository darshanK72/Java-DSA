/**
 * Problem Statement:
 * 
 *     Given a string `s`, find the index of the first non-repeating character in it. 
 *     If it doesn't exist, return -1.
 * 
 * Input:
 *     - A string `s` (1 <= s.length <= 10^5) consisting of lowercase English letters.
 * 
 * Output:
 *     - Return the index of the first non-repeating character in `s`.
 *     - If no such character exists, return -1.
 * 
 * Example:
 *      Input: "leetcode"
 *      Output: 0
 * 
 *      Input: "loveleetcode"
 *      Output: 2
 * 
 *      Explanation:
 *          - In the first example, the first unique character is 'l', which is at index 0.
 *          - In the second example, the first unique character is 'v', which is at index 2.
 */

import java.util.Scanner;

public class j16FindUniqueCharacter {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: string s
        System.out.println(firstUniqChar(s)); // Output the index of the first unique character
        in.close();
    }

    /**
     * Function to find the first unique character in the string.
     * 
     * Intuition:
     * - We will iterate over the string and keep track of the frequency of each character using an array of size 26 (for each lowercase English letter).
     * - After counting the frequencies, we will iterate again to find the first character with a frequency of 1, which is the first unique character.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We iterate over the string twice: once to count frequencies, and once to find the first unique character.
     * 
     * Space Complexity:
     * - O(1), as we are using a fixed-size array of 26 to store character frequencies.
     * 
     * @param str The input string.
     * @return The index of the first non-repeating character, or -1 if no such character exists.
     */
    public static int firstUniqChar(String str) {
        // Array to store the frequency of each character (26 letters in total)
        int[] map = new int[26];

        // First pass: Count the frequency of each character
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i) - 'a']++; // Increment the count for the character at position i
        }

        // Second pass: Find the first character with frequency 1
        for (int i = 0; i < str.length(); i++) {
            if (map[str.charAt(i) - 'a'] == 1) { // Check if this character is unique
                return i; // Return the index of the first unique character
            }
        }

        // Return -1 if no unique character is found
        return -1;
    }
}
