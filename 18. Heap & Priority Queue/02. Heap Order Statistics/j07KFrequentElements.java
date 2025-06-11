/**
 * LeetCode 347: Top K Frequent Elements
 * 
 * Problem Statement:
 *     Given an integer array nums and an integer k, return the k most frequent
 *     elements. You may return the answer in any order.
 * 
 * Input:
 *     - nums[]: Integer array of numbers
 *     - k: Number of most frequent elements to return (1 <= k <= number of unique elements)
 * 
 * Output:
 *     - int[]: Array containing k most frequent elements
 * 
 * Example:
 *     Input: nums = [1,1,1,2,2,3], k = 2
 *     Output: [1,2]
 * 
 *     Explanation:
 *     Element 1 appears 3 times, element 2 appears 2 times, and element 3 appears 1 time.
 *     The two most frequent elements are 1 and 2.
 */

import java.util.HashMap;
import java.util.PriorityQueue;

public class j07KFrequentElements {

    /**
     * Approach: Min Heap with Frequency Count
     * 
     * Intuition:
     * - Count frequency of each element using HashMap
     * - Use min heap to maintain k most frequent elements
     * - Keep only k elements with highest frequency in heap
     * 
     * Explanation:
     * 1. Count frequencies:
     *    - Create HashMap to store element frequencies
     *    - Iterate through array to count occurrences
     * 2. Build min heap:
     *    - Create min heap of size k
     *    - For each unique element:
     *      * If heap not full, add element
     *      * If current frequency > heap root, replace root
     * 3. Extract result:
     *    - Create output array
     *    - Extract elements from heap
     * 
     * Time Complexity: O(n log k) where n is array length
     *                  - O(n) to count frequencies
     *                  - O(log k) for each heap operation
     * Space Complexity: O(n) for HashMap and heap
     * 
     * @param nums    Input array of integers
     * @param k       Number of most frequent elements to return
     * @return        Array containing k most frequent elements
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // Count frequency of each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }

        // Initialize min heap with custom comparator
        // [frequency, element] pairs, sorted by frequency
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        // Maintain k most frequent elements in heap
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new int[] { map.get(key), key });
            } else if (pq.peek()[0] < map.get(key)) {
                pq.remove();
                pq.add(new int[] { map.get(key), key });
            }
        }

        // Extract result from heap
        int[] out = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            out[i++] = pq.remove()[1];
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        System.out.println("Input: nums = [1,1,1,2,2,3], k = 2");
        System.out.println("Output: " + java.util.Arrays.toString(topKFrequent(nums1, 2)));

        // Test Case 2: Single element
        System.out.println("\nSingle Element Test:");
        int[] nums2 = {1};
        System.out.println("Input: nums = [1], k = 1");
        System.out.println("Output: " + java.util.Arrays.toString(topKFrequent(nums2, 1)));

        // Test Case 3: All elements have same frequency
        System.out.println("\nEqual Frequency Test:");
        int[] nums3 = {1, 2, 3, 4};
        System.out.println("Input: nums = [1,2,3,4], k = 2");
        System.out.println("Output: " + java.util.Arrays.toString(topKFrequent(nums3, 2)));

        // Test Case 4: k equals number of unique elements
        System.out.println("\nK equals unique elements Test:");
        int[] nums4 = {1, 1, 2, 2, 3, 3};
        System.out.println("Input: nums = [1,1,2,2,3,3], k = 3");
        System.out.println("Output: " + java.util.Arrays.toString(topKFrequent(nums4, 3)));
    }
}
