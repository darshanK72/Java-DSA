/**
 * LeetCode 1627. Graph Connectivity With Threshold
 * 
 * Problem Statement:
 *     We have n cities labeled from 1 to n. Two different cities are connected if 
 *     and only if there exists a common divisor of both cities that is strictly 
 *     greater than the threshold. Given an array of queries (x,y), for each query
 *     determine if city x and city y are connected.
 * 
 * Input:
 *     - n (2 ≤ n ≤ 10^4): Number of cities
 *     - threshold (0 ≤ threshold ≤ n): Connection threshold value
 *     - queries[][] (1 ≤ queries.length ≤ 10^5): Array of [x,y] pairs
 * 
 * Output:
 *     - List<Boolean>: For each query, true if cities are connected, false otherwise
 * 
 * Example:
 *     Input: n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
 *     Output: [false,false,true]
 * 
 *     Explanation:
 *     Cities 3 and 6 are connected because 3 is their common divisor and 3 > 2.
 *     Other pairs have no common divisor greater than threshold.
 */

import java.util.*;

public class j11GraphConnectivityWithThreshold {
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
     * Approach: Union Find with GCD
     * 
     * Intuition:
     * - If two numbers share a common divisor > threshold, they should be connected
     * - Use DSU to maintain connected components
     * - For each number i, connect it with its multiples if GCD > threshold
     * 
     * Explanation:
     * - Step 1: Initialize DSU with n+1 nodes (1-based indexing)
     * - Step 2: For each number i from 1 to n:
     *     - Check its multiples j by incrementing by i
     *     - If GCD(i,j) > threshold, union i and j
     * - Step 3: For each query [x,y]:
     *     - Find parents of both nodes using path compression
     *     - If parents are same, cities are connected
     * 
     * Time Complexity: O(n * n/2 + q) where n is number of cities, q is queries length
     * Space Complexity: O(n) for DSU arrays
     * 
     * @param n          Number of cities (2 ≤ n ≤ 10^4)
     * @param threshold  Connection threshold (0 ≤ threshold ≤ n)
     * @param queries    Array of query pairs (1 ≤ queries.length ≤ 10^5)
     * @return          List of boolean indicating connectivity for each query
     */
    public static List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        // Initialize DSU with n+1 nodes (1-based indexing)
        DisjointSetUnion dsu = new DisjointSetUnion(n + 1);

        // Connect cities based on common divisors > threshold
        for (int i = 1; i <= n; i++) {
            for (int j = i + i; j <= n; j += i) {
                if (gcd(i, j) > threshold) {
                    dsu.union(i, j);
                }
            }
        }

        // Process queries
        List<Boolean> out = new ArrayList<>();
        for (int[] query : queries) {
            if (dsu.find(query[0]) == dsu.find(query[1])) {
                out.add(true);
            } else {
                out.add(false);
            }
        }

        return out;
    }

    /**
     * Helper Method: Calculate GCD using Euclidean algorithm
     * 
     * Intuition:
     * - GCD can be calculated recursively using remainder
     * - If b divides a evenly, b is the GCD
     * 
     * Explanation:
     * - Step 1: If b is 0, a is the GCD
     * - Step 2: Otherwise, recursively call GCD(b, a%b)
     * - Step 3: Continue until remainder becomes 0
     * 
     * Time Complexity: O(log(min(a,b)))
     * Space Complexity: O(log(min(a,b))) for recursion stack
     * 
     * @param a    First number
     * @param b    Second number
     * @return    Greatest Common Divisor of a and b
     */
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        System.out.println("Input: n=6, threshold=2, queries=[[1,4],[2,5],[3,6]]");
        System.out.println("Output: " + areConnected(6, 2, 
            new int[][]{{1,4},{2,5},{3,6}}));

        // Test Case 2: Edge case - threshold = 0
        System.out.println("\nEdge Case (threshold = 0):");
        System.out.println("Input: n=4, threshold=0, queries=[[1,2],[2,3]]");
        System.out.println("Output: " + areConnected(4, 0, 
            new int[][]{{1,2},{2,3}}));

        // Test Case 3: Large numbers
        System.out.println("\nLarge Numbers Case:");
        System.out.println("Input: n=100, threshold=10, queries=[[1,100],[10,20]]");
        System.out.println("Output: " + areConnected(100, 10, 
            new int[][]{{1,100},{10,20}}));
    }
}