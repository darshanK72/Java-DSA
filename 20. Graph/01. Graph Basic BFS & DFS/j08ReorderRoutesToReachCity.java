/**
 * LeetCode 1466. Reorder Routes to Make All Paths Lead to the City Zero
 * 
 * Problem Statement:
 *     There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network forms a tree). Roads are represented by connections where connections[i] = [a, b] represents a road from city a to city b. The road may only be traversed from a to b (not the other way around).
 *     You need to reorder the roads in the minimum number of edges so that each city can reach city 0. Return the minimum number of edges you must reverse.
 * 
 * Input:
 *     - n (1 <= n <= 5 * 10^4): Number of cities
 *     - connections (n-1 x 2 integer array): Each element [a, b] represents a directed road from a to b
 * 
 * Output:
 *     - Integer: Minimum number of edges to reverse so that every city can reach city 0
 * 
 * Example:
 *     Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 *     Output: 3
 * 
 *     Explanation:
 *     Reverse the edges [0,1], [1,3], [4,5] so that all cities can reach city 0.
 */

import java.util.*;

public class j08ReorderRoutesToReachCity {

    /**
     * Helper class to represent an edge with direction flag
     * flag = true: original direction (from -> to)
     * flag = false: reverse direction (to -> from)
     */
    static class Edge {
        int to;
        boolean flag;
        Edge(int to, boolean flag) {
            this.to = to;
            this.flag = flag;
        }
    }

    /**
     * Approach: DFS Traversal with Edge Direction Tracking
     * 
     * Intuition:
     * - Treat the city network as an undirected tree, but mark the original direction of each edge.
     * - Start DFS from city 0. For each outgoing edge (original direction), increment the reorder count.
     * - For each incoming edge (reverse direction), no need to reorder.
     * - Visit all cities, ensuring every city can reach city 0 by reversing necessary edges.
     * 
     * Explanation:
     * - Step 1: Build an adjacency list for all cities, storing both directions for each edge with a flag.
     * - Step 2: Use DFS to traverse from city 0, marking visited cities.
     * - Step 3: For each neighbor, if the edge is in the original direction, increment reorder count.
     * - Step 4: Recursively visit all unvisited neighbors.
     * - Step 5: Return the total reorder count after traversal.
     * 
     * Time Complexity: O(n) where n is the number of cities (each edge visited once)
     * Space Complexity: O(n) for adjacency list and visited array
     * 
     * @param n         Number of cities (1 <= n <= 5*10^4)
     * @param connections 2D array of directed edges
     * @return         Minimum number of edges to reverse
     */
    public static int minReorder(int n, int[][] connections) {
        // Build adjacency list with direction flags
        ArrayList<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] conn : connections) {
            int from = conn[0], to = conn[1];
            adj[from].add(new Edge(to, true));   // original direction
            adj[to].add(new Edge(from, false));  // reverse direction
        }
        boolean[] visited = new boolean[n];
        int[] reorder = new int[1];
        dfs(adj, visited, reorder, 0);
        return reorder[0];
    }

    /**
     * Helper Method: DFS Traversal
     * 
     * Intuition:
     * - Traverse the tree, counting edges that need to be reversed.
     * 
     * Explanation:
     * - For each unvisited neighbor, if the edge is in the original direction, increment reorder count.
     * - Recursively visit all neighbors.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param adj      Adjacency list with direction flags
     * @param visited  Visited cities
     * @param reorder  Array holding reorder count
     * @param node     Current city
     */
    private static void dfs(ArrayList<Edge>[] adj, boolean[] visited, int[] reorder, int node) {
        // Mark current node as visited
        visited[node] = true;
        for (Edge e : adj[node]) {
            if (!visited[e.to]) {
                if (e.flag) {
                    // Edge needs to be reversed
                    reorder[0]++;
                }
                dfs(adj, visited, reorder, e.to);
            }
        }
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=6, [[0,1],[1,3],[2,3],[4,0],[4,5]], Expected: 3, Output: " +
                minReorder(6, new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}}));
        System.out.println("Input: n=5, [[1,0],[1,2],[3,2],[3,4]], Expected: 2, Output: " +
                minReorder(5, new int[][]{{1,0},{1,2},{3,2},{3,4}}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, [], Expected: 0, Output: " +
                minReorder(1, new int[][]{}));
        System.out.println("Input: n=2, [[1,0]], Expected: 0, Output: " +
                minReorder(2, new int[][]{{1,0}}));
        System.out.println("Input: n=2, [[0,1]], Expected: 1, Output: " +
                minReorder(2, new int[][]{{0,1}}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n = 10000;
        int[][] connections = new int[n-1][2];
        for (int i = 0; i < n-1; i++) connections[i] = new int[]{i, i+1};
        System.out.println("Input: n=10000, chain, Output: " + minReorder(n, connections));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=3, [[2,0],[2,1]], Expected: 1, Output: " +
                minReorder(3, new int[][]{{2,0},{2,1}}));
        System.out.println("Input: n=3, [[1,0],[2,0]], Expected: 0, Output: " +
                minReorder(3, new int[][]{{1,0},{2,0}}));
    }
}
