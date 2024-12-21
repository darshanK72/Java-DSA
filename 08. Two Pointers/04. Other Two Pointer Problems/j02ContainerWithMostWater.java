/**
 * Problem Statement:
 * 
 *     You are given an array of non-negative integers `height` where each element represents the height of a vertical line at that position. 
 *     Find the maximum area of water that can be contained between two lines on the array. The area between two lines is calculated as:
 *     
 *         area = min(height[i], height[j]) * (j - i)
 *     
 *     where `i` and `j` are the indices of the two lines.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^5), the number of elements in the array.
 *     - An array `height` of size `n` where each element satisfies (0 <= height[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the maximum area of water that can be contained.
 * 
 * Example:
 *     Input:
 *     height = {1, 8, 6, 2, 5, 4, 8, 3, 7}
 *     
 *     Output:
 *     49
 * 
 *     Explanation:
 *     The maximum area is between the lines at indices 1 and 8, with heights 8 and 7, respectively.
 *     The area is 7 * (8 - 1) = 49.
 */

import java.util.Scanner;

public class j02ContainerWithMostWater {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        // Read the input array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Call the method to compute the result
        System.out.println(maxArea(arr));

        in.close();
    }

    /**
     * Approach: Two-pointer Solution (O(n))
     * 
     * Intuition:
     * - We use two pointers: one at the start (`s`) and one at the end (`e`) of the array.
     * - Calculate the area between the lines at these two pointers using the formula:
     *     area = min(height[s], height[e]) * (e - s)
     * - If the height at `s` is smaller, move the left pointer to the right (increase `s`).
     * - If the height at `e` is smaller, move the right pointer to the left (decrease `e`).
     * - Continue this process while `s` is less than `e`, keeping track of the maximum area.
     * - The idea is to move the pointer pointing to the smaller height, as increasing the width won't help if the height is small.
     * 
     * Time Complexity:
     * - O(n), as we are only iterating through the array once.
     * 
     * Space Complexity:
     * - O(1), as we only use a few integer variables.
     * 
     * @param height The input array representing the heights of the bars.
     * @return The maximum area that can be contained.
     */
    public static int maxArea(int[] height) {
        int n = height.length;
        int s = 0;
        int e = n - 1;
        int ans = 0;

        // Use two pointers to find the maximum area
        while (s < e) {
            // Calculate the area between the two pointers
            ans = Math.max(ans, Math.min(height[s], height[e]) * (e - s));

            // Move the pointer corresponding to the smaller height
            if (height[s] < height[e]) {
                s++;
            } else {
                e--;
            }
        }
        return ans;
    }
}
