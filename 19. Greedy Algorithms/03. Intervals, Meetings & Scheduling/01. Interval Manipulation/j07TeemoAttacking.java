/**
 * LeetCode 495. Teemo Attacking
 * 
 * Problem Statement:
 *     Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo attacks Ashe,
 *     Ashe gets poisoned for a given duration. However, if Teemo attacks again before the
 *     poison effect ends, the timer for it is reset, and the poison effect will end duration
 *     seconds after the new attack. You are given a non-decreasing integer array timeSeries,
 *     where timeSeries[i] denotes that Teemo attacks Ashe at second timeSeries[i], and an
 *     integer duration. Return the total number of seconds that Ashe is poisoned.
 * 
 * Input:
 *     - timeSeries: Array of attack times in ascending order
 *     - duration: Duration of poison effect in seconds
 *     - 1 <= timeSeries.length <= 10^4
 *     - 0 <= timeSeries[i] <= 10^7
 *     - 1 <= duration <= 10^7
 * 
 * Output:
 *     - Total number of seconds Ashe is poisoned
 * 
 * Example:
 *     Input: timeSeries = [1,4], duration = 2
 *     Output: 4
 * 
 *     Explanation:
 *     - At second 1, Teemo attacks and Ashe is poisoned for 2 seconds
 *     - At second 4, Teemo attacks again and Ashe is poisoned for 2 more seconds
 *     - Total poisoned time = 2 + 2 = 4 seconds
 */

public class j07TeemoAttacking {
    /**
     * Approach: Greedy with Time Intervals
     * 
     * Intuition:
     * - The key insight is to calculate the effective duration between attacks
     * - If attacks are far apart, poison lasts for full duration
     * - If attacks are close, poison duration is reset
     * - Last attack always contributes full duration
     * 
     * Explanation:
     * 1. For each attack (except last):
     *    - Calculate time until next attack
     *    - If time < duration, poison is reset
     *    - If time >= duration, poison lasts full duration
     * 2. Add full duration for the last attack
     * 
     * Time Complexity: O(n) where:
     *                  - n is the number of attacks
     *                  - Single pass through array
     * 
     * Space Complexity: O(1) as we only use a few variables
     * 
     * @param timeSeries  Array of attack times in ascending order
     * @param duration    Duration of poison effect in seconds
     * @return           Total number of seconds Ashe is poisoned
     */
    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int totalSeconds = 0;  // Total poisoned time
        
        // Process all attacks except the last one
        for (int i = 0; i < timeSeries.length - 1; i++) {
            // Calculate effective duration until next attack
            int time = Math.min(timeSeries[i + 1] - timeSeries[i], duration);
            totalSeconds += time;
        }

        // Add full duration for the last attack
        return totalSeconds + duration;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with non-overlapping poison
        System.out.println("\nTest Case 1:");
        int[] timeSeries1 = {1, 4};
        System.out.println("Input: timeSeries = " + java.util.Arrays.toString(timeSeries1) + 
                         ", duration = 2");
        System.out.println("Output: " + findPoisonedDuration(timeSeries1, 2));
        System.out.println("Expected: 4");

        // Test Case 2: Overlapping poison
        System.out.println("\nTest Case 2:");
        int[] timeSeries2 = {1, 2};
        System.out.println("Input: timeSeries = " + java.util.Arrays.toString(timeSeries2) + 
                         ", duration = 2");
        System.out.println("Output: " + findPoisonedDuration(timeSeries2, 2));
        System.out.println("Expected: 3");

        // Test Case 3: Single attack
        System.out.println("\nTest Case 3:");
        int[] timeSeries3 = {1};
        System.out.println("Input: timeSeries = " + java.util.Arrays.toString(timeSeries3) + 
                         ", duration = 2");
        System.out.println("Output: " + findPoisonedDuration(timeSeries3, 2));
        System.out.println("Expected: 2");
    }
}
