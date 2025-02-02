/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` and an integer `k`, return the top `k` most frequent elements.
 *     You may return the answer in any order.
 * 
 * Input:
 *     - An integer array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (1 <= k <= n), representing the number of most frequent elements to return.
 * 
 * Output:
 *     - An array of size `k` containing the `k` most frequent elements from the input array.
 * 
 * Example:
 *     Input:
 *     nums = [1, 1, 1, 2, 2, 3], k = 2
 *     Output:
 *     [1, 2]
 * 
 *     Explanation:
 *     The number 1 appears 3 times, and the number 2 appears 2 times. Thus, the top 2 most frequent elements are 1 and 2.
 */

import java.util.ArrayList;
import java.util.Collections;

public class j12TopKFrequentElements {

    public static void main(String args[]) {
        // Test Case 1: Normal test case with frequent elements
        int[] nums1 = { 1, 1, 1, 2, 2, 3 };
        int k1 = 2;
        int[] result1 = topKFrequent(nums1, k1);
        System.out.println("Top " + k1 + " frequent elements: " + java.util.Arrays.toString(result1));

        // Test Case 2: Edge case with all elements unique
        int[] nums2 = { 4, 5, 6, 7, 8 };
        int k2 = 3;
        int[] result2 = topKFrequent(nums2, k2);
        System.out.println("Top " + k2 + " frequent elements: " + java.util.Arrays.toString(result2));

        // Test Case 3: Test case with large k value
        int[] nums3 = { 1, 1, 1, 2, 3, 3, 3, 4, 5 };
        int k3 = 4;
        int[] result3 = topKFrequent(nums3, k3);
        System.out.println("Top " + k3 + " frequent elements: " + java.util.Arrays.toString(result3));
    }

    /**
     * Approach: Bucket Sort and Frequency Count
     * 
     * Intuition:
     * - We can count the frequency of each element in the array and then sort the elements based on their frequency.
     * - Using a bucket sort approach, we can efficiently organize the elements based on their frequencies.
     * - We first count the occurrences of each element in a frequency array.
     * - Then, we group the elements based on their frequencies using "buckets", where the index of the bucket represents the frequency.
     * - Finally, we traverse the buckets to collect the `k` most frequent elements.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements in the array. The array is scanned once for counting frequencies, 
     *   and then each bucket is processed.
     * 
     * Space Complexity:
     * - O(n), for the frequency array and the bucket structure used to store elements.
     * 
     * @param nums The input array of numbers.
     * @param k The number of most frequent elements to return.
     * @return An array containing the top k most frequent elements.
     */
    public static int[] frequencySort(int[] nums) {
        int min = -10000;
        int max = 10000;
        int[] freq = new int[max - min + 1]; // Frequency array
        for (int n : nums) {
            freq[n - min]++; // Count frequencies of each element
        }

        // Create buckets for storing elements based on their frequency
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i < nums.length + 1; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Group numbers by frequency into buckets
        for (int i = min; i <= max; i++) {
            if (freq[i - min] > 0) {
                buckets[freq[i - min]].add(i); // Add element to the corresponding frequency bucket
            }
        }

        int[] out = new int[nums.length];
        int idx = 0;

        // Traverse buckets in reverse order to get the most frequent elements
        for (int frq = nums.length; frq >= 1; frq--) {
            Collections.reverse(buckets[frq]); // Reverse the order to return higher frequencies first
            for (int ele : buckets[frq]) {
                for (int j = 0; j < frq; j++) {
                    out[idx++] = ele; // Fill output array with frequent elements
                }
            }
        }
        return out;
    }

    /**
     * Approach: Extracting the Top k Most Frequent Elements
     * 
     * Intuition:
     * - After sorting the elements by frequency, we can easily extract the top `k` most frequent elements.
     * - The sorted array from the `frequencySort` function provides a list of elements in descending order of frequency.
     * - We simply pick the first `k` elements from the sorted result.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements in the array. Sorting elements based on frequency is already handled in the previous approach.
     * 
     * Space Complexity:
     * - O(k), where k is the number of most frequent elements to return. The space is used for storing the result array.
     * 
     * @param nums The input array of numbers.
     * @param k The number of most frequent elements to return.
     * @return An array containing the top k most frequent elements.
     */
    public static int[] topKFrequent(int[] nums, int k) {
        int[] sortedArray = frequencySort(nums); // Get the sorted array based on frequency
        int[] out = new int[k];
        int i = sortedArray.length - 1; // Start from the end of the sorted array
        out[0] = sortedArray[i];
        int j = 1;

        // Collect the top k frequent elements from the sorted array
        while (j < k) {
            while (sortedArray[i] == sortedArray[i - 1]) {
                i--; // Skip duplicates
            }
            out[j] = sortedArray[i - 1];
            j++;
            i--;
        }
        return out;
    }
}
