/**
 * LeetCode 743. Network Delay Time
 * 
 * Problem Statement:
 *     You are given a network of n nodes, labeled from 1 to n. You are also given times,
 *     a list of travel times as directed edges times[i] = (u, v, w), where u is the source
 *     node, v is the target node, and w is the time it takes for a signal to travel from
 *     source to target. Send a signal from a given node k. Return the time it takes for all
 *     the n nodes to receive the signal. If it is impossible, return -1.
 * 
 * Input:
 *     - times: int[][], each element is [u, v, w] (1 <= u, v <= n, 1 <= w <= 100)
 *     - n: int, number of nodes (1 <= n <= 100)
 *     - k: int, starting node (1 <= k <= n)
 * 
 * Output:
 *     - int: The minimum time for all nodes to receive the signal, or -1 if impossible
 * 
 * Example:
 *     Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 *     Output: 2
 * 
 *     Explanation:
 *         2 -> 1 (1)
 *         2 -> 3 (1)
 *         3 -> 4 (1)
 *         The signal reaches all nodes in 2 units of time (2->3->4).
 */

import java.util.*;

public class j02NetworkDelayTime {
    /**
     * Helper class to represent an edge in the graph
     * Intuition:
     * - Encapsulates the destination node and the weight (travel time)
     * - Used in adjacency list and priority queue for Dijkstra's algorithm
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
     * - Use Dijkstra's algorithm to find the shortest path from the source node k
     *   to all other nodes in a weighted directed graph.
     * - The maximum distance among all reachable nodes is the answer.
     * - If any node is unreachable, return -1.
     * 
     * Explanation:
     * - Step 1: Build adjacency list for the graph from the times array.
     * - Step 2: Initialize distance array with Integer.MAX_VALUE (infinity).
     * - Step 3: Use a min-heap (priority queue) to always expand the node with the
     *           smallest known distance.
     * - Step 4: For each neighbor, if a shorter path is found, update the distance
     *           and add to the queue.
     * - Step 5: After processing, the answer is the maximum value in the distance
     *           array (excluding unreachable nodes).
     * 
     * Time Complexity: O(E log V), where E is the number of edges, V is the number of nodes.
     * Space Complexity: O(V + E), for adjacency list and distance array.
     * 
     * @param times    2D array of edges [u, v, w] (1 <= u, v <= n, 1 <= w <= 100)
     * @param n        Number of nodes (1 <= n <= 100)
     * @param k        Starting node (1 <= k <= n)
     * @return         Minimum time for all nodes to receive the signal, or -1 if impossible
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list for the graph
        ArrayList<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            int weight = times[i][2];
            adj[from].add(new Edge(to, weight));
        }
        // Initialize distance array with infinity
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // Min-heap to always process the node with the smallest distance
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(k, 0)); // Start from node k with distance 0
        dist[k] = 0;

        // Dijkstra's algorithm main loop
        while (!pq.isEmpty()) {
            Edge edge = pq.poll(); // Get node with smallest distance
            // For each neighbor of the current node
            for (Edge neb : adj[edge.to]) {
                int newWeight = edge.weight + neb.weight; // Calculate new distance
                // If a shorter path to neighbor is found
                if (dist[neb.to] > newWeight) {
                    dist[neb.to] = newWeight; // Update distance
                    pq.add(new Edge(neb.to, newWeight)); // Add neighbor to queue
                }
            }
        }

        // Find the maximum distance to any node
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1; // If any node is unreachable, return -1
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] t1 = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println("Input: [[2,1,1],[2,3,1],[3,4,1]], n=4, k=2, Expected: 2, Output: " + networkDelayTime(t1, 4, 2));
        int[][] t2 = {{1,2,1}};
        System.out.println("Input: [[1,2,1]], n=2, k=1, Expected: 1, Output: " + networkDelayTime(t2, 2, 1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] t3 = {};
        System.out.println("Input: [], n=1, k=1, Expected: 0, Output: " + networkDelayTime(t3, 1, 1));
        int[][] t4 = {{1,2,1}};
        System.out.println("Input: [[1,2,1]], n=2, k=2, Expected: -1, Output: " + networkDelayTime(t4, 2, 2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] t5 = new int[0][0];
        System.out.println("Input: [], n=100, k=1, Expected: 0 or -1, Output: " + networkDelayTime(t5, 100, 1));
        int[][] t6 = {{1,100,1}};
        System.out.println("Input: [[1,100,1]], n=100, k=1, Expected: -1, Output: " + networkDelayTime(t6, 100, 1));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] t7 = {{1,2,1},{2,3,2},{1,3,4}};
        System.out.println("Input: [[1,2,1],[2,3,2],[1,3,4]], n=3, k=1, Expected: 3, Output: " + networkDelayTime(t7, 3, 1));
        int[][] t8 = {{1,2,1},{2,1,3}};
        System.out.println("Input: [[1,2,1],[2,1,3]], n=2, k=2, Expected: 3, Output: " + networkDelayTime(t8, 2, 2));
    }
}
