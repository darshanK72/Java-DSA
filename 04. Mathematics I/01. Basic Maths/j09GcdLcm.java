
/**
 * Problem Statement:
 * 
 *     Given two integers `a` and `b`, calculate their GCD (Greatest Common Divisor) and LCM (Least Common Multiple).
 * 
 * Input:
 *     - Two integers `a` and `b`.
 * 
 * Output:
 *     - The Greatest Common Divisor (GCD) of the two numbers.
 * 
 * Example:
 *     Input:
 *     12 15
 *     Output:
 *     HCF of (12, 15) = 3
 * 
 * Constraints:
 *     - `a` and `b` are non-negative integers.
 * 
 * GCD (Greatest Common Divisor): 
 *     The largest number that divides both `a` and `b` without leaving a remainder. 
 * LCM (Least Common Multiple): 
 *     The smallest number that is divisible by both `a` and `b`.
 */

import java.util.Scanner;

public class j09GcdLcm {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading two integers a and b
        int a = in.nextInt();
        int b = in.nextInt();

        // Calculate GCD using the efficient method
        int hcf = gcdMmodulus(a, b);

        // Display the result for HCF
        System.out.printf("HCF of (%d,%d) = %d\n", a, b, hcf);

        // Calculate LCM using the formula LCM(a, b) = (a * b) / GCD(a, b)
        int lcm = lcm(a, b);

        // Display the result for LCM
        System.out.printf("LCM of (%d,%d) = %d\n", a, b, lcm);

        in.close();
    }

    /**
     * Approach 1: Brute Force Method to calculate GCD.
     * 
     * Intuition:
     * - The brute force approach checks all numbers up to the minimum of `a` and
     * `b` to find the largest number that divides both `a` and `b`.
     * - We start from 1 and keep checking every integer until we reach the smallest
     * of the two numbers.
     * - This is the most straightforward approach but is inefficient for large
     * numbers.
     * 
     * Time Complexity: O(min(a, b)), because we loop through numbers up to the
     * minimum of a and b.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    public static int gcdBruteForce(int a, int b) {
        int gcd = 1;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    /**
     * Approach 2: Repeated Subtraction Method to calculate GCD.
     * 
     * Intuition:
     * - In this method, we subtract the smaller number from the larger number
     * repeatedly until both numbers become equal.
     * - Once the two numbers are equal, that number is the GCD of `a` and `b`.
     * - This method is based on the observation that the GCD remains unchanged
     * after each subtraction.
     * 
     * Time Complexity: O(max(a, b)), as we subtract the smaller number from the
     * larger until they become equal.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    public static int gcdSubtraction(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    /**
     * Approach 3: Modulo Method (Euclidean Algorithm) to calculate GCD.
     * 
     * Intuition:
     * - The Euclidean Algorithm is an efficient method for finding the GCD of two
     * numbers.
     * - The basic idea is that the GCD of two numbers `a` and `b` is the same as
     * the GCD of `b` and `a % b` (the remainder when `a` is divided by `b`).
     * - This process is repeated until one of the numbers becomes zero, and the
     * other number is the GCD.
     * 
     * Time Complexity: O(log(min(a, b))), due to the logarithmic nature of the
     * Euclidean algorithm.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    public static int gcdMmodulus(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Approach 4: Recursive Modulo Method (Euclidean Algorithm) to calculate GCD.
     * 
     * Intuition:
     * - This approach uses recursion to implement the Euclidean algorithm.
     * - The base case occurs when the second number `b` becomes zero, and the first
     * number `a` is the GCD.
     * - The recursive step calls the function with `b` and `a % b` as the
     * arguments, gradually reducing the size of the numbers.
     * 
     * Time Complexity: O(log(min(a, b))), recursive nature of the Euclidean
     * algorithm.
     * Space Complexity: O(log(min(a, b))), due to recursion stack.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    public static int gcdRecursive(int a, int b) {
        if (b == 0)
            return a;
        return gcdRecursive(b, a % b);
    }

    /**
     * Method to calculate LCM using the formula LCM(a, b) = (a * b) / GCD(a, b)
     * 
     * Intuition:
     * - The LCM of two numbers can be derived from their GCD using the formula
     * `LCM(a, b) = (a * b) / GCD(a, b)`.
     * - This formula works because the product of the two numbers is always
     * divisible by both `a` and `b`, and dividing by the GCD gives the smallest
     * multiple.
     * 
     * Time Complexity: O(log(min(a, b))), due to GCD calculation.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The LCM of a and b.
     */
    public static int lcm(int a, int b) {
        return (a * b) / gcdRecursive(a, b);
    }

}
