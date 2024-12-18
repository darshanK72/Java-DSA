/**
 * Problem Statement:
 * 
 *     A valid mountain array is an array where:
 *     - The array has at least three elements.
 *     - There exists an element such that all elements to the left of it are strictly increasing, and all elements to the right of it are strictly decreasing.
 * 
 *     Given an integer array `arr`, return `true` if it is a valid mountain array, otherwise return `false`.
 * 
 * Input:
 *     - An integer `n` (3 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - Return `true` if `arr` is a valid mountain array, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     5
 *     2 1 3 2 1
 *     
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The array forms a valid mountain with elements increasing from 2 to 3 and then decreasing back to 1.
 */

import java.util.Scanner;

public class j35ValidMountainArray {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution method
        System.out.println(validMountainArray(arr));

        in.close();
    }

    /**
     * Approach: Linear Scan (O(n))
     * 
     * Intuition:
     * - To check if the array forms a valid mountain, we start by traversing the array to find the peak. 
     * - The peak is identified when we find the first decreasing element after an increasing sequence.
     * - Once the peak is found, we ensure that all subsequent elements are strictly decreasing.
     * - Additionally, we ensure that the array has a peak (i.e., the array is not strictly increasing or decreasing).
     * 
     * Time Complexity:
     * - O(n), as we scan the array once.
     * 
     * Space Complexity:
     * - O(1), since we use only a few variables for indexing.
     * 
     * @param arr The input array of integers.
     * @return `true` if the array is a valid mountain array, otherwise `false`.
     */
    public static boolean validMountainArray(int[] arr) {
        int i = 0;

        // Ascend the array to find the peak
        while (i + 1 < arr.length && arr[i] < arr[i + 1]) {
            i++;
        }

        // Ensure that the peak is not at the beginning or the end
        if (i == 0 || i == arr.length - 1)
            return false;

        // Descend the array after the peak
        while (i + 1 < arr.length && arr[i] > arr[i + 1]) {
            i++;
        }

        // Check if we have traversed the entire array
        return (i == arr.length - 1);
    }
}
