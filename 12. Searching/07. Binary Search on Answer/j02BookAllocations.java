
/**
 * Problem Statement:
 * 
 *     Given an array `arr[]` where each element represents the number of pages in a book, and an integer `k` representing the number of students.
 *     Allocate books to each student such that:
 *     - Each student gets at least one book.
 *     - Each student gets a contiguous sequence of books.
 *     - No book is assigned to more than one student.
 *     Minimize the maximum number of pages assigned to any student.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6), representing the number of books.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^3).
 *     - An integer `k` (1 <= k <= 10^3), representing the number of students.
 * 
 * Output:
 *     - An integer representing the minimized maximum number of pages assigned to any student.
 * 
 * Example:
 *     Input:
 *     4
 *     12 34 67 90
 *     2
 *     Output:
 *     113
 * 
 *     Explanation:
 *     Allocation can be done in following ways:
 *     [12] and [34, 67, 90] Maximum Pages = 191
 *     [12, 34] and [67, 90] Maximum Pages = 157
 *     [12, 34, 67] and [90] Maximum Pages = 113.
 *     Therefore, the minimum of these cases is 113, which is selected as the output.
 */

import java.util.Scanner;

public class j02BookAllocations {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of books
        int[] arr = new int[n];

        // Reading the number of pages in each book
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt(); // Number of students

        // Output the result of the approach
        System.out.println(findPages(arr, k));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. We need to
     * find the minimum
     * maximum number of pages assigned to any student.
     * - We start with the minimum possible pages as the maximum number of pages in
     * any book and the
     * maximum possible pages as the sum of all pages.
     * - We perform binary search to find the optimal allocation.
     * 
     * Time Complexity:
     * - O(n log(sum(arr))).
     * 
     * Space Complexity:
     * - O(1).
     * 
     * @param arr The input array of books.
     * @param k   The number of students.
     * @return The minimized maximum number of pages assigned to any student.
     */
    public static int findPages(int[] arr, int k) {
        if (k > arr.length)
            return -1;
        long left = 0;
        long right = 0;
        for (int i = 0; i < arr.length; i++) {
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (isPossible(arr, mid, k)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    /**
     * Helper method to check if books can be allocated to students such that the
     * maximum pages
     * assigned to any student is less than or equal to `x`.
     * 
     * @param arr The input array of books.
     * @param k   The number of students.
     * @param x   The maximum pages to check.
     * @return True if allocation is possible, otherwise false.
     */
    public static boolean isPossible(int[] books, long maxLoad, int students) {
        int currentLoad = 0;
        int reqStudents = 1;
        for (int book : books) {
            if (book > maxLoad)
                return false;
            if ((currentLoad + book) <= maxLoad) {
                currentLoad += book;
            } else {
                reqStudents++;
                currentLoad = book;
            }
        }

        return (reqStudents <= students);
    }
}
