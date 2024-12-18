/**
 * Problem Statement:
 * 
 *     You are given an array `gain` of integers where each element represents the change in altitude at that particular index.
 *     The starting altitude is 0. For each element in the `gain` array, the altitude changes by the corresponding value. 
 *     Your task is to find the highest altitude reached during the journey, which is the maximum altitude encountered after all the changes are applied.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number of altitude changes.
 *     - An array `gain` of size `n` where each element represents the change in altitude. ( -100 <= gain[i] <= 100)
 * 
 * Output:
 *     - An integer representing the highest altitude reached.
 * 
 * Example:
 *     Input:
 *     5
 *     -5 1 5 0 -7
 *     Output:
 *     1
 * 
 *     Explanation:
 *     Start with an altitude of 0:
 *     - First change: 0 + (-5) = -5
 *     - Second change: -5 + 1 = -4
 *     - Third change: -4 + 5 = 1
 *     - Fourth change: 1 + 0 = 1
 *     - Fifth change: 1 + (-7) = -6
 *     The highest altitude reached is 1.
 */

import java.util.Scanner;

public class j03HighestAltitude {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the gain array
        int[] gain = new int[n]; // Array to store the altitude gains
        for (int i = 0; i < n; i++) {
            gain[i] = in.nextInt(); // Read each change in altitude into the array
        }

        // Call the method to compute the highest altitude reached
        System.out.println(largestAltitude(gain));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Tracking the Maximum Altitude
     * 
     * Intuition:
     * - The altitude starts at 0. We then iterate through the `gain` array and update the current altitude at each step.
     * - After each update, we check if the current altitude is the highest so far.
     * - By keeping track of the highest altitude encountered during the journey, we can determine the result.
     * 
     * Time Complexity:
     * - O(n), because we are iterating over the `gain` array once.
     * 
     * Space Complexity:
     * - O(1), since we only need a few variables to store the current and highest altitudes.
     * 
     * @param gain The array of altitude changes.
     * @return The highest altitude reached.
     */
    public static int largestAltitude(int[] gain) {
        int highestGain = 0; // To track the highest altitude reached
        int tempGain = 0; // To track the current altitude
        for (int i = 0; i < gain.length; i++) {
            tempGain += gain[i]; // Update the current altitude by adding the change
            if (tempGain > highestGain) {
                highestGain = tempGain; // Update the highest altitude if the current one is higher
            }
        }
        return highestGain; // Return the highest altitude
    }
}
