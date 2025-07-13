/**
 * LeetCode 1976. Number of Ways to Arrive at Destination
 * 
 * Problem Statement:
 *     You are in a city that consists of n intersections numbered from 0 to 
 *     n - 1 with bi-directional roads between some intersections. The roads 
 *     are represented as a 2D integer array roads where roads[i] = [ui, vi, 
 *     timei] means that there is a road between intersections ui and vi that 
 *     takes timei minutes to travel. You want to know in how many different 
 *     ways you can travel from intersection 0 to intersection n - 1 in the 
 *     shortest time. Return the number of ways modulo 10^9 + 7.
 * 
 * Input:
 *     - n (2 <= n <= 200): number of intersections (nodes)
 *     - roads (1 <= roads.length <= 2000): array of [from, to, time] edges
 *     - roads[i][0], roads[i][1] (0 <= ui, vi < n): intersection indices
 *     - roads[i][2] (1 <= timei <= 10^9): travel time in minutes
 * 
 * Output:
 *     - Number of different ways to reach destination in shortest time
 *     - Result modulo 10^9 + 7
 * 
 * Example:
 *     Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],
 *                            [6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 *     Output: 4
 * 
 *     Explanation:
 *     The shortest time from 0 to 6 is 7 minutes. The four ways to get there 
 *     in 7 minutes are:
 *     - 0 → 6 (direct path)
 *     - 0 → 4 → 6
 *     - 0 → 1 → 2 → 5 → 6
 *     - 0 → 1 → 3 → 5 → 6
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class j06NumberOfWaysToArriveAtDestination {

    /**
     * Edge class to represent graph edges with destination and travel time
     * Used for building adjacency list and priority queue operations
     */
    static class Edge {
        int to;
        long weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Approach: Dijkstra's Algorithm with Path Counting
     * 
     * Intuition:
     * - We need to find all shortest paths from source to destination
     * - When multiple paths have the same shortest distance, we count all of them
     * - We use Dijkstra's algorithm to find shortest distances, but also maintain 
     *   a count of ways to reach each node with the current shortest distance
     * - When we find a shorter path, we reset the count. When we find an equal 
     *   path, we add to the existing count
     * 
     * Explanation:
     * - Step 1: Build adjacency list representation of the undirected graph
     * - Step 2: Initialize distance array with maximum values and ways array with 0
     * - Step 3: Use min-heap to process nodes in order of shortest distance
     * - Step 4: For each neighbor, calculate new distance as current + edge weight
     * - Step 5: If shorter path found, update distance and reset ways count
     * - Step 6: If equal distance found, add current ways to neighbor's ways
     * - Step 7: Return number of ways to reach destination modulo 10^9 + 7
     * 
     * Time Complexity: O((V + E) * log V) where V is number of nodes and E is 
     *                  number of edges, due to priority queue operations
     * Space Complexity: O(V + E) for adjacency list and priority queue storage
     * 
     * @param n      Number of intersections (2 <= n <= 200)
     * @param roads  Array of [from, to, time] representing roads
     * @return      Number of ways to reach destination in shortest time (mod 10^9+7)
     */
    public static int countPaths(int n, int[][] roads) {
        // Build adjacency list representation of the undirected graph
        ArrayList<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // Add edges to adjacency list (undirected graph - add both directions)
        for (int i = 0; i < roads.length; i++) {
            int from = roads[i][0];
            int to = roads[i][1];
            int weight = roads[i][2];
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }

        // Initialize distance array with maximum values
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        // Initialize ways array to count paths to each node
        int[] ways = new int[n];
        
        // Initialize min-heap to process nodes with shortest distance first
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        
        // Start with source node having 0 distance and 1 way to reach it
        pq.add(new long[] { 0, 0 });
        dist[0] = 0;
        ways[0] = 1;

        // Process nodes in order of shortest distance
        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int node = (int) current[0];
            long currentDist = current[1];
            
            // Explore all neighbors of current node
            for (Edge neighbor : adj[node]) {
                // Calculate new distance as current distance + edge weight
                long newDistance = currentDist + neighbor.weight;
                
                // If we found a shorter path to this neighbor
                if (dist[neighbor.to] > newDistance) {
                    dist[neighbor.to] = newDistance;
                    // Reset ways count to current node's ways
                    ways[neighbor.to] = ways[node];
                    pq.add(new long[] { neighbor.to, newDistance });
                } 
                // If we found an equal distance path to this neighbor
                else if (dist[neighbor.to] == newDistance) {
                    // Add current node's ways to neighbor's ways (modulo arithmetic)
                    ways[neighbor.to] = ((ways[neighbor.to] + ways[node]) % 1000000007);
                }
            }
        }

        // Return number of ways to reach destination modulo 10^9 + 7
        return ways[n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        int[][] roads1 = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        System.out.println("Input: n=7, roads with multiple shortest paths");
        System.out.println("Expected: 4, Output: " + countPaths(7, roads1));
        
        int[][] roads2 = {{0,1,1},{1,2,1},{2,3,1},{0,3,4}};
        System.out.println("Input: n=4, roads with single shortest path");
        System.out.println("Expected: 1, Output: " + countPaths(4, roads2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] roads3 = {{0,1,1}};
        System.out.println("Input: n=2, single road");
        System.out.println("Expected: 1, Output: " + countPaths(2, roads3));
        
        int[][] roads4 = {{0,1,1},{1,2,1},{0,2,2}};
        System.out.println("Input: n=3, two paths with equal distance");
        System.out.println("Expected: 2, Output: " + countPaths(3, roads4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] roads5 = {{0,1,1000000000}};
        System.out.println("Input: n=2, maximum travel time");
        System.out.println("Expected: 1, Output: " + countPaths(2, roads5));
        
        int[][] roads6 = {{0,1,1},{1,2,1},{2,3,1},{3,4,1},{0,4,4}};
        System.out.println("Input: n=5, multiple equal shortest paths");
        System.out.println("Expected: 2, Output: " + countPaths(5, roads6));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] roads7 = {{0,1,1},{1,2,1},{2,3,1},{0,3,3},{1,3,2}};
        System.out.println("Input: n=4, complex graph with multiple paths");
        System.out.println("Expected: 3, Output: " + countPaths(4, roads7));
        
        int[][] roads8 = {{0,1,1},{1,2,1},{2,3,1},{3,4,1},{4,5,1},{0,5,5}};
        System.out.println("Input: n=6, linear path vs direct path");
        System.out.println("Expected: 2, Output: " + countPaths(6, roads8));
        
        int[][] roads9 = {{0,1,1},{1,2,1},{0,2,2},{2,3,1},{1,3,2},{0,3,3}};
        System.out.println("Input: n=4, multiple paths with different lengths");
        System.out.println("Expected: 3, Output: " + countPaths(4, roads9));
    }
}
