/**
 * GFG. Detect Negative Weight Cycle using Bellman-Ford Algorithm
 *
 * Problem Statement:
 *     Given a directed weighted graph with n vertices and m edges, check if the
 *     graph contains a negative weight cycle. A negative weight cycle is a cycle
 *     whose total sum of edge weights is negative.
 *
 * Input:
 *     - n (1 <= n <= 500): Number of vertices
 *     - edges: 2D array of edges, where each edge is [from, to, weight]
 *
 * Output:
 *     - Return 1 if there is a negative weight cycle, else return 0
 *
 * Example:
 *     Input: n = 3, edges = [[0,1,-1],[1,2,-2],[2,0,-3]]
 *     Output: 1
 *
 *     Explanation:
 *     The cycle 0 -> 1 -> 2 -> 0 has total weight -1 + -2 + -3 = -6 < 0
 */

import java.util.Arrays;

public class j02NegativeWeightCycle {

    /**
     * Approach: Bellman-Ford Algorithm for Negative Cycle Detection
     *
     * Intuition:
     * - Bellman-Ford can detect negative weight cycles by checking if any edge
     *   can be further relaxed after n-1 passes.
     * - If so, a negative cycle exists.
     *
     * Explanation:
     * - Step 1: Initialize distances to all vertices as infinity except source (0)
     * - Step 2: Relax all edges n-1 times
     * - Step 3: Try to relax all edges one more time; if possible, negative cycle exists
     *
     * Time Complexity: O(n * m) where n = number of vertices, m = number of edges
     * Space Complexity: O(n) for distance array
     *
     * @param n     Number of vertices (1 <= n <= 500)
     * @param edges 2D array of edges [from, to, weight]
     * @return     1 if negative weight cycle exists, else 0
     */
    public int isNegativeWeightCycle(int n, int[][] edges) {
        int inf = (int) 1e8; // Use a large value to represent infinity
        int[] dist = new int[n];
        Arrays.fill(dist, inf); // Initialize all distances as infinity
        dist[0] = 0; // Start from vertex 0 (arbitrary, as we only need to detect cycle)
        // Relax all edges n-1 times
        for (int relax = 1; relax < n; relax++) {
            // For each edge, try to improve the distance to the destination vertex
            for (int i = 0; i < edges.length; i++) {
                int from = edges[i][0];
                int to = edges[i][1];
                int weight = edges[i][2];
                // If the source vertex is unreachable, skip this edge
                if (dist[from] == inf)
                    continue;
                // Relaxation step: update dist[to] if a shorter path is found
                dist[to] = Math.min(dist[to],dist[from] + weight);
            }
        }
        // Check for negative weight cycle by trying to relax once more
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            // If we can still relax, a negative cycle exists
            if (dist[from] + weight < dist[to]) {
                return 1; // Negative cycle detected
            }
        }
        return 0; // No negative cycle found
    }

    public static void main(String[] args) {
        j02NegativeWeightCycle solver = new j02NegativeWeightCycle();
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] edges1 = {
            {0,1,-1},{1,2,-2},{2,0,-3}
        };
        System.out.print("Input: n=3, negative cycle\nExpected: 1, Output: ");
        System.out.println(solver.isNegativeWeightCycle(3, edges1));

        // Test Case 2: Edge case - no negative cycle
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {
            {0,1,1},{1,2,2},{2,0,3}
        };
        System.out.print("Input: n=3, no negative cycle\nExpected: 0, Output: ");
        System.out.println(solver.isNegativeWeightCycle(3, edges2));

        // Test Case 3: Boundary case - single node
        System.out.println("\nBoundary Cases:");
        int[][] edges3 = {};
        System.out.print("Input: n=1, no edges\nExpected: 0, Output: ");
        System.out.println(solver.isNegativeWeightCycle(1, edges3));

        // Test Case 4: Special case - disconnected graph with negative cycle
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = {
            {0,1,2},{2,3,-1},{3,2,-2}
        };
        System.out.print("Input: n=4, disconnected, negative cycle in component\nExpected: 1, Output: ");
        System.out.println(solver.isNegativeWeightCycle(4, edges4));
    }
}
