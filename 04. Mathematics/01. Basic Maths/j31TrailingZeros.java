/**
 * Problem Statement:
 * 
 *     The task is to calculate the number of trailing zeros in the factorial of a number `n`.
 *     A trailing zero is produced when a number is divisible by 10, which is the product of 2 and 5.
 *     For any factorial, there are more factors of 2 than factors of 5, so the number of trailing zeros
 *     is determined by the number of times 5 is a factor in the numbers from 1 to `n`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number for which the factorial is to be calculated.
 * 
 * Output:
 *     - An integer representing the number of trailing zeros in the factorial of `n`.
 * 
 * Example:
 *     Input:
 *     100
 *     Output:
 *     24
 * 
 *     Explanation:
 *     The factorial of 100 contains 24 trailing zeros because there are 24 multiples of 5 (and higher powers of 5)
 *     in the numbers from 1 to 100.
 */

import java.util.Scanner;

// Complexity : O(Log5 N)

public class j31TrailingZeros {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        // Input: Integer n representing the number
        int n = in.nextInt();

        // Output the result
        System.out.println("Trailing zeros at end of " + n + "! are " + findTrailingZeros(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Iterative approach to count trailing zeros in factorial.
     * 
     * Intuition:
     * - Every multiple of 5 contributes to a trailing zero in the factorial.
     * - We count how many numbers from 1 to `n` are divisible by 5, 25, 125, etc.
     * - This helps determine how many times 5 is a factor in the numbers from 1 to `n`.
     * 
     * Time Complexity:
     * - O(log5 N), because we divide `n` by increasing powers of 5.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables for counting.
     * 
     * @param n The input number.
     * @return The number of trailing zeros in n!
     */
    public static int findTrailingZeros(int n) {
        int count = 0;

        // Loop to count multiples of powers of 5 (5, 25, 125, etc.)
        for (int i = 5; n / i >= 1; i *= 5) {
            count += n / i; // Add how many numbers are divisible by i (power of 5)
        }

        return count; // Return the total count of trailing zeros
    }
}
