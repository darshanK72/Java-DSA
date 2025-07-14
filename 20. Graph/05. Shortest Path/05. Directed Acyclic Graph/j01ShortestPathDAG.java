/**
 * GFG/Custom: Shortest Path in Directed Acyclic Graph (DAG) using Topological Sort
 *
 * Problem Statement:
 *     Given a weighted directed acyclic graph (DAG) with V vertices and E edges,
 *     find the shortest path from a given source vertex to all other vertices.
 *     The graph is represented as an edge list [from, to, weight].
 *
 * Input:
 *     - V (1 <= V <= 10^5): Number of vertices (0-indexed)
 *     - edges (0 <= edges.length <= 10^5): List of directed edges [from, to, weight]
 *     - src (0 <= src < V): Source vertex
 *
 * Output:
 *     - int[]: Array of shortest distances from src to every vertex (Integer.MAX_VALUE if unreachable)
 *
 * Example:
 *     Input: V = 6, edges = [[0,1,2],[0,4,1],[1,2,3],[4,2,2],[4,5,4],[2,3,6],[5,3,1]], src = 0
 *     Output: [0,2,3,6,1,5]
 *
 *     Explanation:
 *         0 -> 1 (2), 0 -> 4 (1)
 *         1 -> 2 (3), 4 -> 2 (2), 4 -> 5 (4)
 *         2 -> 3 (6), 5 -> 3 (1)
 *         Shortest paths from 0: 0->1 (2), 0->4 (1), 0->4->2 (1+2=3), 0->4->5 (1+4=5), 0->4->5->3 (1+4+1=6)
 */

import java.util.*;

public class j01ShortestPathDAG {

    /**
     * Approach: Topological Sort + Relaxation
     *
     * Intuition:
     * - In a DAG, topological order ensures that for every edge u->v, u comes before v.
     * - By processing nodes in topological order, we guarantee that when we process a node,
     *   all shortest paths to it are already computed.
     * - We relax all outgoing edges from each node in topological order to update shortest paths.
     *
     * Explanation:
     * - Step 1: Build adjacency list from edge list for efficient traversal.
     * - Step 2: Perform topological sort (DFS or Kahn's algorithm) to get node order.
     * - Step 3: Initialize distance array with Integer.MAX_VALUE, set src to 0.
     * - Step 4: For each node in topological order, relax all outgoing edges.
     * - Step 5: Return the distance array.
     *
     * Time Complexity: O(V + E), since each node and edge is processed once.
     * Space Complexity: O(V + E), for adjacency list, visited array, and distance array.
     *
     * @param V      Number of vertices (0-indexed)
     * @param edges  List of directed edges [from, to, weight]
     * @param src    Source vertex (0 <= src < V)
     * @return       Array of shortest distances from src to every vertex
     */
    public static int[] shortestPathDAG(int V, int[][] edges, int src) {
        // Build adjacency list: each node -> list of (neighbor, weight)
        List<int[]>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        // Get topological order
        List<Integer> topo = topoSortDFS(V, adj);
        // Initialize distances
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        // Relax edges in topological order
        for (int u : topo) {
            if (dist[u] == Integer.MAX_VALUE) continue;
            for (int[] neb : adj[u]) {
                int v = neb[0], w = neb[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        return dist;
    }

    /**
     * Helper Method: Topological Sort (DFS)
     *
     * Intuition:
     * - Standard DFS-based topological sort: visit all descendants before the node itself.
     * - Supports main solution by providing correct node order for relaxation.
     *
     * Explanation:
     * - Mark node as visited, recursively visit all unvisited neighbors.
     * - After all neighbors, add node to output list (post-order).
     * - Reverse output for topological order.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     *
     * @param V    Number of vertices
     * @param adj  Adjacency list
     * @return     List of nodes in topological order
     */
    private static List<Integer> topoSortDFS(int V, List<int[]>[] adj) {
        boolean[] visited = new boolean[V];
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs(i, adj, visited, out);
        }
        Collections.reverse(out);
        return out;
    }

    private static void dfs(int u, List<int[]>[] adj, boolean[] visited, List<Integer> out) {
        visited[u] = true;
        for (int[] neb : adj[u]) {
            if (!visited[neb[0]]) dfs(neb[0], adj, visited, out);
        }
        out.add(u);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic
        System.out.println("\nBasic Test Cases:");
        int V1 = 6;
        int[][] edges1 = { {0,1,2},{0,4,1},{1,2,3},{4,2,2},{4,5,4},{2,3,6},{5,3,1} };
        int src1 = 0;
        System.out.println("Input: V="+V1+", edges="+Arrays.deepToString(edges1)+", src="+src1);
        System.out.println("Expected: [0,2,3,6,1,5], Output: " + Arrays.toString(shortestPathDAG(V1, edges1, src1)));

        // Test Case 2: Edge (unreachable nodes)
        System.out.println("\nEdge Cases:");
        int V2 = 4;
        int[][] edges2 = { {0,1,1} };
        int src2 = 0;
        System.out.println("Input: V="+V2+", edges="+Arrays.deepToString(edges2)+", src="+src2);
        System.out.println("Expected: [0,1,2147483647,2147483647], Output: " + Arrays.toString(shortestPathDAG(V2, edges2, src2)));

        // Test Case 3: Boundary (single node)
        System.out.println("\nBoundary Cases:");
        int V3 = 1;
        int[][] edges3 = {};
        int src3 = 0;
        System.out.println("Input: V="+V3+", edges="+Arrays.deepToString(edges3)+", src="+src3);
        System.out.println("Expected: [0], Output: " + Arrays.toString(shortestPathDAG(V3, edges3, src3)));

        // Test Case 4: Special (multiple paths)
        System.out.println("\nSpecial Cases:");
        int V4 = 5;
        int[][] edges4 = { {0,1,10},{0,2,3},{1,2,1},{2,1,4},{2,3,2},{1,3,2},{3,4,2},{4,3,9} };
        int src4 = 0;
        System.out.println("Input: V="+V4+", edges="+Arrays.deepToString(edges4)+", src="+src4);
        System.out.println("Expected: [0,7,3,5,7], Output: " + Arrays.toString(shortestPathDAG(V4, edges4, src4)));
    }
} 