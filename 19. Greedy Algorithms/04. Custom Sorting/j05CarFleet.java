/**
 * LeetCode: Car Fleet
 * 
 * Problem Statement:
 *     There are n cars going to the same destination along a one-lane road. The destination
 *     is target miles away. You are given two integer arrays position and speed, both of
 *     length n, where position[i] is the position of the ith car and speed[i] is the speed
 *     of the ith car (in miles per hour).
 * 
 *     A car can never pass another car ahead of it, but it can catch up to it and drive
 *     bumper to bumper at the same speed. The faster car will slow down to match the
 *     slower car's speed. The distance between these two cars is ignored (i.e., they are
 *     assumed to have the same position).
 * 
 *     A car fleet is some non-empty set of cars driving at the same position and same
 *     speed. Note that a single car is also a car fleet.
 * 
 *     Return the number of car fleets that will arrive at the destination.
 * 
 * Input:
 *     - target (int): Destination position in miles
 *     - position[] (int[]): Array of car positions
 *     - speed[] (int[]): Array of car speeds
 *     - 1 <= n <= 10^5
 *     - 0 <= position[i] < target <= 10^6
 *     - 0 < speed[i] <= 10^6
 * 
 * Output:
 *     - Number of car fleets that will arrive at the destination
 * 
 * Example:
 *     Input: 
 *     target = 12
 *     position = [10,8,0,5,3]
 *     speed = [2,4,1,1,3]
 * 
 *     Output: 3
 * 
 *     Explanation:
 *     The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 *     The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 *     The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 *     Note that no other cars meet these fleets before the destination, so the answer is 3.
 */

import java.util.Arrays;

public class j05CarFleet {
    /**
     * Approach: Sort by Position and Calculate Time to Target
     * 
     * Intuition:
     * - Cars that are closer to target and slower will form fleets
     * - If a car behind can reach the target before the car in front,
     *   they will form a fleet
     * - We need to process cars from closest to target to farthest
     * 
     * Explanation:
     * 1. Sort cars by their position in descending order
     * 2. For each car, calculate time to reach target
     * 3. If current car's time > previous car's time, it forms a new fleet
     * 4. Keep track of maximum time seen so far
     * 
     * Time Complexity: O(n log n) where n is the number of cars
     *                  Due to sorting operation
     * Space Complexity: O(n) for storing indices
     * 
     * @param target   Destination position in miles
     * @param position Array of car positions
     * @param speed    Array of car speeds
     * @return Number of car fleets
     */
    public static int carFleet(int target, int[] position, int[] speed) {
        // Create array of indices for sorting
        Integer[] indices = new Integer[position.length];
        for (int i = 0; i < position.length; i++)
            indices[i] = i;

        // Sort indices by position in descending order
        Arrays.sort(indices, (a, b) -> position[b] - position[a]);

        // Initialize fleets count and previous maximum time
        int fleets = 1;
        double prevMaxTime = ((double) target - position[indices[0]]) / speed[indices[0]];

        // Process each car
        for (int i = 1; i < indices.length; i++) {
            // Calculate time for current car to reach target
            double time = ((double) target - position[indices[i]]) / speed[indices[i]];
            
            // If current car takes longer than previous fleet, it forms a new fleet
            if (prevMaxTime < time) {
                fleets++;
            }
            
            // Update maximum time seen so far
            prevMaxTime = Math.max(prevMaxTime, time);
        }

        return fleets;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nTest Case 1: Basic case");
        int target1 = 12;
        int[] position1 = {10, 8, 0, 5, 3};
        int[] speed1 = {2, 4, 1, 1, 3};
        System.out.println("Input: target = " + target1);
        System.out.println("position = " + Arrays.toString(position1));
        System.out.println("speed = " + Arrays.toString(speed1));
        System.out.println("Output: " + carFleet(target1, position1, speed1));
        System.out.println("Expected: 3");

        // Test Case 2: All cars form one fleet
        System.out.println("\nTest Case 2: All cars form one fleet");
        int target2 = 10;
        int[] position2 = {3, 5, 7};
        int[] speed2 = {3, 2, 1};
        System.out.println("Input: target = " + target2);
        System.out.println("position = " + Arrays.toString(position2));
        System.out.println("speed = " + Arrays.toString(speed2));
        System.out.println("Output: " + carFleet(target2, position2, speed2));
        System.out.println("Expected: 1");

        // Test Case 3: Each car is a separate fleet
        System.out.println("\nTest Case 3: Each car is a separate fleet");
        int target3 = 100;
        int[] position3 = {0, 2, 4};
        int[] speed3 = {4, 2, 1};
        System.out.println("Input: target = " + target3);
        System.out.println("position = " + Arrays.toString(position3));
        System.out.println("speed = " + Arrays.toString(speed3));
        System.out.println("Output: " + carFleet(target3, position3, speed3));
        System.out.println("Expected: 3");

        // Test Case 4: Edge case - single car
        System.out.println("\nTest Case 4: Edge case - single car");
        int target4 = 10;
        int[] position4 = {0};
        int[] speed4 = {1};
        System.out.println("Input: target = " + target4);
        System.out.println("position = " + Arrays.toString(position4));
        System.out.println("speed = " + Arrays.toString(speed4));
        System.out.println("Output: " + carFleet(target4, position4, speed4));
        System.out.println("Expected: 1");
    }
}