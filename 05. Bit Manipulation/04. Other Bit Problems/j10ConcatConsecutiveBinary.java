/**
 * Problem Statement:
 * 
 *     Given a positive integer `n`, return the decimal value of the binary string formed by concatenating the binary representations of
 *     all integers from 1 to `n` in order, modulo 10^9 + 7.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the range of numbers to concatenate in binary.
 * 
 * Output:
 *     - An integer representing the decimal value of the concatenated binary string modulo 10^9 + 7.
 * 
 * Example:
 *     Input:
 *         n = 3
 *     Output:
 *         27
 * 
 *     Explanation:
 *         The binary representations of 1, 2, and 3 are "1", "10", and "11".
 *         Concatenating these gives "11011", which is 27 in decimal.
 */

import java.util.Scanner;

public class j10ConcatConsecutiveBinary {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number range
        System.out.println(concatenatedBinary(n)); // Call the solution
        in.close();
    }

    /**
     * Approach: Efficient Binary Concatenation Using Bitwise Operations
     * 
     * Intuition:
     * - To construct the concatenated binary string efficiently, calculate the length of the binary representation for each number
     *   (using log2 or counting bits).
     * - Use bitwise operations to shift the previously formed number by the required bit-length, then add the current number.
     * - Take modulo 10^9 + 7 to handle large numbers and keep the computation within limits.
     * 
     * Time Complexity:
     * - O(n), where `n` is the input number. Each number requires a constant amount of computation.
     * 
     * Space Complexity:
     * - O(1), as the solution uses constant extra space.
     * 
     * @param n The input range for concatenating binary numbers.
     * @return The decimal value of the concatenated binary string modulo 10^9 + 7.
     */
    public static int concatenatedBinary(int n) {
        final int MOD = 1000000007; // Modulo value
        long ans = 0; // Store the result

        for (int i = 1; i <= n; i++) {
            // Calculate the bit-length of `i`
            int bitLength = (int) (Math.log(i) / Math.log(2)) + 1;

            // Shift the result and add the current number
            ans = ((ans << bitLength) | i) % MOD;
        }

        return (int) ans; // Return result as an integer
    }
}
