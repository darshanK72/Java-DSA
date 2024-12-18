/**
 * Problem Statement:
 * 
 *     Given an integer array `arr` and a list of queries where each query is represented as a pair `[L, R]`, 
 *     your task is to find the XOR of elements from index `L` to `R` (both inclusive) for each query.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the size of the array `arr`.
 *     - An integer array `arr` of size `n` where (0 <= arr[i] <= 10^9).
 *     - An integer `q` (1 <= q <= 3 * 10^4), the number of queries.
 *     - A 2D array `queries` of size `q x 2`, where each query contains:
 *         - `queries[i][0]` (0 <= queries[i][0] <= n-1): the start index.
 *         - `queries[i][1]` (queries[i][0] <= queries[i][1] <= n-1): the end index.
 * 
 * Output:
 *     - An array of size `q` where the i-th element is the XOR of elements in the subarray `arr[L:R]` 
 *       for the i-th query `[L, R]`.
 * 
 * Example:
 *     Input:
 *         n = 4, arr = [1, 3, 4, 8]
 *         q = 2, queries = [[0, 1], [1, 3]]
 *     Output:
 *         [2, 15]
 * 
 *     Explanation:
 *         - Query 1: XOR of arr[0] to arr[1] = 1 ^ 3 = 2.
 *         - Query 2: XOR of arr[1] to arr[3] = 3 ^ 4 ^ 8 = 15.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j15XorQueriesOfSubarray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        int q = in.nextInt(); // Input: number of queries
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = in.nextInt(); // Start index of query
            queries[i][1] = in.nextInt(); // End index of query
        }

        // Calling approaches
        System.out.println(Arrays.toString(xorQueries(arr, queries))); // Brute-force approach
        System.out.println(Arrays.toString(xorQueriesEfficient(arr, queries))); // Optimized approach

        in.close();
    }

    /**
     * Approach: Brute-Force
     * 
     * Intuition:
     * - For each query `[L, R]`, iterate through the subarray from index `L` to `R` and compute the XOR of all elements.
     * - This approach directly processes each query by computing the XOR for the range in the array.
     * 
     * Time Complexity:
     * - O(q * n), where `q` is the number of queries and `n` is the average size of the subarray for each query.
     *   This approach may be slow for large inputs.
     * 
     * Space Complexity:
     * - O(q), for storing the result of each query.
     * 
     * @param arr The input array.
     * @param queries The list of queries.
     * @return An array containing the XOR result for each query.
     */
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] ans = new int[queries.length]; // Initialize result array
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                ans[i] ^= arr[j]; // Compute XOR for the range
            }
        }
        return ans;
    }

    /**
     * Approach: Optimized Using Prefix XOR
     * 
     * Intuition:
     * - Use a prefix XOR array where `prefix[i]` stores the XOR of all elements from index `0` to `i`.
     * - For a query `[L, R]`:
     *     - If `L == 0`, the result is simply `prefix[R]`.
     *     - Otherwise, the result is `prefix[R] ^ prefix[L-1]` because the XOR of a subarray can be derived by removing
     *       the prefix before `L` from the total XOR up to `R`.
     * 
     * Time Complexity:
     * - O(n + q), where `n` is the size of the array and `q` is the number of queries.
     * - Calculating the prefix XOR takes O(n), and each query is processed in O(1).
     * 
     * Space Complexity:
     * - O(n + q), where O(n) is for the prefix XOR array and O(q) is for storing the result.
     * 
     * @param arr The input array.
     * @param queries The list of queries.
     * @return An array containing the XOR result for each query.
     */
    public static int[] xorQueriesEfficient(int[] arr, int[][] queries) {
        int[] xorPrefix = new int[arr.length]; // Prefix XOR array
        int[] ans = new int[queries.length]; // Result array

        // Compute prefix XOR array
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            xorPrefix[i] = xor;
        }

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 0) {
                ans[i] = xorPrefix[queries[i][1]];
            } else {
                ans[i] = xorPrefix[queries[i][1]] ^ xorPrefix[queries[i][0] - 1];
            }
        }
        return ans;
    }
}
