/**
 * M-Coloring Problem (Graph Coloring)
 *
 * Problem Statement:
 *     Given an undirected graph with v vertices and a list of edges, determine if
 *     the graph can be colored with at most m colors such that no two adjacent
 *     vertices share the same color.
 *
 * Input:
 *     - int v: Number of vertices (0-indexed)
 *     - int[][] edges: List of edges (each edge as [from, to])
 *     - int m: Number of colors
 *
 * Output:
 *     - boolean: true if the graph can be colored with m colors, false otherwise
 *
 * Example:
 *     Input: v = 4, edges = [[0,1],[1,2],[2,3],[3,0]], m = 3
 *     Output: true
 *
 *     Explanation:
 *     The graph is a cycle of 4 nodes, which can be colored with 2 or more colors.
 */

import java.util.*;

public class j04MColoring {
    /**
     * Approach: Backtracking (Recursive Coloring)
     *
     * Intuition:
     * - Try to assign each vertex one of m colors such that no two adjacent vertices
     *   have the same color. Use backtracking to explore all possible colorings.
     * - If a valid coloring is found, return true. If all possibilities are exhausted
     *   without success, return false.
     *
     * Explanation:
     * - Build the adjacency list for the graph from the edge list.
     * - Use a visited/color array to track the color assigned to each vertex.
     * - Recursively try to color each vertex with all possible colors (1 to m).
     * - For each color, check if it is valid for the current vertex (no neighbor has the same color).
     * - If valid, assign the color and recurse for the next vertex.
     * - If all vertices are colored successfully, return true. Otherwise, backtrack.
     *
     * Time Complexity: O(m^v), where m = number of colors, v = number of vertices
     * Space Complexity: O(v + e) for adjacency list and O(v) for recursion stack
     *
     * @param v      Number of vertices
     * @param edges  List of edges
     * @param m      Number of colors
     * @return       true if the graph can be colored with m colors, false otherwise
     */
    public static boolean graphColoring(int v, int[][] edges, int m) {
        // Build adjacency list for the graph
        ArrayList<Integer>[] adj = new ArrayList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Add edge from 'from' to 'to'
            adj[to].add(from); // Add edge from 'to' to 'from' (undirected)
        }
        int[] visited = new int[v]; // 0: uncolored, 1..m: color
        // Start coloring from vertex 0
        return color(adj, visited, 0, m);
    }

    /**
     * Helper Method: Recursive Coloring (Backtracking)
     *
     * Intuition:
     * - Try to assign each vertex a color from 1 to m. If a valid color is found,
     *   recurse for the next vertex. If not, backtrack.
     *
     * Explanation:
     * - If all vertices are colored (src == adj.length), return true.
     * - For each color, check if it is valid for the current vertex using canColor().
     * - If valid, assign the color and recurse for the next vertex.
     * - If recursion returns true, propagate success. Otherwise, backtrack.
     * - If no color is valid, return false.
     *
     * Time Complexity: O(m^v)
     * Space Complexity: O(v) for recursion stack
     *
     * @param adj     Adjacency list
     * @param visited Color array
     * @param src     Current vertex
     * @param m       Number of colors
     * @return        true if coloring is possible, false otherwise
     */
    private static boolean color(ArrayList<Integer>[] adj, int[] visited, int src, int m) {
        // If all vertices are colored, return true
        if (src == adj.length)
            return true;
        // Try all colors for the current vertex
        for (int i = 1; i <= m; i++) {
            if (canColor(adj, visited, src, i)) {
                visited[src] = i; // Assign color
                if (color(adj, visited, src + 1, m))
                    return true; // Success, propagate up
                visited[src] = 0; // Backtrack
            }
        }
        return false; // No valid color found
    }

    /**
     * Helper Method: Can Color
     *
     * Intuition:
     * - Check if the current color can be assigned to the vertex without
     *   conflicting with any of its neighbors.
     *
     * Explanation:
     * - For each neighbor, if it is already colored with the same color, return false.
     * - If no neighbor has the same color, return true.
     *
     * Time Complexity: O(degree of src)
     * Space Complexity: O(1)
     *
     * @param adj     Adjacency list
     * @param visited Color array
     * @param src     Current vertex
     * @param color   Color to check
     * @return        true if color can be assigned, false otherwise
     */
    private static boolean canColor(ArrayList<Integer>[] adj, int[] visited, int src, int color) {
        for (int neb : adj[src]) {
            if (visited[neb] == color)
                return false; // Neighbor has the same color
        }
        return true; // No conflict
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] e1 = {{0,1},{1,2},{2,3},{3,0}};
        System.out.println("v=4, edges=[[0,1],[1,2],[2,3],[3,0]], m=3, Expected: true, Output: " + graphColoring(4, e1, 3));
        System.out.println("v=4, edges=[[0,1],[1,2],[2,3],[3,0]], m=2, Expected: true, Output: " + graphColoring(4, e1, 2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] e2 = {};
        System.out.println("v=1, edges=[], m=1, Expected: true, Output: " + graphColoring(1, e2, 1));
        System.out.println("v=2, edges=[], m=1, Expected: true, Output: " + graphColoring(2, e2, 1));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] e3 = new int[0][0];
        System.out.println("v=10, edges=[], m=1, Expected: true, Output: " + graphColoring(10, e3, 1));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] e4 = {{0,1},{1,2},{2,0}};
        System.out.println("v=3, edges=[[0,1],[1,2],[2,0]], m=2, Expected: false, Output: " + graphColoring(3, e4, 2));
        System.out.println("v=3, edges=[[0,1],[1,2],[2,0]], m=3, Expected: true, Output: " + graphColoring(3, e4, 3));
    }
}
