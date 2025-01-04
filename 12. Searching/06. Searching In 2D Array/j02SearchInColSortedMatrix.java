/**
 * Problem Statement:
 * 
 *     Given an `n x n` matrix `arr` where each column is sorted in increasing order, 
 *     write a function that searches for a target value in the matrix.
 *     Return the indices of the target element if found, or `[-1, -1]` if it is not found.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number of rows and columns in the matrix.
 *     - A 2D array `arr` of size `n x n` where each column is sorted in increasing order.
 *     - An integer `target` (1 <= target <= 10^6) representing the target element to search for in the matrix.
 * 
 * Output:
 *     - The indices `[i, j]` if the target is found, or `[-1, -1]` if the target is not found.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     5
 *     Output:
 *     [1, 1]
 *     Explanation: The target 5 is located at the index [1, 1] in the matrix.
 * 
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     10
 *     Output:
 *     [-1, -1]
 *     Explanation: The target 10 is not present in the matrix.
 */

import java.util.Scanner;
import java.util.Arrays;

public class j02SearchInColSortedMatrix {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the matrix (n x n)
        int[][] arr = new int[n][n];

        // Reading the matrix elements
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt(); // Input each element of the matrix
            }
        }

        int target = in.nextInt(); // Input the target element to search
        System.out.println(Arrays.toString(searchInColSortedMatrix(arr, target)));
        in.close();
    }

    /**
     * Approach: Search Target in Column-Sorted Matrix
     * 
     * Intuition:
     * - The matrix is sorted in columns. The target element can lie in any column.
     * - For each column, if the target lies between the first and last element of that column,
     *   we perform a binary search on the column to find the target.
     * - This reduces the time complexity to O(n log n), where `n` is the number of rows (and columns).
     * 
     * Time Complexity:
     * - O(n log n), as we perform binary search for each column, which takes O(log n) time.
     * 
     * Space Complexity:
     * - O(1), as we only use constant space for the search (ignoring input storage).
     * 
     * @param arr The input 2D matrix.
     * @param target The target element to search for.
     * @return The indices of the target element or [-1, -1] if not found.
     */
    public static int[] searchInColSortedMatrix(int[][] arr, int target) {
        int n = arr.length;

        // Iterate over each column
        for (int i = 0; i < n; i++) {
            // If target is within the range of current column
            if (target >= arr[0][i] && target <= arr[n - 1][i]) {
                // Perform binary search on the column
                int j = binarySearch(arr, i, target);
                if (j != -1)
                    return new int[] { j, i }; // Return the indices if found
            }
        }

        return new int[] { -1, -1 }; // Return [-1, -1] if target is not found
    }

    /**
     * Helper Method: Binary Search
     * 
     * Intuition:
     * - Perform binary search in a specific column to find the target element.
     * - If the target is found, return its index; otherwise, return -1.
     * 
     * Time Complexity:
     * - O(log n), as binary search reduces the search space by half at each step.
     * 
     * Space Complexity:
     * - O(1), as we use constant space.
     * 
     * @param arr The input array to search within.
     * @param col The column index to perform binary search in.
     * @param target The element to search for.
     * @return The index of the target element or -1 if not found.
     */
    public static int binarySearch(int[][] arr, int col, int target) {
        int s = 0;
        int e = arr.length - 1;

        // Perform binary search on the column
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid][col] == target) {
                return mid; // Return the index if found
            } else if (arr[mid][col] > target) {
                e = mid - 1; // Search in the upper half of the column
            } else {
                s = mid + 1; // Search in the lower half of the column
            }
        }

        return -1; // Return -1 if target is not found
    }
}
