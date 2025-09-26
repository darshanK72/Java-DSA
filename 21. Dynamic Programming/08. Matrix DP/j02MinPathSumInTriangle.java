/**
 * LeetCode 120. Triangle (Minimum Path Sum in Triangle)
 * 
 * Problem Statement:
 *     Given a triangle array, return the minimum path sum from top to bottom.
 *     At each step, you may move to adjacent numbers on the row below.
 * 
 * Input:
 *     - triangle (1 <= rows <= 200): List<List<Integer>> where row i has i+1
 *       elements; values can be negative or positive.
 * 
 * Output:
 *     - The minimum total from triangle[0][0] down to the last row.
 * 
 * Example:
 *     Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 *     Output: 11
 * 
 *     Explanation:
 *     The path 2 -> 3 -> 5 -> 1 yields a minimal sum of 11.
 */
import java.util.Arrays;
import java.util.List;

public class j02MinPathSumInTriangle {
    /**
     * Approach 1: Top-Down DP (Memoization)
     * 
     * Intuition:
     * - At position (r,c), the next row offers two adjacent choices: (r+1,c)
     *   and (r+1,c+1). The minimal cost from (r,c) equals the triangle value
     *   at (r,c) plus the minimum of the minimal costs from those two choices.
     * - Subproblems overlap because many (r,c) pairs are revisited from
     *   different paths. Memoization avoids recomputation.
     * 
     * Explanation:
     * - Define f(r,c) as the minimal path sum from (r,c) to the bottom.
     *   Step 1: Base row (r == last): return triangle[r][c].
     *   Step 2: If dp[r][c] computed, reuse.
     *   Step 3: Recurse to f(r+1,c) and f(r+1,c+1).
     *   Step 4: dp[r][c] = triangle[r][c] + min(f(r+1,c), f(r+1,c+1)).
     * - Initialize dp with a sentinel (Integer.MIN_VALUE) to denote unknowns.
     * 
     * Time Complexity: O(n^2) where n = number of rows
     * Space Complexity: O(n^2) for memo + O(n) recursion depth
     * 
     * @param triangle triangular list of rows
     * @return minimal path sum from top to bottom
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        // Handle edge cases (null or empty triangle)
        if (triangle == null || triangle.size() == 0)
            return 0;
        int m = triangle.size();
        int[][] dp = new int[m][];              // jagged memo table per row
        for (int i = 0; i < m; i++) {
            dp[i] = new int[triangle.get(i).size()];
            Arrays.fill(dp[i], Integer.MIN_VALUE); // mark as unknown
        }
        // Start recursion from the apex cell (0,0)
        return minPathSumMemo(triangle, dp, 0, 0);
    }

    /**
     * Helper Method: minPathSumMemo
     * 
     * Intuition:
     * - From (row,col), you can go down to the two adjacent positions below.
     *   Choose the cheaper subpath and add current value.
     * 
     * Explanation:
     * - Base case: last row returns its own value.
     * - Memoization: reuse dp[row][col] when available.
     * - Recurrence: value(row,col) + min(down, downRight).
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * 
     * @param triangle triangle values
     * @param dp memo table (Integer.MIN_VALUE means unknown)
     * @param row current row index
     * @param col current column index in this row
     * @return minimal path sum from (row,col) to bottom
     */
    public static int minPathSumMemo(List<List<Integer>> triangle, int[][] dp, int row, int col) {
        // Step A: If at the last row, return the value at this leaf node
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }
        // Step B: If already computed, return memoized value
        if (dp[row][col] != Integer.MIN_VALUE)
            return dp[row][col];

        // Step C: Recurse into the two possible children
        int down = minPathSumMemo(triangle, dp, row + 1, col);       // go down
        int downRight = minPathSumMemo(triangle, dp, row + 1, col + 1); // go diag

        // Step D: Combine current value with the better child path
        return dp[row][col] = triangle.get(row).get(col) + Math.min(down, downRight);
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - Each cell depends only on the two cells directly below it. If we fill
     *   from the bottom row upwards, we can compute each dp[row][col] in O(1)
     *   using previously filled values.
     * 
     * Explanation:
     * - Allocate a square dp array of size m x m (unused cells remain 0). Fill
     *   from the last row towards the top:
     *   Step 1: Last row dp = triangle values.
     *   Step 2: For each cell above, dp[row][col] = value + min(dp[row+1][col],
     *          dp[row+1][col+1]).
     *   Step 3: Answer is dp[0][0].
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2) (can be optimized to O(n) with 1D dp)
     * 
     * @param triangle triangular list of rows
     * @return minimal path sum from top to bottom
     */
    public static int minimumTotalTabulation(List<List<Integer>> triangle) {
        // Handle edge cases (null or empty triangle)
        if (triangle == null || triangle.size() == 0)
            return 0;
        int m = triangle.size();
        int[][] dp = new int[m][m];             // square table for simplicity

        for(int row = m - 1; row >= 0; row--){
            for(int col = triangle.get(row).size() - 1; col >= 0; col--){
                if(row == triangle.size() - 1){
                    dp[row][col] = triangle.get(row).get(col); // base row
                }else{
                    // Transition: value + min(child below and below-right)
                    dp[row][col] = Math.min(dp[row + 1][col],dp[row + 1][col + 1])
                                   + triangle.get(row).get(col);
                }
            }
        }

        return dp[0][0];                        // minimal total at the apex

    }

    public static void main(String[] args) {
        // Basic / Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println(minimumTotal(Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3,4),
            Arrays.asList(6,5,7),
            Arrays.asList(4,1,8,3)
        )) + "  (Expected: 11)");

        // Edge cases (null, empty)
        System.out.println("\nEdge Cases:");
        System.out.println(minimumTotal(null) + "  (Expected: 0)");
        System.out.println(minimumTotal(Arrays.asList()) + "  (Expected: 0)");

        // Boundary cases (single row)
        System.out.println("\nBoundary Cases:");
        System.out.println(minimumTotal(Arrays.asList(
            Arrays.asList(5)
        )) + "  (Expected: 5)");

        // Special / Larger case
        System.out.println("\nSpecial/Large Cases:");
        System.out.println(minimumTotalTabulation(Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(2,3),
            Arrays.asList(3,6,7),
            Arrays.asList(8,9,6,10),
            Arrays.asList(15,1,2,3,4)
        )) + "  (Sample output)");
    }
}
