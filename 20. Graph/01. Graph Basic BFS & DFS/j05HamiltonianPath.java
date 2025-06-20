/**
 * Hamiltonian Path in an Undirected Graph
 *
 * Problem Statement:
 *     Given an undirected graph with n vertices and m edges, determine if there exists a Hamiltonian Path.
 *     A Hamiltonian Path is a path that visits each vertex exactly once.
 *
 * Input:
 *     - n (1 <= n <= 20): Number of vertices
 *     - m (0 <= m <= n*(n-1)/2): Number of edges
 *     - edges: List of m edges, each edge is a pair [u, v] (1-based indexing)
 *
 * Output:
 *     - Boolean: true if a Hamiltonian Path exists, false otherwise
 *
 * Example:
 *     Input: n = 4, m = 3, edges = [[1,2],[2,3],[3,4]]
 *     Output: true
 *
 *     Explanation:
 *     The path 1-2-3-4 is a Hamiltonian Path.
 */

import java.util.*;

public class j05HamiltonianPath {

    /**
     * Approach: Backtracking DFS
     *
     * Intuition:
     * - Try to start a path from every vertex. Use DFS to visit all vertices exactly once.
     * - If a path covers all vertices, it is a Hamiltonian Path.
     *
     * Explanation:
     * - Step 1: Build adjacency list for the graph.
     * - Step 2: For each vertex, start DFS and track visited vertices.
     * - Step 3: If all vertices are visited in a path, return true.
     * - Step 4: If no such path exists, return false.
     *
     * Time Complexity: O(n!) (worst case, all permutations)
     * Space Complexity: O(n) for recursion stack and visited set
     *
     * @param n      Number of vertices
     * @param m      Number of edges
     * @param edges  List of edges
     * @return       true if Hamiltonian Path exists, false otherwise
     */
    public static boolean check(int n, int m, ArrayList<ArrayList<Integer>> edges) {
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

        // Try to start a Hamiltonian Path from every vertex
        for (int i = 1; i <= n; i++) {
            HashSet<Integer> visited = new HashSet<>(); // Track visited nodes
            if (checkForHamiltonianPath(adj, visited, i)) // If path found, return true
                return true;
        }
        return false; // No Hamiltonian Path found
    }

    /**
     * Helper Method: DFS for Hamiltonian Path
     *
     * Intuition:
     * - Recursively visit all unvisited neighbors, tracking visited nodes.
     *
     * Explanation:
     * - If all nodes are visited, return true.
     * - Backtrack after exploring each path.
     *
     * Time Complexity: O(n!)
     * Space Complexity: O(n)
     *
     * @param adj      Adjacency list
     * @param visited  Set of visited nodes
     * @param node     Current node
     * @return         true if path covers all nodes, false otherwise
     */
    public static boolean checkForHamiltonianPath(ArrayList<Integer>[] adj, HashSet<Integer> visited, int node) {
        visited.add(node); // Mark current node as visited
        if (visited.size() == adj.length - 1) { // If all nodes visited
            visited.remove(node); // Backtrack before returning
            return true;
        }
        for (int neb : adj[node]) { // Explore all neighbors
            if (!visited.contains(neb)) { // If neighbor not visited
                if (checkForHamiltonianPath(adj, visited, neb)) { // Recurse
                    visited.remove(node); // Backtrack before returning
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
        System.out.println("Input: n=4, m=3, edges=[[1,2],[2,3],[3,4]], Expected: true, Output: " + check(4, 3, edges1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();
        System.out.println("Input: n=1, m=0, edges=[], Expected: true, Output: " + check(1, 0, edges2));
        ArrayList<ArrayList<Integer>> edges3 = new ArrayList<>();
        edges3.add(new ArrayList<>(Arrays.asList(1,2)));
        System.out.println("Input: n=2, m=1, edges=[[1,2]], Expected: true, Output: " + check(2, 1, edges3));
        ArrayList<ArrayList<Integer>> edges4 = new ArrayList<>();
        edges4.add(new ArrayList<>(Arrays.asList(1,2)));
        System.out.println("Input: n=2, m=0, edges=[], Expected: false, Output: " + check(2, 0, new ArrayList<>()));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        ArrayList<ArrayList<Integer>> edges5 = new ArrayList<>();
        for (int i = 1; i < 20; i++) edges5.add(new ArrayList<>(Arrays.asList(i, i+1)));
        System.out.println("Input: n=20, m=19, path, Output: " + check(20, 19, edges5));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        ArrayList<ArrayList<Integer>> edges6 = new ArrayList<>();
        edges6.add(new ArrayList<>(Arrays.asList(1,2)));
        edges6.add(new ArrayList<>(Arrays.asList(2,3)));
        edges6.add(new ArrayList<>(Arrays.asList(3,1)));
        System.out.println("Input: n=3, m=3, triangle, Output: " + check(3, 3, edges6));
    }
}
