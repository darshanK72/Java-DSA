/**
 * LeetCode 1403. Minimum Subsequence in Non-Increasing Order
 * 
 * Problem Statement:
 *     Given the array nums, obtain a subsequence of the array whose sum of elements
 *     is strictly greater than the sum of the non-included elements in such
 *     subsequence. If there are multiple solutions, return the subsequence with
 *     minimum size and if there still exist multiple solutions, return the
 *     subsequence with the maximum total sum of all its elements.
 * 
 * Input:
 *     - nums (int[]): Array of positive integers
 *     - 1 <= nums.length <= 500
 *     - 1 <= nums[i] <= 100
 * 
 * Output:
 *     - List of integers representing the subsequence in non-increasing order
 *     - The sum of elements in subsequence must be strictly greater than sum of
 *       remaining elements
 * 
 * Example:
 *     Input: nums = [4,3,10,9,8]
 *     Output: [10,9]
 * 
 *     Explanation:
 *     The subsequences [10,9] and [10,8] are minimal such that the sum of their
 *     elements is strictly greater than the sum of elements not included.
 *     However, the subsequence [10,9] has the maximum total sum of its elements.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j02MinSubsequenceInNonIncreasingOrder {
    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - To minimize the size of subsequence while maximizing its sum, we should
     *   take the largest elements first
     * - We can sort the array in ascending order and start taking elements from
     *   the end
     * - We keep track of both the sum of selected elements and remaining elements
     * - We stop when the sum of selected elements becomes greater than the
     *   remaining sum
     * 
     * Explanation:
     * 1. Sort the array in ascending order
     * 2. Calculate total sum of all elements
     * 3. Start from the end of array and keep adding elements to subsequence:
     *    - Add current element to subsequence
     *    - Update sum of selected and remaining elements
     *    - Stop when selected sum > remaining sum
     * 4. Return the subsequence in non-increasing order
     * 
     * Time Complexity: O(n log n) where n is the length of nums array
     *                  Due to sorting operation
     * Space Complexity: O(n) to store the result subsequence
     * 
     * @param nums Array of positive integers
     * @return    Subsequence in non-increasing order with minimum size and
     *            maximum sum
     */
    public List<Integer> minSubsequence(int[] nums) {
        // Sort array in ascending order
        Arrays.sort(nums);
        
        // Calculate total sum of all elements
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        // Start from end of array and build subsequence
        int j = nums.length - 1;
        int revTotal = 0;
        ArrayList<Integer> out = new ArrayList<>();
        
        // Keep adding elements until sum of subsequence > remaining sum
        while (j >= 0 && revTotal <= total) {
            revTotal += nums[j];
            total -= nums[j];
            out.add(nums[j]);
            j--;
        }

        return out;
    }

    public static void main(String[] args) {
        j02MinSubsequenceInNonIncreasingOrder solution = new j02MinSubsequenceInNonIncreasingOrder();

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {4, 3, 10, 9, 8};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Expected: [10, 9], Output: " + solution.minSubsequence(nums1));

        // Test Case 2: All equal numbers
        System.out.println("\nEqual Numbers Test Case:");
        int[] nums2 = {7, 7, 7, 7, 7};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Expected: [7, 7, 7], Output: " + solution.minSubsequence(nums2));

        // Test Case 3: Single element
        System.out.println("\nSingle Element Test Case:");
        int[] nums3 = {6};
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Expected: [6], Output: " + solution.minSubsequence(nums3));

        // Test Case 4: Strictly increasing numbers
        System.out.println("\nIncreasing Numbers Test Case:");
        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Expected: [5, 4], Output: " + solution.minSubsequence(nums4));
    }
}
