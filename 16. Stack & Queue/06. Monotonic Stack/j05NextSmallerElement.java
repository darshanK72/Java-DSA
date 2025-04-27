/**
 * Problem Statement:
 *     Next Smaller Element
 * 
 *     Given an array arr[] of size N having elements, find the next smaller element
 *     for each element in the array in order of their appearance. The Next smaller
 *     Element for an element x is the first smaller element on the right side of x
 *     in the array. Elements for which no smaller element exists, consider -1.
 * 
 * Input:
 *     - Array arr[] of size N (1 ≤ N ≤ 10^6)
 *     - Array elements are in the range of 1 to 10^9
 * 
 * Output:
 *     - ArrayList containing next smaller element for each element
 * 
 * Example:
 *     Input: arr[] = [4, 5, 2, 10, 8]
 *     Output: [2, 2, -1, 8, -1]
 *     
 *     Explanation:
 *     - For 4, next smaller is 2
 *     - For 5, next smaller is 2
 *     - For 2, no smaller element exists, so -1
 *     - For 10, next smaller is 8
 *     - For 8, no smaller element exists, so -1
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class j05NextSmallerElement {

    public static void main(String[] args) {
        // Test cases with different scenarios
        int[][] testCases = {
            {4, 5, 2, 10, 8},       // Basic case with mixed elements
            {1, 2, 3, 4, 5},        // Increasing sequence
            {5, 4, 3, 2, 1},        // Decreasing sequence
            {1, 1, 1, 1},           // All same elements
            {1}                      // Single element
        };

        for(int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Left to Right: " + 
                nextSmallerElementLeftToRight(testCases[i]));
            System.out.println("Right to Left: " + 
                nextSmallerElementRightToLeft(testCases[i]));
            System.out.println();
        }
    }

    /**
     * Approach 1: Left to Right Traversal
     * 
     * Intuition:
     * - Use monotonic stack to keep track of elements waiting for next smaller
     * - When finding a smaller element, update all waiting larger elements
     * - Stack maintains elements in increasing order
     * 
     * Time Complexity: O(n)
     * - Single pass through array
     * - Each element pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n elements in worst case
     * - Output array of size n
     */
    public static ArrayList<Integer> nextSmallerElementLeftToRight(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, -1);
        Stack<Integer> stack = new Stack<>();

        // Process all elements from left to right
        for (int i = 0; i < arr.length; i++) {
            // Update elements that found their next smaller
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                outArr[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        // Convert array to ArrayList
        for (int i : outArr) {
            out.add(i);
        }
        return out;
    }

    /**
     * Approach 2: Right to Left Traversal
     * 
     * Intuition:
     * - Process elements from right to left
     * - Stack maintains smaller elements seen so far
     * - For each element, pop larger elements as they can't be next smaller
     * - Stack top (if exists) is the next smaller element
     * 
     * Time Complexity: O(n)
     * - Single pass through array
     * - Each element pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n elements in worst case
     * - Output array of size n
     */
    public static ArrayList<Integer> nextSmallerElementRightToLeft(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, -1);
        Stack<Integer> stack = new Stack<>();
    
        // Process elements from right to left
        for (int i = arr.length - 1; i >= 0; i--) {
            // Remove larger elements as they can't be next smaller
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            // Update if smaller element exists
            if(!stack.isEmpty()) {
                outArr[i] = arr[stack.peek()];
            }
            stack.push(i);
        }

        // Convert array to ArrayList
        for (int i : outArr) {
            out.add(i);
        }
        return out;
    }
}


