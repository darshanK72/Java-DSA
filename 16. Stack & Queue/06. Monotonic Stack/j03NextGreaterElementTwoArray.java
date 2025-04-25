/**
 * Problem Statement:
 *     LeetCode 496. Next Greater Element I
 * 
 *     Given two arrays nums1 and nums2 where nums1 is a subset of nums2, find all
 *     the next greater numbers for nums1's elements in the corresponding positions
 *     of nums2. The Next Greater Number of a number x in nums1 is the first greater
 *     number to its right in nums2. If it does not exist, return -1.
 * 
 * Input:
 *     - Array nums1 of size n (1 ≤ n ≤ 1000)
 *     - Array nums2 of size m (n ≤ m ≤ 10^4)
 *     - All integers in both arrays are unique
 *     - All elements in nums1 and nums2 are in the range [1, 10^4]
 *     - All the integers in nums1 appear in nums2
 * 
 * Output:
 *     - An array ans of length n where ans[i] is the next greater element for
 *       nums1[i] in nums2
 * 
 * Example:
 *     Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 *     Output: [-1,3,-1]
 *     
 *     Explanation:
 *     - For 4, no greater element exists to its right in nums2, so -1
 *     - For 1, the next greater element is 3
 *     - For 2, no greater element exists to its right in nums2, so -1
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class j03NextGreaterElementTwoArray {

    public static void main(String[] args) {
        // Test cases
        int[][] nums1Cases = {
            {4, 1, 2},              // Basic case
            {2, 4},                 // Elements in different order
            {1},                    // Single element
            {1, 3, 5, 2, 4}        // Longer sequence
        };
        
        int[][] nums2Cases = {
            {1, 3, 4, 2},          // Basic case
            {1, 2, 3, 4},          // Increasing sequence
            {1, 2, 3},             // Single element search
            {6, 5, 4, 3, 2, 1, 7}  // Longer sequence
        };

        // Run tests
        for (int i = 0; i < nums1Cases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("nums1: " + Arrays.toString(nums1Cases[i]));
            System.out.println("nums2: " + Arrays.toString(nums2Cases[i]));
            System.out.println("Brute Force: " + 
                Arrays.toString(nextGreaterElement(nums1Cases[i], nums2Cases[i])));
            System.out.println("Optimized: " + 
                Arrays.toString(nextGreaterElementEfficient(nums1Cases[i], nums2Cases[i])));
            System.out.println();
        }
    }

    /**
     * Approach 1: Brute Force with Linear Search
     * 
     * Intuition:
     * - For each element in nums1, find its position in nums2
     * - Then search for the next greater element to its right in nums2
     * - If no greater element exists, use -1
     * 
     * Time Complexity: O(n*m)
     * - For each element in nums1 (n iterations)
     * - We search through nums2 twice (m iterations)
     * - Total: O(n*m) where n is length of nums1 and m is length of nums2
     * 
     * Space Complexity: O(1)
     * - Only using constant extra space
     * - Output array not counted as per convention
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Initialize output array with -1
        int[] out = new int[nums1.length];
        Arrays.fill(out, -1);
        
        // Process each element in nums1
        for (int i = 0; i < nums1.length; i++) {
            // Find position of nums1[i] in nums2
            int j = 0;
            for (; j < nums2.length; j++) {
                if (nums1[i] == nums2[j])
                    break;
            }
            
            // Find next greater element
            while (j < nums2.length && nums2[j] <= nums1[i]) {
                j++;
            }
            if (j < nums2.length)
                out[i] = nums2[j];
        }
        return out;
    }

    /**
     * Approach 2: Using Stack and HashMap (Optimized)
     * 
     * Intuition:
     * - Use a monotonic stack to find next greater elements for all nums2 elements
     * - Store these relationships in a HashMap for O(1) lookup
     * - Then simply look up the values for nums1 elements
     * 
     * Time Complexity: O(m + n)
     * - One pass through nums2 to build HashMap: O(m)
     * - One pass through nums1 to build result: O(n)
     * - Each element pushed/popped at most once
     * 
     * Space Complexity: O(m)
     * - HashMap stores all elements from nums2: O(m)
     * - Stack can store at most m elements: O(m)
     * - Total: O(m) where m is length of nums2
     */
    public static int[] nextGreaterElementEfficient(int[] nums1, int[] nums2) {
        // Stack to maintain elements in decreasing order
        Stack<Integer> stack = new Stack<>();
        // Map to store next greater element for each number
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Process nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {
            // Remove smaller elements from stack
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            // Store next greater element (or -1 if none exists)
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            // Add current element to stack
            stack.push(nums2[i]);
        }

        // Build result array using HashMap
        int[] out = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            out[i] = map.getOrDefault(nums1[i], -1);
        }
        return out;
    }
}
