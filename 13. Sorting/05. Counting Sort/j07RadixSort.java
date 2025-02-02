/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums`, sort the array in ascending order 
 *     using the Radix Sort algorithm.
 * 
 * Input:
 *     - An integer array `nums` (1 <= nums.length <= 10^5, -10^9 <= nums[i] <= 10^9).
 * 
 * Output:
 *     - A sorted array of integers.
 * 
 * Example:
 *     Input:
 *         nums = [170, 45, 75, 90, 802, 24, 2, 66]
 *     Output:
 *         [2, 24, 45, 66, 75, 90, 170, 802]
 * 
 *     Explanation:
 *         - The given array is sorted using the Radix Sort algorithm, which processes 
 *           digits from the least significant to the most significant.
 */

import java.util.Arrays;

public class j07RadixSort {

    public static void main(String[] args) {
        int[][] testCases = {
                { 170, 45, 75, 90, 802, 24, 2, 66 },
                { 5, 1, 9, 3, 7, 4 },
                { 100, 99, 98, 97, 96 },
                { 3, 1, 4, 1, 5, 9, 2, 6 },
                { 500, 400, 300, 200, 100 }
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("  Unsorted: " + Arrays.toString(testCases[i]));
            System.out.println("  Sorted  : " + Arrays.toString(sortArray(testCases[i])));
            System.out.println();
        }
    }

    /**
     * Method to Sort an Array using Radix Sort
     * 
     * - Calls `radixSort` for each digit place value until the largest number is processed.
     */
    public static int[] sortArray(int[] nums) {
        // Find the maximum number to determine the number of digits
        int max = Arrays.stream(nums).max().orElse(0);

        // Apply counting sort for each digit (1s, 10s, 100s, etc.)
        for (int place = 1; max / place > 0; place *= 10) {
            nums = radixSort(nums, place);
        }
        return nums;
    }

    /**
     * Approach : Radix Sort
     * 
     * Intuition:
     * - Radix Sort is a non-comparative sorting algorithm that sorts numbers digit by digit.
     * - It uses Counting Sort as a subroutine to sort based on individual digit places.
     * - The sorting is performed from the least significant digit (LSD) to the most significant digit (MSD).
     * 
     * Explanation:
     * - For each digit place value (1s, 10s, 100s, etc.), apply Counting Sort.
     * - The Counting Sort ensures the digits are placed in the correct order for stable sorting.
     * - Repeat this process for all digit places until the largest number is processed.
     * 
     * Time Complexity:
     * - O(d * (n + k)), where `d` is the number of digits in the largest number,
     *   `n` is the number of elements, and `k` is the range of digits (0-9).
     * - Since `d` is typically small (log base 10 of the max number), this is nearly linear O(n).
     * 
     * Space Complexity:
     * - O(n) for storing intermediate sorted arrays.
     * 
     * @param nums The array of numbers to be sorted.
     * @param place The current place value (1s, 10s, 100s, etc.).
     * @return The array sorted based on the given place value.
     */
    public static int[] radixSort(int[] nums, int place) {
        int[] freq = new int[10]; // Frequency array for digits (0-9)

        // Count the occurrences of each digit at the given place value
        for (int ele : nums) {
            int digit = (ele / place) % 10;
            freq[digit]++;
        }

        // Convert frequency to cumulative count
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        // Build the output array
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
