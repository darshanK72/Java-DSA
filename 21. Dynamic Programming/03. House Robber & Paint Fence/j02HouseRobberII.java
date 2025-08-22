/**
 * LeetCode 213. House Robber II
 * 
 * Problem Statement:
 *     You are a professional robber planning to rob houses along a street. Each
 *     house has a certain amount of money stashed. All houses at this place are
 *     arranged in a circle. That means the first house is the neighbor of the
 *     last one. Meanwhile, adjacent houses have a security system connected, and
 *     it will automatically contact the police if two adjacent houses were broken
 *     into on the same night. Given an integer array nums representing the amount
 *     of money of each house, return the maximum amount of money you can rob
 *     tonight without alerting the police.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 10^5, 0 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - Integer representing the maximum sum of non-adjacent elements in circular
 *       arrangement.
 * 
 * Example:
 *     Input: nums = [2,3,2]
 *     Output: 3
 * 
 *     Explanation:
 *     You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *     because they are adjacent houses. You can rob house 2 (money = 3).
 */
import java.util.Arrays;

public class j02HouseRobberII {

    /**
     * Approach: Circular House Robber using Two Linear Subproblems
     * 
     * Intuition:
     * - Since houses are arranged in a circle, we cannot rob both first and last
     *   house. We solve this by considering two cases:
     *   1. Rob houses from index 0 to n-2 (exclude last)
     *   2. Rob houses from index 1 to n-1 (exclude first)
     * - Take the maximum of these two cases.
     * 
     * Explanation:
     * - For each case, we use the same DP approach as House Robber I with
     *   memoization over (index, prevState).
     * - Case 1: robHelper(nums, 0, n-1) - consider houses [0, n-2]
     * - Case 2: robHelper(nums, 1, n) - consider houses [1, n-1]
     * - Special case: if only one house, return its value directly.
     * 
     * Time Complexity: O(n) - Each subproblem takes O(n) time
     * Space Complexity: O(n) - DP table and recursion stack
     * 
     * @param nums    Input array of non-negative integers representing money in
     *                each house arranged in a circle. null/empty treated as 0.
     * @return        Maximum money that can be robbed without robbing adjacent
     *                houses in circular arrangement.
     */
    public static int rob(int[] nums) {
        // Handle edge cases: null or empty input yields 0 profit
        if (nums == null || nums.length == 0) {
            return 0; // No houses to rob
        }

        // Special case: only one house, can rob it directly
        if (nums.length == 1) {
            return nums[0]; // Only option is to rob the single house
        }

        // Solve two subproblems and take maximum:
        // Case 1: Rob houses [0, n-2] (exclude last house)
        // Case 2: Rob houses [1, n-1] (exclude first house)
        return Math.max(robHelper(nums, 0, nums.length - 1), 
                       robHelper(nums, 1, nums.length));
    }

    /**
     * Helper Method: robHelper
     * 
     * Intuition:
     * - Solves the linear house robber problem for a specific range [start, end)
     *   using the same DP approach as House Robber I.
     * 
     * Explanation:
     * - Initialize DP table for memoization over (prevState, index).
     * - Call robMaxMoney with the specified range and initial prevState = 0.
     * 
     * Time Complexity: O(end - start)
     * Space Complexity: O(n) for DP table
     * 
     * @param nums   Money in each house
     * @param start  Starting index (inclusive)
     * @param end    Ending index (exclusive)
     * @return       Maximum money obtainable from houses [start, end)
     */
    public static int robHelper(int[] nums, int start, int end) {
        // Initialize DP table: 2 rows for prevState (0 or 1), columns for index
        int n = nums.length;
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], -1); // Mark unseen states for prevState = 0
        Arrays.fill(dp[1], -1); // Mark unseen states for prevState = 1

        // Start recursion from 'start' index with prevState = 0
        return robMaxMoney(nums, dp, start, end, 0);
    }

    /**
     * Helper Method: robMaxMoney
     * 
     * Intuition:
     * - Recursive DP with memoization to solve linear house robber for range
     *   [index, end). Uses same logic as House Robber I but with custom range.
     * 
     * Explanation:
     * - Base case: when index == end, no houses remain -> return 0.
     * - Memo check: if state is computed, reuse it from dp.
     * - Choice:
     *   - If prevState == 0, we can take current: value = nums[index] +
     *     recurse(index + 1, 1).
     *   - We can always skip current: value = recurse(index + 1, 0).
     *   - Take max of the two options.
     * - Store and return the result for dp[prevState][index].
     * 
     * Time Complexity: O(1) per state transition, overall O(end - index)
     * Space Complexity: O(n) due to recursion depth and memo table
     * 
     * @param nums       Money in each house
     * @param dp         Memo table where dp[prevState][index] stores best answer
     * @param index      Current house index under consideration
     * @param end        End index (exclusive) for the range
     * @param prevState  0 if previous house not taken, 1 if taken
     * @return           Maximum money obtainable from houses [index, end)
     */
    public static int robMaxMoney(int[] nums, int[][] dp, int index, int end, int prevState) {
        // Handle base case: no more houses left in the range
        if (index == end) {
            return 0; // Nothing to rob in this range
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
            ans = robMaxMoney(nums, dp, index + 1, end, 1) + nums[index];
        }

        // Option 2: Skip current house and move with prevState=0 (still free)
        ans = Math.max(ans, robMaxMoney(nums, dp, index + 1, end, 0));

        // Memoize and return
        return dp[prevState][index] = ans;
    }

    /**
     * Approach 2: 1D Tabulation Optimized (Space O(1))
     * 
     * Intuition:
     * - Use the same circular house robber logic but with optimized 1D tabulation.
     * - Since houses are in a circle, we solve two subproblems:
     *   1. Houses [0, n-2] (exclude last house)
     *   2. Houses [1, n-1] (exclude first house)
     * - Take the maximum of these two cases.
     * 
     * Explanation:
     * - For each subproblem, use optimized tabulation with only two variables
     *   (prev1, prev2) instead of a full DP array.
     * - rob1DTabulationHelper solves the linear house robber for range [start, end].
     * - Special case: if only one house, return its value directly.
     * 
     * Time Complexity: O(n) - Each subproblem takes O(n) time
     * Space Complexity: O(1) - Only two variables used per subproblem
     * 
     * @param nums    Input array of non-negative integers representing money in
     *                each house arranged in a circle. null/empty treated as 0.
     * @return        Maximum money that can be robbed without robbing adjacent
     *                houses in circular arrangement.
     */
    public static int rob1DTabulation(int[] nums) {
        // Handle edge cases: null or empty input yields 0 profit
        if (nums == null || nums.length == 0) {
            return 0; // No houses to rob
        }

        // Special case: only one house, can rob it directly
        int n = nums.length;
        if (n == 1) {
            return nums[0]; // Only option is to rob the single house
        }

        // Solve two subproblems and take maximum:
        // Case 1: Rob houses [0, n-2] (exclude last house)
        // Case 2: Rob houses [1, n-1] (exclude first house)
        return Math.max(rob1DTabulationHelper(nums, 0, n - 2), 
                       rob1DTabulationHelper(nums, 1, n - 1));
    }

    /**
     * Helper Method: rob1DTabulationHelper
     * 
     * Intuition:
     * - Solves the linear house robber problem for a specific range [start, end]
     *   using optimized tabulation with only two variables.
     * - Uses the same logic as House Robber I but for a custom range.
     * 
     * Explanation:
     * - Initialize prev1 = nums[start] and prev2 = 0
     * - For each house i from start to end:
     *   - take = nums[i] + prev2 (if i > start + 1, else just nums[i])
     *   - notTake = prev1
     *   - curr = max(take, notTake)
     *   - Update: prev2 = prev1, prev1 = curr
     * - Return prev1
     * 
     * Time Complexity: O(end - start + 1)
     * Space Complexity: O(1) - Only two variables used
     * 
     * @param nums   Money in each house
     * @param start  Starting index (inclusive)
     * @param end    Ending index (inclusive)
     * @return       Maximum money obtainable from houses [start, end]
     */
    public static int rob1DTabulationHelper(int[] nums, int start, int end) {
        // Initialize variables: prev1 = dp[i-1], prev2 = dp[i-2]
        int prev1 = nums[start]; // dp[start] = nums[start]
        int prev2 = 0;           // dp[start-1] = 0 (no houses before start)

        // Process houses from start to end
        for (int i = start; i <= end; i++) {
            // Option 1: Take current house (i) - add to result from house i-2
            int take = nums[i];
            if (i > start + 1) {
                take += prev2; // Add result from house i-2 if it exists
            }

            // Option 2: Skip current house (i) - take result from house i-1
            int notTake = prev1;

            // Compute current best result for houses [start, i]
            int curr = Math.max(take, notTake);

            // Update variables for next iteration
            prev2 = prev1; // Move prev1 to prev2
            prev1 = curr;  // Move curr to prev1
        }

        // Return the final result (prev1 contains dp[end])
        return prev1;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [2,3,2], Expected: 3, Output: " +
                rob(new int[]{2, 3, 2}));
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                rob(new int[]{1, 2, 3, 1}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: " +
                rob(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                rob(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1], Expected: 1, Output: " +
                rob(new int[]{1}));
        System.out.println("Input: [0], Expected: 0, Output: " +
                rob(new int[]{0}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [1,2,3], Expected: 3, Output: " +
                rob(new int[]{1, 2, 3}));
        System.out.println("Input: [3,2,1], Expected: 3, Output: " +
                rob(new int[]{3, 2, 1}));

        // Complex/Large Input
        System.out.println("\nComplex/Large Input:");
        System.out.println("Input: [100,1,1,100,1,1,100], Expected: 300, Output: " +
                rob(new int[]{100, 1, 1, 100, 1, 1, 100}));

        // 1D Tabulation Optimized Tests
        System.out.println("\n1D Tabulation Optimized Tests:");
        System.out.println("Input: [2,3,2], Expected: 3, Output: " +
                rob1DTabulation(new int[]{2, 3, 2}));
        System.out.println("Input: [1,2,3,1], Expected: 4, Output: " +
                rob1DTabulation(new int[]{1, 2, 3, 1}));
        System.out.println("Input: [], Expected: 0, Output: " +
                rob1DTabulation(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " +
                rob1DTabulation(null));
        System.out.println("Input: [5,5,5,5], Expected: 10, Output: " +
                rob1DTabulation(new int[]{5, 5, 5, 5}));
    }
}
