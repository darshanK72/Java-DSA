/**
 * Problem Statement:
 * 
 *     Given an integer array `arr` representing heights of bars in a bar chart, display the bar chart 
 *     vertically using asterisks (`*`). Each bar in the chart corresponds to an element in the array, 
 *     where the value of the element represents the height of the bar.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of bars.
 *     - An array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5), representing 
 *       the heights of the bars.
 * 
 * Output:
 *     - Print the bar chart representation, where each row contains either an asterisk (`*`) 
 *       or a blank space (` `) for each bar.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [3, 1, 4, 5, 2]
 *     Output:
 *             *   
 *           * *   
 *       *   * *   
 *       *   * * * 
 *       * * * * *
 * 
 *     Explanation:
 *         - The array represents bars of heights 3, 1, 4, 5, and 2 respectively.
 *         - Each row corresponds to one level of the chart, starting from the tallest bar (row 1).
 */

import java.util.Scanner;

public class j09BarChart {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of bars
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: heights of the bars
        }

        // Call the bar chart generation method
        barChart(arr);

        in.close();
    }

    /**
     * Approach: Vertical Bar Chart Generation
     * 
     * Intuition:
     * - A bar chart is generated row by row, starting from the tallest bar.
     * - To determine whether a specific bar should display an asterisk in a row, check if the current
     *   level (starting from the maximum height) is less than or equal to the bar's height.
     * 
     * Explanation:
     * 1. Find the maximum height of the bars (`max`) to determine the number of rows.
     * 2. Iterate from the topmost row (height `max`) to the bottommost row (height `1`).
     * 3. For each row, check each bar:
     *    - If the current level is less than or equal to the bar's height, print `*`.
     *    - Otherwise, print a blank space (` `).
     * 4. Move to the next row until all rows are processed.
     * 
     * Time Complexity:
     * - O(n + max), where `n` is the number of bars, and `max` is the maximum height of the bars.
     *   - Finding the maximum height requires O(n).
     *   - Printing the chart requires O(n * max).
     * 
     * Space Complexity:
     * - O(1), as we do not use any extra space other than variables.
     * 
     * @param arr The array representing the heights of the bars.
     */
    public static void barChart(int[] arr) {
        // Step 1: Find the maximum height of the bars
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Step 2: Generate the bar chart row by row
        for (int level = max; level > 0; level--) {
            for (int j = 0; j < arr.length; j++) {
                if (level <= arr[j]) {
                    System.out.print("* "); // Print asterisk if the bar height is sufficient
                } else {
                    System.out.print("  "); // Print blank space otherwise
                }
            }
            System.out.println(); // Move to the next row
        }
    }
}
