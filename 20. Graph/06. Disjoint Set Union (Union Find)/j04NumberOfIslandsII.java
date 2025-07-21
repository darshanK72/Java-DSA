/**
 * LeetCode 305. Number of Islands II (Dynamic Connectivity with DSU)
 *
 * Problem Statement:
 *     Given a n x m grid of water (0), you are given a list of positions to add
 *     land (1) one by one. After each addition, return the number of islands.
 *     An island is surrounded by water and is formed by connecting adjacent lands
 *     horizontally or vertically. Use Disjoint Set Union (Union-Find) to solve.
 *
 * Input:
 *     - n (1 <= n <= 10^4): Number of rows
 *     - m (1 <= m <= 10^4): Number of columns
 *     - q: int[k][2], each [x, y] is a position to add land
 *
 * Output:
 *     - int[]: Number of islands after each addition
 *
 * Example:
 *     Input: n = 3, m = 3, q = [[0,0],[0,1],[1,2],[2,1],[1,1]]
 *     Output: [1,1,2,3,1]
 *
 *     Explanation:
 *         After each addition, the number of islands is updated as lands merge.
 */

import java.util.*;

public class j04NumberOfIslandsII {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which land cells are in the same island/component.
     * - Path compression and union by size keep operations nearly constant time.
     * - Visual: As new land is added, merge with adjacent lands if present.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - ranks[i]: Size of the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the smaller component under the larger.
     *
     * Time Complexity: O(α(n*m)) per operation
     * Space Complexity: O(n*m)
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
     * Approach: DSU (Union-Find) for Dynamic Connectivity
     *
     * Intuition:
     * - Each land cell is a node; as land is added, connect to adjacent lands.
     * - If adjacent cell is already land, merge their components (islands).
     * - The number of islands increases by 1 for each new land, but decreases by 1
     *   for each merge with a new component.
     *
     * Explanation:
     * - For each query, if cell is already land, append current island count.
     * - Otherwise, mark as land, increment island count.
     * - For each of 4 directions, if neighbor is land and not already connected,
     *   union and decrement island count.
     * - Append island count after each addition.
     *
     * Time Complexity: O(k * α(n*m)), k = #queries
     * Space Complexity: O(n*m)
     *
     * @param n    Number of rows
     * @param m    Number of columns
     * @param q    int[k][2]: List of land additions
     * @return     int[]: Number of islands after each addition
     */
    public static int[] numOfIslandsII(int n, int m, int[][] q) {
        DisjointSetUnion dsu = new DisjointSetUnion(n * m); // DSU for all cells
        boolean[][] visited = new boolean[n][m]; // Track land cells
        int[] rowDir = new int[] { 0, 1, 0, -1 }; // Directions: right, down, left, up
        int[] colDir = new int[] { 1, 0, -1, 0 };
        int[] out = new int[q.length];
        int islands = 0;
        for (int i = 0; i < q.length; i++) {
            int x = q[i][0];
            int y = q[i][1];
            // If cell is already land, append current island count
            if (visited[x][y]) {
                out[i] = islands;
                continue;
            }
            visited[x][y] = true; // Mark cell as land
            int from = x * m + y; // Convert 2D to 1D index
            islands++; // New island
            // Check all 4 directions
            for (int d = 0; d < 4; d++) {
                int row = x + rowDir[d];
                int col = y + colDir[d];
                // If neighbor is in bounds and is land
                if (row >= 0 && row < n && col >= 0 && col < m) {
                    int to = row * m + col;
                    if (visited[row][col]) {
                        // If not already connected, merge islands
                        if (dsu.find(from) != dsu.find(to)) {
                            dsu.union(from, to);
                            islands--;
                        }
                    }
                }
            }
            out[i] = islands; // mark islands for query
        }
        return out; // return output
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int n1 = 3, m1 = 3;
        int[][] q1 = {{0,0},{0,1},{1,2},{2,1},{1,1}};
        System.out.println("Input: n=3, m=3, q=[[0,0],[0,1],[1,2],[2,1],[1,1]]");
        System.out.println("Expected: [1,1,2,3,1], Output: " + Arrays.toString(numOfIslandsII(n1, m1, q1)));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int n2 = 1, m2 = 1;
        int[][] q2 = {{0,0},{0,0}};
        System.out.println("Input: n=1, m=1, q=[[0,0],[0,0]]");
        System.out.println("Expected: [1,1], Output: " + Arrays.toString(numOfIslandsII(n2, m2, q2)));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n3 = 100, m3 = 100;
        int[][] q3 = {{0,0},{99,99}};
        System.out.println("Input: n=100, m=100, q=[[0,0],[99,99]]");
        System.out.println("Expected: [1,2], Output: " + Arrays.toString(numOfIslandsII(n3, m3, q3)));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int n4 = 2, m4 = 2;
        int[][] q4 = {{0,0},{0,1},{1,1},{1,0}};
        System.out.println("Input: n=2, m=2, q=[[0,0],[0,1],[1,1],[1,0]]");
        System.out.println("Expected: [1,1,1,1], Output: " + Arrays.toString(numOfIslandsII(n4, m4, q4)));
    }
}
