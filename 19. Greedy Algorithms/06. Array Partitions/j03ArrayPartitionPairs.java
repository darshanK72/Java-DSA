/**
 * Problem: Array Partition I
 * 
 * Problem Statement:
 * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) 
 * such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
 * 
 * Example:
 * Input: nums = [1,4,3,2]
 * Output: 4
 * Explanation: All possible pairings (ignoring the ordering of elements) are:
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * So the maximum possible sum is 4.
 * 
 * Constraints:
 * - 1 <= n <= 104
 * - nums.length == 2 * n
 * - -104 <= nums[i] <= 104
 * 
 * Approach:
 * 1. Sort the array in ascending order
 * 2. Take elements in pairs (0,1), (2,3), etc.
 * 3. Sum up the minimum of each pair
 * 
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(1) as we only use a single variable for sum
 */

import java.util.Arrays;

public class j03ArrayPartitionPairs {
    /**
     * Finds the maximum possible sum of minimum values from pairs of numbers
     * 
     * @param nums Array of integers with even length
     * @return Maximum possible sum of minimum values from pairs
     */
    public static int arrayPairSum(int[] nums) {
        // Sort array to ensure we can take adjacent elements as pairs
        Arrays.sort(nums);
        int ans = 0;
        // Take elements in pairs and sum their minimum values
        for(int i = 0; i < nums.length; i+=2){
            ans += nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] nums1 = {1, 4, 3, 2};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + arrayPairSum(nums1));
        System.out.println();

        // Test Case 2: All same numbers
        int[] nums2 = {2, 2, 2, 2};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + arrayPairSum(nums2));
        System.out.println();

        // Test Case 3: Negative numbers
        int[] nums3 = {-1, -4, -3, -2};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Output: " + arrayPairSum(nums3));
        System.out.println();

        // Test Case 4: Mixed positive and negative numbers
        int[] nums4 = {-1, 4, -3, 2};
        System.out.println("Test Case 4:");
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Output: " + arrayPairSum(nums4));
        System.out.println();

        // Test Case 5: Larger array
        int[] nums5 = {6, 2, 6, 5, 1, 2};
        System.out.println("Test Case 5:");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Output: " + arrayPairSum(nums5));
    }
}
