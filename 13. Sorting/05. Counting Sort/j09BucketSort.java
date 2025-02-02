/**
 * Problem Statement:
 * 
 *     Given an array of integers, sort the array using the Bucket Sort algorithm.
 * 
 * Input:
 *     - An array of integers `nums` (1 <= nums.length <= 10^5, -50000 <= nums[i] <= 50000).
 * 
 * Output:
 *     - The sorted array of integers.
 * 
 * Example:
 *     Input:
 *         nums = [3, 6, 1, 9, 4]
 *     Output:
 *         [1, 3, 4, 6, 9]
 */

import java.util.ArrayList;

public class j09BucketSort {

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = { 3, 6, 1, 9, 4 };
        int[] sortedNums1 = bucketSort(nums1);
        System.out.println("Sorted array for nums1: ");
        for (int num : sortedNums1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Test case 2
        int[] nums2 = { 10, 7, 2, 5, 8, 1 };
        int[] sortedNums2 = bucketSort(nums2);
        System.out.println("Sorted array for nums2: ");
        for (int num : sortedNums2) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Test case 3 (edge case with negative values)
        int[] nums3 = { -10, -7, -2, -5, -8, -1 };
        int[] sortedNums3 = bucketSort(nums3);
        System.out.println("Sorted array for nums3: ");
        for (int num : sortedNums3) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Approach: Bucket Sort
     * 
     * Intuition:
     * - Bucket Sort works by distributing the elements into a number of buckets.
     * - Each bucket is sorted individually (either using a different sorting algorithm or recursively).
     * - Finally, the contents of all the buckets are combined into a single sorted array.
     * 
     * Time Complexity:
     * - O(n + k), where `n` is the number of elements in the array, and `k` is the number of buckets.
     *   In practice, this makes Bucket Sort quite efficient when the input is uniformly distributed.
     * 
     * Space Complexity:
     * - O(n + k), since we use an array of buckets to store the elements.
     * 
     * @param nums The array of integers to be sorted.
     * @return The sorted array.
     */
    public static int[] bucketSort(int[] nums) {
        int min = -50000;
        int max = 50000;
        // Create a bucket for each possible value in the range [min, max]
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[max - min + 1];

        // Initialize each bucket as an empty list
        for (int i = 0; i < max - min + 1; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Place each number in its corresponding bucket
        for (int n : nums) {
            buckets[n - min].add(n);
        }

        // Collect the numbers from the buckets and put them back into the array
        int[] out = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < max - min + 1; i++) {
            for (int n : buckets[i]) {
                out[idx++] = n;
            }
        }

        return out;
    }
}
