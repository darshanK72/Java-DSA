/**
 * Problem Statement:
 * 
 *     A **perfect number** is a positive integer that is equal to the sum of its proper divisors, excluding itself.
 *     For example, 28 is a perfect number because its divisors are 1, 2, 4, 7, and 14, and their sum is 28.
 * 
 *     The task is to determine if a given integer `n` is a perfect number.
 * 
 * Input:
 *     - A single integer `n` (1 <= n <= 10^8).
 * 
 * Output:
 *     - Output `true` if the number is perfect, and `false` otherwise.
 * 
 * Example:
 *     Input:
 *     28
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The divisors of 28 are [1, 2, 4, 7, 14], and their sum is 28.
 *     Therefore, 28 is a perfect number.
 */

import java.util.Scanner;

public class j20PerfectNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to check for perfection
        System.out.println(checkPerfectNumber(n)); // Output: call to method to check perfection
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach to Check if a Number is Perfect
     * 
     * Intuition:
     * - A number is perfect if the sum of its proper divisors (divisors excluding the number itself) is equal to the number.
     * - For each number from 1 to the square root of the given number, check if it is a divisor, and add both the divisor and quotient.
     * 
     * Time Complexity:
     * - O(√n), where `n` is the given number, as we only iterate up to the square root of `n` to find divisors.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used to store the sum and iterate through divisors.
     * 
     * @param num The number to check for perfection.
     * @return true if the number is perfect, false otherwise.
     */
    public static boolean checkPerfectNumber(int num) {
        // Initialize the sum of divisors (starting with 1, as 1 is always a divisor)
        int sum = 1;

        // If the number is 1, it is not a perfect number
        if (num == 1)
            return false;

        // Check divisors from 2 to the square root of num
        for (int i = 2; i * i <= num; i++) {
            // If i is a divisor of num
            if (num % i == 0) {
                sum += i; // Add i as a divisor
                sum += (num / i); // Add the quotient as a divisor (if different from i)
            }
        }

        // If the sum of divisors equals the number itself, it's a perfect number
        if (sum == num)
            return true;

        // Otherwise, it's not a perfect number
        return false;
    }
}
