/**
 * LeetCode 300. Longest Increasing Subsequence
 * 
 * Problem Statement:
 *     Given an integer array nums, return the length of the longest strictly 
 *     increasing subsequence. A subsequence is a sequence that can be derived 
 *     from an array by deleting some or no elements without changing the order 
 *     of the remaining elements.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 2500, -10^4 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - Return the length of the longest increasing subsequence
 * 
 * Example:
 *     Input: [10,9,2,5,3,7,101,18]
 *     Output: 4
 * 
 *     Explanation:
 *     The longest increasing subsequence is [2,3,7,18], therefore the length 
 *     is 4.
 */

import java.util.*;

public class j04LISBinarySearch {

    /**
     * Approach: Binary Search with Dynamic Programming
     * 
     * Intuition:
     * - Maintain a list where each index represents the length of LIS ending 
     *   with the smallest possible element
     * - For each element, find the position where it can be placed to maintain 
     *   increasing order
     * - Use binary search to efficiently find the correct position
     * - Replace elements to keep the list as small as possible for future 
     *   elements
     * 
     * Explanation:
     * - Step 1: Initialize empty list to store potential LIS elements
     * - Step 2: For each element, use binary search to find correct position
     * - Step 3: If element is larger than all elements, append to list
     * - Step 4: Otherwise, replace the first element >= current element
     * - Step 5: Return the size of the list as LIS length
     * 
     * Time Complexity: O(n log n) where n is array length - binary search for 
     *                  each element
     * Space Complexity: O(n) for storing the potential LIS elements
     * 
     * @param nums    Array of integers to find LIS length for
     * @return        Length of the longest increasing subsequence
     */
    public static int lengthOfLIS(int[] nums) {
        // Initialize list to store potential LIS elements
        ArrayList<Integer> list = new ArrayList<>();
        
        // Handle edge case of empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Process each element in the input array
        for (int i = 0; i < nums.length; i++) {
            // Find the correct position for current element using binary search
            int lb = lowerBound(list, nums[i]);
            
            // If element is larger than all existing elements, extend the list
            if (lb == list.size()) {
                list.add(nums[i]);
            } else {
                // Replace the first element >= current element to maintain 
                // smallest possible ending elements
                list.set(lb, nums[i]);
            }
        }
        
        // Return the length of the longest increasing subsequence
        return list.size();
    }

    /**
     * Helper Method: Binary Search for Lower Bound
     * 
     * Intuition:
     * - Find the leftmost position where the given number can be inserted
     * - This position represents where the number should be placed to maintain 
     *   sorted order
     * - Used to determine if we should append or replace in the LIS list
     * 
     * Explanation:
     * - Use binary search to find the first position where list[mid] >= num
     * - If all elements are smaller, return list.size() (append position)
     * - Otherwise return the index where replacement should occur
     * - Maintains the invariant that elements before the position are < num
     * 
     * Time Complexity: O(log n) where n is list size
     * Space Complexity: O(1) using iterative approach
     * 
     * @param list    Sorted list to search in
     * @param num     Number to find position for
     * @return        Index where num should be placed
     */
    private static int lowerBound(ArrayList<Integer> list, int num) {
        // Initialize binary search boundaries
        int s = 0;
        int e = list.size() - 1;
        int ans = list.size(); // Default position if num > all elements
        
        // Perform binary search to find lower bound position
        while (s <= e) {
            // Calculate middle index to avoid overflow
            int mid = s + (e - s) / 2;
            
            // If middle element is smaller, search in right half
            if (list.get(mid) < num) {
                s = mid + 1;
            } else {
                // If middle element is >= num, this could be our answer
                e = mid - 1;
                ans = mid; // Update potential answer position
            }
        }
        
        // Return the leftmost position where num can be inserted
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [10,9,2,5,3,7,101,18], Expected: 4, Output: " + 
            lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("Input: [0,1,0,3,2,3], Expected: 4, Output: " + 
            lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println("Input: [7,7,7,7,7,7,7], Expected: 1, Output: " + 
            lengthOfLIS(new int[]{7,7,7,7,7,7,7}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: " + 
            lengthOfLIS(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " + 
            lengthOfLIS(null));
        System.out.println("Input: [1], Expected: 1, Output: " + 
            lengthOfLIS(new int[]{1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,3,6,7,9,4,10,5,6], Expected: 6, Output: " + 
            lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
        System.out.println("Input: [1,2,3,4,5], Expected: 5, Output: " + 
            lengthOfLIS(new int[]{1,2,3,4,5}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [5,4,3,2,1], Expected: 1, Output: " + 
            lengthOfLIS(new int[]{5,4,3,2,1}));
        System.out.println("Input: [1,3,2,4,5], Expected: 4, Output: " + 
            lengthOfLIS(new int[]{1,3,2,4,5}));
    }
}
