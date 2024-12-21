/**
 * Problem Statement:
 * 
 *     You are given an array `nums` that consists of non-negative integers. 
 *     Let us define `rev(x)` as the reverse of the non-negative integer `x`. 
 *     For example, `rev(123) = 321`, and `rev(120) = 21`. 
 *     A pair of indices `(i, j)` is nice if it satisfies all of the following conditions:
 * 
 *         - `0 <= i < j < nums.length`
 *         - `nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])`
 * 
 *     Return the number of nice pairs of indices. Since the result can be too large, 
 *     return it modulo `10^9 + 7`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (0 <= nums[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the number of nice pairs modulo `10^9 + 7`.
 * 
 * Example:
 *     Input:
 *         4
 *         42 11 1 97
 *     Output:
 *         2
 * 
 *     Explanation:
 *         Nice pairs are (0, 1) and (2, 3).
 */

import java.util.HashMap;
import java.util.Scanner;

public class j07CountNicePairs {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Output the result
        System.out.println(countNicePairs(arr));
        in.close();
    }

    /**
     * Approach: HashMap to Store Frequency of Reverse Differences
     * 
     * Intuition:
     * - For two numbers to form a nice pair, the difference between the number and 
     *   its reverse (`nums[i] - rev(nums[i])`) must be the same for both numbers.
     * - By calculating `rev(nums[i])` and keeping track of the difference `nums[i] - rev(nums[i])`
     *   in a HashMap, we can efficiently count the number of pairs satisfying the condition.
     * - For every match found in the map, we add the frequency of the difference to the count.
     * 
     * Explination:
     * - Compute `nums[i] - rev(nums[i])` for each number and use it as the key in the HashMap.
     * - The value in the HashMap represents the frequency of this difference seen so far.
     * - For every match, increment the count by the frequency of the difference, modulo `10^9 + 7`.
     * 
     * Time Complexity:
     * - O(n * k), where `n` is the size of the array and `k` is the average number of digits 
     *   in the numbers (for reversing a number).
     * 
     * Space Complexity:
     * - O(n), where `n` is the size of the array (to store frequencies in the HashMap).
     * 
     * @param nums The input array of numbers.
     * @return The number of nice pairs modulo `10^9 + 7`.
     */
    public static int countNicePairs(int[] nums) {
        final int MOD = 1_000_000_007;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            int revDiff = num - rev(num); // Compute reverse difference
            if (map.containsKey(revDiff)) {
                count = (count + map.get(revDiff)) % MOD; // Add frequency of this difference
                map.put(revDiff, map.get(revDiff) + 1); // Update frequency
            } else {
                map.put(revDiff, 1); // Initialize frequency for this difference
            }
        }
        return count;
    }

    /**
     * Helper Function: Reverse a Number
     * 
     * Intuition:
     * - To reverse a number, repeatedly extract the last digit, append it to a new number, 
     *   and divide the original number by 10.
     * 
     * Time Complexity:
     * - O(k), where `k` is the number of digits in the number.
     * 
     * Space Complexity:
     * - O(1).
     * 
     * @param n The number to reverse.
     * @return The reversed number.
     */
    public static int rev(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }
}
