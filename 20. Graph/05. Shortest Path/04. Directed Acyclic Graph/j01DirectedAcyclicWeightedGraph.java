/**
 * GFG Practice. Shortest Path in Directed Acyclic Graph (DAG)
 *
 * Problem Statement:
 *     Given a Directed Acyclic Graph (DAG) with V vertices and E edges, where each
 *     edge has a weight, find the shortest path from vertex 0 to all other vertices.
 *     If a vertex is unreachable from 0, return -1 for that vertex.
 *
 * Input:
 *     - V (1 <= V <= 10^4): Number of vertices
 *     - E (0 <= E <= 10^5): Number of edges
 *     - edges: int[E][3], where each edge is [from, to, weight] (0 <= from, to < V,
 *       1 <= weight <= 10^4)
 *
 * Output:
 *     - int[V]: Array of shortest distances from vertex 0 to every vertex. If not
 *       reachable, distance is -1.
 *
 * Example:
 *     Input: V = 6, E = 7, edges = [[0,1,2],[0,4,1],[1,2,3],[4,2,2],[4,5,4],[2,3,6],[5,3,1]]
 *     Output: [0,2,3,6,1,5]
 *
 *     Explanation:
 *         0 --2--> 1 --3--> 2 --6--> 3
 *          \       |         ^
 *           \      v         |
 *            1--> 4 --2-----/ 
 *                  |         
 *                  4         
 *                  v         
 *                  5 --1-----
 *
 *         Shortest path from 0 to 3 is 0->4->5->3 = 1+4+1 = 6
 */

import java.util.*;

public class j01DirectedAcyclicWeightedGraph {

    /**
     * Helper class to represent an edge in the graph.
     *
     * Intuition:
     * - Encapsulates destination and weight for each edge.
     * - Simplifies adjacency list representation.
     *
     * Explanation:
     * - Each Edge object stores the 'to' vertex and the edge 'weight'.
     * - Used in adjacency list for efficient traversal.
     *
     * Time Complexity: O(1) per edge
     * Space Complexity: O(1) per edge
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
     * Approach: Topological Sort + Relaxation
     *
     * Intuition:
     * - In a DAG, shortest paths can be found by processing vertices in topological order.
     * - Relaxing edges in topological order ensures all dependencies are processed first.
     *
     * Explanation:
     * - Build adjacency list from edge list.
     * - Perform topological sort (DFS-based) to order vertices.
     * - Initialize distances: dist[0]=0, others=INF.
     * - For each vertex in topological order, relax all outgoing edges.
     * - If a vertex is unreachable, its distance remains INF and is set to -1 at the end.
     *
     * Time Complexity: O(V+E) (topo sort + edge relaxation)
     * Space Complexity: O(V+E) (adjacency list, stack, visited, dist)
     *
     * @param V    Number of vertices (1 <= V <= 10^4)
     * @param E    Number of edges (0 <= E <= 10^5)
     * @param edges    Edge list: int[E][3], each [from, to, weight]
     * @return     int[V]: Shortest distances from 0 to all vertices, -1 if unreachable
     */
    public static int[] shortestPath(int V, int E, int[][] edges) {
        // Build adjacency list
        ArrayList<Edge>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            adj[from].add(new Edge(to, weight));
        }

        // Get topological order using DFS
        Stack<Integer> stack = topoSort(adj, V);
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // Distance to source is 0

        // Relax edges in topological order
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (dist[top] == Integer.MAX_VALUE)
                continue; // Skip unreachable nodes
            for (Edge edge : adj[top]) {
                // Relax edge if shorter path found
                dist[edge.to] = Math.min(dist[edge.to],dist[top] + edge.weight);
            }
        }

        // Set unreachable nodes to -1
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    /**
     * Helper Method: Topological Sort (DFS-based)
     *
     * Intuition:
     * - Topological order ensures all dependencies are processed before a node.
     *
     * Explanation:
     * - Standard DFS to visit all nodes, push to stack after visiting all neighbors.
     * - Stack gives reverse topological order.
     *
     * Time Complexity: O(V+E)
     * Space Complexity: O(V)
     *
     * @param adj    Adjacency list
     * @param V      Number of vertices
     * @return       Stack with topological order
     */
    private static Stack<Integer> topoSort(ArrayList<Edge>[] adj, int V) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoDFS(adj, stack, visited, i);
            }
        }
        return stack;
    }

    /**
     * Helper Method: DFS for Topological Sort
     *
     * Intuition:
     * - Recursively visit all neighbors before pushing node to stack.
     *
     * Explanation:
     * - Standard DFS, mark node visited, recurse on neighbors, push after all done.
     *
     * Time Complexity: O(V+E)
     * Space Complexity: O(V)
     *
     * @param adj      Adjacency list
     * @param stack    Stack for topological order
     * @param visited  Visited array
     * @param src      Current node
     */
    private static void topoDFS(ArrayList<Edge>[] adj, Stack<Integer> stack, boolean[] visited, int src) {
        if (visited[src])
            return;
        visited[src] = true;
        for (Edge edge : adj[src]) {
            topoDFS(adj, stack, visited, edge.to);
        }
        stack.add(src);
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {
            {0,1,2},{0,4,1},{1,2,3},{4,2,2},{4,5,4},{2,3,6},{5,3,1}
        };
        System.out.println("Input: V=6, E=7, edges=" + Arrays.deepToString(edges1));
        System.out.println("Expected: [0, 2, 3, 6, 1, 5], Output: " + Arrays.toString(shortestPath(6,7,edges1)));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {};
        System.out.println("Input: V=1, E=0, edges=[]");
        System.out.println("Expected: [0], Output: " + Arrays.toString(shortestPath(1,0,edges2)));
        int[][] edges3 = {{0,1,5}};
        System.out.println("Input: V=2, E=1, edges=[[0,1,5]]");
        System.out.println("Expected: [0, 5], Output: " + Arrays.toString(shortestPath(2,1,edges3)));
        int[][] edges4 = {{1,0,2}};
        System.out.println("Input: V=2, E=1, edges=[[1,0,2]]");
        System.out.println("Expected: [0, -1], Output: " + Arrays.toString(shortestPath(2,1,edges4)));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] edges5 = new int[0][3];
        System.out.println("Input: V=10000, E=0, edges=[]");
        int[] out5 = shortestPath(10000,0,edges5);
        System.out.println("Output[0]: " + out5[0] + ", Output[9999]: " + out5[9999]);

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges6 = {{0,1,1},{1,2,1},{2,3,1},{3,4,1}};
        System.out.println("Input: V=5, E=4, edges=[[0,1,1],[1,2,1],[2,3,1],[3,4,1]]");
        System.out.println("Expected: [0,1,2,3,4], Output: " + Arrays.toString(shortestPath(5,4,edges6)));
        int[][] edges7 = {{0,1,1},{1,2,1},{2,3,1},{3,4,1},{0,4,10}};
        System.out.println("Input: V=5, E=5, edges=[[0,1,1],[1,2,1],[2,3,1],[3,4,1],[0,4,10]]");
        System.out.println("Expected: [0,1,2,3,4], Output: " + Arrays.toString(shortestPath(5,5,edges7)));
    }
}
