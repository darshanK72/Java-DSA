/*-
 * GeeksForGeeks: Merge K Sorted Arrays
 * 
 * Problem Statement:
 *     Given K sorted arrays arranged in the form of a matrix of size K*K.
 *     The task is to merge them into one sorted array.
 * 
 * Input:
 *     - arr[][]: K x K matrix containing K sorted arrays
 *     - k: Number of sorted arrays (size of matrix)
 * 
 * Output:
 *     - ArrayList<Integer>: Merged sorted array containing all elements
 * 
 * Example:
 *     Input: K = 3
 *            arr[][] = {{1, 3, 5, 7},
 *                      {2, 4, 6, 8},
 *                      {0, 9, 10, 11}}
 *     Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
 * 
 *     Explanation:
 *     The output array is a sorted array containing all elements from
 *     the three input arrays.
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class j13MergeKSortedArrays {

    /**
     * Helper Class: Result
     * 
     * Stores element information including:
     * - num: The actual number from the array
     * - row: Row index in the matrix (array number)
     * - col: Column index in the matrix (position in array)
     * 
     * Implements Comparable to enable sorting in priority queue
     */
    static class Result implements Comparable<Result> {
        int num;
        int row;
        int col;

        Result(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Result other) {
            return this.num - other.num;
        }
    }

    /**
     * Approach: Min Heap with Array Tracking
     * 
     * Intuition:
     * - Use min heap to maintain smallest element from each array
     * - Track array indices to add next element from same array
     * - Extract minimum element and add next element from its array
     * 
     * Explanation:
     * 1. Initialize min heap with first element from each array
     * 2. While heap is not empty:
     *    - Extract minimum element
     *    - Add it to result
     *    - If more elements in same array, add next element to heap
     * 3. Return merged sorted array
     * 
     * Time Complexity: O(n log k) where n is total number of elements
     *                  - O(log k) for each heap operation
     *                  - O(n) elements to process
     * Space Complexity: O(k) for storing k elements in heap
     * 
     * @param arr    K x K matrix containing K sorted arrays
     * @param k      Number of sorted arrays
     * @return       ArrayList containing merged sorted array
     */
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int k) {
        // Initialize min heap with first element from each array
        PriorityQueue<Result> pq = new PriorityQueue<>();

        // Add first element from each array to heap
        for (int i = 0; i < k; i++) {
            pq.add(new Result(arr[i][0], i, 0));
        }

        // Initialize result list
        ArrayList<Integer> out = new ArrayList<>();

        // Process elements until heap is empty
        while (!pq.isEmpty()) {
            // Extract minimum element
            Result res = pq.remove();
            out.add(res.num);
            
            // Add next element from same array if available
            if (res.col + 1 < k) {
                pq.add(new Result(arr[res.row][res.col + 1], res.row, res.col + 1));
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] arr1 = {
            {1, 3, 5, 7},
            {2, 4, 6, 8},
            {0, 9, 10, 11}
        };
        System.out.println("Input: K = 3, arr = " + java.util.Arrays.deepToString(arr1));
        System.out.println("Output: " + mergeKArrays(arr1, 3));

        // Test Case 2: Single array
        System.out.println("\nSingle Array Test:");
        int[][] arr2 = {{1, 2, 3, 4}};
        System.out.println("Input: K = 1, arr = " + java.util.Arrays.deepToString(arr2));
        System.out.println("Output: " + mergeKArrays(arr2, 1));

        // Test Case 3: Arrays of different lengths
        System.out.println("\nDifferent Length Arrays Test:");
        int[][] arr3 = {
            {1, 2},
            {3, 4, 5},
            {6}
        };
        System.out.println("Input: K = 3, arr = " + java.util.Arrays.deepToString(arr3));
        System.out.println("Output: " + mergeKArrays(arr3, 3));

        // Test Case 4: Negative numbers
        System.out.println("\nNegative Numbers Test:");
        int[][] arr4 = {
            {-3, -1, 1, 3},
            {-4, -2, 0, 2},
            {-5, -3, -1, 1}
        };
        System.out.println("Input: K = 3, arr = " + java.util.Arrays.deepToString(arr4));
        System.out.println("Output: " + mergeKArrays(arr4, 3));
    }
}
