/**
 * Problem Statement:
 * 
 *     Given a non-negative integer `n` and a precision value `p`, calculate the square root of `n` up to `p` decimal places.
 *     The square root of a number `x` is the number `y` such that `y * y <= x` and `(y + 1) * (y + 1) > x`.
 *     You are required to compute the square root of `n` to `p` decimal places using binary search for the integer part
 *     and then refining the result to the required precision.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 10^9), representing the number for which we need to find the square root.
 *     - An integer `p` (1 <= p <= 9), representing the number of decimal places of precision required.
 * 
 * Output:
 *     - A floating-point number representing the square root of `n` with `p` decimal places of precision.
 * 
 * Example:
 *     Input:
 *     10 3
 *     Output:
 *     3.162
 * 
 *     Explanation:
 *     The square root of 10 is approximately `3.16227766016838`. The result is rounded to `3.162` up to three decimal places.
 */

import java.util.Scanner;

public class j02SquareRootDecimal {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number for which we need to find the square root
        int p = in.nextInt(); // Input: the precision (number of decimal places)
        System.out.printf("Square Root of %d is %f\n", n, squareRoot(n, p)); // Calling the method for square root
                                                                             // calculation
        in.close();
    }

    /**
     * Approach: Binary Search with Precision Adjustment
     * 
     * Intuition:
     * - First, we use binary search to find the integer part of the square root. The binary search checks for each number
     *   in the range from `1` to `n` and finds the largest number `m` such that `m * m <= n`. This gives us the integer part
     *   of the square root.
     * - Once the integer part is found, we adjust the result by incrementally adding a precision value. We continue this process
     *   until the result is precise up to `p` decimal places.
     * 
     * Time Complexity:
     * - The binary search for the integer part takes `O(log n)`.
     * - The loop for precision adjustment takes `O(p)` iterations where `p` is the number of decimal places. 
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param n The input number.
     * @param p The number of decimal places of precision required.
     * @return The square root of `n` rounded to `p` decimal places.
     */
    public static double squareRoot(long n, int p) {
        long s = 1;
        long e = n;
        double root = 0.0;

        // Binary search to find the integer part of the square root
        while (s <= e) {
            long m = s + (e - s) / 2;

            if (m * m == n) {
                root = m; // Exact square root found
                break;
            }

            if (m * m > n) {
                e = m - 1; // Move search range to the left
            } else {
                root = m; // Store the potential integer part of the square root
                s = m + 1; // Move search range to the right
            }
        }

        // Adjust the precision after finding the integer part
        double precision = 0.1;
        for (int i = 1; i <= p; i++) {
            while ((root + precision) * (root + precision) <= n) {
                root += precision; // Increment the root by the precision value
            }
            precision /= 10; // Reduce precision for the next decimal place
        }

        return root; // Return the square root with the required precision
    }
}
