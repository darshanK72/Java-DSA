/**
 * LeetCode 990. Satisfiability of Equality Equations (DSU)
 *
 * Problem Statement:
 *     Given an array of strings equations representing relationships between
 *     variables (a-z), where each equation is of the form "a==b" or "a!=b",
 *     determine if it is possible to assign values to variables such that all
 *     equations are satisfied.
 *
 * Input:
 *     - equations: String[], each string is "a==b" or "a!=b", 1 <= equations.length <= 500
 *
 * Output:
 *     - boolean: true if all equations can be satisfied, false otherwise
 *
 * Example:
 *     Input: ["a==b","b!=c","c==a"]
 *     Output: false
 *
 *     Explanation:
 *         a==b, c==a implies a==b==c, but b!=c is a contradiction.
 */

import java.util.*;

public class j08SatisfiabilityOfEqualityEquations {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks which variables are in the same equivalence class.
     * - Path compression and union by size keep operations nearly constant time.
     * - Visual: If a==b and b==c, all are merged into one set.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - ranks[i]: Size of the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the smaller component under the larger.
     *
     * Time Complexity: O(α(26)) per operation (constant for 26 variables)
     * Space Complexity: O(26)
     *
     * @param n    Number of variables (26 for a-z)
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1); // Each variable is initially its own root
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
     * Approach: DSU (Union-Find) for Equivalence Classes
     *
     * Intuition:
     * - For all equations of the form a==b, merge a and b into the same set.
     * - For all equations of the form a!=b, check if a and b are in the same set;
     *   if so, contradiction.
     *
     * Explanation:
     * - First, process all equalities and merge sets.
     * - Then, process all inequalities and check for contradictions.
     * - Handles edge cases: self-equality, all variables distinct, all variables equal.
     *
     * Time Complexity: O(k), k = #equations
     * Space Complexity: O(26)
     *
     * @param equations String[]: List of equations
     * @return         boolean: true if all equations can be satisfied
     */
    public boolean equationsPossible(String[] equations) {
        DisjointSetUnion dsu = new DisjointSetUnion(26); // 26 variables a-z
        // First, process all equalities
        for (String eqn : equations) {
            if (eqn.charAt(1) == '=') {
                // Merge the sets for variables eqn[0] and eqn[3]
                dsu.union(eqn.charAt(0) - 'a', eqn.charAt(3) - 'a');
            }
        }
        // Then, process all inequalities
        for (String eqn : equations) {
            if (eqn.charAt(1) == '!') {
                // If variables are in the same set, contradiction
                if (dsu.find(eqn.charAt(0) - 'a') == dsu.find(eqn.charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        String[] eq1 = {"a==b","b!=c","c==a"};
        System.out.println("Input: [a==b,b!=c,c==a]");
        System.out.println("Expected: false, Output: " + new j08SatisfiabilityOfEqualityEquations().equationsPossible(eq1));
        String[] eq2 = {"a==b","b==c","a==c"};
        System.out.println("Input: [a==b,b==c,a==c]");
        System.out.println("Expected: true, Output: " + new j08SatisfiabilityOfEqualityEquations().equationsPossible(eq2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        String[] eq3 = {"a==a"};
        System.out.println("Input: [a==a]");
        System.out.println("Expected: true, Output: " + new j08SatisfiabilityOfEqualityEquations().equationsPossible(eq3));
        String[] eq4 = {"a!=a"};
        System.out.println("Input: [a!=a]");
        System.out.println("Expected: false, Output: " + new j08SatisfiabilityOfEqualityEquations().equationsPossible(eq4));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        String[] eq5 = new String[26];
        for (int i = 0; i < 26; i++) eq5[i] = (char)('a'+i) + "==" + (char)('a'+i);
        System.out.println("Input: all self-equality");
        System.out.println("Expected: true, Output: " + new j08SatisfiabilityOfEqualityEquations().equationsPossible(eq5));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        String[] eq6 = {"a==b","b==c","c!=a"};
        System.out.println("Input: [a==b,b==c,c!=a]");
        System.out.println("Expected: false, Output: " + new j08SatisfiabilityOfEqualityEquations().equationsPossible(eq6));
    }
}
