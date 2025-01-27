/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2`, merge `arr2` into `arr1` in sorted order. 
 *     The first array has enough space to accommodate the elements of the second array. 
 *     The merge should be done in place in the first array.
 * 
 * Input:
 *     - Two integers `m` and `n`, where `m` is the size of the first array `arr1` (excluding the space reserved for elements of `arr2`), and `n` is the size of the second array `arr2`.
 *     - Two arrays `arr1` and `arr2` of sizes `m + n` and `n` respectively.
 * 
 * Output:
 *     - The merged array `arr1`, with the elements of `arr2` merged into it, maintaining the sorted order.
 * 
 * Example:
 *     Input:
 *     m = 3, n = 3
 *     arr1 = [1, 3, 5, 0, 0, 0]
 *     arr2 = [2, 4, 6]
 *     
 *     Output:
 *     arr1 = [1, 2, 3, 4, 5, 6]
 * 
 * Explanation:
 *     The elements of `arr2` are merged into `arr1`, and the array is sorted in ascending order.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j09MergeTwoSortedArraysInFirst {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[] arr1 = new int[m + n]; // The first array with extra space
        int[] arr2 = new int[n]; // The second array

        // Read elements of arr1 (excluding extra space)
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextInt();
        }

        // Read elements of arr2
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt();
        }

        // Merge the arrays efficiently
        mergeArraysEfficient(arr1, m, arr2, n);

        // Print the merged result in arr1
        System.out.println(Arrays.toString(arr1));
        in.close();
    }

    /**
     * Approach: Merge in Place
     * 
     * The idea is to use three pointers: one for each array (`arr1` and `arr2`), and one for the last position in `arr1`.
     * We start by comparing the elements of `arr1` and `arr2` from the back and place the larger element at the current position in `arr1`.
     * This ensures that we fill `arr1` starting from the last position and avoid overwriting elements that haven't been processed yet.
     * 
     * Time Complexity: O(m + n), where m and n are the sizes of the two arrays.
     * Space Complexity: O(1), since the merge is done in place.
     */
    public static void mergeArraysEfficient(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for the last element of the valid part of arr1
        int j = n - 1; // Pointer for the last element of arr2
        int k = nums1.length - 1; // Pointer for the last position in arr1

        // Merge the arrays from the back
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // If any elements are left in arr1, move them (though they are already in the
        // right place)
        while (i >= 0) {
            nums1[k] = nums1[i];
            i--;
            k--;
        }

        // If any elements are left in arr2, move them to arr1
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
