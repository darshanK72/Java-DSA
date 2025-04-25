/**
 * Problem Statement:
 *     LeetCode 503. Next Greater Element II
 * 
 *     Given a circular array arr[] of size N, find the next greater element for each
 *     element. In a circular array, we consider the first element as next to the
 *     last element. The Next greater Element for an element x is the first greater
 *     element on its right side (circular). If no greater element exists, output -1.
 * 
 * Input:
 *     - An array arr[] of size N (1 ≤ N ≤ 10^4)
 *     - Array elements are in the range of -10^9 to 10^9
 * 
 * Output:
 *     - An array containing the next greater element for each element
 * 
 * Example:
 *     Input: arr[] = [4, 5, 2, 10, 8]
 *     Output: [5, 10, 10, -1, 10]
 */

import java.util.Arrays;
import java.util.Stack;

public class j02NextGreaterElementII {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[][] testCases = {
            {4, 5, 2, 10, 8},       // Basic case with mixed elements
            {1, 2, 3, 4, 5},        // Increasing sequence
            {5, 4, 3, 2, 1},        // Decreasing sequence
            {1, 1, 1, 1},           // All same elements
            {1},                     // Single element
            {1, 2},                 // Two elements
            {5, 4, 3, 2, 1, 6}     // Complex circular case
        };

        // Test both approaches for each test case
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Two Loops: " + Arrays.toString(nextGreaterElementsTwoLoops(testCases[i])));
            System.out.println("One Loop:  " + Arrays.toString(nextGreaterElementsOneLoop(testCases[i])));
            System.out.println();
        }
    }

    /**
     * Approach 1: Using Two Loops
     * 
     * Intuition:
     * - Since the array is circular, we need to check elements twice
     * - First loop builds initial next greater elements
     * - Second loop handles circular nature by checking remaining elements
     * - We use a monotonic stack to maintain decreasing order of elements
     * 
     * Time Complexity: O(n)
     * - First loop iterates through array once: O(n)
     * - Second loop iterates through array once: O(n)
     * - While each loop is O(n), each element can only be pushed and popped once
     *   from the stack across all operations
     * - Total operations = n (pushes) + n (pops) = O(n)
     * 
     * Space Complexity: O(n)
     * - Output array: O(n) space
     * - Stack: O(n) space in worst case (when array is in decreasing order)
     * - Example: [5,4,3,2,1] - all elements will be stored in stack
     */
    public static int[] nextGreaterElementsTwoLoops(int[] arr) {
        // Initialize result array with -1 as default value
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, -1);
        
        // Stack to store indices of elements
        Stack<Integer> stack = new Stack<>();

        // First pass: Find next greater elements without circular property
        for (int i = 0; i < arr.length; i++) {
            // Pop elements smaller than current element
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                outArr[stack.pop()] = arr[i];
            }
            // Push current element's index
            stack.push(i);
        }

        // Second pass: Handle circular property
        for (int i = 0; i < arr.length; i++) {
            // Pop elements smaller than current element
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                outArr[stack.pop()] = arr[i];
            }
        }
        return outArr;
    }

    /**
     * Approach 2: Using Single Loop with Modulo
     * 
     * Intuition:
     * - Instead of two loops, we simulate circular array using modulo
     * - We iterate 2n times to cover all possible circular combinations
     * - Using modulo ensures we wrap around the array correctly
     * 
     * Time Complexity: O(n)
     * - While loop appears to be O(2n), we still only push elements once
     * - Each element is pushed exactly once (during first n iterations)
     * - Each element can be popped at most once
     * - Total operations = n (pushes) + n (pops) = O(n)
     * 
     * Space Complexity: O(n)
     * - Output array: O(n) space
     * - Stack: O(n) space in worst case (when array is in decreasing order)
     * - Example: [5,4,3,2,1] - all elements will be stored in stack
     * - No additional space needed for modulo operation
     */
    public static int[] nextGreaterElementsOneLoop(int[] arr) {
        // Initialize result array with -1 as default value
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, -1);
        
        // Stack to store indices of elements
        Stack<Integer> stack = new Stack<>();

        // Single pass: Iterate 2n times to handle circular property
        for (int i = 0; i < 2 * arr.length; i++) {
            // Get actual array index using modulo
            int idx = i % arr.length;
            
            // Pop elements smaller than current element
            while (!stack.isEmpty() && arr[stack.peek()] < arr[idx]) {
                outArr[stack.pop()] = arr[idx];
            }
            // Only push in first n iterations to avoid duplicates
            if (i < arr.length) {
                stack.push(idx);
            }
        }
        return outArr;
    }
}
