/**
 * Problem Statement:
 * 
 *     Given an integer `num`, we perform two reversals on the number. First, we reverse the digits of `num`, 
 *     and then we reverse the digits of the result of the first reversal. The task is to check if the original number 
 *     is equal to the number obtained after these two reversals.
 * 
 * Input:
 *     - An integer `num` (0 <= num <= 10^6), representing the input number.
 * 
 * Output:
 *     - A boolean value indicating whether the original number is equal to the number obtained after two reversals.
 * 
 * Example:
 *     Input:
 *     526
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - Reversing 526 gives 625.
 *     - Reversing 625 gives 526, which is the same as the original number.
 *     Therefore, the output is `true`.
 */

import java.util.Scanner;

public class j08DoubleReversal {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input number n
        // Call the solution methods and print the results
        System.out.println(isSameAfterReversals(n));
        System.out.println(isSameAfterReversalsEfficient(n));
        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Double Reversal (Naive Approach)
     * 
     * Intuition:
     * - Reverse the number twice and compare it with the original number. 
     * - We reverse the number, then reverse the reversed number and check if it matches the original number.
     * 
     * Time Complexity:
     * - O(log10 N), as we reverse the number twice (each reversal takes logarithmic time based on the number of digits).
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space to store the reversed numbers.
     * 
     * @param num The input number.
     * @return `true` if the number is the same after two reversals, `false` otherwise.
     */
    public static boolean isSameAfterReversals(int num) {
        int rev1 = 0;
        int rev2 = 0;
        int x = num;

        // First reversal
        while (x > 0) {
            rev1 = rev1 * 10 + x % 10;
            x /= 10;
        }

        x = rev1; // Use the reversed number for the second reversal
        // Second reversal
        while (x > 0) {
            rev2 = rev2 * 10 + x % 10;
            x /= 10;
        }

        // Compare the final result with the original number
        return num == rev2;
    }

    /**
     * Approach 2: Optimized Solution
     * 
     * Intuition:
     * - We can avoid the second reversal entirely. The number will be equal to its double-reversal if:
     *   1. The number is zero (0) because reversing zero gives zero.
     *   2. The number is not divisible by 10, because reversing a number that ends in 0 will make the
     *      reversal different (e.g., 120 becomes 21, and reversing 21 again gives 12).
     * 
     * Time Complexity:
     * - O(1), since we only check a simple condition on the number.
     * 
     * Space Complexity:
     * - O(1), constant space for storing the result of the check.
     * 
     * @param num The input number.
     * @return `true` if the number is the same after two reversals, `false` otherwise.
     */
    public static boolean isSameAfterReversalsEfficient(int num) {
        // The number is the same after two reversals if it's zero or doesn't end in zero
        return num == 0 || num % 10 != 0;
    }
}
