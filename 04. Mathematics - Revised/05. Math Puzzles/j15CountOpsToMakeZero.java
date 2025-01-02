/**
 * Problem Statement:
 * 
 *     You are given two integers `num1` and `num2`. In each operation, you subtract the smaller number from the larger one, 
 *     and you continue this process until either one or both numbers become zero.
 * 
 *     The task is to count the minimum number of operations required to make one or both of the integers zero.
 * 
 * Input:
 *     - Two integers `num1` and `num2` (1 <= num1, num2 <= 10^5).
 * 
 * Output:
 *     - An integer representing the number of operations required to make one or both integers zero.
 * 
 * Example:
 *     Input:
 *     5 7
 *     Output:
 *     3
 * 
 *     Explanation:
 *     - Step 1: num1 = 5, num2 = 7, subtract 5 from 7 → num1 = 5, num2 = 2.
 *     - Step 2: num1 = 5, num2 = 2, subtract 2 from 5 → num1 = 3, num2 = 2.
 *     - Step 3: num1 = 3, num2 = 2, subtract 2 from 3 → num1 = 1, num2 = 2.
 *     - The number of operations required is 3.
 */

import java.util.Scanner;

public class j15CountOpsToMakeZero {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt(); // Input: first number
        int num2 = in.nextInt(); // Input: second number
        System.out.println(countOperations(num1, num2)); // Output the result of the countOperations method
        in.close();
    }

    /**
     * Approach 1: Iterative Subtraction
     * 
     * Intuition:
     * - The basic idea is to repeatedly subtract the smaller number from the larger one.
     * - This continues until one of the numbers becomes zero.
     * - Each subtraction is considered a single operation.
     * 
     * Time Complexity:
     * - O(max(num1, num2)), as in each operation, the larger number decreases, and in the worst case, we perform one operation per decrement.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of extra space.
     * 
     * @param num1 The first integer.
     * @param num2 The second integer.
     * @return The number of operations required to make one or both integers zero.
     */
    public static int countOperations(int num1, int num2) {
        int op = 0; // Initialize the operation count
        while (num1 != 0 && num2 != 0) {
            // Subtract the smaller number from the larger one
            if (num1 > num2)
                num1 = num1 - num2;
            else
                num2 = num2 - num1;
            op++; // Increment the operation count
        }
        return op; // Return the total number of operations
    }

    /**
     * Approach 2: Recursive Subtraction
     * 
     * Intuition:
     * - This approach recursively subtracts the smaller number from the larger one until one of the numbers becomes zero.
     * - The recursive function returns the total count of operations.
     * 
     * Time Complexity:
     * - O(max(num1, num2)), as each recursive call reduces one of the numbers and the recursion depth depends on the maximum of the two numbers.
     * 
     * Space Complexity:
     * - O(1), as we don't use extra space other than function call stack.
     * 
     * @param n1 The first integer.
     * @param n2 The second integer.
     * @param op The current operation count.
     * @return The total number of operations to make one or both integers zero.
     */
    public static int ops(int n1, int n2, int op) {
        if (n1 == 0 || n2 == 0)
            return op; // Return the current operation count if one of the numbers is zero
        if (n1 > n2)
            return ops(n1 - n2, n2, op + 1); // Recur with the updated numbers
        else
            return ops(n2 - n1, n1, op + 1); // Recur with the updated numbers
    }
}
