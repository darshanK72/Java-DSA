/**
 * LeetCode 1094. Car Pooling
 * 
 * Problem Statement:
 *     There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot
 *     turn around and drive west). You are given the integer capacity and an array trips where
 *     trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi
 *     passengers and the locations to pick them up and drop them off are fromi and toi
 *     respectively. The locations are given as the number of kilometers due east from the
 *     car's initial location. Return true if it is possible to pick up and drop off all
 *     passengers for all the given trips, or false otherwise.
 * 
 * Input:
 *     - trips: 2D array where trips[i] = [numPassengers, from, to]
 *     - capacity: Maximum number of passengers the car can hold
 *     - 1 <= trips.length <= 1000
 *     - 1 <= numPassengers <= 100
 *     - 0 <= from < to <= 1000
 * 
 * Output:
 *     - true if all trips can be completed, false otherwise
 * 
 * Example:
 *     Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 *     Output: true
 * 
 *     Explanation:
 *     - Pick up 2 passengers at location 1
 *     - Drop off 2 passengers at location 5
 *     - Pick up 3 passengers at location 3
 *     - Drop off 3 passengers at location 7
 *     Total passengers never exceeds capacity of 4
 */

import java.util.ArrayList;
import java.util.Collections;

public class j09CarPooling {
    /**
     * Approach: Event-based Chronological Ordering
     * 
     * Intuition:
     * - The key insight is to treat each trip as two events: pickup and dropoff
     * - By processing all events in chronological order, we can track passenger count
     * - When passengers are picked up, we add to current count
     * - When passengers are dropped off, we subtract from current count
     * - For same location events, dropoffs are processed before pickups
     * 
     * Explanation:
     * 1. Create a list of events, where each event is [passengers, location, type]
     *    - type 0: pickup event
     *    - type 1: dropoff event
     * 2. Sort events by location
     *    - If locations are equal, dropoff events come before pickup events
     * 3. Process events in order:
     *    - For pickup events: add passengers and check capacity
     *    - For dropoff events: subtract passengers
     *    - Return false if capacity is exceeded at any point
     * 
     * Time Complexity: O(n log n) where:
     *                  - n is the number of trips
     *                  - Creating and sorting events takes O(n log n)
     *                  - Processing events takes O(n)
     * 
     * Space Complexity: O(n) where:
     *                   - n for storing events list
     * 
     * @param trips    2D array of trips [passengers, from, to]
     * @param capacity Maximum number of passengers the car can hold
     * @return        true if all trips can be completed, false otherwise
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        // Create list to store all events (pickup and dropoff)
        ArrayList<int[]> events = new ArrayList<>();

        // Convert each trip into pickup and dropoff events
        for (int i = 0; i < trips.length; i++) {
            // Add pickup event [passengers, from, type 0]
            events.add(new int[] { trips[i][0], trips[i][1], 0 });
            // Add dropoff event [passengers, to, type 1]
            events.add(new int[] { trips[i][0], trips[i][2], 1 });
        }

        // Sort events by location
        // If locations are equal, dropoff events come before pickup events
        Collections.sort(events, (a, b) -> {
            if (a[1] != b[1])
                return a[1] - b[1];
            else
                return b[2] - a[2];
        });

        int passengers = 0;  // Current number of passengers in car

        // Process all events in chronological order
        for (int[] event : events) {
            if (event[2] == 0) {  // Pickup event
                passengers += event[0];  // Add passengers
                if (passengers > capacity)  // Check if capacity is exceeded
                    return false;
            } else {  // Dropoff event
                passengers -= event[0];  // Remove passengers
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with capacity not exceeded
        System.out.println("\nTest Case 1:");
        int[][] trips1 = {{2,1,5}, {3,3,7}};
        System.out.println("Input: trips = " + java.util.Arrays.deepToString(trips1) + ", capacity = 4");
        System.out.println("Output: " + carPooling(trips1, 4));
        System.out.println("Expected: true");

        // Test Case 2: Capacity exceeded
        System.out.println("\nTest Case 2:");
        int[][] trips2 = {{2,1,5}, {3,3,7}};
        System.out.println("Input: trips = " + java.util.Arrays.deepToString(trips2) + ", capacity = 3");
        System.out.println("Output: " + carPooling(trips2, 3));
        System.out.println("Expected: false");

        // Test Case 3: Same location events
        System.out.println("\nTest Case 3:");
        int[][] trips3 = {{2,1,5}, {3,5,7}};
        System.out.println("Input: trips = " + java.util.Arrays.deepToString(trips3) + ", capacity = 4");
        System.out.println("Output: " + carPooling(trips3, 4));
        System.out.println("Expected: true");
    }
}
