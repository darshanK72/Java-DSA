/**
 * Problem Statement:
 * 
 *     An ugly number is a number whose prime factors only include 2, 3, or 5. 
 *     Given an integer `n`, return true if `n` is an ugly number, or false otherwise.
 *     An ugly number is a number whose only prime factors are 2, 3, or 5.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 2^31 - 1).
 * 
 * Output:
 *     - Return true if `n` is an ugly number; otherwise, return false.
 * 
 * Example:
 *     Input:
 *     6
 *     Output:
 *     true
 * 
 *     Explanation:
 *     6 has only prime factors of 2 and 3, so it is an ugly number.
 * 
 *     Input:
 *     14
 *     Output:
 *     false
 * 
 *     Explanation:
 *     14 has a prime factor of 7, which is not 2, 3, or 5, so it is not an ugly number.
 */

import java.util.Scanner;

public class j11UglyNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to check if it's an ugly number
        System.out.println(isUgly(n)); // Output the result of the function
        in.close();
    }

    /**
     * Approach: Iterative division by prime factors
     * 
     * Intuition:
     * - An ugly number is only divisible by the prime factors 2, 3, and 5.
     * - We start with the number `n`, and we iteratively divide it by 2, 3, and 5 as long as possible.
     * - If we are left with 1, then it means the number was composed only of these prime factors, 
     *   and hence it is an ugly number. Otherwise, it is not.
     * 
     * Time Complexity:
     * - O(log n), since at each step we divide `n` by 2, 3, or 5.
     * 
     * Space Complexity:
     * - O(1), as we only need a constant amount of space for our operations.
     * 
     * @param n The input number to check.
     * @return true if `n` is an ugly number, otherwise false.
     */
    public static boolean isUgly(int n) {
        // Edge case: 0 is not considered an ugly number
        if (n == 0)
            return false;

        // Divide by 2, 3, and 5 as long as possible
        while (n % 2 == 0) {
            n /= 2;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        while (n % 5 == 0) {
            n /= 5;
        }

        // If the resulting number is 1, it means it was divisible only by 2, 3, and 5
        return n == 1;
    }
}
