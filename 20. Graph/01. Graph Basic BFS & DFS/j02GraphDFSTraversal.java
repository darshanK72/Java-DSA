/**
 * Depth-First Search (DFS) Traversal of a Graph
 *
 * Problem Statement:
 *     Given an undirected graph represented as an adjacency list, perform a
 *     depth-first search (DFS) traversal starting from node 0 and return the
 *     order in which nodes are visited.
 *
 * Input:
 *     - adj: ArrayList<ArrayList<Integer>> adjacency list where adj.get(i)
 *       contains all nodes adjacent to node i. The graph may be disconnected.
 *
 * Output:
 *     - ArrayList<Integer> containing the order of nodes visited in DFS
 *       traversal starting from node 0.
 *
 * Example:
 *     Input: adj = [[1,2],[0,3],[0,3],[1,2]]
 *     Output: [0,1,3,2]
 *
 *     Explanation:
 *     - The graph is:
 *         0---1
 *         |   |
 *         2---3
 *     - Starting from 0, one possible DFS order is 0,1,3,2.
 */

import java.util.*;

public class j02GraphDFSTraversal {
    /**
     * Approach: Recursive DFS Traversal
     *
     * Intuition:
     * - DFS explores as far as possible along each branch before backtracking.
     *   By marking nodes as visited, we avoid cycles and redundant visits.
     * - We use a helper method to recursively visit all unvisited neighbors of
     *   the current node, adding each node to the output list as it is visited.
     *
     * Explanation:
     * - The main dfs() method initializes the visited array and output list.
     * - It calls the helper dfsGraphTraversal() starting from node 0.
     * - The helper marks the current node as visited, adds it to the output,
     *   and recursively visits all unvisited neighbors.
     * - The traversal order depends on the order of neighbors in the adjacency
     *   list.
     *
     * Time Complexity: O(n + e)
     *   - Each node (n) is visited at most once.
     *   - Each edge (e) is traversed at most twice (once from each endpoint).
     *   - Thus, the total work is proportional to the sum of nodes and edges.
     *
     * Space Complexity: O(n + e)
     *   - The adjacency list uses O(n + e) space.
     *   - The visited array uses O(n) space.
     *   - The recursion stack can be up to O(n) deep in the worst case (for a
     *     path graph).
     *   - The output list uses O(n) space for storing the traversal order.
     *
     * @param adj Adjacency list of the graph
     * @return    List of nodes in DFS traversal order starting from node 0
     */
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> out = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        dfsGraphTraversal(adj, visited, 0, out);
        return out;
    }

    /**
     * Helper Method: Recursive DFS
     *
     * Intuition:
     * - Recursively visit all unvisited neighbors of the current node.
     * - Mark nodes as visited to avoid cycles and redundant visits.
     *
     * Explanation:
     * - If the current node is already visited, return immediately.
     * - Otherwise, add the node to the output and mark it as visited.
     * - For each neighbor, recursively call the helper if it is unvisited.
     *
     * Time Complexity: O(n + e)
     *   - Each node and edge is processed at most once.
     *
     * Space Complexity: O(n)
     *   - The recursion stack can be up to O(n) deep.
     *
     * @param adj      Adjacency list
     * @param visited  Visited nodes array
     * @param currNode Current node being visited
     * @param out      Output list for traversal order
     */
    private void dfsGraphTraversal(ArrayList<ArrayList<Integer>> adj, boolean[] visited,
            int currNode, ArrayList<Integer> out) {
        if (visited[currNode])
            return;
        out.add(currNode);
        visited[currNode] = true;
        for (int node : adj.get(currNode)) {
            dfsGraphTraversal(adj, visited, node, out);
        }
    }

    public static void main(String[] args) {
        j02GraphDFSTraversal solver = new j02GraphDFSTraversal();

        // Test Case 1: Basic case (connected graph)
        System.out.println("\nBasic Test Case:");
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) adj1.add(new ArrayList<>());
        adj1.get(0).add(1); adj1.get(0).add(2);
        adj1.get(1).add(0); adj1.get(1).add(3);
        adj1.get(2).add(0); adj1.get(2).add(3);
        adj1.get(3).add(1); adj1.get(3).add(2);
        System.out.println("Input: [[1,2],[0,3],[0,3],[1,2]], Expected: [0,1,3,2], Output: " +
            solver.dfs(adj1));

        // Test Case 2: Edge case (single node)
        System.out.println("\nEdge Case (Single node):");
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>());
        System.out.println("Input: [[]], Expected: [0], Output: " + solver.dfs(adj2));

        // Test Case 3: Boundary case (disconnected graph)
        System.out.println("\nBoundary Case (Disconnected graph):");
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj3.add(new ArrayList<>());
        adj3.get(0).add(1);
        adj3.get(1).add(0);
        // Node 2 is disconnected
        System.out.println("Input: [[1],[0],[]], Expected: [0,1], Output: " + solver.dfs(adj3));

        // Test Case 4: Special case (cycle)
        System.out.println("\nSpecial Case (Cycle):");
        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj4.add(new ArrayList<>());
        adj4.get(0).add(1); adj4.get(1).add(2); adj4.get(2).add(0);
        adj4.get(1).add(0); adj4.get(2).add(1); adj4.get(0).add(2);
        System.out.println("Input: [[1,2],[0,2],[1,0]], Output: " + solver.dfs(adj4));
    }
}
