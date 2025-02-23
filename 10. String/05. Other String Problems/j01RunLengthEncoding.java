/**
 * Problem Statement:
 * 
 *     Implement run-length encoding for a given string. The run-length encoding is a 
 *     compression technique that represents consecutive characters in the string 
 *     by the character followed by the number of times it appears consecutively.
 *     For example, "aaabb" should be encoded as "a3b2".
 * 
 * Input:
 *     - A string `s` consisting of lowercase English letters (1 <= s.length <= 1000).
 * 
 * Output:
 *     - A string that represents the run-length encoding of the input string.
 * 
 * Example:
 *     Input:
 *     "aaabb"
 *     Output:
 *     "a3b2"
 * 
 *     Explanation:
 *     The character 'a' appears 3 times consecutively, followed by the character 'b' appearing 2 times consecutively.
 *     Therefore, the run-length encoded string is "a3b2".
 */

import java.util.Scanner;

public class j01RunLengthEncoding {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the string to encode
        System.out.println(runLengthEncode(s)); // Call to runLengthEncode function
        in.close();
    }

    /**
     * Approach: Iterative Run-Length Encoding
     * 
     * Intuition:
     * - We traverse the string while keeping track of consecutive occurrences of each character.
     *   For each group of consecutive characters, we append the character followed by its count
     *   to the result string.
     * - The approach uses a simple loop to count occurrences of the same character and appends them
     *   to the result string.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We traverse the string once, processing each character.
     * 
     * Space Complexity:
     * - O(n), for storing the encoded string in a StringBuilder.
     * 
     * @param s The input string.
     * @return The run-length encoded string.
     */
    public static String runLengthEncode(String s) {
        StringBuilder out = new StringBuilder(); // To store the encoded string
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            int c = 0; // Count of the current character
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                j++; // Increment the index while characters are the same
                c++; // Increment the count for consecutive characters
            }
            j--; // Adjust the index for the next non-repeating character
            out.append(s.charAt(j)).append(c); // Append the character and its count to the result
            i = j; // Move `i` to the end of the current group of characters
        }
        return out.toString(); // Return the final encoded string
    }
}
