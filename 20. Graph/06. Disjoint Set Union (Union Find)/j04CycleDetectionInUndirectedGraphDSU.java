/**
 * GFG/LeetCode. Cycle Detection in Undirected Graph using DSU
 *
 * Problem Statement:
 *     Given an undirected graph with V vertices and a list of edges, determine if
 *     the graph contains a cycle. Use Disjoint Set Union (Union-Find) to detect
 *     cycles efficiently.
 *
 * Input:
 *     - V (1 <= V <= 10^5): Number of vertices
 *     - edges: int[m][2], each edge is [u, v] (0 <= u, v < V)
 *
 * Output:
 *     - boolean: true if the graph contains a cycle, false otherwise
 *
 * Example:
 *     Input: V = 3, edges = [[0,1],[1,2],[2,0]]
 *     Output: true
 *
 *     Explanation:
 *         The edges form a cycle: 0-1-2-0
 */

import java.util.*;

public class j04CycleDetectionInUndirectedGraphDSU {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which vertices are in the same component.
     * - If two endpoints of an edge are already in the same set, adding the edge
     *   would form a cycle.
     * - Path compression and union by size keep operations nearly constant time.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - ranks[i]: Size of the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the smaller component under the larger.
     *
     * Time Complexity: O(α(V)) per operation
     * Space Complexity: O(V)
     *
     * @param n    Number of nodes
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1); // Each node is initially its own root
            Arrays.fill(ranks, 1);    // Each component starts with size 1
        }
        /**
         * Finds the root of the node with path compression.
         * If node is not a root, recursively finds and sets its parent to the root.
         * This flattens the tree for future queries.
         */
        public int find(int node) {
            // If node is a root (parent is -1), return node itself
            if (parents[node] == -1)
                return node;
            // Path compression: recursively find root and set parent directly to root
            // This flattens the tree, making future finds faster
            parents[node] = find(parents[node]);
            return parents[node]; // Return the root of the component
        }
        /**
         * Unites the sets containing node1 and node2 using union by size.
         * The smaller tree is merged under the larger to keep the tree shallow.
         */
        public void union(int node1, int node2) {
            // Find the root of node1 (with path compression)
            int node1Parent = find(node1);
            // Find the root of node2 (with path compression)
            int node2Parent = find(node2);
            // If both nodes have the same root, they are already in the same set
            if (node1Parent == node2Parent)
                return;
            // Union by size: attach the smaller tree to the root of the larger tree
            if (ranks[node1Parent] < ranks[node2Parent]) {
                // Make node2Parent the parent of node1Parent
                parents[node1Parent] = node2Parent;
                // Update the size of node2Parent's component
                ranks[node2Parent] += ranks[node1Parent];
            } else {
                // Make node1Parent the parent of node2Parent
                parents[node2Parent] = node1Parent;
                // Update the size of node1Parent's component
                ranks[node1Parent] += ranks[node2Parent];
            }
        }
    }

    /**
     * Approach: DSU (Union-Find)
     *
     * Intuition:
     * - For each edge, if endpoints are already in the same component, adding the
     *   edge would form a cycle.
     * - Otherwise, merge their components.
     *
     * Explanation:
     * - For each edge [u, v], check if find(u) == find(v): if so, cycle exists.
     * - Otherwise, union(u, v) to merge their components.
     * - Only process each undirected edge once (e.g., u < v) to avoid duplicates.
     *
     * Time Complexity: O(m α(V)), m = #edges
     * Space Complexity: O(V)
     *
     * @param V     Number of vertices
     * @param edges int[m][2]: List of undirected edges
     * @return      boolean: true if cycle exists, false otherwise
     */
    public static boolean isCycle(int V, int[][] edges) {
        DisjointSetUnion dsu = new DisjointSetUnion(V); // Initialize DSU for V nodes
        // Process each edge
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            // Only process each undirected edge once (e.g., from < to)
            if (from < to) {
                // If endpoints are already connected, a cycle exists
                if (dsu.find(from) == dsu.find(to)) {
                    return true;
                } else {
                    dsu.union(from, to); // Merge the two components
                }
            }
        }
        return false; // No cycles found
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {{0,1},{1,2},{2,0}};
        System.out.println("Input: V=3, edges=[[0,1],[1,2],[2,0]]");
        System.out.println("Expected: true, Output: " + isCycle(3, edges1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {};
        System.out.println("Input: V=1, edges=[]");
        System.out.println("Expected: false, Output: " + isCycle(1, edges2));
        int[][] edges3 = {{0,1}};
        System.out.println("Input: V=2, edges=[[0,1]]");
        System.out.println("Expected: false, Output: " + isCycle(2, edges3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int V4 = 100000;
        int[][] edges4 = new int[0][2];
        System.out.println("Input: V=100000, edges=[]");
        System.out.println("Expected: false, Output: " + isCycle(V4, edges4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges5 = {{0,1},{1,2},{2,3},{3,4},{4,0}};
        System.out.println("Input: V=5, edges=[[0,1],[1,2],[2,3],[3,4],[4,0]]");
        System.out.println("Expected: true, Output: " + isCycle(5, edges5));
    }
}
