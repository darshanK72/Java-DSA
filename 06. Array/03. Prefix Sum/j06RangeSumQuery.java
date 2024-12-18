/**
 * Problem Statement:
 * 
 *     Given an array `arr[]` of integers, you need to perform multiple range sum queries. 
 *     Each query consists of two indices `l` and `r`, and the task is to compute the sum of the elements 
 *     between these indices, inclusive. The range sum can be computed efficiently using a prefix sum array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr[]` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `t` (1 <= t <= 10^4), the number of queries.
 *     - `t` pairs of integers `l` and `r` (0 <= l <= r < n), where each pair defines the range for which the sum needs to be computed.
 * 
 * Output:
 *     - For each query, output the sum of elements in the range from index `l` to index `r` (inclusive).
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     3
 *     0 2
 *     1 4
 *     2 4
 * 
 *     Output:
 *     L-R Sum = 6
 *     L-R Sum = 14
 *     L-R Sum = 12
 * 
 *     Explanation:
 *     For the first query (0, 2), the sum is 1 + 2 + 3 = 6.
 *     For the second query (1, 4), the sum is 2 + 3 + 4 + 5 = 14.
 *     For the third query (2, 4), the sum is 3 + 4 + 5 = 12.
 */

import java.util.Scanner;

public class j06RangeSumQuery {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n]; // Array to store the elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        int t = in.nextInt(); // Number of queries
        int[] rangeSum = getRangeSum(arr); // Precompute the prefix sum array

        // Processing each query
        while ((t--) > 0) {
            int l = in.nextInt(); // Left index of the query
            int r = in.nextInt(); // Right index of the query
            if (l == 0) {
                // If l == 0, the range sum is directly the value from the prefix sum array
                System.out.println("L-R Sum = " + rangeSum[r]);
            } else {
                // If l > 0, compute the sum by subtracting the prefix sum at l-1 from r
                System.out.println("L-R Sum = " + (rangeSum[r] - rangeSum[l - 1]));
            }
        }

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Prefix Sum Array
     * 
     * Intuition:
     * - The key idea is to use a prefix sum array to compute the sum of any subarray in constant time.
     * - The prefix sum array `preSum[]` is constructed such that `preSum[i]` stores the sum of the array elements from index 0 to i.
     * - For a query with indices `l` and `r`, the sum of the subarray `arr[l]` to `arr[r]` can be calculated as:
     *     - If `l == 0`, the sum is simply `preSum[r]`.
     *     - Otherwise, the sum is `preSum[r] - preSum[l-1]`.
     * 
     * Time Complexity:
     * - O(n) for constructing the prefix sum array.
     * - O(1) for each query, as we are simply accessing values in the prefix sum array.
     * - Total complexity for all queries: O(n + t), where `t` is the number of queries.
     * 
     * Space Complexity:
     * - O(n) for storing the prefix sum array.
     * 
     * @param arr The array of integers.
     * @return The prefix sum array.
     */
    public static int[] getRangeSum(int[] arr) {
        int[] preSum = new int[arr.length]; // Array to store the prefix sums
        preSum[0] = arr[0]; // Initialize the first element
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = arr[i] + preSum[i - 1]; // Fill the prefix sum array
        }
        return preSum; // Return the prefix sum array
    }
}
