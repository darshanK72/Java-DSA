/**
 * GeeksForGeeks: Maximum Value Permutation
 * 
 * Problem Statement:
 *     Given an array arr[] of N integers, find the maximum value of
 *     Σ(arr[i] * i) for all permutations of the array. The result should be
 *     returned modulo 10^9 + 7.
 * 
 * Input:
 *     - arr[] (int[]): Array of integers
 *     - 1 <= N <= 10^5
 *     - 1 <= arr[i] <= 10^5
 * 
 * Output:
 *     - Maximum value of Σ(arr[i] * i) for any permutation of the array
 *     - Result modulo 10^9 + 7
 * 
 * Example:
 *     Input: arr[] = {3, 5, 6, 1}
 *     Output: 31
 * 
 *     Explanation:
 *     The maximum value is obtained by the permutation [1, 3, 5, 6]:
 *     (1 * 0) + (3 * 1) + (5 * 2) + (6 * 3) = 0 + 3 + 10 + 18 = 31
 */

import java.util.Arrays;

public class j01MaxValuePermutation {
    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - To maximize Σ(arr[i] * i), we should multiply larger numbers with
     *   larger indices
     * - This is because the product of a larger number with a larger index
     *   contributes more to the sum
     * - We can sort the array in ascending order and multiply each element
     *   with its index
     * 
     * Explanation:
     * 1. Sort the array in ascending order
     * 2. For each element, multiply it with its index
     * 3. Sum up all products
     * 4. Take modulo 10^9 + 7 at each step to prevent overflow
     * 
     * Time Complexity: O(n log n) where n is the length of arr
     *                  Due to sorting operation
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param arr Array of integers
     * @return   Maximum value of Σ(arr[i] * i) modulo 10^9 + 7
     */
    public static int maxValue(int arr[]) {
        // Sort array to multiply larger numbers with larger indices
        Arrays.sort(arr);
        
        // Calculate sum of products
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // Multiply each element with its index and take modulo
            ans += ((long)arr[i] * i % 1000000007);
        }
        
        // Return final result modulo 10^9 + 7
        return (int)(ans % 1000000007);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {3, 5, 6, 1};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Expected: 31, Output: " + maxValue(arr1));

        // Test Case 2: All same numbers
        System.out.println("\nAll Same Numbers Test Case:");
        int[] arr2 = {2, 2, 2, 2};
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Expected: 12, Output: " + maxValue(arr2));

        // Test Case 3: Single element
        System.out.println("\nSingle Element Test Case:");
        int[] arr3 = {5};
        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("Expected: 0, Output: " + maxValue(arr3));

        // Test Case 4: Large numbers
        System.out.println("\nLarge Numbers Test Case:");
        int[] arr4 = {100000, 100000, 100000};
        System.out.println("Input: " + Arrays.toString(arr4));
        System.out.println("Expected: 300000, Output: " + maxValue(arr4));
    }
}
