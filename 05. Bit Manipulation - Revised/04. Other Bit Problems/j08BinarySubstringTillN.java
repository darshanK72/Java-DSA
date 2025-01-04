/**
 * Problem Statement:
 * 
 *     Given a binary string `s` and an integer `n`, determine whether all binary representations 
 *     of integers from 1 to `n` are substrings of `s`.
 * 
 * Input:
 *     - A binary string `s` consisting of '0's and '1's.
 *     - An integer `n` (1 <= n <= 10^4).
 * 
 * Output:
 *     - A boolean value:
 *         - `true` if all binary representations of integers from 1 to `n` are substrings of `s`.
 *         - `false` otherwise.
 * 
 * Example:
 *     Input:
 *         s = "0110"
 *         n = 3
 *     Output:
 *         true
 * 
 *     Explanation:
 *         Binary representations of integers 1, 2, and 3 are "1", "10", and "11", respectively.
 *         All these are present as substrings in "0110".
 * 
 *     Input:
 *         s = "0110"
 *         n = 4
 *     Output:
 *         false
 * 
 *     Explanation:
 *         Binary representation of 4 is "100", which is not a substring of "0110".
 */

import java.util.Scanner;

public class j08BinarySubstringTillN {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.next(); // Input: binary string
        int n = in.nextInt(); // Input: integer `n`

        // Call the brute-force solution
        System.out.println(queryStringBruteForce(s, n));

        // Call the optimized solution
        System.out.println(queryStringOptimized(s, n));

        in.close();
    }

    /**
     * Approach 1: Brute-Force Check for All Binary Substrings
     * 
     * Intuition:
     * - Convert each integer from 1 to `n` into its binary representation.
     * - Use the `contains` method of the string `s` to check if the binary string is a substring.
     * - If any binary representation is missing, return `false`.
     * 
     * Time Complexity:
     * - O(n * k), where `n` is the range of numbers (1 to `n`) and `k` is the average length of the binary representation.
     *     - Binary length `k` is approximately O(log(n)).
     *     - Worst-case complexity: O(n * log(n)).
     * 
     * Space Complexity:
     * - O(k), where `k` is the length of the binary representation string (temporary storage for each number).
     * 
     * @param s The binary string.
     * @param n The integer `n`.
     * @return `true` if all binary representations from 1 to `n` are substrings of `s`; `false` otherwise.
     */
    public static boolean queryStringBruteForce(String s, int n) {
        for (int i = 1; i <= n; i++) {
            // Convert integer to binary representation
            String bin = Integer.toBinaryString(i);

            // Check if `s` contains this binary string
            if (!s.contains(bin))
                return false;
        }
        return true;
    }

    /**
     * Approach 2: Optimized Check Using Sliding Window
     * 
     * Intuition:
     * - Instead of generating binary representations for all numbers, extract substrings directly from `s` and validate.
     * - Use a sliding window approach to extract all substrings of possible binary lengths (up to log2(n)) from `s`.
     * - Convert these substrings to integers and track which numbers (from 1 to `n`) are present.
     * - This avoids repeatedly converting numbers to binary and improves efficiency.
     * 
     * Algorithm:
     * - Determine the maximum binary length as `log2(n)`.
     * - For each possible binary length, slide a window over `s` to extract substrings.
     * - Convert substrings to integers and record their presence in a `boolean[]`.
     * 
     * Time Complexity:
     * - O(m * log(n)), where `m` is the length of `s` and `log(n)` is the number of binary lengths to check.
     * 
     * Space Complexity:
     * - O(n), where `n` is the size of the tracking array.
     * 
     * @param s The binary string.
     * @param n The integer `n`.
     * @return `true` if all binary representations from 1 to `n` are substrings of `s`; `false` otherwise.
     */
    public static boolean queryStringOptimized(String s, int n) {
        int maxBinaryLength = Integer.toBinaryString(n).length(); // Max binary length for numbers up to `n`
        boolean[] seen = new boolean[n + 1]; // Tracking array for numbers 1 to `n`

        // Extract substrings of lengths from 1 to maxBinaryLength
        for (int length = 1; length <= maxBinaryLength; length++) {
            for (int i = 0; i <= s.length() - length; i++) {
                String sub = s.substring(i, i + length);
                int num = 0;
                try {
                    num = Integer.parseInt(sub, 2); // Convert binary substring to integer
                } catch (NumberFormatException e) {
                    continue; // Ignore invalid binary strings
                }
                if (num >= 1 && num <= n) {
                    seen[num] = true; // Mark the number as seen
                }
            }
        }

        // Check if all numbers from 1 to `n` are seen
        for (int i = 1; i <= n; i++) {
            if (!seen[i]) {
                return false;
            }
        }

        return true;
    }
}
