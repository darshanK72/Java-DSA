/**
 * Problem Statement:
 * 
 *     Convert a given integer `n` into its hexadecimal representation.
 *     The output should represent the signed 32-bit representation of the number.
 * 
 * Input:
 *     - An integer `n` (-2^31 <= n <= 2^31 - 1), the number to be converted.
 * 
 * Output:
 *     - A string representing the hexadecimal representation of `n`. 
 *       For negative numbers, it should use two's complement representation.
 * 
 * Example:
 *     Input:
 *         26
 *     Output:
 *         "1a"
 * 
 *     Explanation:
 *         The number 26 in hexadecimal is "1a".
 * 
 *     Input:
 *         -1
 *     Output:
 *         "ffffffff"
 * 
 *     Explanation:
 *         The number -1 in hexadecimal is represented as "ffffffff" in 32-bit two's complement.
 */

import java.util.Scanner;

public class j03DecimalToHexadecimal {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to be converted to hexadecimal
        System.out.println(toHex(n)); // Output: hexadecimal representation
        in.close();
    }

    /**
     * Approach: Bitwise Conversion to Hexadecimal
     * 
     * Intuition:
     * - Convert the number `n` into its hexadecimal representation by extracting 4 bits
     *   at a time (using `num & 15` to get the last 4 bits).
     * - For each 4-bit chunk:
     *   - If the value is less than 10, map it to '0' through '9'.
     *   - If the value is 10 or more, map it to 'a' through 'f'.
     * - Right-shift the number by 4 bits (unsigned shift `>>>`) to process the next chunk.
     * 
     * Time Complexity:
     * - O(8), as a 32-bit integer has at most 8 hexadecimal digits.
     * 
     * Space Complexity:
     * - O(8), for the output string.
     * 
     * @param num The input integer.
     * @return A hexadecimal representation of `num`.
     */
    public static String toHex(int num) {
        if (num == 0)
            return "0"; // Handle the case for zero
        StringBuilder out = new StringBuilder();
        while (num != 0) {
            int d = num & 15; // Extract the last 4 bits
            if (d < 10) {
                out.insert(0, (char) ('0' + d)); // Map to '0'-'9'
            } else {
                out.insert(0, (char) ('a' + d - 10)); // Map to 'a'-'f'
            }
            num >>>= 4; // Unsigned right shift by 4 bits
        }
        return out.toString();
    }
}
