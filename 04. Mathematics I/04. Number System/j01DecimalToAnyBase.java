/*-
 * Problem Statement:
 * 
 *     Given an integer `n`, convert it to any base `b` (where 2 <= b <= 36). The output should be the number `n` represented in the specified base `b`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9) representing the number to be converted.
 *     - An integer `b` (2 <= b <= 36) representing the base to which the number `n` should be converted.
 * 
 * Output:
 *     - A string representing the number `n` in the base `b`.
 * 
 * Example:
 *     Input:
 *     255 16
 *     Output:
 *     FF
 * 
 *     Explanation:
 *     The number 255 in base 16 (hexadecimal) is represented as "FF".
 */

import java.util.Scanner;

public class j01DecimalToAnyBase {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to be converted
        int b = in.nextInt(); // Input: the base to convert the number to

        // Print the result of both methods
        System.out.println(decimalToAnyBase(n, b)); // Output using first method
        System.out.println(decimalToAnyBaseEfficient(n, b)); // Output using second, more efficient method

        in.close(); // Close the scanner to release resources
    }

    /*-
     * Approach 1: Convert Decimal to Any Base (Base 1 to 10)
     * 
     * Intuition:
     * - This approach converts the decimal number to any base between 1 to 10 using a straightforward division-based method.
     * - We keep dividing the number by the base and accumulate the remainders to construct the final result.
     * 
     * Time Complexity:
     * - O(log n) since each division reduces the number by a factor of the base.
     * 
     * Space Complexity:
     * - O(1) as only a few variables are used.
     * 
     * @param n The decimal number to convert.
     * @param b The base to convert to (1 to 10).
     * @return The number in base `b` as a long integer.
     */
    public static long decimalToAnyBase(int n, int b) {
        long out = 0; // Initialize the output variable
        int i = 1; // Variable to multiply with to form the number in base `b`
        while (n > 0) { // Keep dividing the number by base `b`
            int d = n % b; // Get the remainder, which is the digit in base `b`
            out = d * i + out; // Add the digit to the result
            n /= b; // Divide the number by base `b`
            i *= 10; // Update the place value for the next digit
        }
        return out; // Return the result
    }

    /*-
     * Approach 2: Efficient Conversion to Any Base (Base 2 to 36)
     * 
     * Intuition:
     * - This approach uses a string to construct the result, and it can handle any base from 2 to 36.
     * - If the remainder is less than 10, it appends a digit; otherwise, it appends a character from 'A' to 'Z' for remainders 10 to 35.
     * - The result is reversed at the end to get the correct order.
     * 
     * Time Complexity:
     * - O(log n) due to division by the base.
     * 
     * Space Complexity:
     * - O(log n) for the StringBuilder used to store the result.
     * 
     * @param n The decimal number to convert.
     * @param b The base to convert to (2 to 36).
     * @return The number in base `b` as a string.
     */
    public static String decimalToAnyBaseEfficient(int n, int b) {
        StringBuilder ans = new StringBuilder(""); // Initialize the StringBuilder to hold the result
        while (n != 0) { // Keep dividing the number by base `b`
            int rem = n % b; // Get the remainder
            if (rem < 10)
                ans.append((char) ('0' + rem)); // Append a digit for remainders less than 10
            else
                ans.append((char) ('A' + rem - 10)); // Append a letter for remainders 10 or greater
            n /= b; // Divide the number by base `b`
        }
        return ans.reverse().toString(); // Reverse the result and return as a string
    }
}
