/**
 * LeetCode 684. Redundant Connection (Cycle Detection with DSU)
 *
 * Problem Statement:
 *     In a tree with n nodes labeled 1 to n, one additional edge is added,
 *     forming a cycle. Given a list of edges, return the edge that can be removed
 *     so that the resulting graph is a tree of n nodes. If multiple answers, return
 *     the last one in the input.
 *
 * Input:
 *     - edges: int[n][2], each edge is [u, v] (1 <= u, v <= n)
 *
 * Output:
 *     - int[2]: The redundant edge that can be removed
 *
 * Example:
 *     Input: edges = [[1,2],[1,3],[2,3]]
 *     Output: [2,3]
 *
 *     Explanation:
 *         Removing [2,3] leaves a tree.
 */

import java.util.*;

public class j05RedundantConnectionI {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which nodes are in the same component.
     * - If two endpoints of an edge are already in the same set, adding the edge
     *   would form a cycle (i.e., the redundant connection).
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
     * Approach: DSU (Union-Find) for Cycle Detection
     *
     * Intuition:
     * - For each edge, if endpoints are already in the same component, adding the
     *   edge would form a cycle (the redundant connection).
     * - Otherwise, merge their components.
     *
     * Explanation:
     * - For each edge [u, v], check if find(u) == find(v): if so, this is the
     *   redundant edge. Otherwise, union(u, v) to merge their components.
     * - Return the last redundant edge found.
     *
     * Time Complexity: O(n α(n)), n = #edges
     * Space Complexity: O(n)
     *
     * @param edges int[n][2]: List of edges
     * @return      int[2]: The redundant edge
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;
        int[] out = new int[]{-1,-1};
        DisjointSetUnion dsu = new DisjointSetUnion(n + 1); // DSU for 1-based nodes
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            // If endpoints are already connected, this edge is redundant
            if (dsu.find(from) == dsu.find(to)) {
                out = edges[i];
            } else {
                dsu.union(from, to); // Merge the two components
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {{1,2},{1,3},{2,3}};
        System.out.println("Input: [[1,2],[1,3],[2,3]]");
        System.out.println("Expected: [2,3], Output: " + Arrays.toString(new j05RedundantConnectionI().findRedundantConnection(edges1)));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {{1,2}};
        System.out.println("Input: [[1,2]]");
        System.out.println("Expected: [-1,-1], Output: " + Arrays.toString(new j05RedundantConnectionI().findRedundantConnection(edges2)));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n3 = 1000;
        int[][] edges3 = new int[n3][2];
        for (int i = 0; i < n3; i++) edges3[i] = new int[]{i+1, (i+2) <= n3 ? i+2 : 1};
        edges3[n3-1] = new int[]{1,2}; // Add a redundant edge
        System.out.println("Input: 1000 nodes, last edge is redundant");
        System.out.println("Expected: [1,2], Output: " + Arrays.toString(new j05RedundantConnectionI().findRedundantConnection(edges3)));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = {{1,2},{2,3},{3,4},{4,1},{1,5}};
        System.out.println("Input: [[1,2],[2,3],[3,4],[4,1],[1,5]]");
        System.out.println("Expected: [4,1], Output: " + Arrays.toString(new j05RedundantConnectionI().findRedundantConnection(edges4)));
    }
}
