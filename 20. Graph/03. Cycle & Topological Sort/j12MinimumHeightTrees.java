/**
 * LeetCode 310. Minimum Height Trees
 * 
 * Problem Statement:
 *     A tree is an undirected graph in which any two vertices are connected by exactly one path. Given a tree of n nodes labeled from 0 to n-1 and a list of n-1 edges, return all the root labels for all possible Minimum Height Trees (MHTs). You can return the answer in any order.
 * 
 * Input:
 *     - n (1 <= n <= 2 * 10^4): Number of nodes
 *     - edges: int[n-1][2] representing the undirected edges
 * 
 * Output:
 *     - List of all root labels for all possible Minimum Height Trees
 * 
 * Example:
 *     Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 *     Output: [1]
 * 
 *     Explanation:
 *     - If you choose node 1 as root, the height is minimized (height = 1).
 *     - Choosing any other node as root results in a higher tree.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;

public class j12MinimumHeightTrees {
    /**
     * Approach: Topological Trimming (Remove Leaves Layer by Layer)
     * 
     * Intuition:
     * - The centers of the tree (1 or 2 nodes) will be the roots of Minimum Height Trees.
     * - Remove leaves iteratively until 1 or 2 nodes remain.
     * 
     * Explanation:
     * - Step 1: Build the adjacency list for the tree.
     * - Step 2: Initialize indegree array to count neighbors for each node.
     * - Step 3: Add all leaves (nodes with indegree 1) to the queue.
     * - Step 4: Remove leaves layer by layer, updating indegrees and queue.
     * - Step 5: When 1 or 2 nodes remain, they are the centers (MHT roots).
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param n      Number of nodes (1 <= n <= 2 * 10^4)
     * @param edges  Edges of the tree
     * @return       List of all root labels for all possible Minimum Height Trees
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Build adjacency list for the tree
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each node
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Add edge from 'from' to 'to'
            adj[to].add(from); // Add edge from 'to' to 'from' (undirected)
        }

        // Special case: single node
        if (n == 1) {
            List<Integer> out = new ArrayList<>();
            out.add(0);
            return out;
        }

        // Initialize indegree array to count neighbors
        int[] indegrees = new int[n];
        for (int i = 0; i < n; i++) {
            for (int neb : adj[i]) {
                indegrees[neb]++;
            }
        }

        // Add all leaves (nodes with indegree 1) to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 1)
                queue.add(i);
        }

        int graphSize = n; // Number of nodes remaining in the graph

        // Remove leaves layer by layer
        while (graphSize > 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll(); // Remove leaf node
                graphSize--;
                for (int neb : adj[node]) {
                    indegrees[neb]--;
                    if (indegrees[neb] == 1) {
                        queue.add(neb); // Add new leaf to queue
                    }
                }
            }
        }

        // Collect remaining nodes as MHT roots
        ArrayList<Integer> out = new ArrayList<>();
        while (!queue.isEmpty())
            out.add(queue.poll());
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {{1,0},{1,2},{1,3}};
        System.out.println("Input: n=4, edges=" + Arrays.deepToString(edges1) + ", Expected: [1], Output: " + findMinHeightTrees(4, edges1));

        // Test Case 2: Edge case (single node)
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {};
        System.out.println("Input: n=1, edges=" + Arrays.deepToString(edges2) + ", Expected: [0], Output: " + findMinHeightTrees(1, edges2));

        // Test Case 3: Boundary case (line tree)
        System.out.println("\nBoundary Cases:");
        int[][] edges3 = {{0,1},{1,2},{2,3},{3,4}};
        System.out.println("Input: n=5, edges=" + Arrays.deepToString(edges3) + ", Expected: [2], Output: " + findMinHeightTrees(5, edges3));

        // Test Case 4: Special case (two centers)
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = {{0,1},{1,2},{2,3}};
        System.out.println("Input: n=4, edges=" + Arrays.deepToString(edges4) + ", Expected: [1,2], Output: " + findMinHeightTrees(4, edges4));
    }
}
