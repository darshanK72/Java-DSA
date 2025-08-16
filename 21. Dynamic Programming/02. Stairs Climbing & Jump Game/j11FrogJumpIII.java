/**
 * Frog Jump III - Can Cross River with Stones
 * 
 * Problem Statement:
 *     A frog is crossing a river. The river is divided into some number of units, 
 *     and at each unit, there may or may not exist a stone. The frog can jump on 
 *     a stone, but it must not jump into the water. Given a list of stones' 
 *     positions (in units) in sorted ascending order, determine if the frog can 
 *     cross the river by landing on the last stone. Initially, the frog is on 
 *     the first stone and assumes the first jump must be 1 unit.
 * 
 * Input:
 *     - stones: int[] (sorted ascending order, 0 <= stones[i] <= 2^31 - 1)
 * 
 * Output:
 *     - boolean: true if frog can cross, false otherwise
 * 
 * Example:
 *     Input: stones = [0,1,3,5,6,8,12,17]
 *     Output: true
 * 
 *     Explanation:
 *     The frog can jump to the last stone by jumping 1 unit to the 2nd stone, 
 *     then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units 
 *     to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
 * 
 *     Input: stones = [0,1,2,3,4,8,9,11]
 *     Output: false
 * 
 *     Explanation:
 *     There is no way to jump to the last stone as the gap between the 5th and 
 *     6th stone is too large.
 */

import java.util.*;

public class j11FrogJumpIII {

    /**
     * Approach 1: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any stone, the frog can jump k-1, k, or k+1 units where k is the previous jump
     * - We need to track both the current stone position and the previous jump size
     * - Use a HashMap to map stone positions to their indices for O(1) lookup
     * - Use memoization to avoid recalculating overlapping subproblems
     * 
     * Explanation:
     * - Step 1: Create a HashMap to map stone positions to their indices
     * - Step 2: Use a 2D DP array to store results for (stone_index, prev_jump)
     * - Step 3: Recursively try jumping k-1, k, and k+1 units from current stone
     * - Step 4: Check if the target position exists in the stones array
     * - Step 5: Return true if any path leads to the last stone
     * 
     * Time Complexity: O(n * k) where n is number of stones and k is max jump size
     * Space Complexity: O(n * k) for DP array and recursion stack
     * 
     * @param stones  Array of stone positions in ascending order
     * @return        true if frog can cross the river, false otherwise
     */
    public static boolean canCross(int[] stones) {
        int n = stones.length;
        
        // Handle edge cases
        if (n == 0) return false;
        if (n == 1) return true;
        
        // Create HashMap to map stone positions to their indices for O(1) lookup
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }
        
        // Initialize 2D DP array with -1 to mark uncomputed states
        // dp[i][j] represents if we can reach the end from stone i with previous jump j
        int[][] dp = new int[n][n + 1]; // n+1 to handle jump sizes up to n
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        // Start from first stone (index 0) with previous jump of 0 (first jump)
        return canCrossDP(stones, map, dp, 0, 0) == 1;
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current stone, try jumping prevJump-1, prevJump, and prevJump+1 units
     * - Check if the target position exists in the stones array
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've reached the last stone (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try jumping prevJump-1, prevJump, and prevJump+1 units
     * - Step 4: Check if target position exists and recursively solve
     * - Step 5: Store and return the computed result
     * 
     * Time Complexity: O(1) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param stones    Array of stone positions
     * @param map       HashMap mapping stone positions to indices
     * @param dp        2D memoization array
     * @param currIndex Current stone index
     * @param prevJump  Previous jump size (0 for first jump)
     * @return          1 if can cross, 0 otherwise
     */
    public static int canCrossDP(int[] stones, HashMap<Integer, Integer> map, 
                                int[][] dp, int currIndex, int prevJump) {
        // Base case: if we've reached the last stone, we can cross
        if (currIndex == stones.length - 1) {
            return 1;
        }
        
        // Return memoized result if already computed
        if (dp[currIndex][prevJump] != -1) {
            return dp[currIndex][prevJump];
        }
        
        // Try jumping prevJump-1, prevJump, and prevJump+1 units
        // For the first jump, prevJump is 0, so we try 0, 1 (but 0 means no jump)
        int[] jumpSizes = {prevJump - 1, prevJump, prevJump + 1};
        
        for (int jump : jumpSizes) {
            // Skip invalid jumps (negative or zero for non-first jump)
            if (jump <= 0 && currIndex > 0) continue;
            
            // Calculate target position
            int targetPosition = stones[currIndex] + jump;
            
            // Check if target position exists in stones array
            if (map.containsKey(targetPosition)) {
                int targetIndex = map.get(targetPosition);
                
                // Recursively check if we can cross from target stone
                if (canCrossDP(stones, map, dp, targetIndex, jump) == 1) {
                    return dp[currIndex][prevJump] = 1;
                }
            }
        }
        
        // If no jump leads to success, return failure
        return dp[currIndex][prevJump] = 0;
    }

    /**
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Instead of using recursion, we use iterative approach
     * - For each stone, we track which jump sizes can reach it
     * - We build the solution by checking all possible jumps from each stone
     * 
     * Explanation:
     * - Step 1: Create a HashMap to map stone positions to their indices
     * - Step 2: Use a 2D boolean array to track reachable jump sizes for each stone
     * - Step 3: For each stone, check all possible jumps from previous stones
     * - Step 4: Mark reachable jump sizes for current stone
     * - Step 5: Return true if last stone has any reachable jump size
     * 
     * Time Complexity: O(n * k) where n is number of stones and k is max jump size
     * Space Complexity: O(n * k) for DP array
     * 
     * @param stones  Array of stone positions in ascending order
     * @return        true if frog can cross the river, false otherwise
     */
    public static boolean canCrossTabulation(int[] stones) {
        int n = stones.length;
        
        // Handle edge cases
        if (n == 0) return false;
        if (n == 1) return true;
        
        // Create HashMap to map stone positions to their indices
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }
        
        // dp[i][j] = true if we can reach stone i with jump size j
        boolean[][] dp = new boolean[n][n + 1];
        
        // Base case: first stone can be reached with jump size 0 (starting position)
        dp[0][0] = true;
        
        // For each stone, check all possible jumps from previous stones
        for (int i = 0; i < n; i++) {
            for (int jump = 0; jump <= n; jump++) {
                if (dp[i][jump]) {
                    // Try jumping jump-1, jump, and jump+1 units from current stone
                    for (int nextJump = Math.max(1, jump - 1); 
                         nextJump <= jump + 1; nextJump++) {
                        
                        int targetPosition = stones[i] + nextJump;
                        
                        // Check if target position exists in stones array
                        if (map.containsKey(targetPosition)) {
                            int targetIndex = map.get(targetPosition);
                            dp[targetIndex][nextJump] = true;
                        }
                    }
                }
            }
        }
        
        // Check if last stone can be reached with any jump size
        for (int jump = 0; jump <= n; jump++) {
            if (dp[n - 1][jump]) {
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: [0,1,3,5,6,8,12,17], Expected: true");
        System.out.println("Memoization Output: " + canCross(new int[]{0,1,3,5,6,8,12,17}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,1,3,5,6,8,12,17}));
        
        System.out.println("\nInput: [0,1,2,3,4,8,9,11], Expected: false");
        System.out.println("Memoization Output: " + canCross(new int[]{0,1,2,3,4,8,9,11}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,1,2,3,4,8,9,11}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [0], Expected: true");
        System.out.println("Memoization Output: " + canCross(new int[]{0}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0}));
        
        System.out.println("Input: [0,1], Expected: true");
        System.out.println("Memoization Output: " + canCross(new int[]{0,1}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,1,2], Expected: true");
        System.out.println("Memoization Output: " + canCross(new int[]{0,1,2}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,1,2}));
        
        System.out.println("Input: [0,2], Expected: false");
        System.out.println("Memoization Output: " + canCross(new int[]{0,2}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,2}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [0,1,3,6,10,15,21], Expected: true");
        System.out.println("Memoization Output: " + canCross(new int[]{0,1,3,6,10,15,21}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,1,3,6,10,15,21}));
        
        System.out.println("Input: [0,1,3,6,10,15,16], Expected: false");
        System.out.println("Memoization Output: " + canCross(new int[]{0,1,3,6,10,15,16}));
        System.out.println("Tabulation Output: " + canCrossTabulation(new int[]{0,1,3,6,10,15,16}));
    }
}
