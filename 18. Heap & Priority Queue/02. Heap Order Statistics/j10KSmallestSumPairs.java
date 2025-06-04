/**
 * LeetCode 373: Find K Pairs with Smallest Sums
 * 
 * Problem Statement:
 *     Given two sorted arrays nums1 and nums2, and an integer k, return the k
 *     pairs (u,v) with the smallest sums where u is from nums1 and v is from
 *     nums2. The pairs should be returned in ascending order of their sums.
 * 
 * Input:
 *     - nums1[]: First sorted array of integers
 *     - nums2[]: Second sorted array of integers
 *     - k: Number of pairs to find (1 <= k <= nums1.length * nums2.length)
 * 
 * Output:
 *     - List<List<Integer>>: K pairs with smallest sums
 * 
 * Example:
 *     Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 *     Output: [[1,2],[1,4],[1,6]]
 * 
 *     Explanation:
 *     The first 3 pairs are returned from the following sequences:
 *     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class j10KSmallestSumPairs {

    /**
     * Approach: Min Heap with Pair Tracking
     * 
     * Intuition:
     * - Use min heap to maintain pairs sorted by their sum
     * - Start with first element of nums1 paired with first element of nums2
     * - For each pair, consider next element from nums2
     * - Track index in nums2 to avoid duplicates
     * 
     * Explanation:
     * 1. Create min heap with custom comparator for pair sums
     * 2. Initialize heap with first element of nums1 paired with nums2[0]
     * 3. For k iterations:
     *    - Extract pair with smallest sum
     *    - Add next pair from same nums1 element with next nums2 element
     * 4. Return k pairs with smallest sums
     * 
     * Time Complexity: O(k log k) where k is number of pairs to find
     *                  - O(log k) for each heap operation
     *                  - O(k) pairs to process
     * Space Complexity: O(k) for storing pairs in heap
     * 
     * @param nums1    First sorted array
     * @param nums2    Second sorted array
     * @param k        Number of pairs to find
     * @return         List of k pairs with smallest sums
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Initialize min heap with custom comparator for pair sums
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return (a[0] + a[1]) - (b[0] + b[1]);
        });

        // Initialize heap with first element of nums1 paired with nums2[0]
        int i = 0;
        while(i < nums1.length && pq.size() < k) {
            pq.add(new int[]{nums1[i++], nums2[0], 0});
        }

        // Extract k pairs with smallest sums
        List<List<Integer>> out = new ArrayList<>();
        while(k-- > 0 && !pq.isEmpty()) {
            // Get pair with smallest sum
            int[] pair = pq.remove();
            out.add(new ArrayList<>(Arrays.asList(pair[0], pair[1])));
            
            // Add next pair from same nums1 element with next nums2 element
            if(pair[2] == nums2.length - 1) continue;
            pq.add(new int[]{pair[0], nums2[pair[2] + 1], pair[2] + 1});
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        System.out.println("Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3");
        System.out.println("Output: " + kSmallestPairs(nums1, nums2, 3));

        // Test Case 2: k equals 1
        System.out.println("\nK equals 1 Test:");
        int[] nums3 = {1, 2, 3};
        int[] nums4 = {4, 5, 6};
        System.out.println("Input: nums1 = [1,2,3], nums2 = [4,5,6], k = 1");
        System.out.println("Output: " + kSmallestPairs(nums3, nums4, 1));

        // Test Case 3: k equals total possible pairs
        System.out.println("\nK equals total pairs Test:");
        int[] nums5 = {1, 2};
        int[] nums6 = {3, 4};
        System.out.println("Input: nums1 = [1,2], nums2 = [3,4], k = 4");
        System.out.println("Output: " + kSmallestPairs(nums5, nums6, 4));

        // Test Case 4: Single element arrays
        System.out.println("\nSingle Element Arrays Test:");
        int[] nums7 = {1};
        int[] nums8 = {2};
        System.out.println("Input: nums1 = [1], nums2 = [2], k = 1");
        System.out.println("Output: " + kSmallestPairs(nums7, nums8, 1));
    }
}
