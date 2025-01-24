/**
 * Problem Statement:
 * 
 *      Given a sorted array `arr` of positive integers, find the `k`th missing positive integer
 *      that is not present in the array.
 * 
 * Input:
 *     - An integer `n` representing the size of the array.
 *     - A sorted array `arr` of size `n`.
 *     - An integer `k` representing the position of the missing positive integer.
 * 
 * Output:
 *     - An integer representing the `k`th missing positive integer.
 * 
 * Example:
 *     Input:
 *         5
 *         2 3 4 7 11
 *         5
 * 
 *     Output:
 *         9
 * 
 *     Explanation:
 *     - The missing positive integers are [1, 5, 6, 8, 9, 10, ...].
 *     - The 5th missing number is 9.
 */

import java.util.Scanner;

public class j04KthMissignPositiveInteger {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Input: Read the size of the array
        int n = in.nextInt();

        // Input: Initialize and populate the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Input: Read the value of k
        int k = in.nextInt();

        // Output: Using method 1 (Iterative approach)
        System.out.println(findKthPositive1(arr, k));

        // Output: Using method 2 (Optimized incremental approach)
        System.out.println(findKthPositive2(arr, k));

        // Output: Using method 3 (Binary search approach)
        System.out.println(findKthPositiveBinarySearch(arr, k));

        in.close(); // Close the scanner
    }

    /**
     * Method 1: Iterative Approach
     * 
     * Intuition:
     * - Start from 1 and check if it is missing from the array.
     * - Decrement `k` for each missing number until `k` becomes 0.
     * - Return the last missing number when `k` reaches 0.
     * 
     * Steps:
     * 1. Use a pointer `i` to traverse the array.
     * 2. Increment `x` from 1 and compare it with the current array element.
     * 3. If `x` is missing, decrement `k`.
     * 4. Stop when `k` becomes 0 and return the last missing number.
     * 
     * Time Complexity:
     * - O(n + k): Traverse the array and check for missing numbers until `k` becomes 0.
     * 
     * Space Complexity:
     * - O(1): Constant space is used.
     * 
     * @param arr The sorted array of integers.
     * @param k The position of the missing number to find.
     * @return The `k`th missing positive integer.
     */
    public static int findKthPositive1(int[] arr, int k) {
        int x = 1; // Start from the first positive integer
        int i = 0; // Pointer to traverse the array

        // Loop until `k` becomes 0
        while (k > 0) {
            // Check if `x` is present in the array
            if (i < arr.length && arr[i] == x) {
                i++; // Move to the next element in the array
            } else {
                k--; // Decrement `k` for each missing number
            }
            x++; // Increment to the next positive integer
        }

        // Return the last missing number
        return x - 1;
    }

    /**
     * Method 2: Optimized Incremental Approach
     * 
     * Intuition:
     * - For each array element, if it is smaller than or equal to `k`, it shifts the missing
     *   numbers forward by 1.
     * - Increment `k` for each element ≤ `k` to account for the shift in missing numbers.
     * 
     * Steps:
     * 1. Traverse the array.
     * 2. For each element ≤ `k`, increment `k`.
     * 3. Stop when a larger element is found or the array ends.
     * 4. Return the final value of `k`.
     * 
     * Time Complexity:
     * - O(n): Traverse the array once.
     * 
     * Space Complexity:
     * - O(1): Constant space is used.
     * 
     * @param arr The sorted array of integers.
     * @param k The position of the missing number to find.
     * @return The `k`th missing positive integer.
     */
    public static int findKthPositive2(int[] arr, int k) {
        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            // Increment `k` if the current array element is ≤ `k`
            if (arr[i] <= k) {
                k++;
            } else {
                break; // Stop when the current element > `k`
            }
        }

        // Return the final value of `k`
        return k;
    }

    /**
     * Method 3: Binary Search Approach
     * 
     * Intuition:
     * - Each array element `arr[mid]` contributes to the number of missing numbers as:
     *   `missing = arr[mid] - (mid + 1)`.
     * - Perform binary search to find the smallest `mid` where the number of missing numbers
     *   is at least `k`.
     * 
     * Steps:
     * 1. Initialize the search range `s` (start) and `e` (end).
     * 2. Calculate the number of missing numbers at each mid-point.
     * 3. Adjust the search range based on the number of missing numbers.
     * 4. Return the `k`th missing number based on the final position.
     * 
     * Time Complexity:
     * - O(log(n)): Binary search on the array.
     * 
     * Space Complexity:
     * - O(1): Constant space is used.
     * 
     * @param arr The sorted array of integers.
     * @param k The position of the missing number to find.
     * @return The `k`th missing positive integer.
     */
    public static int findKthPositiveBinarySearch(int[] arr, int k) {
        int s = 0; // Start of the search range
        int e = arr.length - 1; // End of the search range

        // Perform binary search
        while (s <= e) {
            int mid = s + (e - s) / 2; // Calculate mid-point
            int missing = arr[mid] - (mid + 1); // Number of missing numbers at mid

            if (missing < k) {
                s = mid + 1; // Search in the right half
            } else {
                e = mid - 1; // Search in the left half
            }
        }

        // Calculate the `k`th missing number
        return k + e + 1;
    }
}
