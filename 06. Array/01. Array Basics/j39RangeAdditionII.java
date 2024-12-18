/**
 * Problem Statement:
 * 
 *     You are given a matrix of size `m x n` and a list of `q` operations. Each operation consists of two integers `a` and `b`, 
 *     and it means that all cells in the matrix within the rectangle defined by the top-left corner (1, 1) to (a, b) should be incremented.
 *     After performing all the operations, return the maximum number of cells in the matrix that have the same value.
 * 
 * Input:
 *     - Integers `m` and `n` representing the number of rows and columns in the matrix.
 *     - An integer `q` representing the number of operations.
 *     - A 2D array `ops` of size `q` where each element `ops[i]` is a pair of integers `a` and `b`.
 * 
 * Output:
 *     - Return the maximum number of cells in the matrix that have the same value after all operations.
 * 
 * Example:
 *     Input:
 *     3 3 3
 *     2 2
 *     3 3
 *     2 3
 *     
 *     Output:
 *     4
 * 
 *     Explanation:
 *     After applying the operations, the matrix will look like this:
 *     [[2, 2, 2],
 *      [2, 2, 2],
 *      [2, 2, 2]]
 *     The maximum number of cells with the same value is 4 (the top-left 2x2 submatrix).
 */

import java.util.Scanner;

public class j39RangeAdditionII {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Input: number of rows in the matrix
        int n = in.nextInt(); // Input: number of columns in the matrix
        int q = in.nextInt(); // Input: number of operations
        int[][] ops = new int[q][2]; // Array to hold operations
        for (int i = 0; i < q; i++) {
            ops[i][0] = in.nextInt(); // Operation: top-left row limit
            ops[i][1] = in.nextInt(); // Operation: top-left column limit
        }

        // Call the solution
        System.out.println(maxCount(m, n, ops)); // Output: the maximum number of cells with the same value

        in.close();
    }

    /**
     * Approach 1: Optimized Range Approach (O(q))
     * 
     * Intuition:
     * - Instead of applying each operation to the entire submatrix, we can focus on the effect of the 
     *   operation on the "smallest" possible submatrix.
     * - We compute the smallest row and column indices across all operations, which defines the region 
     *   that will have the highest value after all operations.
     * - This smallest region will have the maximum number of cells with the same value, which corresponds 
     *   to the minimum `a` and `b` from the operations.
     * 
     * Time Complexity:
     * - O(q), where `q` is the number of operations. We iterate over each operation to find the minimum `a` and `b`.
     * 
     * Space Complexity:
     * - O(1), no additional space is used apart from input storage.
     * 
     * @param m The number of rows in the matrix.
     * @param n The number of columns in the matrix.
     * @param ops The list of operations to perform.
     * @return The maximum number of cells with the same value.
     */
    public static int maxCount(int m, int n, int[][] ops) {
        int minM = Integer.MAX_VALUE;
        int minN = Integer.MAX_VALUE;

        // Find the smallest possible submatrix defined by the operations
        for (int i = 0; i < ops.length; i++) {
            minM = Math.min(ops[i][0], minM); // Min row limit
            minN = Math.min(ops[i][1], minN); // Min column limit
        }

        // If no operations, return the total number of cells
        if (ops.length == 0)
            return m * n;

        // Return the area of the smallest submatrix that is affected by all operations
        return minM * minN;
    }
}
