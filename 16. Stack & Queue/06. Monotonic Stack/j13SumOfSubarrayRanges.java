/**
 * Problem Statement:
 *     LeetCode 2104. Sum of Subarray Ranges
 * 
 *     Given an integer array nums, return the sum of the differences between the 
 *     maximum and minimum value in each subarray. A subarray is a contiguous 
 *     part of an array.
 * 
 * Input:
 *     - Array nums where 1 <= nums.length <= 1000
 *     - -109 <= nums[i] <= 109
 * 
 * Output:
 *     - Long value representing sum of ranges of all subarrays
 * 
 * Example:
 *     Input: nums = [1,2,3]
 *     Output: 4
 *     
 *     Explanation:
 *     Subarrays are [1], [2], [3], [1,2], [2,3], [1,2,3]
 *     Ranges are    0,   0,   0,   1,     1,     2
 *     Sum is 0 + 0 + 0 + 1 + 1 + 2 = 4
 */

import java.util.Arrays;
import java.util.Stack;

public class j13SumOfSubarrayRanges {

    public static void main(String args[]) {
        // Test cases
        int[][] testCases = {
            {1, 2, 3},           // Basic case
            {1, 3, 3},          // Duplicate max
            {1, 2, 3, 4},       // Increasing order
            {4, 3, 2, 1},       // Decreasing order
            {1},                // Single element
            {1, 2, 3, 4, 5}     // Longer increasing order
        };

        // Test both implementations
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Array: " + Arrays.toString(testCases[i]));
            System.out.println("Brute Force: " + subArrayRanges(testCases[i]));
            System.out.println("Optimized: " + subArrayRangesEfficient(testCases[i]));
            System.out.println();
        }
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Generate all possible subarrays using two nested loops
     * - For each subarray, find its minimum and maximum
     * - Add difference between max and min to total sum
     * 
     * Time Complexity: O(n³)
     * - Two nested loops for generating subarrays: O(n²)
     * - Finding min and max in each subarray: O(n)
     * 
     * Space Complexity: O(1)
     * - Only using constant extra space
     */
    public static long subArrayRanges(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += (max - min);
            }
        }
        return ans;
    }

    /**
     * Approach 2: Using Monotonic Stack
     * 
     * Intuition:
     * - Range = Maximum - Minimum
     * - Calculate sum of all subarray maximums
     * - Calculate sum of all subarray minimums
     * - Final result is difference between these sums
     * 
     * Time Complexity: O(n)
     * - Four passes through array for finding boundaries: O(n)
     * - Each element pushed/popped once in each operation
     * 
     * Space Complexity: O(n)
     * - Four arrays for storing boundaries: O(n)
     * - Stack space in each operation: O(n)
     */
    public static long subArrayRangesEfficient(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    public static long sumSubarrayMins(int[] arr) {
        long total = 0;
        int[] nextSmaller = getNextSmallerElements(arr);
        int[] prevSmaller = getPrevSmallerElements(arr);

        for (int i = 0; i < arr.length; i++) {
            long left = (long) prevSmaller[i];
            long right = (long) nextSmaller[i];

            long count = ((right - left - 1L) * (right - left) / 2)
                    - ((i - left - 1L) * (i - left) / 2)
                    - ((right - i - 1L) * (right - i) / 2);

            total = (total + (count * arr[i]));
        }

        return total;
    }

    public static long sumSubarrayMaxs(int[] arr) {
        long total = 0;
        int[] nextLarger = getNextLargerElements(arr);
        int[] prevLarger = getPrevLargerElements(arr);

        for (int i = 0; i < arr.length; i++) {
            long left = (long) prevLarger[i];
            long right = (long) nextLarger[i];

            long count = ((right - left - 1L) * (right - left) / 2)
                    - ((i - left - 1L) * (i - left) / 2)
                    - ((right - i - 1L) * (right - i) / 2);

            total = (total + (count * arr[i]));
        }

        return total;
    }

    // Helper methods with proper documentation
    /**
     * Helper Method: Get Next Larger Elements
     * Uses monotonic stack to find next larger element for each position
     */
    private static int[] getNextLargerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, arr.length);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Helper Method: Get Previous Larger Elements
     * Uses monotonic stack to find previous larger element for each position
     */
    private static int[] getPrevLargerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, -1);
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Helper Method: Get Next Smaller Elements
     * Uses monotonic stack to find next smaller element for each position
     */
    private static int[] getNextSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, arr.length);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Helper Method: Get Previous Smaller Elements
     * Uses monotonic stack to find previous smaller element for each position
     */
    private static int[] getPrevSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, -1);
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

}
