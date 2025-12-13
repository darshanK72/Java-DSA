/**
 * GeeksforGeeks. Maximum Sum Bitonic Subsequence
 * 
 * Problem Statement:
 *     Given an array of integers. A subsequence of arr[] is called Bitonic if 
 *     it is first increasing, then decreasing. Find the maximum sum of such a 
 *     bitonic subsequence.
 * 
 * Input:
 *     - nums (array of integers, n >= 1)
 * 
 * Output:
 *     - Integer representing the maximum sum of bitonic subsequence
 * 
 * Example:
 *     Input: arr[] = {1, 15, 51, 45, 33, 100, 12, 18, 9}
 *     Output: 194
 * 
 *     Explanation:
 *     Maximum sum bitonic subsequence is {1, 15, 51, 100, 18, 9} with sum 
 *     1 + 15 + 51 + 100 + 18 + 9 = 194
 * 
 *     Input: arr[] = {80, 60, 30, 40, 20, 10}
 *     Output: 210
 */

public class j11MaxBitonicSum {

    /**
     * Approach: Dynamic Programming with LIS and LDS
     * 
     * Intuition:
     * - A bitonic subsequence first increases to a peak, then decreases
     * - For each position, we need to find:
     *   - Maximum sum of increasing subsequence ending at that position
     *   - Maximum sum of decreasing subsequence starting at that position
     * - The bitonic sum at position i = left[i] + right[i] - nums[i] 
     *   (peak element counted twice, so subtract once)
     * - We use dynamic programming similar to LIS but track sums instead of 
     *   lengths
     * - left[i] stores maximum sum of increasing subsequence ending at i
     * - right[i] stores maximum sum of decreasing subsequence starting at i
     * 
     * Explanation:
     * - Step 1: Calculate left[] array from left to right
     *   - left[i] stores maximum sum of increasing subsequence ending at index i
     *   - For each i, check all previous elements j < i where nums[j] < nums[i]
     *   - Update left[i] = max(left[i], left[j] + nums[i]) for valid pairs
     *   - Initialize left[i] with nums[i] as base case (single element)
     * - Step 2: Calculate right[] array from right to left
     *   - right[i] stores maximum sum of decreasing subsequence starting at 
     *     index i
     *   - For each i, check all later elements j > i where nums[j] < nums[i]
     *   - Update right[i] = max(right[i], right[j] + nums[i]) for valid pairs
     *   - Initialize right[i] with nums[i] as base case (single element)
     * - Step 3: Find maximum bitonic sum
     *   - For each position i, calculate bitonic sum = left[i] + right[i] - 
     *     nums[i]
     *   - Track the maximum bitonic sum across all positions
     * - Step 4: Return the maximum bitonic sum found
     * 
     * Time Complexity: O(n^2) where n is the length of nums array
     *                  - Two nested loops for left array calculation: O(n^2)
     *                  - Two nested loops for right array calculation: O(n^2)
     *                  - Single loop to find max bitonic sum: O(n)
     * 
     * Space Complexity: O(n) where n is the length of nums array
     *                   - Two arrays left[] and right[] of size n each
     * 
     * @param nums    Integer array (n >= 1)
     * @return        Maximum sum of bitonic subsequence
     */
    public static int maxSumBS(int[] nums) {
        // Get the length of input array
        int n = nums.length;
        
        // Initialize array to store maximum sum of increasing subsequence 
        // ending at each index
        int[] left = new int[n];

        // Calculate maximum sum of increasing subsequence from left to right
        // For each position i, find the maximum sum ending at i
        for (int i = 0; i < n; i++) {
            // Initialize with current element as base case (single element 
            // subsequence)
            int maxSumTill = 0;
            
            // Check all previous elements to find valid increasing pairs
            for (int j = 0; j < i; j++) {
                // If previous element is smaller, we can extend the subsequence
                if (nums[j] < nums[i]) {
                    // Update maximum sum by taking maximum of current value and 
                    // extended subsequence sum from j
                    maxSumTill = Math.max(maxSumTill, left[j]);
                }
            }
            
            // Store maximum sum ending at i (previous max sum + current element)
            left[i] = maxSumTill + nums[i];
        }

        // Initialize array to store maximum sum of decreasing subsequence 
        // starting at each index
        int[] right = new int[n];
        
        // Calculate maximum sum of decreasing subsequence from right to left
        // For each position i, find the maximum sum starting at i
        for (int i = n - 1; i >= 0; i--) {
            // Initialize with current element as base case (single element 
            // subsequence)
            int maxSumTill = 0;
            
            // Check all later elements to find valid decreasing pairs
            for (int j = n - 1; j > i; j--) {
                // If later element is smaller, we can extend the subsequence
                if (nums[j] < nums[i]) {
                    // Update maximum sum by taking maximum of current value and 
                    // extended subsequence sum from j
                    maxSumTill = Math.max(maxSumTill, right[j]);
                }
            }
            
            // Store maximum sum starting at i (previous max sum + current element)
            right[i] = maxSumTill + nums[i];
        }

        // Find the maximum bitonic subsequence sum
        int maxBitonicSum = 0;
        // Check each position as potential peak of bitonic subsequence
        for (int i = 0; i < n; i++) {
            // Bitonic sum = increasing sum + decreasing sum - peak element 
            // (counted twice)
            maxBitonicSum = Math.max(maxBitonicSum, 
                left[i] + right[i] - nums[i]);
        }

        // Return the maximum bitonic sum found
        return maxBitonicSum;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,15,51,45,33,100,12,18,9], Expected: 194, Output: " + 
            maxSumBS(new int[]{1,15,51,45,33,100,12,18,9}));
        System.out.println("Input: [80,60,30,40,20,10], Expected: 210, Output: " + 
            maxSumBS(new int[]{80,60,30,40,20,10}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: 1, Output: " + 
            maxSumBS(new int[]{1}));
        System.out.println("Input: [1,2,3], Expected: 6, Output: " + 
            maxSumBS(new int[]{1,2,3}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [5,4,3,2,1], Expected: 15, Output: " + 
            maxSumBS(new int[]{5,4,3,2,1}));
        System.out.println("Input: [1,2,3,2,1], Expected: 9, Output: " + 
            maxSumBS(new int[]{1,2,3,2,1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [12,11,40,5,3,1], Expected: 71, Output: " + 
            maxSumBS(new int[]{12,11,40,5,3,1}));
        System.out.println("Input: [5,3,9,2,7,6,4], Expected: 19, Output: " + 
            maxSumBS(new int[]{5,3,9,2,7,6,4}));
    }
}
