/**
 * LeetCode 416. Partition Equal Subset Sum
 * 
 * Problem Statement:
 *     Given an integer array nums, return true if you can partition the array
 *     into two subsets such that the sum of the elements in both subsets is
 *     equal, or false otherwise.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 200)
 *     - 1 <= nums[i] <= 100
 * 
 * Output:
 *     - boolean: true if such a partition exists, else false
 * 
 * Example:
 *     Input: [1,5,11,5]
 *     Output: true
 *     Explanation:
 *     The array can be partitioned as [1,5,5] and [11] with equal sums 11.
 * 
 *     Input: [1,2,3,5]
 *     Output: false
 *     Explanation:
 *     No partition yields equal subset sums.
 */

import java.util.Arrays;

public class j03PartitionEqualSubsetSum {
    /**
     * Approach: Top-Down (Memoized) Subset Sum to target = totalSum/2
     * 
     * Intuition:
     * - If total sum is odd, equal partition is impossible.
     * - Otherwise, the task reduces to: can we pick a subset whose sum is
     *   target = totalSum/2?
     * - Use recursion with memoization over (index, remainingTarget) to avoid
     *   re-computation.
     * 
     * Explanation:
     * - Compute total sum; if odd, return false.
     * - Create dp[n+1][target+1] with tri-state values {-1,0,1} representing
     *   unknown/false/true for feasibility of forming sum using suffix.
     * - Recurse by either taking current element or skipping it; store result.
     * 
     * Time Complexity: O(n * target) where target = sum/2
     * Space Complexity: O(n * target) for memo table + O(n) recursion stack
     * 
     * @param nums    Non-null array of positive integers
     * @return        True if array can be partitioned into two equal-sum subsets
     */
    public static boolean canPartitionMemo(int[] nums) {
        int n = nums.length;                  // total number of elements
        int sum = 0;                          // compute total sum of array
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];                   // accumulate element into sum
        }

        if(sum % 2 == 1) return false;       // odd sum cannot be split equally

        int[][] dp = new int[n + 1][sum/2 + 1]; // memo table: {-1,0,1}
        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i],-1);           // initialize all states as unknown
        }

        // ask if we can make target = sum/2 starting from index 0
        return canPartitionMemoHelper(nums,dp,sum / 2,0);
    }

    /**
     * Helper Method: Recursive subset-sum with memoization
     * 
     * Intuition:
     * - Explore include/exclude decisions while caching results by state to
     *   avoid exponential blow-up.
     * 
     * Explanation:
     * - Base: if target == 0 => formed; if index reaches end => fail.
     * - If current value exceeds target, we can only skip; else try both.
     * - Memoize as 1 (true) or 0 (false).
     * 
     * Time Complexity: O(n * target)
     * Space Complexity: O(n * target)
     * 
     * @param nums      Input array
     * @param dp        Memo table storing {-1,0,1}
     * @param target    Remaining sum to form
     * @param index     Current index in nums
     * @return          True if a subset from index can form target
     */
    public static boolean canPartitionMemoHelper(int[] nums,int[][] dp,int target,int index){
        if(index == nums.length) return false; // no items left, can't form >0
        if(target == 0) return true;           // formed required target sum
        if(target < 0) return false;           // overshoot: invalid branch
        if (dp[index][target] != -1) {
            // return memoized boolean based on 1/0
            if (dp[index][target] == 1)
                return true;  
            else
                return false; 
        }

        // try including current element nums[index]
        boolean take = canPartitionMemoHelper(nums,dp,target - nums[index],index + 1);
        // try excluding current element
        boolean notTake = canPartitionMemoHelper(nums,dp,target,index + 1);

        if(take || notTake){
            dp[index][target] = 1;            // memoize success for this state
            return true;
        }else{
            dp[index][target] = 0;            // memoize failure for this state
            return false;
        }
    }

    /**
     * Approach: Bottom-Up (Tabulation) Subset Sum to a given sum
     * 
     * Intuition:
     * - Classic 0/1 subset sum DP where dp[i][s] indicates whether a sum s is
     *   achievable using the first i elements.
     * 
     * Explanation:
     * - Initialize dp[i][0] = true for all i (sum 0 with empty subset).
     * - Transition: dp[i][s] = dp[i-1][s] OR dp[i-1][s - arr[i-1]] if allowed.
     * - Answer is dp[n][sum]. For equal partition, invoke with sum = total/2.
     * 
     * Time Complexity: O(n * sum)
     * Space Complexity: O(n * sum)
     * 
     * @param arr     Input array (may be empty but not null for general use)
     * @param sum     Target subset sum to check
     * @return        True if a subset achieves sum, else false
     */
    public static boolean canPartitionTabulation(int arr[], int sum) {
        // Validate input parameters
        if (arr == null) return sum == 0;     // null array: only sum 0 is true
        int n = arr.length;                    // number of elements

        // dp[i][s] = 1 if sum s can be formed using first i elements
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;                     // sum 0 is always achievable
        }

        // Fill table row by row over elements and sums
        for (int index = 1; index <= n; index++) {
            for (int s = 1; s <= sum; s++) {
                if (arr[index - 1] <= s) {    // item fits into current sum
                    int take = dp[index - 1][s - arr[index - 1]]; // include
                    int notTake = dp[index - 1][s];               // exclude
                    if (take == 1 || notTake == 1) {
                        dp[index][s] = 1;     // reachable via either choice
                    } else {
                        dp[index][s] = 0;     // unreachable for this (i,s)
                    }
                } else {
                    dp[index][s] = dp[index - 1][s]; // cannot include item
                }
            }
        }

        // Final answer whether sum can be formed using all N elements
        return dp[n][sum] == 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        int[] a1 = {1,5,11,5};                // equal partition possible
        System.out.println("Input: [1,5,11,5], Expected: true, Output: " + canPartitionMemo(a1));
        int total1 = Arrays.stream(a1).sum(); // compute total for tabulation
        System.out.println("Tabu: Expected: true, Output: " + (total1 % 2 == 0 && canPartitionTabulation(a1, total1/2)));

        int[] a2 = {1,2,3,5};                 // equal partition not possible
        System.out.println("Input: [1,2,3,5], Expected: false, Output: " + canPartitionMemo(a2));
        int total2 = Arrays.stream(a2).sum(); // compute total for tabulation
        System.out.println("Tabu: Expected: false, Output: " + (total2 % 2 == 0 && canPartitionTabulation(a2, total2/2)));

        // Test Case 2: Edge cases (empty, single element)
        System.out.println("\nEdge Cases:");
        int[] a3 = {};                        // empty array -> sum 0 -> true
        System.out.println("Input: [], Expected: true, Output: " + canPartitionMemo(new int[]{}));
        int total3 = Arrays.stream(a3).sum(); // total is 0
        System.out.println("Tabu: Expected: true, Output: " + (total3 % 2 == 0 && canPartitionTabulation(a3, total3/2)));

        int[] a4 = {1};                       // single element cannot split
        System.out.println("Input: [1], Expected: false, Output: " + canPartitionMemo(a4));
        int total4 = Arrays.stream(a4).sum(); // total is odd
        System.out.println("Tabu: Expected: false, Output: " + (total4 % 2 == 0 && canPartitionTabulation(a4, total4/2)));

        // Test Case 3: Boundary cases (min/max values within reasonable bounds)
        System.out.println("\nBoundary Cases:");
        int[] a5 = {100,100};                 // trivially split into equal halves
        System.out.println("Input: [100,100], Expected: true, Output: " + canPartitionMemo(a5));
        int total5 = Arrays.stream(a5).sum(); // total is even
        System.out.println("Tabu: Expected: true, Output: " + (total5 % 2 == 0 && canPartitionTabulation(a5, total5/2)));

        // Test Case 4: Special cases (already equal halves, duplicates)
        System.out.println("\nSpecial Cases:");
        int[] a6 = {2,2,3,5};                 // 12 -> target 6 not achievable
        System.out.println("Input: [2,2,3,5], Expected: false, Output: " + canPartitionMemo(a6));
        int total6 = Arrays.stream(a6).sum(); // total is even but infeasible
        System.out.println("Tabu: Expected: false, Output: " + (total6 % 2 == 0 && canPartitionTabulation(a6, total6/2)));
    }

}
