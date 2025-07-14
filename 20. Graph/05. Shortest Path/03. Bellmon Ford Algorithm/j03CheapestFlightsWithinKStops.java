/**
 * LeetCode 787. Cheapest Flights Within K Stops
 *
 * Problem Statement:
 *     There are n cities connected by m flights. Each flight [from, to, price]
 *     starts from city 'from' and arrives at city 'to' with a price. You are given
 *     all the flights and the number of stops k. Find the cheapest price from src
 *     to dst with at most k stops. If there is no such route, return -1.
 *
 * Input:
 *     - n (1 <= n <= 100): Number of cities
 *     - flights: 2D array of flights [from, to, price]
 *     - src (0 <= src < n): Source city
 *     - dst (0 <= dst < n): Destination city
 *     - k (0 <= k < n): Maximum number of stops allowed
 *
 * Output:
 *     - Minimum cost to reach dst from src with at most k stops, or -1 if not possible
 *
 * Example:
 *     Input: n = 4, flights = [[0,1,100],[1,2,100],[2,3,100],[0,2,500]],
 *            src = 0, dst = 3, k = 1
 *     Output: 300
 *
 *     Explanation:
 *     The cheapest route is 0 -> 1 -> 2 -> 3 with cost 100 + 100 + 100 = 300.
 *     Only 1 stop is allowed, so direct 0 -> 2 -> 3 (cost 600) is not valid.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class j03CheapestFlightsWithinKStops {
    static class Flight {
        int to;
        int price;
        Flight(int to, int price) {
            this.to = to;
            this.price = price;
        }
    }

    /**
     * Approach 1: BFS/Queue (Level Order Traversal)
     *
     * Intuition:
     * - We need to find the minimum cost path from src to dst with at most k stops.
     * - Each stop is a level in the BFS traversal.
     * - We use a queue to process all possible paths up to k stops.
     *
     * Why not use PriorityQueue (Dijkstra)?
     * - Dijkstra's algorithm (with a priority queue) is optimal for shortest path
     *   problems with non-negative weights and no stop constraints.
     * - Here, a path with a higher cost but fewer stops may lead to a cheaper path
     *   later, so we cannot greedily discard longer paths early.
     * - Using a queue (BFS) ensures we explore all paths up to k stops, and do not
     *   miss a cheaper path that appears after more stops.
     *
     * Explanation:
     * - Step 1: Build adjacency list for all flights
     * - Step 2: Use a queue to perform BFS, tracking city, cost, and stops so far
     * - Step 3: For each neighbor, if a cheaper price is found, update and enqueue
     * - Step 4: Only process paths with stops <= k
     * - Step 5: Return the minimum price to reach dst, or -1 if not possible
     *
     * Time Complexity: O(n^2 * k) in the worst case (all paths up to k stops)
     * Space Complexity: O(n) for price array and queue
     *
     * @param n      Number of cities
     * @param flights 2D array of flights [from, to, price]
     * @param src    Source city
     * @param dst    Destination city
     * @param k      Maximum number of stops
     * @return       Minimum cost to reach dst with at most k stops, or -1
     */
    public static int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<Flight>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            int from = flights[i][0];
            int to = flights[i][1];
            int price = flights[i][2];
            adj[from].add(new Flight(to, price));
        }

        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        // We use a queue (BFS) instead of a priority queue because a cheaper path
        // may be found with more stops, and Dijkstra's greedy approach may miss it.
        Queue<int[]> queue = new LinkedList<>(); // int[]: {city, cost, stops}
        queue.add(new int[] { src, 0, 0 });
        prices[src] = 0;
        while (!queue.isEmpty()) {
            int[] flight = queue.poll();
            int city = flight[0];
            int cost = flight[1];
            int stops = flight[2];
            if (stops > k)
                continue; // Exceeded allowed stops
            for (Flight neb : adj[city]) {
                int newPrice = cost + neb.price;
                // Only update if we find a cheaper price for this city with <= k stops
                if (prices[neb.to] > newPrice && stops <= k) {
                    prices[neb.to] = newPrice;
                    queue.add(new int[] { neb.to, newPrice, stops + 1 });
                }
            }
        }

        if (prices[dst] == Integer.MAX_VALUE)
            return -1;
        return prices[dst];
    }

    /**
     * Approach 2: Bellman-Ford (DP with at most k edges)
     *
     * Intuition:
     * - Bellman-Ford can be adapted to find the shortest path with at most k edges
     *   (k stops = k+1 edges, but we use <= k relaxations for stops)
     * - We iteratively relax all edges up to k times, tracking the minimum cost
     *   to each city with at most k stops.
     *
     * Explanation:
     * - Step 1: Initialize distance array with infinity, set src to 0
     * - Step 2: For each relaxation (up to k), copy current distances
     * - Step 3: For each flight, relax the edge if a cheaper path is found
     * - Step 4: After k+1 relaxations, dist[dst] holds the answer
     *
     * Time Complexity: O(k * m) where m = number of flights
     * Space Complexity: O(n) for distance arrays
     *
     * @param n      Number of cities
     * @param flights 2D array of flights [from, to, price]
     * @param src    Source city
     * @param dst    Destination city
     * @param k      Maximum number of stops
     * @return       Minimum cost to reach dst with at most k stops, or -1
     */
    public static int findCheapestPriceBellmonFord(int n, int[][] flights, int src, int dst, int k) {
        int inf = (int)1e8; // Use a large value to represent infinity
        int[] dist = new int[n];
        Arrays.fill(dist,inf); // Initialize all distances as infinity
        dist[src] = 0; // Distance to source is always 0
        for(int relax = 0; relax <= k; relax++){
            int[] copy = new int[n];
            for(int i = 0; i < n; i++) copy[i] = dist[i]; // Copy current distances
            for(int i = 0; i < flights.length; i++){
                int from = flights[i][0];
                int to = flights[i][1];
                int weight = flights[i][2];
                if(dist[from] == inf) continue; // Skip unreachable sources
                // Relaxation step: update copy[to] if a cheaper path is found
                copy[to] = Math.min(copy[to],dist[from] + weight);
            }
            dist = copy; // Update distances for next iteration
        }
        if(dist[dst] == inf) return -1; // No path found
        return dist[dst];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        int[][] flights1 = {{0,1,100},{1,2,100},{2,3,100},{0,2,500}};
        System.out.println("Input: n=4, src=0, dst=3, k=1");
        System.out.println("Expected: 300, Output: " + findCheapestPriceDijkstra(4, flights1, 0, 3, 1));
        System.out.println("Expected: 300, Output: " + findCheapestPriceBellmonFord(4, flights1, 0, 3, 1));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] flights2 = {{0,1,100},{1,2,100},{2,0,100}};
        System.out.println("Input: n=3, src=0, dst=2, k=0");
        System.out.println("Expected: -1, Output: " + findCheapestPriceDijkstra(3, flights2, 0, 2, 0));
        System.out.println("Expected: -1, Output: " + findCheapestPriceBellmonFord(3, flights2, 0, 2, 0));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] flights3 = {{0,1,1}};
        System.out.println("Input: n=2, src=0, dst=1, k=0");
        System.out.println("Expected: 1, Output: " + findCheapestPriceDijkstra(2, flights3, 0, 1, 0));
        System.out.println("Expected: 1, Output: " + findCheapestPriceBellmonFord(2, flights3, 0, 1, 0));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] flights4 = {{0,1,1},{1,2,1},{0,2,10}};
        System.out.println("Input: n=3, src=0, dst=2, k=1");
        System.out.println("Expected: 2, Output: " + findCheapestPriceDijkstra(3, flights4, 0, 2, 1));
        System.out.println("Expected: 2, Output: " + findCheapestPriceBellmonFord(3, flights4, 0, 2, 1));
    }
}
