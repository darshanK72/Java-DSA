/**
 * LeetCode 198. House Robber
 * 
 * Problem Statement:
 *     You are a professional robber planning to rob houses along a street. Each
 *     house has a certain amount of money stashed, the only constraint stopping
 *     you from robbing each of them is that adjacent houses have security
 *     systems connected and it will automatically contact the police if two
 *     adjacent houses were broken into on the same night. Given an integer array
 *     nums representing the amount of money of each house, return the maximum
 *     amount of money you can rob tonight without alerting the police.
 * 
 * Input:
 *     - nums (0 <= nums.length <= 10^5, 0 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - Integer representing the maximum sum of non-adjacent elements.
 * 
 * Example:
 *     Input: nums = [1,2,3,1]
 *     Output: 4
 * 
 *     Explanation:
 *     Rob house 1 (money = 1) and then rob house 3 (money = 3). Total = 1 + 3
 *     = 4.
 */
import java.util.Arrays;

public class j01HouseRobberI {

    /**
     * Approach: Top-Down DP with Memoization (State = index, prevState)
     * 
     * Intuition:
     * - At each house we have a binary choice: rob or skip. We cannot rob two
     *   adjacent houses. We model this as a DP over the index and whether the
     *   previous house was taken.
     * - Memoization ensures each state is computed once.
     * 
     * Explanation:
     * - Use dp[prevState][index] where prevState in {0: prev not taken,
     *   1: prev taken}. If prevState == 0, we may take current house and add
     *   nums[index]. In all cases, we may skip current house.
     * - Recurse to next index with appropriate prevState and take max.
     * 
     * Time Complexity: O(n) — There are 2*n states, each computed once.
     * Space Complexity: O(n) — Recursion stack O(n) and DP table O(2*n).
     * 
     * @param nums    Input array of non-negative integers representing money in
     *                each house. null/empty treated as 0.
     * @return        Maximum money that can be robbed without robbing adjacent
     *                houses.
     */
    public static int rob(int[] nums) {
        // Handle edge cases: null or empty input yields 0 profit
        if (nums == null || nums.length == 0) {
            return 0; // No houses to rob
        }

        // Initialize memo table: 2 rows for prevState (0 or 1), columns for index
        int n = nums.length;
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], -1); // Mark unseen states for prevState = 0
        Arrays.fill(dp[1], -1); // Mark unseen states for prevState = 1

        // Start at index 0 with prevState = 0 (previous not taken)
        return robMaxMoney(nums, dp, 0, 0);
    }

    /**
     * Helper Method: robMaxMoney
     * 
     * Intuition:
     * - Decide at each index based on whether the previous house was taken.
     *   Use memoization to avoid recomputation.
     * 
     * Explanation:
     * - Base case: when index == nums.length, no houses remain -> return 0.
     * - Memo check: if state is computed, reuse it from dp.
     * - Choice:
     *   - If prevState == 0, we can take current: value = nums[index] +
     *     recurse(index + 1, 1).
     *   - We can always skip current: value = recurse(index + 1, 0).
     *   - Take max of the two options.
     * - Store and return the result for dp[prevState][index].
     * 
     * Time Complexity: O(1) per state transition, overall O(n).
     * Space Complexity: O(n) due to recursion depth and memo table.
     * 
     * @param nums       Money in each house
     * @param dp         Memo table where dp[prevState][index] stores best answer
     * @param index      Current house index under consideration
     * @param prevState  0 if previous house not taken, 1 if taken
     * @return           Maximum money obtainable from index onward
     */
    public static int robMaxMoney(int[] nums, int[][] dp, int index, int prevState) {
        // Handle base case: no more houses left to consider
        if (index == nums.length) {
            return 0; // Nothing to rob
        }

        // Return memoized answer if this state was computed before
        if (dp[prevState][index] != -1) {
            return dp[prevState][index];
        }

        // Initialize answer for this state
        int ans = 0;

        // Option 1: If previous house was NOT taken, we may take this house
        if (prevState == 0) {
            // Take current house and move with prevState=1 (current taken)
            ans = robMaxMoney(nums, dp, index + 1, 1) + nums[index];
        }

        // Option 2: Skip current house and move with prevState=0 (still free)
        ans = Math.max(ans, robMaxMoney(nums, dp, index + 1, 0));

        // Memoize and return
        return dp[prevState][index] = ans;
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - Convert the top-down recurrence into an iterative DP where we build the
     *   answer from smaller prefixes of the array. Maintain two states per
     *   index i: dp[0][i] = best if house i is NOT taken, dp[1][i] = best if
     *   house i IS taken. This mirrors the adjacency constraint.
     * 
     * Explanation:
     * - Initialize base for i = 0 (no houses): both states are 0.
     * - For i from 1..n:
     *   - dp[0][i] = max(dp[0][i-1], dp[1][i-1])  // skip current
     *   - dp[1][i] = dp[0][i-1] + nums[i-1]       // take current
     * - Answer is max(dp[0][n], dp[1][n]).
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the 2 x (n+1) table (can be optimized to O(1)).
     * 
     * @param nums  Money in each house (null/empty treated as 0)
     * @return      Maximum money that can be robbed without robbing adjacent
     *              houses
     */
    public static int robTabulation(int[] nums) {
        // Handle edge cases: null or empty input yields 0
        if (nums == null || nums.length == 0) {
            return 0; // No profit possible
        }

        // Prepare DP table with two rows for states: 0 = not taken, 1 = taken
        int n = nums.length;
        int[][] dp = new int[2][n + 1];

        // Base initialization for i = 0 (no houses considered)
        dp[0][0] = 0; // Best if nothing taken is 0
        dp[1][0] = 0; // Best if taken state is also 0 when no items

        // Build table from i = 1 to n over prefix of houses
        for (int i = 1; i <= n; i++) {
            // Skip current house: take best from previous regardless of state
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
            // Take current house: previous must be 'not taken'
            dp[1][i] = dp[0][i - 1] + nums[i - 1];
        }

        // Final answer is the better of taking or skipping the last house
        return Math.max(dp[0][n], dp[1][n]);
    }

    /**
     * Approach 3: Backward DP with Memoization
     * 
     * Intuition:
     * - Instead of going forward from index 0, we solve backward from index n-1.
     *   At each index i, we have two choices: pick house i or skip it.
     * - If we pick house i, we cannot pick house i-1, so we add nums[i] to the
     *   result from house i-2.
     * - If we skip house i, we can pick house i-1, so we take the result from
     *   house i-1.
     * 
     * Explanation:
     * - Start from the last house (index n-1) and work backwards.
     * - Base cases: index < 0 returns 0, index == 0 returns nums[0].
     * - For each index i > 0:
     *   - pick = houseRobberBackward(i-2) + nums[i] (pick current, skip previous)
     *   - notPick = houseRobberBackward(i-1) (skip current, consider previous)
     *   - Return max(pick, notPick)
     * 
     * Time Complexity: O(n) - Each index is computed once due to memoization
     * Space Complexity: O(n) - DP array and recursion stack
     * 
     * @param nums    Input array of non-negative integers representing money in
     *                each house. null/empty treated as 0.
     * @return        Maximum money that can be robbed without robbing adjacent
     *                houses.
     */
    public static int rob1DMemoizationBackward(int[] nums) {
        // Handle edge cases: null or empty input yields 0 profit
        if (nums == null || nums.length == 0) {
            return 0; // No houses to rob
        }

        // Initialize DP array for memoization: dp[i] stores best result for houses [0, i]
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Mark all states as uncomputed

        // Start backward recursion from the last house (index n-1)
        return houseRobberBackward(nums, dp, n - 1);
    }

    /**
     * Helper Method: houseRobberBackward
     * 
     * Intuition:
     * - Recursive DP function that solves the problem for houses [0, index]
     *   by considering whether to pick or skip the house at 'index'.
     * 
     * Explanation:
     * - Base cases:
     *   - index < 0: no houses left -> return 0
     *   - index == 0: only one house -> return nums[0]
     * - For index > 0:
     *   - pick: take current house + best from houses [0, index-2]
     *   - notPick: skip current house + best from houses [0, index-1]
     *   - Return maximum of pick and notPick
     * 
     * Time Complexity: O(1) per state transition, overall O(n)
     * Space Complexity: O(n) due to recursion depth and memo array
     * 
     * @param nums   Money in each house
     * @param dp     Memo array where dp[index] stores best answer for [0, index]
     * @param index  Current house index under consideration (working backwards)
     * @return       Maximum money obtainable from houses [0, index]
     */
    public static int houseRobberBackward(int[] nums, int[] dp, int index) {
        // Base case: no houses left to consider
        if (index < 0) {
            return 0; // Nothing to rob
        }

        // Base case: only one house left (first house)
        if (index == 0) {
            return nums[index]; // Must take the only available house
        }

        // Return memoized answer if this state was computed before
        if (dp[index] != -1) {
            return dp[index];
        }

        // Option 1: Pick current house (index) - cannot pick previous (index-1)
        int pick = houseRobberBackward(nums, dp, index - 2) + nums[index];

        // Option 2: Skip current house (index) - can pick previous (index-1)
        int notPick = houseRobberBackward(nums, dp, index - 1);

        // Memoize and return the better option
        return dp[index] = Math.max(pick, notPick);
    }

    /**
     * Approach 4: 1D Tabulation (Forward DP)
     * 
     * Intuition:
     * - Use a 1D DP array where dp[i] represents the maximum money that can be
     *   robbed from houses [0, i]. At each house i, we have two choices:
     *   pick house i or skip it.
     * - If we pick house i, we add nums[i] to the result from house i-2.
     * - If we skip house i, we take the result from house i-1.
     * 
     * Explanation:
     * - Initialize dp[0] = nums[0] (base case: only one house)
     * - For each house i from 1 to n-1:
     *   - take = nums[i] + dp[i-2] (if i > 1, else just nums[i])
     *   - notTake = dp[i-1]
     *   - dp[i] = max(take, notTake)
     * - Return dp[n-1]
     * 
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(n) - DP array of size n
     * 
     * @param nums    Input array of non-negative integers representing money in
     *                each house. null/empty treated as 0.
     * @return        Maximum money that can be robbed without robbing adjacent
     *                houses.
     */
    public static int rob1DTabulation(int[] nums) {
        // Handle edge cases: null or empty input yields 0 profit
        if (nums == null || nums.length == 0) {
            return 0; // No houses to rob
        }

        // Special case: only one house, can rob it directly
        if (nums.length == 1) {
            return nums[0]; // Only option is to rob the single house
        }

        // Initialize DP array: dp[i] stores best result for houses [0, i]
        int n = nums.length;
        int[] dp = new int[n];

        // Base case: first house can always be robbed
        dp[0] = nums[0];

        // Fill DP table from index 1 to n-1
        for (int i = 1; i < n; i++) {
            // Option 1: Take current house (i) - add to result from house i-2
            int take = nums[i];
            if (i > 1) {
                take += dp[i - 2]; // Add result from house i-2 if it exists
            }

            // Option 2: Skip current house (i) - take result from house i-1
            int notTake = dp[i - 1];

            // Store the better option for houses [0, i]
            dp[i] = Math.max(take, notTake);
        }

        // Return the best result for all houses [0, n-1]
        return dp[n - 1];
    }

    /**
     * Approach 5: 1D Tabulation Optimized (Space O(1))
     * 
     * Intuition:
     * - Same logic as 1D tabulation but optimize space by using only two
     *   variables instead of a full DP array. We only need the previous two
     *   values to compute the current value.
     * - prev1 represents dp[i-1] and prev2 represents dp[i-2].
     * 
     * Explanation:
     * - Initialize prev1 = nums[0] (dp[0]) and prev2 = 0 (dp[-1] = 0)
     * - For each house i from 1 to n-1:
     *   - take = nums[i] + prev2 (if i > 1, else just nums[i])
     *   - notTake = prev1
     *   - curr = max(take, notTake)
     *   - Update: prev2 = prev1, prev1 = curr
     * - Return prev1
     * 
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(1) - Only two variables used
     * 
     * @param nums    Input array of non-negative integers representing money in
     *                each house. null/empty treated as 0.
     * @return        Maximum money that can be robbed without robbing adjacent
     *                houses.
     */
    public static int rob1DTabulationOptimized(int[] nums) {
        // Handle edge cases: null or empty input yields 0 profit
        if (nums == null || nums.length == 0) {
            return 0; // No houses to rob
        }

        // Special case: only one house, can rob it directly
        if (nums.length == 1) {
            return nums[0]; // Only option is to rob the single house
        }

        // Initialize variables: prev1 = dp[i-1], prev2 = dp[i-2]
        int n = nums.length;
        int prev1 = nums[0]; // dp[0] = nums[0]
        int prev2 = 0;       // dp[-1] = 0 (no houses before index 0)

        // Process houses from index 1 to n-1
        for (int i = 1; i < n; i++) {
            // Option 1: Take current house (i) - add to result from house i-2
            int take = nums[i];
            if (i > 1) {
                take += prev2; // Add result from house i-2 if it exists
            }

            // Option 2: Skip current house (i) - take result from house i-1
            int notTake = prev1;

            // Compute current best result for houses [0, i]
            int curr = Math.max(take, notTake);

            // Update variables for next iteration
            prev2 = prev1; // Move prev1 to prev2
            prev1 = curr;  // Move curr to prev1
        }

        // Return the final result (prev1 contains dp[n-1])
        return prev1;
    }
    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                rob(new int[]{1, 2, 3, 1}));
        System.out.println("Input: [2,7,9,3,1], Expected: 12, Output: " +
                rob(new int[]{2, 7, 9, 3, 1}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: " +
                rob(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                rob(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0], Expected: 0, Output: " +
                rob(new int[]{0}));
        System.out.println("Input: [MAX_VALUE], Expected: " + Integer.MAX_VALUE + ", Output: " +
                rob(new int[]{Integer.MAX_VALUE}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [1,2,3,4,5], Expected: 9, Output: " +
                rob(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Input: [5,4,3,2,1], Expected: 9, Output: " +
                rob(new int[]{5, 4, 3, 2, 1}));

        // Complex/Large Input
        System.out.println("\nComplex/Large Input:");
        System.out.println("Input: [100,1,1,100,1,1,100], Expected: 300, Output: " +
                rob(new int[]{100, 1, 1, 100, 1, 1, 100}));

        // Tabulation Method Tests
        System.out.println("\nTabulation Tests:");
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                robTabulation(new int[]{1, 2, 3, 1}));
        System.out.println("Input: [2,7,9,3,1], Expected: 12, Output: " +
                robTabulation(new int[]{2, 7, 9, 3, 1}));
        System.out.println("Input: [], Expected: 0, Output: " +
                robTabulation(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                robTabulation(null));
        System.out.println("Input: [5,5,5,5], Expected: 10, Output: " +
                robTabulation(new int[]{5, 5, 5, 5}));

        // Backward DP Method Tests
        System.out.println("\nBackward DP Tests:");
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                rob1DMemoizationBackward(new int[]{1, 2, 3, 1}));
        System.out.println("Input: [2,7,9,3,1], Expected: 12, Output: " +
                rob1DMemoizationBackward(new int[]{2, 7, 9, 3, 1}));
        System.out.println("Input: [], Expected: 0, Output: " +
                rob1DMemoizationBackward(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                rob1DMemoizationBackward(null));
        System.out.println("Input: [5,5,5,5], Expected: 10, Output: " +
                rob1DMemoizationBackward(new int[]{5, 5, 5, 5}));

        // 1D Tabulation Tests
        System.out.println("\n1D Tabulation Tests:");
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                rob1DTabulation(new int[]{1, 2, 3, 1}));
        System.out.println("Input: [2,7,9,3,1], Expected: 12, Output: " +
                rob1DTabulation(new int[]{2, 7, 9, 3, 1}));
        System.out.println("Input: [], Expected: 0, Output: " +
                rob1DTabulation(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                rob1DTabulation(null));
        System.out.println("Input: [5,5,5,5], Expected: 10, Output: " +
                rob1DTabulation(new int[]{5, 5, 5, 5}));

        // 1D Tabulation Optimized Tests
        System.out.println("\n1D Tabulation Optimized Tests:");
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                rob1DTabulationOptimized(new int[]{1, 2, 3, 1}));
        System.out.println("Input: [2,7,9,3,1], Expected: 12, Output: " +
                rob1DTabulationOptimized(new int[]{2, 7, 9, 3, 1}));
        System.out.println("Input: [], Expected: 0, Output: " +
                rob1DTabulationOptimized(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                rob1DTabulationOptimized(null));
        System.out.println("Input: [5,5,5,5], Expected: 10, Output: " +
                rob1DTabulationOptimized(new int[]{5, 5, 5, 5}));
    }
}