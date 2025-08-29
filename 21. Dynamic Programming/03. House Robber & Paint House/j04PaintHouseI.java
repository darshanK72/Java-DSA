/**
 * LeetCode 256. Paint House
 * 
 * Problem Statement:
 *     There are a row of n houses, each house can be painted with one of the
 *     three colors: red, blue, or green. The cost of painting each house with
 *     a certain color is different. You have to paint all the houses such that
 *     no two adjacent houses have the same color. The cost of painting each
 *     house with a certain color is represented by an n x 3 cost matrix. For
 *     example, costs[0][0] is the cost of painting house 0 with the color red;
 *     costs[1][2] is the cost of painting house 1 with color green, and so on.
 *     Find the minimum cost to paint all houses.
 * 
 * Input:
 *     - costs (n x 3 matrix, 1 <= n <= 10^5, 0 <= costs[i][j] <= 10^4) - cost
 *       of painting house i with color j
 * 
 * Output:
 *     - Integer representing the minimum cost to paint all houses
 * 
 * Example:
 *     Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 *     Output: 10
 * 
 *     Explanation:
 *     Paint house 0 into blue, house 1 into green, house 2 into blue.
 *     Minimum cost: 2 + 5 + 3 = 10.
 */
import java.util.Arrays;

public class j04PaintHouseI {

    /**
     * Approach: Top-Down DP with Memoization (State = house, lastColor)
     * 
     * Intuition:
     * - At each house, we have 3 color choices (red=0, blue=1, green=2) but
     *   cannot choose the same color as the previous house. We model this as
     *   a DP over the house index and the last color used.
     * - Memoization ensures each state is computed once.
     * 
     * Explanation:
     * - Use dp[house][lastColor] where lastColor in {0,1,2,3} (3 means no previous color)
     * - For each house, try all colors except the one from previous house
     * - Base case: house == 0, choose minimum cost color (excluding lastColor)
     * - Recurse to previous house with current color as lastColor
     * 
     * Time Complexity: O(n * 4 * 3) = O(n) - Each state computed once
     * Space Complexity: O(n * 4) - DP table and recursion stack
     * 
     * @param cost   n x 3 matrix where cost[i][j] = cost of painting house i
     *               with color j. null/empty treated as 0.
     * @return       Minimum cost to paint all houses with no adjacent same colors
     */
    public static int minCost(int[][] cost) {
        // Handle edge cases: null or empty input
        if (cost == null || cost.length == 0) {
            return 0; // No houses to paint
        }

        // Initialize DP table: n rows for houses, 4 columns for lastColor (0,1,2,3)
        int n = cost.length;
        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1); // Mark all states as uncomputed
        }

        // Start from the last house (n-1) with no previous color (lastColor = 3)
        return findMinCost(cost, dp, n - 1, 3);
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
     *   - Try all colors (0,1,2) except lastColor
     *   - For each valid color, add current cost and recurse to house-1
     *   - Take minimum over all valid choices
     * - Memoize result in dp[house][lastColor]
     * 
     * Time Complexity: O(1) per state transition, overall O(n)
     * Space Complexity: O(n) due to recursion depth and memo table
     * 
     * @param cost      n x 3 matrix of painting costs for each house and color
     * @param dp        Memo table where dp[house][lastColor] stores best answer
     * @param house     Current house under consideration
     * @param lastColor Last color used (0,1,2) or 3 if no previous color
     * @return          Minimum cost for houses [0, house]
     */
    public static int findMinCost(int[][] cost, int[][] dp, int house, int lastColor) {
        // Base case: first house (house == 0)
        if (house == 0) {
            int minCost = Integer.MAX_VALUE;
            // Try all colors except the one from previous house
            for (int i = 0; i <= 2; i++) {
                if (i != lastColor) {
                    minCost = Math.min(minCost, cost[house][i]);
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
        for (int i = 0; i <= 2; i++) {
            if (i != lastColor) {
                // Choose color i for current house and recurse to previous house
                minCost = Math.min(minCost, 
                    findMinCost(cost, dp, house - 1, i) + cost[house][i]);
            }
        }

        // Memoize and return the best result
        return dp[house][lastColor] = minCost;
    }

    /*-
     * Approach 2: Bottom-Up DP with Tabulation
     * 
     * Intuition:
     * - Build the solution iteratively from the first house to the last house.
     * - For each house, we compute the minimum cost for all possible last colors
     *   (0,1,2,3) by considering the costs from the previous house and adding
     *   the current house's painting cost for each valid color choice.
     * - This eliminates recursion overhead and builds the solution systematically.
     * 
     * Explanation:
     * - Step 1: Initialize dp[house][lastColor] where lastColor in {0,1,2,3}.
     *   lastColor represents the color used for the previous house (3 means no previous).
     * - Step 2: Base case: For house 0, compute dp[0][lastColor] as the minimum
     *   cost of painting house 0 with any color except lastColor.
     * - Step 3: For each subsequent house, for each possible lastColor, try all
     *   colors except lastColor and take the minimum cost.
     * - Step 4: The answer is dp[n-1][3] (minimum cost for all houses with no
     *   previous color constraint).
     * 
     * Time Complexity: O(n * 4 * 3) = O(n) - Each house considers 4 last colors
     *                  and 3 color choices
     * Space Complexity: O(n * 4) - DP table size
     * 
     * @param cost   n x 3 matrix where cost[i][j] = cost of painting house i
     *               with color j (0=red, 1=blue, 2=green)
     * @return       Minimum cost to paint all houses with no adjacent same colors
     */
    public static int minCostTabulation(int[][] cost) {
        // Handle edge cases: null or empty input
        if (cost == null || cost.length == 0) {
            return 0; // No houses to paint
        }
        
        int n = cost.length;                    // Number of houses to paint
        int[][] dp = new int[n][4];             // dp[house][lastColor] = min cost
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i],-1);              // Initialize all states as uncomputed
        }

        // Base case: Compute minimum cost for first house (house 0)
        // dp[0][lastColor] = min cost to paint house 0 with any color except lastColor
        dp[0][0] = Math.min(cost[0][1],cost[0][2]);  // House 0 painted with min of blue/green
        dp[0][1] = Math.min(cost[0][0],cost[0][2]);  // House 0 painted with min of red/green
        dp[0][2] = Math.min(cost[0][0],cost[0][1]);  // House 0 painted with min of red/blue
        dp[0][3] = Math.min(cost[0][0],Math.min(cost[0][1],cost[0][2])); // House 0 with any color

        // Build solution iteratively for remaining houses
        for(int day = 1; day < n; day++){
            // For each possible last color used in previous house
            for(int last = 0; last <= 3; last++){
                int minCost = Integer.MAX_VALUE;     // Track minimum cost for current state
                // Try all colors (0=red, 1=blue, 2=green) except the last color
                for(int i = 0; i <= 2; i++){
                    if(i != last){
                        // Candidate cost: previous house cost + current house color cost
                        minCost = Math.min(minCost,cost[day][i] + dp[day-1][i]); 
                    }
                }
                dp[day][last] = minCost;             // Store minimum cost for this state
            }
        }
        
        // Return minimum cost for all houses with no previous color constraint
        return dp[n-1][3];
    }   

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] costs1 = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        System.out.println("Input: [[17,2,17],[16,16,5],[14,3,19]], Expected: 10, Output: " +
                minCost(costs1));

        int[][] costs2 = {{7, 6, 2}};
        System.out.println("Input: [[7,6,2]], Expected: 2, Output: " +
                minCost(costs2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: " +
                minCost(new int[][]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                minCost(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] costs3 = {{1, 1, 1}};
        System.out.println("Input: [[1,1,1]], Expected: 1, Output: " +
                minCost(costs3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] costs4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Input: [[1,2,3],[4,5,6],[7,8,9]], Expected: 12, Output: " +
                minCost(costs4));

        // Complex/Large Input
        System.out.println("\nComplex/Large Input:");
        int[][] costs5 = {{100, 50, 25}, {50, 100, 25}, {25, 50, 100}, {100, 25, 50}};
        System.out.println("Input: [[100,50,25],[50,100,25],[25,50,100],[100,25,50]], Expected: 100, Output: " +
                minCost(costs5));
    }
}
