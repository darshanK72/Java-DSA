/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums` and an integer `k`, check if the array has a subarray
 *     of size at least 2 whose elements sum up to a multiple of `k` (i.e., sum % k == 0).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (-10^5 <= nums[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), the divisor.
 * 
 * Output:
 *     - A boolean value: true if such a subarray exists, otherwise false.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         nums = [23, 2, 4, 6, 7]
 *         k = 6
 *     Output:
 *         true
 * 
 *     Explanation:
 *         The subarray [2, 4] has a sum of 6, which is divisible by k = 6.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j07CheckSubarraySumDivByK {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Divisor

        // Approach 1: Brute Force
        System.out.println(checkSubarraySumDivByK(nums, k));

        // Approach 2: HashMap (Optimized)
        System.out.println(checkSubarraySumDivByKHashMap(nums, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Iterate through all possible subarrays, calculate their sum, and check if it is divisible by `k`.
     * - Ensure that the subarray has a size of at least 2.
     * 
     * Time Complexity:
     * - O(N^2), as we calculate the sum for every subarray.
     * 
     * Space Complexity:
     * - O(1), as we use constant extra space.
     * 
     * @param nums The input array.
     * @param k The divisor.
     * @return True if a subarray divisible by `k` exists, otherwise false.
     */
    public static boolean checkSubarraySumDivByK(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0; // Initialize sum for the subarray starting at index i
            for (int j = i; j < nums.length; j++) {
                sum += nums[j]; // Add the current element to the sum
                if (sum % k == 0 && (j - i + 1) >= 2) {
                    return true; // Check divisibility and subarray size
                }
            }
        }
        return false;
    }

    /**
     * Approach 2: HashMap (Optimized)
     * 
     * Intuition:
     * - Use the modulo operation and HashMap to reduce the problem's complexity.
     * - Maintain a running sum and store the remainders when divided by `k` in the HashMap.
     * - If the same remainder is encountered again, the subarray between those indices has a sum divisible by `k`.
     * - Ensure the subarray size is at least 2.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array once and perform constant-time HashMap operations.
     * 
     * Space Complexity:
     * - O(N), due to the storage of remainders in the HashMap.
     * 
     * @param nums The input array.
     * @param k The divisor.
     * @return True if a subarray divisible by `k` exists, otherwise false.
     */
    public static boolean checkSubarraySumDivByKHashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store remainders and their first occurrence index
        int sum = 0; // Cumulative sum
        map.put(0, -1); // Base case: Remainder 0 at index -1

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Update the cumulative sum

            // Calculate remainder and adjust for negative values
            int rem = (sum % k + k) % k;

            if (map.containsKey(rem)) {
                if (i - map.get(rem) >= 2) { // Ensure subarray size is at least 2
                    return true;
                }
            } else {
                map.put(rem, i); // Store the first occurrence of the remainder
            }
        }

        return false; // Return false if no valid subarray is found
    }
}
