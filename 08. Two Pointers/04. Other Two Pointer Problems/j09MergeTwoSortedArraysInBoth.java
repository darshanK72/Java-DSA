/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2`, merge them into two sorted arrays while maintaining the sorting order.
 *     The goal is to achieve this in two ways: using extra space for merging (in a new array), and efficiently merging in-place by swapping elements.
 * 
 * Input:
 *     - Two integers `m` and `n`, where `m` is the size of the first array `arr1` and `n` is the size of the second array `arr2`.
 *     - Two arrays `arr1` and `arr2` of sizes `m` and `n`, respectively.
 * 
 * Output:
 *     - Two sorted arrays: `arr1` and `arr2`, after merging them.
 * 
 * Example:
 *     Input:
 *     m = 3, n = 3
 *     arr1 = [1, 3, 5]
 *     arr2 = [2, 4, 6]
 *     
 *     Output:
 *     arr1 = [1, 2, 3]
 *     arr2 = [4, 5, 6]
 * 
 * Explanation:
 *     The elements of `arr2` are merged into `arr1` and both arrays are sorted.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j09MergeTwoSortedArraysInBoth {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        long[] arr1 = new long[m];
        long[] arr2 = new long[n];

        // Read elements for arr1
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextLong();
        }

        // Read elements for arr2
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextLong();
        }

        // Merge arrays and print the results
        mergeArrays(arr1, arr2, m, n);
        System.out.println("Merged arr1: " + Arrays.toString(arr1));
        System.out.println("Merged arr2: " + Arrays.toString(arr2));

        in.close();
    }

    /**
     * Approach 1: Merging with Extra Space
     * 
     * The approach merges the two sorted arrays into a new array `out`, and then splits the merged array back 
     * into `arr1` and `arr2` while maintaining the sorted order.
     * 
     * Time Complexity: O(m + n) where m and n are the sizes of the two arrays, because we iterate through both arrays once.
     * Space Complexity: O(m + n) for the temporary array `out`.
     */
    public static void mergeArrays(long arr1[], long arr2[], int n, int m) {
        long[] out = new long[n + m];
        int i = 0, j = 0, k = 0;

        // Merge arr1 and arr2 into the output array `out`
        while (i < n && j < m) {
            if (arr1[i] < arr2[j]) {
                out[k] = arr1[i];
                i++;
            } else {
                out[k] = arr2[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from arr1
        while (i < n) {
            out[k] = arr1[i];
            k++;
            i++;
        }

        // Copy remaining elements from arr2
        while (j < m) {
            out[k] = arr2[j];
            k++;
            j++;
        }

        // Split the merged array back into arr1 and arr2
        k = 0;
        j = 0;
        while (j < n) {
            arr1[j] = out[k];
            k++;
            j++;
        }

        j = 0;
        while (j < m) {
            arr2[j] = out[k];
            k++;
            j++;
        }
    }

    /**
     * Approach 2: Efficient In-place Merge
     * 
     * This approach performs the merge in place by comparing and swapping elements between `arr1` and `arr2`.
     * We only swap elements when necessary and after the swaps, both arrays are sorted.
     * 
     * Time Complexity: O(n log n + m log m), because we perform a partial swap and then sort both arrays.
     * Space Complexity: O(1) as no extra space is used other than the input arrays.
     */
    public static void mergeArraysEfficient(long arr1[], long arr2[], int n, int m) {
        int i = n - 1;
        int j = 0;

        // Swap elements between arr1 and arr2 where necessary
        while (i >= 0 && j < m) {
            if (arr1[i] > arr2[j]) {
                long temp = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = temp;
                i--;
                j++;
            } else {
                break;
            }
        }

        // Sort both arrays after the in-place swaps
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }
}
