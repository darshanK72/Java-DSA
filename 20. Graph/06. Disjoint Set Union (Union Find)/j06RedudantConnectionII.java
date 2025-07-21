/**
 * LeetCode 685. Redundant Connection II (Directed Graph, DSU + Parent Tracking)
 *
 * Problem Statement:
 *     In a rooted tree with n nodes labeled 1 to n, one additional directed edge
 *     is added, possibly causing a node to have two parents or a cycle. Given a
 *     list of edges, return the edge that can be removed to restore the tree.
 *     If multiple answers, return the last one in the input.
 *
 * Input:
 *     - edges: int[n][2], each edge is [u, v] (1 <= u, v <= n)
 *
 * Output:
 *     - int[2]: The redundant edge that can be removed
 *
 * Example:
 *     Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 *     Output: [4,1]
 *
 *     Explanation:
 *         Removing [4,1] restores the tree property.
 */

import java.util.*;

public class j06RedudantConnectionII {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which nodes are in the same component.
     * - Used to detect cycles in the directed graph after handling double-parent.
     * - Path compression and union by size keep operations nearly constant time.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - ranks[i]: Size of the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the smaller component under the larger.
     *
     * Time Complexity: O(α(n)) per operation
     * Space Complexity: O(n)
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
     * Approach: DSU + Parent Tracking for Directed Graphs
     *
     * Intuition:
     * - If a node has two parents, one of those edges must be removed.
     * - If there is a cycle, the edge causing the cycle must be removed.
     * - If both, remove the earlier of the two edges to the double-parent node.
     *
     * Explanation:
     * - First, track parents for each node. If a node gets a second parent, mark
     *   both edges (a, b) and (c, b). Temporarily invalidate the second edge.
     * - Then, use DSU to check for cycles, skipping the invalidated edge.
     * - If a cycle is found, return the appropriate edge depending on whether a
     *   double-parent was found.
     * - Otherwise, return the double-parent edge.
     *
     * Time Complexity: O(n α(n)), n = #edges
     * Space Complexity: O(n)
     *
     * @param edges int[n][2]: List of directed edges
     * @return      int[2]: The redundant edge
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length + 1;
        int[] parents = new int[n+1];
        Arrays.fill(parents, -1); // Track parent for each node
        int[] a = null; // First edge to double-parent node
        int[] b = null; // Second edge to double-parent node

        // Step 1: Detect a node with two parents
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (parents[to] != -1) {
                a = new int[]{parents[to], to}; // First parent edge
                b = new int[]{from, to};        // Second parent edge
                edge[1] = -1;                   // Temporarily invalidate second edge
            } else {
                parents[to] = from;
            }
        }
        // Step 2: Use DSU to check for cycles, skipping invalidated edge
        DisjointSetUnion dsu = new DisjointSetUnion(n + 1);
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == -1) continue; // Skip invalidated edge
            int from = edges[i][0];
            int to = edges[i][1];
            // If endpoints are already connected, a cycle exists
            if (dsu.find(from) == dsu.find(to)) {
                if (a == null) return edges[i]; // No double-parent, return this edge
                else return a;                  // Double-parent, return first edge
            } else {
                dsu.union(from, to); // Merge the two components
            }
        }
        return b; // If no cycle, return second edge to double-parent node
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {{1,2},{2,3},{3,4},{4,1},{1,5}};
        System.out.println("Input: [[1,2],[2,3],[3,4],[4,1],[1,5]]");
        System.out.println("Expected: [4,1], Output: " + Arrays.toString(new j06RedudantConnectionII().findRedundantDirectedConnection(edges1)));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {{1,2}};
        System.out.println("Input: [[1,2]]");
        System.out.println("Expected: null, Output: " + Arrays.toString(new j06RedudantConnectionII().findRedundantDirectedConnection(edges2)));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n3 = 1000;
        int[][] edges3 = new int[n3][2];
        for (int i = 0; i < n3; i++) edges3[i] = new int[]{i+1, (i+2) <= n3 ? i+2 : 1};
        edges3[n3-1] = new int[]{1,2}; // Add a redundant edge
        System.out.println("Input: 1000 nodes, last edge is redundant");
        System.out.println("Expected: [1,2], Output: " + Arrays.toString(new j06RedudantConnectionII().findRedundantDirectedConnection(edges3)));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = {{2,1},{3,1},{4,2},{1,4}};
        System.out.println("Input: [[2,1],[3,1],[4,2],[1,4]]");
        System.out.println("Expected: [3,1], Output: " + Arrays.toString(new j06RedudantConnectionII().findRedundantDirectedConnection(edges4)));
    }
}
