/**
 * Problem Statement:
 * 
 *     Given an array of integers, sort the array using the Counting Sort algorithm.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-50,000 <= arr[i] <= 50,000).
 * 
 * Output:
 *     - The sorted array in non-decreasing order.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [4, 2, -3, 1, 0]
 *     Output:
 *         [-3, 0, 1, 2, 4]
 * 
 *     Explanation:
 *         The input array [4, 2, -3, 1, 0] is sorted to [-3, 0, 1, 2, 4] using Counting Sort.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j01CountingSort {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt(); // Input: size of the array

        int[] arr = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Output the rearranged array
        System.out.println("Sorted array: " + Arrays.toString(countingSort(arr)));

        in.close();
    }

    /**
     * Approach 1: Counting Sort
     * 
     * Intuition:
     * - Counting Sort is a non-comparison-based sorting algorithm that works
     *   efficiently for arrays where the range of input values is not
     *   significantly larger than the number of elements. It counts the
     *   occurrences of each unique element and uses this information to
     *   determine the positions of elements in the sorted array.
     * 
     * Explanation:
     * - Determine the minimum (`min`) and maximum (`max`) values in the array to
     *   establish the range of the input data.
     * - Create a frequency array (`freq`) of size `(max - min + 1)` to count the
     *   occurrences of each element.
     * - Iterate through the input array and populate the frequency array.
     * - Traverse the frequency array to reconstruct the sorted array by placing
     *   elements in their correct positions based on their counts.
     * 
     * Time Complexity:
     * - O(n + k), where `n` is the number of elements in the input array and `k`
     *   is the range of the input values (`max - min`).
     * 
     * Space Complexity:
     * - O(k), due to the additional space required for the frequency array.
     * 
     * @param nums The input array of integers.
     * @return The sorted array.
     */
    public static int[] countingSort(int[] nums) {
        int min = -50000;
        int max = 50000;
        int[] freq = new int[max - min + 1];

        for (int ele : nums) {
            freq[ele - min]++;
        }

        int idx = 0;
        for (int i = min; i <= max; i++) {
            while (freq[i - min] > 0) {
                nums[idx] = i;
                idx++;
                freq[i - min]--;
            }
        }
        return nums;
    }

    /**
     * Approach 2: Stable Counting Sort
     * 
     * Intuition:
     * - The standard Counting Sort algorithm does not guarantee stability,
     *   meaning that the relative order of equal elements may not be preserved.
     *   To achieve a stable sort, we can modify the algorithm by computing the
     *   cumulative frequency array, which allows us to place each element in its
     *   correct position in the output array while maintaining the original
     *   order of equal elements.
     * 
     * Explanation:
     * - Determine the minimum (`min`) and maximum (`max`) values in the array to
     *   establish the range of the input data.
     * - Create a frequency array (`freq`) of size `(max - min + 1)` to count the
     *   occurrences of each element.
     * - Iterate through the input array and populate the frequency array.
     * - Transform the frequency array into a cumulative frequency array, where
     *   each element at index `i` contains the sum of frequencies up to `i`.
     * - Create an output array and iterate through the input array in reverse
     *   order, placing each element in its correct position in the output array
     *   based on the cumulative frequencies, and decrementing the corresponding
     *   frequency count to handle duplicates.
     * 
     * Time Complexity:
     * - O(n + k), where `n` is the number of elements in the input array and `k`
     *   is the range of the input values (`max - min`).
     * 
     * Space Complexity:
     * - O(n + k), due to the additional space required for the frequency array
     *   and the output array.
     * 
     * @param nums The input array of integers.
     * @return The stably sorted array.
     */
    public static int[] countingSortStable(int[] nums) {
        int min = -50000;
        int max = 50000;
        int[] freq = new int[max - min + 1];

        for (int ele : nums) {
            freq[ele - min]++;
        }

        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        int[] out = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int ele = nums[i];
            int idx = --freq[ele - min];
            out[idx] = ele;
        }
        return out;
    }
}
