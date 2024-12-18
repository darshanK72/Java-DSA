/**
 * Problem Statement:
 * 
 *     Given an array of integers and a positive integer `k`, rotate the array to the right by `k` positions. 
 *     A right rotation operation shifts each element of the array to its right by one position, and the last element moves 
 *     to the front.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the number of positions to rotate the array.
 * 
 * Output:
 *     - The array after performing `k` right rotations.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     2
 *     Output:
 *     [4, 5, 1, 2, 3]
 * 
 *     Explanation:
 *     - After 2 right rotations:
 *         - The first rotation shifts the array to [5, 1, 2, 3, 4].
 *         - The second rotation shifts it further to [4, 5, 1, 2, 3].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j19RightRotate {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        int k = in.nextInt(); // Input: number of positions to rotate

        // Print the original array
        System.out.println("Original Array: " + Arrays.toString(arr));

        // Call the most efficient solution
        rightRotate(arr, k);

        // Print the rotated array
        System.out.println("Rotated Array: " + Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach: Reversal Algorithm (Most Efficient)
     * 
     * Intuition:
     * - To perform a right rotation, we can use the same reversal logic as the left rotation, but with modified indices.
     * - Steps:
     *   1. Reverse the first `n-k` elements.
     *   2. Reverse the last `k` elements.
     *   3. Reverse the entire array.
     * - This rearrangement efficiently achieves the desired rotation in-place.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Each element is part of a reverse operation once.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param nums The input array of integers.
     * @param k    The number of positions to rotate the array to the right.
     */
    public static void rightRotate(int[] nums, int k) {
        k = k % nums.length; // Handle cases where k > n
        reverse(nums, 0, nums.length - k - 1); // Reverse the first n-k elements
        reverse(nums, nums.length - k, nums.length - 1); // Reverse the last k elements
        reverse(nums, 0, nums.length - 1); // Reverse the entire array
    }

    /**
     * Utility Method: Reverse a portion of the array
     * 
     * @param arr The input array.
     * @param s   The start index.
     * @param e   The end index.
     */
    public static void reverse(int[] arr, int s, int e) {
        while (s < e) {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
}
