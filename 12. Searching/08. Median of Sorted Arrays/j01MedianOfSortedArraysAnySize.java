/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `nums1` and `nums2` of sizes `n1` and `n2`, 
 *     find the median of the two arrays. The overall time complexity 
 *     should be O(log(min(n1, n2))).
 * 
 * Input:
 *     - Two integers `n1` and `n2` (1 <= n1, n2 <= 10^4), representing the sizes of the arrays.
 *     - Two sorted arrays `nums1` and `nums2` of sizes `n1` and `n2`, respectively, where 
 *       each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - A double value representing the median of the two sorted arrays.
 * 
 * Example:
 *     Input:
 *         n1 = 3, n2 = 4
 *         nums1 = [1, 3, 8]
 *         nums2 = [7, 9, 10, 11]
 *     Output:
 *         8.0
 * 
 *     Explanation:
 *         Merging the arrays gives [1, 3, 7, 8, 9, 10, 11]. The median is 8.0.
 */

import java.util.Scanner;

public class j01MedianOfSortedArraysAnySize {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Size of the first array
        int n2 = in.nextInt(); // Size of the second array
        int[] nums1 = new int[n1];
        int[] nums2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            nums1[i] = in.nextInt(); // Input elements for the first array
        }

        for (int i = 0; i < n2; i++) {
            nums2[i] = in.nextInt(); // Input elements for the second array
        }

        // Call different approaches and print results
        System.out.printf("Approach 1 (Brute Force): %.5f\n", medianOf2BruteForce(nums1, nums2));
        System.out.printf("Approach 2 (Two Pointers): %.5f\n", medianOf2TwoPointers(nums1, nums2));
        System.out.printf("Approach 3 (Binary Search): %.5f\n", findMedianSortedArraysBinary(nums1, nums2));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - This approach combines the two arrays into a single sorted array and calculates the median.
     * - A median is defined as the middle element for odd-sized arrays and the average of the 
     *   two middle elements for even-sized arrays. 
     * - By merging the arrays, we directly access the middle element(s).
     * 
     * Explanation of Steps:
     * 1. Merge the two sorted arrays into a single sorted array.
     * 2. Calculate the median using the middle element(s).
     * 
     * Time Complexity:
     * - O(n1 + n2), where n1 and n2 are the sizes of the two arrays.
     * 
     * Space Complexity:
     * - O(n1 + n2), as we store the merged array explicitly.
     * 
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the two arrays.
     */
    public static double medianOf2BruteForce(int[] nums1, int[] nums2) {
        // Merge the arrays
        int[] merged = mergeTwoSortedArrays(nums1, nums2);
        int n = merged.length;

        // If the total size is even, return the average of the two middle elements
        if (n % 2 == 0) {
            return ((double) merged[n / 2 - 1] + (double) merged[n / 2]) / 2.0;
        }
        // Otherwise, return the middle element
        else {
            return merged[n / 2];
        }
    }

    /**
     * Utility Function: Merge Two Sorted Arrays
     * 
     * Intuition:
     * - This function merges two sorted arrays into one sorted array using the two-pointer technique.
     * - By comparing the current elements of both arrays, we add the smaller element to the result array.
     * - This ensures the merged array remains sorted.
     * 
     * Explanation of Steps:
     * 1. Use two pointers (i, j) to iterate over nums1 and nums2.
     * 2. Compare the elements at i and j, and add the smaller one to the merged array.
     * 3. If one array is exhausted, append the remaining elements of the other array.
     * 
     * Time Complexity:
     * - O(n1 + n2), where n1 and n2 are the sizes of the two arrays.
     * 
     * Space Complexity:
     * - O(n1 + n2), as the merged array is stored explicitly.
     * 
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The merged sorted array.
     */
    public static int[] mergeTwoSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;

        // Merge arrays by comparing elements
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // Add remaining elements from nums1 (if any)
        while (i < nums1.length) {
            merged[k++] = nums1[i++];
        }

        // Add remaining elements from nums2 (if any)
        while (j < nums2.length) {
            merged[k++] = nums2[j++];
        }

        return merged;
    }

    /**
     * Approach 2: Two Pointers
     * 
     * Intuition:
     * - Instead of explicitly merging the arrays, use two pointers to track the smallest elements 
     *   as we iterate through both arrays.
     * - Keep track of the two middle elements during the iteration.
     * 
     * Explanation of Steps:
     * 1. Use two pointers to traverse both arrays until the midpoint is reached.
     * 2. Track the last two elements processed to calculate the median.
     * 
     * Time Complexity:
     * - O(n1 + n2), where n1 and n2 are the sizes of the two arrays.
     * 
     * Space Complexity:
     * - O(1), as no additional storage is required.
     * 
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the two arrays.
     */
    public static double medianOf2TwoPointers(int a[], int b[]) {
        int m = a.length; // Length of array a
        int n = b.length; // Length of array b
        int i = 0, j = 0; // Pointers for a and b
        int m1 = 0, m2 = 0; // Variables to track the two middle elements

        // Traverse until the midpoint of the combined arrays
        for (int count = 0; count <= (m + n) / 2; count++) {
            m2 = m1; // Store the last middle element
            if (i != m && j != n) { // Both arrays have elements left
                m1 = (a[i] > b[j]) ? b[j++] : a[i++]; // Pick the smaller element
            } else if (i < m) { // Only array a has elements left
                m1 = a[i++];
            } else { // Only array b has elements left
                m1 = b[j++];
            }
        }

        // Return the average for even-sized combined array, otherwise return the middle
        if ((m + n) % 2 == 0) {
            return (m1 + m2) / 2.0;
        } else {
            return m1;
        }
    }

    /**
     * Approach 3: Binary Search (Optimized)
     * 
     * Intuition:
     * - The problem can be reduced to finding the correct partition of the combined array 
     *   such that the left part has the smaller half of elements and the right part has the larger half.
     * - Binary search on the smaller array helps in determining the correct partition efficiently.
     * 
     * Explanation of Steps:
     * 1. Perform binary search on the smaller array to find a partition.
     * 2. Ensure that the partition divides the combined array into two valid halves.
     * 3. Calculate the median based on the maximum of the left and minimum of the right halves.
     * 
     * Time Complexity:
     * - O(log(min(n1, n2))), as binary search is performed on the smaller array.
     * 
     * Space Complexity:
     * - O(1), as no additional storage is used.
     * 
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the two arrays.
     */
    public static double findMedianSortedArraysBinary(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // Ensure nums1 is the smaller array for binary search
        if (m > n) {
            return findMedianSortedArraysBinary(nums2, nums1);
        }

        int size = m + n; // Total size of the combined arrays
        int left = (m + n + 1) / 2; // Number of elements in the left partition

        int low = 0, high = m; // Binary search bounds
        while (low <= high) {
            int mid1 = (low + high) / 2; // Partition point for nums1
            int mid2 = left - mid1; // Partition point for nums2

            // Elements around the partitions
            int al = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int bl = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int ar = (mid1 < m) ? nums1[mid1] : Integer.MAX_VALUE;
            int br = (mid2 < n) ? nums2[mid2] : Integer.MAX_VALUE;

            // Check if the partition is valid
            if (al <= br && bl <= ar) {
                // Odd-sized array: return the max of the left partitions
                if (size % 2 == 1) {
                    return Math.max(al, bl);
                }
                // Even-sized array: return the average of max left and min right
                return ((double) Math.max(al, bl) + Math.min(ar, br)) / 2.0;
            } else if (al > br) {
                high = mid1 - 1; // Move left in nums1
            } else {
                low = mid1 + 1; // Move right in nums1
            }
        }
        return 0; // Return 0 if no solution is found (input arrays should always be sorted)
    }
}
