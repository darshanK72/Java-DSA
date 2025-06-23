/**
 * LeetCode/GeeksForGeeks. Cycle Detection in Directed Graph
 *
 * Problem Statement:
 *     Given a directed graph with V vertices and a list of edges, determine
 *     if the graph contains a cycle. Each edge is represented as a pair [u, v].
 *
 * Input:
 *     - V (1 <= V <= 10^5): Number of vertices in the graph
 *     - edges (0 <= edges.length <= 2*10^5): List of directed edges
 *       Each edge is a pair [u, v] (0 <= u, v < V)
 *
 * Output:
 *     - true if the graph contains a cycle, false otherwise
 *
 * Example:
 *     Input: V = 4, edges = [[0,1],[1,2],[2,3],[3,1]]
 *     Output: true
 *     Explanation:
 *         0 → 1 → 2 → 3
 *              ↑     ↓
 *              ←-----
 *         There is a cycle: 1-2-3-1
 *
 *     Input: V = 4, edges = [[0,1],[1,2],[2,3]]
 *     Output: false
 *     Explanation:
 *         0 → 1 → 2 → 3 (no cycle)
 */

import java.util.*;

public class j02CycleInDirectedGraph {
    /**
     * Approach 1: DFS with Coloring (3-state visited array)
     *
     * Intuition:
     * - Use a 3-state visited array: 0 = unvisited, 1 = visiting, 2 = visited.
     * - If we visit a node marked as 'visiting' (1), a cycle exists.
     *
     * Explanation:
     * - Build adjacency list from edge list.
     * - For each unvisited node, start DFS.
     * - Mark node as 'visiting' on entry, 'visited' on exit.
     * - If DFS finds a 'visiting' node, return true.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     *
     * @param V      Number of vertices (1 <= V <= 10^5)
     * @param edges  Edge list (0 <= edges.length <= 2*10^5)
     * @return       true if cycle exists, false otherwise
     */
    public static boolean isCyclicDFSColoring(int V, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Directed edge
        }

        int[] visited = new int[V]; // 0 = unvisited, 1 = visiting, 2 = visited

        for (int i = 0; i < V; i++) {
            if (visited[i] != 0)
                continue;
            else if (dfsColor(adj, visited, i))
                return true;
        }

        return false;
    }

    /**
     * Helper Method: DFS with Coloring
     *
     * Intuition:
     * - Recursively visit neighbors. If a node is found in 'visiting' state,
     *   a cycle exists.
     *
     * Explanation:
     * - Mark node as 'visiting' (1) on entry.
     * - For each neighbor, if neighbor is 'visiting', cycle exists.
     * - On exit, mark node as 'visited' (2).
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) recursion stack
     *
     * @param adj     Adjacency list
     * @param visited 3-state visited array
     * @param src     Current node
     * @return        true if cycle found, false otherwise
     */
    public static boolean dfsColor(ArrayList<Integer>[] adj, int[] visited, int src) {
        if (visited[src] == 1)
            return true; // Found a back edge (cycle)
        else if (visited[src] == 2)
            return false; // Already fully processed
        visited[src] = 1; // Mark as visiting
        for (int neb : adj[src]) {
            if (dfsColor(adj, visited, neb))
                return true;
        }
        visited[src] = 2; // Mark as visited
        return false;
    }

    /**
     * Approach 2: DFS with Recursion Stack
     *
     * Intuition:
     * - Use a recursion stack to track nodes in the current path.
     * - If a node is revisited while in the recursion stack, a cycle exists.
     *
     * Explanation:
     * - Build adjacency list from edge list.
     * - For each unvisited node, start DFS.
     * - Mark node as visited and add to recursion stack.
     * - If DFS finds a node in the recursion stack, return true.
     * - Remove node from recursion stack on exit.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     *
     * @param V      Number of vertices
     * @param edges  Edge list
     * @return       true if cycle exists, false otherwise
     */
    public static boolean isCyclicRecStack(int V, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Directed edge
        }

        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (visited[i])
                continue;
            else if (dfsRecStack(adj, visited, recStack, i))
                return true;
        }

        return false;
    }

    /**
     * Helper Method: DFS with Recursion Stack
     *
     * Intuition:
     * - Recursively visit neighbors. If a node is found in the recursion stack,
     *   a cycle exists.
     *
     * Explanation:
     * - Mark node as visited and add to recursion stack.
     * - For each neighbor, if neighbor is in recursion stack, cycle exists.
     * - On exit, remove node from recursion stack.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) recursion stack
     *
     * @param adj      Adjacency list
     * @param visited  Visited array
     * @param recStack Recursion stack array
     * @param src      Current node
     * @return         true if cycle found, false otherwise
     */
    public static boolean dfsRecStack(ArrayList<Integer>[] adj, boolean[] visited, boolean[] recStack, int src) {
        if (recStack[src])
            return true; // Found a back edge (cycle)
        else if (visited[src])
            return false; // Already fully processed
        recStack[src] = true; // Add to recursion stack
        visited[src] = true; // Mark as visited
        for (int neb : adj[src]) {
            if (dfsRecStack(adj, visited, recStack, neb))
                return true;
        }
        recStack[src] = false; // Remove from recursion stack
        return false;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {{0,1},{1,2},{2,3},{3,1}};
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3],[3,1]], Expected: true, Output: " + isCyclicDFSColoring(4, edges1));
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3],[3,1]], Expected: true, Output: " + isCyclicRecStack(4, edges1));

        int[][] edges2 = {{0,1},{1,2},{2,3}};
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3]], Expected: false, Output: " + isCyclicDFSColoring(4, edges2));
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3]], Expected: false, Output: " + isCyclicRecStack(4, edges2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges3 = {};
        System.out.println("Input: V=1, edges=[], Expected: false, Output: " + isCyclicDFSColoring(1, edges3));
        System.out.println("Input: V=1, edges=[], Expected: false, Output: " + isCyclicRecStack(1, edges3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] edges4 = {{0,0}};
        System.out.println("Input: V=1, edges=[[0,0]], Expected: true, Output: " + isCyclicDFSColoring(1, edges4));
        System.out.println("Input: V=1, edges=[[0,0]], Expected: true, Output: " + isCyclicRecStack(1, edges4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges5 = {{0,1},{1,2},{2,0}};
        System.out.println("Input: V=3, edges=[[0,1],[1,2],[2,0]], Expected: true, Output: " + isCyclicDFSColoring(3, edges5));
        System.out.println("Input: V=3, edges=[[0,1],[1,2],[2,0]], Expected: true, Output: " + isCyclicRecStack(3, edges5));
    }
}
