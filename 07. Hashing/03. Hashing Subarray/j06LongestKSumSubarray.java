/**
 * Problem Statement:
 * 
 *     Given an array of integers and an integer `k`, find the length of the longest subarray
 *     whose elements sum up to `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 *     - An integer `k` (-10^9 <= k <= 10^9), the target sum.
 * 
 * Output:
 *     - An integer representing the length of the longest subarray with a sum equal to `k`.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [1, 2, 3, 2, 5]
 *         k = 5
 *     Output:
 *         2
 * 
 *     Explanation:
 *         The subarrays with sum 5 are [5] and [3, 2]. The longest has length 2.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j06LongestKSumSubarray {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Target sum

        // Approach 1: Brute Force
        System.out.println(longestKSumSubarray(arr, n, k));

        // Approach 2: HashMap (Optimized)
        System.out.println(longestKSumSubarrayHashMap(arr, n, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Iterate through all possible subarrays, calculate their sum, and check if it equals `k`.
     * - Track the maximum length of any subarray satisfying the condition.
     * 
     * Time Complexity:
     * - O(N^2), as we calculate the sum for every subarray.
     * 
     * Space Complexity:
     * - O(1), as we use constant extra space.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @param k The target sum.
     * @return The length of the longest subarray with a sum equal to `k`.
     */
    public static int longestKSumSubarray(int[] arr, int n, int k) {
        int maxL = 0; // Initialize maximum length
        for (int i = 0; i < n; i++) {
            int sum = 0; // Initialize sum for the subarray starting at index i
            for (int j = i; j < n; j++) {
                sum += arr[j]; // Add the current element to the sum
                if (sum == k) {
                    maxL = Math.max(maxL, j - i + 1); // Update maximum length
                }
            }
        }
        return maxL;
    }

    /**
     * Approach 2: HashMap (Optimized)
     * 
     * Intuition:
     * - Use a prefix sum approach combined with a HashMap to store the first occurrence of each prefix sum.
     * - If the difference between the current prefix sum and `k` exists in the HashMap,
     *   the subarray between those indices has a sum equal to `k`.
     * - Track the maximum length of such subarrays.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array once and perform constant-time HashMap operations.
     * 
     * Space Complexity:
     * - O(N), due to the storage of prefix sums in the HashMap.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @param k The target sum.
     * @return The length of the longest subarray with a sum equal to `k`.
     */
    public static int longestKSumSubarrayHashMap(int[] arr, int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store prefix sums and their first occurrences
        int sum = 0; // Cumulative sum
        int maxL = 0; // Initialize maximum length
        map.put(0, -1); // Base case: A prefix sum of 0 at index -1

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // Update the cumulative sum

            // Check if (sum - k) exists in the map
            if (map.containsKey(sum - k)) {
                maxL = Math.max(maxL, i - map.get(sum - k)); // Update maximum length
            }

            // Store the first occurrence of the cumulative sum
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxL;
    }
}
