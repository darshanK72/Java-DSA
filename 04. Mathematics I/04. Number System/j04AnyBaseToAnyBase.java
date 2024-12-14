/*-
 * Problem Statement:
 * 
 *     Given a number `n` in base `b1`, convert it to base `b2` and return the result.
 * 
 * Input:
 *     - An integer `n` representing the number in base `b1`.
 *     - An integer `b1` representing the base of the input number `n`.
 *     - An integer `b2` representing the target base to which the number needs to be converted.
 * 
 * Output:
 *     - The number `n` converted to base `b2`.
 * 
 * Example:
 *     Input:
 *     101 2 10
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The binary number `101` in base 2 is equivalent to `5` in base 10.
 */

import java.util.Scanner;

public class j04AnyBaseToAnyBase {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number in the source base
        int b1 = in.nextInt(); // Input: the source base
        int b2 = in.nextInt(); // Input: the target base

        // Output the number in the target base
        System.out.println(decimalToAnyBase(anyBaseToDecimal(n, b1), b2)); // Convert and print the result

        in.close(); // Close the scanner to release resources
    }

    /*-
     * Helper : Convert Any Base to Decimal
     * 
     * Intuition:
     * - This approach converts a number from any base `b1` (1 to 10) to its decimal (base 10) equivalent.
     * - The number is processed from right to left, and for each digit, we multiply it by the base raised to the appropriate power.
     * 
     * Time Complexity:
     * - O(m), where m is the length of the number string.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param n The input number in base `b1`.
     * @param b1 The source base.
     * @return The decimal (base 10) equivalent of the number.
     */
    public static int anyBaseToDecimal(int n, int b1) {
        int dec = 0; // Initialize the result to 0
        int temp = n; // Copy of the input number
        int i = 1; // Variable to hold powers of the base
        while (temp > 0) { // Iterate through the number
            int d = temp % 10; // Extract the rightmost digit
            dec = d * i + dec; // Update the decimal value
            temp /= 10; // Remove the rightmost digit
            i *= b1; // Multiply the base for the next place value
        }
        return dec; // Return the decimal equivalent
    }

    /*-
     * Helper : Convert Decimal to Any Base
     * 
     * Intuition:
     * - This approach converts a number from decimal (base 10) to any target base `b2` (1 to 10).
     * - The number is divided by the base, and the remainders are used to build the result.
     * - The result is constructed from right to left.
     * 
     * Time Complexity:
     * - O(log(n)), where n is the value of the input number.
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of extra space.
     * 
     * @param n The input number in decimal (base 10).
     * @param b The target base.
     * @return The number in the target base.
     */
    public static int decimalToAnyBase(int n, int b) {
        int out = 0; // Initialize the result to 0
        int i = 1; // Variable to hold powers of the base
        while (n > 0) { // Iterate through the number
            int d = n % b; // Extract the remainder
            out = d * i + out; // Update the result
            n /= b; // Reduce the number
            i *= 10; // Multiply the base for the next place value
        }
        return out; // Return the number in the target base
    }
}
