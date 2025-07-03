/**
 * LeetCode 785. Is Graph Bipartite?
 *
 * Problem Statement:
 *     Given an undirected graph represented as an adjacency list, determine if
 *     the graph is bipartite. A graph is bipartite if the nodes can be colored
 *     using two colors such that no two adjacent nodes have the same color.
 *
 * Input:
 *     - int[][] graph: Adjacency list of the graph (0 <= node < n)
 *
 * Output:
 *     - boolean: true if the graph is bipartite, false otherwise
 *
 * Example:
 *     Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 *     Output: false
 *
 *     Explanation:
 *     The graph contains an odd-length cycle, so it is not bipartite.
 */

import java.util.*;

public class j01BipartiteGraph {
    /**
     * Approach 1: Depth-First Search (DFS) Coloring
     *
     * Intuition:
     * - The core idea is to try to color each node with one of two colors (1 or 2)
     *   such that no two adjacent nodes share the same color. If we can do this
     *   for all nodes, the graph is bipartite. DFS is used to traverse the graph
     *   recursively, propagating the coloring to all reachable nodes. If we ever
     *   find a neighbor with the same color as the current node, the graph cannot
     *   be bipartite. This approach works for both connected and disconnected graphs.
     *
     * Explanation:
     * - We iterate through all nodes to ensure we cover disconnected components.
     * - For each unvisited node, we start DFS coloring with color 1.
     * - The dfsColoring method colors the current node and recursively attempts to
     *   color all its neighbors with the alternate color.
     * - If a coloring conflict is found (neighbor already colored with the same color),
     *   we return false immediately.
     * - If all nodes can be colored without conflict, we return true.
     *
     * Time Complexity: O(V + E), where V = number of nodes, E = number of edges
     * Space Complexity: O(V) for the visited/color array and recursion stack
     *
     * @param graph    Adjacency list of the graph (int[][])
     * @return        true if the graph is bipartite, false otherwise
     */
    public static boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n]; // 0: unvisited, 1/2: color
        // Iterate through all nodes to handle disconnected graphs
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                // Start DFS coloring for each unvisited component
                if (!dfsColoring(graph, visited, i, 1))
                    return false; // If any component is not bipartite, return false
            }
        }
        return true; // All components are bipartite
    }

    /**
     * Helper Method: DFS Coloring
     *
     * Intuition:
     * - Recursively color the node and its neighbors with alternate colors.
     *   If a coloring conflict is found (i.e., a neighbor is already colored with
     *   the same color as the current node), the graph is not bipartite.
     *
     * Explanation:
     * - If the current node is already colored, we check if the color matches the
     *   intended color. If not, a conflict is found and we return false.
     * - Otherwise, we color the node and recursively attempt to color all its neighbors
     *   with the alternate color (1 <-> 2).
     * - If any recursive call returns false, we propagate the failure up the call stack.
     * - If all neighbors can be colored without conflict, we return true.
     *
     * Time Complexity: O(V + E) for a connected component
     * Space Complexity: O(V) for recursion stack
     *
     * @param graph    Adjacency list
     * @param visited  Color array
     * @param src      Current node
     * @param color    Color to assign
     * @return         true if coloring is possible, false otherwise
     */
    private static boolean dfsColoring(int[][] graph, int[] visited, int src, int color) {
        // If node is already colored, check for color conflict
        if (visited[src] != 0) {
            return visited[src] == color; // Conflict if colors don't match
        }
        visited[src] = color; // Color the node
        // Traverse all neighbors
        for (int neb : graph[src]) {
            int newColor = (color == 1) ? 2 : 1; // Alternate color for neighbor
            // Recursively color the neighbor; if conflict, return false
            if (!dfsColoring(graph, visited, neb, newColor))
                return false;
        }
        return true; // All neighbors colored successfully
    }

    /**
     * Helper Class: Pair
     *
     * Intuition:
     * - Used to store a node and its assigned color for BFS traversal.
     *   This allows us to keep track of which color to assign to each node
     *   as we process them in the queue.
     *
     */
    static class Pair {
        int node, color;
        Pair(int node, int color) {
            this.node = node;
            this.color = color;
        }
    }

    /**
     * Approach 2: Breadth-First Search (BFS) Coloring
     *
     * Intuition:
     * - Instead of using recursion, we use a queue to traverse the graph level by level.
     *   We color each node as we dequeue it, and enqueue its neighbors with the alternate color.
     *   If we ever find a neighbor already colored with the same color as the current node,
     *   the graph is not bipartite. This approach is iterative and avoids recursion stack overflow.
     *
     * Explanation:
     * - We iterate through all nodes to handle disconnected graphs.
     * - For each unvisited node, we start BFS coloring with color 1.
     * - The bfsColoring method uses a queue to process nodes. For each node, we color it
     *   and enqueue all its neighbors with the alternate color.
     * - If a neighbor is already colored and the color matches the current node, we return false.
     * - If all nodes can be colored without conflict, we return true.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for visited array and queue
     *
     * @param graph    Adjacency list
     * @return         true if the graph is bipartite, false otherwise
     */
    public static boolean isBipartiteBFS(int[][] graph) {
        int[] visited = new int[graph.length]; // 0: unvisited, 1/2: color
        // Iterate through all nodes to handle disconnected graphs
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0) {
                // Start BFS coloring for each unvisited component
                if (!bfsColoring(graph, visited, i)) return false;
            }
        }
        return true; // All components are bipartite
    }

    /**
     * Helper Method: BFS Coloring
     *
     * Intuition:
     * - Use a queue to color nodes and check for conflicts. Each time we dequeue a node,
     *   we color it and enqueue its unvisited neighbors with the alternate color. If a neighbor
     *   is already colored with the same color, the graph is not bipartite.
     *
     * Explanation:
     * - We initialize a queue and add the source node with color 1.
     * - While the queue is not empty, we dequeue a node and check if it is already colored.
     *   If it is, we check for a color conflict. If not, we color it and enqueue all its neighbors
     *   with the alternate color.
     * - If a neighbor is already colored and the color matches the current node, we return false.
     * - If all nodes can be colored without conflict, we return true.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     *
     * @param graph    Adjacency list
     * @param visited  Color array
     * @param src      Source node
     * @return         true if coloring is possible, false otherwise
     */
    private static boolean bfsColoring(int[][] graph, int[] visited, int src) {
        Queue<Pair> queue = new LinkedList<>(); // Queue for BFS
        queue.add(new Pair(src, 1)); // Start with source node and color 1
        while (!queue.isEmpty()) {
            Pair curr = queue.poll(); // Dequeue current node
            // If node is already colored, check for color conflict
            if (visited[curr.node] != 0) {
                if (visited[curr.node] != curr.color) return false; // Conflict found
                continue; // Already colored correctly, skip
            }
            visited[curr.node] = curr.color; // Color the node
            // Traverse all neighbors
            for (int neb : graph[curr.node]) {
                int newColor = (curr.color == 1) ? 2 : 1; // Alternate color for neighbor
                queue.add(new Pair(neb, newColor)); // Enqueue neighbor with alternate color
            }
        }
        return true; // All nodes colored successfully
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] g1 = {{1,3},{0,2},{1,3},{0,2}};
        // This is a bipartite graph (even cycle)
        System.out.println("Input: [[1,3],[0,2],[1,3],[0,2]], Expected: true, Output: " + isBipartiteDFS(g1));
        System.out.println("Input: [[1,3],[0,2],[1,3],[0,2]], Expected: true, Output: " + isBipartiteBFS(g1));

        int[][] g2 = {{1,2,3},{0,2},{0,1,3},{0,2}};
        // This graph contains an odd cycle, so not bipartite
        System.out.println("Input: [[1,2,3],[0,2],[0,1,3],[0,2]], Expected: false, Output: " + isBipartiteDFS(g2));
        System.out.println("Input: [[1,2,3],[0,2],[0,1,3],[0,2]], Expected: false, Output: " + isBipartiteBFS(g2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] g3 = {};
        // Empty graph (no nodes) is trivially bipartite
        System.out.println("Input: [], Expected: true, Output: " + isBipartiteDFS(g3));
        System.out.println("Input: [], Expected: true, Output: " + isBipartiteBFS(g3));

        int[][] g4 = {{}};
        // Single node with no edges is bipartite
        System.out.println("Input: [[]], Expected: true, Output: " + isBipartiteDFS(g4));
        System.out.println("Input: [[]], Expected: true, Output: " + isBipartiteBFS(g4));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] g5 = new int[1000][0];
        // Large graph with 1000 isolated nodes (no edges)
        System.out.println("Input: 1000 isolated nodes, Expected: true, Output: " + isBipartiteDFS(g5));
        System.out.println("Input: 1000 isolated nodes, Expected: true, Output: " + isBipartiteBFS(g5));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] g6 = {{1},{0}};
        // Two nodes with one edge (simple bipartite)
        System.out.println("Input: [[1],[0]], Expected: true, Output: " + isBipartiteDFS(g6));
        System.out.println("Input: [[1],[0]], Expected: true, Output: " + isBipartiteBFS(g6));

        int[][] g7 = {{1},{0,2},{1,3},{2}};
        // Linear chain of 4 nodes (bipartite)
        System.out.println("Input: [[1],[0,2],[1,3],[2]], Expected: true, Output: " + isBipartiteDFS(g7));
        System.out.println("Input: [[1],[0,2],[1,3],[2]], Expected: true, Output: " + isBipartiteBFS(g7));
    }
}
