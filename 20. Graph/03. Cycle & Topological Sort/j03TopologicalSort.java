/**
 * Custom Implementation. Topological Sort (DFS & BFS)
 * 
 * Problem Statement:
 *     Given a directed acyclic graph (DAG) with V vertices and a list of edges,
 *     return a topological ordering of its vertices using both DFS and BFS (Kahn's Algorithm).
 *     If there are multiple valid orderings, return any one of them.
 * 
 * Input:
 *     - V (1 <= V <= 10^5): Number of vertices (0-indexed)
 *     - edges (0 <= edges.length <= 10^5): List of directed edges [from, to]
 * 
 * Output:
 *     - ArrayList<Integer>: A valid topological ordering of the vertices
 * 
 * Example:
 *     Input: V = 4, edges = [[0,1],[0,2],[1,2],[2,3]]
 *     Output: [0,1,2,3] or [0,1,3,2] (any valid topological order)
 * 
 *     Explanation:
 *     0 -> 1 -> 2 -> 3
 *     |         ^
 *     +----> 2--+
 */

import java.util.*;

public class j03TopologicalSort {

    /**
     * Approach 1: DFS-based Topological Sort
     * 
     * Intuition:
     * - The core idea is to visit each node and recursively visit all its descendants before
     *   adding the node itself to the result. This ensures that all dependencies (edges from
     *   the node) are resolved before the node is placed in the ordering.
     * - By reversing the post-order of the DFS traversal, we obtain a valid topological order.
     * - This approach leverages the property that in a DAG, there are no cycles, so we can
     *   always finish visiting all descendants before the node itself.
     * - DFS is chosen because it naturally explores all paths from a node before backtracking,
     *   which aligns with the requirements of topological sorting.
     * 
     * Explanation:
     * - Step 1: Build an adjacency list from the edge list for efficient neighbor lookup.
     * - Step 2: For each unvisited node, perform a DFS. Mark nodes as visited to avoid cycles
     *   and redundant work.
     * - Step 3: In the DFS, after visiting all neighbors, add the node to the result list.
     *   This ensures all dependencies are already in the result.
     * - Step 4: After all nodes are processed, reverse the result list to get the correct
     *   topological order (since nodes are added post-order).
     * - Example: For edges [[0,1],[0,2],[1,2],[2,3]], DFS from 0 visits 1, then 2, then 3,
     *   and adds them in the order 3,2,1,0. Reversing gives 0,1,2,3.
     * 
     * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges,
     *   since each node and edge is visited once.
     * Space Complexity: O(V + E) for the adjacency list and visited array.
     * 
     * @param V      Number of vertices (0-indexed)
     * @param edges  List of directed edges [from, to]
     * @return       Topological order as ArrayList<Integer>
     */
    public static ArrayList<Integer> topoSortDFS(int V, int[][] edges) {
        // Build adjacency list for the graph
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each vertex
        for (int[] edge : edges)
            adj[edge[0]].add(edge[1]); // Add directed edge from 'from' to 'to'

        ArrayList<Integer> out = new ArrayList<>(); // Stores the topological order
        boolean[] visited = new boolean[V]; // Tracks visited nodes
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // Start DFS from every unvisited node
                dfsUtil(adj, visited, out, i);
            }
        }
        Collections.reverse(out); // Reverse to get correct topological order
        return out;
    }

    /**
     * Helper Method: DFS Utility
     * 
     * Intuition:
     * - This helper method ensures that all descendants of a node are visited before the node
     *   itself is added to the result. This is the essence of post-order DFS for topological sort.
     * - It supports the main solution by recursively traversing the graph and building the order.
     * 
     * Explanation:
     * - Step 1: If the node is already visited, return immediately to avoid cycles/redundancy.
     * - Step 2: Mark the current node as visited.
     * - Step 3: Recursively visit all unvisited neighbors (descendants).
     * - Step 4: After all neighbors are processed, add the current node to the result list.
     *   This ensures all dependencies are already included.
     * - Handles edge cases where a node has no outgoing edges (added immediately after marking visited).
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param adj     Adjacency list
     * @param visited Visited array
     * @param out     Output list
     * @param src     Current node
     */
    private static void dfsUtil(ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> out, int src) {
        if (visited[src])
            return; // Node already processed
        visited[src] = true; // Mark node as visited
        for (int neb : adj[src]) {
            if (!visited[neb]) {
                // Recursively visit all unvisited neighbors
                dfsUtil(adj, visited, out, neb);
            }
        }
        out.add(src); // Add node after all its neighbors (post-order)
    }

    /**
     * Approach 2: BFS-based Topological Sort (Kahn's Algorithm)
     * 
     * Intuition:
     * - The key insight is that nodes with in-degree 0 have no dependencies and can be safely
     *   placed at the start of the topological order. By removing these nodes and updating the
     *   in-degrees of their neighbors, we iteratively build the order.
     * - This approach is suitable for detecting cycles (if not all nodes are processed) and is
     *   often more intuitive for problems involving dependency resolution.
     * 
     * Explanation:
     * - Step 1: Build the adjacency list and compute the in-degree for each node.
     * - Step 2: Initialize a queue with all nodes having in-degree 0 (no dependencies).
     * - Step 3: While the queue is not empty, remove a node, add it to the result, and decrease
     *   the in-degree of its neighbors. If any neighbor's in-degree becomes 0, add it to the queue.
     * - Step 4: Continue until the queue is empty. If the result contains all nodes, a valid
     *   topological order is found. Otherwise, the graph has a cycle.
     * - Example: For edges [[0,1],[0,2],[1,2],[2,3]], in-degrees are [0,1,2,1]. Start with 0,
     *   process and update in-degrees, resulting in order [0,1,2,3].
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     * 
     * @param V      Number of vertices (0-indexed)
     * @param edges  List of directed edges [from, to]
     * @return       Topological order as ArrayList<Integer>
     */
    public static ArrayList<Integer> topoSortBFS(int V, int[][] edges) {
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

        ArrayList<Integer> out = new ArrayList<>(); // Stores the topological order
        Queue<Integer> q = new ArrayDeque<>(); // Queue for BFS
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i); // Add all nodes with in-degree 0 to the queue
        }
        while (!q.isEmpty()) {
            int node = q.poll(); // Remove node with in-degree 0
            out.add(node); // Add to result as it has no remaining dependencies
            for (int neb : adj[node]) {
                indegree[neb]--; // Remove edge and update in-degree
                if (indegree[neb] == 0)
                    q.add(neb); // If neighbor now has in-degree 0, add to queue
            }
        }
        // If out.size() < V, the graph has a cycle (not a DAG)
        return out;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = { {0,1},{0,2},{1,2},{2,3} };
        System.out.println("DFS: " + topoSortDFS(4, edges1));
        System.out.println("BFS: " + topoSortBFS(4, edges1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {};
        System.out.println("DFS: " + topoSortDFS(1, edges2));
        System.out.println("BFS: " + topoSortBFS(1, edges2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] edges3 = { {0,1} };
        System.out.println("DFS: " + topoSortDFS(2, edges3));
        System.out.println("BFS: " + topoSortBFS(2, edges3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = { {0,1},{1,2},{2,3},{3,4} };
        System.out.println("DFS: " + topoSortDFS(5, edges4));
        System.out.println("BFS: " + topoSortBFS(5, edges4));
    }
}