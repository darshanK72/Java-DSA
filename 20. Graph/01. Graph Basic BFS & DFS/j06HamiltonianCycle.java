/**
 * Hamiltonian Cycle in an Undirected Graph
 *
 * Problem Statement:
 *     Given an undirected graph with n vertices and m edges, determine if there exists a Hamiltonian Cycle.
 *     A Hamiltonian Cycle is a cycle that visits each vertex exactly once and returns to the starting vertex.
 *
 * Input:
 *     - n (1 <= n <= 20): Number of vertices
 *     - m (0 <= m <= n*(n-1)/2): Number of edges
 *     - edges: List of m edges, each edge is a pair [u, v] (1-based indexing)
 *
 * Output:
 *     - Boolean: true if a Hamiltonian Cycle exists, false otherwise
 *
 * Example:
 *     Input: n = 4, m = 4, edges = [[1,2],[2,3],[3,4],[4,1]]
 *     Output: true
 *
 *     Explanation:
 *     The cycle 1-2-3-4-1 is a Hamiltonian Cycle.
 */

import java.util.*;

public class j06HamiltonianCycle {

    /**
     * Approach: Backtracking DFS
     *
     * Intuition:
     * - Try to start a cycle from every vertex. Use DFS to visit all vertices exactly once and check if you can return to the start.
     *
     * Explanation:
     * - Step 1: Build adjacency list for the graph.
     * - Step 2: For each vertex, start DFS and track visited vertices.
     * - Step 3: If all vertices are visited and there is an edge back to the start, return true.
     * - Step 4: If no such cycle exists, return false.
     *
     * Time Complexity: O(n!) (worst case, all permutations)
     * Space Complexity: O(n) for recursion stack and visited set
     *
     * @param n      Number of vertices
     * @param m      Number of edges
     * @param edges  List of edges
     * @return       true if Hamiltonian Cycle exists, false otherwise
     */
    public static boolean checkHamiltonianCycle(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<Integer>[] adj = new ArrayList[n + 1]; // Adjacency list (1-based)
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list
        // Build the adjacency list from edge list
        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0); // Edge start
            int to = edge.get(1);   // Edge end
            adj[from].add(to);      // Add edge both ways (undirected)
            adj[to].add(from);
        }
        HashSet<Integer> visited = new HashSet<>(); // Track visited nodes
        // Try to start a Hamiltonian Cycle from every vertex
        for (int i = 1; i <= n; i++) {
            if (checkForHamiltonianCycle(adj, visited, i, i, n)) // If cycle found, return true
                return true;
        }
        return false; // No Hamiltonian Cycle found
    }

    /**
     * Helper Method: DFS for Hamiltonian Cycle
     *
     * Intuition:
     * - Recursively visit all unvisited neighbors, tracking visited nodes. If all nodes are visited, check for edge to start.
     *
     * Explanation:
     * - If all nodes are visited and there is an edge to the start, return true.
     * - Backtrack after exploring each path.
     *
     * Time Complexity: O(n!)
     * Space Complexity: O(n)
     *
     * @param adj      Adjacency list
     * @param visited  Set of visited nodes
     * @param start    Starting node
     * @param node     Current node
     * @param n        Number of vertices
     * @return         true if cycle covers all nodes and returns to start, false otherwise
     */
    public static boolean checkForHamiltonianCycle(ArrayList<Integer>[] adj, HashSet<Integer> visited, int start, int node, int n) {
        visited.add(node); // Mark current node as visited
        if (visited.size() == n) { // If all nodes visited
            if (adj[node].contains(start)) { // Check if there is an edge back to start
                return true;
            }
            return false;
        }
        for (int neb : adj[node]) { // Explore all neighbors
            if (!visited.contains(neb)) { // If neighbor not visited
                if (checkForHamiltonianCycle(adj, visited, start, neb, n)) { // Recurse
                    return true;
                }
            }
        }
        visited.remove(node); // Backtrack after exploring all neighbors
        return false;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        ArrayList<ArrayList<Integer>> edges1 = new ArrayList<>();
        edges1.add(new ArrayList<>(Arrays.asList(1,2)));
        edges1.add(new ArrayList<>(Arrays.asList(2,3)));
        edges1.add(new ArrayList<>(Arrays.asList(3,4)));
        edges1.add(new ArrayList<>(Arrays.asList(4,1)));
        System.out.println("Input: n=4, m=4, edges=[[1,2],[2,3],[3,4],[4,1]], Expected: true, Output: " + checkHamiltonianCycle(4, 4, edges1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();
        System.out.println("Input: n=1, m=0, edges=[], Expected: false, Output: " + checkHamiltonianCycle(1, 0, edges2));
        ArrayList<ArrayList<Integer>> edges3 = new ArrayList<>();
        edges3.add(new ArrayList<>(Arrays.asList(1,2)));
        System.out.println("Input: n=2, m=1, edges=[[1,2]], Expected: false, Output: " + checkHamiltonianCycle(2, 1, edges3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        ArrayList<ArrayList<Integer>> edges4 = new ArrayList<>();
        for (int i = 1; i < 20; i++) edges4.add(new ArrayList<>(Arrays.asList(i, i+1)));
        edges4.add(new ArrayList<>(Arrays.asList(20,1)));
        System.out.println("Input: n=20, m=20, cycle, Output: " + checkHamiltonianCycle(20, 20, edges4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        ArrayList<ArrayList<Integer>> edges5 = new ArrayList<>();
        edges5.add(new ArrayList<>(Arrays.asList(1,2)));
        edges5.add(new ArrayList<>(Arrays.asList(2,3)));
        edges5.add(new ArrayList<>(Arrays.asList(3,1)));
        System.out.println("Input: n=3, m=3, triangle, Output: " + checkHamiltonianCycle(3, 3, edges5));
    }
}
