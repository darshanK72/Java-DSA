/*-
 * Problem Statement:
 * 
 *      Given an integer array `nums`, return an integer array `counts` where
 *      `counts[i]` is the number of smaller elements to the right of `nums[i]`.
 * 
 * Input:
 *     - An integer array `nums` of size `n` where each element satisfies
 *       (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - An integer array `counts` where each `counts[i]` represents the number
 *       of elements smaller than `nums[i]` to its right.
 * 
 * Example:
 *     Input:
 *         nums = [5, 2, 6, 1]
 *     Output:
 *         [2, 1, 1, 0]
 * 
 *     Explanation:
 *         - To the right of 5, there are 2 smaller elements (2 and 1).
 *         - To the right of 2, there is only 1 smaller element (1).
 *         - To the right of 6, there is only 1 smaller element (1).
 *         - To the right of 1, there are no smaller elements.
 */

import java.util.ArrayList;
import java.util.List;

public class j05CountOfSmallerNumAfterItself {

    /*-
     * Approach: Merge Sort Based Solution
     * 
     * Intuition:
     * - The problem can be transformed into counting the number of inversions
     *   to the right of each element. An inversion is a pair (i, j) such that
     *   i < j and nums[i] > nums[j].
     * - By modifying the merge sort algorithm, we can count these inversions
     *   efficiently. During the merge step, when an element from the right
     *   half is placed before an element from the left half, it indicates
     *   that the elements remaining in the left half are greater than the
     *   current element from the right half.
     * 
     * Explanation:
     * - We first convert the input array into an array of pairs where each
     *   pair contains the element value and its original index.
     * - We perform a merge sort on this array of pairs, and while merging,
     *   we count the number of times an element from the left subarray is
     *   placed before an element from the right subarray.
     * - Whenever we pick an element from the right subarray before an element
     *   from the left subarray, we increment the count for that left subarray
     *   element, as all remaining elements in the right subarray are smaller.
     * - The final result is constructed from the counts array.
     * 
     * Time Complexity:
     * - O(n log n), where n is the length of the input array. This is due to
     *   the merge sort algorithm.
     * 
     * Space Complexity:
     * - O(n), for the temporary arrays used during the merge process.
     * 
     * @param nums The input array of numbers.
     * @return A list of integers representing the count of smaller numbers
     *         to the right of each element.
     */
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        mergeSort(pairs, 0, n - 1, out);

        List<Integer> result = new ArrayList<>();
        for (int count : out) {
            result.add(count);
        }
        return result;
    }

    /*-
     * Helper Method: mergeSort
     * 
     * Intuition:
     * - This function performs a modified merge sort that sorts the elements
     *   while keeping track of how many smaller elements appear after each.
     * 
     * Explanation:
     * - We recursively divide the array into left and right halves.
     * - After sorting each half, we merge them back together while counting
     *   the number of smaller elements on the right.
     * 
     * Time Complexity:
     * - O(n log n), since we are applying a standard merge sort.
     * 
     * Space Complexity:
     * - O(n), due to auxiliary space used for merging.
     */
    private static void mergeSort(int[][] pairs, int start, int end, int[] out) {
        if (start >= end)
            return;
        int mid = start + (end - start) / 2;
        mergeSort(pairs, start, mid, out);
        mergeSort(pairs, mid + 1, end, out);
        mergeAndCount(pairs, start, mid, end, out);
    }

    /*-
     * Helper Method: mergeAndCount
     * 
     * Intuition:
     * - This function merges two sorted halves while counting how many elements
     *   from the right half are smaller than elements in the left half.
     * 
     * Explanation:
     * - If an element from the right subarray is placed before an element from
     *   the left subarray, it means all remaining elements in the right
     *   subarray are smaller than that left element.
     * - We maintain an auxiliary list to store the merged elements and use it
     *   to update the original array.
     * - We update the count for the elements of the left subarray whenever an
     *   element from the right subarray is placed before them.
     * 
     * Time Complexity:
     * - O(n), as each merge step processes all elements in the range.
     * 
     * Space Complexity:
     * - O(n), since we use an auxiliary array.
     */
    private static void mergeAndCount(int[][] pairs, int start, int mid, int end, int[] out) {
        List<int[]> temp = new ArrayList<>();
        int i = start, j = mid + 1;

        while (i <= mid && j <= end) {
            if (pairs[i][0] <= pairs[j][0]) {
                temp.add(pairs[j++]);
            } else {
                out[pairs[i][1]] += (end - j + 1);
                temp.add(pairs[i++]);
            }
        }

        while (i <= mid)
            temp.add(pairs[i++]);
        while (j <= end)
            temp.add(pairs[j++]);

        for (int k = start; k <= end; k++) {
            pairs[k] = temp.get(k - start);
        }
    }
}
