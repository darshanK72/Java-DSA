/**
 * Custom Implementation. Detect Cycle in Directed Graph using Topological Sort (Kahn's Algorithm)
 *
 * Problem Statement:
 *     Given a directed graph with V vertices and a list of edges, determine if the graph contains a cycle.
 *     Use a topological sort (BFS/Kahn's Algorithm) based approach.
 *
 * Input:
 *     - V (1 <= V <= 10^5): Number of vertices (0-indexed)
 *     - edges (0 <= edges.length <= 10^5): List of directed edges [from, to]
 *
 * Output:
 *     - boolean: true if the graph contains a cycle, false otherwise
 *
 * Example:
 *     Input: V = 4, edges = [[0,1],[1,2],[2,3],[3,1]]
 *     Output: true
 *
 *     Explanation:
 *     There is a cycle: 1 -> 2 -> 3 -> 1
 */

import java.util.*;

public class j02CycleInDirectedGraphTopo {
    /**
     * Approach: Kahn's Algorithm (BFS-based Topological Sort)
     *
     * Intuition:
     * - In a DAG, all nodes can be sorted topologically, and every node will be processed.
     * - If there is a cycle, some nodes will always have non-zero in-degree and will never be processed.
     * - By counting how many nodes are processed, we can detect if a cycle exists.
     *
     * Explanation:
     * - Step 1: Build the adjacency list and compute in-degree for each node.
     * - Step 2: Add all nodes with in-degree 0 to a queue.
     * - Step 3: Process nodes from the queue, decrementing in-degree of their neighbors.
     *   If a neighbor's in-degree becomes 0, add it to the queue.
     * - Step 4: If the number of processed nodes is less than V, a cycle exists.
     *   Otherwise, the graph is acyclic.
     * - Example: For edges [[0,1],[1,2],[2,3],[3,1]], node 1 will never reach in-degree 0 due to the cycle.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     *
     * @param V      Number of vertices (0-indexed)
     * @param edges  List of directed edges [from, to]
     * @return       true if the graph contains a cycle, false otherwise
     */
    public static boolean isCyclicTopologicalSort(int V, int[][] edges) {
        // Build adjacency list for the graph
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each vertex
        for (int[] edge : edges)
            adj[edge[0]].add(edge[1]); // Add directed edge from 'from' to 'to'

        int[] indegree = new int[V]; // Array to store in-degree of each node
        for (int u = 0; u < V; u++) {
            for (int v : adj[u])
                indegree[v]++; // Count incoming edges for each node
        }

        Queue<Integer> q = new ArrayDeque<>(); // Queue for BFS
        int processed = 0; // Count of processed nodes
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i); // Add all nodes with in-degree 0 to the queue
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll(); // Remove node with in-degree 0
            processed++; // Increment processed node count
            for (int neb : adj[node]) {
                indegree[neb]--; // Remove edge and update in-degree
                if (indegree[neb] == 0)
                    q.add(neb); // If neighbor now has in-degree 0, add to queue
            }
        }

        // If processed < V, there is a cycle
        return (processed != V);
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = { {0,1},{1,2},{2,3},{3,1} };
        System.out.println("Cycle: " + isCyclicTopologicalSort(4, edges1)); // true

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {};
        System.out.println("Cycle: " + isCyclicTopologicalSort(1, edges2)); // false

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] edges3 = { {0,1} };
        System.out.println("Cycle: " + isCyclicTopologicalSort(2, edges3)); // false

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = { {0,1},{1,2},{2,0} };
        System.out.println("Cycle: " + isCyclicTopologicalSort(3, edges4)); // true
    }
}
