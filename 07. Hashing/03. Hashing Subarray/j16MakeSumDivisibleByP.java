/**
 * Problem Statement:
 * 
 *     Given an array of positive integers `nums`, remove the smallest subarray 
 *     (possibly empty) such that the sum of the remaining elements is divisible by `p`. 
 *     It is not allowed to remove the whole array. 
 *     Return the length of the smallest subarray that you need to remove, or `-1` if it is impossible.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `p` (1 <= p <= 10^9) representing the divisor.
 * 
 * Output:
 *     - An integer representing the length of the smallest subarray that needs to be removed,
 *       or `-1` if it's impossible.
 * 
 * Example:
 *     Input:
 *     4
 *     3 1 4 2
 *     6
 *     Output:
 *     1
 * 
 *     Explanation:
 *     The sum of the array is `3 + 1 + 4 + 2 = 10`, and 10 % 6 = 4. 
 *     By removing the subarray `[4]`, the remaining sum `3 + 1 + 2 = 6` is divisible by 6. 
 *     Thus, the smallest subarray length to be removed is `1`.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j16MakeSumDivisibleByP {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int p = in.nextInt(); // Input: divisor `p`

        // Call solution
        System.out.println(minSubarrayRemoved(arr, p));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach (O(N^2))
     * 
     * Intuition:
     * - We can iterate over all possible subarrays of the given array and check 
     *   if the sum of the remaining elements (after removing a subarray) is divisible by `p`.
     *   This can be achieved by directly computing the sum of the remaining elements 
     *   and checking its divisibility.
     * - However, this approach is inefficient as it checks all subarrays and performs the modulo operation
     *   multiple times.
     * 
     * Time Complexity:
     * - O(N^2), as we iterate through all possible subarrays, which involves a nested loop.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used beyond the input array.
     * 
     * @param nums The input array of numbers.
     * @param p The divisor.
     * @return The length of the smallest subarray that needs to be removed, or `-1` if impossible.
     */
    public static int minSubarrayRemovedBruteForce(int[] nums, int p) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int k = (int) (sum % p);
        if (k == 0)
            return 0;

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long subarraySum = 0;
                for (int l = i; l <= j; l++) {
                    subarraySum += nums[l];
                }
                long remainingSum = sum - subarraySum;
                if (remainingSum % p == 0) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * Approach 2: Optimized Approach using Hash Map (O(N))
     * 
     * Intuition:
     * - Instead of checking all subarrays, we can take advantage of prefix sums and use a HashMap.
     * - We can calculate the prefix sum of the array and compute its modulo `p` at each index.
     *   The idea is to find the smallest subarray that when removed results in the remaining sum divisible by `p`.
     * - By storing the modulus results in a HashMap, we can track the first occurrence of each remainder,
     *   and for any given element, check if the remainder difference is a multiple of `p` to identify valid subarrays.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array only once and perform constant-time operations for each element.
     * 
     * Space Complexity:
     * - O(N), as we use a HashMap to store the remainders and their indices.
     * 
     * @param nums The input array of numbers.
     * @param p The divisor.
     * @return The length of the smallest subarray that needs to be removed, or `-1` if impossible.
     */
    public static int minSubarrayRemoved(int[] nums, int p) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int k = (int) (sum % p);
        if (k == 0)
            return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Base case to handle cases where the sum modulo p is found at the beginning
        long prefixSum = 0;
        int minLength = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int mod = (int) (prefixSum % p);
            int requiredMod = ((mod - k) % p + p) % p;

            if (map.containsKey(requiredMod)) {
                minLength = Math.min(minLength, i - map.get(requiredMod));
            }

            map.put(mod, i);
        }

        return minLength == nums.length ? -1 : minLength;
    }
}
