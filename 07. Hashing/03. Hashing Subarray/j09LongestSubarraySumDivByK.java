/**
 * Problem Statement:
 *
 *     Given an array of integers `nums` and an integer `k`, you are required to find the length of the longest
 *     subarray whose sum is divisible by `k`. A subarray is defined as a contiguous part of the array.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the divisor for checking divisibility of subarray sums.
 *
 * Output:
 *     - An integer representing the length of the longest subarray whose sum is divisible by `k`.
 *
 * Example:
 *     Input:
 *     6
 *     4 5 0 -2 -3 1
 *     5
 *
 *     Output:
 *     7
 *
 *     Explanation:
 *     The longest subarray whose sum is divisible by 5 is the subarray from index 0 to 6, i.e., the entire array
 *     [4, 5, 0, -2, -3, 1].
 */

import java.util.HashMap;
import java.util.Scanner;

public class j09LongestSubarraySumDivByK {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the divisor for checking divisibility

        // Call both solution methods
        System.out.println(longestSubarraysDivByK(arr, k));
        System.out.println(longestSubarraysDivByKHashMap(arr, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - This approach iterates through all possible subarrays and computes their sums. If the sum of a subarray is divisible
     *   by `k`, we update the length of the longest valid subarray found so far.
     * - This approach has time complexity O(n^2), which is inefficient for large input sizes due to the nested loops.
     * 
     * Time Complexity:
     * - O(n^2): We iterate over all possible pairs of start and end indices for the subarrays, and for each pair, we calculate the sum.
     * 
     * Space Complexity:
     * - O(1): The space complexity is constant as we only use a few variables to track the result.
     * 
     * @param nums The input array of numbers.
     * @param k The divisor to check divisibility.
     * @return The length of the longest subarray whose sum is divisible by `k`.
     */
    public static int longestSubarraysDivByK(int[] nums, int k) {
        int maxL = 0; // Initialize the maximum length of the subarray
        for (int i = 0; i < nums.length; i++) {
            int s = 0; // Initialize the cumulative sum
            for (int j = i; j < nums.length; j++) {
                s += nums[j]; // Update the cumulative sum
                if (s % k == 0) { // Check if the sum is divisible by k
                    maxL = Math.max(maxL, j - i + 1); // Update the maximum length
                }
            }
        }
        return maxL;
    }

    /**
     * Approach 2: Optimized Approach Using HashMap
     * 
     * Intuition:
     * - We use a prefix sum and a HashMap to track the first occurrence of each remainder when the cumulative sum is divided by `k`.
     * - If the same remainder is found again, it means there is a subarray between the two indices that is divisible by `k`.
     * - This approach allows us to solve the problem in O(n) time by scanning the array just once and using the remainder as a key.
     * 
     * Time Complexity:
     * - O(n): We traverse the array only once, and HashMap operations (insert and lookup) are O(1) on average.
     * 
     * Space Complexity:
     * - O(n): The space complexity is proportional to the number of different remainders we encounter.
     * 
     * Explanation of Key Step:
     * - We use the formula `(s % k + k) % k` to ensure that the remainder is always positive, even when the cumulative sum `s` is negative.
     * - The reason we add `k` and take the modulus again is to avoid negative remainders. Modulo operations in Java can produce negative values for negative numbers.
     * 
     * @param nums The input array of numbers.
     * @param k The divisor to check divisibility.
     * @return The length of the longest subarray whose sum is divisible by `k`.
     */
    public static int longestSubarraysDivByKHashMap(int[] nums, int k) {
        // Initialize a map to store the first occurrence of each remainder
        HashMap<Integer, Integer> map = new HashMap<>();

        // Initialize the map with remainder 0 at index -1 to handle the case where the
        // subarray starts from index 0
        map.put(0, -1);

        int s = 0; // This variable stores the cumulative sum
        int maxL = 0; // This variable stores the length of the longest subarray found so far

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            s += nums[i]; // Update the cumulative sum

            // Calculate remainder of cumulative sum when divided by k
            // (s % k + k) % k ensures the remainder is always non-negative
            int rem = (s % k + k) % k;

            // Check if this remainder has been encountered before
            if (map.containsKey(rem)) {
                // Calculate the length of the subarray between the previous occurrence of this
                // remainder and the current index
                maxL = Math.max(maxL, i - map.get(rem));
            }
            // If this remainder hasn't been encountered before, store the current index
            if (!map.containsKey(rem)) {
                map.put(rem, i);
            }
        }

        return maxL;
    }
}
