/**
 * Problem Statement:
 * 
 *     Given a string, the goal is to implement string compression using the counts of repeated characters. 
 *     If the string has no repeated characters, the original string should be returned as it is. 
 *     If there are repeated characters, we should replace the sequences of repeated characters with the 
 *     character followed by its count.
 * 
 *     For example:
 *     - "aabcccccaaa" should be compressed to "a2b1c5a3".
 * 
 * Input:
 *     - A string `s` consisting of lowercase English letters (1 <= s.length <= 1000).
 * 
 * Output:
 *     - The string after applying the compression algorithm.
 * 
 * Example:
 *     Input:
 *     "aabcccccaaa"
 *     Output:
 *     "a2b1c5a3"
 * 
 *     Explanation:
 *     The character 'a' appears twice, 'b' appears once, 'c' appears five times, and 'a' appears three times.
 *     Therefore, the compressed string is "a2b1c5a3".
 */

import java.util.Scanner;

public class j03StringCompression {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        char[] s = in.nextLine().toCharArray(); // Input: the string to compress
        int i = compress(s); // Compress the string

        // Print the compressed string
        for (int j = 0; j < i; j++) {
            System.out.print(s[j]);
        }

        in.close();
    }

    /**
     * Approach: In-Place String Compression
     * 
     * Intuition:
     * - We iterate over the string and, for each group of consecutive identical characters,
     *   we count how many times they appear. If the count is greater than 1, we replace the 
     *   sequence with the character followed by its count.
     * - We modify the original array in-place, updating it as we go to avoid extra space usage.
     * - The `index` variable is used to track where to insert the next character or count.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We process each character at most twice.
     * 
     * Space Complexity:
     * - O(1), since the compression is done in-place with no additional space for another string.
     * 
     * @param chars The input character array to compress.
     * @return The new length of the compressed string.
     */
    public static int compress(char[] chars) {
        int i = 0; // Pointer to traverse the input array
        int index = 0; // Pointer to track where to insert in the array

        while (i < chars.length) {
            char c = chars[i]; // Current character
            int count = 0; // To count the number of consecutive occurrences of the character

            // Count consecutive occurrences of the character
            while (i < chars.length && c == chars[i]) {
                i++; // Move to the next character
                count++; // Increment the count for consecutive characters
            }

            // Insert the character at the current index
            chars[index++] = c;

            // If the count is greater than 1, append the count to the array
            if (count > 1) {
                for (char chr : String.valueOf(count).toCharArray()) {
                    chars[index++] = chr; // Insert each digit of the count
                }
            }
        }

        return index; // Return the new length of the compressed string
    }
}
