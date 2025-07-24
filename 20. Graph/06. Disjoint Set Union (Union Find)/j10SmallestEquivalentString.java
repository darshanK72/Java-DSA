/**
 * LeetCode 1061. Lexicographically Smallest Equivalent String (DSU)
 *
 * Problem Statement:
 *     You are given two strings s and t of the same length, and a base string str.
 *     s[i] and t[i] are equivalent. Find the lexicographically smallest equivalent
 *     string of str by replacing each character with the smallest equivalent
 *     character in its equivalence class.
 *
 * Input:
 *     - s: String, t: String, str: String (all lowercase, 1 <= s.length == t.length <= 1000)
 *
 * Output:
 *     - String: Lexicographically smallest equivalent string
 *
 * Example:
 *     Input: s = "parker", t = "morris", str = "parser"
 *     Output: "makkek"
 *
 *     Explanation:
 *         Equivalence classes: {a,o}, {e,i}, {k,r,s}, {m,p},
 *         Replace each character in str with the smallest in its class.
 */

import java.util.*;

public class j10SmallestEquivalentString {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks equivalence classes of characters.
     * - Always merge to the lexicographically smallest root for minimal result.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the two classes, always keeping the smaller as root.
     *
     * Time Complexity: O(α(26)) per operation (constant for 26 letters)
     * Space Complexity: O(26)
     *
     * @param n    Number of characters (26 for a-z)
     */
    public static class DisjointSetUnion {
        int[] parents;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            Arrays.fill(parents, -1); // Each character is initially its own root
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
         * Unites the sets containing node1 and node2, always keeping the smaller as root.
         * Ensures lexicographically smallest character is the root of each class.
         */
        public void union(int node1, int node2) {
            int node1Parent = find(node1);
            int node2Parent = find(node2);
            if (node1Parent == node2Parent)
                return;
            // Always attach the larger root to the smaller root for lex order
            if (node1Parent < node2Parent) {
                parents[node2Parent] = node1Parent;
            } else {
                parents[node1Parent] = node2Parent;
            }
        }
    }

    /**
     * Approach: DSU for Lexicographically Smallest Equivalence
     *
     * Intuition:
     * - Merge equivalence classes for each pair (s[i], t[i]).
     * - For each character in str, replace with the smallest in its class.
     *
     * Explanation:
     * - For each i, union s[i] and t[i] in DSU.
     * - For each character in str, find its root and convert to char.
     * - Handles edge cases: all characters distinct, all characters equivalent.
     *
     * Time Complexity: O(L + N), L = str.length, N = s.length
     * Space Complexity: O(26)
     *
     * @param s   String: First equivalence string
     * @param t   String: Second equivalence string
     * @param str String: Base string to transform
     * @return    String: Lexicographically smallest equivalent string
     */
    public static String smallestString(String s, String t, String str) {
        DisjointSetUnion dsu = new DisjointSetUnion(26); // 26 lowercase letters
        // Merge equivalence classes for each pair (s[i], t[i])
        for (int i = 0; i < s.length(); i++) {
            dsu.union(s.charAt(i) - 'a', t.charAt(i) - 'a');
        }
        StringBuilder out = new StringBuilder();
        // For each character in str, find the smallest equivalent
        for (int i = 0; i < str.length(); i++) {
            out.append((char) (dsu.find(str.charAt(i) - 'a') + 'a'));
        }
        return out.toString();
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        String s1 = "parker", t1 = "morris", str1 = "parser";
        System.out.println("Input: s=parker, t=morris, str=parser");
        System.out.println("Expected: makkek, Output: " + smallestString(s1, t1, str1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        String s2 = "a", t2 = "b", str2 = "a";
        System.out.println("Input: s=a, t=b, str=a");
        System.out.println("Expected: a, Output: " + smallestString(s2, t2, str2));
        String s3 = "a", t3 = "b", str3 = "b";
        System.out.println("Input: s=a, t=b, str=b");
        System.out.println("Expected: a, Output: " + smallestString(s3, t3, str3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        String s4 = "abcdefghijklmnopqrstuvwxyz", t4 = "abcdefghijklmnopqrstuvwxyz", str4 = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("Input: all letters, all self-equivalent");
        System.out.println("Expected: abcdefghijklmnopqrstuvwxyz, Output: " + smallestString(s4, t4, str4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        String s5 = "abc", t5 = "bcd", str5 = "cda";
        System.out.println("Input: s=abc, t=bcd, str=cda");
        System.out.println("Expected: aba, Output: " + smallestString(s5, t5, str5));
    }
}
