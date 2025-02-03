/*-
 * Problem Statement:
 * 
 *     Given an array of integers, you need to determine whether the number of global inversions
 *     in the array is equal to the number of local inversions. A local inversion is a pair (i, i + 1)
 *     such that nums[i] > nums[i + 1], and a global inversion is a pair (i, j) such that i < j and
 *     nums[i] > nums[j].
 * 
 *     Your task is to implement a method that checks if the array satisfies the condition:
 *     if the number of global inversions is equal to the number of local inversions.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums[]` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - Return `true` if the number of global inversions is equal to the number of local inversions,
 *       otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     [1, 2, 0, 3, 4]
 *     Output:
 *     true
 *     
 *     Explanation:
 *     The only inversion is the local inversion between elements (2, 0), which is the same as the global inversion.
 */

public class j04GlobalAndLocalInversions {

    /*-
     * Approach: Check if Global Inversions are equal to Local Inversions
     * 
     * Intuition:
     * - Local inversions are the adjacent elements in the array that are out of order.
     * - Global inversions are all pairs (i, j) such that i < j and nums[i] > nums[j].
     * - If there are no global inversions beyond the local inversions (i.e., no non-adjacent inversions),
     *   then the number of global inversions will be equal to the number of local inversions.
     * 
     * Time Complexity:
     * - O(n log n), as we use merge sort to count global inversions.
     * 
     * Space Complexity:
     * - O(n), as we need extra space for the left and right subarrays during the merge operation.
     * 
     * @param nums The input array of numbers.
     * @return `true` if global inversions are equal to local inversions, otherwise `false`.
     */
    public static boolean isIdealPermutation(int[] nums) {
        // Count the number of local inversions
        int local = countLocalInversions(nums);
        // Count the number of global inversions using merge sort
        int global = countGlobalInversions(nums, 0, nums.length - 1);
        // Return true if global and local inversions are the same, otherwise false
        return (global == local);
    }

    /*-
     * Helper function: Count the number of global inversions using merge sort.
     * 
     * Intuition:
     * - This function uses a modified merge sort to count all global inversions.
     * - During the merge process, we count how many times an element from the left subarray
     *   is greater than an element from the right subarray.
     * 
     * Time Complexity:
     * - O(n log n), as this is part of the merge sort algorithm.
     * 
     * @param nums The input array.
     * @param start The starting index of the subarray.
     * @param end The ending index of the subarray.
     * @return The total number of global inversions in the array.
     */
    public static int countGlobalInversions(int[] nums, int start, int end) {
        if (start >= end)
            return 0; // Base case: if subarray has 1 or 0 elements, no inversions exist
        int mid = start + (end - start) / 2;
        int count = countGlobalInversions(nums, start, mid); // Count global inversions in the left half
        count += countGlobalInversions(nums, mid + 1, end); // Count global inversions in the right half
        count += mergeAndCount(nums, start, mid, end); // Count global inversions during the merge
        return count;
    }

    /*-
     * Helper function: Count the number of local inversions.
     * 
     * Intuition:
     * - A local inversion is simply a pair of adjacent elements where nums[i] > nums[i + 1].
     * - We iterate through the array and check each adjacent pair for inversion.
     * 
     * Time Complexity:
     * - O(n), as we simply traverse the array once.
     * 
     * @param nums The input array.
     * @return The number of local inversions in the array.
     */
    public static int countLocalInversions(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) // Check if adjacent elements are in inversion
                count++;
        }
        return count;
    }

    /*-
     * Helper function: Merge two sorted subarrays and count global inversions.
     * 
     * Intuition:
     * - This is the merge step of merge sort, where we merge two sorted subarrays.
     * - During the merge, we count the number of global inversions by checking how many elements
     *   in the left subarray are greater than elements in the right subarray.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements in the merged subarrays.
     * 
     * @param nums The input array.
     * @param start The starting index of the subarray.
     * @param mid The middle index dividing the array into two halves.
     * @param end The ending index of the subarray.
     * @return The number of global inversions found during the merge.
     */
    public static int mergeAndCount(int[] nums, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        // Create temporary arrays to store the left and right subarrays
        int[] left = new int[n1];
        int[] right = new int[n2];
        int count = 0;

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            left[i] = nums[start + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = nums[mid + 1 + i];
        }

        int i = 0, j = 0, k = start;
        // Merge the two sorted subarrays
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
                count += (n1 - i); // Count how many elements in the left subarray are greater than right[j]
            }
        }

        // Copy remaining elements from left subarray
        while (i < n1) {
            nums[k++] = left[i++];
        }

        // Copy remaining elements from right subarray
        while (j < n2) {
            nums[k++] = right[j++];
        }

        return count;
    }

    /*-
     * Main method to test the code with different test cases.
     */
    public static void main(String[] args) {

        // Test Case 1
        int[] arr1 = { 1, 2, 0, 3, 4 };
        System.out.println("Test Case 1 - Result: " + isIdealPermutation(arr1)); // Expected: true

        // Test Case 2
        int[] arr2 = { 1, 2, 3, 4 };
        System.out.println("Test Case 2 - Result: " + isIdealPermutation(arr2)); // Expected: true

        // Test Case 3
        int[] arr3 = { 1, 3, 2, 4, 5 };
        System.out.println("Test Case 3 - Result: " + isIdealPermutation(arr3)); // Expected: false

        // Test Case 4
        int[] arr4 = { 5, 3, 4, 2, 1 };
        System.out.println("Test Case 4 - Result: " + isIdealPermutation(arr4)); // Expected: false
    }
}
