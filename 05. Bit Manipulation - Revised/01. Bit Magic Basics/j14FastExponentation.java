/**
 * Problem Statement:
 * 
 *     You are given a base `n` and an integer exponent `p`. Your task is to compute the value of `n` 
 *     raised to the power `p` (i.e., `n^p`), efficiently using fast exponentiation techniques.
 *     Additionally, consider handling both positive and negative exponents. 
 * 
 * Input:
 *     - A double `n` (1 <= n <= 10^5), representing the base.
 *     - An integer `p` (-10^5 <= p <= 10^5), representing the exponent.
 * 
 * Output:
 *     - A double representing the result of `n^p`, computed using fast exponentiation.
 * 
 * Example:
 *     Input:
 *     2.0 3
 *     Output:
 *     Naive Fast Exponentiation: 8.000000
 *     Optimized Fast Exponentiation: 8.000000
 * 
 *     Input:
 *     2.0 -3
 *     Output:
 *     Naive Fast Exponentiation: 0.125000
 *     Optimized Fast Exponentiation: 0.125000
 * 
 * Explanation:
 *     In the first example, the base is 2 and the exponent is 3. The result of `2^3 = 8` is computed.
 *     In the second example, the base is 2 and the exponent is -3. The result of `2^(-3) = 1 / 2^3 = 0.125` is computed.
 */

import java.util.Scanner;

public class j14FastExponentation {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        double n = in.nextDouble(); // Input: base
        int p = in.nextInt(); // Input: exponent

        // Naive fast exponentiation solution
        System.out.printf("Naive Fast Exponentiation: %.6f\n", fastExpNive(n, p));

        // Optimized fast exponentiation solution (handles negative exponents as well)
        System.out.printf("Optimized Fast Exponentiation: %.6f\n", fastExpEfficnent(n, p));

        // Optimized fast exponentiation solution (handles negative exponents and large
        // numbers)
        System.out.printf("Optimized Fast Exponentiation: %.6f\n", fastExponention(n, p, (10e9 + 7)));

        in.close();
    }

    /**
     * Approach: Naive Fast Exponentiation (handles only positive powers)
     * 
     * Intuition:
     * - This method computes the power of the base `n` raised to the exponent `p` by repeatedly 
     *   squaring the base and reducing the exponent.
     * 
     * Time Complexity:
     * - O(log p), where `p` is the exponent. Each iteration halves the exponent.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of space.
     * 
     * @param x The base number.
     * @param n The exponent.
     * @return The result of `x^n` using naive fast exponentiation.
     */
    public static double fastExpNive(double x, int n) {
        double ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans *= x;
            }
            x *= x; // Square the base
            n >>= 1; // Divide the exponent by 2 (right shift)
        }
        return ans;
    }

    /**
     * Approach: Optimized Fast Exponentiation (handles both positive and negative exponents)
     * 
     * Intuition:
     * - This optimized method handles negative exponents by inverting the base. The algorithm uses 
     *   the same principles as naive exponentiation, but it considers the case of negative exponents
     *   by reciprocating the base.
     * 
     * Time Complexity:
     * - O(log p), where `p` is the exponent. Each iteration halves the exponent.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of space.
     * 
     * @param x The base number.
     * @param n The exponent.
     * @return The result of `x^n` using optimized fast exponentiation.
     */
    public static double fastExpEfficnent(double x, int n) {
        double result = 1.0;
        if (n < 0) {
            n = -n; // Make exponent positive for negative exponents
            x = 1 / x; // Take reciprocal of the base
        }
        while (n > 0) {
            if ((n & 1) == 1) {
                result *= x;
            }
            x *= x; // Square the base
            n >>= 1; // Divide the exponent by 2 (right shift)
        }
        return result;
    }

    /**
     * Approach: Modular Fast Exponentiation (for handling large numbers)
     * 
     * Intuition:
     * - This approach is useful for computing large powers under modulo arithmetic. It uses the same idea as fast exponentiation but also applies the modulus to each step to keep the numbers manageable.
     * 
     * Time Complexity:
     * - O(log b), where `b` is the exponent.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of space.
     * 
     * @param a The base number.
     * @param b The exponent.
     * @param m The modulus.
     * @return The result of `(a^b) % m` using modular exponentiation.
     */
    public static double modMul(double a, double b, double m) {
        return (a % m * b % m) % m;
    }

    public static double fastExponention(double a, long b, double m) {
        double result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = modMul(result, a, m);
            }
            a = modMul(a, a, m);
            b >>= 2;
        }
        return result;
    }
}
