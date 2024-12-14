/**
 * Problem Statement:
 * 
 *     Given a number `n`, count all the Pythagorean triplets `(a, b, c)` such that:
 *     - `a^2 + b^2 = c^2`
 *     - `1 <= a, b, c <= n`
 * 
 *     A Pythagorean triplet is a set of three positive integers that fit the condition `a^2 + b^2 = c^2`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), the maximum value for `a`, `b`, and `c`.
 * 
 * Output:
 *     - The number of Pythagorean triplets `(a, b, c)` where `a^2 + b^2 = c^2` and all values are less than or equal to `n`.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The two triplets are:
 *     - (3, 4, 5)
 *     - (4, 3, 5)
 * 
 *     These are valid Pythagorean triplets as 3^2 + 4^2 = 5^2.
 */

import java.util.Scanner;

public class j09CountPythaghorianTriplets {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the maximum value for a, b, and c
        System.out.println(countTriples(n)); // Output the count of Pythagorean triplets
        in.close();
    }

    /**
     * Approach: Brute force to find all pairs (a, b) and check if a corresponding c exists
     * 
     * Intuition:
     * - We iterate through all pairs of integers `i` and `j` where 1 <= i, j <= n.
     * - For each pair (i, j), calculate `i^2 + j^2`, check if this sum is a perfect square.
     * - If it is a perfect square and its square root is less than or equal to `n`, count it as a valid Pythagorean triplet.
     * 
     * Time Complexity:
     * - O(n^2), as we are iterating over all pairs (i, j) where 1 <= i, j <= n.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables for counting.
     * 
     * @param n The maximum value for `a`, `b`, and `c`.
     * @return The count of valid Pythagorean triplets.
     */
    public static int countTriples(int n) {
        int c = 0; // Variable to hold the count of valid Pythagorean triplets
        for (int i = 1; i <= n; i++) { // Loop through all values for `i` (a)
            for (int j = 1; j <= n; j++) { // Loop through all values for `j` (b)
                int sq = i * i + j * j; // Calculate the sum of squares
                double sqr = Math.sqrt(sq); // Take the square root of the sum
                if (sq <= n * n && Math.floor(sqr) == sqr) // Check if the sum is a perfect square and its square root
                                                           // is valid
                    c++; // Increment the count for valid triplets
            }
        }
        return c; // Return the total count of valid triplets
    }

    /**
     * Approach 2: Optimized approach using integer check for triplets
     * 
     * Intuition:
     * - We iterate over possible values for `c` and calculate the possible values for `a` and `b` such that `a^2 + b^2 = c^2`.
     * - This reduces the number of iterations needed to find the triplets.
     * 
     * Time Complexity:
     * - O(n^2), but the approach may be more efficient depending on the number of perfect squares checked.
     * 
     * Space Complexity:
     * - O(1).
     * 
     * @param n The maximum value for `a`, `b`, and `c`.
     * @return The count of valid Pythagorean triplets.
     */
    public static int optimizedCountTriples(int n) {
        int c = 0; // Variable to hold the count of valid Pythagorean triplets
        for (int cVal = 1; cVal <= n; cVal++) { // Iterate over possible values for c
            for (int a = 1; a < cVal; a++) { // For each `c`, iterate over possible values for `a`
                for (int b = a; b < cVal; b++) { // Iterate over possible values for `b`
                    if (a * a + b * b == cVal * cVal) { // Check if a^2 + b^2 = c^2
                        c++; // Increment the count for valid triplets
                    }
                }
            }
        }
        return c; // Return the total count of valid triplets
    }
}
