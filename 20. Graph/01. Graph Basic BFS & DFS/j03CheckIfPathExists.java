/**
 * LeetCode 1971. Find if Path Exists in Graph
 * 
 * Problem Statement:
 *     There is a bi-directional (undirected) graph with n nodes, where each
 *     node is labeled from 0 to n - 1. You are given a 2D integer array edges
 *     where edges[i] = [ui, vi] denotes a bi-directional edge between node ui
 *     and node vi. Given two integers source and destination, return true if
 *     there is a valid path from source to destination, or false otherwise.
 * 
 * Input:
 *     - n (1 <= n <= 2 * 10^5): Number of nodes
 *     - edges (0 <= edges.length <= 2 * 10^5): List of edges
 *     - source (0 <= source < n): Start node
 *     - destination (0 <= destination < n): End node
 * 
 * Output:
 *     - true if a valid path exists from source to destination, else false
 * 
 * Example:
 *     Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]],
 *            source = 0, destination = 5
 *     Output: false
 * 
 *     Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3],[2,3]],
 *            source = 0, destination = 5
 *     Output: true
 * 
 *     Explanation:
 *     - In the first example, there is no path from 0 to 5.
 *     - In the second example, a path exists: 0 -> 2 -> 3 -> 5.
 */

import java.util.*;

public class j03CheckIfPathExists {
    /**
     * Approach: DFS (Depth-First Search)
     * 
     * Intuition:
     * - The problem asks whether there is a path between two nodes in an
     *   undirected graph. This is a classic connectivity check, which can be
     *   solved by traversing the graph from the source node and seeing if we
     *   can reach the destination node.
     * - Depth-First Search (DFS) is a natural fit for this, as it explores as
     *   far as possible along each branch before backtracking, ensuring all
     *   possible paths are checked.
     * - We use an adjacency list for efficient neighbor lookups, and a visited
     *   array to avoid cycles and redundant work.
     * 
     * Explanation:
     * - First, we build the adjacency list for the undirected graph. Each node
     *   has a list of its neighbors, constructed from the edges array. This
     *   allows O(1) access to all neighbors of any node.
     * - We then initiate a DFS from the source node. The DFS recursively visits
     *   each unvisited neighbor, marking nodes as visited to prevent infinite
     *   loops in cyclic graphs.
     * - If at any point the destination node is reached, we return true
     *   immediately, as a valid path exists.
     * - If all possible paths are explored and the destination is not reached,
     *   we return false.
     * 
     * Time Complexity: O(n + e)
     *   - Building the adjacency list takes O(e), where e is the number of edges.
     *   - In the worst case, DFS visits every node (O(n)) and traverses every
     *     edge (O(e)), since each edge is checked at most twice (once from each
     *     endpoint in an undirected graph).
     *   - Thus, the total time is O(n + e).
     * 
     * Space Complexity: O(n + e)
     *   - The adjacency list requires O(n + e) space: O(n) for the array of
     *     lists, and O(e) for storing all edges.
     *   - The visited array uses O(n) space.
     *   - The recursion stack in DFS can be up to O(n) in the worst case (for a
     *     path graph), but this does not exceed the O(n + e) bound.
     * 
     * @param n           Number of nodes (0-based)
     * @param edges       Edge list
     * @param source      Start node
     * @param destination End node
     * @return            True if path exists, else false
     */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        // Initialize adjacency list: each node gets a list of neighbors
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build the undirected graph by adding both directions for each edge
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }

        // Start DFS from source, using a visited array to avoid cycles
        return checkPathExistsDFS(adj, new boolean[n], source, destination);
    }

    /**
     * Helper Method: DFS Traversal
     * 
     * Intuition:
     * - The DFS explores all possible paths from the current node. If the
     *   destination is found during traversal, we know a path exists.
     * - Marking nodes as visited is crucial to prevent revisiting nodes and
     *   getting stuck in cycles, which are common in undirected graphs.
     * 
     * Explanation:
     * - If the current node is the destination, we have found a valid path and
     *   return true.
     * - If the current node has already been visited, we return false to avoid
     *   redundant work and infinite recursion.
     * - Otherwise, mark the current node as visited and recursively check all
     *   its neighbors. If any recursive call returns true, propagate that result
     *   up the call stack.
     * - If none of the neighbors lead to the destination, return false.
     * 
     * Time Complexity: O(n + e)
     *   - In the worst case, every node and every edge is visited once. Each
     *     node is marked visited at most once, and each edge is traversed at
     *     most twice (once from each endpoint). Thus, the total work is
     *     proportional to the sum of nodes and edges.
     * 
     * Space Complexity: O(n)
     *   - The visited array uses O(n) space.
     *   - The recursion stack can be up to O(n) deep in the worst case (e.g.,
     *     for a linear chain of nodes), but does not exceed O(n).
     *   - The adjacency list is not counted here as it is part of the main
     *     method's space analysis.
     * 
     * @param adj         Adjacency list
     * @param visited     Visited nodes array
     * @param source      Current node
     * @param destination Target node
     * @return            True if path exists, else false
     */
    private static boolean checkPathExistsDFS(ArrayList<Integer>[] adj, boolean[] visited,
            int source, int destination) {
        // If source is destination, path exists
        if (source == destination)
            return true;
        // If already visited, no need to explore again
        if (visited[source])
            return false;
        // Mark current node as visited
        visited[source] = true;
        // Explore all neighbors recursively
        for (int node : adj[source]) {
            if (checkPathExistsDFS(adj, visited, node, destination))
                return true;
        }
        // No path found from this node
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case (path exists)
        System.out.println("\nBasic Test Case:");
        int n1 = 6;
        int[][] edges1 = {{0,1},{0,2},{3,5},{5,4},{4,3},{2,3}};
        System.out.println("Input: n=6, source=0, dest=5, Expected: true, Output: " +
            validPath(n1, edges1, 0, 5));

        // Test Case 2: Basic case (no path)
        System.out.println("\nBasic Test Case (No path):");
        int[][] edges2 = {{0,1},{0,2},{3,5},{5,4},{4,3}};
        System.out.println("Input: n=6, source=0, dest=5, Expected: false, Output: " +
            validPath(n1, edges2, 0, 5));

        // Test Case 3: Edge case (source == destination)
        System.out.println("\nEdge Case (Source == Destination):");
        System.out.println("Input: n=3, source=2, dest=2, Expected: true, Output: " +
            validPath(3, new int[][]{{0,1},{1,2}}, 2, 2));

        // Test Case 4: Boundary case (no edges)
        System.out.println("\nBoundary Case (No edges):");
        System.out.println("Input: n=2, source=0, dest=1, Expected: false, Output: " +
            validPath(2, new int[][]{}, 0, 1));

        // Test Case 5: Special case (fully connected)
        System.out.println("\nSpecial Case (Fully connected):");
        int[][] edges3 = {{0,1},{0,2},{1,2}};
        System.out.println("Input: n=3, source=0, dest=2, Expected: true, Output: " +
            validPath(3, edges3, 0, 2));
    }
}
