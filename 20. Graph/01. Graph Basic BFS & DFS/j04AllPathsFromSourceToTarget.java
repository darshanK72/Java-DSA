/**
 * LeetCode 797. All Paths From Source to Target
 * 
 * Problem Statement:
 *     Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
 *     find all possible paths from node 0 to node n - 1 and return them in any order.
 *     The graph is given as a 2D array, where graph[i] is a list of all nodes
 *     you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 * 
 * Input:
 *     - graph (2 <= n <= 15): int[][], adjacency list of the DAG
 * 
 * Output:
 *     - List<List<Integer>>: All possible paths from node 0 to node n-1
 * 
 * Example:
 *     Input: graph = [[1,2],[3],[3],[]]
 *     Output: [[0,1,3],[0,2,3]]
 * 
 *     Explanation:
 *     There are two paths from 0 to 3:
 *     - 0 -> 1 -> 3
 *     - 0 -> 2 -> 3
 */

import java.util.*;

public class j04AllPathsFromSourceToTarget {

    /**
     * Approach: DFS Backtracking
     * 
     * Intuition:
     * - Use DFS to explore all possible paths from source (0) to target (n-1).
     * - Backtrack after visiting each node to explore alternative paths.
     * 
     * Explanation:
     * - Start DFS from node 0, keep track of current path.
     * - When target node is reached, add the path to the result list.
     * - For each neighbor, recursively continue DFS.
     * - Backtrack by removing the last node after recursion.
     * 
     * Time Complexity: O(2^n * n) in the worst case (exponential number of paths).
     * Space Complexity: O(2^n * n) for storing all paths and recursion stack.
     * 
     * @param graph    Adjacency list of the DAG (2 <= n <= 15)
     * @return         List of all paths from node 0 to node n-1
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> out = new ArrayList<>(); // Stores all valid paths
        ArrayList<Integer> curr = new ArrayList<>(); // Current path being explored
        dfsTraversal(graph, 0, graph.length - 1, out, curr);
        return out;
    }

    /**
     * Helper Method: DFS Traversal
     * 
     * Intuition:
     * - Recursively explore all neighbors from the current node.
     * - Add node to path, recurse, then backtrack.
     * 
     * Explanation:
     * - Add current node to path.
     * - If destination is reached, add a copy of path to output.
     * - For each neighbor, recursively call DFS.
     * - Remove current node from path before returning (backtrack).
     * 
     * Time Complexity: O(2^n * n)
     * Space Complexity: O(n) for recursion stack
     * 
     * @param graph    Adjacency list
     * @param src      Current node
     * @param dest     Target node
     * @param out      Output list of paths
     * @param curr     Current path
     */
    private static void dfsTraversal(int[][] graph, int src, int dest, List<List<Integer>> out,
            ArrayList<Integer> curr) {
        curr.add(src); // Add current node to path
        if (src == dest) {
            out.add(new ArrayList<>(curr)); // Found a path, add a copy
        } else {
            for (int node : graph[src]) {
                dfsTraversal(graph, node, dest, out, curr); // Recurse for neighbors
            }
        }
        curr.remove(curr.size() - 1); // Backtrack
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] graph1 = {{1,2},{3},{3},{}};
        System.out.println("Input: [[1,2],[3],[3],[]], Expected: [[0,1,3],[0,2,3]], Output: " + allPathsSourceTarget(graph1));

        int[][] graph2 = {{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println("Input: [[4,3,1],[3,2,4],[3],[4],[]], Output: " + allPathsSourceTarget(graph2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] graph3 = {{1}, {}}; // Only one path
        System.out.println("Input: [[1],[]], Expected: [[0,1]], Output: " + allPathsSourceTarget(graph3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] graph4 = {{}}; // Single node
        System.out.println("Input: [[]], Expected: [[0]], Output: " + allPathsSourceTarget(graph4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] graph5 = {{1},{2},{3},{}}; // Linear graph
        System.out.println("Input: [[1],[2],[3],[]], Expected: [[0,1,2,3]], Output: " + allPathsSourceTarget(graph5));
    }
}
