/**
 * Problem Statement:
 *     LeetCode 1856. Maximum Subarray Min-Product
 * 
 *     The min-product of an array is equal to the minimum value in the array
 *     multiplied by the array's sum. Given an array of integers nums, return
 *     the maximum min-product of any non-empty subarray of nums.
 * 
 * Input:
 *     - Array nums where 1 <= nums.length <= 10^5
 *     - 1 <= nums[i] <= 10^7
 * 
 * Output:
 *     - Integer representing maximum min-product modulo 10^9 + 7
 * 
 * Example:
 *     Input: nums = [3,1,2,4]
 *     Output: 20
 *     
 *     Explanation:
 *     Subarrays and their min-products:
 *     - [3] = 3 * 3 = 9
 *     - [1] = 1 * 1 = 1
 *     - [2] = 2 * 2 = 4
 *     - [4] = 4 * 4 = 16
 *     - [3,1] = 1 * 4 = 4
 *     - [1,2] = 1 * 3 = 3
 *     - [2,4] = 2 * 6 = 12
 *     - [3,1,2] = 1 * 6 = 6
 *     - [1,2,4] = 1 * 7 = 7
 *     - [3,1,2,4] = 1 * 10 = 10
 *     Maximum min-product is 20 from subarray [2,4]
 */

import java.util.Arrays;
import java.util.Stack;

public class j14MaxSubarrayMinProduct {

    public static void main(String[] args) {
        // Test cases with different scenarios
        int[][] testCases = {
            {3, 1, 2, 4},           // Example case
            {1, 2, 3, 4},           // Increasing order
            {4, 3, 2, 1},           // Decreasing order
            {1, 3, 2, 4},           // Random order
            {5, 5, 5, 5},           // All same elements
            {1},                     // Single element
            {},                      // Empty array
            {0},                     // Zero element
            {-1, -2, -3},           // Negative numbers
            {Integer.MAX_VALUE},     // Max integer value
            {Integer.MIN_VALUE}      // Min integer value
        };

        // Test implementation with all cases
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ": " + 
                Arrays.toString(testCases[i]));
            System.out.println("Max Subarray Min Product: " + 
                maxSumMinProduct(testCases[i]));
            System.out.println();
        }
    }

    /**
     * Approach: Using Monotonic Stack and Prefix Sums
     * 
     * Intuition:
     * - For each element as minimum, we need:
     *   * The sum of the subarray where it's minimum
     *   * The boundaries of this subarray (next/prev smaller elements)
     * - Use prefix sums for efficient subarray sum calculation
     * - Use monotonic stack to find boundaries efficiently
     * 
     * Time Complexity: O(n)
     * - One pass for prefix sums: O(n)
     * - Two passes for finding boundaries: O(n)
     * - One pass for calculating min-products: O(n)
     * 
     * Space Complexity: O(n)
     * - Prefix sums array: O(n)
     * - Boundary arrays: O(n)
     * - Stack space: O(n)
     */
    public static int maxSumMinProduct(int[] nums) {
        int MOD = 1000000007;
        
        // Calculate prefix sums for efficient range sum queries
        long[] prefixSum = new long[nums.length];
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        // Find boundaries where current element is minimum
        int[] next = getNextSmallerElements(nums);
        int[] prev = getPrevSmallerElements(nums);
        
        // Calculate maximum min-product
        long maxMinProd = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = prev[i];
            int right = next[i] - 1;
            long total = prefixSum[right] - ((left == -1) ? 0 : prefixSum[left]);
            long minProd = (long) nums[i] * total;
            maxMinProd = Math.max(maxMinProd, minProd);
        }
        
        return (int) (maxMinProd % MOD);
    }

    /**
     * Helper Method: Find next smaller elements
     * Uses monotonic stack to find next smaller element for each position
     */
    private static int[] getNextSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, arr.length);  // Default: no smaller element found
        
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Helper Method: Find previous smaller elements
     * Uses monotonic stack to find previous smaller element for each position
     */
    private static int[] getPrevSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, -1);  // Default: no smaller element found
        
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }
}