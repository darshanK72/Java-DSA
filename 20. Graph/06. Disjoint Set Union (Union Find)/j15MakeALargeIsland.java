/**
 * LeetCode 827. Making A Large Island
 * 
 * Problem Statement:
 *     You are given an n x n binary matrix grid. A cell (r, c) is called land if 
 *     grid[r][c] = 1 and water if grid[r][c] = 0. You may change at most one cell
 *     from water to land. Return the size of the largest island after applying at
 *     most one transformation.
 * 
 * Input:
 *     - grid: n x n binary matrix (1 ≤ n ≤ 500)
 *     - grid[i][j] is either 0 or 1
 * 
 * Output:
 *     - int: Size of largest possible island after at most one transformation
 * 
 * Example:
 *     Input: grid = [[1,0],[0,1]]
 *     Output: 3
 * 
 *     Explanation:
 *     Change any one 0 to 1 and connect two islands to form an island of size 3.
 */

import java.util.*;

public class j15MakeALargeIsland {
    /**
     * Helper class for Disjoint Set Union data structure
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;

        /**
         * Initialize DSU with n nodes
         * 
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1);
            Arrays.fill(ranks, 1);
        }

        /**
         * Find parent of node with path compression
         * 
         * Intuition:
         * - Use path compression to optimize future lookups
         * 
         * Explanation:
         * - Step 1: If node is root, return node
         * - Step 2: Recursively find and update parent pointers
         * 
         * Time Complexity: O(α(n)) ≈ O(1)
         * Space Complexity: O(1)
         */
        public int find(int node) {
            if (parents[node] == -1)
                return node;
            parents[node] = find(parents[node]);
            return parents[node];
        }

        /**
         * Union two nodes by rank
         * 
         * Intuition:
         * - Use union by rank to keep tree balanced
         * 
         * Explanation:
         * - Step 1: Find parents of both nodes
         * - Step 2: Merge smaller tree into larger one
         * - Step 3: Update ranks accordingly
         * 
         * Time Complexity: O(α(n)) ≈ O(1)
         * Space Complexity: O(1)
         */
        public void union(int node1, int node2) {
            int node1Parent = find(node1);
            int node2Parent = find(node2);
            if (node1Parent == node2Parent)
                return;
            if (ranks[node1Parent] < ranks[node2Parent]) {
                parents[node1Parent] = node2Parent;
                ranks[node2Parent] += ranks[node1Parent];
            } else {
                parents[node2Parent] = node1Parent;
                ranks[node1Parent] += ranks[node2Parent];
            }
        }
    }

    /**
     * Approach: Union Find with Grid Transformation
     * 
     * Intuition:
     * - Use DSU to track connected land cells
     * - Try converting each water cell to land and check resulting island size
     * 
     * Explanation:
     * - Step 1: Create DSU and connect all adjacent land cells
     * - Step 2: For each water cell:
     *     - Check all adjacent land cells
     *     - Calculate total size if this cell becomes land
     * - Step 3: Track maximum possible island size
     * - Step 4: Handle case where no water cells exist
     * 
     * Time Complexity: O(n^2) where n is grid dimension
     * Space Complexity: O(n^2) for DSU arrays
     * 
     * @param grid    n x n binary matrix (1 ≤ n ≤ 500)
     * @return       Size of largest possible island
     */
    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int[] rowDir = new int[] { 0, 1, 0, -1 };
        int[] colDir = new int[] { 1, 0, -1, 0 };
        DisjointSetUnion dsu = new DisjointSetUnion(n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int from = i * n + j;
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int row = i + rowDir[d];
                        int col = j + colDir[d];
                        if (row >= 0 && row < n && col >= 0 && col < n && grid[row][col] == 1) {
                            int to = row * n + col;
                            dsu.union(from, to);
                        }
                    }
                }
            }
        }

        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    HashSet<Integer> set = new HashSet<>();
                    for (int d = 0; d < 4; d++) {
                        int row = i + rowDir[d];
                        int col = j + colDir[d];
                        if (row >= 0 && row < n && col >= 0 && col < n && grid[row][col] == 1) {
                            int to = row * n + col;
                            int parent = dsu.find(to);
                            set.add(parent);
                        }
                    }

                    int totalSize = 1;
                    for (int parent : set) {
                        totalSize += dsu.ranks[parent];
                    }

                    maxSize = Math.max(totalSize, maxSize);
                }
            }
        }

        for (int i = 0; i < n * n; i++) {
            if (grid[i / n][i % n] == 1) {
                maxSize = Math.max(maxSize, dsu.ranks[dsu.find(i)]);
            }
        }

        return maxSize;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] grid1 = {{1,0},{0,1}};
        System.out.println("Input: [[1,0],[0,1]]");
        System.out.println("Output: " + largestIsland(grid1));
        System.out.println("Expected: 3");

        // Test Case 2: All lands
        System.out.println("\nAll Lands Case:");
        int[][] grid2 = {{1,1},{1,1}};
        System.out.println("Input: [[1,1],[1,1]]");
        System.out.println("Output: " + largestIsland(grid2));
        System.out.println("Expected: 4");

        // Test Case 3: All waters
        System.out.println("\nAll Waters Case:");
        int[][] grid3 = {{0,0},{0,0}};
        System.out.println("Input: [[0,0],[0,0]]");
        System.out.println("Output: " + largestIsland(grid3));
        System.out.println("Expected: 1");

        // Test Case 4: Single cell
        System.out.println("\nSingle Cell Case:");
        int[][] grid4 = {{1}};
        System.out.println("Input: [[1]]");
        System.out.println("Output: " + largestIsland(grid4));
        System.out.println("Expected: 1");

        // Test Case 5: Complex pattern
        System.out.println("\nComplex Pattern Case:");
        int[][] grid5 = {
            {1,1,0,0},
            {1,1,0,0},
            {0,0,1,1},
            {0,0,1,1}
        };
        System.out.println("Input: [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]");
        System.out.println("Output: " + largestIsland(grid5));
        System.out.println("Expected: 9");
    }
}
