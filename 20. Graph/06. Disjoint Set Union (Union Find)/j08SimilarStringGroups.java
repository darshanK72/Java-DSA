/**
 * LeetCode 839. Similar String Groups (DSU for Connected Components)
 *
 * Problem Statement:
 *     Given a list of strings strs, group all similar strings together. Two
 *     strings are similar if they have the same length and differ by at most one
 *     swap of characters. Return the number of such groups.
 *
 * Input:
 *     - strs: String[], 1 <= strs.length <= 300, 1 <= strs[i].length <= 300
 *
 * Output:
 *     - int: Number of similar string groups
 *
 * Example:
 *     Input: ["tars","rats","arts","star"]
 *     Output: 2
 *
 *     Explanation:
 *         "tars" is similar to "rats" and "arts".
 *         "star" is not similar to any of them.
 *         So, {"tars", "rats", "arts"} is one group, and {"star"} is another.
 */

import java.util.*;

public class j08SimilarStringGroups {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which strings belong to the same group/component.
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
     * @param n    Number of strings
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1); // Each string is initially its own root
            Arrays.fill(ranks, 1);    // Each component starts with size 1
        }
        /**
         * Finds the root of the node with path compression.
         */
        public int find(int node) {
            if (parents[node] == -1) return node;
            return parents[node] = find(parents[node]);
        }
        /**
         * Unites the sets containing node1 and node2 using union by size.
         */
        public void union(int node1, int node2) {
            int node1Parent = find(node1);
            int node2Parent = find(node2);
            if (node1Parent == node2Parent) return;
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
     * Approach: DSU for Connected Components
     *
     * Intuition:
     * - Each string is a node in a graph. An edge exists between two nodes if the
     *   strings are similar. The problem is to find the number of connected
     *   components in this graph.
     *
     * Explanation:
     * - For each pair of strings, check if they are similar.
     * - If they are, union their components in the DSU.
     * - After processing all pairs, count the number of unique roots.
     *
     * Time Complexity: O(n^2 * L * α(n)), n = #strs, L = str length
     * Space Complexity: O(n)
     *
     * @param strs String[]: List of strings
     * @return     int: Number of similar string groups
     */
    public static int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        // Compare each pair of strings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // If similar, merge their components
                if (isSimilar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }
        int groups = 0;
        // Count number of unique roots (groups)
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) {
                groups++;
            }
        }
        return groups;
    }

    /**
     * Helper Method: isSimilar
     *
     * Intuition:
     * - Two strings are similar if they differ at exactly 0 or 2 positions.
     * - 0 differences means they are identical, 2 differences means one swap.
     *
     * Explanation:
     * - Count the number of positions where characters differ.
     * - If count is 0 or 2, they are similar; otherwise, they are not.
     *
     * Time Complexity: O(L), L = string length
     * Space Complexity: O(1)
     *
     * @param s1 First string
     * @param s2 Second string
     * @return   boolean: true if strings are similar
     */
    private static boolean isSimilar(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 0 || diff == 2;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        String[] strs1 = {"tars","rats","arts","star"};
        System.out.println("Input: [tars,rats,arts,star]");
        System.out.println("Expected: 2, Output: " + numSimilarGroups(strs1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        String[] strs2 = {"a"};
        System.out.println("Input: [a]");
        System.out.println("Expected: 1, Output: " + numSimilarGroups(strs2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        String[] strs3 = new String[300];
        for (int i = 0; i < 300; i++) strs3[i] = "a";
        System.out.println("Input: 300 identical strings");
        System.out.println("Expected: 1, Output: " + numSimilarGroups(strs3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        String[] strs4 = {"abc","acb","bac","bca","cab","cba"};
        System.out.println("Input: all permutations of 'abc'");
        System.out.println("Expected: 1, Output: " + numSimilarGroups(strs4));
    }
}
