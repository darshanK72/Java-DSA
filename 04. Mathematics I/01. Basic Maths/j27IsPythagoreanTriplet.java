/**
 * Problem Statement:
 * 
 *     Given three integers `a`, `b`, and `c`, determine if they can form a Pythagorean triplet.
 *     A Pythagorean triplet is a set of three positive integers `a`, `b`, and `c` such that:
 *     a² + b² = c²
 *     where `c` is the largest number, and `a` and `b` are the other two numbers.
 * 
 * Input:
 *     - Three integers `a`, `b`, and `c` (1 <= a, b, c <= 10^3).
 * 
 * Output:
 *     - Return `true` if the three integers form a Pythagorean triplet, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     3 4 5
 *     Output:
 *     true
 * 
 *     Explanation:
 *     3² + 4² = 9 + 16 = 25, which equals 5².
 *     Therefore, 3, 4, and 5 form a Pythagorean triplet.
 */

import java.util.Scanner;

public class j27IsPythagoreanTriplet {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        // Call the solution method
        System.out.println(isPythagoreanTriplet(a, b, c));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Naive Solution
     * 
     * Intuition:
     * - A Pythagorean triplet satisfies the equation a² + b² = c² where `c` is the largest number.
     * - We check all combinations of the three integers to verify if they satisfy the triplet condition.
     * 
     * Time Complexity:
     * - O(1), as we are only performing a constant number of operations.
     * 
     * Space Complexity:
     * - O(1), as we are not using any extra space other than variables.
     * 
     * @param a The first number
     * @param b The second number
     * @param c The third number
     * @return true if the numbers form a Pythagorean triplet, false otherwise.
     */
    public static boolean isPythagoreanTriplet(int a, int b, int c) {
        // Check if any permutation of the numbers satisfies the Pythagorean theorem.
        return a * a + b * b == c * c || b * b + c * c == a * a || a * a + c * c == b * b;
    }
}
