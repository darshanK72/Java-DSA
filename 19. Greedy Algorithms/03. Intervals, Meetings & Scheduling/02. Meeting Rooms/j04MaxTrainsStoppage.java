/**
 * Problem: Maximum Trains Stoppage
 * 
 * Problem Statement:
 *     You are given 'n' number of trains and 'm' number of platforms for a station. Every train
 *     has an associated 'arrival time', 'departure time', and 'platform' number. Your task is to
 *     determine the maximum number of trains for which you can provide a stoppage at the station.
 *     You can provide stoppage to only one train at platform 'x' between 'arrival time' to
 *     'departure time' of the current train.
 * 
 * Input:
 *     - trains: 2D array where trains[i] = [arrival_time, departure_time, platform_number]
 *     - n: number of trains
 *     - m: number of platforms
 *     Note: Times are in 24-hour format (e.g., 1015 means 10:15)
 * 
 * Output:
 *     - Maximum number of trains that can be accommodated
 * 
 * Example:
 *     Input: n=5, m=2
 *     trains = [
 *         [0950, 1005, 1],
 *         [1000, 1030, 1],
 *         [1015, 1030, 1],
 *         [1200, 1205, 2],
 *         [1215, 1230, 2]
 *     ]
 *     Output: 4
 * 
 *     Explanation:
 *     Platform 1: Can accommodate 2 trains (0950-1005 and 1015-1030)
 *     Platform 2: Can accommodate 2 trains (1200-1205 and 1215-1230)
 *     Total: 4 trains
 */

import java.util.Arrays;

public class j04MaxTrainsStoppage {
    /**
     * Approach: Greedy with Sorting by Departure Time
     * 
     * Intuition:
     * - The key insight is to prioritize trains that depart earlier
     * - By sorting trains by departure time, we can maximize the number of trains
     * - This ensures we don't block platforms for longer than necessary
     * 
     * Explanation:
     * 1. Sort trains by departure time in ascending order
     * 2. Keep track of the last departure time for each platform
     * 3. For each train:
     *    - If its platform is available (current time >= last departure)
     *    - Schedule the train and update platform's last departure time
     *    - Increment count of accommodated trains
     * 
     * Time Complexity: O(n log n) where:
     *                  - n is the number of trains
     *                  - Sorting takes O(n log n)
     *                  - Single pass through array takes O(n)
     * 
     * Space Complexity: O(m) where:
     *                   - m is the number of platforms
     * 
     * @param trains  2D array of train schedules [arrival, departure, platform]
     * @param n       Number of trains
     * @param m       Number of platforms
     * @return       Maximum number of trains that can be accommodated
     */
    public static int maxStop(int[][] trains, int n, int m) {
        // Initialize array to track last departure time for each platform
        int[] platforms = new int[m];
        
        // Sort trains by departure time in ascending order
        Arrays.sort(trains,(a,b) -> {
            return a[1] - b[1];
        });

        int ans = 0;  // Count of trains that can be accommodated

        // Process each train
        for(int i = 0; i < n; i++){
            // If platform is available (current time >= last departure)
            if(platforms[trains[i][2] - 1] <= trains[i][0]){
                ans++;  // Increment count of accommodated trains
                platforms[trains[i][2] - 1] = trains[i][1];  // Update platform's last departure
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Multiple platforms with overlapping trains
        System.out.println("\nTest Case 1:");
        int[][] trains1 = {
            {950, 1005, 1},
            {1000, 1030, 1},
            {1015, 1030, 1},
            {1200, 1205, 2},
            {1215, 1230, 2}
        };
        System.out.println("Input: n=5, m=2");
        System.out.println("Output: " + maxStop(trains1, 5, 2));
        System.out.println("Expected: 4");

        // Test Case 2: Single platform with overlapping trains
        System.out.println("\nTest Case 2:");
        int[][] trains2 = {
            {1200, 1210, 1},
            {1205, 1220, 1},
            {1215, 1230, 1},
            {1215, 1240, 1}
        };
        System.out.println("Input: n=4, m=1");
        System.out.println("Output: " + maxStop(trains2, 4, 1));
        System.out.println("Expected: 2");
    }
}
