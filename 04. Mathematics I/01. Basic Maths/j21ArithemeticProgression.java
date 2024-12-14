/**
 * Problem Statement:
 * 
 *     Given the first term, number of terms, and the common difference of an Arithmetic Progression (AP),
 *     the task is to:
 *     1. Print the first `n` terms of the AP.
 *     2. Find and print the `n`th term of the AP.
 *     3. Calculate and print the sum of the first `n` terms of the AP.
 * 
 * Input:
 *     - Three integers `a`, `n`, and `d`, where:
 *         - `a` is the first term of the AP.
 *         - `n` is the number of terms to print.
 *         - `d` is the common difference between consecutive terms.
 * 
 * Output:
 *     - The series of `n` terms of the AP.
 *     - The `n`th term of the AP.
 *     - The sum of the first `n` terms of the AP.
 * 
 * Example:
 *     Input:
 *     5 10 3
 *     Output:
 *     Series : 5 8 11 14 17 20 23 26 29 32 
 *     Nth Term : 156
 *     Sum of N Terms : 155
 */

import java.util.Scanner;

public class j21ArithemeticProgression {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(); // First term of the AP
        int n = in.nextInt(); // Number of terms
        int d = in.nextInt(); // Common difference

        // Call methods to print series, find nth term, and sum of terms
        printAP(a, n, d); // Print the AP series
        System.out.println("Nth Term : " + getNthTerm(a, 52, d)); // Find and print the 52nd term
        System.out.println("Sum of N Terms : " + getSumOfNTerms(a, 23, d)); // Find and print the sum of first 23 terms

        // Close the scanner
        in.close();
    }

    /**
     * Problem 1: Print the first `n` terms of the Arithmetic Progression (AP)
     * 
     * Intuition:
     * - The nth term of an AP is calculated as `a + (n - 1) * d`. By using this formula iteratively, we can print the first `n` terms.
     * 
     * Time Complexity:
     * - O(n), as we iterate over the terms to print them.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables to store values.
     * 
     * @param a The first term of the AP.
     * @param n The number of terms to print.
     * @param d The common difference between consecutive terms.
     */
    public static void printAP(int a, int n, int d) {
        System.out.print("Series : ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " "); // Print the current term
            a += d; // Calculate the next term
        }
        System.out.println(); // Move to the next line after printing the series
    }

    /**
     * Problem 2: Get the nth term of the Arithmetic Progression (AP)
     * 
     * Intuition:
     * - The nth term of an AP can be calculated using the formula: `nth term = a + (n - 1) * d`.
     * 
     * Time Complexity:
     * - O(1), as the formula is directly evaluated without loops.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables for calculation.
     * 
     * @param a The first term of the AP.
     * @param n The term number (nth term).
     * @param d The common difference.
     * @return The nth term of the AP.
     */
    public static int getNthTerm(int a, int n, int d) {
        return a + (n - 1) * d; // Formula to find the nth term
    }

    /**
     * Approach 3: Get the sum of the first `n` terms of the Arithmetic Progression (AP)
     * 
     * Intuition:
     * - The sum of the first `n` terms of an AP can be calculated using the formula: 
     *   `Sum = (n / 2) * (2a + (n - 1) * d)`.
     *   This formula computes the sum efficiently without needing to loop through the terms.
     * 
     * Time Complexity:
     * - O(1), as the sum is directly calculated using the formula.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables for the sum calculation.
     * 
     * @param a The first term of the AP.
     * @param n The number of terms to sum.
     * @param d The common difference.
     * @return The sum of the first `n` terms.
     */
    public static int getSumOfNTerms(int a, int n, int d) {
        return (n / 2) * (2 * a + (n - 1) * d); // Formula to find the sum of first `n` terms
    }
}
