/**
 * Problem Statement:
 *     LeetCode 85. Maximal Rectangle
 * 
 *     Given a rows x cols binary matrix filled with 0's and 1's, find the largest
 *     rectangle containing only 1's and return its area.
 * 
 * Input:
 *     - Matrix of size m x n where 1 <= row, col <= 200
 *     - Matrix elements are '0' or '1'
 * 
 * Output:
 *     - Integer representing area of largest rectangle
 * 
 * Example:
 *     Input: matrix = [["1","0","1","0","0"],
 *                      ["1","0","1","1","1"],
 *                      ["1","1","1","1","1"],
 *                      ["1","0","0","1","0"]]
 *     Output: 6
 *     
 *     Explanation:
 *     The maximal rectangle is shown in the above grid (marked with x):
 *     [["1","0","1","0","0"],
 *      ["1","0","1","1","1"],
 *      ["1","1","x","x","x"]]
 *     Area = 2 * 3 = 6
 */

import java.util.Arrays;
import java.util.Stack;

public class j11MaximalRectangle {

    public static void main(String[] args) {
        // Test cases with different scenarios
        char[][][] testCases = {
            {   // Basic case
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
            },
            {   // All ones
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
            },
            {   // All zeros
                {'0', '0', '0'},
                {'0', '0', '0'}
            },
            {   // Single element
                {'1'}
            },
            {   // Complex pattern
                {'1', '0', '1'},
                {'1', '1', '1'},
                {'1', '1', '0'}
            }
        };

        // Test each case
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            printMatrix(testCases[i]);
            System.out.println("Maximal Rectangle Area: " + 
                maximalRectangle(testCases[i]));
            System.out.println();
        }
    }

    /**
     * Approach: Convert to Histogram Problem
     * 
     * Intuition:
     * - For each row, treat it as base of histogram
     * - Calculate height of consecutive 1's for each column
     * - Apply largest rectangle in histogram algorithm for each row
     * - Keep track of maximum area found
     * 
     * Time Complexity: O(R * C)
     * - For each row (R), process all columns (C)
     * - Histogram calculation for each row: O(C)
     * 
     * Space Complexity: O(R * C)
     * - Prefix sum array: O(R * C)
     * - Additional arrays for histogram: O(C)
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        // Create prefix sum array for heights
        int[][] prefix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                // Reset sum if current element is 0
                if (matrix[j][i] - '0' == 0) sum = 0;
                sum += matrix[j][i] - '0';
                prefix[j][i] = sum;
            }
        }

        // Find maximum rectangle for each row
        int maxArea = 0;
        for (int i = 0; i < prefix.length; i++) {
            maxArea = Math.max(maxArea, largestRectangleInHistogram(prefix[i]));
        }
        return maxArea;
    }

    /**
     * Helper Method: Calculate Largest Rectangle in Histogram
     * Uses monotonic stack to find boundaries of rectangles
     */
    private static int largestRectangleInHistogram(int[] heights) {
        int[] nextSmaller = getNextSmallerElements(heights);
        int[] prevSmaller = getPrevSmallerElements(heights);

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, 
                heights[i] * (nextSmaller[i] - prevSmaller[i] - 1));
        }
        return maxArea;
    }

    /**
     * Helper Method: Find Next Smaller Elements
     * Uses monotonic stack to find next smaller element for each position
     */
    private static int[] getNextSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, arr.length);

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Helper Method: Find Previous Smaller Elements
     * Uses monotonic stack to find previous smaller element for each position
     */
    private static int[] getPrevSmallerElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[arr.length];
        Arrays.fill(out, -1);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                out[stack.pop()] = i;
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Helper Method: Print Matrix
     * Utility method to display test cases
     */
    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
