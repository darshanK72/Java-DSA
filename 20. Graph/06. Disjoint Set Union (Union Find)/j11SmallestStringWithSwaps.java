/**
 * LeetCode 1202. Smallest String With Swaps
 *
 * Problem Statement:
 *     You are given a string s, and an array of pairs of indices in the string pairs 
 *     where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string. You can 
 *     swap the characters at any pair of indices in the given pairs any number of 
 *     times. Return the lexicographically smallest string that s can be changed to 
 *     after using the swaps.
 * 
 * Input:
 *     - s: String containing lowercase English letters (1 <= s.length <= 10^5)
 *     - pairs: List<List<Integer>> where pairs[i] = [a, b] (0 <= a, b < s.length)
 * 
 * Output:
 *     - String: Lexicographically smallest possible string after performing swaps
 * 
 * Example:
 *     Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 *     Output: "abcd"
 * 
 *     Explanation:
 *     - Swap s[0] and s[3], s = "bcad"
 *     - Swap s[1] and s[2], s = "bacd"
 *     - Swap s[0] and s[2], s = "abcd"
 *     After these swaps, we get the lexicographically smallest string "abcd".
 */

import java.util.*;

public class j11SmallestStringWithSwaps {

    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - DSU efficiently tracks connected components of indices.
     * - Allows grouping of indices that can be swapped.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the two components, keeping rank balanced.
     *
     * Time Complexity: O(α(N)) per operation (amortized constant for N indices)
     * Space Complexity: O(N)
     *
     * @param n    Number of elements (length of string s)
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;

        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1); // Each element is initially its own root
            Arrays.fill(ranks, 1);   // Initial rank of each component is 1
        }

        /**
         * Finds the root of the node with path compression.
         * If node is not a root, recursively finds and sets its parent to the root.
         */
        public int find(int node) {
            // If node is a root (parent is -1), return node itself
            if (parents[node] == -1)
                return node;
            // Path compression: recursively find root and set parent directly to root
            parents[node] = find(parents[node]);
            return parents[node]; // Return the root of the component
        }

        /**
         * Unites the sets containing node1 and node2, balancing by rank.
         * Ensures minimal tree height for efficient operations.
         */
        public void union(int node1, int node2) {
            int node1Parent = find(node1);
            int node2Parent = find(node2);
            if (node1Parent == node2Parent)
                return;
            // Attach smaller rank tree under larger rank tree
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
     * Approach: DSU for Grouping and Sorting
     *
     * Intuition:
     * - Use DSU to group indices that can be swapped.
     * - Sort characters within each group to achieve lexicographical order.
     *
     * Explanation:
     * - For each pair in pairs, union the indices in DSU.
     * - Group indices by their root and sort characters in each group.
     * - Reconstruct the string by replacing characters with sorted group values.
     *
     * Time Complexity: O(N log N + M), N = s.length, M = pairs.size
     * Space Complexity: O(N)
     *
     * @param s      String: Input string
     * @param pairs  List<List<Integer>>: Pairs of indices that can be swapped
     * @return       String: Lexicographically smallest string after swaps
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // Initialize DSU for the length of the string
        DisjointSetUnion dsu = new DisjointSetUnion(s.length());

        // Merge indices based on the given pairs
        for (List<Integer> pair : pairs) {
            dsu.union(pair.get(0), pair.get(1));
        }

        // Map to group characters by their root
        Map<Integer, List<Character>> charMap = new HashMap<>();
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        // Group characters and indices by their root
        for (int i = 0; i < s.length(); i++) {
            int root = dsu.find(i);
            charMap.computeIfAbsent(root, k -> new ArrayList<>()).add(s.charAt(i));
            indexMap.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        // Sort characters in each group
        for (int key : charMap.keySet()) {
            Collections.sort(charMap.get(key));
        }

        // Reconstruct the string with sorted characters
        char[] result = new char[s.length()];
        for (int key : charMap.keySet()) {
            List<Character> chars = charMap.get(key);
            List<Integer> indices = indexMap.get(key);
            for (int i = 0; i < chars.size(); i++) {
                result[indices.get(i)] = chars.get(i);
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        String s1 = "dcab";
        List<List<Integer>> pairs1 = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2));
        System.out.println("Input: s=dcab, pairs=[[0,3],[1,2],[0,2]]");
        System.out.println("Expected: abcd, Output: " + new j11SmallestStringWithSwaps().smallestStringWithSwaps(s1, pairs1));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        String s2 = "a";
        List<List<Integer>> pairs2 = new ArrayList<>();
        System.out.println("Input: s=a, pairs=[]");
        System.out.println("Expected: a, Output: " + new j11SmallestStringWithSwaps().smallestStringWithSwaps(s2, pairs2));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        String s3 = "cba";
        List<List<Integer>> pairs3 = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2));
        System.out.println("Input: s=cba, pairs=[[0,1],[1,2]]");
        System.out.println("Expected: abc, Output: " + new j11SmallestStringWithSwaps().smallestStringWithSwaps(s3, pairs3));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        String s4 = "dcab";
        List<List<Integer>> pairs4 = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2));
        System.out.println("Input: s=dcab, pairs=[[0,3],[1,2]]");
        System.out.println("Expected: bacd, Output: " + new j11SmallestStringWithSwaps().smallestStringWithSwaps(s4, pairs4));
    }
}
