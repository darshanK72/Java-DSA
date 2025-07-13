/**
 * GFG/Custom: Shortest Path in Weighted Undirected Graph
 *
 * Problem Statement:
 *     Given a weighted undirected graph with n nodes and m edges, find the shortest
 *     path from node 1 to node n. If there are multiple shortest paths, return any one.
 *     If node n is unreachable from node 1, return [-1].
 *     The path should be returned as a list of nodes in order, followed by the total cost.
 *
 * Input:
 *     - n: int, number of nodes (1 <= n <= 10^5)
 *     - m: int, number of edges (1 <= m <= 2*10^5)
 *     - edges: int[m][3], each edge is [u, v, w] (1 <= u, v <= n, 1 <= w <= 10^9)
 *
 * Output:
 *     - List<Integer>: Path from 1 to n (inclusive), followed by total cost. If unreachable, [-1].
 *
 * Example:
 *     Input: n = 5, m = 6, edges = [[1,2,2],[1,3,4],[2,3,1],[2,4,7],[3,5,3],[4,5,1]]
 *     Output: [1,2,3,5,6]
 *
 *     Explanation:
 *         Path: 1 -> 2 -> 3 -> 5, cost = 2+1+3 = 6
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class j01WeightedUndirectedGraph {
    /**
     * Helper class to represent an edge in the graph
     * Intuition:
     * - Encapsulates the destination node and the weight (cost)
     * - Used in adjacency list and Dijkstra's algorithm
     * Explanation:
     * - 'to' is the destination node
     * - 'weight' is the cost to reach 'to' from the current node
     */
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Approach: Dijkstra's Algorithm (Min-Heap Priority Queue)
     *
     * Intuition:
     * - Use Dijkstra's algorithm to find the shortest path from node 1 to node n
     *   in a weighted undirected graph.
     * - Track parent pointers to reconstruct the path.
     *
     * Explanation:
     * - Step 1: Build adjacency list for the graph from the edges array.
     * - Step 2: Initialize distance array with Integer.MAX_VALUE (infinity).
     * - Step 3: Use a min-heap (priority queue) to always expand the node with the
     *           smallest known distance.
     * - Step 4: For each neighbor, if a shorter path is found, update the distance
     *           and parent, and add to the queue.
     * - Step 5: After processing, reconstruct the path from node n to 1 using parent array.
     * - Step 6: If node n is unreachable, return [-1].
     *
     * Time Complexity: O((n + m) log n), where n is nodes, m is edges.
     * Space Complexity: O(n + m), for adjacency list, distance, and parent arrays.
     *
     * @param n      Number of nodes (1 <= n <= 10^5)
     * @param m      Number of edges (1 <= m <= 2*10^5)
     * @param edges  Edge list [u, v, w] (1 <= u, v <= n, 1 <= w <= 10^9)
     * @return       List of nodes in path from 1 to n, followed by total cost. If unreachable, [-1].
     */
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        // Build adjacency list for the graph
        ArrayList<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight)); // Undirected graph
        }

        // Min-heap to always process the node with the smallest distance
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { 1, 0 }); // Start from node 1 with distance 0
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        // Dijkstra's algorithm main loop
        while (!pq.isEmpty()) {
            int[] edge = pq.poll(); // edge[0]=node, edge[1]=cost so far
            for (Edge neb : adj[edge[0]]) {
                int newWeight = edge[1] + neb.weight; // Calculate new distance
                if (newWeight < dist[neb.to]) {
                    parent[neb.to] = edge[0]; // Track parent for path reconstruction
                    dist[neb.to] = newWeight; // Update distance
                    pq.add(new int[] { neb.to, newWeight }); // Add neighbor to queue
                }
            }
        }

        List<Integer> out = new ArrayList<>();

        if (dist[n] == Integer.MAX_VALUE) {
            out.add(-1); // If node n is unreachable, return [-1]
            return out;
        }

        // Reconstruct path from n to 1 using parent array
        int curr = n;
        List<Integer> path = new ArrayList<>();
        while (curr != -1) {
            path.add(curr);
            curr = parent[curr];
        }
        Collections.reverse(path); // Path from 1 to n
        out.addAll(path);
        out.add(dist[n]); // Add total cost at the end
        return out;
    }

    public static void main(String[] args) {
        j01WeightedUndirectedGraph solver = new j01WeightedUndirectedGraph();
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] e1 = {{1,2,2},{1,3,4},{2,3,1},{2,4,7},{3,5,3},{4,5,1}};
        System.out.println("Input: n=5, m=6, edges=[[1,2,2],[1,3,4],[2,3,1],[2,4,7],[3,5,3],[4,5,1]], Expected: [1,2,3,5,6], Output: " + solver.shortestPath(5, 6, e1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] e2 = {{1,2,1}};
        System.out.println("Input: n=2, m=1, edges=[[1,2,1]], Expected: [1,2,1], Output: " + solver.shortestPath(2, 1, e2));
        int[][] e3 = {{2,3,1}};
        System.out.println("Input: n=3, m=1, edges=[[2,3,1]], Expected: [-1], Output: " + solver.shortestPath(3, 1, e3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] e4 = new int[0][0];
        System.out.println("Input: n=1, m=0, edges=[], Expected: [1,0], Output: " + solver.shortestPath(1, 0, e4));
        int[][] e5 = {{1,2,1000000000},{2,3,1000000000}};
        System.out.println("Input: n=3, m=2, edges=[[1,2,1000000000],[2,3,1000000000]], Expected: [1,2,3,2000000000], Output: " + solver.shortestPath(3, 2, e5));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] e6 = {{1,2,1},{2,3,1},{3,4,1},{4,5,1}};
        System.out.println("Input: n=5, m=4, edges=[[1,2,1],[2,3,1],[3,4,1],[4,5,1]], Expected: [1,2,3,4,5,4], Output: " + solver.shortestPath(5, 4, e6));
    }
}
