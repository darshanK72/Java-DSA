/**
 * Problem Statement:
 * 
 *     Given an array `arr` representing the elevation of bars, where the width of each bar is 1,
 *     compute the amount of water that can be trapped between these bars after raining.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the number of elements in the array.
 *     - An array `arr` of size `n`, where each element satisfies (0 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the total amount of water that can be trapped.
 * 
 * Example:
 *     Input:
 *     arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
 *     
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The water trapped is represented by the vertical bars and the water will be trapped
 *     between bars of heights 2 and 3, 3 and 1, 1 and 2, etc. The total amount of trapped water is 6.
 */

import java.util.Scanner;

public class j01TrappingWater {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        // Read the input array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Call and print the result of different approaches
        System.out.println(trapedWater(arr)); // O(n^2)
        System.out.println(trapedWaterPrefixSuffix(arr)); // O(n)
        System.out.println(trapRainWaterEfficient(arr)); // O(n)

        in.close();
    }

    /**
     * Approach 1: Brute Force Solution (O(n^2))
     * 
     * Intuition:
     * - For each bar at index i, we need to find the maximum height to its left and right.
     * - The trapped water above the bar is determined by the minimum of the left and right 
     *   maximum heights minus the height of the bar itself.
     * - This method uses two nested loops to calculate the maximum height on both sides for each 
     *   element, leading to a time complexity of O(n^2).
     * 
     * Time Complexity:
     * - O(n^2), as there are two nested loops to calculate left and right maximum heights.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space for variables.
     * 
     * @param arr The input array representing the heights of the bars.
     * @return The total amount of water that can be trapped.
     */
    public static int trapedWater(int[] arr) {
        int result = 0;
        int n = arr.length;

        // Iterate through each bar
        for (int i = 1; i < arr.length - 1; i++) {
            int lmax = arr[i];
            // Find the maximum height to the left of the current bar
            for (int j = 0; j < i; j++) {
                if (arr[j] > lmax)
                    lmax = arr[j];
            }

            int rmax = arr[i];
            // Find the maximum height to the right of the current bar
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > rmax)
                    rmax = arr[j];
            }

            // Calculate the trapped water for the current bar
            result += Math.min(lmax, rmax) - arr[i];
        }

        return result;
    }

    /**
     * Approach 2: Prefix-Suffix Solution (O(n))
     * 
     * Intuition:
     * - This approach uses two arrays, `lMax` and `rMax`, to store the maximum height 
     *   encountered from the left and right sides respectively for each bar.
     * - The trapped water above each bar can then be calculated by subtracting the bar's 
     *   height from the minimum of `lMax[i]` and `rMax[i]`.
     * - This method reduces the time complexity to O(n), as we only need three linear passes 
     *   through the array (one for building `lMax`, one for `rMax`, and one for calculating the water).
     * 
     * Time Complexity:
     * - O(n), as we only make three linear passes through the array.
     * 
     * Space Complexity:
     * - O(n), as we use two extra arrays (`lMax` and `rMax`) to store the maximum heights.
     * 
     * @param arr The input array representing the heights of the bars.
     * @return The total amount of water that can be trapped.
     */
    public static int trapedWaterPrefixSuffix(int[] arr) {
        int result = 0;
        int n = arr.length;

        // Create arrays to store the left and right maximum heights
        int[] lMax = new int[n];
        int[] rMax = new int[n];

        // Fill the left maximum heights
        lMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            lMax[i] = Math.max(arr[i], lMax[i - 1]);
        }

        // Fill the right maximum heights
        rMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(arr[i], rMax[i + 1]);
        }

        // Calculate the trapped water by taking the minimum of the left and right max
        for (int i = 1; i < n - 1; i++) {
            result += Math.min(lMax[i], rMax[i]) - arr[i];
        }
        return result;
    }

    /**
     * Approach 3: Efficient Two-pointer Solution (O(n))
     * 
     * Intuition:
     * - This approach uses two pointers, one at the start (`s`) and one at the end (`e`) of the array.
     * - We keep track of the maximum height seen from the left (`leftMax`) and right (`rightMax`).
     * - The water trapped depends on which side has the smaller height, so we move the pointers accordingly.
     * - If the left height is smaller, we check if the current height is greater than `leftMax` 
     *   or calculate the trapped water if it's smaller.
     * - This approach only requires a single pass through the array, making it efficient.
     * 
     * Time Complexity:
     * - O(n), as we only pass through the array once.
     * 
     * Space Complexity:
     * - O(1), as we only use a few integer variables for tracking heights and the total water.
     * 
     * @param height The input array representing the heights of the bars.
     * @return The total amount of water that can be trapped.
     */
    public static int trapRainWaterEfficient(int[] height) {
        int n = height.length;
        int s = 0;
        int e = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        // Use two pointers to calculate trapped water
        while (s < e) {
            if (height[s] < height[e]) {
                if (height[s] >= leftMax) {
                    leftMax = height[s];
                } else {
                    water += (leftMax - height[s]);
                }
                s++;
            } else {
                if (height[e] >= rightMax) {
                    rightMax = height[e];
                } else {
                    water += (rightMax - height[e]);
                }
                e--;
            }
        }
        return water;
    }
}
