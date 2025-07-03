/**
 * LeetCode 886. Possible Bipartition
 *
 * Problem Statement:
 *     Given a set of n people (labeled 1 to n) and a list of pairs (dislikes),
 *     where dislikes[i] = [a, b] means person a and person b dislike each other,
 *     return true if it is possible to split everyone into two groups such that
 *     no pair of people in the same group dislike each other.
 *
 * Input:
 *     - int n: Number of people (1 <= n <= 2000)
 *     - int[][] dislikes: List of pairs (1 <= dislikes.length <= 10000)
 *
 * Output:
 *     - boolean: true if possible to bipartition, false otherwise
 *
 * Example:
 *     Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 *     Output: true
 *
 *     Explanation:
 *     Group 1: [1,4], Group 2: [2,3]. No pair in the same group dislikes each other.
 */

import java.util.*;

public class j02PossibleBipartition {
    /**
     * Approach: DFS Coloring (Bipartite Check)
     *
     * Intuition:
     * - The problem can be modeled as a graph where each person is a node and
     *   dislikes are edges. We need to check if the graph is bipartite, i.e.,
     *   can be colored with two colors such that no two adjacent nodes have the
     *   same color. If possible, the bipartition exists.
     *
     * Explanation:
     * - Build the adjacency list for the graph using the dislikes pairs.
     * - Use a color array (visited) to track the group/color of each person.
     * - For each unvisited person, start DFS coloring with color 1.
     * - If a coloring conflict is found (neighbor already colored with the same color),
     *   return false immediately.
     * - If all nodes can be colored without conflict, return true.
     *
     * Time Complexity: O(N + E), where N = number of people, E = number of dislikes
     * Space Complexity: O(N + E) for adjacency list and color array
     *
     * @param n         Number of people
     * @param dislikes  Dislike pairs
     * @return          true if possible to bipartition, false otherwise
     */
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        // Build adjacency list for the graph
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < dislikes.length; i++) {
            int from = dislikes[i][0];
            int to = dislikes[i][1];
            adj[from].add(to); // Add edge from 'from' to 'to'
            adj[to].add(from); // Add edge from 'to' to 'from' (undirected)
        }
        int[] visited = new int[n + 1]; // 0: unvisited, 1/2: color
        // Try to color each component
        for (int i = 1; i <= n; i++) {
            if (visited[i] != 0)
                continue; // Already colored
            if (!dfsColoring(adj, visited, i, 1))
                return false; // Conflict found, not bipartite
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
        int[][] d1 = {{1,2},{1,3},{2,4}};
        System.out.println("n=4, dislikes=[[1,2],[1,3],[2,4]], Expected: true, Output: " + possibleBipartition(4, d1));

        int[][] d2 = {{1,2},{1,3},{2,3}};
        System.out.println("n=3, dislikes=[[1,2],[1,3],[2,3]], Expected: false, Output: " + possibleBipartition(3, d2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] d3 = {};
        System.out.println("n=1, dislikes=[], Expected: true, Output: " + possibleBipartition(1, d3));
        System.out.println("n=2, dislikes=[], Expected: true, Output: " + possibleBipartition(2, d3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] d4 = new int[0][0];
        System.out.println("n=1000, dislikes=[], Expected: true, Output: " + possibleBipartition(1000, d4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] d5 = {{1,2},{2,3},{3,4},{4,5},{5,1}};
        System.out.println("n=5, dislikes=[[1,2],[2,3],[3,4],[4,5],[5,1]], Expected: false, Output: " + possibleBipartition(5, d5));
    }
}
