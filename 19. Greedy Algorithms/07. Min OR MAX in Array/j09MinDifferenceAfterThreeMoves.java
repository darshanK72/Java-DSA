/**
 * LeetCode 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 * 
 * Problem Statement:
 *     Given an array nums, you are allowed to choose one element of nums and
 *     change it by any value in one move. Return the minimum difference between
 *     the largest and smallest value of nums after performing at most three moves.
 * 
 * Input:
 *     - nums: integer array (1 <= nums.length <= 10^5)
 *     - -10^9 <= nums[i] <= 10^9
 * 
 * Output:
 *     - Return the minimum possible difference between largest and smallest value
 *       after at most three moves
 * 
 * Example:
 *     Input: nums = [5,3,2,4]
 *     Output: 0
 * 
 *     Explanation:
 *     - We can make at most 3 moves
 *     - Change 2 to 3, 3 to 3, 4 to 3
 *     - Final array: [5,3,3,3]
 *     - Difference between largest and smallest is 5 - 3 = 2
 */

import java.util.Arrays;

public class j09MinDifferenceAfterThreeMoves {
    /**
     * Approach: Greedy - Sort and Check Possible Combinations
     * 
     * Intuition:
     * - After sorting, we only need to consider the first 4 and last 4 elements
     * - With 3 moves, we can change any 3 elements to any value
     * - The minimum difference will be among these combinations:
     *   1. Remove 3 largest elements
     *   2. Remove 3 smallest elements
     *   3. Remove 2 largest and 1 smallest
     *   4. Remove 1 largest and 2 smallest
     * 
     * Explanation:
     * - Step 1: Sort the array to easily access smallest and largest elements
     * - Step 2: If array length < 4, return 0 (can make all elements equal)
     * - Step 3: Check all possible combinations of removing 3 elements
     * - Step 4: Return the minimum difference found
     * 
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param nums    Array of integers to find minimum difference
     * @return        Minimum possible difference after at most 3 moves
     */
    public static int minDifference(int[] nums) {
        int n = nums.length;
        // If array length is less than 4, we can make all elements equal
        if(n < 4) return 0;
        
        // Sort array to easily access smallest and largest elements
        Arrays.sort(nums);
        
        // Check all possible combinations of removing 3 elements
        int ans = nums[n-4] - nums[0];     // Remove 3 largest elements
        ans = Math.min(ans, nums[n-1] - nums[3]);  // Remove 3 smallest elements
        ans = Math.min(ans, nums[n-3] - nums[1]);  // Remove 2 largest, 1 smallest
        ans = Math.min(ans, nums[n-2] - nums[2]);  // Remove 1 largest, 2 smallest
        
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {5, 3, 2, 4};
        System.out.println("Input: [5,3,2,4], Expected: 0, Output: " + minDifference(nums1));

        // Test Case 2: Array with length < 4
        System.out.println("\nSmall Array:");
        int[] nums2 = {1, 2, 3};
        System.out.println("Input: [1,2,3], Expected: 0, Output: " + minDifference(nums2));

        // Test Case 3: Large numbers
        System.out.println("\nLarge Numbers:");
        int[] nums3 = {1000000, 1000001, 1000002, 1000003, 1000004};
        System.out.println("Input: [1000000,1000001,1000002,1000003,1000004], Expected: 1, Output: " + minDifference(nums3));

        // Test Case 4: Negative numbers
        System.out.println("\nNegative Numbers:");
        int[] nums4 = {-5, -3, -2, -4, -1};
        System.out.println("Input: [-5,-3,-2,-4,-1], Expected: 1, Output: " + minDifference(nums4));
    }
}
