/*-
 * Problem Statement:
 * 
 *     Given a non-negative integer `n`, find the integer part of its square root.
 *     The square root of a number `x` is the number `y` such that `y * y <= x` and `(y + 1) * (y + 1) > x`.
 *     You need to find the integer value of `y` without using any built-in square root function.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 10^9), representing the number for which we need to find the square root.
 * 
 * Output:
 *     - An integer representing the integer part of the square root of `n`.
 * 
 * Example:
 *     Input:
 *     16
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The square root of 16 is 4. The integer part is 4.
 */

import java.util.Scanner;

// Complexity : O(log N)
public class j01SquareRootIntegral {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number for which we need to find the square root
        System.out.printf("Square Root of %d is %f\n", n, sqrt(n)); // Calling brute force method
        System.out.printf("Square Root of %d is %f\n", n, sqrtEfficient(n)); // Calling binary search method
        in.close();
    }

    /*-
     * Approach 1: Brute Force Method
     * 
     * Intuition:
     * - The simplest approach is to start from 1 and incrementally check each integer `i` to see if `i * i <= x`. 
     * - We keep updating the result until we find the largest `i` such that `i * i <= x`. 
     * - This approach will give the correct integer square root, but it's inefficient for large values of `n`.
     * 
     * Time Complexity:
     * - O(N), where `N` is the number itself, as we check each number starting from 1 up to `n`.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param x The input number.
     * @return The integer part of the square root of `x`.
     */
    public static int sqrt(int x) {
        long ans = 0;
        for (long i = 1; i <= x; i++) {
            if (i * i <= x) {
                ans = i; // Update result with the largest integer whose square is <= x
            } else {
                break; // Stop if we find the square exceeds x
            }
        }
        return (int) ans; // Return the integer part of the square root
    }

    /*-
     * Approach 2: Efficient Method (Binary Search)
     * 
     * Intuition:
     * - A more efficient approach involves using binary search. We start by searching between 1 and `x` for the integer
     *   whose square is closest to `x` but does not exceed it.
     * - By narrowing down the search space, we can find the result in O(log N) time, which is much faster for large numbers.
     * 
     * Time Complexity:
     * - O(log N), where `N` is the input number, as we are using binary search to find the integer square root.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param x The input number.
     * @return The integer part of the square root of `x`.
     */
    public static int sqrtEfficient(int x) {
        long s = 1;
        long e = x;
        long ans = 0;
        while (s <= e) {
            long mid = s + (e - s) / 2;
            if (mid * mid <= x) {
                ans = mid; // Update the answer when mid squared is less than or equal to x
                s = mid + 1; // Move to the right half
            } else {
                e = mid - 1; // Move to the left half
            }
        }
        return (int) ans; // Return the integer part of the square root
    }
}
