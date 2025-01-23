/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `a` and `b`, find the k-th smallest element in their union.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^5), representing the size of the first array.
 *     - An integer `n2` (1 <= n2 <= 10^5), representing the size of the second array.
 *     - An integer `k` (1 <= k <= n1 + n2), representing the k-th position to find in the combined arrays.
 *     - Two sorted arrays `a` of size `n1` and `b` of size `n2`, where each element satisfies (1 <= a[i], b[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the k-th smallest element in the union of `a` and `b`.
 * 
 * Example:
 *     Input:
 *         n1 = 5
 *         n2 = 4
 *         a = [2, 3, 6, 7, 9]
 *         b = [1, 4, 8, 10]
 *         k = 5
 *     Output:
 *         6
 * 
 *     Explanation:
 *         The merged array is [1, 2, 3, 4, 6, 7, 8, 9, 10]. The 5th smallest element is 6.
 */

import java.util.Scanner;

public class j02KthElementInTwoSortedArrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read sizes of arrays
        int n1 = in.nextInt();
        int n2 = in.nextInt();

        // Initialize arrays
        int[] a = new int[n1];
        int[] b = new int[n2];

        // Populate the first array
        for (int i = 0; i < n1; i++) {
            a[i] = in.nextInt();
        }

        // Populate the second array
        for (int i = 0; i < n2; i++) {
            b[i] = in.nextInt();
        }

        // Read the k-th position to find
        int k = in.nextInt();

        // Find and print the k-th smallest element using both approaches
        System.out.println(kthElementTwoPointers(a, b, k)); // Two Pointers Approach
        System.out.println(kthElementBinarySearch(a, b, k)); // Binary Search Approach

        in.close(); // Close scanner
    }

    /**
     * Approach 1: Two Pointers
     * 
     * Intuition:
     * - We traverse both sorted arrays simultaneously using two pointers, selecting the smaller element at each step.
     * - By maintaining a counter for how many elements we have processed, we stop when we reach the k-th element.
     * 
     * Steps:
     * - Use two pointers `i` and `j` to traverse the arrays `a` and `b`.
     * - At each step, move the pointer corresponding to the smaller element forward and decrease `k`.
     * - When `k` becomes 0, the last selected element is the k-th smallest.
     * 
     * Time Complexity:
     * - O(k), as we process `k` elements to find the result.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used apart from a few variables.
     * 
     * @param a First sorted array.
     * @param b Second sorted array.
     * @param k The k-th position to find.
     * @return The k-th smallest element in the union of `a` and `b`.
     */
    public static int kthElementTwoPointers(int a[], int b[], int k) {
        int m = a.length; // Size of first array
        int n = b.length; // Size of second array
        int i = 0; // Pointer for array `a`
        int j = 0; // Pointer for array `b`
        int ans = 0; // Variable to store the result

        // Traverse until we process k elements
        while (i < m || j < n) {
            // Select the smaller element between a[i] and b[j]
            if (i != m && j != n) {
                ans = (a[i] > b[j]) ? b[j++] : a[i++];
            } else if (i < m) { // If only array `a` has remaining elements
                ans = a[i++];
            } else { // If only array `b` has remaining elements
                ans = b[j++];
            }
            k--; // Decrement k as we process one element
            if (k == 0) {
                return ans; // Return the k-th smallest element
            }
        }
        return -1; // Edge case: if k is invalid (not possible in constraints)
    }

    /**
     * Approach 2: Binary Search
     * 
     * Intuition:
     * - Use binary search to partition the smaller of the two arrays.
     * - For a valid partition, ensure the maximum of the left partitions is less than or equal to the minimum of the right partitions.
     * - The k-th smallest element lies at the boundary of the left and right partitions.
     * 
     * Steps:
     * - Ensure `nums1` is the smaller array for efficient binary search.
     * - Perform binary search on `nums1` to find the partition point.
     * - At each step, compare the left and right elements around the partition to validate.
     * - Stop when a valid partition is found, and return the k-th smallest element.
     * 
     * Time Complexity:
     * - O(log(min(m, n))), where `m` and `n` are the sizes of the arrays.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used apart from a few variables.
     * 
     * @param nums1 First sorted array.
     * @param nums2 Second sorted array.
     * @param k The k-th position to find.
     * @return The k-th smallest element in the union of `nums1` and `nums2`.
     */
    public static int kthElementBinarySearch(int nums1[], int nums2[], int k) {
        int m = nums1.length; // Size of the first array
        int n = nums2.length; // Size of the second array

        // Ensure nums1 is the smaller array
        if (m > n) {
            return kthElementBinarySearch(nums2, nums1, k);
        }

        int left = k; // Total elements in the left partition

        // Define binary search range
        int low = Math.max(0, k - n);
        int high = Math.min(k, m);

        // Perform binary search on nums1
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
                return Math.max(al, bl); // Return the k-th smallest element
            } else if (al > br) { // Move left in nums1
                high = mid1 - 1;
            } else { // Move right in nums1
                low = mid1 + 1;
            }
        }
        return -1; // Edge case: should not reach here based on constraints
    }
}
