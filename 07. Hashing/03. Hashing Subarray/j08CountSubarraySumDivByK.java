/**
 * Problem Statement:
 *
 *     Given an array of integers `nums` and an integer `k`, you need to find the total number of subarrays
 *     whose sum is divisible by `k`. A subarray is defined as a contiguous part of an array.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the divisor for checking divisibility of subarray sums.
 *
 * Output:
 *     - An integer representing the number of subarrays whose sum is divisible by `k`.
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
 *     The subarrays whose sums are divisible by 5 are:
 *     - [5, 0, -2, -3] 
 *     - [0, -2, -3, 1] 
 *     - [4, 5, 0, -2, -3] 
 *     - [4, 5] 
 *     - [5, 0] 
 *     - [-2, -3, 1] 
 *     - [4, 5, 0, -2, -3, 1] 
 */

import java.util.HashMap;
import java.util.Scanner;

public class j08CountSubarraySumDivByK {
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
        System.out.println(subarraysDivByK(arr, k));
        System.out.println(subarraysDivByKHashMap(arr, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - This approach checks each subarray starting from every index in the array and computes its sum.
     *   If the sum of the subarray is divisible by `k`, it is counted.
     * - This approach is straightforward but inefficient for large arrays due to its O(n^2) time complexity.
     * 
     * Time Complexity:
     * - O(n^2): We iterate over every possible subarray, and each subarray sum computation takes O(n) time.
     * 
     * Space Complexity:
     * - O(1): No extra space is used apart from the input array.
     * 
     * @param nums The input array of numbers.
     * @param k The divisor to check divisibility.
     * @return The number of subarrays whose sum is divisible by `k`.
     */
    public static int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            for (int j = i; j < nums.length; j++) {
                s += nums[j];
                if (s % k == 0)
                    count++;
            }
        }
        return count;
    }

    /**
     * Approach 2: Optimized Approach Using HashMap
     * 
     * Intuition:
     * - We use a prefix sum to track the cumulative sum as we traverse the array.
     * - For each cumulative sum, we calculate its remainder when divided by `k`. If the same remainder
     *   has appeared before, the subarray between the previous index and the current index has a sum
     *   divisible by `k`.
     * - A HashMap is used to store the frequency of remainders. If a remainder repeats, we add the
     *   frequency of that remainder to the count.
     * 
     * Time Complexity:
     * - O(n): We only pass through the array once and use constant-time operations for each element.
     * 
     * Space Complexity:
     * - O(n): In the worst case, we store one remainder for every element in the array.
     * 
     * @param nums The input array of numbers.
     * @param k The divisor to check divisibility.
     * @return The number of subarrays whose sum is divisible by `k`.
     */
    public static int subarraysDivByKHashMap(int[] nums, int k) {
        // Initialize a map to store frequency of remainders
        HashMap<Integer, Integer> map = new HashMap<>();

        // Initialize the map with remainder 0 appearing once to handle edge case of
        // subarrays starting from index 0
        map.put(0, 1);

        int s = 0; // This variable stores the cumulative sum
        int count = 0; // This variable stores the number of subarrays divisible by k

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            s += nums[i]; // Update cumulative sum

            // Calculate remainder of cumulative sum when divided by k
            // (s % k + k) % k ensures the remainder is always non-negative
            // Explanation: Modulo can result in negative values, so adding k ensures we get
            // a positive remainder
            // and then we take modulo k again to handle the cases where the sum is exactly
            // divisible by k.
            int rem = (s % k + k) % k;

            // Add the number of times the same remainder has occurred before to the count
            // If remainder rem has occurred before, it means there exists a subarray whose
            // sum is divisible by k
            count += map.getOrDefault(rem, 0);

            // Update the frequency of the current remainder
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}
