/**
 * Problem Statement:
 * 
 *     Given a string `S`, find the first character that appears more than once in the string. 
 *     If no character repeats, return `#`.
 * 
 * Input:
 *     - A string `S` (1 <= S.length <= 10^5), consisting of lowercase English letters.
 * 
 * Output:
 *     - Return the first repeated character in the string `S`.
 *     - If no character is repeated, return `#`.
 * 
 * Example:
 * Input: "abcdca"
 * Output: 'a'
 * 
 * Input: "abcdef"
 * Output: '#'
 * 
 * Explanation:
 *     - In the first example, the first repeated character is 'a', which appears again at the last position.
 *     - In the second example, no character is repeated, so the output is '#'.
 */

import java.util.Scanner;

public class j17FirstRepeatedCharacter {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: string S
        System.out.println(firstRep(s)); // Output the first repeated character or '#'
        in.close();
    }

    /**
     * Function to find the first repeated character in the string.
     * 
     * Intuition:
     * - We can solve this problem efficiently by iterating over the string and counting the frequency of each character.
     * - As we iterate through the string, if we encounter a character that has already been seen (i.e., its frequency is >= 2), 
     *   it means it's the first repeated character, so we return it immediately.
     * - If no repeated character is found, we return `#`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We perform two iterations over the string: one to count the frequencies, 
     *   and another to identify the first repeated character.
     * 
     * Space Complexity:
     * - O(1), as we are using a fixed-size array of 26 elements to store the frequency of characters.
     * 
     * @param S The input string.
     * @return The first repeated character, or `#` if no repeated character exists.
     */
    public static char firstRep(String S) {
        // Array to store the frequency of each character (26 letters in total)
        int[] map = new int[26];

        // First pass: Count the frequency of each character
        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a']++; // Increment the count for the character at position i
        }

        // Second pass: Find the first character with frequency >= 2
        for (int i = 0; i < S.length(); i++) {
            if (map[S.charAt(i) - 'a'] >= 2) { // Check if this character is repeated
                return S.charAt(i); // Return the first repeated character
            }
        }

        // Return '#' if no repeated character is found
        return '#';
    }
}
