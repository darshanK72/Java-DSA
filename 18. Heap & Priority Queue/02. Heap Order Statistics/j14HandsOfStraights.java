/**
 * LeetCode 846: Hand of Straights
 * 
 * Problem Statement:
 *     Alice has a hand of cards, given as an array of integers. Now she wants to
 *     rearrange the cards into groups so that each group is size k, and consists
 *     of k consecutive cards. Return true if and only if she can.
 * 
 * Input:
 *     - nums[]: Array of integers representing cards
 *     - k: Size of each group (number of consecutive cards)
 * 
 * Output:
 *     - boolean: True if cards can be arranged into groups of k consecutive cards
 * 
 * Example:
 *     Input: nums = [1,2,3,6,2,3,4,7,8], k = 3
 *     Output: true
 * 
 *     Explanation:
 *     Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *     Each group contains 3 consecutive cards.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class j14HandsOfStraights {

    /**
     * Approach 1: HashMap with Minimum Element Tracking
     * 
     * Intuition:
     * - Count frequency of each card using HashMap
     * - Repeatedly find minimum card and try to form a group
     * - Check if k consecutive cards exist starting from minimum
     * 
     * Explanation:
     * 1. Count card frequencies in HashMap
     * 2. While HashMap is not empty:
     *    - Find minimum card
     *    - Try to form group of k consecutive cards
     *    - Decrease frequency or remove cards used
     * 3. Return true if all cards are used
     * 
     * Time Complexity: O(n * k) where n is number of cards
     *                  - O(n) to count frequencies
     *                  - O(n) iterations to process cards
     *                  - O(k) to check consecutive cards
     * Space Complexity: O(n) for storing card frequencies
     * 
     * @param nums    Array of integers representing cards
     * @param k       Size of each group
     * @return        True if cards can be arranged into groups
     */
    public static boolean isNStraightHandHashMap(int[] nums, int k) {
        // Count frequency of each card
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Process cards until map is empty
        while (!map.isEmpty()) {
            // Find minimum card
            int min = Collections.min(map.keySet());
            
            // Try to form group of k consecutive cards
            for (int i = min; i < min + k; i++) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) - 1);
                    if (map.get(i) == 0)
                        map.remove(i);
                } else
                    return false;
            }
        }

        return true;
    }

    /**
     * Approach 2: TreeMap for Sorted Keys
     * 
     * Intuition:
     * - Use TreeMap to maintain sorted order of cards
     * - First check if total cards can be divided into groups
     * - Process cards in sorted order to form groups
     * 
     * Explanation:
     * 1. Check if total cards can be divided into groups of size k
     * 2. Count card frequencies in TreeMap
     * 3. While TreeMap is not empty:
     *    - Get minimum card (first key)
     *    - Try to form group of k consecutive cards
     *    - Decrease frequency or remove cards used
     * 4. Return true if all cards are used
     * 
     * Time Complexity: O(n log n) where n is number of cards
     *                  - O(n log n) to maintain sorted order
     *                  - O(n) to process all cards
     * Space Complexity: O(n) for storing card frequencies
     * 
     * @param nums    Array of integers representing cards
     * @param k       Size of each group
     * @return        True if cards can be arranged into groups
     */
    public static boolean isPossibleDivideTreeMap(int[] nums, int k) {
        // Check if total cards can be divided into groups
        if (nums.length % k != 0)
            return false;

        // Count frequency of each card
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Process cards until map is empty
        while (!map.isEmpty()) {
            // Get minimum card
            int min = map.firstEntry().getKey();
            
            // Try to form group of k consecutive cards
            for (int i = min; i < min + k; i++) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) - 1);
                    if (map.get(i) == 0)
                        map.remove(i);
                } else
                    return false;
            }
        }

        return true;
    }

    /**
     * Approach 3: Priority Queue
     * 
     * Intuition:
     * - Use min heap to maintain cards in sorted order
     * - Repeatedly try to form groups starting from minimum card
     * - Remove cards as they are used in groups
     * 
     * Explanation:
     * 1. Add all cards to min heap
     * 2. While heap is not empty:
     *    - Get minimum card
     *    - Try to form group of k consecutive cards
     *    - Remove cards as they are used
     * 3. Return true if all cards are used
     * 
     * Time Complexity: O(n log n) where n is number of cards
     *                  - O(n log n) for heap operations
     *                  - O(n) to process all cards
     * Space Complexity: O(n) for storing cards in heap
     * 
     * @param nums    Array of integers representing cards
     * @param k       Size of each group
     * @return        True if cards can be arranged into groups
     */
    public static boolean isNStraightHandPriorityQueue(int[] nums, int k) {
        // Initialize min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }

        // Process cards until heap is empty
        while (!pq.isEmpty()) {
            // Get minimum card
            int start = pq.peek();
            
            // Try to form group of k consecutive cards
            for (int i = start; i < start + k; i++) {
                if (!pq.remove(i))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int k1 = 3;
        System.out.println("Input: nums = " + java.util.Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("HashMap Output: " + isNStraightHandHashMap(nums1, k1));
        System.out.println("TreeMap Output: " + isPossibleDivideTreeMap(nums1, k1));
        System.out.println("PriorityQueue Output: " + isNStraightHandPriorityQueue(nums1, k1));

        // Test Case 2: Cannot divide into groups
        System.out.println("\nCannot Divide Test:");
        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 4;
        System.out.println("Input: nums = " + java.util.Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("HashMap Output: " + isNStraightHandHashMap(nums2, k2));
        System.out.println("TreeMap Output: " + isPossibleDivideTreeMap(nums2, k2));
        System.out.println("PriorityQueue Output: " + isNStraightHandPriorityQueue(nums2, k2));

        // Test Case 3: Single group
        System.out.println("\nSingle Group Test:");
        int[] nums3 = {1, 2, 3, 4};
        int k3 = 4;
        System.out.println("Input: nums = " + java.util.Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("HashMap Output: " + isNStraightHandHashMap(nums3, k3));
        System.out.println("TreeMap Output: " + isPossibleDivideTreeMap(nums3, k3));
        System.out.println("PriorityQueue Output: " + isNStraightHandPriorityQueue(nums3, k3));

        // Test Case 4: Duplicate cards
        System.out.println("\nDuplicate Cards Test:");
        int[] nums4 = {1, 1, 2, 2, 3, 3};
        int k4 = 2;
        System.out.println("Input: nums = " + java.util.Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("HashMap Output: " + isNStraightHandHashMap(nums4, k4));
        System.out.println("TreeMap Output: " + isPossibleDivideTreeMap(nums4, k4));
        System.out.println("PriorityQueue Output: " + isNStraightHandPriorityQueue(nums4, k4));
    }
}
