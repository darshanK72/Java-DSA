/**
 * Problem Statement:
 * 
 *     Given an array of positive integers `nums` and a target integer `k`, the task is to find the length of the
 *     smallest contiguous subarray whose sum is greater than or equal to `k`. If no such subarray exists, return `0`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^9), the target sum.
 * 
 * Output:
 *     - Return an integer representing the length of the smallest subarray whose sum is greater than or equal to `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     2 3 1 2 4 3
 *     7
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The smallest subarray with a sum >= 7 is [4, 3], which has length 2.
 */

import java.util.Scanner;

public class j03MinimumSizeSubarraySum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: target sum

        // Call the solution method
        System.out.println(minSubArrayLen(k, arr)); // Output the result

        in.close();
    }

    /**
     * Approach: Sliding Window (Two Pointers)
     * 
     * Intuition:
     * - The sliding window technique is used to maintain a contiguous subarray with two pointers (`j` and `i`).
     * - We expand the window by moving the right pointer (`i`) and calculate the sum of elements within the window.
     * - Once the sum of elements in the window is greater than or equal to `k`, we attempt to shrink the window by moving the left pointer (`j`).
     * - We keep track of the minimum length of the subarray that satisfies the sum condition.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Both pointers only move forward once.
     * 
     * Space Complexity:
     * - O(1), no extra space is used other than variables to track the sum and pointers.
     * 
     * @param target The target sum to achieve.
     * @param nums The input array of positive integers.
     * @return The length of the smallest subarray whose sum is greater than or equal to `target`.
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE; // To store the minimum length of subarray
        int currentSum = 0; // To store the sum of elements in the current window
        int j = 0; // Left pointer of the sliding window

        // Iterate through the array with the right pointer
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i]; // Add the current element to the window sum

            // Shrink the window from the left while the sum is greater than or equal to the
            // target
            while (currentSum >= target) {
                minLength = Math.min(minLength, i - j + 1); // Update minimum length
                currentSum -= nums[j]; // Remove the element at the left pointer
                j++; // Move the left pointer to shrink the window
            }
        }

        // If no valid subarray is found, return 0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
