/**
 * LeetCode 931. Minimum Falling Path Sum
 * 
 * Problem Statement:
 *     Given an n x n array of integers matrix, return the minimum sum of any
 *     falling path through matrix. A falling path starts at any element in the
 *     first row and chooses one element from each row. The next row's choice
 *     must be in the same column, or the column to the left or the right.
 * 
 * Input:
 *     - matrix (1 <= n <= 200): square matrix; values can be negative.
 * 
 * Output:
 *     - The minimal possible sum among all valid falling paths.
 * 
 * Example:
 *     Input: [[2,1,3],[6,5,4],[7,8,9]]
 *     Output: 13
 * 
 *     Explanation:
 *     One minimal path: 1 (row0,col1) -> 5 (row1,col1) -> 7 (row2,col0).
 */
import java.util.Arrays;

public class j03MinFallingPathSum {
    /**
     * Approach 1: Top-Down DP (Memoization)
     * 
     * Intuition:
     * - For a cell (r,c), the minimal falling path sum starting there equals
     *   its value plus the minimum of the three permissible next steps in the
     *   next row: (r+1,c-1), (r+1,c), (r+1,c+1). This yields an optimal
     *   substructure. Overlapping subproblems arise across different starts.
     * 
     * Explanation:
     * - Define f(r,c) as the minimal sum from (r,c) to the bottom row.
     *   Step 1: Out-of-bounds columns are invalid; return +∞ (sentinel).
     *   Step 2: Last row base: return matrix[r][c].
     *   Step 3: Memo check; reuse dp[r][c] if computed.
     *   Step 4: Recurse to left-down, down, right-down and take the minimum.
     *   Step 5: Add current value and store in dp.
     * - The answer is min over all starts in row 0: min_c f(0,c).
     * 
     * Time Complexity: O(n^2) — each state computed once
     * Space Complexity: O(n^2) for memo + O(n) recursion depth
     * 
     * @param matrix n x n integer matrix
     * @return minimal falling path sum
     */
    public static int minFallingPathSum(int[][] matrix) {
        // Handle edge cases (null or empty matrix)
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        int n = matrix.length;
        int[][] dp = new int[n + 1][n + 1];    // memo table; extra row/col unused
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE); // mark as unknown
        }

        int minAns = Integer.MAX_VALUE;         // track minimal path among starts

        // Try all starting columns in the top row
        for (int i = 0; i < n; i++) {
            minAns = Math.min(minAns, minFallingPathSumMemo(matrix, dp, 0, i));
        }

        return minAns;
    }

    /**
     * Helper Method: minFallingPathSumMemo
     * 
     * Intuition:
     * - From (row,col), choose the minimal among the three allowed downward
     *   moves, then add current cell value.
     * 
     * Explanation:
     * - Guard invalid columns with +∞. Base case on last row returns value.
     *   Memoize results to avoid recomputation.
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * 
     * @param matrix input matrix
     * @param dp memo table initialized to Integer.MIN_VALUE
     * @param row current row index
     * @param col current column index
     * @return minimal falling path sum from (row,col)
     */
    public static int minFallingPathSumMemo(int[][] matrix, int[][] dp, int row, int col) {
        // Step A: Guard out-of-bounds columns; rows out-of-bounds end recursion
        if (row >= matrix.length || col < 0 || col >= matrix.length)
            return Integer.MAX_VALUE;           // +∞ so it won't be chosen
        // Step B: Last row: no further moves — return cell value
        if (row == matrix.length - 1)
            return matrix[row][col];
        // Step C: Return memoized value if available
        if (dp[row][col] != Integer.MIN_VALUE)
            return dp[row][col];

        // Step D: Explore the three permissible downward moves
        int leftDown = minFallingPathSumMemo(matrix, dp, row + 1, col - 1);
        int down = minFallingPathSumMemo(matrix, dp, row + 1, col);
        int rightDown = minFallingPathSumMemo(matrix, dp, row + 1, col + 1);

        // Step E: Combine current value with minimal child path cost
        return dp[row][col] = Math.min(down, Math.min(leftDown, rightDown)) + matrix[row][col];
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - Each cell's minimal falling path sum depends only on the three cells
     *   directly below it in the next row. Filling the DP table from the last
     *   row upward ensures dependencies are ready when needed.
     * 
     * Explanation:
     * - Create an n x n dp table where dp[r][c] stores the minimal falling
     *   path sum starting at (r,c).
     *   Step 1: Initialize the last row with the matrix values themselves.
     *   Step 2: For rows r = n-2..0 and columns c = 0..n-1, compute:
     *           dp[r][c] = matrix[r][c] + min(dp[r+1][c-1], dp[r+1][c], dp[r+1][c+1])
     *           with column bounds checks for c-1 and c+1.
     *   Step 3: Answer is min over dp[0][c] for c in [0..n-1].
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2) (can be optimized to O(n) with rolling arrays)
     * 
     * @param matrix n x n integer matrix
     * @return minimal falling path sum
     */
    public static int minFallingPathSumTabulation(int[][] matrix) {
        // Handle edge cases (null or empty)
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        int n = matrix.length;
        int[][] dp = new int[n][n];             // dp[r][c] = minimal sum from (r,c)

        for(int row = n - 1; row >= 0; row--){
            for(int col = 0; col < n; col++){
                if(row == n - 1){
                    dp[row][col] = matrix[row][col]; // base: last row equals itself
                }else{
                    // Start with vertical down
                    dp[row][col] = dp[row + 1][col];
                    // Consider down-left if within bounds
                    if(col - 1 >= 0){
                        dp[row][col] = Math.min(dp[row][col],dp[row + 1][col - 1]);
                    }
                    // Consider down-right if within bounds
                    if(col + 1 < n){
                        dp[row][col] = Math.min(dp[row][col],dp[row + 1][col + 1]);
                    }
                    // Add current cell value after selecting best child
                    dp[row][col] += matrix[row][col];
                }
            }
        }
        
        int minAns = Integer.MAX_VALUE;         // minimal among top-row starts
        for(int i = 0; i < n; i++) minAns = Math.min(dp[0][i],minAns);
        return minAns;
    }

    public static void main(String[] args) {
        // Basic / Happy path cases
        System.out.println("\nBasic Test Cases:");
        int[][] m1 = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(minFallingPathSum(m1) + "  (Expected: 13)");
        int[][] m2 = {{-19,57},{-40,-5}};
        System.out.println(minFallingPathSum(m2) + "  (Expected: -59)");

        // Edge cases (null, empty)
        System.out.println("\nEdge Cases:");
        System.out.println(minFallingPathSum(null) + "  (Expected: 0)");
        System.out.println(minFallingPathSum(new int[][]{}) + "  (Expected: 0)");

        // Boundary cases (single row)
        System.out.println("\nBoundary Cases:");
        int[][] m3 = {{5,1,2}};
        System.out.println(minFallingPathSum(m3) + "  (Expected: 1)");

        // Special / Larger case
        System.out.println("\nSpecial/Large Cases:");
        int[][] m4 = {
            {1,2,3,4},
            {4,5,6,7},
            {7,8,1,2},
            {9,1,2,3}
        };
        System.out.println(minFallingPathSum(m4) + "  (Sample output)");
    }
}
