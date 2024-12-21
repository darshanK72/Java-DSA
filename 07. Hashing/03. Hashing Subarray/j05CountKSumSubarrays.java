/**
 * Problem Statement:
 * 
 *     Given an array of integers and an integer `k`, count the number of subarrays
 *     that have a sum equal to `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 *     - An integer `k` (-10^9 <= k <= 10^9), the target sum.
 * 
 * Output:
 *     - An integer representing the count of subarrays with a sum equal to `k`.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [1, 2, 3, 3, 2]
 *         k = 6
 *     Output:
 *         3
 * 
 *     Explanation:
 *         The subarrays with sum 6 are: [1, 2, 3], [3, 3], [3, 2].
 */

import java.util.HashMap;
import java.util.Scanner;

public class j05CountKSumSubarrays {

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
        System.out.println(countSubarraysSumK(arr, k));

        // Approach 2: HashMap (Optimized)
        System.out.println(countSubarraysSumKHashMap(arr, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Iterate through all possible subarrays, calculate their sum, and check if it equals `k`.
     * - Increment the count for each subarray satisfying the condition.
     * 
     * Time Complexity:
     * - O(N^2), as we calculate the sum for every subarray.
     * 
     * Space Complexity:
     * - O(1), as we use constant extra space.
     * 
     * @param nums The input array.
     * @param k The target sum.
     * @return The count of subarrays with a sum equal to `k`.
     */
    public static int countSubarraysSumK(int[] nums, int k) {
        int count = 0; // Initialize the count
        for (int i = 0; i < nums.length; i++) {
            int sum = 0; // Sum for subarray starting at index i
            for (int j = i; j < nums.length; j++) {
                sum += nums[j]; // Add current element to sum
                if (sum == k)
                    count++; // Increment count if sum equals k
            }
        }
        return count;
    }

    /**
     * Approach 2: HashMap (Optimized)
     * 
     * Intuition:
     * - Use a prefix sum approach combined with a HashMap to store the frequency of prefix sums.
     * - At each step, check if the difference between the current prefix sum and `k` exists in the HashMap.
     * - If it exists, it means there are subarrays that sum up to `k` ending at the current index.
     * - Add the frequency of the difference to the count.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array once and perform constant-time HashMap operations.
     * 
     * Space Complexity:
     * - O(N), due to the storage of prefix sums in the HashMap.
     * 
     * @param arr The input array.
     * @param k The target sum.
     * @return The count of subarrays with a sum equal to `k`.
     */
    public static int countSubarraysSumKHashMap(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store prefix sums and their frequencies
        map.put(0, 1); // Base case: A prefix sum of 0 occurs once
        int sum = 0; // Cumulative sum
        int count = 0; // Initialize count

        for (int value : arr) {
            sum += value; // Update the cumulative sum

            // If (sum - k) exists in the map, add its frequency to count
            count += map.getOrDefault(sum - k, 0);

            // Update the frequency of the current prefix sum in the map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
