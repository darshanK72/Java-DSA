/**
 * Problem Statement:
 * 
 *     Given an array `nums` of integers, you need to find the minimum starting value such that the running sum of the array never becomes less than 1. The running sum starts with the minimum starting value, and after adding each element of the array, the running sum is updated.
 *     If at any point the running sum is less than 1, the starting value must be increased to ensure the running sum stays positive.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array `nums`.
 *     - An array `nums` of size `n` where each element satisfies (-10^5 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the minimum starting value to ensure the running sum never drops below 1.
 * 
 * Example:
 *     Input:
 *     5
 *     -3 2 -3 4 2
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The running sum starts with 5:
 *     - First change: 5 + (-3) = 2
 *     - Second change: 2 + 2 = 4
 *     - Third change: 4 + (-3) = 1
 *     - Fourth change: 1 + 4 = 5
 *     - Fifth change: 5 + 2 = 7
 *     The minimum starting value to keep the running sum always positive is 5.
 */

import java.util.Scanner;

public class j04PositiveStepSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the nums array
        int[] arr = new int[n]; // Array to store the elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each number into the array
        }

        // Call the method to compute the minimum starting value
        System.out.println(minStartValue(arr));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Greedy Approach
     * 
     * Intuition:
     * - We want the running sum to never become less than 1. Hence, we keep track of the running sum and adjust the starting value as needed.
     * - At each step, we check if the running sum goes below 1. If it does, we increase the starting value to bring it back to at least 1.
     * - We iterate through the array, and if the running sum becomes negative, we adjust the starting value accordingly.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array. We are iterating through the array once.
     * 
     * Space Complexity:
     * - O(1), because we only need a few variables to store the current sum and the starting value.
     * 
     * @param nums The array of integers representing the changes in the running sum.
     * @return The minimum starting value.
     */
    public static int minStartValue(int[] nums) {
        int ans = 1; // Initialize the minimum starting value to 1
        int tempSum = 1; // Initialize the temporary sum to the starting value

        for (int i = 0; i < nums.length; i++) {
            tempSum += nums[i]; // Update the temporary sum by adding the current element
            if (tempSum <= 0) {
                // If the temporary sum goes below or equals 0, we need to increase the starting
                // value
                ans += (1 - tempSum); // Increase the starting value so that the sum is at least 1
                tempSum = 1; // Reset the temporary sum to 1
            }
        }

        return ans; // Return the computed minimum starting value
    }
}
