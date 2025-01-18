/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to find the number of triplets (i, j, k) such that:
 *     1 <= i < j < k <= n, and the XOR of all elements between indices i to k (inclusive) is 0.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the count of valid triplets (i, j, k) such that XOR of elements from i to k is 0.
 * 
 * Example:
 *     Input:
 *     4
 *     1 1 0 1
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The valid triplets are:
 *     - (i=0, j=1, k=2)
 *     - (i=1, j=2, k=3)
 *     - (i=0, j=2, k=3)
 */

import java.util.Scanner;

public class j04FindXorTriplets {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        System.out.println(findXorTriplets(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - The brute force approach loops over every possible triplet of indices (i, j, k) 
     *   and checks if the XOR of all elements between i and k is 0.
     * - For each starting index i, we loop through every ending index j (where j >= i) and compute the XOR progressively.
     * - If the XOR equals zero at any point, the number of valid triplets is incremented.
     * 
     * Time Complexity:
     * - O(n^2), because for each index i, we are iterating through all subsequent elements.
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of extra space.
     * 
     * @param arr The input array of numbers.
     * @return The count of triplets where XOR of elements between i and k is 0.
     */
    public static int findXorTriplets(int[] arr) {
        int count = 0; // Initialize count of valid triplets
        for (int i = 0; i < arr.length; i++) {
            int xor = 0; // Initialize XOR for each starting index
            for (int j = i; j < arr.length; j++) {
                xor ^= arr[j]; // Update XOR for current subarray
                if (xor == 0) {
                    count += (j - i); // Increment count for valid triplet
                }
            }
        }
        return count;
    }
}
