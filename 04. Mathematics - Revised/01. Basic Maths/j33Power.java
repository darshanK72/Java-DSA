/**
 * Problem Statement:
 * 
 *    Implement various methods to calculate the power of a number `x` raised to an integer `p` (x^p).
 *    The methods should handle positive, negative, and large powers efficiently.
 * 
 * Input:
 *    - A double `x`, the base.
 *    - An integer `p`, the exponent.
 * 
 * Output:
 *    - The computed value of x^p using various methods, showcasing different approaches.
 * 
 * Example:
 * Input:
 *    2.0
 *    10
 * Output:
 *    1024.0 (Naive, Binary Exponentiation)
 * 
 * Input:
 *    2.0
 *    -3
 * Output:
 *    0.125 (Handles negative powers)
 */

import java.util.Scanner;

public class j33Power {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Input: Base and exponent
        double x = in.nextDouble();
        int p = in.nextInt();

        // Outputs using various methods
        System.out.println("Naive Power (O(n)): " + powerNaive(x, p));
        System.out.println("Handles Negatives (O(n)): " + powerForNegatives(x, p));
        System.out.println("Binary Exponentiation (O(log n)): " + powerBinaryExponentiation(x, p));

        in.close();
    }

    /**
     * Approach 1: Naive Iterative Method
     * 
     * Intuition:
     * - Multiply `x` by itself `p` times to calculate x^p.
     * - This is a straightforward and simple approach but inefficient for large values of `p`.
     * 
     * Time Complexity:
     * - O(p) for positive powers, where p is the exponent.
     * 
     * Space Complexity:
     * - O(1), no additional memory used.
     * 
     * Limitations:
     * - Does not handle negative powers or very large values efficiently.
     * 
     * @param x The base.
     * @param p The exponent.
     * @return The result of x^p.
     */
    public static double powerNaive(double x, int p) {
        double res = 1.0;
        for (int i = 1; i <= p; i++) {
            res *= x;
        }
        return res;
    }

    /**
     * Approach 2: Handling Negative and Large Exponents Iteratively
     * 
     * Intuition:
     * - For negative exponents, use the formula: x^(-p) = 1 / x^p.
     * - For large powers, iterate through a loop but invert `x` when p < 0.
     * 
     * Time Complexity:
     * - O(|p|), linear in the magnitude of the exponent.
     * 
     * Space Complexity:
     * - O(1), no additional memory used.
     * 
     * @param x The base.
     * @param p The exponent.
     * @return The result of x^p.
     */
    public static double powerForNegatives(double x, int p) {
        if (x == 1)
            return 1; // Special case
        if (x == -1)
            return (p % 2 == 0) ? 1 : -1; // Special case

        double res = 1.0;
        long b = p; // Handle negative powers
        if (b < 0) {
            b = -b;
            x = 1.0 / x;
        }

        for (long i = 1; i <= b; i++) {
            res *= x;
        }
        return res;
    }

    /**
     * Approach 3: Binary Exponentiation
     * 
     * Intuition:
     * - Binary exponentiation is a faster way to calculate x^p.
     * - Split the problem into smaller subproblems using the property:
     *   x^p = (x^(p/2))^2 if p is even, else x^p = x * (x^(p/2))^2.
     * - Halve the problem at each step, achieving logarithmic time complexity.
     * - For negative exponents, invert `x` and convert `p` to positive.
     * 
     * Time Complexity:
     * - O(log p), where p is the exponent.
     * 
     * Space Complexity:
     * - O(1), iterative approach avoids stack usage.
     * 
     * @param x The base.
     * @param p The exponent.
     * @return The result of x^p.
     */
    public static double powerBinaryExponentiation(double x, int p) {
        if (x == 1)
            return 1; // Special case
        if (x == -1)
            return (p % 2 == 0) ? 1 : -1; // Special case

        double result = 1.0;
        long b = p; // Handle negative powers
        if (b < 0) {
            b = -b;
            x = 1.0 / x;
        }

        while (b > 0) {
            if (b % 2 == 1) { // If the current power is odd
                result *= x;
            }
            x = x * x; // Square the base
            b /= 2; // Halve the power
        }
        return result;
    }

    /**
    * Approach 4: Recursive Naive Power Calculation
    * 
    * Intuition:
    * - Recursively multiply the base `n` by itself `p` times until `p` becomes 0.
    * - This is a straightforward approach but not efficient due to repeated work in recursion.
    * 
    * Time Complexity:
    * - O(p), where p is the exponent.
    * 
    * Space Complexity:
    * - O(p), due to recursion stack.
    * 
    * @param n The base.
    * @param p The exponent.
    * @return The result of n^p.
    */
    public static int power(int n, int p) {
        if (p == 0)
            return 1; // Base case
        return n * power(n, p - 1); // Recursive case
    }

    /**
     * Approach 5: Recursive Power with Optimization (Divide and Conquer)
     * 
     * Intuition:
     * - Split the exponentiation into smaller subproblems:
     *   If p is even: n^p = (n^(p/2)) * (n^(p/2)).
     *   If p is odd: n^p = n * (n^(p/2)) * (n^(p/2)).
     * - Reduces the number of multiplications compared to naive recursion.
     * 
     * Time Complexity:
     * - O(p), for redundant calculations of subproblems.
     * 
     * Space Complexity:
     * - O(p), due to recursion stack.
     * 
     * @param n The base.
     * @param p The exponent.
     * @return The result of n^p.
     */
    public static int powerEfficient(int n, int p) {
        if (p == 0)
            return 1; // Base case
        if ((p & 1) == 0) {
            return powerEfficient(n, p / 2) * powerEfficient(n, p / 2);
        } else {
            return n * powerEfficient(n, p / 2) * powerEfficient(n, p / 2);
        }
    }

    /**
     * Approach 6: Optimized Recursive Power (Avoid Redundant Work)
     * 
     * Intuition:
     * - Cache the result of the smaller subproblem to avoid recomputation.
     * - Compute the half power once and reuse it to achieve logarithmic complexity.
     * 
     * Time Complexity:
     * - O(log p), as the problem size is halved at each recursive step.
     * 
     * Space Complexity:
     * - O(log p), due to recursion stack.
     * 
     * @param n The base.
     * @param p The exponent.
     * @return The result of n^p.
     */
    public static int powerMoreEfficient(int n, int p) {
        if (p == 0)
            return 1; // Base case
        int halfPower = powerMoreEfficient(n, p / 2); // Compute half power
        if ((p % 2 ) == 0) {
            return halfPower * halfPower;
        } else {
            return n * halfPower * halfPower;
        }
    }

    /**
     * Approach 7: Fast Modular Exponentiation
     * 
     * Intuition:
     * - Combine binary exponentiation with modular arithmetic.
     * - Efficiently calculate (a^b) % m by squaring the base and reducing the exponent.
     * - Modulo is applied at each step to keep numbers manageable.
     * 
     * Time Complexity:
     * - O(log b), where b is the exponent.
     * 
     * Space Complexity:
     * - O(1), as the method is iterative.
     * 
     * @param a The base.
     * @param b The exponent.
     * @param m The modulo.
     * @return The result of (a^b) % m.
     */
    public static long fastExponentiation(long a, long b, long m) {
        long result = 1;
        while (b > 0) {
            if ((b % 2) == 1) { // If the current power is odd
                result = modMul(result, a, m); // Multiply base with result
            }
            a = modMul(a, a, m); // Square the base
            b /= 2; // Halve the exponent
        }
        return result;
    }

    /**
     * Helper Function: Modular Multiplication
     * 
     * Intuition:
     * - Calculate (a * b) % m efficiently to avoid overflow.
     * 
     * Time Complexity:
     * - O(1), single multiplication and modulo operation.
     * 
     * Space Complexity:
     * - O(1).
     * 
     * @param a The first number.
     * @param b The second number.
     * @param m The modulo.
     * @return The result of (a * b) % m.
     */
    public static long modMul(long a, long b, long m) {
        return (a % m * b % m) % m;
    }
}
