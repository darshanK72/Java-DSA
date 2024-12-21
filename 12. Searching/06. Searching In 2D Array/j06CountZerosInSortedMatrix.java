/*-
 * Problem Statement:
 * 
 *     Given an n x n binary matrix where each row is sorted in non-decreasing order, write a function to 
 *     count the total number of zeros in the matrix.
 * 
 * Input:
 *     - An integer n (1 <= n <= 1000), representing the number of rows and columns in the matrix.
 *     - A binary matrix of size n x n where each row is sorted in non-decreasing order (contains only 0's and 1's).
 * 
 * Output:
 *     - Return the number of zeros in the matrix.
 * 
 * Example:
 *     Input:
 *     4
 *     0 0 0 1
 *     0 0 1 1
 *     0 1 1 1
 *     1 1 1 1
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The matrix has 6 zeros:
 *     0 0 0 1
 *     0 0 1 1
 *     0 1 1 1
 *     1 1 1 1
 */

import java.util.Scanner;

public class j06CountZerosInSortedMatrix {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the matrix
        int[][] arr = new int[n][n];

        // Reading the matrix input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        // Output the result of countZeros function
        System.out.println(countZerosEfficient(arr, n));
        in.close();
    }

    /*-
     * Approach:
     * 
     * Intuition:
     * - The matrix is binary and sorted row-wise. We can count the zeros by iterating through each row and 
     *   counting how many 1's are present using binary search. The number of zeros is the difference between 
     *   the total number of elements in the matrix and the number of 1's.
     * 
     * Time Complexity:
     * - O(n * log n), where n is the number of rows. For each row, we perform binary search (`O(log n)`), 
     *   and there are n rows.
     * 
     * Space Complexity:
     * - O(1): We use constant space to track the count of zeros.
     * 
     * @param A The input binary matrix.
     * @param N The size of the matrix.
     * @return The number of zeros in the matrix.
     */
    public static int countZeros(int A[][], int N) {
        int ans = 0;

        // Traverse each row to count the number of 1's
        for (int i = 0; i < A.length; i++) {
            int oneCount = countOnesInRow(A[i]);
            ans += oneCount;
        }

        // The total number of zeros in the matrix
        return N * N - ans;
    }

    /*-
     * Binary Search to count the number of 1's in a row.
     * 
     * The row is sorted, so we can use binary search to find the first occurrence of 1.
     * 
     * @param arr The input row.
     * @return The number of 1's in the row.
     */
    public static int countOnesInRow(int[] arr) {
        int s = 0; // Start of the row
        int e = arr.length - 1; // End of the row

        // Perform binary search to find the first 1 in the row
        while (s <= e) {
            int mid = s + (e - s) / 2;

            // If the middle element is 0, move to the right half
            if (arr[mid] == 0) {
                s = mid + 1;
            } else {
                // If the middle element is 1, move to the left half
                e = mid - 1;
            }
        }

        // If s == arr.length, it means there are no 1's in the row
        if (s == arr.length) {
            return 0;
        }

        // Return the number of 1's in the row (i.e., total elements - position of the
        // first 0)
        return arr.length - s;
    }

    /*-
     * Efficient method using the Staircase Algorithm to count the number of zeros.
     * 
     * We start from the top-right corner of the matrix and move left if the element is 1, 
     * or move down if the element is 0. For each 0 we encounter, we count all the 0's to the left of it 
     * in the current row.
     * 
     * @param A The input binary matrix.
     * @param N The size of the matrix.
     * @return The number of zeros in the matrix.
     */
    public static int countZerosEfficient(int A[][], int N) {
        int zeros = 0;
        int row = 0;
        int col = N - 1;

        // Start from the top-right corner
        while (row < N && col >= 0) {
            if (A[row][col] == 0) {
                // If a 0 is found, all elements to the left are also 0's
                zeros += col + 1;
                row++; // Move down to the next row
            } else {
                // Move left if the element is 1
                col--;
            }
        }

        return zeros;
    }
}
