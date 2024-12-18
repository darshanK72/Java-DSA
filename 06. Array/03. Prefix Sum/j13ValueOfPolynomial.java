/**
 * Problem Statement:
 * 
 *     Given a polynomial of degree `n - 1` represented by its coefficients in an array `poly`,
 *     and an integer `x`, compute the value of the polynomial for the given `x`.
 *     The polynomial is defined as:
 *         P(x) = poly[0] * x^(n-1) + poly[1] * x^(n-2) + ... + poly[n-1] * x^0
 *     Since the result can be large, return the result modulo 10^9+7.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the number of coefficients.
 *     - An array `poly` of size `n`, where (0 <= poly[i] <= 10^9) represents the coefficients.
 *     - An integer `x` (0 <= x <= 10^9), the value at which the polynomial is evaluated.
 * 
 * Output:
 *     - An integer representing the value of the polynomial modulo 10^9+7.
 * 
 * Example:
 *     Input:
 *     3
 *     [2, -6, 3]
 *     2
 * 
 *     Output:
 *     11
 * 
 *     Explanation:
 *     - Polynomial is 2x^2 - 6x + 3.
 *     - Substituting x = 2:
 *         P(2) = 2*(2^2) - 6*(2^1) + 3*(2^0)
 *              = 2*4 - 6*2 + 3
 *              = 8 - 12 + 3
 *              = -1 % 10^9+7 = 11 (mod 10^9+7).
 */

import java.util.Scanner;

public class j13ValueOfPolynomial {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Degree of the polynomial + 1
        long[] poly = new long[n];
        for (int i = 0; i < n; i++) {
            poly[i] = in.nextLong(); // Coefficients of the polynomial
        }
        int x = in.nextInt(); // The value of x

        // Compute and print the result
        System.out.println(valueOfPolynomial(n, poly, x));
        in.close();
    }

    /**
     * Approach 1: Naive Calculation Using Fast Exponentiation
     * 
     * Intuition:
     * - Evaluate the polynomial by calculating each term separately.
     * - Use modular arithmetic to keep intermediate results under control, avoiding overflow.
     * - Fast exponentiation is used to compute powers of `x` efficiently.
     * 
     * Time Complexity:
     * - O(n * log(m)), where `n` is the number of terms in the polynomial and `log(m)` is the time for exponentiation.
     * 
     * Space Complexity:
     * - O(1) (ignoring input storage).
     * 
     * @param n The degree of the polynomial + 1.
     * @param poly The coefficients of the polynomial.
     * @param x The value at which the polynomial is evaluated.
     * @return The value of the polynomial modulo 10^9+7.
     */
    public static int valueOfPolynomial(int n, long[] poly, int x) {
        final int MOD = 1000000007;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long term = (poly[i] * fastExp(x, (n - i - 1), MOD)) % MOD;
            ans = (ans + (int) term) % MOD;
        }

        return ans;
    }

    /**
     * Fast Exponentiation
     * 
     * Intuition:
     * - To compute x^p efficiently, repeatedly square the base while halving the power.
     * - If the power is odd, multiply the result by the current base.
     * 
     * Time Complexity:
     * - O(log(p)).
     * 
     * Space Complexity:
     * - O(1).
     * 
     * @param x The base.
     * @param p The exponent.
     * @param mod The modulus.
     * @return (x^p) % mod.
     */
    public static int fastExp(int x, int p, int mod) {
        int ans = 1;
        while (p > 0) {
            if ((p & 1) == 1) { // If power is odd
                ans = (int) ((long) ans * x % mod);
            }
            x = (int) ((long) x * x % mod); // Square the base
            p >>= 1; // Divide the power by 2
        }
        return ans;
    }
}
