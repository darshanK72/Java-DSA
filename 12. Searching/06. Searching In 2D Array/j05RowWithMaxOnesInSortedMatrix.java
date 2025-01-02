/**
 * Problem Statement:
 * 
 *     You are given a binary matrix of size m x n where each row is sorted in non-decreasing order.
 *     Write a function that returns the index of the row that contains the maximum number of 1's. 
 *     If multiple rows have the maximum number of 1's, return the index of the first such row.
 * 
 * Input:
 *     - An integer n (1 <= n <= 1000), representing the number of rows and columns in the matrix.
 *     - A matrix of size n x n where each row is sorted in non-decreasing order.
 * 
 * Output:
 *     - Return the index of the row that contains the maximum number of 1's.
 * 
 * Example:
 *     Input:
 *     4
 *     0 0 0 1
 *     0 0 1 1
 *     0 1 1 1
 *     1 1 1 1
 *     Output:
 *     3
 * 
 *     Explanation:
 *     Row 3 has the maximum number of 1's (4 1's in this case).
 */

import java.util.Scanner;

public class j05RowWithMaxOnesInSortedMatrix {

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

        // Output the result of rowWithMax1s function
        System.out.println(rowWithMax1s(arr));
        in.close();
    }

    /**
     * Approach:
     * 
     * Intuition:
     * - The rows of the matrix are sorted, so we can use binary search to efficiently count the number of 1's in each row.
     * - For each row, we count how many 1's it contains by performing binary search to find the first 1 in the row.
     * - Then, we keep track of the row with the maximum number of 1's.
     * 
     * Time Complexity:
     * - O(n * log n), where n is the number of rows. For each row, we perform binary search which takes O(log n).
     * 
     * Space Complexity:
     * - O(1): We use constant space to track the maximum row index and count of 1's.
     * 
     * @param arr The input binary matrix.
     * @return The index of the row with the maximum number of 1's.
     */
    public static int rowWithMax1s(int arr[][]) {
        int max = 0; // To track the maximum number of 1's found
        int ans = -1; // To track the index of the row with the maximum number of 1's

        // Traverse each row in the matrix
        for (int i = 0; i < arr.length; i++) {
            // Count the number of 1's in the current row
            int oneCount = countOnesInRow(arr[i]);

            // If the current row has more 1's, update the result
            if (oneCount > max && oneCount != 0) {
                max = oneCount;
                ans = i;
            }
        }

        // Return the index of the row with the maximum number of 1's
        return ans;
    }

    /**
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
        return arr.length - e - 1;
    }
}
