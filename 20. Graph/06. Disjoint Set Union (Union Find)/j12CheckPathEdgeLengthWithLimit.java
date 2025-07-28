/**
 * LeetCode 1697. Checking Existence of Edge Length Limited Paths
 * 
 * Problem Statement:
 *     Given an undirected graph and queries, determine for each query if there exists
 *     a path between two nodes such that the maximum edge weight along the path is 
 *     strictly less than the query's limit.
 * 
 * Input:
 *     - n (1 ≤ n ≤ 10^5): Number of nodes
 *     - edgeList[][]: Array of [node1, node2, weight] edges
 *     - queries[][]: Array of [node1, node2, limit] queries
 * 
 * Output:
 *     - boolean[]: For each query, true if path exists with all edges < limit
 * 
 * Example:
 *     Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8]], queries = [[0,1,2],[1,2,3]]
 *     Output: [true,false]
 * 
 *     Explanation:
 *     Query 1: Path from 0->1 exists with edge weight 2 < limit 2
 *     Query 2: No path from 1->2 exists with all edges < 3
 */

import java.util.Arrays;

public class j12CheckPathEdgeLengthWithLimit {
    /**
     * Helper class for Disjoint Set Union data structure
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;

        /**
         * Initialize DSU with n nodes
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
     * Approach: Sorted Queries with Union Find
     * 
     * Intuition:
     * - Sort queries and edges by weight/limit to process in order
     * - Use DSU to track connected components under current limit
     * 
     * Explanation:
     * - Step 1: Sort queries by limit while maintaining original indices
     * - Step 2: Sort edges by weight in ascending order
     * - Step 3: For each query:
     *     - Add all edges with weight < query limit to DSU
     *     - Check if query nodes are connected in DSU
     * 
     * Time Complexity: O(QlogQ + ElogE) where Q is queries length, E is edges length
     * Space Complexity: O(Q + N) for indices array and DSU
     * 
     * @param n          Number of nodes (1 ≤ n ≤ 10^5)
     * @param edgeList   Array of edges [node1, node2, weight]
     * @param queries    Array of queries [node1, node2, limit]
     * @return          Array of boolean indicating path existence for each query
     */
    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        // Create indices array to maintain original query order
        Integer[] indices = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++)
            indices[i] = i;

        // Sort queries by limit
        Arrays.sort(indices, (a, b) -> {
            if (queries[a][2] > queries[b][2])
                return 1;
            else if (queries[a][2] < queries[b][2])
                return -1;
            else
                return 0;
        });

        // Sort edges by weight
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);

        // Process queries in order of increasing limit
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        boolean[] out = new boolean[queries.length];
        int edgeIndex = 0;
        for (int i = 0; i < queries.length; i++) {
            int index = indices[i];
            int from = queries[index][0];
            int to = queries[index][1];
            int limit = queries[index][2];

            // Add all edges with weight < limit
            while (edgeIndex < edgeList.length && edgeList[edgeIndex][2] < limit) {
                dsu.union(edgeList[edgeIndex][0], edgeList[edgeIndex][1]);
                edgeIndex++;
            }

            // Check if nodes are connected
            out[index] = (dsu.find(from) == dsu.find(to));
        }
        return out;
    }
}
