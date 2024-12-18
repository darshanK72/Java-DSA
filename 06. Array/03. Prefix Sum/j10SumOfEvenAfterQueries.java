/**
 * Problem Statement:
 * 
 *     You are given an integer array `nums` and a 2D array `queries` where each query contains two integers `val` and `index`.
 *     For each query:
 *     - Add `val` to `nums[index]`.
 *     - Calculate the sum of all even integers in the array after the update.
 * 
 *     Return an array of integers, where each element corresponds to the sum of even numbers after applying the respective query.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An array `nums` of size `n`, where (1 <= nums[i] <= 10^4).
 *     - An integer `q` (1 <= q <= 10^4), the number of queries.
 *     - A 2D array `queries` of size `q` where each element is a pair `[val, index]`.
 * 
 * Output:
 *     - An array of size `q`, where each element is the sum of even numbers after applying the respective query.
 * 
 * Example:
 *     Input:
 *     4
 *     [1, 2, 3, 4]
 *     3
 *     [[1, 0], [-3, 2], [-4, 3]]
 *     Output:
 *     [8, 6, 2]
 * 
 *     Explanation:
 *     - Initial array: [1, 2, 3, 4].
 *     - After the first query: [2, 2, 3, 4], even sum = 2 + 2 + 4 = 8.
 *     - After the second query: [2, 2, 0, 4], even sum = 2 + 2 + 0 + 4 = 6.
 *     - After the third query: [2, 2, 0, 0], even sum = 2 + 2 = 2.
 */

import java.util.Scanner;

public class j10SumOfEvenAfterQueries {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Elements of the array
        }

        int q = in.nextInt(); // Number of queries
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = in.nextInt(); // val in the query
            queries[i][1] = in.nextInt(); // index in the query
        }

        // Call solution method
        int[] result = sumEvenAfterQueries(arr, queries);
        for (int res : result) {
            System.out.println(res); // Output the result of each query
        }

        in.close();
    }

    /**
     * Approach: Efficient Update and Tracking
     * 
     * Intuition:
     * - Instead of recalculating the sum of all even numbers from scratch after each query, 
     *   maintain a running sum of even numbers (`sum`).
     * - For each query:
     *     - If `nums[index]` is even before the update, subtract it from `sum`.
     *     - Update `nums[index]` by adding `val`.
     *     - If `nums[index]` becomes even after the update, add it to `sum`.
     * - This approach avoids scanning the entire array repeatedly and keeps the time complexity optimal.
     * 
     * Time Complexity:
     * - O(n + q), where `n` is the size of the array and `q` is the number of queries.
     *   - Initial sum calculation: O(n).
     *   - Processing each query: O(1), repeated `q` times.
     * 
     * Space Complexity:
     * - O(q), where `q` is the size of the output array.
     * 
     * @param nums The input array of integers.
     * @param queries A 2D array containing the queries.
     * @return An array of integers representing the sum of even numbers after each query.
     */
    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] output = new int[queries.length]; // To store the result for each query
        int sum = 0;

        // Calculate the initial sum of even numbers
        for (int num : nums) {
            if (num % 2 == 0) {
                sum += num;
            }
        }

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0];
            int index = queries[i][1];

            // If nums[index] is even, remove it from sum
            if (nums[index] % 2 == 0) {
                sum -= nums[index];
            }

            // Update nums[index]
            nums[index] += val;

            // If nums[index] is now even, add it to sum
            if (nums[index] % 2 == 0) {
                sum += nums[index];
            }

            // Store the current sum in the output array
            output[i] = sum;
        }

        return output;
    }
}
