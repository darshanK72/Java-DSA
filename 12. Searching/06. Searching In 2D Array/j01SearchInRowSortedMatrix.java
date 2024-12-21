/*-
 * Problem Statement:
 * 
 *     Given an `n x n` matrix `arr` where each row is sorted in increasing order and each column is also sorted in increasing order, 
 *     write a function that searches for a target value in the matrix.
 *     Return the indices of the target element if found, or `[-1, -1]` if it is not found.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number of rows and columns in the matrix.
 *     - A 2D array `arr` of size `n x n` where each row and each column is sorted in increasing order.
 *     - An integer `target` (1 <= target <= 10^6) representing the target element to search for in the matrix.
 * 
 * Output:
 *     - The indices `[i, j]` if the target is found, or `[-1, -1]` if the target is not found.
 * 
 * Example:
 *     Input:
 *     3
 *     1 4 7
 *     2 5 8
 *     3 6 9
 *     5
 *     Output:
 *     [1, 1]
 *     Explanation: The target 5 is located at the index [1, 1] in the matrix.
 * 
 *     Input:
 *     3
 *     1 4 7
 *     2 5 8
 *     3 6 9
 *     10
 *     Output:
 *     [-1, -1]
 *     Explanation: The target 10 is not present in the matrix.
 */

import java.util.Scanner;
import java.util.Arrays;

public class j01SearchInRowSortedMatrix {

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
        System.out.println(Arrays.toString(searchInRowSortedMatrix(arr, target)));
        in.close();
    }

    /*-
     * Approach 1: Search Target in Row-Sorted Matrix
     * 
     * Intuition:
     * - The matrix is sorted in both rows and columns. The target element may appear in any row but will always lie 
     *   between the first and last element of that row.
     * - To find the target, we can iterate through each row and perform a binary search on it.
     * - This reduces the complexity to O(n log n) since we are performing a binary search in each row.
     * 
     * Time Complexity:
     * - O(n log n), as we are performing binary search in each of the n rows.
     * 
     * Space Complexity:
     * - O(1), as we only use constant space for the search (ignoring input storage).
     * 
     * @param arr The input 2D matrix.
     * @param target The target element to search for.
     * @return The indices of the target element or [-1, -1] if not found.
     */
    public static int[] searchInRowSortedMatrix(int[][] arr, int target) {
        int n = arr.length;

        // Iterate over each row
        for (int i = 0; i < n; i++) {
            // If target is within the range of current row
            if (target >= arr[i][0] && target <= arr[i][n - 1]) {
                // Perform binary search in the row
                int j = binarySearch(arr[i], target);
                if (j != -1)
                    return new int[] { i, j }; // Return the indices if found
            }
        }

        return new int[] { -1, -1 }; // Return [-1, -1] if target is not found
    }

    /*-
     * Helper Method: Binary Search
     * 
     * Intuition:
     * - Perform binary search in a sorted array to find the target element.
     * - If the target is found, return its index; otherwise, return -1.
     * 
     * Time Complexity:
     * - O(log n), as binary search reduces the search space by half at each step.
     * 
     * Space Complexity:
     * - O(1), as we use constant space.
     * 
     * @param arr The input array to search within.
     * @param target The element to search for.
     * @return The index of the target element or -1 if not found.
     */
    public static int binarySearch(int[] arr, int target) {
        int s = 0;
        int e = arr.length - 1;

        // Perform binary search
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == target) {
                return mid; // Return the index if found
            } else if (arr[mid] > target) {
                e = mid - 1; // Search in the left half
            } else {
                s = mid + 1; // Search in the right half
            }
        }

        return -1; // Return -1 if target is not found
    }
}
