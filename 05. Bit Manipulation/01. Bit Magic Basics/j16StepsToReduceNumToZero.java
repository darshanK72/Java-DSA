/**
 * Problem Statement:
 * 
 *     Given a non-negative integer `num`, you need to determine how many steps it will take to reduce it to zero. In each step, you can either:
 *     - If the number is even, divide it by 2.
 *     - If the number is odd, subtract 1 from it.
 * 
 *     Your task is to find the number of steps needed to reduce `num` to zero.
 * 
 * Input:
 *     - A non-negative integer `num` (0 <= num <= 10^6).
 * 
 * Output:
 *     - An integer representing the number of steps required to reduce `num` to zero.
 * 
 * Example:
 *     Input:
 *     14
 *     Output:
 *     6
 * 
 *     Input:
 *     8
 *     Output:
 *     4
 * 
 * Explanation:
 *     In the first example, the sequence of operations is:
 *     14 (even, divide by 2) -> 7 (odd, subtract 1) -> 6 (even, divide by 2) -> 3 (odd, subtract 1) -> 2 (even, divide by 2) -> 1 (odd, subtract 1) -> 0
 *     The total number of steps is 6.
 * 
 *     In the second example, the sequence of operations is:
 *     8 (even, divide by 2) -> 4 (even, divide by 2) -> 2 (even, divide by 2) -> 1 (odd, subtract 1) -> 0
 *     The total number of steps is 4.
 */

import java.util.Scanner;

public class j16StepsToReduceNumToZero {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(numberOfSteps(num));
        System.out.println(numberOfStepsEfficient(num));
        in.close();
    }

    /**
     * Approach: Recursive Solution
     * 
     * Intuition:
     * - This approach recursively checks whether the number is even or odd. 
     *   If the number is even, we divide it by 2; if it's odd, we subtract 1 from it.
     * 
     * Time Complexity:
     * - O(log n), as the number is halved in each even operation, which reduces the problem size logarithmically.
     * 
     * Space Complexity:
     * - O(log n) due to recursion depth.
     * 
     * @param num The input number.
     * @return The number of steps to reduce `num` to zero.
     */
    public static int numberOfSteps(int num) {
        return counter(num, 0);
    }

    public static int counter(int n, int count) {
        if (n == 0)
            return count;
        if ((n & 1) == 0)
            return counter(n / 2, ++count);
        return counter(--n, ++count);
    }

    /**
     * Approach: Iterative Solution (Efficient)
     * 
     * Intuition:
     * - This approach uses a loop to repeatedly apply the same logic: if the number is even, 
     *   divide it by 2; if it's odd, subtract 1.
     * 
     * Time Complexity:
     * - O(log n), as the number is halved in each even operation, which reduces the problem size logarithmically.
     * 
     * Space Complexity:
     * - O(1), as the space used is constant.
     * 
     * @param num The input number.
     * @return The number of steps to reduce `num` to zero.
     */
    public static int numberOfStepsEfficient(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 0)
                num >>= 1;
            else
                num--;
            count++;
        }
        return count;
    }
}
