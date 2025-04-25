/*-
 * Problem Statement:
 *     LeetCode 907. Sum of Subarray Minimums
 * 
 *     Given an array of integers arr, find the sum of min(b), where b ranges over
 *     every (contiguous) subarray of arr. Since the answer may be large, return
 *     the answer modulo 10^9 + 7.
 * 
 * Input:
 *     - Array arr where 1 <= arr.length <= 3 * 10^4
 *     - 1 <= arr[i] <= 3 * 10^4
 * 
 * Output:
 *     - Integer representing sum of minimums of all subarrays modulo 10^9 + 7
 * 
 * Example:
 *     Input: arr = [3,1,2,4]
 *     Output: 17
 *     
 *     Explanation:
 *     Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]
 *     Minimums are  3,   1,   2,   4,   1,     1,     2,     1,       1,       1
 *     Sum is 3 + 1 + 2 + 4 + 1 + 1 + 2 + 1 + 1 + 1 = 17
 */

import java.util.Arrays;
import java.util.Stack;

public class j10SumOfSubarrayMinimums {

    public static void main(String args[]) {
        // Test cases
        int[][] testCases = {
            {3, 1, 2, 4},          // Basic case
            {1, 2, 3, 4},          // Increasing sequence
            {4, 3, 2, 1},          // Decreasing sequence
            {1, 1, 1, 1},          // Same elements
            {71, 55, 82, 55},      // Repeated elements
            {1}                     // Single element
        };

        // Test implementation
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Array: " + Arrays.toString(testCases[i]));
            System.out.println("Sum of Subarray Minimums: " + 
                sumSubarrayMins(testCases[i]));
            System.out.println();
        }
    }

    /*-
     * Approach: Using Monotonic Stack
     * 
     * Intuition:
     * - For each element, we need to find how many subarrays have it as minimum
     * - This depends on:
     *   * Number of elements greater than it on left (till smaller element)
     *   * Number of elements greater than it on right (till smaller element)
     * - Use monotonic stack to find next and previous smaller elements efficiently
     * - Calculate contribution of each element using these boundaries
     * 
     * Time Complexity: O(n)
     * - Three passes through array: O(n) each
     * - Each element pushed/popped once in stack operations
     * 
     * Space Complexity: O(n)
     * - Two arrays for boundaries: O(n)
     * - Stack space: O(n)
     */
    public static int sumSubarrayMins(int[] arr) {
        // Find boundaries for each element
        int[] nextSmaller = getNextSmallerElements(arr);
        int[] prevSmaller = getPrevSmallerElements(arr);
        
        long total = 0;
        int MOD = 1000000007;

        // Calculate contribution of each element
        for (int i = 0; i < arr.length; i++) {
            int left = prevSmaller[i];
            int right = nextSmaller[i];
            
            // Calculate number of subarrays where current element is minimum
            long count = ((right - left - 1L) * (right - left) / 2)
                      - ((i - left - 1L) * (i - left) / 2)
                      - ((right - i - 1L) * (right - i) / 2);

            // Add contribution to total
            total = (total + (count * arr[i]) % MOD) % MOD;
        }
        
        return (int) (total % MOD);
    }

    /*-
     * Helper Method: Find next smaller elements
     * Uses monotonic stack to find next smaller element for each position
     */
    private static int[] getNextSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, arr.length);  // Default: no smaller element found
        
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /*-
     * Helper Method: Find previous smaller elements
     * Uses monotonic stack to find previous smaller element for each position
     */
    private static int[] getPrevSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, -1);  // Default: no smaller element found
        
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }
}
