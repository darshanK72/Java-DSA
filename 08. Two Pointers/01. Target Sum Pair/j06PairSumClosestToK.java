/**
 * Problem Statement:
 * 
 *     Given an array of integers and an integer `k`, find a pair of elements in the array such that their sum is closest to `k`.
 *     Return the pair of elements that produce this sum.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the target sum.
 * 
 * Output:
 *     - A pair of integers from the array whose sum is closest to `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     6
 *     Output:
 *     [1, 5]
 * 
 *     Explanation:
 *     The pair with the sum closest to 6 is [1, 5] with a sum of 6.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j06PairSumClosestToK {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target sum
        System.out.println(Arrays.toString(sumClosest(arr, k))); // Call the solution method

        in.close();
    }

    /**
     * Approach: Two Pointer Technique
     * 
     * Intuition:
     * - The goal is to find a pair of elements in the array whose sum is closest to the given target `k`.
     * - We can efficiently find this pair by first sorting the array and then using the two-pointer technique.
     * - Start by setting one pointer at the beginning of the array (`s`) and the other at the end (`e`).
     * - We calculate the sum of the elements at these pointers, and compare it to the target `k`.
     * - If the sum is closer to `k` than any previous pair, we update the result.
     * - If the sum is greater than `k`, we move the right pointer inward to reduce the sum.
     * - If the sum is smaller than `k`, we move the left pointer outward to increase the sum.
     * 
     * Time Complexity:
     * - O(n log n) for sorting the array.
     * - O(n) for iterating through the array with two pointers.
     * - Overall time complexity is O(n log n).
     * 
     * Space Complexity:
     * - O(1) for space since we are only storing the two integers that give the closest sum.
     * 
     * @param arr The input array of integers.
     * @param k The target sum to be approached.
     * @return A pair of integers whose sum is closest to `k`.
     */
    public static int[] sumClosest(int[] arr, int k) {
        int s = 0; // Start pointer
        int e = arr.length - 1; // End pointer
        int minDiff = Integer.MAX_VALUE; // Initialize minimum difference to a large value
        int[] out = new int[2]; // Array to store the result

        // Iterate with two pointers
        while (s < e) {
            int sum = arr[s] + arr[e];
            int d = Math.abs(sum - k); // Calculate the absolute difference from the target sum

            // If the difference is smaller than any previous difference, update the result
            if (d < minDiff) {
                out[0] = arr[s];
                out[1] = arr[e];
                minDiff = d;
            }

            // Adjust pointers based on the sum comparison with k
            if (sum > k) {
                e--; // Reduce the sum by moving the end pointer left
            } else {
                s++; // Increase the sum by moving the start pointer right
            }
        }

        return out; // Return the pair whose sum is closest to k
    }
}
