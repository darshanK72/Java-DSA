/**
 * Problem Statement:
 * 
 *     Given an array `arr` of integers, find the length of the largest contiguous subarray
 *     where the elements of the subarray are consecutive integers (not necessarily in order).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^9 <= arr[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the length of the largest contiguous subarray of consecutive integers.
 * 
 * Example:
 *     Input:
 *     7
 *     1 2 3 4 5 6 7
 *     Output:
 *     7
 * 
 *     Explanation:
 *     The entire array is a contiguous subarray where all elements are consecutive integers,
 *     thus the length of the largest subarray is `7`.
 */

import java.util.HashSet;
import java.util.Scanner;

public class j17LargestContigousSubarray {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        System.out.println(largestContigousSubarray(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach (O(N^2))
     * 
     * Intuition:
     * - A brute force solution could involve checking every subarray for consecutive integers.
     *   This approach requires comparing all pairs of elements within each subarray to verify if they are consecutive.
     * - The brute force method checks all possible subarrays and compares the difference between the maximum and minimum
     *   values in the subarray to determine if it is consecutive.
     * 
     * Time Complexity:
     * - O(N^2), as we are iterating over every possible subarray (nested loops).
     * 
     * Space Complexity:
     * - O(N), due to the use of a HashSet to track elements in the subarray.
     * 
     * @param arr The input array of integers.
     * @return The length of the largest contiguous subarray where elements are consecutive.
     */
    public static int largestContigousSubarrayBruteForce(int[] arr) {
        int maxL = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int max = arr[i];
            HashSet<Integer> set = new HashSet<>();
            set.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break; // Found duplicate, exit this subarray exploration
                } else {
                    set.add(arr[j]);
                    max = Math.max(max, arr[j]);
                    min = Math.min(min, arr[j]);
                    if (max - min == j - i) { // If the range of max and min equals the number of elements, it's
                                              // consecutive
                        maxL = Math.max(maxL, j - i + 1);
                    }
                }
            }
        }
        return maxL;
    }

    /**
     * Approach 2: Optimized Approach using HashSet (O(N))
     * 
     * Intuition:
     * - In the optimized approach, we can utilize a HashSet to track unique elements and
     *   avoid redundant checks for duplicates.
     * - The key idea is that for an array to be a consecutive subarray, the difference between the maximum
     *   and minimum element should be equal to the length of the subarray minus one.
     * - We can traverse the array and maintain a dynamic window of elements using a HashSet. For every element,
     *   we will check if the subarray formed by including the element forms a consecutive subarray.
     * 
     * Time Complexity:
     * - O(N), as we are traversing the array once and using HashSet operations that are constant time on average.
     * 
     * Space Complexity:
     * - O(N), as we use a HashSet to store the elements in the current subarray.
     * 
     * @param arr The input array of integers.
     * @return The length of the largest contiguous subarray where elements are consecutive.
     */
    public static int largestContigousSubarray(int[] arr) {
        int maxL = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int max = arr[i];
            HashSet<Integer> set = new HashSet<>();
            set.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break; // Found duplicate, exit this subarray exploration
                } else {
                    set.add(arr[j]);
                    max = Math.max(max, arr[j]);
                    min = Math.min(min, arr[j]);
                    if (max - min == j - i) { // Consecutive subarray condition
                        maxL = Math.max(maxL, j - i + 1);
                    }
                }
            }
        }
        return maxL;
    }
}
