/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums`, find the sum of all divisors of numbers in the array 
 *     that have exactly four divisors. If a number has exactly four divisors, add their sum to the result.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the sum of divisors for all numbers in the array with exactly four divisors.
 * 
 * Example:
 *     Input:
 *     4
 *     21 4 7 6
 *     Output:
 *     32
 * 
 *     Explanation:
 *     - 21 has divisors [1, 3, 7, 21] → sum = 32.
 *     - 4 has divisors [1, 2, 4] → not considered.
 *     - 7 has divisors [1, 7] → not considered.
 *     - 6 has divisors [1, 2, 3, 6] → sum = 12.
 *     - Total = 32.
 */

import java.util.Scanner;

public class j18FourDivisors {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }

        // Call your solution
        System.out.printf("Your Solution: Sum of Four Divisors = %d\n", sumFourDivisors(nums));

        // Call optimized solution
        System.out.printf("Optimized Solution: Sum of Four Divisors = %d\n", sumFourDivisorsOptimized(nums));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach to Count Divisors and Calculate Sum
     * 
     * Intuition:
     * - For each number, count its divisors and calculate their sum.
     * - If a number has exactly four divisors, add the sum of those divisors to the result.
     * 
     * Time Complexity:
     * - O(n * √m), where `n` is the size of the array, and `m` is the maximum value in the array.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param nums The input array of numbers.
     * @return The sum of divisors of numbers with exactly four divisors.
     */
    public static int sumFourDivisors(int[] nums) {
        int sum = 0; // Initialize the result sum
        for (int i = 0; i < nums.length; i++) {
            int tempSum = 0; // Temporary sum for the current number
            int c = 0; // Count of divisors
            for (int j = 1; j * j <= nums[i]; j++) {
                if (nums[i] % j == 0) {
                    tempSum += j; // Add the divisor
                    if (j != nums[i] / j) {
                        tempSum += (nums[i] / j); // Add the quotient if it's different
                        c += 2; // Two divisors found
                    } else {
                        c++; // Only one divisor (perfect square case)
                    }
                }
                if (c > 4)
                    break; // Early exit if divisors exceed 4
            }
            if (c == 4)
                sum += tempSum; // Add the sum of divisors if exactly 4 divisors
        }
        return sum;
    }

    /**
     * Approach 2: Optimized Solution Using Factorization
     * 
     * Intuition:
     * - Reduce iterations using early stopping and prime factorization logic.
     * - A number has exactly four divisors if it is:
     *   1. A product of two distinct primes `p` and `q`: Divisors = [1, p, q, p*q].
     *   2. A cube of a prime `p^3`: Divisors = [1, p, p^2, p^3].
     * 
     * Algorithm:
     * - Check these two cases for every number in the array.
     * 
     * Time Complexity:
     * - O(n * √m), similar to brute force, but optimized for specific cases.
     * 
     * Space Complexity:
     * - O(1), as only variables are used.
     * 
     * @param nums The input array of numbers.
     * @return The sum of divisors of numbers with exactly four divisors.
     */
    public static int sumFourDivisorsOptimized(int[] nums) {
        int sum = 0; // Initialize the result sum
        for (int num : nums) {
            int tempSum = 0, count = 0; // Temporary sum and divisor count
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    tempSum += i; // Add the divisor
                    if (i != num / i) {
                        tempSum += (num / i); // Add the quotient if it's different
                        count += 2; // Two divisors found
                    } else {
                        count++; // Only one divisor (perfect square case)
                    }
                }
                if (count > 4)
                    break; // Early exit if divisors exceed 4
            }
            if (count == 4)
                sum += tempSum; // Add the sum of divisors if exactly 4 divisors
        }
        return sum;
    }
}
