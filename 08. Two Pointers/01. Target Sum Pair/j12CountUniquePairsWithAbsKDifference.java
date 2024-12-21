/**
 * Problem Statement:
 * 
 *     Given an array of integers, count the number of unique pairs whose absolute 
 *     difference is exactly `k`. The pair (i, j) is considered valid if 
 *     `|arr[i] - arr[j]| == k`, where `i != j`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the target absolute difference.
 * 
 * Output:
 *     - An integer representing the number of unique pairs with the absolute difference `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 2 1 4
 *     1
 * 
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The pairs with absolute difference 1 are (1, 2) and (2, 1), which count as one unique pair.
 *     The pair (3, 4) is not valid as the difference is not 1.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class j12CountUniquePairsWithAbsKDifference {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target absolute difference
        System.out.println(countPairs(arr, k)); // Output: count of unique pairs
        System.out.println(countPairsEfficient(arr, k)); // Output: efficient solution
        in.close();
    }

    /**
     * Approach 1: Brute-force approach using HashSet to store unique pairs.
     * 
     * Intuition:
     * - We use a brute-force method by iterating over all pairs of elements.
     * - If the absolute difference between two elements is `k`, we check if the pair
     *   has already been counted by storing pairs in a HashSet.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the array. We are checking all pairs of elements.
     * 
     * Space Complexity:
     * - O(n), for storing unique pairs in the HashSet.
     * 
     * @param nums The input array of numbers.
     * @param k The target absolute difference.
     * @return The count of unique pairs with the absolute difference equal to `k`.
     */
    public static int countPairs(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array for efficient pairing.
        int s = 0, e = 1, count = 0;
        HashSet<String> set = new HashSet<>(); // To store unique pairs.
        while (e < nums.length) {
            int diff = Math.abs(nums[s] - nums[e]);
            if (diff == k && s != e) {
                if (!set.contains(nums[s] + "#" + nums[e])) {
                    count++;
                }
                set.add(nums[s] + "#" + nums[e]);
                e++;
                s++;
            } else if (diff > k) {
                s++;
            } else {
                e++;
            }
            if (s == e)
                e++;
        }
        return count;
    }

    /**
     * Approach 2: Optimized approach with additional checks to avoid duplicates.
     * 
     * Intuition:
     * - We optimize the solution by skipping duplicate values of `nums[s]` and `nums[e]` 
     *   to ensure we count only unique pairs.
     * - We iterate over the array with two pointers, but skip over any duplicate values after 
     *   a valid pair is found.
     * 
     * Time Complexity:
     * - O(n log n), where n is the size of the array due to sorting.
     * - The rest of the operations are linear, so the overall complexity is dominated by the sort step.
     * 
     * Space Complexity:
     * - O(n), for the HashSet to store unique pairs.
     * 
     * @param nums The input array of numbers.
     * @param k The target absolute difference.
     * @return The count of unique pairs with the absolute difference equal to `k`.
     */
    public static int countPairsEfficient(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array for efficient pairing.
        int s = 0, e = 1, count = 0;
        while (e < nums.length) {
            int diff = Math.abs(nums[s] - nums[e]);
            if (diff == k && s != e) {
                count++;
                e++;
                s++;
                // Skip over duplicates of `nums[e]` and `nums[s]`.
                while (e < nums.length && nums[e] == nums[e - 1]) {
                    e++;
                }
                while (s < e && nums[s] == nums[s - 1]) {
                    s++;
                }
            } else if (diff > k) {
                s++;
            } else {
                e++;
            }
            if (s == e)
                e++;
        }
        return count;
    }
}
