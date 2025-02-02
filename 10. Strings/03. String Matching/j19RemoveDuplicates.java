/**
 * Problem Statement:
 * 
 *     Given a string `str`, remove all duplicate characters from the string and return the result.
 *     Each character should appear only once in the result, in the order of their first appearance in the original string.
 * 
 * Input:
 *     - A string `str` of length `n` (1 <= n <= 10^5) where each character is an ASCII character.
 * 
 * Output:
 *     - A string where all duplicate characters are removed, with each character appearing only once, preserving the order of their first appearance.
 * 
 * Example:
 *     Input:
 *     "apple"
 *     Output:
 *     "aple"
 * 
 *     Explanation:
 *     The character 'p' appears twice in the input string. The output removes the duplicate 'p' and keeps the first occurrence.
 */

import java.util.Scanner;

public class j19RemoveDuplicates {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the string str
        System.out.println(removeDuplicates(s)); // Call the function to remove duplicates
        in.close();
    }

    /**
     * Approach 1: Remove Duplicates Using Frequency Map
     * 
     * Intuition:
     * - We use an integer array `map` of size 256 to count the frequency of each character in the string.
     * - Then, we traverse the string again, and for each character, if it appears for the first time (i.e., its count is non-zero),
     *   we add it to the result and set its count to zero to ensure it won't be added again.
     * - This approach ensures that we preserve the order of first appearances while removing duplicates.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string. We traverse the string twice: once to build the frequency map and once to construct the output.
     * 
     * Space Complexity:
     * - O(1) because the array `map` has a fixed size of 256 (constant space).
     * 
     * @param str The input string.
     * @return The string with duplicates removed.
     */
    public static String removeDuplicates(String str) {
        int[] map = new int[256]; // Frequency array for ASCII characters
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++; // Count the frequency of each character
        }

        StringBuilder out = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            if (map[str.charAt(i)] != 0) { // If the character is appearing for the first time
                out.append(str.charAt(i)); // Add to the result string
                map[str.charAt(i)] = 0; // Set the count to zero to avoid adding duplicates
            }
        }

        return out.toString(); // Return the result string
    }
}
