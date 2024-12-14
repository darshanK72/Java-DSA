/*- 
 * Problem Statement:
 * 
 *     A happy number is a number that eventually reaches 1 when replaced by the sum of the square of each digit.
 *     If a number is not happy, it will eventually fall into a cycle of numbers that does not include 1.
 *     The task is to determine whether a given number is a happy number.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9) representing the number to check.
 * 
 * Output:
 *     - `true` if the number is a happy number, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     19
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - Starting from 19: 1^2 + 9^2 = 82
 *     - 8^2 + 2^2 = 68
 *     - 6^2 + 8^2 = 100
 *     - 1^2 + 0^2 + 0^2 = 1
 *     Thus, 19 is a happy number.
 * 
 *     Input:
 *     2
 *     Output:
 *     false
 * 
 *     Explanation:
 *     - Starting from 2: 2^2 = 4
 *     - 4^2 = 16
 *     - 1^2 + 6^2 = 37
 *     - 3^2 + 7^2 = 58
 *     - 5^2 + 8^2 = 89
 *     - 8^2 + 9^2 = 145
 *     - 1^2 + 4^2 + 5^2 = 42
 *     - 4^2 + 2^2 = 20
 *     - 2^2 + 0^2 = 4 (which leads back to 4, creating a cycle).
 *     Thus, 2 is not a happy number.
 */

import java.util.HashSet;
import java.util.Scanner;

public class j13HappyNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Check if the number is happy and print the result
        System.out.println(isHappy(n));

        // Close the scanner
        in.close();
    }

    /*- 
     * Approach: Check if the number eventually leads to 1 or enters a cycle.
     * 
     * Intuition:
     * - We start by calculating the sum of squares of the digits of the number.
     * - If the number reaches 1, it is a happy number.
     * - If we encounter a number that we have seen before, we are in a cycle and the number is not happy.
     * - To detect cycles, we use a `HashSet` to store the numbers we've encountered.
     * 
     * Time Complexity:
     * - O(log N), as the number of digits in the number reduces by a factor of 10 in each iteration.
     * 
     * Space Complexity:
     * - O(log N), due to the space used by the `HashSet` to store the previously encountered numbers.
     * 
     * @param n The input number to check for happiness.
     * @return `true` if the number is a happy number, `false` otherwise.
     */
    public static boolean isHappy(int n) {
        // A set to keep track of the numbers encountered during the process
        HashSet<Integer> set = new HashSet<>();

        // Loop until the number becomes 1 or a cycle is detected
        while (n > 1) {
            if (set.contains(n)) // If we encounter a number already seen, return false (cycle detected)
                return false;

            set.add(n); // Add the current number to the set

            int t = n; // Temporary variable for the number
            int x = 0; // Variable to store the sum of squares of digits

            // Calculate the sum of squares of digits of n
            while (t > 0) {
                int d = t % 10;
                x += d * d;
                t /= 10;
            }
            n = x; // Set the number to the new sum of squares
        }
        return true; // If n becomes 1, the number is happy
    }
}
