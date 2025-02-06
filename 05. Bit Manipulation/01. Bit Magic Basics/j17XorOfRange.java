/**
 * Problem Statement:
 * 
 *     Given two integers `n1` and `n2`, find the XOR of all numbers from `n1` to `n2` (inclusive). The XOR operation is performed on all the numbers in the range `[n1, n2]`.
 * 
 *     Additionally, you are provided with a method to compute the XOR of all integers from 0 to `n`, which will help in calculating the XOR of a range efficiently.
 * 
 * Input:
 *     - Two integers `n1` and `n2` (0 <= n1 <= n2 <= 10^9).
 * 
 * Output:
 *     - An integer representing the XOR of all numbers from `n1` to `n2`.
 * 
 * Example:
 *     Input:
 *     5 9
 *     Output:
 *     1
 * 
 *     Input:
 *     10 15
 *     Output:
 *     4
 * 
 * Explanation:
 *     In the first example, the XOR of numbers from 5 to 9 is:
 *     5 ^ 6 ^ 7 ^ 8 ^ 9 = 1.
 *     In the second example, the XOR of numbers from 10 to 15 is:
 *     10 ^ 11 ^ 12 ^ 13 ^ 14 ^ 15 = 4.
 */

import java.util.Scanner;

public class j17XorOfRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // int n = in.nextInt();
        // System.out.println(xorFromZeroToN(n));

        int n1 = in.nextInt();
        int n2 = in.nextInt();

        System.out.println(xorFromN1ToN2(n1, n2));
        in.close();
    }

    /**
     * Approach: XOR from 0 to N
     * 
     * Intuition:
     * - The XOR of all numbers from 0 to N follows a periodic pattern based on `N % 4`. By identifying the pattern, we can compute the XOR of numbers from 0 to `N` in constant time.
     * 
     * Time Complexity:
     * - O(1), as the computation is based on simple modulo operations.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of space.
     * 
     * @param n The input number up to which XOR is computed.
     * @return The XOR of all numbers from 0 to `n`.
     */
    public static int xorFromZeroToN(int n) {
        switch (n % 4) {
            case 0 -> {
                return n;
            }
            case 1 -> {
                return 1;
            }
            case 2 -> {
                return n + 1;
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * Approach: XOR from N1 to N2 using XOR from 0 to N
     * 
     * Intuition:
     * - To compute the XOR of numbers from `n1` to `n2`, we use the property of XOR:
     *   - XOR(n1 to n2) = XOR(0 to n2) ^ XOR(0 to n1-1)
     * - This method leverages the previously defined function to efficiently compute the XOR over a range.
     * 
     * Time Complexity:
     * - O(1), as the calculation is based on constant-time XOR operations.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of space.
     * 
     * @param n1 The starting number of the range.
     * @param n2 The ending number of the range.
     * @return The XOR of all numbers from `n1` to `n2`.
     */
    public static int xorFromN1ToN2(int n1, int n2) {
        return xorFromZeroToN(n1 - 1) ^ xorFromZeroToN(n2);
    }
}
