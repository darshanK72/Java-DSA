/**
 * Problem Statement:
 * 
 *     You are given an array of integers of size `n` initialized to 0. 
 *     Additionally, there are `q` update operations, where each operation is represented as an array of 3 integers 
 *     `[startIndex, endIndex, increment]`. For each operation, you need to increment all elements in the array 
 *     from `startIndex` to `endIndex` (both inclusive) by `increment`.
 * 
 *     Your task is to return the resulting array after performing all the updates.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An integer `q` (1 <= q <= 10^4), representing the number of update operations.
 *     - A 2D array `updates` of size `q x 3`, where each row contains three integers:
 *         - `updates[i][0]` (0 <= updates[i][0] <= n-1): start index for the operation.
 *         - `updates[i][1]` (0 <= updates[i][1] <= n-1): end index for the operation.
 *         - `updates[i][2]` (-10^5 <= updates[i][2] <= 10^5): increment value.
 * 
 * Output:
 *     - An array of integers of size `n` representing the final state of the array after applying all updates.
 * 
 * Example:
 *     Input:
 *         n = 5, q = 2
 *         updates = [[1, 3, 2], [2, 4, 3]]
 *     Output:
 *         [0, 2, 5, 5, 3]
 * 
 *     Explanation:
 *         - After the first update [1, 3, 2], the array becomes [0, 2, 2, 2, 0].
 *         - After the second update [2, 4, 3], the array becomes [0, 2, 5, 5, 3].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j14RangeAdditionI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int q = in.nextInt(); // Input: number of update operations
        int[][] updates = new int[q][3];
        for (int i = 0; i < q; i++) {
            updates[i][0] = in.nextInt(); // startIndex of the update
            updates[i][1] = in.nextInt(); // endIndex of the update
            updates[i][2] = in.nextInt(); // increment value
        }

        // Calling approaches
        System.out.println(Arrays.toString(rangeAdditionII(n, updates))); // Brute-force approach
        System.out.println(Arrays.toString(rangeAdditionIIEfficient(n, updates))); // Optimized approach

        in.close();
    }

    /**
     * Approach: Brute-Force
     * 
     * Intuition:
     * - For each update operation, traverse all the indices from `startIndex` to `endIndex` and increment their values.
     * - This approach directly applies the increments for each range specified in the updates array.
     * 
     * Time Complexity:
     * - O(q * n), where `q` is the number of updates and `n` is the size of the array. For each update, we potentially 
     *   traverse a large portion of the array.
     * 
     * Space Complexity:
     * - O(n), for storing the output array.
     * 
     * @param n The size of the array.
     * @param updates A 2D array containing the range and increment operations.
     * @return The updated array after all operations.
     */
    public static int[] rangeAdditionII(int n, int[][] updates) {
        int[] out = new int[n]; // Initialize the output array to 0
        for (int i = 0; i < updates.length; i++) {
            for (int j = updates[i][0]; j <= updates[i][1]; j++) {
                out[j] += updates[i][2]; // Increment the range
            }
        }
        return out;
    }

    /**
     * Approach: Optimized Using Prefix Sum Technique
     * 
     * Intuition:
     * - Instead of incrementing all elements in the range `[startIndex, endIndex]` for each update directly,
     *   we mark the increment at `startIndex` and the decrement just after `endIndex`.
     * - After processing all updates, a single pass is made through the array to calculate the final values using a 
     *   prefix sum approach.
     * 
     * Time Complexity:
     * - O(n + q), where `n` is the size of the array and `q` is the number of updates.
     * - Each update operation takes O(1), and a single pass over the array takes O(n).
     * 
     * Space Complexity:
     * - O(n), for storing the output array.
     * 
     * @param n The size of the array.
     * @param updates A 2D array containing the range and increment operations.
     * @return The updated array after all operations.
     */
    public static int[] rangeAdditionIIEfficient(int n, int[][] updates) {
        int[] out = new int[n]; // Initialize the output array to 0
        for (int i = 0; i < updates.length; i++) {
            int startIndex = updates[i][0];
            int endIndex = updates[i][1];
            int inc = updates[i][2];

            // Apply the range increment
            out[startIndex] += inc;
            if (endIndex + 1 < n) {
                out[endIndex + 1] -= inc;
            }
        }

        // Perform prefix sum to compute final array values
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += out[i];
            out[i] = sum;
        }
        return out;
    }
}
