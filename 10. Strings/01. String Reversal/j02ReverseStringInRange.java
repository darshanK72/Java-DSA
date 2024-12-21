/**
 * Problem Statement:
 * 
 *     Given a string `s` and an integer `k`, you are tasked with reversing every `k` characters in the string.
 *     For example, if `s = "abcdefg"` and `k = 2`, the output should be `"bacdfeg"`.
 *     The reversal should happen in blocks of `k` characters, with the last block possibly being smaller than `k` if necessary.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 1000), which contains only printable characters.
 *     - An integer `k` (1 <= k <= 1000), indicating the length of the substring to reverse.
 * 
 * Output:
 *     - A string where every `k` characters are reversed.
 * 
 * Example:
 *     Input:
 *     "abcdefg", 2
 *     Output:
 *     "bacdfeg"
 */

import java.util.Scanner;

public class j02ReverseStringInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input string
        int k = in.nextInt(); // Input integer k
        System.out.println(reverseStringInRange(s, k)); // Output the string with reversed blocks
        in.close();
    }

    /**
     * Approach: Reversing every k-length block of the string
     * 
     * Intuition:
     * - The idea is to iterate through the string in steps of `2 * k` and reverse each block of `k` characters.
     * - For every block starting at index `i`, we reverse the substring from index `i` to `i + k - 1`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We iterate over the string in steps of `2 * k`, with a reverse operation on each block.
     * 
     * Space Complexity:
     * - O(n), as we convert the string into a character array to facilitate the reversal of characters in place.
     * 
     * @param str The input string.
     * @param k The length of the block to reverse.
     * @return The string with every `k` characters reversed.
     */
    public static String reverseStringInRange(String str, int k) {
        char[] out = str.toCharArray(); // Convert string to character array for easier manipulation
        for (int i = 0; i < str.length(); i += (2 * k)) {
            int s = i; // Starting index of the block
            int e = Math.min(str.length() - 1, i + k - 1); // Ending index of the block (ensure it doesn't go out of
                                                           // bounds)
            while (s < e) {
                // Swap characters at positions s and e
                char t = out[s];
                out[s] = out[e];
                out[e] = t;
                s++; // Move the start pointer to the right
                e--; // Move the end pointer to the left
            }
        }
        return new String(out); // Convert the character array back to a string and return it
    }
}
