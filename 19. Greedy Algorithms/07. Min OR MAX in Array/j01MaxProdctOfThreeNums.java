/**
 * LeetCode 628. Maximum Product of Three Numbers
 * 
 * Problem Statement:
 *     Given an integer array nums, find three numbers whose product is maximum and
 *     return the maximum product. The product of any triplet will fit in a 32-bit
 *     integer.
 * 
 * Input:
 *     - nums (int[]): Array of integers
 *     - 3 <= nums.length <= 10^4
 *     - -1000 <= nums[i] <= 1000
 * 
 * Output:
 *     - Maximum product of any triplet in the array
 * 
 * Example:
 *     Input: nums = [1,2,3]
 *     Output: 6
 * 
 *     Explanation:
 *     The maximum product is obtained by multiplying 1, 2, and 3:
 *     1 * 2 * 3 = 6
 */

import java.util.Arrays;

public class j01MaxProdctOfThreeNums {
    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - The maximum product of three numbers can be obtained in two ways:
     *   1. Product of three largest positive numbers
     *   2. Product of two smallest negative numbers and the largest positive number
     * - This is because two negative numbers multiply to a positive number
     * - We need to consider both cases to find the maximum product
     * 
     * Explanation:
     * 1. Sort the array to easily access largest and smallest numbers
     * 2. Calculate two possible maximum products:
     *    - Product of last three numbers (three largest)
     *    - Product of first two numbers and last number (two smallest and largest)
     * 3. Return the maximum of these two products
     * 
     * Time Complexity: O(n log n) where n is the length of nums array
     *                  Due to sorting operation
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param nums Array of integers
     * @return    Maximum product of any triplet
     */
    public int maximumProduct(int[] nums) {
        // Sort array to access largest and smallest numbers
        Arrays.sort(nums);

        // Calculate product of three largest numbers
        int lastThree = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];

        // Calculate product of two smallest numbers and largest number
        int firstTwoAndLast = nums[0] * nums[1] * nums[nums.length - 1];

        // Return maximum of the two products
        return Math.max(lastThree, firstTwoAndLast);
    }

    public static void main(String[] args) {
        j01MaxProdctOfThreeNums solution = new j01MaxProdctOfThreeNums();

        // Test Case 1: Basic case with positive numbers
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {1, 2, 3};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Expected: 6, Output: " + solution.maximumProduct(nums1));

        // Test Case 2: Mixed positive and negative numbers
        System.out.println("\nMixed Numbers Test Case:");
        int[] nums2 = {-4, -3, -2, -1, 60};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Expected: 720, Output: " + solution.maximumProduct(nums2));

        // Test Case 3: All negative numbers
        System.out.println("\nAll Negative Test Case:");
        int[] nums3 = {-4, -3, -2, -1};
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Expected: -6, Output: " + solution.maximumProduct(nums3));

        // Test Case 4: Large numbers
        System.out.println("\nLarge Numbers Test Case:");
        int[] nums4 = {1000, 1000, 1000};
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Expected: 1000000000, Output: " + solution.maximumProduct(nums4));
    }
}
