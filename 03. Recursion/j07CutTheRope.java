/**
 * Problem Statement:
 * 
 *     Given a rope of length `n` and three possible lengths `a`, `b`, and `c`, 
 *     the task is to determine the maximum number of pieces the rope can be cut into.
 *     If it is not possible to cut the rope into the given lengths, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the length of the rope.
 *     - Three integers `a`, `b`, `c` (1 <= a, b, c <= 4000), the allowed segment lengths.
 * 
 * Output:
 *     - An integer representing the maximum number of pieces the rope can be cut into.
 *     - If cutting is not possible, return -1.
 * 
 * Example:
 *     Input:
 *     n = 5, a = 2, b = 5, c = 1
 * 
 *     Output:
 *     5
 * 
 *     Explanation:
 *     - The rope of length 5 can be divided into 5 pieces of size 1 (1,1,1,1,1).
 * 
 *     Input:
 *     n = 7, a = 5, b = 5, c = 2
 * 
 *     Output:
 *     2
 * 
 *     Explanation:
 *     - The rope of length 7 can be cut into segments (5,2).
 */

import java.util.Scanner;

public class j07CutTheRope {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        System.out.println(cutRopeInMaxPeices(n, a, b, c));
        in.close();
    }

    /**
     * Approach: Recursion with Maximum Subproblem Selection
     * 
     * Intuition:
     * - The problem is solved by recursively subtracting `a`, `b`, or `c` from `n` 
     *   and checking which choice yields the maximum pieces.
     * - If `n` becomes 0, a valid cut has been found, so return 0.
     * - If `n` becomes negative, the cut is invalid, so return -1.
     * - We recursively try all three segment choices and take the maximum result.
     * - If no valid cut is possible, return -1.
     * 
     * Explanation:
     * - Base case: If `n == 0`, return 0 (valid division found).
     * - If `n < 0`, return -1 (invalid division).
     * - Compute the max pieces by recursively calling the function with `n - a`, `n - b`, `n - c`.
     * - If the result is `-1`, return `-1` (no valid cuts found).
     * - Otherwise, return `1 + result` to count the current cut.
     * 
     * Time Complexity:
     * - O(3^n) in the worst case, as we explore three possibilities per recursive call.
     * - Can be optimized using memoization (Dynamic Programming).
     * 
     * Space Complexity:
     * - O(n) due to recursive call stack depth.
     * 
     * @param n The length of the rope.
     * @param a The first possible cut length.
     * @param b The second possible cut length.
     * @param c The third possible cut length.
     * @return The maximum number of pieces the rope can be cut into, or -1 if not possible.
     */
    public static int cutRopeInMaxPeices(int n, int a, int b, int c) {
        if (n == 0)
            return 0;
        if (n < 0)
            return -1;
        int result = Math.max(cutRopeInMaxPeices(n - a, a, b, c),
                Math.max(cutRopeInMaxPeices(n - b, a, b, c), cutRopeInMaxPeices(n - c, a, b, c)));

        if (result == -1)
            return -1;
        return 1 + result;
    }
}
