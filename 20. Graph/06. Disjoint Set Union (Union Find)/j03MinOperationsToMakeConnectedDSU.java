/**
 * LeetCode 1319. Number of Operations to Make Network Connected
 *
 * Problem Statement:
 *     There are n computers numbered from 0 to n-1. You are given a 2D array
 *     connections where connections[i] = [ai, bi] represents a direct connection
 *     between computers ai and bi. Any computer can reach any other computer
 *     directly or indirectly through the network. You can remove extra cables and
 *     place them between any two computers to connect disconnected components.
 *     Return the minimum number of operations needed to connect all computers, or
 *     -1 if impossible.
 *
 * Input:
 *     - n (1 <= n <= 10^5): Number of computers
 *     - connections: int[m][2], 0 <= ai, bi < n
 *
 * Output:
 *     - int: Minimum number of reconnections needed, or -1 if impossible
 *
 * Example:
 *     Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 *     Output: 1
 *
 *     Explanation:
 *         There are 2 components: {0,1,2} and {3}. One extra cable can connect them.
 */

import java.util.*;

public class j03MinOperationsToMakeConnectedDSU {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which computers are in the same network/component.
     * - Path compression and union by size keep operations nearly constant time.
     * - Visual: If 0-1-2 are connected, after unions, all point to a single root.
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
     * Approach: DSU (Union-Find)
     *
     * Intuition:
     * - Each computer is a node; connections are edges. We want to connect all
     *   nodes into a single component (network).
     * - If there are enough extra cables (edges not needed for connectivity), we
     *   can use them to connect components. Otherwise, it's impossible.
     *
     * Explanation:
     * - For each connection, if endpoints are already connected, it's an extra edge.
     * - Otherwise, union their components.
     * - After processing, count the number of components (unique roots).
     * - To connect k components, we need at least k-1 cables. If extraEdges >= k-1,
     *   return k-1; else, return -1.
     *
     * Time Complexity: O(m α(n)), m = #connections
     * Space Complexity: O(n)
     *
     * @param n           Number of computers
     * @param connections int[m][2]: List of direct connections
     * @return            int: Minimum number of reconnections needed, or -1
     */
    public static int makeConnected(int n, int[][] connections) {
        DisjointSetUnion dsu = new DisjointSetUnion(n); // Initialize DSU for n computers
        int extraEdges = 0; // Count of redundant/extra cables
        // Process each connection
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            // If already connected, this edge is extra
            if (dsu.find(from) == dsu.find(to)) {
                extraEdges++;
            } else {
                dsu.union(from, to); // Merge the two components
            }
        }
        int components = 0;
        // Count unique roots (components)
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) {
                components++;
            }
        }
        int ans = components - 1; // Need (components-1) cables to connect all
        if (extraEdges < ans) return -1; // Not enough extra cables
        return ans;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int n1 = 4;
        int[][] con1 = {{0,1},{0,2},{1,2}};
        System.out.println("Input: n=4, connections=[[0,1],[0,2],[1,2]]");
        System.out.println("Expected: 1, Output: " + makeConnected(n1, con1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int n2 = 1;
        int[][] con2 = {};
        System.out.println("Input: n=1, connections=[]");
        System.out.println("Expected: 0, Output: " + makeConnected(n2, con2));
        int n3 = 2;
        int[][] con3 = {};
        System.out.println("Input: n=2, connections=[]");
        System.out.println("Expected: -1, Output: " + makeConnected(n3, con3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n4 = 100000;
        int[][] con4 = new int[0][2];
        System.out.println("Input: n=100000, connections=[]");
        System.out.println("Expected: -1, Output: " + makeConnected(n4, con4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int n5 = 6;
        int[][] con5 = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println("Input: n=6, connections=[[0,1],[0,2],[0,3],[1,2],[1,3]]");
        System.out.println("Expected: 2, Output: " + makeConnected(n5, con5));
    }
}
