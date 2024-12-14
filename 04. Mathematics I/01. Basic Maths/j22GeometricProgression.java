/**
 * Problem Statement:
 * 
 *     Given the first term `a`, the number of terms `n`, and the common ratio `r` of a Geometric Progression (GP),
 *     the task is to:
 *     1. Print the first `n` terms of the GP.
 *     2. Find and print the `n`th term of the GP.
 *     3. Calculate and print the sum of the first `n` terms of the GP.
 * 
 * Input:
 *     - Three integers `a`, `n`, and `r`, where:
 *         - `a` is the first term of the GP.
 *         - `n` is the number of terms to print.
 *         - `r` is the common ratio between consecutive terms.
 * 
 * Output:
 *     - The series of `n` terms of the GP.
 *     - The `n`th term of the GP.
 *     - The sum of the first `n` terms of the GP.
 * 
 * Example:
 *     Input:
 *     2 5 3
 *     Output:
 *     Seriese : 2 6 18 54 162 
 *     Nth Term : 1458.0
 *     Sum of N Terms : 242.0
 */

import java.util.Scanner;

public class j22GeometricProgression {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(); // First term of the GP
        int n = in.nextInt(); // Number of terms
        int r = in.nextInt(); // Common ratio

        // Call methods to print series, find nth term, and sum of terms
        printGp(a, n, r); // Print the GP series
        System.out.println("Nth Term : " + getNthTerm(a, 52, r)); // Find and print the 52nd term
        System.out.println("Sum of N Terms : " + getSumOfNTerms(a, 23, r)); // Find and print the sum of first 23 terms

        // Close the scanner
        in.close();
    }

    /**
     * Problem 1: Print the first `n` terms of the Geometric Progression (GP)
     * 
     * Intuition:
     * - The nth term of a GP is calculated by multiplying the previous term by the common ratio `r`.
     * - By using this formula iteratively, we can print the first `n` terms.
     * 
     * Time Complexity:
     * - O(n), as we iterate over the terms to print them.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables to store values.
     * 
     * @param a The first term of the GP.
     * @param n The number of terms to print.
     * @param r The common ratio between consecutive terms.
     */
    public static void printGp(int a, int n, int r) {
        System.out.print("Seriese : ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " "); // Print the current term
            a *= r; // Calculate the next term by multiplying by the common ratio
        }
        System.out.println(); // Move to the next line after printing the series
    }

    /**
     * Problem 2: Get the nth term of the Geometric Progression (GP)
     * 
     * Intuition:
     * - The nth term of a GP can be calculated using the formula: `nth term = a * r^(n-1)`.
     * 
     * Time Complexity:
     * - O(1), as it's a simple formula calculation.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables for calculation.
     * 
     * @param a The first term of the GP.
     * @param n The term number (nth term).
     * @param r The common ratio.
     * @return The nth term of the GP.
     */
    public static double getNthTerm(int a, int n, int r) {
        return a * Math.pow(r, n - 1); // Formula to find the nth term
    }

    /**
     * Problem 3: Get the sum of the first `n` terms of the Geometric Progression (GP)
     * 
     * Intuition:
     * - The sum of the first `n` terms of a GP can be calculated using the formula: 
     *   `Sum = a * (1 - r^n) / (1 - r)` for `r < 1` and `Sum = a * (r^n - 1) / (r - 1)` for `r > 1`.
     *   These formulas efficiently calculate the sum based on the ratio `r`.
     * 
     * Time Complexity:
     * - O(1), as the sum is directly calculated using the formula.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables for the sum calculation.
     * 
     * @param a The first term of the GP.
     * @param n The number of terms to sum.
     * @param r The common ratio.
     * @return The sum of the first `n` terms.
     */
    public static double getSumOfNTerms(int a, int n, int r) {
        if (r == 1) {
            return a * n; // Special case when r = 1
        } else if (r < 1) {
            return (a * (1 - Math.pow(r, n))) / (1 - r); // Sum formula for r < 1
        } else {
            return (a * (Math.pow(r, n) - 1)) / (r - 1); // Sum formula for r > 1
        }
    }
}
