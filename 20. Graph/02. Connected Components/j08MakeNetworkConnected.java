/**
 * LeetCode 1319. Number of Operations to Make Network Connected
 * 
 * Problem Statement:
 *     There are n computers numbered from 0 to n-1 connected by ethernet cables.
 *     Connections[i] = [a, b] represents a cable between computers a and b.
 *     Any computer can reach any other directly or indirectly via cables.
 *     You can remove cables and place them between any two computers.
 *     Return the minimum number of operations to connect all computers, or -1 if impossible.
 * 
 * Input:
 *     - n (1 <= n <= 10^5): Number of computers
 *     - connections (connections.length <= 10^5): Each is [a, b] (0 <= a, b < n)
 * 
 * Output:
 *     - Integer: Minimum number of operations to connect all computers, or -1 if impossible
 * 
 * Example:
 *     Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 *     Output: 1
 *     Explanation:
 *         There are 2 disconnected components. We need 1 operation to connect them.
 * 
 *     Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 *     Output: 2
 *     Explanation:
 *         There are 3 disconnected components. We need 2 operations to connect them.
 */

import java.util.*;

public class j08MakeNetworkConnected {

    /**
     * Approach: DFS for Connected Components
     * 
     * Intuition:
     * - If there are fewer than n-1 cables, it's impossible to connect all computers.
     * - Otherwise, count the number of connected components using DFS.
     * - To connect k components, we need at least k-1 extra cables.
     * 
     * Explanation:
     * - Step 1: Check if connections are enough (at least n-1 cables).
     * - Step 2: Build adjacency list for the network graph.
     * - Step 3: Use DFS to count connected components.
     * - Step 4: Return (components - 1) as the answer.
     * 
     * Time Complexity: O(n + m), where n = number of computers, m = number of cables
     * Space Complexity: O(n + m), for adjacency list and visited array
     * 
     * @param n           Number of computers (1 <= n <= 10^5)
     * @param connections List of cable connections (connections.length <= 10^5)
     * @return            Minimum number of operations to connect all computers, or -1 if impossible
     */
    public static int makeConnected(int n, int[][] connections) {
        // If not enough cables, impossible to connect all computers
        if (connections.length < n - 1)
            return -1;
        // Build adjacency list for the network
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int[] conn : connections) {
            int from = conn[0], to = conn[1];
            adj[from].add(to);
            adj[to].add(from);
        }
        // Track visited computers
        boolean[] visited = new boolean[n];
        int components = 0;
        // Count connected components using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(adj, visited, i);
            }
        }
        // Need (components - 1) operations to connect all
        return components - 1;
    }

    /**
     * Helper Method: DFS to mark all nodes in a component
     * 
     * Intuition:
     * - Traverse all reachable nodes from a source to mark the component.
     * 
     * Explanation:
     * - Standard DFS traversal to visit all nodes in the same component.
     * 
     * Time Complexity: O(n + m)
     * Space Complexity: O(n)
     * 
     * @param adj     Adjacency list of the network
     * @param visited Visited array
     * @param src     Current node
     */
    private static void dfs(ArrayList<Integer>[] adj, boolean[] visited, int src) {
        // Mark current node as visited
        visited[src] = true;
        // Visit all unvisited neighbors
        for (int neighbor : adj[src]) {
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=4, [[0,1],[0,2],[1,2]], Expected: 1, Output: " +
            makeConnected(4, new int[][]{{0,1},{0,2},{1,2}}));
        System.out.println("Input: n=6, [[0,1],[0,2],[0,3],[1,2],[1,3]], Expected: 2, Output: " +
            makeConnected(6, new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, [], Expected: 0, Output: " +
            makeConnected(1, new int[][]{}));
        System.out.println("Input: n=2, [], Expected: -1, Output: " +
            makeConnected(2, new int[][]{}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=2, [[0,1]], Expected: 0, Output: " +
            makeConnected(2, new int[][]{{0,1}}));
        System.out.println("Input: n=3, [[0,1]], Expected: -1, Output: " +
            makeConnected(3, new int[][]{{0,1}}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=5, [[0,1],[0,2],[3,4],[2,3],[1,4]], Expected: 0, Output: " +
            makeConnected(5, new int[][]{{0,1},{0,2},{3,4},{2,3},{1,4}}));
        System.out.println("Input: n=5, [[0,1],[2,3]], Expected: -1, Output: " +
            makeConnected(5, new int[][]{{0,1},{2,3}}));
    }
}
