/**
 * Problem Statement:
 * 
 *     Convert a given integer `n` into its 32-bit binary representation.
 *     The output should represent the signed 32-bit representation of the number.
 * 
 * Input:
 *     - An integer `n` (-2^31 <= n <= 2^31 - 1), the number to be converted.
 * 
 * Output:
 *     - A string of 32 characters, representing the binary representation of `n`.
 * 
 * Example:
 *     Input:
 *         5
 *     Output:
 *         00000000000000000000000000000101
 * 
 *     Explanation:
 *         The number 5 in binary is "101". Padded with leading zeros to make it 32-bit: 
 *         "00000000000000000000000000000101".
 * 
 *     Input:
 *         -5
 *     Output:
 *         11111111111111111111111111111011
 * 
 *     Explanation:
 *         The number -5 in 32-bit signed representation is calculated as two's complement. 
 *         In binary: "11111111111111111111111111111011".
 */

import java.util.Scanner;

public class j02BinaryRepresentation {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to be converted to binary
        System.out.println(getBinaryUsingMethod(n)); // Binary using Integer.toBinaryString
        System.out.println(toBinaryStringForPositive(n)); // Binary for positive integers
        System.out.println(toBinaryStringForBoth(n)); // Binary for signed integers
        System.out.println(toBinaryStringUnsignedShift(n)); // Binary for signed integers using Unsigned Shift
        System.out.println(toBinaryStringEfficient(n)); // Optimized binary generation

        in.close();
    }

    /**
     * Approach 1: Using Built-In Method
     * 
     * Intuition:
     * - Use Java's built-in `Integer.toBinaryString()` to generate the binary representation
     *   of a number, and pad it with leading zeros to make it 32-bit.
     * 
     * Time Complexity:
     * - O(32) for padding and string concatenation.
     * 
     * Space Complexity:
     * - O(32), for the output string.
     * 
     * @param N The input integer.
     * @return A 32-bit binary representation of `N`.
     */
    public static String getBinaryUsingMethod(int N) {
        String output = "";
        String bin = Integer.toBinaryString(N); // Generate binary string
        for (int i = 1; i <= 32 - bin.length(); i++) { // Pad with leading zeros
            output += "0";
        }
        output += bin;
        return output;
    }

    /**
     * Approach 2: Iterative Conversion (Positive Integers)
     * 
     * Intuition:
     * - For positive integers, repeatedly divide the number by 2, collecting the remainders
     *   to build the binary representation. Pad with zeros to make it 32-bit.
     * 
     * Time Complexity:
     * - O(32), as we iterate over at most 32 bits.
     * 
     * Space Complexity:
     * - O(32), for the output string.
     * 
     * @param n The input positive integer.
     * @return A 32-bit binary representation of `n`.
     */
    public static String toBinaryStringForPositive(int n) {
        StringBuilder out = new StringBuilder();
        while (n > 0) {
            out.append(n & 1); // Append the least significant bit
            n >>= 1; // Right shift the number
        }
        int l = out.length();

        for (int i = 1; i <= 32 - l; i++) { // Pad with leading zeros
            out.append("0");
        }
        return out.reverse().toString(); // Reverse to get the correct binary order
    }

    /**
     * Approach 3: Handle Both Positive and Negative Integers
     * 
     * Intuition:
     * - For negative integers, convert them to their two's complement form, then follow
     *   the same steps as for positive integers.
     * 
     * Time Complexity:
     * - O(32), as we iterate over at most 32 bits.
     * 
     * Space Complexity:
     * - O(32), for the output string.
     * 
     * @param n The input integer (can be positive or negative).
     * @return A 32-bit binary representation of `n`.
     */
    public static String toBinaryStringForBoth(int n) {
        StringBuilder out = new StringBuilder();
        boolean isNegative = false;
        if (n < 0) {
            // Convert to two's complement
            isNegative = true;
            n = Integer.MAX_VALUE + 1 + n;
        }
        while (n > 0) {
            out.append(n & 1); // Append the least significant bit
            n >>= 1; // Right shift the number
        }

        int length = out.length();
        for (int i = 1; i <= 31 - length; i++) { // Pad with leading zeros
            out.append("0");
        }
        if (isNegative)
            out.append("1");
        else
            out.append("0");
        return out.reverse().toString();
    }

    public static String toBinaryStringUnsignedShift(int n) {
        StringBuilder out = new StringBuilder();
        while (n != 0) {
            out.append(n & 1); // Append the least significant bit
            n >>>= 1; // Unsigned Right shift the number
        }
        int length = out.length();
        for (int i = 1; i <= 32 - length; i++) { // Pad with leading zeros
            out.append("0");
        }
        return out.reverse().toString();
    }

    /**
     * Approach 4: Efficient Bitwise Conversion
     * 
     * Intuition:
     * - Iterate through all 32 bits of the number and extract each bit using right-shift
     *   and bitwise AND operations.
     * 
     * Time Complexity:
     * - O(32), as we iterate through 32 bits of the number.
     * 
     * Space Complexity:
     * - O(32), for the output string.
     * 
     * @param n The input integer.
     * @return A 32-bit binary representation of `n`.
     */
    public static String toBinaryStringEfficient(int n) {
        StringBuilder out = new StringBuilder();
        for (int i = 31; i >= 0; i--) { // Iterate through all 32 bits
            out.append((n >> i) & 1); // Extract the bit at position `i`
        }
        return out.toString(); // Return the binary string
    }
}
