/**
 * Problem Statement:
 * 
 *     Given an integer `n`, check if `n` can be represented as the sum of three consecutive integers.
 *     - If it is possible, return the three consecutive integers.
 *     - If it is not possible, return an empty array.
 * 
 *     Specifically, check if `n = x - 1 + x + x + 1` for some integer `x`.
 * 
 * Input:
 *     - A long integer `n` (1 <= n <= 10^9).
 * 
 * Output:
 *     - If `n` can be expressed as the sum of three consecutive integers, return the integers in an array: `[x - 1, x, x + 1]`.
 *     - Otherwise, return an empty array.
 * 
 * Example:
 *     Input:
 *     9
 *     Output:
 *     [2, 3, 4]
 * 
 *     Explanation:
 *     The sum of three consecutive integers is `2 + 3 + 4 = 9`. Hence, the output is `[2, 3, 4]`.
 * 
 *     Input:
 *     10
 *     Output:
 *     []
 * 
 *     Explanation:
 *     It is not possible to represent 10 as the sum of three consecutive integers.
 */

import java.util.Scanner;

public class j10ThreeConsecutiveIntSum {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        long n = in.nextLong(); // Input: the number to check if it can be represented as sum of 3 consecutive
                                // integers
        System.out.println(sumOfThree(n)); // Output the result of the function
        in.close();
    }

    /**
     * Approach: Direct check using modulus and division
     * 
     * Intuition:
     * - For the number `n` to be the sum of three consecutive integers `x - 1`, `x`, and `x + 1`,
     *   it must be divisible by 3 (i.e., `n % 3 == 0`).
     * - If `n % 3 == 0`, then we can express `n = x - 1 + x + x + 1` as `n = 3x`.
     * - So, `x = n / 3` and the three consecutive integers will be `x - 1`, `x`, and `x + 1`.
     * - If `n % 3 != 0`, it is impossible to represent `n` as the sum of three consecutive integers, 
     *   and we return an empty array.
     * 
     * Time Complexity:
     * - O(1), as the solution only involves a modulus operation and a division.
     * 
     * Space Complexity:
     * - O(1), as we only return a fixed-size array (either the triplet or an empty array).
     * 
     * @param num The number to check.
     * @return An array containing the three consecutive integers, or an empty array if not possible.
     */
    public static long[] sumOfThree(long num) {
        // Check if the number is divisible by 3
        if (num % 3 != 0) {
            // If not, return an empty array
            return new long[] {};
        }
        // Calculate x as the quotient of num divided by 3
        long x = num / 3;
        // Return the consecutive integers
        return new long[] { x - 1, x, x + 1 };
    }
}
