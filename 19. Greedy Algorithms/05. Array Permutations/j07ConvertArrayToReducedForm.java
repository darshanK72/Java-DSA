/**
 * GeeksForGeeks: Convert an array to reduced form
 * 
 * Problem Statement:
 *     Given an array with n distinct elements, convert the array to a form where all elements are
 *     in range from 0 to n-1. The order of elements is same, i.e., 0 is placed in place of smallest
 *     element, 1 is placed for second smallest element, ... n-1 is placed for largest element.
 * 
 * Input:
 *     - arr (int[]): Array of n distinct elements
 *     - n (int): Size of the array
 * 
 * Output:
 *     - void: The array is modified in-place to reduced form
 * 
 * Example:
 *     Input: arr[] = {10, 40, 20}
 *     Output: arr[] = {0, 2, 1}
 * 
 *     Explanation:
 *     - 10 is the smallest element, so it becomes 0
 *     - 20 is the second smallest element, so it becomes 1
 *     - 40 is the largest element, so it becomes 2
 */

import java.util.Arrays;

public class j07ConvertArrayToReducedForm {

    /**
     * Approach: Custom Sorting with Indices
     * 
     * Intuition:
     * - We need to preserve the original array while sorting
     * - Create an array of indices and sort them based on original array values
     * - Use sorted indices to place ranks in original array
     * - This maintains relative ordering while converting to reduced form
     * 
     * Explanation:
     * - Step 1: Create array of indices [0,1,2,...,n-1]
     * - Step 2: Sort indices based on corresponding values in original array
     * - Step 3: Place ranks (0 to n-1) in original array using sorted indices
     * 
     * Time Complexity: O(n log n) where n is size of array
     *                  - Due to sorting of indices array
     * Space Complexity: O(n) for storing indices array
     * 
     * @param arr    Array to be converted to reduced form
     * @param n      Size of the array
     */
    public static void convert(int[] arr, int n) {
        // Create array of indices
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++)
            indices[i] = i;

        // Sort indices based on corresponding values in original array
        Arrays.sort(indices, (a, b) -> arr[a] - arr[b]);

        // Place ranks in original array using sorted indices
        for (int i = 0; i < n; i++) {
            arr[indices[i]] = i;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {10, 40, 20};
        System.out.println("Input: " + Arrays.toString(arr1));
        convert(arr1, arr1.length);
        System.out.println("Expected: [0, 2, 1], Output: " + Arrays.toString(arr1));

        // Test Case 2: Already sorted array
        System.out.println("\nSpecial Case - Sorted Array:");
        int[] arr2 = {5, 10, 15, 20};
        System.out.println("Input: " + Arrays.toString(arr2));
        convert(arr2, arr2.length);
        System.out.println("Expected: [0, 1, 2, 3], Output: " + Arrays.toString(arr2));

        // Test Case 3: Reverse sorted array
        System.out.println("\nSpecial Case - Reverse Sorted Array:");
        int[] arr3 = {20, 15, 10, 5};
        System.out.println("Input: " + Arrays.toString(arr3));
        convert(arr3, arr3.length);
        System.out.println("Expected: [3, 2, 1, 0], Output: " + Arrays.toString(arr3));

        // Test Case 4: Single element
        System.out.println("\nEdge Case - Single Element:");
        int[] arr4 = {100};
        System.out.println("Input: " + Arrays.toString(arr4));
        convert(arr4, arr4.length);
        System.out.println("Expected: [0], Output: " + Arrays.toString(arr4));

        // Test Case 5: Negative numbers
        System.out.println("\nSpecial Case - Negative Numbers:");
        int[] arr5 = {-10, -30, -20};
        System.out.println("Input: " + Arrays.toString(arr5));
        convert(arr5, arr5.length);
        System.out.println("Expected: [1, 0, 2], Output: " + Arrays.toString(arr5));
    }
}
