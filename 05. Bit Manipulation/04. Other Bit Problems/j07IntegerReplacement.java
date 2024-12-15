/**
 * Problem Statement:
 * 
 *     Given a positive integer `n`, you can perform the following operations:
 *     - If `n` is even, divide it by 2.
 *     - If `n` is odd, you can either increment or decrement it by 1.
 * 
 *     Return the minimum number of operations needed to reduce `n` to 1.
 * 
 * Input:
 *     - A positive integer `n` (1 <= n <= 2^31 - 1).
 * 
 * Output:
 *     - An integer representing the minimum number of operations to reduce `n` to 1.
 * 
 * Example:
 *     Input:
 *         8
 *     Output:
 *         3
 * 
 *     Explanation:
 *         8 -> 4 -> 2 -> 1 (3 operations).
 * 
 *     Input:
 *         7
 *     Output:
 *         4
 * 
 *     Explanation:
 *         7 -> 8 -> 4 -> 2 -> 1 (4 operations).
 */

import java.util.Scanner;

public class j07IntegerReplacement {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the positive integer `n`

        // Call the iterative solution
        System.out.println(integerReplacement1(n));

        // Call the recursive solution
        System.out.println(integerReplacement2(n));

        in.close();
    }

    /**
     * Approach 1: Iterative Solution
     * 
     * Intuition:
     * - Use a loop to repeatedly reduce `n` to 1 based on its parity:
     *     - If `n` is even, divide by 2.
     *     - If `n` is odd, decide whether to increment or decrement based on the number of trailing 1s in binary form.
     * - Use bitwise operations to efficiently check conditions.
     * 
     * Time Complexity:
     * - O(log(n)), as the value of `n` is approximately halved in each step.
     * 
     * Space Complexity:
     * - O(1), since no additional space is used.
     * 
     * @param n The positive integer to be reduced.
     * @return The minimum number of operations needed to reduce `n` to 1.
     */
    public static int integerReplacement1(int n) {
        int count = 0;

        // Special case for Integer.MAX_VALUE
        if (n == Integer.MAX_VALUE)
            return 32;

        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2; // Even: divide by 2
            } else if (n == 3 || (n & 3) == 1) {
                n = n - 1; // Odd: decrement
            } else if ((n & 3) == 3) {
                n = n + 1; // Odd: increment
            }
            count++;
        }
        return count;
    }

    /**
     * Approach 2: Recursive Solution
     * 
     * Intuition:
     * - Use recursion to handle the reduction:
     *     - If `n` is even, divide by 2 and recurse.
     *     - If `n` is odd, recursively calculate the minimum operations for `n - 1` and `n + 1`.
     * - This approach naturally explores all possible paths but ensures optimality via recursive logic.
     * 
     * Time Complexity:
     * - O(log(n)), as the value of `n` is approximately halved in each step.
     * 
     * Space Complexity:
     * - O(log(n)), due to the recursion stack.
     * 
     * @param n The positive integer to be reduced.
     * @return The minimum number of operations needed to reduce `n` to 1.
     */
    public static int integerReplacement2(int n) {
        // Special case for Integer.MAX_VALUE
        if (n == Integer.MAX_VALUE)
            return 32;

        if (n == 1)
            return 0; // Base case: already 1

        if ((n & 1) == 0)
            return 1 + integerReplacement2(n / 2); // Even: divide by 2

        // Odd: take the minimum of incrementing or decrementing
        if (n == 3 || (n & 3) == 1)
            return 1 + integerReplacement2(n - 1);
        else
            return 1 + integerReplacement2(n + 1);
    }
}
