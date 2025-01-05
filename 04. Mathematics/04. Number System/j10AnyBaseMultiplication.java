/**
 * Problem Statement:
 * 
 *     Given two non-negative integers `n1` and `n2`, represented as numbers in base `b`, 
 *     perform the multiplication of `n1` and `n2` in base `b` and return the result. 
 *     The multiplication is performed digit by digit, similar to how multiplication is done in base 10.
 *     The final result should also be returned in base `b`.
 * 
 * Input:
 *     - An integer `n1` (0 <= n1 <= 10^9), representing the first number.
 *     - An integer `n2` (0 <= n2 <= 10^9), representing the second number.
 *     - An integer `b` (2 <= b <= 10), the base in which the numbers are represented.
 * 
 * Output:
 *     - The result of multiplying `n1` and `n2` in base `b` as an integer in base `b`.
 * 
 * Example:
 *     Input:
 *     123 456 6
 *     Output:
 *     101104
 * 
 *     Explanation:
 *     - First, the numbers 123 and 456 are in base 6, which are equivalent to 51 and 174 in base 10 respectively.
 *     - Multiplying 51 and 174 in base 10 gives 8874.
 *     - The result 8874 is then converted back to base 6, which is 101104.
 */

import java.util.Scanner;

public class j10AnyBaseMultiplication {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: first number in base 'b'
        int n2 = in.nextInt(); // Input: second number in base 'b'
        int b = in.nextInt(); // Input: base in which the numbers are represented

        // Call the multiplication method and output result
        System.out.println(anyBaseMultiplication(n1, n2, b));

        in.close();
    }

    /**
     * Approach: Multiplication of two numbers in any base.
     * 
     * Intuition:
     * - This approach multiplies two numbers digit by digit, similar to how we multiply numbers in base 10.
     * - For each digit of the second number, we multiply it by the entire first number and accumulate the results.
     * - Addition of intermediate results is done using the `anyBaseAddition` function, which ensures that the result 
     *   is in the specified base.
     * 
     * Time Complexity:
     * - O(log_b(n1) * log_b(n2)), where n1 and n2 are the input numbers. The inner loop processes each digit of n2 
     *   and for each, we multiply with all digits of n1.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space (apart from the input and output).
     * 
     * @param n1 The first number to multiply.
     * @param n2 The second number to multiply.
     * @param b  The base in which the numbers are represented.
     * @return The result of n1 * n2 in base 'b'.
     */
    public static int anyBaseMultiplication(int n1, int n2, int b) {
        int finalAns = 0; // Final result of multiplication
        int k = 1; // Place value multiplier (units, tens, hundreds, etc.)

        // Loop through each digit of n2
        while (n2 > 0) {
            int ans = 0; // Temporary result of multiplying n1 with current digit of n2
            int carry = 0; // Carry for multiplication
            int i = 1; // Place value for temporary result
            int d = n2 % 10; // Get the last digit of n2
            int temp = n1; // Copy of n1 for multiplication

            // Multiply n1 by the current digit of n2
            while (temp > 0 || carry > 0) {
                int d1 = temp % 10;
                ans = ((d * d1 + carry) % b) * i + ans; // Compute current digit of result
                carry = (d * d1 + carry) / b; // Update carry
                temp /= 10; // Remove last digit of n1
                i *= 10; // Move to the next place value
            }

            // Add the intermediate result to the final result
            finalAns = anyBaseAddition(finalAns, ans * k, b);
            n2 /= 10; // Remove last digit of n2
            k *= 10; // Increase place value for the next iteration
        }

        return finalAns; // Return the final multiplication result
    }

    /**
     * Approach: Addition of two numbers in any base.
     * 
     * Intuition:
     * - This function adds two numbers digit by digit in base `b`, handling any carry that may arise.
     * - Similar to how addition is done in base 10, but the carry and digit computation are done modulo the base.
     * 
     * Time Complexity:
     * - O(log_b(n1) + log_b(n2)), where n1 and n2 are the input numbers. We process each digit of the numbers once.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space (apart from the input and output).
     * 
     * @param n1 The first number to add.
     * @param n2 The second number to add.
     * @param b  The base in which the numbers are represented.
     * @return The result of n1 + n2 in base 'b'.
     */
    public static int anyBaseAddition(int n1, int n2, int b) {
        int ans = 0; // Store the result of the addition
        int carry = 0; // Carry from the addition
        int i = 1; // Place value (units, tens, hundreds, etc.)

        while (n1 > 0 || n2 > 0 || carry > 0) {
            // Get the last digit of both numbers
            int d1 = n1 % 10;
            int d2 = n2 % 10;
            ans = ((d1 + d2 + carry) % b) * i + ans; // Compute current digit of result
            carry = (d1 + d2 + carry) / b; // Update carry
            i *= 10; // Move to the next place value
            n1 /= 10; // Remove last digit of n1
            n2 /= 10; // Remove last digit of n2
        }

        return ans; // Return the sum
    }
}
