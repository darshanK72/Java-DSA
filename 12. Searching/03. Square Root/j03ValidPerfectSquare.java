/**
 * Problem Statement:
 * 
 *     Given a non-negative integer `num`, determine if it is a perfect square. A number is a perfect square if there exists 
 *     an integer `x` such that `x * x == num`. You need to implement an efficient solution to check whether the given number
 *     is a perfect square.
 * 
 * Input:
 *     - An integer `num` (0 <= num <= 10^9), representing the number to be checked.
 * 
 * Output:
 *     - A boolean value `true` if the number is a perfect square, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     16
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The square root of 16 is 4, and 4 * 4 equals 16, so 16 is a perfect square.
 */

import java.util.Scanner;

public class j03ValidPerfectSquare {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to check
        System.out.print(isPerfectSquare(n)); // Output: whether the number is a perfect square or not
        in.close();
    }

    /**
     * Approach: Binary Search to Check for Perfect Square
     * 
     * Intuition:
     * - A perfect square is a number that has an integer square root. We can efficiently check whether a number is a perfect 
     *   square using binary search. The idea is to search for a number `x` such that `x * x == num`.
     * - We use binary search over the range from `1` to `num`. If the middle element of the current search range, when squared, 
     *   equals the target `num`, then `num` is a perfect square.
     * 
     * Time Complexity:
     * - The binary search runs in `O(log num)` time, as we divide the search range in half at each step.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space for variables.
     * 
     * @param num The input number to check.
     * @return `true` if the number is a perfect square, otherwise `false`.
     */
    public static boolean isPerfectSquare(int num) {
        long s = 1; // Start of the search range
        long e = num; // End of the search range
        while (s <= e) {
            long mid = s + (e - s) / 2; // Find the middle element of the search range
            if (mid * mid == num) {
                return true; // If mid squared equals num, it's a perfect square
            } else if (mid * mid < num) {
                s = mid + 1; // If mid squared is less than num, narrow the range to the right
            } else {
                e = mid - 1; // If mid squared is greater than num, narrow the range to the left
            }
        }
        return false; // If no perfect square is found, return false
    }
}
