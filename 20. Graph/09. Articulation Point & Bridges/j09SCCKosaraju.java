/**
 * GeeksForGeeks : Kosaraju's Algorithm for Strongly Connected Components (SCC)
 * 
 * Problem Statement:
 *     Given a directed graph, find the number of strongly connected components (SCCs).
 *     A strongly connected component is a maximal group of nodes such that every node
 *     is reachable from every other node in the group.
 * 
 * Input:
 *     - adj: Adjacency list of the directed graph (0-based indexing)
 *            adj.size() = n (number of vertices)
 *            Each adj.get(i) contains a list of vertices j such that there is an edge i -> j
 * 
 * Output:
 *     - Integer: Number of strongly connected components in the graph
 * 
 * Example:
 *     Input: adj = [[1], [2], [0,3], [4], [5], [3]]
 *     Output: 2
 *     Explanation:
 *         SCCs are: {0,1,2}, {3,4,5}
 */

import java.util.*;

public class j09SCCKosaraju {
    /**
     * Approach: Kosaraju's Algorithm (DFS + Reverse Graph)
     * 
     * Intuition:
     * - Kosaraju's algorithm finds SCCs in two passes of DFS:
     *   1. Do a DFS and push nodes to a stack in post-order (finish time order).
     *   2. Reverse all edges, then do DFS in stack order to find SCCs.
     * 
     * Explanation:
     * - Step 1: DFS to fill stack with finish order.
     * - Step 2: Reverse all edges to get the transpose graph.
     * - Step 3: DFS on transpose graph in stack order, counting SCCs.
     * 
     * Time Complexity: O(V + E), where V = number of vertices, E = number of edges
     * Space Complexity: O(V + E), for adjacency lists, stack, and visited arrays
     * 
     * @param adj Adjacency list of the directed graph
     * @return    Number of strongly connected components
     */
    public static int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // First DFS to fill stack with finish order
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                dfs(adj, visited, i, stack);
            }
        }
        // Reverse all edges to get the transpose graph
        ArrayList<ArrayList<Integer>> adj2 = reverseEdges(adj);
        int scc = 0;
        boolean[] newVisited = new boolean[adj.size()];
        // DFS on transpose graph in stack order
        while (!stack.isEmpty()) {
            int src = stack.pop();
            if (!newVisited[src]) {
                scc++;
                countSCC(adj2, newVisited, src);
            }
        }
        return scc;
    }

    /**
     * Helper Method: DFS to fill stack with finish order
     * 
     * Intuition:
     * - Standard DFS, push node to stack after visiting all descendants.
     * 
     * Explanation:
     * - Used in the first pass to record finish times.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param adj     Adjacency list
     * @param visited Visited array
     * @param src     Current node
     * @param stack   Stack to record finish order
     */
    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int src, Stack<Integer> stack) {
        // Mark node as visited
        visited[src] = true;
        // Visit all unvisited neighbors
        for (int neb : adj.get(src)) {
            if (!visited[neb]) {
                dfs(adj, visited, neb, stack);
            }
        }
        // Push to stack after visiting all descendants
        stack.add(src);
    }

    /**
     * Helper Method: DFS to mark all nodes in an SCC
     * 
     * Intuition:
     * - Standard DFS to visit all nodes in the same SCC in the transpose graph.
     * 
     * Explanation:
     * - Used in the second pass to mark all nodes in a component.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param adj     Transpose adjacency list
     * @param visited Visited array
     * @param src     Current node
     */
    private static void countSCC(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int src) {
        // Mark node as visited
        visited[src] = true;
        // Visit all unvisited neighbors
        for (int neb : adj.get(src)) {
            if (!visited[neb]) {
                countSCC(adj, visited, neb);
            }
        }
    }

    /**
     * Helper Method: Reverse all edges in the graph
     * 
     * Intuition:
     * - For Kosaraju's second pass, we need the transpose of the graph.
     * 
     * Explanation:
     * - For each edge u -> v, add v -> u in the new adjacency list.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     * 
     * @param adj Original adjacency list
     * @return    Transpose adjacency list
     */
    private static ArrayList<ArrayList<Integer>> reverseEdges(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            adj2.add(new ArrayList<>());
        }
        for (int i = 0; i < adj.size(); i++) {
            for (int neb : adj.get(i)) {
                adj2.get(neb).add(i);
            }
        }
        return adj2;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) adj1.add(new ArrayList<>());
        adj1.get(0).add(1);
        adj1.get(1).add(2);
        adj1.get(2).add(0); adj1.get(2).add(3);
        adj1.get(3).add(4);
        adj1.get(4).add(5);
        adj1.get(5).add(3);
        System.out.println("Input: [[1],[2],[0,3],[4],[5],[3]], Expected: 2, Output: " + kosaraju(adj1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>()); // 0 nodes
        System.out.println("Input: [[]], Expected: 1, Output: " + kosaraju(adj2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj3.add(new ArrayList<>());
        adj3.get(0).add(1);
        adj3.get(1).add(2);
        System.out.println("Input: [[1],[2],[]], Expected: 3, Output: " + kosaraju(adj3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) adj4.add(new ArrayList<>());
        adj4.get(0).add(1);
        adj4.get(1).add(2);
        adj4.get(2).add(3);
        adj4.get(3).add(0);
        System.out.println("Input: [[1],[2],[3],[0]], Expected: 1, Output: " + kosaraju(adj4));
    }
}
