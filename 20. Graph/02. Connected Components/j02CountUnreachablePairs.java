/**
 * LeetCode 2316. Count Unreachable Pairs of Nodes in an Undirected Graph
 *
 * Problem Statement:
 *     You are given an integer n. There is an undirected graph with n nodes,
 *     numbered from 0 to n - 1. You are given a 2D integer array edges where
 *     edges[i] = [ai, bi] denotes that there exists an undirected edge
 *     connecting nodes ai and bi.
 *     Return the number of pairs of nodes (u, v) such that u < v and u and v
 *     are not reachable from each other.
 *
 * Input:
 *     - n: Number of nodes in the graph (1 <= n <= 10^5)
 *     - edges: List of undirected edges (0 <= ai, bi < n)
 *
 * Output:
 *     - Number of pairs of nodes that are unreachable from each other
 *
 * Example:
 *     Input: n = 3, edges = [[0,1],[0,2]]
 *     Output: 0
 *     Explanation: All pairs are reachable.
 *
 *     Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 *     Output: 14
 *     Explanation: There are 2 connected components: {0,2,4,5}, {1,6}, {3}
 *     Unreachable pairs: 4*2 + 4*1 + 2*1 = 8 + 4 + 2 = 14
 */

import java.util.*;

public class j02CountUnreachablePairs {

    /**
     * Approach: Connected Components + Counting
     *
     * Intuition:
     * - Nodes in the same connected component are reachable from each other.
     * - Nodes in different components are not reachable.
     * - For each component of size s, there are s*(n-s) unreachable pairs
     *   (each node in the component cannot reach any node outside).
     * - To avoid double counting, sum for all components and divide by 2.
     *
     * Explanation:
     * - Step 1: Build adjacency list from edge list.
     * - Step 2: Use DFS to find the size of each connected component.
     * - Step 3: For each component, add s*(n-s) to the answer.
     * - Step 4: Divide the total by 2 to avoid double counting.
     *
     * Time Complexity: O(n + m), where n = number of nodes, m = number of edges
     * Space Complexity: O(n + m) for adjacency list and visited array
     *
     * @param n      Number of nodes (1 <= n <= 10^5)
     * @param edges  List of undirected edges
     * @return       Number of unreachable pairs
     */
    public static long countPairs(int n, int[][] edges) {
        // Handle edge case: no nodes
        if (n <= 1) return 0;

        // Build adjacency list
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            adj[from].add(to);
            adj[to].add(from);
        }

        boolean[] visited = new boolean[n];
        long pairs = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                long size = getComponentSize(adj, visited, i);
                pairs += size * (n - size);
            }
        }
        return pairs / 2; // Each pair counted twice
    }

    /**
     * Helper Method: DFS to get size of connected component
     *
     * Intuition:
     * - Explore all nodes in the component using DFS
     * - Count the number of nodes visited
     *
     * Explanation:
     * - Mark node as visited
     * - Recursively visit all unvisited neighbors
     * - Return total size
     *
     * Time Complexity: O(size of component)
     * Space Complexity: O(size of component) for recursion stack
     *
     * @param adj      Adjacency list
     * @param visited  Visited array
     * @param src      Current node
     * @return         Size of the connected component
     */
    private static int getComponentSize(ArrayList<Integer>[] adj, boolean[] visited, int src) {
        if (visited[src]) return 0;
        int size = 1;
        visited[src] = true;
        for (int neighbor : adj[src]) {
            size += getComponentSize(adj, visited, neighbor);
        }
        return size;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] edges1 = {{0,1},{0,2}};
        System.out.println("Input: n=3, edges=[[0,1],[0,2]], Expected: 0, Output: " + countPairs(3, edges1));

        int[][] edges2 = {{0,2},{0,5},{2,4},{1,6},{5,4}};
        System.out.println("Input: n=7, edges=[[0,2],[0,5],[2,4],[1,6],[5,4]], Expected: 14, Output: " + countPairs(7, edges2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, edges=[], Expected: 0, Output: " + countPairs(1, new int[][]{}));
        System.out.println("Input: n=2, edges=[], Expected: 1, Output: " + countPairs(2, new int[][]{}));
        System.out.println("Input: n=4, edges=[], Expected: 6, Output: " + countPairs(4, new int[][]{}));

        // Test Case 3: All nodes connected
        System.out.println("\nAll Nodes Connected:");
        int[][] edges3 = {{0,1},{1,2},{2,3}};
        System.out.println("Input: n=4, edges=[[0,1],[1,2],[2,3]], Expected: 0, Output: " + countPairs(4, edges3));

        // Test Case 4: Multiple components
        System.out.println("\nMultiple Components:");
        int[][] edges4 = {{0,1},{2,3}};
        System.out.println("Input: n=4, edges=[[0,1],[2,3]], Expected: 4, Output: " + countPairs(4, edges4));

        // Test Case 5: Large input (boundary)
        System.out.println("\nBoundary Case:");
        int n = 100000;
        int[][] noEdges = new int[0][2];
        System.out.println("Input: n=100000, edges=[], Expected: 4999950000, Output: " + countPairs(n, noEdges));
    }
}