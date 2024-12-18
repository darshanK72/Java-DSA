/**
 * Problem Statement:
 * 
 *     Given two polynomials represented as arrays `arr1` and `arr2`, where each element represents the 
 *     coefficient of the corresponding term in increasing order of powers, compute the product of the two 
 *     polynomials. The output should be an array representing the coefficients of the resultant polynomial.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 10^4), representing the size of the first array.
 *     - An array `arr1` of size `m`, where each element is the coefficient of a term in the first polynomial.
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the second array.
 *     - An array `arr2` of size `n`, where each element is the coefficient of a term in the second polynomial.
 * 
 * Output:
 *     - An array representing the product of the two polynomials, where each element is the coefficient 
 *       of a term in increasing order of powers.
 * 
 * Example:
 *     Input:
 *         3
 *         1 2 3
 *         2
 *         4 5
 *     Output:
 *         [4, 13, 22, 15]
 * 
 *     Explanation:
 *         The first polynomial is 1 + 2x + 3x^2.
 *         The second polynomial is 4 + 5x.
 *         Their product is 4 + 13x + 22x^2 + 15x^3, represented as [4, 13, 22, 15].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j26MultiplyPolynomials {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Input: size of the first polynomial
        int[] arr1 = new int[m];
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextInt(); // Input: coefficients of the first polynomial
        }

        int n = in.nextInt(); // Input: size of the second polynomial
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt(); // Input: coefficients of the second polynomial
        }

        // Call the polynomial multiplication method and print the result
        System.out.println("Product of Polynomials: " + Arrays.toString(polyMultiply(arr1, arr2, m, n)));

        in.close();
    }

    /**
     * Approach: Brute Force Multiplication
     * 
     * Intuition:
     *     - Each term in the first polynomial (represented by its coefficient and power) is multiplied 
     *       with every term in the second polynomial.
     *     - The resulting products contribute to terms in the resultant polynomial with powers being 
     *       the sum of the powers of the two multiplied terms.
     *     - We iterate through all terms in both polynomials, multiply the corresponding coefficients,
     *       and add the product to the appropriate position in the result array.
     * 
     * Explanation:
     *     - Initialize an output array `out` of size `m + n - 1`, since the degree of the product polynomial 
     *       is (m - 1) + (n - 1).
     *     - For every pair of coefficients `arr1[i]` and `arr2[j]`:
     *         - Multiply the coefficients.
     *         - Add the result to `out[i + j]` because powers add up during multiplication.
     *     - Return the resulting array.
     * 
     * Time Complexity:
     *     - O(m * n), where `m` and `n` are the sizes of the input polynomials.
     *       This is because every term in the first polynomial is multiplied with every term in the second polynomial.
     * 
     * Space Complexity:
     *     - O(m + n), for storing the resultant polynomial.
     * 
     * @param arr1 The first polynomial represented as an array of coefficients.
     * @param arr2 The second polynomial represented as an array of coefficients.
     * @param m The size of the first polynomial.
     * @param n The size of the second polynomial.
     * @return An array representing the product of the two polynomials.
     */
    public static int[] polyMultiply(int arr1[], int arr2[], int m, int n) {
        // Initialize an output array to store the result
        int[] out = new int[m + n - 1];

        // Multiply each term of the first polynomial with every term of the second
        // polynomial
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                out[i + j] += (arr1[i] * arr2[j]);
            }
        }

        return out;
    }
}
