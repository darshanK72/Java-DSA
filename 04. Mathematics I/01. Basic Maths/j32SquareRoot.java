/**
 * Problem Statement:
 * 
 *     The task is to find the square root of a given number `n` using the Newton-Raphson method.
 *     The Newton-Raphson method is an iterative numerical technique to find the roots of a real-valued function.
 *     It is commonly used to find square roots of numbers. The iterative formula for finding the square root
 *     is given by:
 *     
 *     x_(n+1) = 0.5 * (x_n + n / x_n)
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6), representing the number for which the square root is to be computed.
 * 
 * Output:
 *     - A floating-point number representing the square root of `n` up to 5 decimal places.
 * 
 * Example:
 *     Input:
 *     25
 *     Output:
 *     Square Root of 25 is 5.00000
 * 
 *     Explanation:
 *     The square root of 25 is 5, which is output with 5 decimal places.
 */

import java.util.Scanner;

// Complexity : O(log N * f(N))
public class j32SquareRoot {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Output the result
        System.out.printf("Square Root of %d is %.5f", n, sqrt(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Newton-Raphson Method for Square Root
     * 
     * Intuition:
     * - Newton-Raphson is an iterative method for approximating the roots of a function. For square root, 
     *   we apply it to the function f(x) = x^2 - n, which has a root at sqrt(n).
     * - The iterative formula is: x_(n+1) = 0.5 * (x_n + n / x_n), where x_n is the current approximation.
     * 
     * Time Complexity:
     * - O(log N * f(N)), where f(N) is the number of iterations required for the desired precision.
     * 
     * Space Complexity:
     * - O(1), as we only need a few variables for computation.
     * 
     * @param n The number for which the square root is to be computed.
     * @return The square root of n calculated using the Newton-Raphson method.
     */
    public static double sqrt(int n) {
        double x = n; // Start with an initial guess (n itself is a good starting point)
        double root;

        // Iterate until the difference between successive estimates is small enough
        while (true) {
            root = 0.5 * (x + (n / x)); // Newton-Raphson formula for square root
            if (Math.abs(root - x) <= 1e-6) { // Precision threshold of 1e-6
                break;
            }
            x = root; // Update the guess
        }

        return root;
    }
}
