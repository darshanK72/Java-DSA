/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` and an integer `k`, the task is to find the number of pairs `(i, j)` 
 *     such that `i < j` and `|nums[i] - nums[j]| == k`.
 *     The absolute difference `|x|` is defined as:
 *     - x if x >= 0.
 *     - -x if x < 0.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (-10^9 <= nums[i] <= 10^9).
 *     - An integer `k` (0 <= k <= 10^9), the target absolute difference.
 * 
 * Output:
 *     - An integer representing the number of pairs `(i, j)` such that `|nums[i] - nums[j]| == k`.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 2 1
 *     1
 *     Output:
 *     4
 * 
 *     Explanation:
 *     - The pairs with absolute difference 1 are: (1, 2), (2, 1), (1, 2), (2, 1).
 *     - So, the output is 4.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j01CountPairsWithAbsDiffK {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n]; // Array to hold the elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the value of k (absolute difference)

        // Output the result using both approaches
        System.out.println(countKDifference(arr, k)); // Brute force approach
        System.out.println(countKDifferenceHashMap(arr, k)); // Optimized approach

        // Close the scanner to avoid memory leaks
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - Iterate over all pairs `(i, j)` where `i < j`.
     * - For each pair, calculate the absolute difference and check if it equals `k`.
     * - Count all such pairs.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the array. We are checking all pairs.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from the input array.
     * 
     * @param nums The input array of numbers.
     * @param k The target absolute difference.
     * @return The number of pairs with absolute difference `k`.
     */
    public static int countKDifference(int[] nums, int k) {
        int count = 0; // Initialize the count of valid pairs
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    count++; // If the absolute difference is equal to k, increment the count
                }
            }
        }
        return count; // Return the final count of valid pairs
    }

    /**
     * Approach 2: Optimized Approach Using HashMap
     * 
     * Intuition:
     * - Instead of checking all pairs, we can use a hash map to store the frequency of numbers seen so far.
     * - For each number `nums[i]`, check if `nums[i] - k` or `nums[i] + k` has been seen before using the hash map.
     * - If either exists, it means there's a pair whose absolute difference is `k`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We only traverse the array once.
     * 
     * Space Complexity:
     * - O(n), as we store the frequency of each element in the hash map.
     * 
     * @param nums The input array of numbers.
     * @param k The target absolute difference.
     * @return The number of pairs with absolute difference `k`.
     */
    public static int countKDifferenceHashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Hash map to store the frequency of numbers
        int count = 0; // Initialize the count of valid pairs

        for (int i = 0; i < nums.length; i++) {
            // Check if nums[i] - k or nums[i] + k exists in the map
            count += map.getOrDefault(nums[i] - k, 0); // If nums[i] - k is present, add its frequency to count
            count += map.getOrDefault(nums[i] + k, 0); // If nums[i] + k is present, add its frequency to count
            // Update the frequency of nums[i] in the map
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        return count; // Return the final count of valid pairs
    }
}
