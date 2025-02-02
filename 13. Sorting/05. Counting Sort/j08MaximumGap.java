/**
 * Problem Statement:
 * 
 *     Given an unsorted array of integers, return the maximum difference between
 *     two successive elements in the array after sorting it. If the array contains 
 *     less than two elements, return 0.
 * 
 * Input:
 *     - An array of integers `nums` (1 <= nums.length <= 10^5, 0 <= nums[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the maximum gap between successive elements after sorting.
 * 
 * Example:
 *     Input:
 *         nums = [3, 6, 9, 1]
 *     Output:
 *         3
 * 
 *     Explanation:
 *         - The sorted array is [1, 3, 6, 9].
 *         - The gaps between consecutive elements are [2, 3, 3].
 *         - The maximum gap is 3.
 */

public class j08MaximumGap {

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = { 3, 6, 9, 1 };
        System.out.println("Maximum gap for nums1: " + maximumGap(nums1)); // Expected output: 3

        // Test case 2
        int[] nums2 = { 10 };
        System.out.println("Maximum gap for nums2: " + maximumGap(nums2)); // Expected output: 0

        // Test case 3
        int[] nums3 = { 1, 100, 3, 4, 2, 8 };
        System.out.println("Maximum gap for nums3: " + maximumGap(nums3)); // Expected output: 96
    }

    /**
     * Approach: Radix Sort and Linear Scan
     * 
     * Intuition:
     * - The goal is to find the maximum gap between any two consecutive elements in
     *   the sorted version of the input array.
     * - To sort the array, we can use Radix Sort, which works efficiently for
     *   large numbers of elements, especially when the number of digits in the
     *   largest number is relatively small (logarithmic in the value of the largest element).
     * - After sorting, we can simply scan through the array to compute the maximum gap
     *   between any two successive elements.
     * 
     * Time Complexity:
     * - O(n * k), where `n` is the number of elements in the array and `k` is the
     *   number of digits in the largest number (which is typically small).
     * 
     * Space Complexity:
     * - O(n), for the space required to store the array and the intermediate sorted results.
     * 
     * @param nums The array of integers to find the maximum gap in.
     * @return The maximum gap between two successive elements after sorting.
     */
    public static int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        // Apply Radix Sort to sort the array
        for (int i = 1; i <= (int) 1e8; i *= 10) {
            nums = radixSort(nums, i);
        }
        // Find the maximum gap
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(gap, nums[i] - nums[i - 1]);
        }
        return gap;
    }

    /**
     * Radix Sort
     * 
     * Intuition:
     * - Radix Sort is a non-comparative sorting algorithm that sorts the input 
     *   elements digit by digit, from least significant digit (LSD) to most 
     *   significant digit (MSD).
     * - In this case, we are using Radix Sort for sorting the array, which helps
     *   us in obtaining the sorted array to calculate the maximum gap.
     * 
     * Time Complexity:
     * - O(n * k), where `n` is the number of elements in the array and `k` is the
     *   number of digits in the largest number.
     * 
     * Space Complexity:
     * - O(n), for the space used to store the intermediate results during sorting.
     * 
     * @param nums The array of integers to be sorted.
     * @param place The place value (1, 10, 100, ...) to sort by.
     * @return The sorted array.
     */
    public static int[] radixSort(int[] nums, int place) {
        int[] freq = new int[10];

        // Count occurrences of each digit at the given place value
        for (int ele : nums) {
            int digit = (ele / place) % 10;
            freq[digit]++;
        }

        // Convert frequency to prefix sum (cumulative count)
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        // Build the output array (sorted)
        int[] out = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int ele = nums[i];
            int digit = (ele / place) % 10;
            int idx = --freq[digit];
            out[idx] = ele;
        }
        return out;
    }
}
