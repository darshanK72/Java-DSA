/**
 * Problem Statement:
 *     Maximum of Minimum for Every Window Size
 * 
 *     Given an integer array of size N, find the maximum of minimum for every
 *     window size from 1 to N. For each window size K, find the minimum element
 *     in every possible window of size K, then return the maximum among all these
 *     minimums.
 * 
 * Input:
 *     - Array arr[] where 1 <= N <= 10^5
 *     - -10^9 <= arr[i] <= 10^9
 * 
 * Output:
 *     - ArrayList containing maximum of minimums for each window size
 * 
 * Example:
 *     Input: arr[] = [1, 3, 2, 4, 5]
 *     Output: [5, 4, 3, 2, 1]
 *     
 *     Explanation:
 *     Window size 1: min values [1,3,2,4,5], max = 5
 *     Window size 2: min values [1,2,2,4], max = 4
 *     Window size 3: min values [1,2,2], max = 3
 *     Window size 4: min values [1,2], max = 2
 *     Window size 5: min values [1], max = 1
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class j13MaxOfMinOfEveryWindowSize {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[][] testArrays = {
            {1, 3, 2, 4, 5},      // Basic case
            {5, 1, 10, 2, 3},     // Multiple elements
            {1, 2, 3, 4, 5},      // Increasing order
            {5, 4, 3, 2, 1},      // Decreasing order
            {0, -1, -2}           // Negative numbers
        };

        for (int[] test : testArrays) {
            System.out.println("Array: " + java.util.Arrays.toString(test));
            System.out.println("Max of Mins: " + maxOfMins(test));
            System.out.println();
        }
    }

    /**
     * Approach: Using Sliding Window for Each Size
     * 
     * Intuition:
     * - For each window size K from 1 to N:
     *   * Find minimum element in each window of size K
     *   * Find maximum among these minimums
     * - Use deque to efficiently find window minimums
     * 
     * Time Complexity: O(n²)
     * - For each window size (n)
     * - Process array once for each size (n)
     * - Deque operations are O(1)
     * 
     * Space Complexity: O(n)
     * - Deque can store at most n elements
     * - Output ArrayList stores n elements
     */
    public static ArrayList<Integer> maxOfMins(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        // Process each window size
        for (int i = 0; i < arr.length; i++) {
            out.add(maxOfMinSlidingWindow(arr, (i + 1)));
        }
        return out;
    }

    /**
     * Helper Method: Find Maximum of Minimums for Given Window Size
     * Uses sliding window with deque to track minimums efficiently
     */
    public static int maxOfMinSlidingWindow(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        
        // Process each element
        for (int i = 0; i < nums.length; i++) {
            // Remove elements outside current window
            if (!deque.isEmpty() && deque.getFirst() <= (i - k)) {
                deque.removeFirst();
            }

            // Remove larger elements as they can't be minimum
            while (!deque.isEmpty() && nums[deque.getLast()] > nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);  // Add current element

            // Update maximum of minimums for current window
            if (i >= k - 1)
                max = Math.max(max, nums[deque.getFirst()]);
        }
        return max;
    }
}
