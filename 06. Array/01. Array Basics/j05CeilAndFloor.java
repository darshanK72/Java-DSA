/**
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, and an integer `k`, find the ceiling and floor of `k` in the array.
 *     The ceiling of `k` is the smallest element in the array that is greater than or equal to `k`.
 *     The floor of `k` is the largest element in the array that is less than or equal to `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5).
 * 
 * Output:
 *     - An array of two integers where:
 *         - The first integer is the ceiling of `k`.
 *         - The second integer is the floor of `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     3 1 4 5 2
 *     4
 *     Output:
 *     [4, 3]
 *     Explanation:
 *     - The ceiling of 4 is 4, as it is the smallest element greater than or equal to 4.
 *     - The floor of 4 is 3, as it is the largest element less than or equal to 4.
 * 
 */

import java.util.Arrays;
import java.util.Scanner;

public class j05CeilAndFloor {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the value of k

        // Call the solution method
        System.out.println(Arrays.toString(ceilAndFloor(arr, k))); // Find the ceiling and floor of k

        in.close();
    }

    /**
     * Approach 1: Iterative Search for Ceil and Floor
     * 
     * Intuition:
     * - Traverse the array and check for elements greater than or equal to `k` for the ceiling.
     * - Similarly, check for elements less than or equal to `k` for the floor.
     * - Keep track of the smallest element greater than or equal to `k` (ceil) and the largest element less than or equal to `k` (floor).
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We scan the array once.
     * 
     * Space Complexity:
     * - O(1), since only a constant amount of space is used for storing the `ceil` and `floor` values.
     * 
     * @param arr The input array of numbers.
     * @param k The value for which we need to find the ceiling and floor.
     * @return An array containing the ceiling and floor of `k`.
     */
    public static int[] ceilAndFloor(int[] arr, int k) {
        int ceil = Integer.MIN_VALUE;
        int floor = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= k && arr[i] <= floor) {
                floor = arr[i]; // Update floor if arr[i] is greater than or equal to k and less than current
                                // floor
            }
            if (arr[i] <= k && arr[i] >= ceil) {
                ceil = arr[i]; // Update ceil if arr[i] is less than or equal to k and greater than current
                               // ceil
            }
        }
        return new int[] { ceil, floor }; // Return an array with [ceil, floor]
    }
}
