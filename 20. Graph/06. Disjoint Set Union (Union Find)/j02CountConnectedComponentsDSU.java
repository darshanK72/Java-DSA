/**
 * LeetCode 547. Number of Provinces (Connected Components in Undirected Graph)
 *
 * Problem Statement:
 *     There are n cities. Some of them are connected, while some are not. A
 *     province is a group of directly or indirectly connected cities. You are
 *     given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith
 *     city and the jth city are directly connected, and 0 otherwise. Return the
 *     total number of provinces.
 *
 * Input:
 *     - isConnected: int[n][n], 1 <= n <= 200, isConnected[i][j] in {0,1}
 *
 * Output:
 *     - int: Number of provinces (connected components)
 *
 * Example:
 *     Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 *     Output: 2
 *
 *     Explanation:
 *         Cities 0 and 1 are connected, city 2 is separate.
 */

import java.util.*;

public class j02CountConnectedComponentsDSU {

    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - The DSU efficiently keeps track of which nodes belong to the same component.
     * - Each node starts in its own set; as we process connections, we merge sets.
     * - Path compression ensures that repeated find operations are fast by flattening
     *   the tree structure, so every node points directly to the root after a find.
     * - Union by size (or rank) ensures that the smaller tree is always merged under
     *   the larger, keeping the tree shallow and operations efficient.
     * - Visual: If 0-1-2 are connected, after unions, all point to a single root.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent in the tree.
     * - sizes[i]: Number of nodes in the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path for speed.
     * - union(x, y): Finds roots of x and y, merges the smaller tree under the larger.
     * - This structure allows us to merge and query components in nearly constant time.
     * - Example: For isConnected = [[1,1,0],[1,1,0],[0,0,1]], after unions, 0 and 1
     *   share a root, 2 is its own root, so there are 2 provinces.
     *
     * Time Complexity: O(α(n)) per operation (inverse Ackermann, nearly constant)
     * Space Complexity: O(n)
     *
     * @param n    Number of nodes
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] sizes;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.sizes = new int[n];
            Arrays.fill(parents, -1); // Each node is initially its own root
            Arrays.fill(sizes, 1);    // Each component starts with size 1
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
            int root1 = find(node1);
            // Find the root of node2 (with path compression)
            int root2 = find(node2);
            // If both nodes have the same root, they are already in the same set
            if (root1 == root2)
                return;
            // Union by size: attach the smaller tree to the root of the larger tree
            if (sizes[root1] < sizes[root2]) {
                // Make root2 the parent of root1
                parents[root1] = root2;
                // Update the size of root2's component
                sizes[root2] += sizes[root1];
            } else {
                // Make root1 the parent of root2
                parents[root2] = root1;
                // Update the size of root1's component
                sizes[root1] += sizes[root2];
            }
        }
    }

    /**
     * Approach: DSU (Union-Find)
     *
     * Intuition:
     * - Treat each city as a node in a graph. If isConnected[i][j] == 1, there is
     *   an edge between i and j, so they belong to the same province.
     * - By merging all directly or indirectly connected cities, we can count the
     *   number of unique connected components (provinces).
     * - DSU is ideal for this because it efficiently merges and queries components.
     * - Visual: For a 3x3 matrix, after all unions, the number of unique roots is
     *   the number of provinces.
     *
     * Explanation:
     * - For each pair (i, j), if isConnected[i][j] == 1, call union(i, j) to merge
     *   their components. This ensures all directly/indirectly connected cities are
     *   grouped together.
     * - After processing, iterate through all nodes and count how many are roots
     *   (i.e., dsu.find(i) == i). Each root represents a unique province.
     * - Handles edge cases: single city, all cities isolated, all cities connected.
     *
     * Time Complexity: O(n^2 * α(n)), where n is the number of cities
     * Space Complexity: O(n)
     *
     * @param isConnected   int[n][n]: Adjacency matrix
     * @return             int: Number of provinces
     */
    public static int findNumberOfProvinces(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n); // Initialize DSU for n cities
        // Merge all directly connected cities
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    // If city i and j are connected, merge their components
                    dsu.union(i, j);
                }
            }
        }
        int components = 0;
        // Count unique roots (provinces)
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) components++; // Node is a root, so a province
        }
        return components;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] mat1 = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println("Input: [[1,1,0],[1,1,0],[0,0,1]]");
        System.out.println("Expected: 2, Output: " + findNumberOfProvinces(mat1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] mat2 = {{1}};
        System.out.println("Input: [[1]]");
        System.out.println("Expected: 1, Output: " + findNumberOfProvinces(mat2));
        int[][] mat3 = {{1,0},{0,1}};
        System.out.println("Input: [[1,0],[0,1]]");
        System.out.println("Expected: 2, Output: " + findNumberOfProvinces(mat3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n = 200;
        int[][] mat4 = new int[n][n];
        for (int i = 0; i < n; i++) mat4[i][i] = 1;
        System.out.println("Input: 200 isolated nodes");
        System.out.println("Expected: 200, Output: " + findNumberOfProvinces(mat4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] mat5 = {{1,1,1},{1,1,1},{1,1,1}};
        System.out.println("Input: [[1,1,1],[1,1,1],[1,1,1]]");
        System.out.println("Expected: 1, Output: " + findNumberOfProvinces(mat5));
    }
} 