/**
 * Problem Statement:
 * 
 *     Given two numbers `n1` and `n2` in a given base `b`, find their sum and return it in the same base `b`.
 *     You need to handle the addition of numbers in bases ranging from 1 to 10.
 * 
 * Input:
 *     - Three integers `n1`, `n2`, and `b`, where `n1` and `n2` are numbers in base `b` and `b` is the base of the numbers (1 <= b <= 10).
 * 
 * Output:
 *     - The sum of `n1` and `n2` in base `b`.
 * 
 * Example:
 *     Input:
 *     23 45 6
 *     Output:
 *     111
 * 
 *     Explanation:
 *     23 in base 6 is 2*6 + 3 = 15, and 45 in base 6 is 4*6 + 5 = 29.
 *     Adding 15 + 29 = 44 in decimal, which is 1*6^2 + 1*6^1 + 1*6^0 = 111 in base 6.
 */

import java.util.Scanner;

public class j06AnyBaseAddition {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // First number in base `b`
        int n2 = in.nextInt(); // Second number in base `b`
        int b = in.nextInt(); // The base `b`

        // Call your solution and print the result
        System.out.println(anyBaseAddition(n1, n2, b)); // Output: the sum in base `b`

        in.close(); // Close the scanner to release resources
    }

    /**
     * Approach 1: Add numbers in any base
     * 
     * Intuition:
     * - The algorithm works by performing digit-by-digit addition (like how we add numbers manually).
     * - We extract the last digit of both numbers using modulus (`%`), add them together along with any carry, and store the result.
     * - We propagate the carry and move to the next digit by dividing by `b` and adjusting accordingly.
     * 
     * Time Complexity:
     * - O(max(digits(n1), digits(n2))), where `digits(n)` is the number of digits in the base `b` representation of `n`.
     * 
     * Space Complexity:
     * - O(1), as we are using only a few extra variables for computation.
     * 
     * @param n1 The first number in base `b`.
     * @param n2 The second number in base `b`.
     * @param b The base `b` of the numbers.
     * @return The sum of `n1` and `n2` in base `b`.
     */
    public static int anyBaseAddition(int n1, int n2, int b) {
        int ans = 0; // To store the result of the addition
        int carry = 0; // To store the carry during addition

        int i = 1; // Multiplier for positioning the digits correctly in the result
        while (n1 > 0 || n2 > 0 || carry > 0) { // Loop while there are digits or carry
            int d1 = n1 % 10; // Extract the last digit of n1
            int d2 = n2 % 10; // Extract the last digit of n2
            ans = ((d1 + d2 + carry) % b) * i + ans; // Add digits and carry, then add to result
            carry = (d1 + d2 + carry) / b; // Calculate the new carry
            i *= 10; // Move to the next place value (units, tens, etc.)
            n1 /= 10; // Remove the last digit of n1
            n2 /= 10; // Remove the last digit of n2
        }
        return ans; // Return the final sum in base `b`
    }
}
