/**
 * LeetCode 265. Paint House II
 * 
 * Problem Statement:
 *     There are a row of n houses, each house can be painted with one of the k
 *     colors. The cost of painting each house with a certain color is different.
 *     You have to paint all the houses such that no two adjacent houses have the
 *     same color. The cost of painting each house with a certain color is
 *     represented by an n x k cost matrix. For example, costs[0][0] is the cost
 *     of painting house 0 with color 0; costs[1][2] is the cost of painting
 *     house 1 with color 2, and so on. Find the minimum cost to paint all houses.
 * 
 * Input:
 *     - n (1 <= n <= 10^5) - number of houses
 *     - k (1 <= k <= 10^5) - number of colors
 *     - costs (n x k matrix, 0 <= costs[i][j] <= 10^4) - cost of painting house
 *       i with color j
 * 
 * Output:
 *     - Integer representing the minimum cost to paint all houses
 * 
 * Example:
 *     Input: n = 4, k = 3, costs = [[1,5,3],[2,9,4]]
 *     Output: 5
 * 
 *     Explanation:
 *     Paint house 0 into color 0, house 1 into color 2.
 *     Minimum cost: 1 + 4 = 5.
 */
import java.util.Arrays;

public class j05PaintHouseII {

    /**
     * Approach: Top-Down DP with Memoization (State = house, lastColor)
     * 
     * Intuition:
     * - At each house, we have k color choices but cannot choose the same color
     *   as the previous house. We model this as a DP over the house index and
     *   the last color used.
     * - Memoization ensures each state is computed once.
     * 
     * Explanation:
     * - Use dp[house][lastColor] where lastColor in {0,1,...,k-1,k} (k means no previous color)
     * - For each house, try all colors except the one from previous house
     * - Base case: house == 0, choose minimum cost color (excluding lastColor)
     * - Recurse to previous house with current color as lastColor
     * 
     * Time Complexity: O(n * (k+1) * k) = O(n * k^2) - Each state computed once
     * Space Complexity: O(n * (k+1)) - DP table and recursion stack
     * 
     * @param n       Number of houses to paint
     * @param k       Number of colors available
     * @param costs   n x k matrix where costs[i][j] = cost of painting house i
     *                with color j. null/empty treated as 0.
     * @return        Minimum cost to paint all houses with no adjacent same colors
     */
    public static int paintCost(int n, int k, int[][] costs) {
        // Handle edge cases: invalid input
        if (n <= 0 || k <= 0 || costs == null || costs.length == 0) {
            return 0; // No houses to paint
        }

        // Initialize DP table: n rows for houses, k+1 columns for lastColor (0,1,...,k-1,k)
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1); // Mark all states as uncomputed
        }

        // Start from the last house (n-1) with no previous color (lastColor = k)
        return findMinCost(k, costs, dp, n - 1, k);
    }

    /**
     * Helper Method: findMinCost
     * 
     * Intuition:
     * - Recursive DP function that finds the minimum cost for houses [0, house]
     *   given that the last color used was 'lastColor'.
     * 
     * Explanation:
     * - Base case: house == 0, choose minimum cost color excluding lastColor
     * - For house > 0:
     *   - Try all colors (0,1,...,k-1) except lastColor
     *   - For each valid color, add current cost and recurse to house-1
     *   - Take minimum over all valid choices
     * - Memoize result in dp[house][lastColor]
     * 
     * Time Complexity: O(1) per state transition, overall O(n * k^2)
     * Space Complexity: O(n * k) due to recursion depth and memo table
     * 
     * @param k         Number of colors available
     * @param costs     n x k matrix of painting costs for each house and color
     * @param dp        Memo table where dp[house][lastColor] stores best answer
     * @param house     Current house under consideration
     * @param lastColor Last color used (0,1,...,k-1) or k if no previous color
     * @return          Minimum cost for houses [0, house]
     */
    public static int findMinCost(int k, int[][] costs, int[][] dp, int house, int lastColor) {
        // Base case: first house (house == 0)
        if (house == 0) {
            int minCost = Integer.MAX_VALUE;
            // Try all colors except the one from previous house
            for (int i = 0; i < k; i++) {
                if (i != lastColor) {
                    minCost = Math.min(minCost, costs[house][i]);
                }
            }
            return dp[house][lastColor] = minCost;
        }

        // Return memoized answer if this state was computed before
        if (dp[house][lastColor] != -1) {
            return dp[house][lastColor];
        }

        // For other houses, try all colors except the one from previous house
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (i != lastColor) {
                // Choose color i for current house and recurse to previous house
                minCost = Math.min(minCost, 
                    findMinCost(k, costs, dp, house - 1, i) + costs[house][i]);
            }
        }

        // Memoize and return the best result
        return dp[house][lastColor] = minCost;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] costs1 = {{1, 5, 3}, {2, 9, 4}};
        System.out.println("Input: n=2, k=3, costs=[[1,5,3],[2,9,4]], Expected: 5, Output: " +
                paintCost(2, 3, costs1));

        int[][] costs2 = {{1, 3}, {2, 4}};
        System.out.println("Input: n=2, k=2, costs=[[1,3],[2,4]], Expected: 5, Output: " +
                paintCost(2, 2, costs2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=0, k=3, costs=[], Expected: 0, Output: " +
                paintCost(0, 3, new int[][]{}));
        System.out.println("Input: n=1, k=1, costs=[[5]], Expected: 5, Output: " +
                paintCost(1, 1, new int[][]{{5}}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] costs3 = {{1, 1, 1}};
        System.out.println("Input: n=1, k=3, costs=[[1,1,1]], Expected: 1, Output: " +
                paintCost(1, 3, costs3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] costs4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Input: n=3, k=3, costs=[[1,2,3],[4,5,6],[7,8,9]], Expected: 12, Output: " +
                paintCost(3, 3, costs4));

        // Complex/Large Input
        System.out.println("\nComplex/Large Input:");
        int[][] costs5 = {{100, 50, 25, 10}, {50, 100, 25, 10}, {25, 50, 100, 10}};
        System.out.println("Input: n=3, k=4, costs=[[100,50,25,10],[50,100,25,10],[25,50,100,10]], Expected: 35, Output: " +
                paintCost(3, 4, costs5));
    }
}
