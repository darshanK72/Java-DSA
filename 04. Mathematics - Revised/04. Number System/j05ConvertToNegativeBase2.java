/**
 * Problem Statement:
 * 
 *     Given an integer n, convert it to its representation in base -2 (negative base).
 *     The conversion should be implemented such that the output is a valid representation in base -2.
 * 
 * Input:
 *     - An integer `n` (-10^9 <= n <= 10^9).
 * 
 * Output:
 *     - A string representing the number in base -2.
 * 
 * Example:
 *     Input:
 *     2
 *     Output:
 *     "110"
 * 
 *     Explanation:
 *     2 in base -2 is "110".
 */

import java.util.Scanner;

public class j05ConvertToNegativeBase2 {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to be converted to base -2

        // Call your solution
        System.out.println(baseNeg2(n)); // Output: the number in base -2

        in.close(); // Close the scanner to release resources
    }

    /**
     * Approach 1: Convert a number to base -2
     * 
     * Intuition:
     * - We continuously divide the number by -2, adjusting the remainder when necessary to ensure it is non-negative.
     * - If the remainder is negative, we add 2 to it and increment the quotient by 1.
     * 
     * Time Complexity:
     * - O(log(n)), as we are dividing the number by 2 each time.
     * 
     * Space Complexity:
     * - O(log(n)), as we are storing the result in a string.
     * 
     * @param n The integer to be converted to base -2.
     * @return A string representing the number in base -2.
     */
    public static String baseNeg2(int n) {
        if (n == 0)
            return "0"; // Special case for 0
        StringBuilder output = new StringBuilder();
        while (n != 0) {
            int d = n % (-2); // Find remainder
            n /= (-2); // Divide by -2
            if (d < 0) {
                d += 2; // Adjust if remainder is negative
                n++; // Increment quotient to balance the adjustment
            }
            output.insert(0, d); // Add the digit to the output
        }
        return output.toString(); // Return the result as a string
    }
}
