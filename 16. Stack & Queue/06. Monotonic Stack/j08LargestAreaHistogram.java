/**
 * Problem Statement:
 *     LeetCode 84. Largest Rectangle in Histogram
 * 
 *     Given an array of integers heights representing the histogram's bar height
 *     where the width of each bar is 1, return the area of the largest rectangle
 *     in the histogram.
 * 
 * Input:
 *     - Array heights where 1 <= heights.length <= 10^5
 *     - 0 <= heights[i] <= 10^4
 * 
 * Output:
 *     - Integer representing the area of largest rectangle
 * 
 * Example:
 *     Input: heights = [2,1,5,6,2,3]
 *     Output: 10
 *     
 *     Explanation:
 *     - The largest rectangle has area = 10 units
 *     - Can be achieved using the bars at index 2 and 3 (heights 5 and 6)
 *     - Width = 2, height = 5, thus area = 2 * 5 = 10
 */

import java.util.Arrays;
import java.util.Stack;

public class j08LargestAreaHistogram {

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {2, 1, 5, 6, 2, 3},    // Basic case
            {2, 4},                 // Two bars
            {2, 1, 2},             // Valley shape
            {1, 1, 1, 1},          // Equal heights
            {1},                    // Single bar
            {4, 2, 0, 3, 2, 5}     // Complex case with zero
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Heights: " + Arrays.toString(testCases[i]));
            System.out.println("Brute Force: " + largestRectangleArea(testCases[i].clone()));
            System.out.println("Optimized: " + largestRectangleAreaEfficient(testCases[i].clone()));
            System.out.println();
        }
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - For each bar, expand horizontally in both directions
     * - Keep expanding while adjacent bars are >= current bar
     * - Calculate area using width * height of current bar
     * 
     * Time Complexity: O(n²)
     * - For each bar, we may need to scan entire array
     * - n bars * n possible expansions = O(n²)
     * 
     * Space Complexity: O(1)
     * - Only using constant extra space
     */
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        
        // Check each bar as potential height of rectangle
        for (int i = 0; i < heights.length; i++) {
            int area = heights[i];
            
            // Expand right while possible
            int j = i + 1;
            while (j < heights.length && heights[j] >= heights[i]) {
                area += heights[i];
                j++;
            }
            
            // Expand left while possible
            j = i - 1;
            while (j >= 0 && heights[j] >= heights[i]) {
                area += heights[i];
                j--;
            }
            
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /**
     * Approach 2: Using Monotonic Stack
     * 
     * Intuition:
     * - For each bar, we need its next smaller and previous smaller bar
     * - These define the boundaries of rectangle with current bar as height
     * - Use monotonic stack to find these boundaries efficiently
     * - Area = height * (right_boundary - left_boundary - 1)
     * 
     * Time Complexity: O(n)
     * - Three passes through array: O(n) each
     * - Each element pushed/popped once in each stack operation
     * 
     * Space Complexity: O(n)
     * - Two arrays for boundaries: O(n)
     * - Stack space: O(n)
     */
    public static int largestRectangleAreaEfficient(int[] heights) {
        // Find boundaries for each bar
        int[] nextSmaller = getNextSmallerElements(heights);
        int[] prevSmaller = getPrevSmallerElements(heights);
        
        int maxArea = 0;
        // Calculate area for each bar
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, 
                heights[i] * (nextSmaller[i] - prevSmaller[i] - 1));
        }
        return maxArea;
    }

    /**
     * Helper Method: Find next smaller element for each bar
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

    /**
     * Helper Method: Find previous smaller element for each bar
     */
    private static int[] getPrevSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, -1);  // Default: no smaller element found
        
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }
}
