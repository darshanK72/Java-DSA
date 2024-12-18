/**
 * Problem Statement:
 *     Given a sorted integer array `arr` of size `n`, remove the duplicates in-place such that each unique
 *     element appears only once. The relative order of the elements should remain the same.
 *     Return the new length `k` of the modified array.
 *
 * Input:
 *     - An integer array `arr` where (1 <= arr.length <= 10^4) and (-10^5 <= arr[i] <= 10^5).
 *
 * Output:
 *     - An integer `k`, representing the length of the array after removing duplicates.
 *     - The first `k` elements of the array will contain the unique elements in sorted order.
 *
 * Example:
 *     Input:
 *         arr = [1, 1, 2]
 *     Output:
 *         k = 2
 *         Modified array = [1, 2]
 *
 *     Explanation:
 *         The array contains duplicates of `1`. After removing duplicates, only `1` and `2` remain.
 *
 *     Input:
 *         arr = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
 *     Output:
 *         k = 5
 *         Modified array = [0, 1, 2, 3, 4]
 *
 *     Explanation:
 *         After removing duplicates, the first 5 elements are `0, 1, 2, 3, 4`.
 */

import java.util.*;

public class j13RemoveDuplicateInSorted {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        System.out.println("Original Array: " + Arrays.toString(arr));
        int k = removeDuplicates(arr);
        System.out.println("Length after removing duplicates: " + k);
        System.out.println("Modified Array: " + Arrays.toString(Arrays.copyOfRange(arr, 0, k)));
        in.close();
    }

    /**
     * Approach:
     *     This approach uses a two-pointer technique to remove duplicates in a sorted array.
     *     - A pointer `k` tracks the position where the next unique element should be placed.
     *     - A for-loop iterates through the array, and whenever a new unique element is encountered
     *       (i.e., `arr[i] != arr[k-1]`), it is moved to the position at `arr[k]`.
     *     - Finally, `k` represents the length of the modified array.
     *
     * Intuition:
     *     Since the array is sorted, duplicates will always appear consecutively. By maintaining a pointer
     *     to track the last unique element's position, we can efficiently overwrite duplicates.
     *
     * Time Complexity:
     *     O(n), where `n` is the size of the array. We traverse the array once.
     *
     * Space Complexity:
     *     O(1), as we use only a constant amount of extra space.
     *
     * @param arr The input sorted array.
     * @return The length of the array after removing duplicates.
     */
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return 0; // Edge case: empty array
        }
        int k = 1; // Pointer to track the position of the next unique element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[k - 1]) { // Found a new unique element
                arr[k] = arr[i]; // Move it to the correct position
                k++;
            }
        }
        return k;
    }
}
