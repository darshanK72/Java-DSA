/**
 * Two Cliques Problem
 *
 * Problem Statement:
 *     Given an undirected graph with n nodes and a list of edges, determine if
 *     the graph can be divided into two cliques. A clique is a subset of nodes
 *     such that every two distinct nodes are adjacent. The problem reduces to
 *     checking if the complement graph is bipartite.
 *
 * Input:
 *     - int n: Number of nodes (0-indexed)
 *     - ArrayList<ArrayList<Integer>> edgeList: List of edges
 *
 * Output:
 *     - boolean: true if the graph can be divided into two cliques, false otherwise
 *
 * Example:
 *     Input: n = 5, edgeList = [[0,1],[1,2],[2,3],[3,4],[4,0],[1,3]]
 *     Output: true
 *
 *     Explanation:
 *     The complement graph is bipartite, so the original graph can be split into two cliques.
 */

import java.util.*;

public class j03TwoCliques {
    /**
     * Approach: Complement Graph Bipartite Check (DFS Coloring)
     *
     * Intuition:
     * - To check if a graph can be divided into two cliques, check if its complement
     *   graph is bipartite. In the complement graph, an edge exists between two nodes
     *   if and only if it does not exist in the original graph.
     * - If the complement graph is bipartite, then the original graph can be split
     *   into two cliques.
     *
     * Explanation:
     * - Build the adjacency matrix for the original graph.
     * - Construct the complement graph's adjacency list by adding an edge between
     *   every pair of nodes that are not connected in the original graph.
     * - Use DFS coloring to check if the complement graph is bipartite.
     * - If any component fails the bipartite check, return false.
     *
     * Time Complexity: O(N^2) for complement construction and O(N+E) for DFS
     * Space Complexity: O(N^2) for adjacency matrix and list
     *
     * @param n         Number of nodes
     * @param edgeList  List of edges
     * @return          true if the graph can be divided into two cliques, false otherwise
     */
    public static boolean twoCliques(int n, ArrayList<ArrayList<Integer>> edgeList) {
        // Build adjacency matrix for the original graph
        boolean[][] adjM = new boolean[n][n];
        for (ArrayList<Integer> edge : edgeList) {
            adjM[edge.get(0)][edge.get(1)] = true;
            adjM[edge.get(1)][edge.get(0)] = true;
        }

        // Build complement graph's adjacency list
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && !adjM[i][j]) {
                    adj[i].add(j); // Add edge in complement graph
                }
            }
        }

        int[] visited = new int[n]; // 0: unvisited, 1/2: color
        // Try to color each component
        for (int i = 0; i < n; i++) {
            if (visited[i] != 0) continue; // Already colored
            if (!dfsColoring(adj, visited, i, 1)) return false; // Conflict found
        }
        return true; // All components are bipartite
    }

    /**
     * Helper Method: DFS Coloring
     *
     * Intuition:
     * - Recursively color the node and its neighbors with alternate colors.
     *   If a coloring conflict is found (neighbor already colored with the same color),
     *   the graph is not bipartite.
     *
     * Explanation:
     * - If the current node is already colored, check if the color matches the intended color.
     *   If not, a conflict is found and we return false.
     * - Otherwise, color the node and recursively attempt to color all its neighbors
     *   with the alternate color (1 <-> 2).
     * - If any recursive call returns false, propagate the failure up the call stack.
     * - If all neighbors can be colored without conflict, return true.
     *
     * Time Complexity: O(N + E) for a connected component
     * Space Complexity: O(N) for recursion stack
     *
     * @param adj      Adjacency list
     * @param visited  Color array
     * @param src      Current node
     * @param color    Color to assign
     * @return         true if coloring is possible, false otherwise
     */
    private static boolean dfsColoring(ArrayList<Integer>[] adj, int[] visited, int src, int color) {
        // If node is already colored, check for color conflict
        if (visited[src] != 0) {
            return visited[src] == color; // Conflict if colors don't match
        }
        visited[src] = color; // Color the node
        // Traverse all neighbors
        for (int neb : adj[src]) {
            int newColor = (color == 1) ? 2 : 1; // Alternate color for neighbor
            // Recursively color the neighbor; if conflict, return false
            if (!dfsColoring(adj, visited, neb, newColor))
                return false;
        }
        return true; // All neighbors colored successfully
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        ArrayList<ArrayList<Integer>> e1 = new ArrayList<>();
        e1.add(new ArrayList<>(Arrays.asList(0,1)));
        e1.add(new ArrayList<>(Arrays.asList(1,2)));
        e1.add(new ArrayList<>(Arrays.asList(2,3)));
        e1.add(new ArrayList<>(Arrays.asList(3,4)));
        e1.add(new ArrayList<>(Arrays.asList(4,0)));
        e1.add(new ArrayList<>(Arrays.asList(1,3)));
        System.out.println("n=5, edges=[[0,1],[1,2],[2,3],[3,4],[4,0],[1,3]], Expected: true, Output: " + twoCliques(5, e1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        ArrayList<ArrayList<Integer>> e2 = new ArrayList<>();
        System.out.println("n=1, edges=[], Expected: true, Output: " + twoCliques(1, e2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        ArrayList<ArrayList<Integer>> e3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = i+1; j < 10; j++) {
                e3.add(new ArrayList<>(Arrays.asList(i, j)));
            }
        }
        System.out.println("n=10, complete graph, Expected: true, Output: " + twoCliques(10, e3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        ArrayList<ArrayList<Integer>> e4 = new ArrayList<>();
        e4.add(new ArrayList<>(Arrays.asList(0,1)));
        e4.add(new ArrayList<>(Arrays.asList(1,2)));
        e4.add(new ArrayList<>(Arrays.asList(2,0)));
        System.out.println("n=3, edges=[[0,1],[1,2],[2,0]], Expected: false, Output: " + twoCliques(3, e4));
    }
}