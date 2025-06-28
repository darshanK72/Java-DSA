/**
 * LeetCode 2360. Longest Cycle in a Graph
 * 
 * Problem Statement:
 *     You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 *     The graph is represented with a given 0-indexed array edges of length n, indicating that there is a directed edge from node i to node edges[i].
 *     If edges[i] == -1, then node i does not have any outgoing edges.
 *     Return the length of the longest cycle in the graph. If no cycle exists, return -1.
 * 
 * Input:
 *     - edges: int[] (1 <= edges.length <= 10^5, -1 <= edges[i] < n)
 * 
 * Output:
 *     - int: Length of the longest cycle, or -1 if no cycle exists
 * 
 * Example:
 *     Input: edges = [3,3,4,2,3]
 *     Output: 3
 *     Explanation:
 *         The cycle is: 2 -> 4 -> 3 -> 2 (length 3)
 * 
 *     Input: edges = [2,-1,3,1]
 *     Output: -1
 *     Explanation:
 *         No cycle exists in the graph.
 */

import java.util.*;

public class j05LongestCycleInGraph {

    /**
     * Approach: DFS with Recursion Stack
     * 
     * Intuition:
     * - Use DFS to traverse the graph, keeping track of nodes in the current recursion stack.
     * - If we revisit a node in the current stack, a cycle is detected.
     * - Use a count array to track the depth of each node in the current path to compute cycle length.
     * 
     * Explanation:
     * - For each unvisited node, start DFS.
     * - Mark nodes as visited and add to recursion stack.
     * - If a node is revisited in the current stack, calculate the cycle length.
     * - Remove nodes from the stack after recursion.
     * - Track the maximum cycle length found.
     * 
     * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
     * Space Complexity: O(n), for visited, recStack, and count arrays.
     * 
     * @param edges    Directed graph as an array, edges[i] is the next node from i or -1
     * @return        Length of the longest cycle, or -1 if none exists
     */
    public static int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] visited = new boolean[n]; // Track visited nodes
        boolean[] recStack = new boolean[n]; // Track nodes in current recursion stack
        int[] count = new int[n]; // Track depth of each node in current path
        Arrays.fill(count, 1);
        int[] maxCycleLength = new int[1];
        maxCycleLength[0] = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                findLongestCycle(edges, visited, recStack, count, maxCycleLength, i);
            }
        }
        return maxCycleLength[0];
    }

    /**
     * Helper Method: findLongestCycle
     * 
     * Intuition:
     * - Recursively traverse the graph, marking nodes in the current path.
     * - If a node is revisited in the current path, a cycle is found.
     * 
     * Explanation:
     * - If node is in recStack, calculate cycle length.
     * - If already visited or no outgoing edge, return.
     * - Mark node as visited and add to recStack.
     * - Recurse to next node, updating count.
     * - Remove node from recStack after recursion.
     * 
     * Time Complexity: O(1) per call, O(n) overall.
     * Space Complexity: O(1) per call, O(n) overall.
     * 
     * @param edges    Graph edges
     * @param visited  Visited nodes
     * @param recStack Recursion stack
     * @param count    Depth count
     * @param max      Max cycle length (array for mutability)
     * @param src      Current node
     */
    private static void findLongestCycle(int[] edges, boolean[] visited, boolean[] recStack, int[] count, int[] max,
            int src) {
        if (recStack[src]) { // Cycle detected
            max[0] = Math.max(max[0], count[src] - count[edges[src]] + 1);
            return;
        }
        if (visited[src]) // Already visited
            return;
        if (edges[src] == -1) // No outgoing edge
            return;
        visited[src] = true; // Mark as visited
        recStack[src] = true; // Add to recursion stack
        count[edges[src]] = count[src] + 1; // Update depth for next node
        findLongestCycle(edges, visited, recStack, count, max, edges[src]); // Recurse
        recStack[src] = false; // Remove from recursion stack
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [3,3,4,2,3], Expected: 3, Output: " + longestCycle(new int[]{3,3,4,2,3}));
        System.out.println("Input: [2,-1,3,1], Expected: -1, Output: " + longestCycle(new int[]{2,-1,3,1}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [-1], Expected: -1, Output: " + longestCycle(new int[]{-1}));
        System.out.println("Input: [], Expected: -1, Output: " + longestCycle(new int[]{}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[] maxInput = new int[100000];
        Arrays.fill(maxInput, -1);
        System.out.println("Input: [100000x-1], Expected: -1, Output: " + longestCycle(maxInput));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [1,2,0], Expected: 3, Output: " + longestCycle(new int[]{1,2,0})); // Single cycle
        System.out.println("Input: [1,2,3,4,0], Expected: 5, Output: " + longestCycle(new int[]{1,2,3,4,0})); // Large cycle
    }
}
