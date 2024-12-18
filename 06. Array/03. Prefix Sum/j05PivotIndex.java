/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums`, we need to find the index where the sum of the elements to the left is equal to the sum of the elements to the right. The left and right sums do not include the element at the pivot index.
 *     If there is no such index, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array `nums`.
 *     - An array `nums` of size `n` where each element satisfies (-10^5 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - The pivot index, or -1 if no such index exists.
 * 
 * Example:
 *     Input:
 *     5
 *     1 7 3 6 5 6
 *     Output:
 *     3
 * 
 *     Explanation:
 *     At index 3 (value = 6):
 *     - Left sum: 1 + 7 + 3 = 11
 *     - Right sum: 5 + 6 = 11
 *     Hence, the pivot index is 3.
 */

import java.util.*;

public class j05PivotIndex {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the nums array
        int[] arr = new int[n]; // Array to store the elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each number into the array
        }

        // Call the method to compute the pivot index
        System.out.println(pivotIndex(arr));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Prefix and Suffix Sum
     * 
     * Intuition:
     * - The idea is to compute the prefix sum (sum of elements before the current element) and the suffix sum (sum of elements after the current element) for each index.
     * - For each index, we compare the prefix sum and the suffix sum. If they are equal, we return that index as the pivot index.
     * - The prefix sum and suffix sum for each element are calculated using two separate arrays (`preSum` and `sufSum`).
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array. We iterate through the array to compute prefix and suffix sums and check for the pivot index.
     * 
     * Space Complexity:
     * - O(n), because we use two additional arrays to store prefix and suffix sums.
     * 
     * @param nums The array of integers.
     * @return The pivot index if it exists, otherwise -1.
     */
    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        int[] sufSum = new int[n];

        // Initialize the first and last elements of the prefix and suffix sum arrays
        preSum[0] = nums[0];
        sufSum[n - 1] = nums[n - 1];

        // Fill the prefix sum array
        for (int i = 1; i < n; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }

        // Fill the suffix sum array
        for (int i = n - 2; i >= 0; i--) {
            sufSum[i] = nums[i] + sufSum[i + 1];
        }

        // Find the pivot index by comparing prefix and suffix sums
        for (int i = 0; i < n; i++) {
            if (preSum[i] == sufSum[i]) {
                return i; // Return the pivot index
            }
        }

        // If no pivot index is found, return -1
        return -1;
    }
}
