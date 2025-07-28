/**
 * LeetCode 947. Most Stones Removed with Same Row or Column
 * 
 * Problem Statement:
 *     On a 2D plane, we place n stones at some integer coordinate points. Each coordinate 
 *     point may have at most one stone. A stone can be removed if it shares either the 
 *     same row or the same column as another stone that has not been removed.
 *     Return the largest possible number of stones that can be removed.
 * 
 * Input:
 *     - stones[][]: Array of [x,y] coordinates where stones are placed
 *     - 1 ≤ stones.length ≤ 1000
 *     - 0 ≤ xi, yi ≤ 10^4
 * 
 * Output:
 *     - int: Maximum number of stones that can be removed
 * 
 * Example:
 *     Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 *     Output: 5
 * 
 *     Explanation:
 *     One way to remove 5 stones:
 *     1. Remove stone at [2,2] because it shares row with [2,1]
 *     2. Remove stone at [2,1] because it shares column with [0,1]
 *     3. Remove stone at [1,2] because it shares row with [1,0]
 *     4. Remove stone at [1,0] because it shares column with [0,0]
 *     5. Remove stone at [0,1] because it shares row with [0,0]
 *     Stone at [0,0] cannot be removed since it's the last stone.
 */

import java.util.*;

public class j15MostStonesRemovedInRowCol {
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
         * - Each find operation updates parent pointers to root
         * 
         * Explanation:
         * - Step 1: If node is root (parent = -1), return node
         * - Step 2: Recursively find true parent and update parent pointer
         * - Step 3: Return compressed path parent
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
         * - Attach smaller tree to larger tree's root
         * 
         * Explanation:
         * - Step 1: Find parents of both nodes
         * - Step 2: If same parent, nodes already connected
         * - Step 3: Compare ranks and merge smaller into larger
         * - Step 4: Update ranks for merged trees
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
     * Approach: Union Find with Row-Column Mapping
     * 
     * Intuition:
     * - Stones in same row/column form a connected component
     * - Maximum removable stones = total stones - number of components
     * 
     * Explanation:
     * - Step 1: Find maximum row (m) and column (n) coordinates
     * - Step 2: Create DSU with size m+n+2 (to handle both row and col indices)
     * - Step 3: For each stone:
     *     - Map column to index after row indices (col + m + 1)
     *     - Union row and mapped column indices
     *     - Keep track of used indices in set
     * - Step 4: Count number of components (roots in DSU)
     * - Step 5: Return total stones minus components
     * 
     * Time Complexity: O(N * α(N)) where N is number of stones
     * Space Complexity: O(M + N) where M is max coordinate value
     * 
     * @param stones    Array of [x,y] coordinates (1 ≤ stones.length ≤ 1000)
     * @return         Maximum number of removable stones
     */
    public static int removeStones(int[][] stones) {
        // Find maximum row and column coordinates
        int m = 0;
        int n = 0;
        for (int[] cord : stones) {
            m = Math.max(m, cord[0]);  // Update max row coordinate
            n = Math.max(n, cord[1]);  // Update max column coordinate
        }

        // Initialize DSU with size to accommodate both row and column indices
        DisjointSetUnion dsu = new DisjointSetUnion(m + n + 2);
        
        // Set to keep track of used row and column indices
        HashSet<Integer> set = new HashSet<>();
        
        // Process each stone and union its row and column
        for (int[] cord : stones) {
            int row = cord[0];                 // Get row coordinate
            int col = cord[1] + m + 1;         // Map column to index after row indices
            dsu.union(row, col);               // Connect row and column in DSU
            set.add(row);                      // Mark row as used
            set.add(col);                      // Mark column as used
        }

        // Count number of distinct components
        int comp = 0;
        for (int pos : set) {
            // If position is its own parent, it's a root of a component
            if (dsu.find(pos) == pos) {
                comp++;
            }
        }
        
        // Total removable stones = total stones - number of components
        return stones.length - comp;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with multiple connections
        System.out.println("\nBasic Test Case:");
        int[][] stones1 = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println("Input: [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]");
        System.out.println("Output: " + removeStones(stones1));
        System.out.println("Expected: 5");

        // Test Case 2: Edge case with single stone
        System.out.println("\nSingle Stone Case:");
        int[][] stones2 = {{0,0}};
        System.out.println("Input: [[0,0]]");
        System.out.println("Output: " + removeStones(stones2));
        System.out.println("Expected: 0");

        // Test Case 3: Special case with no connections possible
        System.out.println("\nNo Connections Case:");
        int[][] stones3 = {{0,0},{1,1},{2,2}};
        System.out.println("Input: [[0,0],[1,1],[2,2]]");
        System.out.println("Output: " + removeStones(stones3));
        System.out.println("Expected: 0");

        // Test Case 4: Case where all stones are connected
        System.out.println("\nAll Connected Case:");
        int[][] stones4 = {{0,0},{0,1},{0,2},{1,0},{2,0}};
        System.out.println("Input: [[0,0],[0,1],[0,2],[1,0],[2,0]]");
        System.out.println("Output: " + removeStones(stones4));
        System.out.println("Expected: 4");

        // Test Case 5: Test with maximum coordinate values
        System.out.println("\nLarge Coordinates Case:");
        int[][] stones5 = {{0,0},{10000,10000},{10000,0},{0,10000}};
        System.out.println("Input: [[0,0],[10000,10000],[10000,0],[0,10000]]");
        System.out.println("Output: " + removeStones(stones5));
        System.out.println("Expected: 3");
    }
}
