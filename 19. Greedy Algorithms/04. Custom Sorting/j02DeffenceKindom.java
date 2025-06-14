/**
 * LeetCode 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 * 
 * Problem Statement:
 *     Given a rectangular cake with height h and width w, and two arrays of integers
 *     horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from
 *     the top of the rectangular cake to the ith horizontal cut and similarly,
 *     verticalCuts[j] is the distance from the left of the rectangular cake to the
 *     jth vertical cut. Return the maximum area of a piece of cake after you cut at
 *     each horizontal and vertical position provided in the arrays horizontalCuts
 *     and verticalCuts.
 * 
 * Input:
 *     - h (int): Height of the cake (1 <= h <= 10^9)
 *     - w (int): Width of the cake (1 <= w <= 10^9)
 *     - horizontalCuts (int[]): Array of horizontal cut positions
 *     - verticalCuts (int[]): Array of vertical cut positions
 *     - 1 <= horizontalCuts.length <= h-1
 *     - 1 <= verticalCuts.length <= w-1
 *     - 1 <= horizontalCuts[i] < h
 *     - 1 <= verticalCuts[i] < w
 * 
 * Output:
 *     - Maximum area of a piece of cake after all cuts
 *     - Return the result modulo 10^9 + 7
 * 
 * Example:
 *     Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 *     Output: 4
 * 
 *     Explanation:
 *     The cake is cut as follows:
 *     - Horizontal cuts at heights 1, 2, and 4
 *     - Vertical cuts at widths 1 and 3
 *     The maximum area piece has dimensions 2x2 = 4
 */

import java.util.Arrays;

public class j02DeffenceKindom {
    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - To find the maximum area, we need to find the maximum height and width
     *   between any two consecutive cuts
     * - The maximum area will be the product of maximum height and maximum width
     * - We need to consider the edges of the cake (0 to h and 0 to w) as cuts
     * - Sorting the cuts helps us easily find consecutive cut positions
     * 
     * Explanation:
     * 1. Sort both horizontal and vertical cuts
     * 2. Find maximum height by checking distances between consecutive horizontal cuts
     *    and the edges (0 to first cut and last cut to h)
     * 3. Find maximum width by checking distances between consecutive vertical cuts
     *    and the edges (0 to first cut and last cut to w)
     * 4. Multiply maximum height and width to get maximum area
     * 5. Return result modulo 10^9 + 7
     * 
     * Time Complexity: O(n log n + m log m) where n and m are lengths of
     *                  horizontalCuts and verticalCuts respectively
     *                  Due to sorting operations
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param h              Height of the cake
     * @param w              Width of the cake
     * @param horizontalCuts Array of horizontal cut positions
     * @param verticalCuts   Array of vertical cut positions
     * @return              Maximum area of a piece of cake modulo 10^9 + 7
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // Sort both arrays to find consecutive cuts
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        // Find maximum height between consecutive horizontal cuts
        long maxHeight = -1;
        long maxWidth = -1;
        int i = 0;
        int s = 0;
        for (; i < horizontalCuts.length; i++) {
            maxHeight = Math.max(maxHeight, horizontalCuts[i] - s);
            s = horizontalCuts[i];
        }
        // Check the last segment (from last cut to bottom edge)
        maxHeight = Math.max(maxHeight, h - s);

        // Find maximum width between consecutive vertical cuts
        i = 0;
        s = 0;
        for (; i < verticalCuts.length; i++) {
            maxWidth = Math.max(maxWidth, verticalCuts[i] - s);
            s = verticalCuts[i];
        }
        // Check the last segment (from last cut to right edge)
        maxWidth = Math.max(maxWidth, w - s);

        // Return the product modulo 10^9 + 7
        return (int) (maxHeight * maxWidth % 1000000007);
    }

    public static void main(String[] args) {
        j02DeffenceKindom solution = new j02DeffenceKindom();

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int h1 = 5, w1 = 4;
        int[] horizontalCuts1 = {1, 2, 4};
        int[] verticalCuts1 = {1, 3};
        System.out.println("Input: h=" + h1 + ", w=" + w1 + 
                         ", horizontalCuts=" + Arrays.toString(horizontalCuts1) +
                         ", verticalCuts=" + Arrays.toString(verticalCuts1));
        System.out.println("Expected: 4, Output: " + 
                         solution.maxArea(h1, w1, horizontalCuts1, verticalCuts1));

        // Test Case 2: Single cut in each direction
        System.out.println("\nSingle Cut Test Case:");
        int h2 = 5, w2 = 4;
        int[] horizontalCuts2 = {3};
        int[] verticalCuts2 = {3};
        System.out.println("Input: h=" + h2 + ", w=" + w2 + 
                         ", horizontalCuts=" + Arrays.toString(horizontalCuts2) +
                         ", verticalCuts=" + Arrays.toString(verticalCuts2));
        System.out.println("Expected: 9, Output: " + 
                         solution.maxArea(h2, w2, horizontalCuts2, verticalCuts2));

        // Test Case 3: No cuts
        System.out.println("\nNo Cuts Test Case:");
        int h3 = 5, w3 = 4;
        int[] horizontalCuts3 = {};
        int[] verticalCuts3 = {};
        System.out.println("Input: h=" + h3 + ", w=" + w3 + 
                         ", horizontalCuts=" + Arrays.toString(horizontalCuts3) +
                         ", verticalCuts=" + Arrays.toString(verticalCuts3));
        System.out.println("Expected: 20, Output: " + 
                         solution.maxArea(h3, w3, horizontalCuts3, verticalCuts3));
    }
}
