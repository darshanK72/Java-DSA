/*-
 * Problem Statement:
 * 
 *     Given a string `n` representing a number in any base `b` (1 <= b <= 16), convert it to a decimal (base 10) number and return the result.
 * 
 * Input:
 *     - A string `n` representing a number in base `b`.
 *     - An integer `b` (1 <= b <= 16) representing the base of the number `n`.
 * 
 * Output:
 *     - The decimal (base 10) equivalent of the input number `n`.
 * 
 * Example:
 *     Input:
 *     A 16
 *     Output:
 *     10
 * 
 *     Explanation:
 *     The number "A" in base 16 is equivalent to the decimal number 10.
 */

import java.util.Scanner;

public class j03AnyBaseToDecimal {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String n = in.next(); // Input: the number in the given base
        int b = in.nextInt(); // Input: the base of the number

        // Output using the efficient conversion method
        System.out.println(anyBaseToDecimalEfficient(n, b)); // Convert and print the result

        in.close(); // Close the scanner to release resources
    }

    /*-
     * Approach: Convert Any Base to Decimal (Efficient Method)
     * 
     * Intuition:
     * - This approach converts a number in any base (1 to 16) to its decimal (base 10) equivalent.
     * - The number is processed from right to left, and for each digit, we multiply it by the base raised to the appropriate power.
     * - Characters 'A' to 'F' are handled as digits with values from 10 to 15 for base 16.
     * 
     * Time Complexity:
     * - O(m), where m is the length of the number string.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param n The input number in base `b` as a string.
     * @param b The base of the input number.
     * @return The decimal (base 10) equivalent of the number.
     */
    public static long anyBaseToDecimalEfficient(String n, int b) {
        long ans = 0; // Initialize the result to 0
        int base = 1; // Variable to hold powers of the base
        for (int i = n.length() - 1; i >= 0; i--) { // Iterate through the string from right to left
            if (n.charAt(i) >= 'A' && n.charAt(i) <= 'F') { // If the character is between 'A' and 'F' (for base 16)
                ans = (10 + n.charAt(i) - 'A') * base + ans; // Convert character to corresponding value
            } else { // Otherwise, it's a digit between '0' and '9'
                ans = (n.charAt(i) - '0') * base + ans; // Convert character to numeric value
            }
            base *= b; // Multiply the base by the base for the next place value
        }
        return ans; // Return the decimal equivalent
    }
}
