/**
 * Problem Statement:
 *     LeetCode 933. Number of Recent Calls
 * 
 *     Design a system that counts the number of recent requests within a certain
 *     time frame. Implement the RecentCounter class:
 *     - RecentCounter() Initializes the counter with zero recent requests
 *     - int ping(int t) Adds a new request at time t, where t represents some 
 *       time in milliseconds, and returns the number of requests that have happened
 *       in the past 3000 milliseconds (including the new request)
 * 
 * Input:
 *     - Sequence of ping calls with timestamps t
 *     - 1 <= t <= 10^9
 *     - Each test case will call ping with strictly increasing values of t
 * 
 * Output:
 *     - Number of calls in last 3000ms window for each ping
 * 
 * Example:
 *     Input: ["RecentCounter", "ping", "ping", "ping", "ping"]
 *            [[], [1], [100], [3001], [3002]]
 *     Output: [null, 1, 2, 3, 3]
 *     
 *     Explanation:
 *     - At t=1: 1 request in window [0,1]
 *     - At t=100: 2 requests in window [0,100]
 *     - At t=3001: 3 requests in window [1,3001]
 *     - At t=3002: 3 requests in window [2,3002]
 */

import java.util.ArrayDeque;
import java.util.Queue;

public class j02NumberOfRecentCalls {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[] testCases = {
                1, 100, 3001, 3002, 3003, 3004, 3005, 3006, 3007, 3008
        };

        RecentCounter counter = new RecentCounter();

        // Test each case
        for (int test : testCases) {
            System.out.println("Ping: " + test + ", Count: " + counter.ping(test));
        }
        System.out.println("Final Queue: " + counter.list);
    }

    /**
     * RecentCounter class maintains a sliding window of requests
     * using a queue to track timestamps within last 3000ms
     */
    static class RecentCounter {
        Queue<Integer> list;  // Queue to store timestamps

        /**
         * Constructor: Initialize empty queue for timestamps
         */
        public RecentCounter() {
            list = new ArrayDeque<>();
        }

        /**
         * Approach: Sliding Window with Queue
         * 
         * Intuition:
         * - Add new timestamp to queue
         * - Remove timestamps outside 3000ms window
         * - Remaining queue size is count of recent requests
         * 
         * Time Complexity: O(1) amortized
         * - Each timestamp is added and removed at most once
         * 
         * Space Complexity: O(W)
         * - W is window size (3000ms)
         * - Queue stores at most W timestamps
         */
        public int ping(int t) {
            this.list.add(t);  // Add new timestamp
            int rangeStart = t - 3000;  // Calculate window start
            
            // Remove timestamps outside window
            while (this.list.peek() < rangeStart)
                this.list.remove();
                
            return this.list.size();  // Return count in window
        }
    }
}
