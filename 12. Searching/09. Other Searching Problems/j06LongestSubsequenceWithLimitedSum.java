/**
 * Problem Statement:
 * 
 * Given an array `nums` and an array `queries`, find the longest subsequence of `nums` such that 
 * the sum of the subsequence is less than or equal to each query value in `queries`.
 * 
 * Input:
 *     - An array `nums` of integers.
 *     - An array `queries` of integers, representing the maximum allowable sum for the subsequences.
 * 
 * Output:
 *     - An array of integers where each value corresponds to the length of the longest subsequence 
 *       with a sum <= `queries[i]`.
 * 
 * Example:
 *     Input:
 *         nums = {4, 2, 1, 3}
 *         queries = {5, 10, 3}
 *     Output:
 *         Results = {2, 4, 1}
 * 
 * Constraints:
 *     - `nums` and `queries` contain positive integers.
 *     - The size of `nums` and `queries` can vary from 1 to 10^4.
 */

import java.util.Arrays;

public class j06LongestSubsequenceWithLimitedSum {
    public static void main(String[] args) {
        // Input: Array of numbers and queries
        int[] nums = { 4, 2, 1, 3 };
        int[] queries = { 5, 10, 3 };

        // Approach 1: Brute Force Method
        int[] results = answerQueriesBruitForce(nums, queries);
        System.out.println("Results (Brute Force): " + Arrays.toString(results));

        // Approach 2: Efficient Method Using Prefix Sums and Binary Search
        int[] efficientResults = answerQueriesEfficient(nums, queries);
        System.out.println("Results (Efficient): " + Arrays.toString(efficientResults));
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Sort the `nums` array in ascending order to consider smaller elements first for the subsequence.
     * - For each query in `queries`, iterate through the sorted `nums` array, summing elements until the sum exceeds the query value.
     * - Count the number of elements included before exceeding the query value.
     * 
     * Steps:
     * 1. Sort the `nums` array in ascending order.
     * 2. For each query, find the longest subsequence with a sum <= query value.
     *    - Call the helper function `longestSeqWithLimitedSum` for this purpose.
     * 
     * Time Complexity:
     * - O(n log n + q * n), where `n` is the length of `nums` and `q` is the length of `queries`.
     *   Sorting `nums` takes O(n log n), and for each query, iterating through `nums` takes O(n).
     * 
     * Space Complexity:
     * - O(q) for the output array.
     * 
     * @param nums Array of numbers.
     * @param queries Array of query values.
     * @return An array of results for each query.
     */
    public static int[] answerQueriesBruitForce(int[] nums, int[] queries) {
        Arrays.sort(nums); // Step 1: Sort the `nums` array.
        int[] out = new int[queries.length]; // Output array to store results.

        // Step 2: Process each query.
        for (int i = 0; i < queries.length; i++) {
            out[i] = longestSeqWithLimitedSum(nums, queries[i]);
        }
        return out;
    }

    /**
     * Helper Method for Brute Force: Finds the longest subsequence with a sum <= q.
     * 
     * Steps:
     * 1. Iterate through the sorted `nums` array.
     * 2. Add elements to the sum until it exceeds `q`.
     * 3. Return the number of elements included.
     * 
     * @param nums Sorted array of numbers.
     * @param q Query value (maximum sum allowed).
     * @return Length of the longest subsequence with a sum <= q.
     */
    public static int longestSeqWithLimitedSum(int[] nums, int q) {
        int sum = 0; // Tracks the running sum of the subsequence.
        int i = 0; // Tracks the length of the subsequence.

        // Add elements until the sum exceeds `q`.
        for (; i < nums.length; i++) {
            if (sum + nums[i] <= q) {
                sum += nums[i]; // Add the current element to the sum.
            } else {
                return i; // Return the length if the sum exceeds `q`.
            }
        }
        return i; // If all elements fit, return the total length.
    }

    /**
     * Approach 2: Efficient Method Using Prefix Sums and Binary Search
     * 
     * Intuition:
     * - Sort the `nums` array and compute a prefix sum array to track cumulative sums.
     * - For each query, use binary search to find the maximum length of the subsequence 
     *   whose sum <= query value.
     * 
     * Steps:
     * 1. Sort the `nums` array.
     * 2. Compute prefix sums for `nums`, where `nums[i]` contains the sum of the first `i + 1` elements.
     * 3. For each query, use binary search on the prefix sums array to find the length of the 
     *    subsequence with a sum <= query value.
     * 
     * Time Complexity:
     * - O(n log n + q log n), where `n` is the length of `nums` and `q` is the length of `queries`.
     *   Sorting `nums` and computing prefix sums take O(n log n), and each binary search for `q` queries takes O(q log n).
     * 
     * Space Complexity:
     * - O(q) for the output array.
     * 
     * @param nums Array of numbers.
     * @param queries Array of query values.
     * @return An array of results for each query.
     */
    public static int[] answerQueriesEfficient(int[] nums, int[] queries) {
        Arrays.sort(nums); // Step 1: Sort the `nums` array.

        // Step 2: Compute prefix sums for `nums`.
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        int[] out = new int[queries.length]; // Output array to store results.

        // Step 3: Process each query using binary search.
        for (int i = 0; i < queries.length; i++) {
            out[i] = binarySearch(nums, queries[i]);
        }
        return out;
    }

    /**
     * Helper Method for Binary Search: Finds the length of the subsequence with a sum <= q.
     * 
     * Steps:
     * 1. Perform binary search on the prefix sums array to find the largest index `i` such that `nums[i] <= q`.
     * 2. Return the index + 1 as the length of the subsequence.
     * 
     * @param nums Prefix sums array.
     * @param q Query value (maximum sum allowed).
     * @return Length of the longest subsequence with a sum <= q.
     */
    public static int binarySearch(int[] nums, int q) {
        int s = 0; // Start of the search range.
        int e = nums.length - 1; // End of the search range.
        int ans = -1; // Tracks the maximum valid index.

        // Binary search to find the largest valid index.
        while (s <= e) {
            int mid = s + (e - s) / 2;

            // Check if the current prefix sum <= query value.
            if (nums[mid] <= q) {
                ans = mid; // Update the answer to the current index.
                s = mid + 1; // Move to the right half.
            } else {
                e = mid - 1; // Move to the left half.
            }
        }
        return ans + 1; // Return the length of the subsequence.
    }
}
