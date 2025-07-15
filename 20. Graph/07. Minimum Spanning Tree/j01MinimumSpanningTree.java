/**
 * GFG Practice. Minimum Spanning Tree (MST) - Prim's Algorithm
 *
 * Problem Statement:
 *     Given a connected, undirected, weighted graph with V vertices and E edges,
 *     find the sum of weights of the edges of the Minimum Spanning Tree (MST).
 *
 * Input:
 *     - V (1 <= V <= 10^4): Number of vertices
 *     - E (0 <= E <= 10^5): Number of edges
 *     - adj: List<List<int[]>> adjacency list, where adj.get(u) contains int[]{v, w}
 *       representing an edge from u to v with weight w (0 <= u, v < V, 1 <= w <= 10^4)
 *
 * Output:
 *     - int: Sum of weights of the MST
 *
 * Example:
 *     Input: V = 4, E = 5, edges = [[0,1,1],[0,2,2],[0,3,2],[1,2,3],[2,3,3]]
 *     Output: 5
 *
 *     Explanation:
 *         MST edges: (0-1, weight 1), (0-2, weight 2), (0-3, weight 2)
 *         Total weight = 1 + 2 + 2 = 5
 */

import java.util.*;

public class j01MinimumSpanningTree {

    /**
     * Approach: Prim's Algorithm using Min-Heap (PriorityQueue)
     *
     * Intuition:
     * - Always pick the minimum weight edge that connects a new vertex to the MST.
     * - Use a min-heap to efficiently get the next minimum edge.
     *
     * Explanation:
     * - Start from vertex 0, push (0,0) to the priority queue.
     * - While the queue is not empty, pop the smallest edge.
     * - If the vertex is already visited, skip.
     * - Otherwise, add its weight to the MST sum and mark as visited.
     * - For all adjacent edges, push them to the queue.
     * - Repeat until all vertices are included.
     *
     * Time Complexity: O((V+E) log V) (each edge/vertex heap operation)
     * Space Complexity: O(V+E) (adjacency list, visited array, heap)
     *
     * @param V    Number of vertices (1 <= V <= 10^4)
     * @param E    Number of edges (0 <= E <= 10^5)
     * @param adj  Adjacency list: List<List<int[]>>, each int[]{v, w}
     * @return     int: Sum of weights of the MST
     */
    public static int spanningTree(int V, int E, List<List<int[]>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { 0, 0 }); // Start from vertex 0 with weight 0
        int ans = 0;
        int count = 0;
        while (!pq.isEmpty() && count < V) {
            int[] node = pq.poll();
            if (visited[node[0]])
                continue; // Skip already included vertices
            visited[node[0]] = true;
            ans += node[1];
            count++;
            for (int[] neb : adj.get(node[0])) {
                if (!visited[neb[0]]) {
                    pq.add(new int[]{neb[0], neb[1]});
                }
            }
        }
        // If not all vertices are included, the graph is not connected
        if (count < V) return -1;
        return ans;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int V1 = 4, E1 = 5;
        int[][] edges1 = {{0,1,1},{0,2,2},{0,3,2},{1,2,3},{2,3,3}};
        List<List<int[]>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());
        for (int[] e : edges1) {
            adj1.get(e[0]).add(new int[]{e[1], e[2]});
            adj1.get(e[1]).add(new int[]{e[0], e[2]});
        }
        System.out.println("Input: V=4, E=5, edges=" + Arrays.deepToString(edges1));
        System.out.println("Expected: 5, Output: " + spanningTree(V1, E1, adj1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int V2 = 1, E2 = 0;
        List<List<int[]>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>());
        System.out.println("Input: V=1, E=0, edges=[]");
        System.out.println("Expected: 0, Output: " + spanningTree(V2, E2, adj2));
        int V3 = 2, E3 = 0;
        List<List<int[]>> adj3 = new ArrayList<>();
        adj3.add(new ArrayList<>()); adj3.add(new ArrayList<>());
        System.out.println("Input: V=2, E=0, edges=[]");
        System.out.println("Expected: -1, Output: " + spanningTree(V3, E3, adj3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int V4 = 10000, E4 = 0;
        List<List<int[]>> adj4 = new ArrayList<>();
        for (int i = 0; i < V4; i++) adj4.add(new ArrayList<>());
        System.out.println("Input: V=10000, E=0, edges=[]");
        System.out.println("Expected: -1, Output: " + spanningTree(V4, E4, adj4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int V5 = 3, E5 = 3;
        int[][] edges5 = {{0,1,1},{1,2,1},{0,2,2}};
        List<List<int[]>> adj5 = new ArrayList<>();
        for (int i = 0; i < V5; i++) adj5.add(new ArrayList<>());
        for (int[] e : edges5) {
            adj5.get(e[0]).add(new int[]{e[1], e[2]});
            adj5.get(e[1]).add(new int[]{e[0], e[2]});
        }
        System.out.println("Input: V=3, E=3, edges=" + Arrays.deepToString(edges5));
        System.out.println("Expected: 2, Output: " + spanningTree(V5, E5, adj5));
    }
}
