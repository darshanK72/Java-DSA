/**
 * GFG/LeetCode. Bellman-Ford Algorithm (Single Source Shortest Path with Negative Weights)
 *
 * Problem Statement:
 *     Given a directed weighted graph with V vertices and E edges, find the shortest
 *     path from a given source vertex to all other vertices. The graph may contain
 *     negative weight edges. If a negative weight cycle exists, report it.
 *
 * Input:
 *     - V (1 <= V <= 500): Number of vertices
 *     - edges: 2D array of edges, where each edge is [from, to, weight]
 *     - src (0 <= src < V): Source vertex
 *
 * Output:
 *     - Array of shortest distances from src to every vertex
 *     - If a negative weight cycle exists, return [-1]
 *
 * Example:
 *     Input: V = 5, edges = [[0,1,-1],[0,2,4],[1,2,3],[1,3,2],[1,4,2],
 *                            [3,2,5],[3,1,1],[4,3,-3]], src = 0
 *     Output: [0, -1, 2, -2, 1]
 *
 *     Explanation:
 *     Shortest paths from 0 to all vertices are as above. No negative cycle exists.
 */

import java.util.Arrays;

public class j01BellmonFord {

    /**
     * Approach: Bellman-Ford Algorithm
     *
     * Intuition:
     * - Bellman-Ford is used for graphs with negative weights and can detect
     *   negative weight cycles.
     * - It relaxes all edges V-1 times to ensure shortest paths are found.
     * - If a further relaxation is possible after V-1 passes, a negative cycle exists.
     *
     * Explanation:
     * - Step 1: Initialize distances to all vertices as infinity except source (0)
     * - Step 2: For V-1 times, relax all edges (update dist[to] if shorter path found)
     * - Step 3: Check for negative cycles by trying to relax once more
     * - Step 4: If negative cycle detected, return [-1]; else, return dist array
     *
     * Time Complexity: O(V * E) where V = number of vertices, E = number of edges
     * Space Complexity: O(V) for distance array
     *
     * @param V    Number of vertices (1 <= V <= 500)
     * @param edges 2D array of edges [from, to, weight]
     * @param src  Source vertex (0 <= src < V)
     * @return     Array of shortest distances from src, or [-1] if negative cycle
     */
    public static int[] bellmanFord(int V, int[][] edges, int src) {
        int inf = (int) 1e8; // Use a large value to represent infinity
        int[] dist = new int[V];
        Arrays.fill(dist, inf); // Initialize all distances as infinity
        dist[src] = 0; // Distance to source is always 0
        // Relax all edges V-1 times
        for (int relax = 1; relax < V; relax++) {
            // For each edge, try to improve the distance to the destination vertex
            for (int i = 0; i < edges.length; i++) {
                int from = edges[i][0];
                int to = edges[i][1];
                int weight = edges[i][2];
                // If the source vertex is unreachable, skip this edge
                if (dist[from] == inf)
                    continue;
                // Relaxation step: update dist[to] if a shorter path is found
                if (dist[to] > dist[from] + weight) {
                    dist[to] = dist[from] + weight;
                }
            }
        }
        // Check for negative weight cycle by trying to relax once more
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            // If the source vertex is unreachable, skip this edge
            if (dist[from] == inf)
                continue;
            // If we can still relax, a negative cycle exists
            if (dist[from] + weight < dist[to]) {
                // Negative cycle detected, return [-1] as per convention
                return new int[] { -1 };
            }
        }
        // Return the array of shortest distances from src to all vertices
        return dist;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] edges1 = {
            {0,1,-1},{0,2,4},{1,2,3},{1,3,2},{1,4,2},{3,2,5},{3,1,1},{4,3,-3}
        };
        int[] result1 = bellmanFord(5, edges1, 0);
        System.out.print("Input: V=5, src=0, edges=...\nExpected: [0, -1, 2, -2, 1], Output: ");
        System.out.println(Arrays.toString(result1));

        // Test Case 2: Edge case - negative cycle
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {
            {0,1,1},{1,2,-1},{2,0,-1}
        };
        int[] result2 = bellmanFord(3, edges2, 0);
        System.out.print("Input: V=3, src=0, negative cycle\nExpected: [-1], Output: ");
        System.out.println(Arrays.toString(result2));

        // Test Case 3: Boundary case - single node
        System.out.println("\nBoundary Cases:");
        int[][] edges3 = {};
        int[] result3 = bellmanFord(1, edges3, 0);
        System.out.print("Input: V=1, src=0, no edges\nExpected: [0], Output: ");
        System.out.println(Arrays.toString(result3));

        // Test Case 4: Special case - disconnected graph
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = {
            {0,1,2},{2,3,3}
        };
        int[] result4 = bellmanFord(4, edges4, 0);
        System.out.print("Input: V=4, src=0, disconnected\nExpected: [0,2,100000000,100000000], Output: ");
        System.out.println(Arrays.toString(result4));
    }
}