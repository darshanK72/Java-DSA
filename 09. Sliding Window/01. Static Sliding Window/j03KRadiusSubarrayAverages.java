/**
 * Problem Statement:
 *
 *     Given an integer array `nums` and an integer `k`, return an array of the averages of each subarray of size `2*k + 1` centered at each element in the array. If an element does not have a subarray of size `2*k + 1` centered at it (because it lies near the boundaries), it should be set to -1 in the output.
 *
 * Input:
 *     - An integer `n` representing the size of the array `nums`.
 *     - An integer array `nums` of size `n` where each element is an integer.
 *     - An integer `k` representing the radius of the subarray (1 <= k <= 100).
 *
 * Output:
 *     - An array of size `n` where each element represents the average of the subarray of size `2*k + 1` centered at that element, or -1 if no such subarray exists.
 *
 * Example:
 *     Input:
 *     7
 *     7 3 1 2 5 10 7
 *     3
 *     Output:
 *     [-1, -1, 3, 3, 4, 5, -1]
 *
 *     Explanation:
 *     The averages are calculated for subarrays of size `2*3 + 1 = 7`. The center elements 3, 2, 5, and 10 have subarrays of size 7 centered around them, while the first and last 3 elements do not.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03KRadiusSubarrayAverages {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Radius of the subarray

        // Call the function to get the averages of the subarrays
        System.out.println(Arrays.toString(getAverages(nums, k)));
        in.close();
    }

    /**
     * Solution to calculate the averages of subarrays of size 2*k + 1 centered at each element.
     *
     * Intuition:
     * - For each element, we need to compute the average of the subarray of size `2*k + 1` centered at that element.
     * - We sum the elements from `nums[i-k]` to `nums[i+k]` (where `i` is the center of the subarray).
     * - If a valid subarray can't be formed due to boundaries of the array, we return -1 for that element.
     *
     * Time Complexity: O(N), where `N` is the length of the array.
     * Space Complexity: O(N)
     *
     * @param nums The input array.
     * @param k The radius of the subarray.
     * @return The array of averages for each center element, or -1 where not possible.
     */
    public static int[] getAverages(int[] nums, int k) {
        int[] out = new int[nums.length];
        Arrays.fill(out, -1); // Initialize the output array with -1
        int s = 2 * k + 1; // Subarray size of 2*k + 1
        if (s > nums.length) {
            return out; // If the subarray is larger than the entire array, return [-1, -1, ...]
        }

        long sum = 0;
        // Compute the sum of the first (2*k) elements (to be able to start the sliding
        // window)
        for (int i = 0; i < s - 1; i++) {
            sum += nums[i];
        }

        // Slide the window and compute averages
        for (int i = k; i < nums.length - k; i++) {
            sum += nums[i + k]; // Add the rightmost element of the window
            out[i] = (int) (sum / s); // Calculate the average and assign to the center element
            sum -= nums[i - k]; // Remove the leftmost element of the window
        }

        return out; // Return the final result
    }
}
