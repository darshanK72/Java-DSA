/**
 * Problem Statement:
 *     First Negative Integer in Every Window of Size K
 * 
 *     Given an array arr[] of size N and a positive integer K, find the first 
 *     negative integer for each and every window(contiguous subarray) of size K.
 *     If a window has no negative element, output 0 for that window.
 * 
 * Input:
 *     - Array arr[] where 1 <= N <= 10^5
 *     - Window size K where 1 <= K <= N
 *     - -10^5 <= arr[i] <= 10^5
 * 
 * Output:
 *     - List containing first negative integer in each window
 * 
 * Example:
 *     Input: arr[] = [-8, 2, 3, -6, 10], K = 2
 *     Output: [-8, 0, -6]
 *     
 *     Explanation:
 *     First window [-8, 2]: first negative = -8
 *     Second window [2, 3]: no negative = 0
 *     Third window [3, -6]: first negative = -6
 *     Fourth window [-6, 10]: first negative = -6
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j12FirstNegativeInSlidingWindow {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[][] testArrays = {
            {-8, 2, 3, -6, 10},      // Basic case
            {12, -1, -7, 8, -15, 30, 16, 28},  // Multiple negatives
            {1, 2, 3, 4, 5},         // No negatives
            {-1, -2, -3, -4},        // All negatives
            {0, 0, 0}                // All zeros
        };
        int[] testWindows = {2, 3, 2, 2, 2};

        // Test each case
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Array: " + Arrays.toString(testArrays[i]));
            System.out.println("Window Size: " + testWindows[i]);
            System.out.println("First Negatives: " + 
                firstNegInt(testArrays[i], testWindows[i]));
            System.out.println();
        }
    }

    /**
     * Approach: Using Deque (Double-ended Queue)
     * 
     * Intuition:
     * - Use deque to store indices of negative numbers
     * - Remove indices outside current window
     * - First element in deque is index of first negative in window
     * - If deque empty, no negative in current window
     * 
     * Time Complexity: O(n)
     * - Single pass through array
     * - Each element processed exactly once
     * - Deque operations are O(1)
     * 
     * Space Complexity: O(k)
     * - Deque can store at most k indices
     * - Output list stores (n-k+1) elements
     */
    public static List<Integer> firstNegInt(int arr[], int k) {
        ArrayList<Integer> out = new ArrayList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            if (!deque.isEmpty() && deque.getFirst() <= (i - k)) {
                deque.removeFirst();
            }

            if (arr[i] < 0)
                deque.addLast(i);

            if (i >= k - 1) {
                if (deque.isEmpty())
                    out.add(0);
                else
                    out.add(arr[deque.getFirst()]);
            }
        }
        return out;
    }
}
