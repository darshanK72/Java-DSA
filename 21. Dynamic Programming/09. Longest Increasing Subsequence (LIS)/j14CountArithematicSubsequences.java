/**
 * LeetCode 446. Arithmetic Slices II - Subsequence
 * 
 * Problem Statement:
 *     Given an integer array nums, return the number of all the arithmetic 
 *     subsequences of nums. A sequence of numbers is called arithmetic if it 
 *     consists of at least three elements and if the difference between any 
 *     two consecutive elements is the same. For example, [1, 3, 5, 7, 9], 
 *     [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 *     A subsequence of an array is a sequence that can be formed by removing 
 *     some (possibly zero) elements from the array, without changing the order 
 *     of the remaining elements.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 1000, -2^31 <= nums[i] <= 2^31 - 1)
 * 
 * Output:
 *     - Integer representing the number of all arithmetic subsequences
 * 
 * Example:
 *     Input: nums = [2,4,6,8,10]
 *     Output: 7
 * 
 *     Explanation:
 *     All arithmetic subsequence slices are:
 *     [2,4,6], [4,6,8], [6,8,10], [2,4,6,8], [4,6,8,10], [2,4,6,8,10], 
 *     [2,6,10]
 * 
 *     Input: nums = [7,7,7,7,7]
 *     Output: 16
 * 
 *     Explanation:
 *     Any subsequence of this array is arithmetic.
 */

import java.util.HashMap;

public class j14CountArithematicSubsequences {

    /**
     * Approach: Dynamic Programming with HashMap
     * 
     * Intuition:
     * - Unlike arithmetic subarrays (contiguous), subsequences can skip 
     *   elements
     * - For each position i, we need to track all possible differences and 
     *   count of subsequences ending at i with each difference
     * - We use dp[i] as a HashMap where key is the difference and value is the 
     *   count of subsequences ending at i with that difference
     * - For each pair (j, i) where j < i, we calculate diff = nums[i] - nums[j]
     * - If dp[j] has subsequences with difference diff, we can extend them 
     *   with nums[i]
     * - The count of new subsequences formed = count from dp[j][diff] + 1 
     *   (the pair itself)
     * - We only count subsequences of length >= 3, so we add dp[j][diff] to 
     *   the answer (not the +1 part)
     * 
     * Explanation:
     * - Step 1: Initialize DP array of HashMaps
     *   - dp[i] is a HashMap<Long, Long> storing (difference -> count)
     *   - Each HashMap tracks subsequences ending at index i with various 
     *     differences
     * - Step 2: For each position i from 1 to n-1
     *   - Check all previous positions j < i
     *   - Calculate difference diff = nums[i] - nums[j]
     *   - Get existing count for this difference at position i (oldVal)
     *   - Calculate new count: oldVal + dp[j][diff] + 1
     *     - oldVal: existing subsequences ending at i with diff
     *     - dp[j][diff]: subsequences ending at j with diff (can be extended)
     *     - +1: the new pair (nums[j], nums[i]) itself
     *   - Update dp[i][diff] with new count
     *   - Add dp[j][diff] to total count (only count length >= 3 subsequences)
     * - Step 3: Return total count
     *   - Total count represents all arithmetic subsequences of length >= 3
     * 
     * Time Complexity: O(n^2) where n is the length of nums array
     *                  - Nested loops: O(n^2)
     *                  - HashMap operations: O(1) average case
     * 
     * Space Complexity: O(n^2) in worst case where n is the length of nums 
     *                   array
     *                   - Array of n HashMaps
     *                   - Each HashMap can have up to O(n) entries in worst 
     *                     case
     * 
     * @param nums    Integer array (1 <= nums.length <= 1000, 
     *                -2^31 <= nums[i] <= 2^31 - 1)
     * @return        Number of all arithmetic subsequences
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        // Get the length of input array
        int n = nums.length;
        
        // DP array: each element is a HashMap storing (difference -> count)
        // dp[i][diff] = count of subsequences ending at i with difference diff
        HashMap<Long, Long>[] dp = new HashMap[n];
        
        // Initialize each HashMap in the DP array
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        
        // Track total count of arithmetic subsequences (length >= 3)
        long count = 0;
        
        // For each position i, check all previous positions j
        for (int i = 1; i < n; i++) {
            // Check all previous elements to form subsequences
            for (int j = i - 1; j >= 0; j--) {
                // Calculate difference between current and previous element
                // Use long to handle integer overflow
                long diff = (long) nums[i] - (long) nums[j];
                
                // Get existing count of subsequences ending at i with this 
                // difference
                long oldVal = dp[i].getOrDefault(diff, 0L);
                
                // Calculate new count: extend subsequences from j with diff, 
                // plus the new pair (j, i)
                // dp[j][diff] gives count of subsequences ending at j with 
                // difference diff
                long newVal = oldVal + dp[j].getOrDefault(diff, 0L) + 1L;
                
                // Update count of subsequences ending at i with difference diff
                dp[i].put(diff, newVal);
                
                // Add to total count: only count subsequences of length >= 3
                // dp[j][diff] represents subsequences that can be extended 
                // (already length >= 2, adding i makes >= 3)
                count += dp[j].getOrDefault(diff, 0L);
            }
        }
        
        // Return total count of arithmetic subsequences
        return (int) count;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [2,4,6,8,10], Expected: 7, Output: " + 
            numberOfArithmeticSlices(new int[]{2,4,6,8,10}));
        System.out.println("Input: [7,7,7,7,7], Expected: 16, Output: " + 
            numberOfArithmeticSlices(new int[]{7,7,7,7,7}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1,2], Expected: 0, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2}));
        System.out.println("Input: [1,2,3], Expected: 1, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2,3}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,2000000000,-294967296], Expected: 0, Output: " + 
            numberOfArithmeticSlices(new int[]{0,2000000000,-294967296}));
        System.out.println("Input: [1,1,1,1], Expected: 5, Output: " + 
            numberOfArithmeticSlices(new int[]{1,1,1,1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [2,2,3,4], Expected: 2, Output: " + 
            numberOfArithmeticSlices(new int[]{2,2,3,4}));
        System.out.println("Input: [0,1,2,2,2], Expected: 4, Output: " + 
            numberOfArithmeticSlices(new int[]{0,1,2,2,2}));
    }
}
