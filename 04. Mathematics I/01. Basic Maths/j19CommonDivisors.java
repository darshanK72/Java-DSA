/**
 * Problem Statement:
 * 
 *     Given two integers `a` and `b`, find the number of common divisors between the two numbers.
 *     A divisor of a number is any integer that divides the number exactly (without remainder). 
 *     The task is to find how many numbers are divisors of both `a` and `b`.
 * 
 * Input:
 *     - Two integers `a` and `b` (1 <= a, b <= 10^9).
 * 
 * Output:
 *     - An integer representing the number of common divisors of `a` and `b`.
 * 
 * Example:
 *     Input:
 *     12 18
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The divisors of 12 are [1, 2, 3, 4, 6, 12].
 *     The divisors of 18 are [1, 2, 3, 6, 9, 18].
 *     The common divisors are [1, 2, 3, 6].
 *     Therefore, the output is 4.
 */

import java.util.Scanner;

public class j19CommonDivisors {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        long a = in.nextLong(); // Input: first integer a
        long b = in.nextLong(); // Input: second integer b
        System.out.println(commDiv(a, b)); // Output: call to the method to compute common divisors
        in.close();
    }

    /**
     * Approach 1: Brute Force to Find Common Divisors
     * 
     * Intuition:
     * - For each integer from 1 to the minimum of a and b, check if it divides both a and b.
     * - If it divides both numbers, count it as a common divisor.
     * 
     * Time Complexity:
     * - O(min(a, b)), where a and b are the input integers. We iterate through numbers from 1 to the smaller of a or b.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used for counting and iterating.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The number of common divisors between a and b.
     */
    public static long commDiv(long a, long b) {
        long c = 0; // Initialize counter for common divisors
        for (long i = 1; i <= Math.min(a, b); i++) { // Loop through numbers from 1 to min(a, b)
            if (a % i == 0 && b % i == 0) { // If i divides both a and b
                c++; // Increment the common divisor count
            }
        }
        return c; // Return the count of common divisors
    }

    /**
     * Approach 2: Optimized Approach Using GCD (Greatest Common Divisor)
     * 
     * Intuition:
     * - Instead of checking each number from 1 to the minimum of a and b, we can use the fact that the common divisors
     *   of two numbers are exactly the divisors of their GCD.
     * - We first compute the GCD of a and b, then find all divisors of the GCD.
     * 
     * Time Complexity:
     * - O(√gcd(a, b)), where gcd(a, b) is the greatest common divisor of a and b.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used for GCD and divisors.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The number of common divisors between a and b.
     */
    public static long commDivOptimized(long a, long b) {
        long gcd = gcd(a, b); // Find the greatest common divisor of a and b
        long c = 0; // Initialize counter for divisors of the GCD
        for (long i = 1; i * i <= gcd; i++) { // Loop through numbers from 1 to sqrt(gcd)
            if (gcd % i == 0) { // If i divides gcd
                c++; // Count i as a divisor
                if (i != gcd / i) { // If i is not the square root of gcd, count the other divisor
                    c++;
                }
            }
        }
        return c; // Return the count of common divisors
    }

    /**
     * Helper method to compute the greatest common divisor (GCD) using Euclidean algorithm.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The greatest common divisor of a and b.
     */
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a; // Return the GCD
    }
}
