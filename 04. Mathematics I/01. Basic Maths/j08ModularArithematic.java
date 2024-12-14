
/**
 * Problem Statement:
 * 
 *     Given two integers `a` and `b`, compute the following operations modulo `m`:
 *     1. (a + b) % m
 *     2. (a - b) % m
 *     3. (a * b) % m
 * 
 *     The modulus `m` is predefined as 1000000007.
 * 
 * Input:
 *     - Two integers `a` and `b`.
 * 
 * Output:
 *     - The result of the three operations modulo 1000000007:
 *         1. (a + b) % 1000000007
 *         2. (a - b) % 1000000007
 *         3. (a * b) % 1000000007
 * 
 * Example:
 *     Input:
 *     5 7
 *     Output:
 *     12
 *     -2
 *     35
 * 
 * Constraints:
 *     - `a` and `b` are non-negative integers.
 */

import java.util.Scanner;

public class j08ModularArithematic {

    /**
     * Intuition:
     * - Modular arithmetic is used to handle large numbers by ensuring that
     * intermediate results
     * do not exceed a fixed size.
     * - The operations here calculate the sum, difference, and product of two
     * numbers modulo a given modulus `m`.
     * 
     * Approach:
     * 1. **modAdd(a, b, m)**: Adds `a` and `b`, then takes the modulus `m` of the
     * sum.
     * 2. **modSub(a, b, m)**: Subtracts `b` from `a`, then takes the modulus `m` of
     * the result.
     * 3. **modMul(a, b, m)**: Multiplies `a` and `b`, then takes the modulus `m` of
     * the product.
     * In each case, the modulus operation ensures that the result remains within
     * the bounds of the modulus.
     */

    /**
     * Method to add two numbers modulo m.
     * 
     * Time Complexity: O(1), constant time for addition and modulus operation.
     * Space Complexity: O(1), no additional space used.
     * 
     * @param a The first number.
     * @param b The second number.
     * @param m The modulus.
     * @return The result of (a + b) % m.
     */
    public static long modAdd(long a, long b, long m) {
        return (a % m + b % m) % m;
    }

    /**
     * Method to subtract two numbers modulo m.
     * 
     * Time Complexity: O(1), constant time for subtraction and modulus operation.
     * Space Complexity: O(1), no additional space used.
     * 
     * @param a The first number.
     * @param b The second number.
     * @param m The modulus.
     * @return The result of (a - b) % m.
     */
    public static long modSub(long a, long b, long m) {
        return (a % m - b % m + m) % m; // Ensure result is positive by adding m
    }

    /**
     * Method to multiply two numbers modulo m.
     * 
     * Time Complexity: O(1), constant time for multiplication and modulus
     * operation.
     * Space Complexity: O(1), no additional space used.
     * 
     * @param a The first number.
     * @param b The second number.
     * @param m The modulus.
     * @return The result of (a * b) % m.
     */
    public static long modMul(long a, long b, long m) {
        return (a % m * b % m) % m;
    }

    public static void main(String args[]) {
        long mod = 1000000007L; // Predefined modulus

        Scanner in = new Scanner(System.in);

        // Reading the two integers a and b
        long a = in.nextLong();
        long b = in.nextLong();

        // Performing modular arithmetic operations
        System.out.println(modAdd(a, b, mod)); // Output: (a + b) % mod
        System.out.println(modSub(a, b, mod)); // Output: (a - b) % mod
        System.out.println(modMul(a, b, mod)); // Output: (a * b) % mod

        in.close();
    }
}
