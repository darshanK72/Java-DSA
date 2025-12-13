/**
 * LeetCode 1671. Minimum Number of Removals to Make Mountain Array
 * 
 * Problem Statement:
 *     An array arr is a mountain array if and only if:
 *     - arr.length >= 3
 *     - There exists some index i (0-indexed) with 0 < i < arr.length - 1 
 *       such that:
 *       * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *       * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *     Given an integer array nums, return the minimum number of elements to 
 *     remove to make nums a mountain array.
 * 
 * Input:
 *     - nums (3 <= nums.length <= 1000, 1 <= nums[i] <= 10^9)
 * 
 * Output:
 *     - Integer representing minimum number of removals required
 * 
 * Example:
 *     Input: nums = [1,3,1]
 *     Output: 0
 * 
 *     Explanation:
 *     The array itself is a mountain array so we do not need to remove any 
 *     elements.
 * 
 *     Input: nums = [2,1,1,5,6,2,3,1]
 *     Output: 3
 * 
 *     Explanation:
 *     One solution is to remove the elements at indices 0, 1, and 5, making 
 *     the array nums = [1,5,6,3,1].
 */

import java.util.Arrays;

public class j09MinRemovalsToMakeLargestBitonicArray {

    /**
     * Approach: Dynamic Programming with LIS and LDS
     * 
     * Intuition:
     * - A mountain array has a peak element with strictly increasing sequence 
     *   before it and strictly decreasing sequence after it
     * - To minimize removals, we need to find the longest mountain subsequence 
     *   that can be formed from the given array
     * - For each position, we can calculate the longest increasing subsequence 
     *   (LIS) ending at that position from the left
     * - Similarly, we can calculate the longest decreasing subsequence (LDS) 
     *   starting at that position from the right
     * - A valid peak must have both LIS length > 1 and LDS length > 1 (to 
     *   ensure there are elements on both sides)
     * - The longest mountain length at position i is: LIS[i] + LDS[i] - 1 
     *   (subtract 1 because peak is counted twice)
     * - Minimum removals = total length - longest mountain length
     * 
     * Explanation:
     * - Step 1: Calculate LIS from left to right for each position using DP
     *   - left[i] stores the length of longest increasing subsequence ending 
     *     at index i
     *   - For each i, check all previous elements j < i where nums[j] < nums[i]
     *   - Update left[i] = max(left[i], left[j] + 1) for valid pairs
     * - Step 2: Calculate LDS from right to left for each position
     *   - right[i] stores the length of longest decreasing subsequence starting 
     *     at index i
     *   - For each i, check all later elements j > i where nums[j] < nums[i]
     *   - Update right[i] = max(right[i], right[j] + 1) for valid pairs
     * - Step 3: Find the longest valid mountain by checking each position as 
     *   potential peak
     *   - A position is valid peak if left[i] > 1 AND right[i] > 1
     *   - Mountain length at i = left[i] + right[i] - 1
     * - Step 4: Return n - maxBitonic (minimum removals needed)
     * 
     * Time Complexity: O(n^2) where n is the length of nums array
     *                  - Two nested loops for LIS calculation: O(n^2)
     *                  - Two nested loops for LDS calculation: O(n^2)
     *                  - Single loop to find max mountain: O(n)
     * 
     * Space Complexity: O(n) where n is the length of nums array
     *                   - Two arrays left[] and right[] of size n each
     * 
     * @param nums    Integer array (3 <= nums.length <= 1000, 
     *                1 <= nums[i] <= 10^9)
     * @return        Minimum number of removals to make mountain array
     */
    public static int minimumMountainRemovals(int[] nums) {
        // Get the length of input array
        int n = nums.length;
        
        // Initialize array to store LIS lengths ending at each index
        int[] left = new int[n];
        // Each element is at least a subsequence of length 1
        Arrays.fill(left, 1);

        // Calculate longest increasing subsequence (LIS) from left to right
        // For each position i, find the longest increasing subsequence ending 
        // at i
        for (int i = 1; i < n; i++) {
            // Check all previous elements to find valid increasing pairs
            for (int j = 0; j < i; j++) {
                // If previous element is smaller, we can extend the subsequence
                if (nums[j] < nums[i]) {
                    // Update LIS length at i by taking maximum of current 
                    // value and extended subsequence from j
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }

        // Initialize array to store LDS lengths starting at each index
        int[] right = new int[n];
        // Each element is at least a subsequence of length 1
        Arrays.fill(right, 1);
        
        // Calculate longest decreasing subsequence (LDS) from right to left
        // For each position i, find the longest decreasing subsequence 
        // starting at i
        for (int i = n - 2; i >= 0; i--) {
            // Check all later elements to find valid decreasing pairs
            for (int j = n - 1; j > i; j--) {
                // If later element is smaller, we can extend the subsequence
                if (nums[j] < nums[i]) {
                    // Update LDS length at i by taking maximum of current 
                    // value and extended subsequence from j
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }

        // Find the longest valid mountain subsequence
        int maxBitonic = 0;
        // Check each position as potential peak of mountain
        for (int i = 0; i < n; i++) {
            // Valid peak must have elements on both sides (LIS > 1 and 
            // LDS > 1)
            if (left[i] > 1 && right[i] > 1) {
                // Mountain length = LIS length + LDS length - 1 (peak counted 
                // twice)
                maxBitonic = Math.max(maxBitonic, left[i] + right[i] - 1);
            }
        }

        // Minimum removals = total elements - longest mountain length
        return n - maxBitonic;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,3,1], Expected: 0, Output: " + 
            minimumMountainRemovals(new int[]{1,3,1}));
        System.out.println("Input: [2,1,1,5,6,2,3,1], Expected: 3, Output: " + 
            minimumMountainRemovals(new int[]{2,1,1,5,6,2,3,1}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1,2,3,4,5], Expected: 4, Output: " + 
            minimumMountainRemovals(new int[]{1,2,3,4,5}));
        System.out.println("Input: [5,4,3,2,1], Expected: 4, Output: " + 
            minimumMountainRemovals(new int[]{5,4,3,2,1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,2,1], Expected: 0, Output: " + 
            minimumMountainRemovals(new int[]{1,2,1}));
        System.out.println("Input: [1,1,1], Expected: 2, Output: " + 
            minimumMountainRemovals(new int[]{1,1,1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [9,8,1,7,6,5,4,3,2,1], Expected: 2, Output: " + 
            minimumMountainRemovals(new int[]{9,8,1,7,6,5,4,3,2,1}));
        System.out.println("Input: [1,2,3,2,1], Expected: 0, Output: " + 
            minimumMountainRemovals(new int[]{1,2,3,2,1}));
    }

}