/**
 * LeetCode 494. Target Sum
 * 
 * Problem Statement:
 *     You are given an integer array nums and an integer target.
 *     You want to build an expression out of nums by adding one of the symbols
 *     '+' or '-' before each integer in nums and then concatenate all the
 *     integers.
 *     - Return the number of different expressions that you can build, which
 *       evaluates to target.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 20), 0 <= nums[i] <= 1000
 *     - target (-1000 <= target <= 1000)
 * 
 * Output:
 *     - int: number of ways to assign '+' / '-' to reach target
 * 
 * Example:
 *     Input: nums = [1,1,1,1,1], target = 3
 *     Output: 5
 *     Explanation:
 *     There are 5 ways to assign symbols to get sum 3.
 */

public class j05TargetSum {
    /**
     * Approach: Brute-force recursion over +/- choices
     * 
     * Intuition:
     * - For each element, we either add or subtract it from the running sum.
     * - Explore both choices recursively and count valid completions.
     * 
     * Explanation:
     * - Recurse with parameters (index, currentSum). When index == n, check if
     *   currentSum equals target; count 1 if yes, else 0.
     * - Total ways = ways with '+' + ways with '-'.
     * 
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) recursion depth
     * 
     * @param nums    Array of non-negative integers
     * @param target  Desired expression result
     * @return        Number of expressions that evaluate to target
     */
    public static int findTargetSumWaysRec(int[] nums, int target) {
        return findTargetRecursion(nums, target, 0, 0);
    }

    /**
     * Helper Method:
     * 
     * Intuition:
     * - DFS over decision tree (+/-). Each level decides for one element.
     * 
     * Explanation:
     * - If we've placed symbols for all elements, compare the accumulated sum
     *   with target and return 1 or 0 accordingly.
     * - Otherwise branch into add and subtract paths.
     * 
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     * 
     * @param nums     Input array
     * @param target   Target sum to reach
     * @param sum      Running sum formed so far
     * @param index    Current index in nums
     * @return         Count of valid assignments from this state
     */
    public static int findTargetRecursion(int[] nums, int target, int sum, int index) {
        if (index == nums.length) {            // placed all symbols
            if (sum == target)                 // check if expression equals target
                return 1;                      // count 1 valid way
            else
                return 0;                      // otherwise 0
        }
        int add = findTargetRecursion(nums, target, sum + nums[index], index + 1); // choose '+'
        int sub = findTargetRecursion(nums, target, sum - nums[index], index + 1); // choose '-'

        return add + sub;                      // total ways from this state
    }

    /**
     * Approach: Reduce to counting subsets with given sum
     * 
     * Intuition:
     * - Let S be total sum of nums. Assign '+' to a subset P and '-' to the
     *   rest N. Then P - N = target and P + N = S => 2P = S + target.
     * - So P = (S + target) / 2. We need to count subsets with sum P.
     * 
     * Explanation:
     * - If S < |target| or (S + target) is odd, there are 0 ways.
     * - Otherwise, count number of subsets summing to P using DP tabulation.
     * 
     * Time Complexity: O(n * P)
     * Space Complexity: O(n * P)
     * 
     * @param nums    Array of non-negative integers
     * @param target  Desired expression result
     * @return        Number of subsets with sum P = (S + target)/2
     */
    public static int findTargetSumWaysPartition(int[] nums, int target) {
        int n = nums.length;                   // number of elements
        int sum = 0;                           // compute total sum
        for(int i = 0; i < n; i++){
            sum += nums[i];                    // accumulate into total
        }

        // If |target| > sum or parity mismatch => impossible
        if (Math.abs(target) > sum) return 0;  // cannot reach outside [-sum, sum]
        if ((sum + target) % 2 != 0) return 0; // S+target must be even

        int subsetSum = (sum + target) / 2;    // P = (S + target)/2
        return countSubsetWithTargetTabulation(nums, subsetSum);
    }

    /**
     * Helper Method: Count subsets with exact sum using DP tabulation
     * 
     * Intuition:
     * - Classic 0/1 subset sum counting DP: dp[i][t] = ways to build sum t
     *   using first i elements.
     * 
     * Explanation:
     * - Initialize dp[i][0] = 1 for all i (empty subset makes sum 0).
     * - Transition: dp[i][t] = dp[i-1][t] + dp[i-1][t - nums[i-1]] if allowed.
     * - Answer is dp[n][target]. Handles zeros correctly by doubling counts.
     * 
     * Time Complexity: O(n * target)
     * Space Complexity: O(n * target)
     * 
     * @param nums     Input array (can contain zeros)
     * @param target   Target subset sum to count
     * @return         Number of subsets forming target
     */
    public static int countSubsetWithTargetTabulation(int[] nums, int target) {
        if (nums == null || nums.length == 0) { // handle empty/null input
            return target == 0 ? 1 : 0;         // only 1 way to form 0
        }
        
        if (target < 0) {                       // negative target impossible
            return 0;
        }
        
        int n = nums.length;                    // number of items
        
        int[][] dp = new int[n + 1][target + 1]; // dp table for counts
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;                       // one way to form sum 0
        }
        
        for (int index = 1; index <= n; index++) {
            for (int tar = 0; tar <= target; tar++) {
                dp[index][tar] = dp[index - 1][tar]; // exclude current
                if (tar >= nums[index - 1]) {         // include if it fits
                    dp[index][tar] += dp[index - 1][tar - nums[index - 1]];
                }
            }
        }
        
        return dp[n][target];                   // total ways for full array
    }

    public static void main(String[] args) {
        // Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        int[] a1 = {1,1,1,1,1};
        System.out.println("Input: [1,1,1,1,1], target=3, Expected: 5, Rec: " + findTargetSumWaysRec(a1, 3));
        System.out.println("Partition DP: " + findTargetSumWaysPartition(a1, 3));

        // Edge cases: empty array, zero-only array
        System.out.println("\nEdge Cases:");
        int[] a2 = {};
        System.out.println("Input: [], target=0, Expected: 1, Partition DP: " + findTargetSumWaysPartition(a2, 0));
        int[] a3 = {0,0,0}; // each zero can be +0 or -0 -> doubles ways
        System.out.println("Input: [0,0,0], target=0, Partition DP: " + findTargetSumWaysPartition(a3, 0));

        // Boundary cases: large numbers but small n
        System.out.println("\nBoundary Cases:");
        int[] a4 = {1000, 1000};
        System.out.println("Input: [1000,1000], target=0, Partition DP: " + findTargetSumWaysPartition(a4, 0));

        // Special cases: impossible due to parity/abs bounds
        System.out.println("\nSpecial Cases:");
        int[] a5 = {1,2,3};
        System.out.println("Input: [1,2,3], target=7, Expected: 0, Partition DP: " + findTargetSumWaysPartition(a5, 7));
        int[] a6 = {1,2,1}; // S=4, target=2 -> P=(4+2)/2=3, subsets count=2
        System.out.println("Input: [1,2,1], target=2, Partition DP: " + findTargetSumWaysPartition(a6, 2));
    }
}
