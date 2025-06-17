/**
 * GeeksForGeeks. Minimize the Heights I
 * 
 * Problem Statement:
 *     Given an array arr[] denoting heights of N towers and a positive integer K, you have
 *     to modify the height of each tower either by increasing or decreasing them by K only
 *     once. After modifying, height should be a non-negative integer. Find out what could
 *     be the possible minimum difference of the height of shortest and longest towers after
 *     you have modified each tower.
 * 
 * Input:
 *     - arr[] (1 <= N <= 10^5, 1 <= K <= 10^5)
 *     - K (1 <= K <= 10^5)
 * 
 * Output:
 *     - Return the minimum possible difference between the heights of the shortest and
 *       longest towers after modification
 * 
 * Example:
 *     Input: arr[] = {1, 5, 8, 10}, K = 2
 *     Output: 5
 * 
 *     Explanation:
 *     The array can be modified as {3, 3, 6, 8}. The difference between the largest and
 *     the smallest is 8-3 = 5.
 *     We can't get a lower difference than 5 by modifying the heights.
 */

import java.util.Arrays;

public class j07MinimizeTheHeightsI {

    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - To minimize the difference, we need to make the heights as close as possible
     * - After sorting, we can try to increase smaller heights and decrease larger heights
     * - For each position, we try to make the current element larger and next element
     *   smaller to minimize the gap
     * 
     * Explanation:
     * - Step 1: Sort the array to get elements in ascending order
     * - Step 2: Initialize result with difference between first and last element
     * - Step 3: For each position i:
     *   * Try to increase arr[i] by K and decrease arr[i+1] by K
     *   * Calculate new max and min after these operations
     *   * Update result if we get a better difference
     * 
     * Time Complexity: O(n log n) where n is the length of array
     *                  - O(n log n) for sorting
     *                  - O(n) for the loop
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param arr    Array of tower heights
     * @param k      Maximum amount by which height can be increased or decreased
     * @return       Minimum possible difference between heights of shortest and longest towers
     */
    public static int getMinDiff(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        
        // Initialize the result with the difference between first and last element
        int ans = arr[n-1] - arr[0];
        
        // Try all possible combinations of increasing and decreasing
        for(int i = 0; i < n-1; i++) {
            // Try to increase current element and decrease next element
            int max = Math.max(arr[i] + k, arr[n-1] - k);
            int min = Math.min(arr[i+1] - k, arr[0] + k);
            
            // Update answer if we found a better solution
            ans = Math.min(ans, max - min);
        }
        
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {1, 5, 8, 10};
        int k1 = 2;
        System.out.println("Input: arr = [1,5,8,10], k = 2");
        System.out.println("Expected: 5, Output: " + getMinDiff(arr1, k1));

        // Test Case 2: All elements same
        System.out.println("\nAll Elements Same Case:");
        int[] arr2 = {3, 3, 3, 3};
        int k2 = 1;
        System.out.println("Input: arr = [3,3,3,3], k = 2");
        System.out.println("Expected: 0, Output: " + getMinDiff(arr2, k2));

        // Test Case 3: Large difference
        System.out.println("\nLarge Difference Case:");
        int[] arr3 = {1, 15, 10};
        int k3 = 6;
        System.out.println("Input: arr = [1,15,10], k = 6");
        System.out.println("Expected: 5, Output: " + getMinDiff(arr3, k3));

        // Test Case 4: Small array
        System.out.println("\nSmall Array Case:");
        int[] arr4 = {2, 6};
        int k4 = 3;
        System.out.println("Input: arr = [2,6], k = 3");
        System.out.println("Expected: 2, Output: " + getMinDiff(arr4, k4));
    }
}
