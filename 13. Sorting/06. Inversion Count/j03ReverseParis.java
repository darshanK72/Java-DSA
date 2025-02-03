/*-
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to count the number of reverse pairs in the array.
 *     A reverse pair is defined as a pair of indices (i, j) such that i < j and nums[i] > 2 * nums[j].
 *     Your goal is to find how many such reverse pairs exist in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums[]` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - The number of reverse pairs in the array.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 2 3 1
 *     Output:
 *     2
 * 
 *     Explanation:
 *     There are two reverse pairs in this example:
 *     Pair 1: (3, 1) - 3 > 2 * 1
 *     Pair 2: (3, 1) - 3 > 2 * 1
 */

public class j03ReverseParis {

    public static void main(String[] args) {

        // Test Case 1
        int[] arr1 = { 1, 3, 2, 3, 1 };
        System.out.println("Reverse pairs for arr1: " + reversePairs(arr1)); // Expected: 2

        // Test Case 2
        int[] arr2 = { 1, 2, 3, 4, 5 };
        System.out.println("Reverse pairs for arr2: " + reversePairs(arr2)); // Expected: 0

        // Test Case 3
        int[] arr3 = { 5, 4, 3, 2, 1 };
        System.out.println("Reverse pairs for arr3: " + reversePairs(arr3)); // Expected: 10

        // Test Case 4
        int[] arr4 = { 1, 5, 3, 4, 2 };
        System.out.println("Reverse pairs for arr4: " + reversePairs(arr4)); // Expected: 3
    }

    /*-
     * Approach: Merge Sort-based Count of Reverse Pairs
     * 
     * Intuition:
     * - We use a modified merge sort to count the reverse pairs efficiently.
     * - In each merge step, we count how many elements in the left half are greater than 2 times any element in the right half.
     * - The merge sort divides the array into two halves, counts reverse pairs in both halves, and then merges them while counting pairs.
     * 
     * Time Complexity:
     * - O(n log n), as merge sort is applied and counting pairs is done while merging.
     * 
     * Space Complexity:
     * - O(n), as extra space is required to store the left and right subarrays during the merge step.
     * 
     * @param nums The input array of numbers.
     * @return The total number of reverse pairs in the array.
     */
    public static int reversePairs(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    /*-
     * Helper function: Performs merge sort and counts the reverse pairs.
     * 
     * Intuition:
     * - This function recursively divides the array into two halves and counts the reverse pairs.
     * - It calls the mergeAndCount function during the merge step to calculate the reverse pairs between the left and right subarrays.
     * 
     * Time Complexity:
     * - O(n log n), as this is part of the merge sort algorithm.
     * 
     * @param nums The input array.
     * @param start The starting index of the subarray.
     * @param end The ending index of the subarray.
     * @return The total count of reverse pairs in the array.
     */
    public static int mergeSortAndCount(int[] nums, int start, int end) {
        int count = 0;
        if (start < end) {
            // Find the middle point to divide the array into two halves
            int mid = start + (end - start) / 2;
            count += mergeSortAndCount(nums, start, mid); // Count reverse pairs in the left half
            count += mergeSortAndCount(nums, mid + 1, end); // Count reverse pairs in the right half
            count += mergeAndCount(nums, start, mid, end); // Count reverse pairs during the merge step
        }
        return count;
    }

    /*-
     * Helper function: Counts the reverse pairs between two sorted subarrays.
     * 
     * Intuition:
     * - This method compares elements from two sorted subarrays and counts how many times an element from the left
     *   subarray is greater than 2 times an element from the right subarray.
     * - This helps count the reverse pairs efficiently during the merge step.
     * 
     * Time Complexity:
     * - O(n), where n is the total number of elements in the left and right subarrays combined.
     * 
     * @param left The left subarray.
     * @param right The right subarray.
     * @return The number of reverse pairs found between the two subarrays.
     */
    public static int countPairs(int[] left, int[] right) {
        int count = 0;
        int n1 = left.length;
        int n2 = right.length;
        int i = 0;
        int j = 0;

        // Traverse both arrays to count reverse pairs
        while (i < n1 && j < n2) {
            if (left[i] <= 2l * (long) (right[j])) {
                i++;
            } else {
                j++;
                count += (n1 - i); // Count how many pairs from the left subarray are greater than 2 * right[j]
            }
        }
        return count;
    }

    /*-
     * Helper function: Merges two sorted subarrays and counts the reverse pairs.
     * 
     * Intuition:
     * - After counting reverse pairs between two subarrays, this method merges them into a sorted array.
     * - While merging, it ensures the count of reverse pairs is updated.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements in the subarrays.
     * 
     * @param nums The input array.
     * @param start The starting index of the subarray.
     * @param mid The middle index where the array is divided.
     * @param end The ending index of the subarray.
     * @return The count of reverse pairs during the merge step.
     */
    public static int mergeAndCount(int[] nums, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        // Create temporary arrays to store left and right subarrays
        int[] left = new int[n1];
        int[] right = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            left[i] = nums[start + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = nums[mid + 1 + i];
        }

        // Count reverse pairs between the left and right subarrays
        int count = countPairs(left, right);

        int i = 0, j = 0, k = start;
        // Merge the two sorted subarrays
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left subarray
        while (i < n1) {
            nums[k++] = left[i++];
        }

        // Copy any remaining elements from the right subarray
        while (j < n2) {
            nums[k++] = right[j++];
        }

        return count;
    }
}
