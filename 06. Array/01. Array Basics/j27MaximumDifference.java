/**
 * Problem Statement:
 * 
 *     Given an array `arr` of size `n`, find the maximum difference between two elements such that 
 *     the larger element appears after the smaller element in the array. 
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the maximum difference between two elements such that the larger 
 *       element comes after the smaller element in the array.
 * 
 * Example:
 *     Input:
 *         6
 *         2 3 10 6 4 8
 *     Output:
 *         8
 * 
 *     Explanation:
 *         The maximum difference is `10 - 2 = 8` where 10 comes after 2 in the array.
 */

import java.util.Scanner;
import java.lang.Math;

public class j27MaximumDifference {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Output the maximum difference
        System.out.println("Maximum Difference: " + maxDiff(arr));

        in.close();
    }

    /**
     * Approach: Dynamic Tracking of Minimum Value
     * 
     * Intuition:
     *     - To achieve the maximum difference where the larger element appears after the smaller 
     *       one, we can track the minimum value encountered so far while iterating through the array.
     *     - For each element in the array, compute the difference between the current element and 
     *       the minimum value encountered up to that point. Update the result if this difference 
     *       is greater than the current maximum difference.
     *     - This ensures that we are always comparing the current value with the smallest value 
     *       seen before it, satisfying the condition that the larger value must appear later.
     * 
     * Explanation:
     *     - Initialize `result` to store the maximum difference, initially set to the difference 
     *       between the first two elements.
     *     - Initialize `min` to store the minimum value encountered so far, initially set to `arr[0]`.
     *     - For each element starting from index 1:
     *         - Update `result` with the maximum of the current difference (`arr[i] - min`) and 
     *           the previous result.
     *         - Update `min` with the minimum value between `min` and the current element.
     *     - Return the `result` which stores the maximum difference.
     * 
     * Time Complexity:
     *     - O(n), where `n` is the size of the array. This is because we perform a single pass 
     *       through the array.
     * 
     * Space Complexity:
     *     - O(1), as no additional space is used apart from a few variables.
     * 
     * @param arr The input array of integers.
     * @return The maximum difference between two elements such that the larger element 
     *         comes after the smaller element.
     */
    public static int maxDiff(int[] arr) {
        int result = arr[1] - arr[0]; // Initialize the result with the first difference
        int min = arr[0]; // Initialize minimum value to the first element

        // Traverse the array from the second element
        for (int i = 1; i < arr.length; i++) {
            result = Math.max(result, arr[i] - min); // Update result with the maximum difference
            min = Math.min(min, arr[i]); // Update the minimum value
        }

        return result; // Return the maximum difference
    }
}
