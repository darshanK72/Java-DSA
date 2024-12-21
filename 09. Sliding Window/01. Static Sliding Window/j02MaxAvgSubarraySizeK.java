/**
 * Problem Statement:
 *
 *     Given an integer array `nums` and an integer `k`, find the maximum average sum of a subarray of size `k`.
 *
 * Input:
 *     - An integer `n` representing the size of the array (1 <= n <= 10^5).
 *     - An integer array `nums` of size `n` where each element is an integer.
 *     - An integer `k` representing the size of the subarray (1 <= k <= n).
 *
 * Output:
 *     - The maximum average sum of a subarray of size `k`.
 *
 * Example:
 *     Input:
 *     5
 *     1 12 -5 -6 50
 *     4
 *     Output:
 *     12.75
 *
 *     Explanation:
 *     The maximum average subarray of size 4 is [12, -5, -6, 50], and its average is 12.75.
 */

import java.util.Scanner;

public class j02MaxAvgSubarraySizeK {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: size of the subarray

        // Call the function to find maximum average sum of subarray of size k using
        // different approaches
        System.out.println(findMaxAverage(nums, k)); // Brute Force Approach
        System.out.println(findMaxAverageEfficient(nums, k)); // Sliding Window Approach
        in.close();
    }

    /**
     * Brute Force Approach (O(N*k)):
     *
     * Intuition:
     * - We check every possible subarray of size `k`, calculate its sum, and compute the average.
     * - This is done using a nested loop where for each starting index `i`, we compute the sum of the subarray of size `k`.
     *
     * Time Complexity: O(N*k), where `N` is the length of the array and `k` is the size of the subarray.
     * Space Complexity: O(1)
     *
     * @param nums The input array.
     * @param k The size of the subarray.
     * @return The maximum average of a subarray of size k.
     */
    public static double findMaxAverage(int[] nums, int k) {
        double ans = (double) Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            double sum = 0.0;
            // Calculate the sum of each subarray of size k
            for (int j = i; j < i + k && j < nums.length; j++) {
                sum += nums[j];
            }
            ans = Math.max(sum / k, ans); // Update the maximum average
        }
        return ans;
    }

    /**
     * Sliding Window Approach (O(N)):
     *
     * Intuition:
     * - Calculate the sum of the first `k` elements, then slide the window across the array.
     * - As the window moves, subtract the element that is leaving the window and add the element that is entering the window.
     * - This allows us to compute the average in constant time as we slide the window.
     *
     * Time Complexity: O(N), where `N` is the length of the array.
     * Space Complexity: O(1)
     *
     * @param nums The input array.
     * @param k The size of the subarray.
     * @return The maximum average of a subarray of size k.
     */
    public static double findMaxAverageEfficient(int[] nums, int k) {
        double ans = (double) Integer.MIN_VALUE;
        double sum = 0.0;

        // Calculate the sum of the first k elements
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        ans = sum / k; // Initial average of the first window

        // Slide the window and update the sum
        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k]; // Subtract the element going out of the window
            sum += nums[i]; // Add the new element coming into the window
            ans = Math.max(ans, sum / k); // Update the maximum average
        }
        return ans;
    }
}
