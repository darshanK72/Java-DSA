/*-
 * Problem Statement:
 * 
 *     You are given a number `n`, representing the number of children standing in a circle, and a number 
 *     `k`, representing the number of seconds that have passed.Initially, the first child has the bell. 
 *     In each second, the child with the bell passes it to the next child. If the child with the bell 
 *     is at the last position,the bell passes to the previous child. The direction of passing alternates 
 *     after every time the bell reaches the first or last child.
 * 
 *     The task is to determine which child will have the bell after `k` seconds.
 * 
 * Input:
 *     - Two integers `n` and `k` (1 <= n <= 10^5, 1 <= k <= 10^5).
 * 
 * Output:
 *     - An integer representing the index (0-based) of the child who will have the bell after `k` seconds.
 * 
 * Example:
 *     Input:
 *     5 4
 *     Output:
 *     4
 * 
 *     Explanation:
 *     - Initially, the bell starts at child 0.
 *     - After 1 second, the bell is passed to child 1.
 *     - After 2 seconds, the bell is passed to child 2.
 *     - After 3 seconds, the bell is passed to child 3.
 *     - After 4 seconds, the bell is passed to child 4 .
 *     - Therefore, the child with the bell is child 4.
 */

import java.util.Scanner;

public class j18WhoHasBellAfterKSeconds {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of children
        int k = in.nextInt(); // Input: number of seconds
        System.out.println(numberOfChild(n, k)); // Output the result of the numberOfChild method
        in.close();
    }

    /*-
     * Approach: Simulate the Bell Passing
     * 
     * Intuition:
     * - We simulate the process of passing the bell from one child to another.
     * - We alternate the direction of passing the bell: it moves forward until the last child, 
     *   then it moves backward until the first child.
     * 
     * Time Complexity:
     * - O(k), as we simulate k seconds, and for each second we only perform constant time operations.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space.
     * 
     * @param n The number of children.
     * @param k The number of seconds passed.
     * @return The index of the child who has the bell after k seconds.
     */
    public static int numberOfChild(int n, int k) {
        int start = 0; // The child initially having the bell
        boolean flag = true; // Flag to control the direction (true for forward, false for backward)
        // Simulate the passing of the bell for k seconds
        while (k > 0) {
            if (flag) { // Move the bell forward
                start++;
                if (start == n - 1) // If the bell reaches the last child, reverse direction
                    flag = false;
            } else { // Move the bell backward
                start--;
                if (start == 0) // If the bell reaches the first child, reverse direction
                    flag = true;
            }
            k--; // Decrease the remaining seconds
        }
        return start; // Return the child who has the bell
    }
}
