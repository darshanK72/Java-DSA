/**
 * Problem Statement:
 * 
 *     Given two non-negative integers `n1` and `n2`, represented as numbers in base `b`, 
 *     subtract `n2` from `n1` in base `b` and return the result. The subtraction is performed digit by digit 
 *     starting from the least significant digit, considering any necessary borrow (carry).
 * 
 * Input:
 *     - An integer `n1` (0 <= n1 <= 10^9), representing the first number.
 *     - An integer `n2` (0 <= n2 <= 10^9), representing the second number.
 *     - An integer `b` (2 <= b <= 10), the base in which the numbers are represented.
 * 
 * Output:
 *     - The result of `n1 - n2` in base `b` as an integer.
 * 
 * Example:
 *     Input:
 *     543 234 6
 *     Output:
 *     305
 * 
 *     Explanation:
 *     - First, the numbers 543 and 234 are in base 6, which are equivalent to 215 and 100 in base 10 respectively.
 *     - Subtracting 100 from 215 in base 10 gives 115.
 *     - The result 115 is then converted back to base 6, which is 305.
 */

import java.util.Scanner;

public class j09AnyBaseSubtraction {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: first number in base 'b'
        int n2 = in.nextInt(); // Input: second number in base 'b'
        int b = in.nextInt(); // Input: base in which the numbers are represented

        // Call the subtraction method and output result
        System.out.println(anyBaseSubtraction(n1, n2, b));

        in.close();
    }

    /**
     * Approach: Subtraction of two numbers in any base.
     * 
     * Intuition:
     * - We subtract the numbers digit by digit, similar to how we would do in base 10, but we handle borrowing 
     *   according to the specified base.
     * - For each digit, if the first digit is greater than the second, we subtract directly. If not, we borrow 
     *   from the next higher digit, adjusting accordingly.
     * - The result is constructed in reverse order, and we multiply each resulting digit by its place value 
     *   (which is a power of the base).
     * 
     * Time Complexity:
     * - O(log_b(n1) + log_b(n2)), where n1 and n2 are the input numbers. This is due to the fact that we process 
     *   each digit of the numbers once.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space (apart from the input and output).
     * 
     * @param n1 The first number to subtract.
     * @param n2 The second number to subtract.
     * @param b  The base in which the numbers are represented.
     * @return The result of n1 - n2 in base 'b'.
     */
    public static int anyBaseSubtraction(int n1, int n2, int b) {
        int ans = 0; // Store the result
        int carry = 0; // Store any borrow (carry)
        int i = 1; // Place value (units, tens, hundreds, etc.)

        while (n1 > 0 || n2 > 0 || carry > 0) {
            // Get the last digit of both numbers
            int d1 = n1 % 10;
            int d2 = n2 % 10;

            if (d1 > d2) {
                // No borrow needed, just subtract
                ans = (d1 - d2 - carry) * i + ans;
                carry = 0;
            } else {
                // Borrow needed, add base and subtract
                ans = (d1 + b - d2 - carry) * i + ans;
                carry = 1; // Set borrow flag
            }

            i *= 10; // Move to the next place value
            n1 /= 10; // Remove last digit of n1
            n2 /= 10; // Remove last digit of n2
        }

        return ans; // Return the result
    }
}
